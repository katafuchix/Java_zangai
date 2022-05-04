package jp.co.lastminute.cart;

import java.io.*;
import java.util.*;
import java.lang.reflect.Method;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import jp.co.lastminute.*;
import jp.co.lastminute.cart.model.Order;
import jp.co.lastminute.cart.model.Sub_Order;
import jp.co.lastminute.cart.model.Product_Send;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.jdbc.*;
import jp.co.lastminute.cart.prop.*;

import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;

import org.apache.struts.action.*;

import jp.co.yobrain.util.DataFormat;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CartInServlet extends HttpServlet {

	private final static String XML_PARA = "XML_DOC";
	private final static String AGT_CD = "AGT_CD";
	private final static String PRODUCT_TYPE_CD = "PRODUCT_TYPE_CD";
	private final static String CONN_STYLE = "CONN_STYLE";

	private final static String SESSION_ORDER = "ORDER";
	private final static String BUY_PROP_TAG = "BUY_PROP";
	private final static String SUB_ORDER_TAG = "SUB_ORDER";
	private final static String PRODUCT_SEND_TAG = "PRODUCT_SEND";
	//データソース
	private DataSource dss;

	private int order_no = 0;
	private int user_id = 0;

	private ServletContext servletContext;
	private ServletConfig servletConfig;
	private HashMap xmlhmap;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext(); //サーブレットテキスト
		this.dss =
			(DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		this.xmlhmap = (HashMap) servletContext.getAttribute(Constants.Product_Type_Mapping_);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException {
		Order order;
		Sub_Order sub_order;
		RequestDispatcher dispatcher;
		int conn_style = 0;
		String session_id = "";
		//データがきているか把握
		boolean error_flg = true;
		boolean anmnswer = false;
		//ユーザーセッションの取得
		boolean success_flg = false;
		try {
			HttpSession session = req.getSession(true); //セッションの取得
			req.setCharacterEncoding("Shift_JIS");
			session.removeAttribute(Contents.AFTER_CARTID_);
			session.removeAttribute(Contents.ASP_CART_SESSION_);
			session.removeAttribute( Constants.SESSION_COM );

			conn_style = isErrorbyParameter(req);
			int status = Constants.CART_IN_;
			if (conn_style >= 0) {
				////本処理
				session_id = session.getId();
				switch (conn_style) {
					case 0 : //個別情報の登録が必要
						break;
					case 1 : //個別情報の登録が必要
						anmnswer = true;
						break;
					case 2 : //個別情報の登録は不要で、まだ、戻ってくる。     
						anmnswer = true;
						break;
					case 3 : //個別情報の登録が不要
						break;
					case 4 : //個別情報の登録が不要で、もう戻ってこない。
						anmnswer = true;
						break;
					case 5 :
						break;	//個別登録が不要で連続購入可能：GIFT、チケット
					default :
						error_flg = false;
						break;
				}
			} else {
				error_flg = false;
			}
			//////不正値の処理の場合、エラー飛ばす。
			if (!error_flg) {
				RequestDispatcher dispather;
				dispather = req.getRequestDispatcher(Constants.CRACK_);
				dispather.forward(req, res);
				dispather = null;
			}
			//パラメータ取得
			String xmlstring = "";
			if (req.getParameterValues(this.XML_PARA) != null) {
				xmlstring = (String) req.getParameterValues(this.XML_PARA)[0];
			} else {
				xmlstring = (String) req.getAttribute(this.XML_PARA);
			}
			//セッション判断	
			System.err.println("<-----CartInServlet:130---->");
			if (session.getAttribute(Constants.CartOrder) == null) {
				order = new Order();
				order.initOrder();
				order.setExec_type(Constants.INSERT_);
			} else {
				order = (Order) session.getAttribute(Constants.CartOrder);
				//ステータスが、全て完了であれば、新しいカートを走らせる。
				if( lookupStatus( order ) > Constants.NOT_CONFIRM_ ){
					order = new Order();
					order.initOrder();
					order.setExec_type(Constants.INSERT_);					
				}
			}
			order.clearError_comm(); //エラーコメントをクリアする。
			//ユーザーセッションの取得
			//本処理開始
			//boolean success_flg = false;
			getUserID(session);
			System.err.println("<-----CartInServlet:143---->");
			if (!isAddSuborder(order, this.user_id )) {
				System.err.println("<-----CartInServlet:144---->");
				order.addError_comm( "同時にての複数の予約はできません" );
				//session.setAttribute( Constants.SESSION_COM, "同時にての複数の予約はできません。");
				success_flg = true;
			} else {
				System.err.println("<-----CartInServlet:146---->");
				//分岐で判別した遷移のフラグ

				this.order_no = order.getORDER_NO();
				Document doc; //ドキュメント生成
				System.err.println("<-----CartInServlet:155---->");
				//ヘッダーの取得
				xmlstring = ParsingTool.getHeader(xmlstring);
				BuyPropDivid buyPropDivid = null;
				if( (buyPropDivid = ParsingTool.dividBuyProp( xmlstring )) != null ){
					System.err.println("<-----CartInServlet:164---->");
					xmlstring = buyPropDivid.getOthers();
					Vector buyprops = buyPropDivid.getBuyprops();
					if ((doc = ParsingTool.XMLToDoc(new StringReader(xmlstring))) != null) {
						System.err.println("<-----CartInServlet:168---->");
						Element root = doc.getRootElement(); //ルートエレメントに移動
						//パージんぐ サブオーダーが欲しい	
						Order temp_order = null;
						if ((temp_order =
							getSaxReadOrder(root, order, status, session_id, buyprops))
							!= null) {
								System.err.println("<-----CartInServlet:175---->");
							/////////////////////////////////////////////////////////////////////////////////////
							////正常遷移系             
							//オーダーを作成/更新する。
							temp_order.calcTotal();
							success_flg = true;
							order = temp_order;
						}else{
							System.err.println("<-----CartInServlet:183---->");
							order.addError_comm( "在庫引当が出来ませんでした" );
						}
					}
				}
			}
			System.err.println("<-----CartInServlet:189---->");
			if( order == null ){	System.err.println("[ CRAT IN 184 ORDER IS NULL ]");	}
			else{	System.err.println("[ CRAT IN 184 ORDER IS NOT NULL ]");	}
			session.setAttribute(Constants.CartOrder, order);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			//answerは、回答ページがHTMLか、XMLかを分岐
			if (anmnswer) { // HTMLに遷移
				RequestDispatcher dispather;
				if (success_flg) {
					System.err.println("<-----CartInServlet:170---->");
					dispather = req.getRequestDispatcher(Constants.CART_START_);
				} else {
					System.err.println("<-----CartInServlet:172---->");
					dispather = req.getRequestDispatcher(Constants.CART_FIAL_);
				}
				dispather.forward(req, res);
				dispather = null;
			} else { //XMLを回答
				res.setContentType("text/xml");
				PrintWriter out = res.getWriter();
				if (success_flg) {
					out.println( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					out.println( "<CXLBOKIN>");
					out.println( "<SUCCESS>");
					out.println( "<ERRORCODE>0</ERRORCODE>");
					out.println( "</SUCCESS>");
					out.println( "</CXLBOKIN>");
					
				} else {
					out.println( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					out.println( "<CXLBOKIN>");
					out.println( "<FAIL>");
					out.println( "<ERRORCODE>-1</ERRORCODE>");
					out.println( "</FAIL>");
					out.println( "</CXLBOKIN>");
				}
				out.close();
			}
			//グローバルの初期化
			order_no = 0;
			user_id = 0;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * サブオーダーのインサート
	 */
	synchronized private boolean addSubOrder(Sub_Order so) {
		boolean rebool = false;
		Navigato navigato = (Navigato)so.getActionclass();
		String classname = navigato.getAllotCheckClass();
		dbProductAdapter dbs =  null;
		///////在庫をチェックする////
		try{
			dbs = (dbProductAdapter)Class.forName( classname ).newInstance();
			dbs.init( this.dss );
			AllotCheckCondition acheck = new AllotCheckCondition();
			if( so.getSheat() > 0 ){
				acheck.setAllot( so.getSheat() );	
			}else{
				acheck.setAllot( so.getPax() );
			}
			acheck.setProduct_type_cd(  so.getPRODUCT_TYPE_CD() );
			acheck.setProduct_cd( so.getPROD_CD() );
			////////在庫を確認/////////
			if( dbs.checkAllotment( acheck ) < 0){
				return false;
			}
			////////在庫を落とす/////////
			dbs.getAllotment( acheck, so.getPax() );			
			dbs = null;
		}catch(Exception ex){	ex.printStackTrace();	
			return rebool;
		}
		dbAdapterSubOrder db = new dbAdapterSubOrder(this.dss);
		rebool = db.addSubOrder(so);
		db = null;
		System.err.println("<---AddSubOrder Sen and reboo is " + rebool + "---->");
		return rebool;
	}
	/**
	 * ユーザーIDの取得
	 */
	private void getUserID(HttpSession session) {
		///ユーザープロファイルの取得
		User userprofile;
		if (session.getAttribute(Constants.CartUser) != null) {
			userprofile = (User) session.getAttribute(Constants.CartUser);
		} else {
			userprofile = new User();
			userprofile.init();
		}
		this.user_id = userprofile.getUser().getUSER_ID();
	}
	/**
	 * サブオーダーNoの取得
	 */
	synchronized private int getSubOrderNo() {	
		dbAdapterSubOrder db = new dbAdapterSubOrder(this.dss);
		int sub_order_no = db.getSubOrderNO();
		db = null;
		return sub_order_no;
	}
	/**
	 * XMLに分解
	 * @param String XML文書
	 */
	synchronized private Order getSaxReadOrder(
		Element element,
		Order ods,
		int status,
		String session_id, Vector buyprops) {
			System.err.println("<-----	CartInServlet:306	---->");
		try {
			List children = element.getChildren();
			System.err.println("<-----	CartInServlet:309	---->");
			if (children.isEmpty()) {
				System.err.println("<-----	CartInServlet:311	---->");
				return null;
			}
			Sub_Order[] subarray = getSaxReadSubOrder( element, status, session_id, buyprops);
			System.err.println("<-----	CartInServlet:314	---->");
			for (int i = 0; i < buyprops.size(); i++) {
				System.err.println("<-----	CartInServlet:316  + " + i + " ---->");
				Sub_Order temp = subarray[i];
				if (temp != null) {
					ods.setSubOrder(temp);
				}
				temp = null;
			}
			System.err.println("<-----	CartInServlet:323 + " + ods.getSubOrderCount() +  " ---->");
			if (ods.getSubOrderCount() > 0) {
				return ods;
			}
			System.err.println("<-----	CartInServlet:327  ---->");
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * サブオーダーのマッピング
	 */
	synchronized private Sub_Order[] getSaxReadSubOrder(
		Element element,
		int status,
		String session_id, Vector buyprops) {
		Sub_Order[] sodarrays = new Sub_Order[buyprops.size()];
		System.err.println( "CartIn:getSaxReadSubOrder:  line 343" );
		try {
			List childrens = element.getChildren();
			Iterator iters = childrens.iterator();
			System.err.println( "CartIn:getSaxReadSubOrder:  line 347" );
			int index = 0;
			while (iters.hasNext()) {
				System.err.println( "CartIn:getSaxReadSubOrder:  line 350" );
				Element els = (Element) iters.next();
				String names = els.getName();
				if (names.equals(SUB_ORDER_TAG)) {
					System.err.println( "CartIn:getSaxReadSubOrder:  line 354" );
					List children = els.getChildren();
					Iterator iter = children.iterator();	
					Sub_Order sod = new Sub_Order();
					///////
					while (iter.hasNext()) {
						System.err.println( "CartIn:getSaxReadSubOrder:  line 360" );
						Element el = (Element) iter.next();
						String name = el.getName();
						String value;
						value = el.getTextTrim();
						if (value.length() != 0) {
							System.err.println( "CartIn:getSaxReadSubOrder:  line 366" );
							if (name.equals("ORDER_XSL_FILE")) {
								sod.setORDER_XSL_FILE(value);
							} else if (name.equals("PRODUCT_TYPE_CD")) {
								sod.setPRODUCT_TYPE_CD(Integer.parseInt(value));
							} else if (name.equals("PROD_CD")) {
								sod.setPROD_CD(value);
							} else if (name.equals("AGT_PROD_CD")) {
								sod.setAGT_PROD_CD(value);
							} else if (name.equals("AGT_PLAN_CD")) {
								sod.setAGT_PLAN_CD(value);
							} else if (name.equals("AGT_CD")) {
								sod.setAGT_CD(value);
							} else if (name.equals("TITLE")) {
								sod.setTITLE(value);
							} else if (name.equals("GUARANTEE_FLG")) {
								sod.setGUARANTEE_FLG(Integer.parseInt(value));
							} else if (name.equals("PRICE")) {
								sod.setPRICE(Integer.parseInt(value));
							} else if (name.equals("THROW_FLG")) {
								//遷移をコントロールはカートではしない
							} else if (name.equals("AGENT_ACTION_URL")) {
								sod.setAGENT_ACTION_URL(value);
							} else if (name.equals("MAKE_DATE")) {
								sod.setMAKE_DATE(value);
							} else if (name.equals("LAST_SALE")) {
								try{
									sod.setLAST_SALE( Long.parseLong(value) );
								} catch (Exception ex) {
									ex.printStackTrace();
									sod.setLAST_SALE(0);
								}
							} else if (name.equals("END_SALE")) {
								try{
									sod.setEND_SALE( Long.parseLong(value) );
								} catch (Exception ex) {
									ex.printStackTrace();
									sod.setEND_SALE(0);
								}
							} else if (name.equals("ETC")) {
								sod.setEtc(value);
							} else if (name.equals("PAX")) {
								try {
									sod.setPax(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setPax(1);
								}
							}else if (name.equals("ADULT")) {
								try {
									sod.setAdult(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setAdult(0);
								}
							} else if (name.equals("CHILD")) {
								try {
									sod.setChild(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setChild(0);
								}
							}else if (name.equals("INFANT")) {
								try {
									sod.setInfant(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setInfant(0);
								}
							}else if (name.equals("SENDING")) {
								try {
									sod.setSending(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setSending(0);
								}
							} else if (name.equals("SENDING_TAX")) {
								try {
									sod.setSending_tax(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setSending_tax(0);
								}
							} else if (name.equals("SENDING_CALC_UNIT")){
								try{
									sod.setSending_calc_unit( Integer.parseInt(value) );
								}catch(Exception ex){
									sod.setSending_calc_unit( 0 );
								}
							} else if (name.equals("TAX")) {
								try {
									sod.setTax(Integer.parseInt(value));
								} catch (Exception ex) {
									sod.setTax(1);
								}
							} else if( name.equals("SALES_DATE")){
								//System.err.println("<-- SERVLETTARGET DATE ---" + value +"------>");
								sod.setSALES_DATE(value);
							} else if( name.equals("SHEAT")){
								try{
									sod.setSheat( Integer.parseInt( value ) );
								}catch(Exception ex){	ex.printStackTrace();	
									sod.setSheat( 0 );
								}
							}
							System.err.println( "CartIn:getSaxReadSubOrder:  line 465" );
						}
					}
					sod.setSessionid(session_id);
					//その他の要素を出力する。
					
					System.err.println( "CartIn:getSaxReadSubOrder:  line 471" );
					if (sod.getMAKE_DATE().length() == 0) {
						DataFormat df = null;
						sod.setMAKE_DATE(df.getNowDate(0, true));
					}
					String buypropstr = (String)buyprops.get( index );
					sod.setBUY_PROP( buypropstr );
					sod.setStatus(status);
					sod.setSUB_ORDER_NO( this.getSubOrderNo() );
					sod.setORDER_NO(this.order_no);
					System.err.println( "CartIn:getSaxReadSubOrder:  line 481" );
					sodarrays[index] = getActionClass( sod );
					if( !addSubOrder( sod ) ){	//サブオーダーの追加
						return null;
					}
					index++;
					System.err.println( "CartIn:getSaxReadSubOrder:  line 484" );
					sod = null;
				}
			}
			System.err.println( "CartIn:getSaxReadSubOrder:  line 488" );
			return sodarrays;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println( "CartIn:getSaxReadSubOrder:  line 492" );
			return null;
		}
	}
	/**
	 * オブジェクトの取得
	 */
	private Sub_Order getActionClass( Sub_Order suborder){
		try{
			System.err.println( "CartIn:getActionClass:  line 498" );
			jp.co.lastminute.cart.prop.Navigato navigate = null;
			if( this.xmlhmap == null){
				System.err.println( "CartIn:getActionClass:  line 501" );
				this.xmlhmap = (HashMap) servletContext.getAttribute(Constants.Product_Type_Mapping_);
			}
			System.err.println( "CartIn:getActionClass:  line 504" );
			jp.co.lastminute.common.xml.XmlReader xreader = new jp.co.lastminute.common.xml.XmlReader( xmlhmap );
			System.err.println( "CartIn:getActionClass:  line 506" );
			String classtarget = xreader.getFlowClass( suborder.getAGT_CD(), suborder.getPRODUCT_TYPE_CD() ).trim();
			System.err.println("<-----" + classtarget + "--" + suborder.getAGT_CD() + "---" + suborder.getPRODUCT_TYPE_CD() +"-->");
			suborder.setActionclass( Class.forName( classtarget ).newInstance() );
			System.err.println( "CartIn:getActionClass:  line 507" );
			return (suborder);
		}catch(Exception ex){	ex.printStackTrace();	}
		return suborder;
	}

	/**
	 * ドキュメントをマッピング
	 */
	synchronized private String DocToXML(Document doc) throws Exception {
		try {
			XMLOutputter putter = new XMLOutputter("", false);
			putter.setOmitDeclaration(true);
			putter.setIndent(false);
			putter.setEncoding("UTF-8");
			return putter.outputString(doc);
		} catch (Exception e) {
			throw e;
		}
	}
	///エラーの判断
	private int isErrorbyParameter(HttpServletRequest req) {
		if ((req.getAttribute(this.CONN_STYLE) == null)
			&& (req.getParameterValues(this.CONN_STYLE) == null)) {
			return -1;
		}
		if ((req.getAttribute(this.PRODUCT_TYPE_CD) == null)
			&& (req.getParameterValues(this.PRODUCT_TYPE_CD) == null)) {
			return -1;
		}
		if ((req.getAttribute(this.AGT_CD) == null)
			&& (req.getParameterValues(this.AGT_CD) == null)) {
			return -1;
		}
		if ((req.getAttribute(this.XML_PARA) == null)
			&& (req.getParameterValues(this.XML_PARA) == null)) {
			return -1;
		}
		try {
			if (req.getAttribute(this.CONN_STYLE) != null) {
				return Integer.parseInt(
					(String) req.getAttribute(this.CONN_STYLE));
			}
			return Integer.parseInt(req.getParameterValues(this.CONN_STYLE)[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	//オーダーの内容について確認
	protected static boolean isAddSuborder(Order orders, int user_id ) {
		if ((user_id == 0) && (orders.getSubOrderCount() > 0)) {
			return false;
		}
		for (int i = 0; i < orders.getSubOrderCount(); i++) {
			//ギフト以外でステータスが2以外ならエラー
			//if (orders.getSubOrder(i).getStatus() != Constants.CON_CONFIRM_) {
			if (orders.getSubOrder(i).getStatus() < Constants.CON_CONFIRM_) {
				if( !CheckTool.isPassCart( orders.getSubOrder(i).getPRODUCT_TYPE_CD()) ){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * オーダーの最終ステータスの確認
	 */
	public static int lookupStatus( Order orders ){
		int tempstatus_ = 0;
		for (int i = 0; i < orders.getSubOrderCount(); i++) {
			if ((orders.getSubOrder(i).getStatus() > tempstatus_ )&&
			 (orders.getSubOrder(i).getStatus() < Constants.CANCLL_STATUS_) ){
				tempstatus_ = orders.getSubOrder(i).getStatus();
			}
		}
		return tempstatus_;
	}
}