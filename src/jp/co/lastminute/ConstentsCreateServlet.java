/*
 * ConstentsCreateServlet.java
 *
 * Created on 2002/05/03, 1:06
 */

package jp.co.lastminute;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.common.xml.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;
import jp.co.yobrain.util.OptionLabel;
import org.apache.struts.action.*;

import jp.co.lastminute.ContextProperty;

/**
 *
 * @author  skondo
 * @version 
 */
public class ConstentsCreateServlet extends HttpServlet {
	private ServletContext servletContext = null;
	//HashMap hm = null;
	public void destroy() {
		// Remove the database tables from our application attributes
		servletContext.removeAttribute(Constants.Product_Type_Mapping_);
		servletContext.removeAttribute(ContextProperty.CCTYPE_ARRAY_KEY_);
        servletContext.removeAttribute(ContextProperty.TITLES_);
        servletContext.removeAttribute(ContextProperty.CMONTHS_);
        servletContext.removeAttribute(ContextProperty.CYEARS_);
        servletContext.removeAttribute(ContextProperty.BYEARS_);
        servletContext.removeAttribute(ContextProperty.STATE_);
        servletContext.removeAttribute(ContextProperty.BSTATE_);
        servletContext.removeAttribute(ContextProperty.SEX_);
        servletContext.removeAttribute(ContextProperty.NSTATE_);
        servletContext.removeAttribute(ContextProperty.AMPM_);
        servletContext.removeAttribute(ContextProperty.HOURS_);
        servletContext.removeAttribute(ContextProperty.MINUTES_Q_);
        servletContext.removeAttribute(ContextProperty.SENDING_LENGTH_);
	}
	public void init() throws ServletException {
		// Process our servlet initialization parameters
		String value = getServletConfig().getInitParameter("debug");
		servletContext = getServletContext();
		DataSource dss =
			(DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		// Load our database from persistent storage
		System.err.println("ConstentsCreateServlet Load ready");
		try {
			setContents(dss);
			System.out.println("ConstentsCreateServlet Load ready: setContents");
			resetBasicContext();
			System.out.println("ConstentsCreateServlet Load ready: resetBasicContext");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("ConstentsCreateServlet Load ready: Exception");
			throw new UnavailableException("Cannot load database from ");
		}
	}
	/**
	 *
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		String value = getServletConfig().getInitParameter("debug");
		servletContext = getServletContext();
		//ServletContext _servletContext = servletContext.getContext("/");
		DataSource dss =
			(DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		try {
			setContents(dss);
			out.println("<H2>Loading Is OK </H2>");
		} catch (Exception ex) {
			ex.printStackTrace();
			out.println("<H2>Loading Is BAD </H2>");
			throw new UnavailableException("Cannot load database from ");
		}
	}
	public void setContents(DataSource dss) throws Exception {
		//System.err.println("ConstentsCreateServlet Load ready: setContents 00");
		String SQL = "SELECT PRODUCT_TYPE_CD, " //00
		+"AGT_CD, " //01
		+"CATEGORY, " //02
		+"TEMPLATEXSDURI, " //03
		+"CHECKURL, " //04
		+"SENDURL, " //05
		+"SENDXSL, " //06
		+"STOPURL, " //07
		+"STOPXSL, " //08
		+"DESCXSL, " //09
		+"TEMPLATEXSL " //10
	+"  FROM PRODUCT_TYPE_MASTER";
		//System.err.println("ConstentsCreateServlet Load ready: setContents 01");
		dbDataAccesser dbaccess;
		HashMap hm = new HashMap();
		//System.err.println("ConstentsCreateServlet Load ready: setContents 02");
		//ConstantsXml xmlsq = null;
		//UriParam param = null;
		JdbcAdapter db = new JdbcAdapter();
		//System.err.print( SQL );
		db.init(dss);
		//System.err.println("ConstentsCreateServlet Load ready: setContents 04");
		dbaccess = new dbDataAccesser(db.dbSelect(SQL));
		for (int i = 0; i < dbaccess.getRowsize(); i++) {
			ConstantsXml xmlsq = new ConstantsXml();
			UriParam param = new UriParam();
			param.setAgt_cd(dbaccess.getData(i, 1));
			param.setProduct_type_cd(dbaccess.getDatabyInt(i, 0));
			//System.err.print( "Product_type_cd =" + dbaccess.getDatabyInt(i, 0 ) );
			//System.err.print( "agt_cd =" + dbaccess.getData(i, 1) );
			xmlsq.setProduct_type_cd(dbaccess.getDatabyInt(i, 0));
			xmlsq.setAgt_cd(dbaccess.getData(i, 1));
			xmlsq.setCategory(dbaccess.getData(i, 2));
			xmlsq.setTemplatexsd(dbaccess.getData(i, 3));
			xmlsq.setCheckurl(dbaccess.getData(i, 4));
			xmlsq.setSendurl(dbaccess.getData(i, 5));
			xmlsq.setSendxslt(dbaccess.getData(i, 6));
			xmlsq.setCancelurl(dbaccess.getData(i, 7));
			xmlsq.setCancelxslt(dbaccess.getData(i, 8));
			xmlsq.setDescxslt(dbaccess.getData(i, 9));
			xmlsq.setTemplatexsl(dbaccess.getData(i, 10));
			//System.err.print("new_agt_cd="+ param.getAgt_cd() );
			//System.err.print("new_Product_type_cd"+ param.getProduct_type_cd() );
			hm.put(param, xmlsq);
		}
		servletContext.setAttribute(Constants.Product_Type_Mapping_, hm);
	}
	private void resetBasicContext(){
		setCard();
        setTitle();
        setPaxValues();
        setDayValues();
        setMonthValues();
        setYearValues();
        setBYearValues();
        setState();
        setBState();
        setCardCT();
        setSex();
        setNstate();
        setMinuteQ();
        setHors();
        setAmpm();
        setSendingLength();
	}
	public void setSendingLength(){
		ArrayList sending = new ArrayList(4);
		sending.add( new OptionLabel("“Á‚É‚È‚µ", "“Á‚É‚È‚µ") );
		sending.add( new OptionLabel("10:00-12:00", "10:00-12:00") );
        sending.add( new OptionLabel("12:00-18:00", "12:00-18:00") );
        sending.add( new OptionLabel("18:00-20:00", "18:00-20:00") );
        servletContext.setAttribute(ContextProperty.SENDING_LENGTH_, sending);
	}
	public void setMinuteQ(){
        ArrayList cMinuteQ = new ArrayList(4);
        cMinuteQ.add( new OptionLabel("00", "00") );
        cMinuteQ.add( new OptionLabel("15", "15") );
        cMinuteQ.add( new OptionLabel("30", "30") );
        cMinuteQ.add( new OptionLabel("45", "45") );
        servletContext.setAttribute(ContextProperty.MINUTES_Q_, cMinuteQ);
    }
    public void setHors(){
        ArrayList cHours = new ArrayList(12);
        cHours.add( new OptionLabel("0", "00") );
        cHours.add( new OptionLabel("1", "01") );
        cHours.add( new OptionLabel("2", "02") );
        cHours.add( new OptionLabel("3", "03") );
        cHours.add( new OptionLabel("4", "04") );
        cHours.add( new OptionLabel("5", "05") );
        cHours.add( new OptionLabel("6", "06") );
        cHours.add( new OptionLabel("7", "07") );
        cHours.add( new OptionLabel("8", "08") );
        cHours.add( new OptionLabel("9", "09") );
        cHours.add( new OptionLabel("10", "10") );
        cHours.add( new OptionLabel("11", "11") );
        servletContext.setAttribute(ContextProperty.HOURS_, cHours);
    }
    public void setAmpm(){
        ArrayList cAmpm = new ArrayList(2);
        cAmpm.add( new OptionLabel("Œß‘O", "AM") );
        cAmpm.add( new OptionLabel("ŒßŒã", "PM") );
        servletContext.setAttribute(ContextProperty.AMPM_, cAmpm);
    }
    public void setSex(){
        ArrayList cSex = new ArrayList(2);
        cSex.add( new OptionLabel("’j«", "1") );
        cSex.add( new OptionLabel("—«", "2") );
        servletContext.setAttribute(ContextProperty.SEX_, cSex);
    }
    public void setCard(){
        ArrayList ccTypes = new ArrayList(5);
        ccTypes.add( new OptionLabel("VISA", "VISA") );
        ccTypes.add( new OptionLabel("MASTER", "MASTER") );
        ccTypes.add( new OptionLabel("SAISON", "SAISON") );
        ccTypes.add( new OptionLabel("JCB", "JCB") );
        ccTypes.add( new OptionLabel("DINERS", "DINERS") );
        
        
        servletContext.setAttribute(ContextProperty.CCTYPE_ARRAY_KEY_, ccTypes);
    }
    public void setCardCT(){
        ArrayList ccTypes = new ArrayList(5);
        ccTypes.add( new OptionLabel("VISA", "VISA") );
        ccTypes.add( new OptionLabel("MASTER", "MASTER") );
        ccTypes.add( new OptionLabel("JCB", "JCB") );
        ccTypes.add( new OptionLabel("DINERS", "DINERS") );
        
        servletContext.setAttribute(ContextProperty.CCTYPE_ARRAY_KEY_CT_, ccTypes);
    }
    public void setTitle(){
        ArrayList cctitle = new ArrayList(3);
        cctitle.add( new OptionLabel("Mr", "1") );
        cctitle.add( new OptionLabel("Miss", "2") );
        cctitle.add( new OptionLabel("Inf", "3") );

        servletContext.setAttribute(ContextProperty.TITLES_, cctitle);
    }
    public void setPaxValues() {
	ArrayList paxs = new ArrayList(8);
	for (int i = 0; i < 8; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(i + 1);
            paxs.add(new OptionLabel(sb.toString(), sb.toString()));
	}
	servletContext.setAttribute(ContextProperty.CPAX_, paxs);
    } //end setMonthValues 
    public void setDayValues() {
	ArrayList days = new ArrayList(31);
	for (int i = 0; i < 31; i++) {
            StringBuffer sb = new StringBuffer();
            if (i < 9) {
		sb.append("0");
            }
            sb.append(i + 1);
            days.add(new OptionLabel(sb.toString(), sb.toString()));
	}
	servletContext.setAttribute(ContextProperty.CDAY_, days);
    } //end setMonthValues 
    public void setMonthValues() {
	ArrayList months = new ArrayList(12);
	for (int i = 0; i < 12; i++) {
            StringBuffer sb = new StringBuffer();
            if (i < 9) {
		sb.append("0");
            }
            sb.append(i + 1);
            months.add(new OptionLabel(sb.toString(), sb.toString()));
	}
	servletContext.setAttribute(ContextProperty.CMONTHS_, months);
    } //end setMonthValues	
    public void setYearValues() {
	ArrayList years = new ArrayList(10);
	int base = 3;
	for (int i = base; i < base + 10; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append("20");
            if (i < 10) {
		sb.append("0");
            }
            sb.append(i);
            years.add(new OptionLabel(sb.toString(), sb.toString()));
	}
	servletContext.setAttribute(ContextProperty.CYEARS_, years);
    } //end setYearValues
    public void setBYearValues() {
	ArrayList years = new ArrayList(91);
    years.add(new OptionLabel("--", ""));
	int start = 1910;
	for (int i = 0; i < 91; i++) {
			String sb = Integer.toString( start );
            start++;
            years.add(new OptionLabel(sb, sb));
	}
	servletContext.setAttribute(ContextProperty.BYEARS_, years);
    } //end setBYearValues
    public void setState(){
        ArrayList state = new ArrayList(47);
        state.add(new OptionLabel("–kŠC“¹", "1"));
        state.add(new OptionLabel("ÂXŒ§", "2"));
        state.add(new OptionLabel("ŠâŽèŒ§", "3"));
        state.add(new OptionLabel("‹{éŒ§", "4"));
        state.add(new OptionLabel("H“cŒ§", "5"));
        state.add(new OptionLabel("ŽRŒ`Œ§", "6"));
        state.add(new OptionLabel("•Ÿ“‡Œ§", "7"));
        state.add(new OptionLabel("ˆïéŒ§", "8"));
        state.add(new OptionLabel("“È–ØŒ§", "9"));
        state.add(new OptionLabel("ŒQ”nŒ§", "10"));
        state.add(new OptionLabel("é‹ÊŒ§", "11"));
        state.add(new OptionLabel("ç—tŒ§", "12"));
        state.add(new OptionLabel("“Œ‹ž“s", "13"));
        state.add(new OptionLabel("_“ÞìŒ§", "14"));
        state.add(new OptionLabel("ŽR—œŒ§", "15"));
        state.add(new OptionLabel("’·–ìŒ§", "16"));
        state.add(new OptionLabel("VŠƒŒ§", "17"));
        state.add(new OptionLabel("•xŽRŒ§", "18"));
        state.add(new OptionLabel("ÎìŒ§", "19"));
        state.add(new OptionLabel("•ŸˆäŒ§", "20"));
        state.add(new OptionLabel("Šò•ŒŒ§", "21"));
        state.add(new OptionLabel("Ã‰ªŒ§", "22"));
        state.add(new OptionLabel("ˆ¤’mŒ§", "23"));
        state.add(new OptionLabel("ŽOdŒ§", "24"));
        state.add(new OptionLabel("Ž ‰êŒ§", "25"));
        state.add(new OptionLabel("‹ž“s•{", "26"));
        state.add(new OptionLabel("‘åã•{", "27"));
        state.add(new OptionLabel("•ºŒÉŒ§", "28"));
        state.add(new OptionLabel("“Þ—ÇŒ§", "29"));
        state.add(new OptionLabel("˜a‰ÌŽRŒ§", "30"));
        state.add(new OptionLabel("’¹ŽæŒ§", "31"));
        state.add(new OptionLabel("“‡ªŒ§", "32"));
        state.add(new OptionLabel("‰ªŽRŒ§", "33"));
        state.add(new OptionLabel("L“‡Œ§", "34"));
        state.add(new OptionLabel("ŽRŒûŒ§", "35"));
        state.add(new OptionLabel("“¿“‡Œ§", "36"));
        state.add(new OptionLabel("ìŒ§", "37"));
        state.add(new OptionLabel("ˆ¤•QŒ§", "38"));
        state.add(new OptionLabel("‚’mŒ§", "39"));
        state.add(new OptionLabel("•Ÿ‰ªŒ§", "40"));
        state.add(new OptionLabel("²‰êŒ§", "41"));
        state.add(new OptionLabel("’·èŒ§", "42"));
        state.add(new OptionLabel("ŒF–{Œ§", "43"));
        state.add(new OptionLabel("‘å•ªŒ§", "44"));
        state.add(new OptionLabel("‹{èŒ§", "45"));
        state.add(new OptionLabel("Ž­Ž™“‡Œ§", "46"));
        state.add(new OptionLabel("‰«“êŒ§", "47"));
        servletContext.setAttribute(ContextProperty.STATE_, state);
    }
    public void setBState(){
        ArrayList state = new ArrayList(49);
        state.add(new OptionLabel("‘I‘ð‚µ‚Ä‚­‚¾‚³‚¢", ""));
        state.add(new OptionLabel("–kŠC“¹", "1"));
        state.add(new OptionLabel("ÂXŒ§", "2"));
        state.add(new OptionLabel("ŠâŽèŒ§", "3"));
        state.add(new OptionLabel("‹{éŒ§", "4"));
        state.add(new OptionLabel("H“cŒ§", "5"));
        state.add(new OptionLabel("ŽRŒ`Œ§", "6"));
        state.add(new OptionLabel("•Ÿ“‡Œ§", "7"));
        state.add(new OptionLabel("ˆïéŒ§", "8"));
        state.add(new OptionLabel("“È–ØŒ§", "9"));
        state.add(new OptionLabel("ŒQ”nŒ§", "10"));
        state.add(new OptionLabel("é‹ÊŒ§", "11"));
        state.add(new OptionLabel("ç—tŒ§", "12"));
        state.add(new OptionLabel("“Œ‹ž“s", "13"));
        state.add(new OptionLabel("_“ÞìŒ§", "14"));
        state.add(new OptionLabel("ŽR—œŒ§", "15"));
        state.add(new OptionLabel("’·–ìŒ§", "16"));
        state.add(new OptionLabel("VŠƒŒ§", "17"));
        state.add(new OptionLabel("•xŽRŒ§", "18"));
        state.add(new OptionLabel("ÎìŒ§", "19"));
        state.add(new OptionLabel("•ŸˆäŒ§", "20"));
        state.add(new OptionLabel("Šò•ŒŒ§", "21"));
        state.add(new OptionLabel("Ã‰ªŒ§", "22"));
        state.add(new OptionLabel("ˆ¤’mŒ§", "23"));
        state.add(new OptionLabel("ŽOdŒ§", "24"));
        state.add(new OptionLabel("Ž ‰êŒ§", "25"));
        state.add(new OptionLabel("‹ž“s•{", "26"));
        state.add(new OptionLabel("‘åã•{", "27"));
        state.add(new OptionLabel("•ºŒÉŒ§", "28"));
        state.add(new OptionLabel("“Þ—ÇŒ§", "29"));
        state.add(new OptionLabel("˜a‰ÌŽRŒ§", "30"));
        state.add(new OptionLabel("’¹ŽæŒ§", "31"));
        state.add(new OptionLabel("“‡ªŒ§", "32"));
        state.add(new OptionLabel("‰ªŽRŒ§", "33"));
        state.add(new OptionLabel("L“‡Œ§", "34"));
        state.add(new OptionLabel("ŽRŒûŒ§", "35"));
        state.add(new OptionLabel("“¿“‡Œ§", "36"));
        state.add(new OptionLabel("ìŒ§", "37"));
        state.add(new OptionLabel("ˆ¤•QŒ§", "38"));
        state.add(new OptionLabel("‚’mŒ§", "39"));
        state.add(new OptionLabel("•Ÿ‰ªŒ§", "40"));
        state.add(new OptionLabel("²‰êŒ§", "41"));
        state.add(new OptionLabel("’·èŒ§", "42"));
        state.add(new OptionLabel("ŒF–{Œ§", "43"));
        state.add(new OptionLabel("‘å•ªŒ§", "44"));
        state.add(new OptionLabel("‹{èŒ§", "45"));
        state.add(new OptionLabel("Ž­Ž™“‡Œ§", "46"));
        state.add(new OptionLabel("‰«“êŒ§", "47"));
        state.add(new OptionLabel("ŠCŠO", "50"));
        servletContext.setAttribute(ContextProperty.BSTATE_, state);
    }
    public void setNstate(){
        ArrayList state = new ArrayList(48);
        state.add(new OptionLabel("‘I‘ð‚µ‚Ä‚­‚¾‚³‚¢", ""));
        state.add(new OptionLabel("–kŠC“¹", "1"));
        state.add(new OptionLabel("ÂXŒ§", "2"));
        state.add(new OptionLabel("ŠâŽèŒ§", "3"));
        state.add(new OptionLabel("‹{éŒ§", "4"));
        state.add(new OptionLabel("H“cŒ§", "5"));
        state.add(new OptionLabel("ŽRŒ`Œ§", "6"));
        state.add(new OptionLabel("•Ÿ“‡Œ§", "7"));
        state.add(new OptionLabel("ˆïéŒ§", "8"));
        state.add(new OptionLabel("“È–ØŒ§", "9"));
        state.add(new OptionLabel("ŒQ”nŒ§", "10"));
        state.add(new OptionLabel("é‹ÊŒ§", "11"));
        state.add(new OptionLabel("ç—tŒ§", "12"));
        state.add(new OptionLabel("“Œ‹ž“s", "13"));
        state.add(new OptionLabel("_“ÞìŒ§", "14"));
        state.add(new OptionLabel("ŽR—œŒ§", "15"));
        state.add(new OptionLabel("’·–ìŒ§", "16"));
        state.add(new OptionLabel("VŠƒŒ§", "17"));
        state.add(new OptionLabel("•xŽRŒ§", "18"));
        state.add(new OptionLabel("ÎìŒ§", "19"));
        state.add(new OptionLabel("•ŸˆäŒ§", "20"));
        state.add(new OptionLabel("Šò•ŒŒ§", "21"));
        state.add(new OptionLabel("Ã‰ªŒ§", "22"));
        state.add(new OptionLabel("ˆ¤’mŒ§", "23"));
        state.add(new OptionLabel("ŽOdŒ§", "24"));
        state.add(new OptionLabel("Ž ‰êŒ§", "25"));
        state.add(new OptionLabel("‹ž“s•{", "26"));
        state.add(new OptionLabel("‘åã•{", "27"));
        state.add(new OptionLabel("•ºŒÉŒ§", "28"));
        state.add(new OptionLabel("“Þ—ÇŒ§", "29"));
        state.add(new OptionLabel("˜a‰ÌŽRŒ§", "30"));
        state.add(new OptionLabel("’¹ŽæŒ§", "31"));
        state.add(new OptionLabel("“‡ªŒ§", "32"));
        state.add(new OptionLabel("‰ªŽRŒ§", "33"));
        state.add(new OptionLabel("L“‡Œ§", "34"));
        state.add(new OptionLabel("ŽRŒûŒ§", "35"));
        state.add(new OptionLabel("“¿“‡Œ§", "36"));
        state.add(new OptionLabel("ìŒ§", "37"));
        state.add(new OptionLabel("ˆ¤•QŒ§", "38"));
        state.add(new OptionLabel("‚’mŒ§", "39"));
        state.add(new OptionLabel("•Ÿ‰ªŒ§", "40"));
        state.add(new OptionLabel("²‰êŒ§", "41"));
        state.add(new OptionLabel("’·èŒ§", "42"));
        state.add(new OptionLabel("ŒF–{Œ§", "43"));
        state.add(new OptionLabel("‘å•ªŒ§", "44"));
        state.add(new OptionLabel("‹{èŒ§", "45"));
        state.add(new OptionLabel("Ž­Ž™“‡Œ§", "46"));
        state.add(new OptionLabel("‰«“êŒ§", "47"));
        servletContext.setAttribute(ContextProperty.NSTATE_, state);
    }
}