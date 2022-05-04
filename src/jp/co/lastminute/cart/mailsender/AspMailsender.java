package jp.co.lastminute.cart.mailsender;
/*
 * AspzMailsender.java
 *
 * Created on 2002/07/26, 15:00
 */

import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
//////////////////////////////////////
import javax.servlet.*;
import javax.servlet.http.*;
///////////////////////////////////////
import org.apache.struts.action.*;
///////////////////////////////////////
import jp.co.lastminute.supply.Agent;
import jp.co.lastminute.supply.jdbc.*;
//////////////////////////////////////
import jp.co.yobrain.util.DataFormat;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;
/**
 *
 * @author  skondo
 * @version
 */
public class AspMailsender extends HttpServlet implements Serializable {

	//データソース
	private DataSource dss;
	private ServletContext servletContext;
	private ServletConfig servletConfig;

	private static final String LMJ_SUPPLY = "supply@lastminute.co.jp";
	private static final String LMJ_CUSTOMER = "customer@lastminute.co.jp";
	private static final String LMJ_COPY = "copy@lastminute.co.jp";
	//////////////////////////////////////////////////////////////////////////////

	private static final String templateMailPath =
		"/usr/local/java/jakarta-tomcat-4.0.3/webapps/lmj/WEB-INF/classes/jp/co/lastminute/common/resources/";
	private static final String flight_mail =
		templateMailPath + "flightmail.xsl";
	private static final String gift_mail = templateMailPath + "giftmail.xsl";
	private static final String agent_mail = templateMailPath + "agentmail.xsl";
	private static final String agent_mailgift =
		templateMailPath + "agentmailgift.xsl";
	/*------------------------------------------------------------------------*/

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext(); //サーブレットテキスト
		this.dss =
			(DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
	}
	/**
	 * doGetではエラーを出す
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		out.print(this.getFail());
		out.close();
	}
	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		StringBuffer mailxml = new StringBuffer();
		String order_no = "";
		String flex_id = "";
		String eline_course_name = "";
		String branch_id = "";
		String from = "";
		String email = "";
		String zip = "";
		String state = "";
		String address1 = "";
		String address2 = "";
		String tel1 = "";
		String tel_type = "";
		String ticketing = "";
		String payment_way = "";
		String addressflg = "";
		String pax_adl_str = "";
		String pax_chd_str = "";
		String pax_inf_str = "";
		String air_remarks = "";

		String leader_name = "";

		Vector members = new Vector();
		Vector flights = new Vector();
		/////////////////////
		String agt_cd = "";
		String product_type_cd = "";
		/////////////////////
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		order_no = getParam(req, "order_no", 0);
		flex_id = getParam(req, "flex_id", 0);
		eline_course_name = encode(getParam(req, "eline_course_name", 0));
		branch_id = getParam(req, "branch_id", 0);
		from = encode(getParam(req, "from", 0));
		email = getParam(req, "email", 0);
		zip = getParam(req, "zip", 0);
		state = getParam(req, "state", 0);
		address1 = encode(getParam(req, "address1", 0));
		address2 = encode(getParam(req, "address2", 0));
		tel1 = getParam(req, "tel1", 0);
		tel_type = getParam(req, "tel_type", 0);
		ticketing = getParam(req, "ticketing", 0);
		addressflg = getParam(req, "addressflg", 0);
		payment_way = getParam(req, "payment_way", 0);
		pax_adl_str = getParam(req, "pax_adl_str", 0);
		pax_chd_str = getParam(req, "pax_chd_str", 0);
		pax_inf_str = getParam(req, "pax_inf_str", 0);
		air_remarks = encode(getParam(req, "air_remarks", 0));
		///////////////////////////////////////////////////////////////
		agt_cd = getParam(req, "agt_cd", 0);
		product_type_cd = getParam(req, "product_type_cd", 0);
		///////////////////////////////////////////////////////////////
		try {
			String[] k_lname = getParam(req, "k_lname");
			String[] k_fname = getParam(req, "k_fname");
			String[] r_lname = getParam(req, "r_lname");
			String[] r_fname = getParam(req, "r_fname");
			String[] sex = getParam(req, "sex");
			String[] birthday = getParam(req, "birthday");
			String[] title = getParam(req, "title");
			Member leader = null;
			for (int i = 0; i < sex.length; i++) {
				String k_lnames = encode(k_lname[i]);
				String k_fnames = encode(k_fname[i]);
				String r_lnames = encode(r_lname[i]);
				String r_fnames = encode(r_fname[i]);
				//public Member(String k_lname, String k_fname, String r_lname, String r_fname, String sex, String birthday, String title)
				Member member =
					new Member(
						k_lnames,
						k_fnames,
						r_lnames,
						r_fnames,
						sex[i],
						birthday[i],
						title[i]);
				members.add(member);
			}
			String[] arrive_time_year = getParam(req, "arrive_time_year");
			String[] arrive_time_month = getParam(req, "arrive_time_month");
			String[] arrive_time_day = getParam(req, "arrive_time_day");
			String[] arrive = getParam(req, "arrive");
			String[] dept = getParam(req, "dept");
			String[] dept_time = getParam(req, "dept_time");
			for (int i = 0; i < arrive_time_year.length; i++) {
				String depts = encode(dept[i]);
				String arrives = encode(arrive[i]);
				String dept_times = encode(dept_time[i]);
				//Flight(String arrive_time_year, String arrive_time_month, String arrive_time_day, String dept, String arrive, String dept_time)
				Flight flight =
					new Flight(
						arrive_time_year[i],
						arrive_time_month[i],
						arrive_time_day[i],
						depts,
						arrives,
						dept_times);
				flights.add(flight);
			}
			Agent agent = getAgent(agt_cd);
			if (agent == null) {
				out.print(this.getFail());
				out.close();
				return;
			}

			String agent_email = agent.getE_MAIL();
			jp.co.lastminute.common.jdbc.CommonCityCarrier cc =
				new jp.co.lastminute.common.jdbc.CommonCityCarrier();
			try {
				state = cc.getStateName(Integer.parseInt(state));
			} catch (Exception ex) {
			}
			try {
				zip = zip.substring(0, 3) + "-" + zip.substring(3);
			} catch (Exception ex) {
			}

			mailxml.append("<MAIL>");
			mailxml.append("<ORDER_NO>" + order_no + "</ORDER_NO>");
			mailxml.append("<AGENT>");
			mailxml.append("<CODE>" + agt_cd + "</CODE>");
			mailxml.append("<NAME>" + agent.getFIRST_NAME() + "</NAME>");
			mailxml.append(
				"<MAILCOMMENT>" + agent.getMAILCOMMENT() + "</MAILCOMMENT>");
			mailxml.append("</AGENT>");
			mailxml.append("<CODE>" + flex_id + "</CODE>");
			mailxml.append(
				"<COURSE_NAME>" + eline_course_name + "</COURSE_NAME>");
			mailxml.append("<EMAIL>" + email + "</EMAIL>");
			mailxml.append("<ZIP>" + zip + "</ZIP>");
			mailxml.append("<STATE>" + state + "</STATE>");
			mailxml.append("<ADDRESS1>" + address1 + "</ADDRESS1>");
			mailxml.append("<ADDRESS2>" + address2 + "</ADDRESS2>");
			mailxml.append("<TEL>" + tel1 + "</TEL>");
			mailxml.append("<ADULT>" + pax_adl_str + "</ADULT>");
			mailxml.append("<CHILD>" + pax_chd_str + "</CHILD>");
			mailxml.append("<INFANT>" + pax_inf_str + "</INFANT>");
			mailxml.append(
				"<PAYMENT>" + getPaymentStr(payment_way) + "</PAYMENT>");
			mailxml.append(
				"<TICKETING>" + getTicketingStr(ticketing) + "</TICKETING>");
			mailxml.append(
				"<ADDRESSFLG>"
					+ getAddressflgStr(addressflg)
					+ "</ADDRESSFLG>");
			mailxml.append(
				"<ADDRESSFLG>"
					+ getAddressflgStr(addressflg)
					+ "</ADDRESSFLG>");
			mailxml.append("<REMARKS>" + air_remarks + "</REMARKS>");
			String dept_date = "";
			for (int i = 0; i < flights.size(); i++) {
				Flight flight = (Flight) flights.elementAt(i);
				if (i == 0)
					dept_date =
						flight.getArrive_time_year()
							+ "/"
							+ flight.getArrive_time_month()
							+ "/"
							+ flight.getArrive_time_day();
				mailxml.append("<FLIGHT seq=\"").append(i + 1).append("\">");
				mailxml.append("<DEPT>" + flight.getDept() + "</DEPT>");
				mailxml.append("<ARRIVE>" + flight.getArrive() + "</ARRIVE>");
				mailxml.append(
					"<ARRIVE_DATE>"
						+ flight.getArrive_time_year()
						+ "/"
						+ flight.getArrive_time_month()
						+ "/"
						+ flight.getArrive_time_day()
						+ "</ARRIVE_DATE>");
				mailxml.append(
					"<DEPT_TIME>" + flight.getDept_time() + "</DEPT_TIME>");
				mailxml.append("</FLIGHT>");
			}
			for (int i = 0; i < members.size(); i++) {
				Member member = (Member) members.elementAt(i);
				mailxml.append("<MEMBER seq=\"").append(i + 1).append("\">");
				mailxml.append(
					"<K_LNAME>" + member.getK_lname() + "</K_LNAME>");
				mailxml.append(
					"<K_FNAME>" + member.getK_fname() + "</K_FNAME>");
				mailxml.append(
					"<R_LNAME>" + member.getR_lname() + "</R_LNAME>");
				mailxml.append(
					"<R_FNAME>" + member.getR_fname() + "</R_FNAME>");
				mailxml.append(
					"<TITLE>" + getTitle(member.getTitle()) + "</TITLE>");
				mailxml.append(
					"<BIRTHDAY>" + member.getBirthday() + "</BIRTHDAY>");
				mailxml.append("<AGE>").append(
					member.getAge(dept_date)).append(
					"</AGE>");
				mailxml.append("</MEMBER>");
			}
			mailxml.append("</MAIL>");
			System.err.println(mailxml.toString());
			MailSender mail_user = new MailSender();
			//String to, String from, String cc, String bcc, String subject, String body

			if (product_type_cd.equals("9")) {
				mail_user.SendMail(
					email,
					this.LMJ_CUSTOMER,
					"",
					this.LMJ_COPY,
					this.flight_title_[0],
					transform(mailxml.toString(), flight_mail));
				mail_user = null;
				MailSender mail_sup = new MailSender();
				mail_sup.SendMail(
					agent_email,
					this.LMJ_SUPPLY,
					"",
					this.LMJ_COPY,
					this.sup_title_[0][0],
					transform(mailxml.toString(), agent_mail));
				mail_sup = null;
			} else {
				mail_user.SendMail(
					email,
					this.LMJ_CUSTOMER,
					"",
					this.LMJ_COPY,
					this.gift_title_[0],
					transform(mailxml.toString(), gift_mail));
				mail_user = null;
				MailSender mail_sup = new MailSender();
				mail_sup.SendMail(
					agent_email,
					this.LMJ_SUPPLY,
					"",
					this.LMJ_COPY,
					this.sup_title_[1][0],
					transform(mailxml.toString(), agent_mailgift));
				mail_sup = null;
			}
			////////
			out.print(this.getSuccess(order_no, ""));
			out.close();
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			out.print(this.getFail());
			out.close();
			return;
		}
	}

	synchronized private String transform(String xml, String xsltPath) {
		Reader xmlReader = new StringReader(xml);
		StringWriter sw = new StringWriter();

		try {
			InputStreamReader xslReader =
				new InputStreamReader(
					new FileInputStream(xsltPath),
					"Shift_JIS");
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			transformer = tFactory.newTransformer(new StreamSource(xslReader));

			transformer.setOutputProperty("omit-xml-declaration", "yes");
			transformer.setOutputProperty("indent", "yes");

			transformer.transform(
				new StreamSource(xmlReader),
				new StreamResult((java.io.Writer) sw));
			return sw.toString();
		} catch (Exception e1) {
			System.err.println(e1.toString());
		}
		return "";
	}

	synchronized private String[] getParam(
		HttpServletRequest req,
		String str) {

		try {
			if (req.getParameterValues(str) != null)
				return req.getParameterValues(str);
		} catch (Exception e) {
		}
		return new String[0];
	}

	synchronized private String getParam(
		HttpServletRequest req,
		String str,
		int num) {

		try {
			String[] ret = req.getParameterValues(str);
			if (ret.length > 0)
				return ret[num];
		} catch (Exception e) {
		}
		return "";
	}

	synchronized private String encode(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "Shift_JIS");
		} catch (Exception e) {
		}
		return "";
	}

	synchronized public String getPaymentStr(String flg) {
		String reStr = "";
		if (flg.equals("1"))
			reStr = "銀行振込み";
		else if (flg.equals("2"))
			reStr = "カード";
		return reStr;
	}

	synchronized public String getTicketingStr(String flg) {
		String reStr = "";
		if (flg.equals("1"))
			reStr = "空港お渡し";
		else if (flg.equals("2"))
			reStr = "宅配便で送付";
		return reStr;
	}

	synchronized public String getAddressflgStr(String flg) {
		String reStr = "";
		if (flg.equals("1"))
			reStr = "観光";
		else if (flg.equals("2"))
			reStr = "ビジネス";
		return reStr;
	}

	synchronized public static String getTitle(String title) {
		String reStr = "";
		if (title.equals("1"))
			reStr = "男性";
		else if (title.equals("2"))
			reStr = "女性";
		return reStr;
	}
	/**
	 *　エージェントの取得
	 */
	synchronized public Agent getAgent(String agt_cd) {
		dbAdapterAgent db = new dbAdapterAgent(this.dss);
		Agent agent = db.findAgentOnlyStr(agt_cd);
		if (agent != null) {
			return agent;
		}
		return null;
	}
	/**
	 * 正解
	 */
	private static String getSuccess(String order_no, String price) {
		try {
			return "<?xml version=\"1.0\" encoding=\"Shift-JIS\" ?>\n"
				+ "<NEWBOKOUT>\n"
				+ "\t<SUCCESS>\n"
				+ "\t\t<ORDERNO>"
				+ order_no
				+ "</ORDERNO>\n"
				+ "\t\t<PRICE>"
				+ price
				+ "</PRICE>\n"
				+ "\t\t<DISCRIPTION><CONTENT><![CDATA[ ]]></CONTENT></DISCRIPTION>\n"
				+ "\t</SUCCESS>\n"
				+ "</NEWBOKOUT>";
		} catch (Exception ex) {
			return getFail();
		}
	}
	/**
	 * NG
	 */
	private static String getFail() {
		return "<?xml version=\"1.0\" encoding=\"Shift-JIS\" ?>\n"
			+ "<NEWBOKOUT>\n"
			+ "\t<FAIL>\n"
			+ "\t\t<ERRCODE>-1</ERRCODE>\n"
			+ "\t\t<DISCRIPTION><CONTENT><![CDATA[ ERROR ]]></CONTENT></DISCRIPTION>\n"
			+ "\t</FAIL>\n"
			+ "</NEWBOKOUT>";
	}
	public static final String[] flight_title_ =
		{ "[LMJ格安航空券]お問い合わせ内容確認", "キャンセルされました", "オーダー" };
	public static final String[] gift_title_ =
		{ "【ラストミニット・ドット・コム 海外パッケージツアー】ご注文・ご予約確認", "キャンセルされました", "オーダー" };
	public static final String[][] sup_title_ =
		{ { "[LMJ格安航空券]お問い合せ", "キャンセルされました", "オーダー" }, {
			"[ラストミニット・ドット・コム 海外パッケージツアー]お問い合せ", "キャンセルされました", "オーダー" }
	};
}

class Member {
	String k_lname = "";
	String k_fname = "";
	String r_lname = "";
	String r_fname = "";
	String sex = "";
	String birthday = "";
	String title = "";
	public Member(
		String k_lname,
		String k_fname,
		String r_lname,
		String r_fname,
		String sex,
		String birthday,
		String title) {
		this.k_lname = k_lname;
		this.k_fname = k_fname;
		this.r_lname = r_lname;
		this.r_fname = r_fname;
		this.sex = sex;
		this.birthday = birthday;
		this.title = title;
	}
	/** プロパティ birthday の取得メソッド。
	 * @return プロパティ birthday の値。
	 */
	public String getBirthday() {
		return birthday;
	}

	/** プロパティ birthday の設定メソッド。
	 * @param birthday プロパティ birthday の新しい値。
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/** プロパティ k_fname の取得メソッド。
	 * @return プロパティ k_fname の値。
	 */
	public String getK_fname() {
		return k_fname;
	}

	/** プロパティ k_fname の設定メソッド。
	 * @param k_fname プロパティ k_fname の新しい値。
	 */
	public void setK_fname(String k_fname) {
		this.k_fname = k_fname;
	}

	/** プロパティ k_lname の取得メソッド。
	 * @return プロパティ k_lname の値。
	 */
	public String getK_lname() {
		return k_lname;
	}

	/** プロパティ k_lname の設定メソッド。
	 * @param k_lname プロパティ k_lname の新しい値。
	 */
	public void setK_lname(String k_lname) {
		this.k_lname = k_lname;
	}

	/** プロパティ r_fname の取得メソッド。
	 * @return プロパティ r_fname の値。
	 */
	public String getR_fname() {
		return r_fname;
	}

	/** プロパティ r_fname の設定メソッド。
	 * @param r_fname プロパティ r_fname の新しい値。
	 */
	public void setR_fname(String r_fname) {
		this.r_fname = r_fname;
	}

	/** プロパティ r_lname の取得メソッド。
	 * @return プロパティ r_lname の値。
	 */
	public String getR_lname() {
		return r_lname;
	}

	/** プロパティ r_lname の設定メソッド。
	 * @param r_lname プロパティ r_lname の新しい値。
	 */
	public void setR_lname(String r_lname) {
		this.r_lname = r_lname;
	}

	/** プロパティ sex の取得メソッド。
	 * @return プロパティ sex の値。
	 */
	public String getSex() {
		return sex;
	}

	/** プロパティ sex の設定メソッド。
	 * @param sex プロパティ sex の新しい値。
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/** プロパティ title の取得メソッド。
	 * @return プロパティ title の値。
	 */
	public String getTitle() {
		return title;
	}

	/** プロパティ title の設定メソッド。
	 * @param title プロパティ title の新しい値。
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 年齢 の取得メソッド。
	 * @param date 計算される日。
	 * @return 年齢 の値。
	 */
	public int getAge(String date) {
		int age = -1;

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cl1 = Calendar.getInstance();
		Calendar cl2 = Calendar.getInstance();
		try {
			cl1.setTime(sdf1.parse(this.birthday));
			cl2.setTime(sdf2.parse(date));
			age = cl2.get(Calendar.YEAR) - cl1.get(Calendar.YEAR);
			cl2.add(Calendar.YEAR, -age);

			if (cl2.before(cl1))
				age--;
		} catch (Exception e) {
		}

		return age;

	}

}
class Flight {
	String arrive_time_year = "";
	String arrive_time_month = "";
	String arrive_time_day = "";
	String dept = "";
	String arrive = "";
	String dept_time = "";
	Flight(
		String arrive_time_year,
		String arrive_time_month,
		String arrive_time_day,
		String dept,
		String arrive,
		String dept_time) {
		this.arrive_time_year = arrive_time_year;
		this.arrive_time_month = arrive_time_month;
		this.arrive_time_day = arrive_time_day;
		this.dept = dept;
		this.arrive = arrive;
		this.dept_time = dept_time;
	}

	/** プロパティ arrive の取得メソッド。
	 * @return プロパティ arrive の値。
	 */
	public String getArrive() {
		return arrive;
	}

	/** プロパティ arrive の設定メソッド。
	 * @param arrive プロパティ arrive の新しい値。
	 */
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	/** プロパティ arrive_time_day の取得メソッド。
	 * @return プロパティ arrive_time_day の値。
	 */
	public String getArrive_time_day() {
		return arrive_time_day;
	}

	/** プロパティ arrive_time_day の設定メソッド。
	 * @param arrive_time_day プロパティ arrive_time_day の新しい値。
	 */
	public void setArrive_time_day(String arrive_time_day) {
		this.arrive_time_day = arrive_time_day;
	}

	/** プロパティ arrive_time_month の取得メソッド。
	 * @return プロパティ arrive_time_month の値。
	 */
	public String getArrive_time_month() {
		return arrive_time_month;
	}

	/** プロパティ arrive_time_month の設定メソッド。
	 * @param arrive_time_month プロパティ arrive_time_month の新しい値。
	 */
	public void setArrive_time_month(String arrive_time_month) {
		this.arrive_time_month = arrive_time_month;
	}

	/** プロパティ arrive_time_year の取得メソッド。
	 * @return プロパティ arrive_time_year の値。
	 */
	public String getArrive_time_year() {
		return arrive_time_year;
	}

	/** プロパティ arrive_time_year の設定メソッド。
	 * @param arrive_time_year プロパティ arrive_time_year の新しい値。
	 */
	public void setArrive_time_year(String arrive_time_year) {
		this.arrive_time_year = arrive_time_year;
	}

	/** プロパティ dept の取得メソッド。
	 * @return プロパティ dept の値。
	 */
	public String getDept() {
		return dept;
	}

	/** プロパティ dept の設定メソッド。
	 * @param dept プロパティ dept の新しい値。
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/** プロパティ dept_time の取得メソッド。
	 * @return プロパティ dept_time の値。
	 */
	public String getDept_time() {
		return dept_time;
	}

	/** プロパティ dept_time の設定メソッド。
	 * @param dept_time プロパティ dept_time の新しい値。
	 */
	public void setDept_time(String dept_time) {
		this.dept_time = dept_time;
	}

}
