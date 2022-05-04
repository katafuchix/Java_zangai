package jp.co.lastminute.Hotel.jdbc;

import jp.co.lastminute.*;
import jp.co.lastminute.Hotel.*;
import jp.co.lastminute.Hotel.allot.*;
import jp.co.lastminute.Hotel.allot.ProductRegistrationFrom;
import jp.co.lastminute.Hotel.PriceView;
import jp.co.lastminute.Hotel.Property;
import jp.co.lastminute.Hotel.areas.Areas;
import jp.co.lastminute.Hotel.detail.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.jdbc.*;

import jp.co.lastminute.cart.xml.*;
import jp.co.lastminute.common.xml.XmlReader;

import jp.co.yobrain.util.rpc.SendClient;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.jdbc.Sqlmaker;
import jp.co.yobrain.util.ParseFormat;

import jp.co.yobrain.util.OptionLabel;

import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class dbAdapterHotel implements dbProductAdapter, Serializable {
	private static final String OptionLabels_ = "_optionLabels_";
	private static final String ALtps_ = "aLtps_";
	private ServletContext context = null;
	private static int cat_id = Property.cat_id;
	private static String detailsql = Property.detailsql;
	private static String allotchecksql = Property.allotchecksql;
	private static String allotcatchsql = Property.allotcatchsql;
	private static String allotreturnsql = Property.allotreturnsql;
	private static String listsqlallot = Property.listsqlallot;
	private static String listsqlscatid = Property.listsqlscatid;
	private static String memberUpdatesql = Property.memberUpdatesql;
	private static String suborderUpdatesql = Property.suborderUpdatesql;
	
	private int totalPages = 0;
	
	private DataSource ds;
	/**
	 * コンストラクター
	 */
	public dbAdapterHotel() {
	}
	
	public dbAdapterHotel(DataSource ds) {
		this.ds = ds;
	}
	public void init(DataSource ds) {
		this.ds = ds;
	}
	public void setContext( ServletContext context ){
		this.context = context;
	}
	private static HotelSearchCondition modiftHotelSearchCondition( HotelSearchCondition searchCondition){
		DataFormat dataformat = null;
		if( searchCondition.getCheckindate().length() == 0 ){
			searchCondition.setCheckindate( dataformat.getNowDate(0, true) );
			searchCondition.setNight( "0" );
		}
		if( searchCondition.getNight().length() == 0){
			searchCondition.setNight( "0" );
		}
		return searchCondition;
	}
	/**
	 * リスト用XML出力
	 */
	public String getLIst(Object obj) {
		String reStr = "";
		
		ParseFormat pf = null;
		try{
			HotelSearchCondition searchCondition = (HotelSearchCondition)obj;
			
			searchCondition = modiftHotelSearchCondition( searchCondition );
			
			String sql = getListSql( searchCondition );
					
			//ページカウント
			int from = 0;
			int to = Property._pagingsize;
			String pages = searchCondition.getPages();
			if(( pages.length() > 0 )&&( !pages.equals("0") )){
				from = Property._pagingsize * Integer.parseInt( pages ) ;
				to = from + Property._pagingsize;
			}
			System.err.println(sql);
			jp.co.lastminute.Hotel.areas.Areashandler areas = 
			new jp.co.lastminute.Hotel.areas.Areashandler( );
			areas.setServletContext( this.context );
			jp.co.lastminute.Hotel.localarea.localareashandler localarea =
			new jp.co.lastminute.Hotel.localarea.localareashandler();
			localarea.setServletContext( this.context  );
			jp.co.lastminute.common.Scatidhandler scatids =
			new jp.co.lastminute.common.Scatidhandler();
			scatids.setServletContext( this.context  );			
			//クエリー発行
			System.err.println( sql );
			StringBuffer sb = new StringBuffer();
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){	
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
					dbDataAccesser dba = new dbDataAccesser( rows );
					sb.append("<LIST_VIEW>\n"
				         + "<SEARCH_CONDITION>\n"
				         + "<CATID>" + Property.cat_id + "</CATID>\n"
						 + "<SCATID>\n"
						 + "<ID>" + searchCondition.getScatid() + "</ID>\n"
						 + "</SCATID>\n"
						 + "<POSTAL>\n"
						 + "<STATE_CD>" + searchCondition.state_cd + "</STATE_CD>\n"
						 + "<STATE_NAME>" + areas.getStateName( searchCondition.state_cd ) + "</STATE_NAME>\n"
						 + "<CITY_CD>" + searchCondition.city_cd + "</CITY_CD>\n"
						 + "<CITY_NAME>" + areas.getCityName( searchCondition.state_cd,searchCondition.city_cd ) + "</CITY_NAME>\n"
						 + "</POSTAL>\n"
						 + "<LOCALAREA>"
						 + "<LOCAL_AREA_CODE>" + searchCondition.local_area_code + "</LOCAL_AREA_CODE>\n"
						 + "<LOCAL_AREA_CODE_NAME>" + localarea.getLocalareaStr( searchCondition.local_area_code ) + "</LOCAL_AREA_CODE_NAME>\n"
						 + "</LOCALAREA>\n"
						 + "</SEARCH_CONDITION>\n"
						 + "<ROWCOUNT>"+dba.getRowsize()+"</ROWCOUNT>\n"
						 + "<LISTS>\n");
					this.setTotalPages( ((dba.getRowsize() -1 - dba.getRowsize()%Property._pagingsize))/Property._pagingsize );
					String tempsupnbr = "";
					for(int i=from; i<to; i++){
						if( i< dba.getRowsize() ){
							if( !tempsupnbr.equals( dba.getData(i, 0)) ){
								if( tempsupnbr.length() != 0 ){
									sb.append("</PLISTS></SLIST>\n");
								}
								sb.append("<SLIST>\n"
									  + "<SUPNBR><![CDATA[" + dba.getData(i, 0) + "]]></SUPNBR>\n"
									  + "<JPNNAM><![CDATA[" + dba.getData(i, 3) + "]]></JPNNAM>\n"
									  + "<AGT_CD>" + dba.getData(i, 1) + "</AGT_CD>\n"
									  + "<CATCH_COPY><![CDATA[" + dba.getData(i, 4) + "]]></CATCH_COPY>\n"
									  + "<PLISTS>\n");
								tempsupnbr = dba.getData(i, 0);
							}
							sb.append("<PLIST>\n"
	                          + "<PRODUCT_ID><![CDATA[" + dba.getData(i, 2 ) + "]]></PRODUCT_ID>\n"
	                          + "<SUPNBR><![CDATA[" + dba.getData(i, 0) + "]]></SUPNBR>\n"
	                          + "<AGT_CD>" + dba.getData(i, 1) + "</AGT_CD>\n"
	                          + "<COPY><![CDATA[ " + dba.getData(i, 7) + "]]></COPY>\n"
	                          + "<PRICESTR><![CDATA[" +  dba.getData(i, 6) + "]]></PRICESTR>\n"
	                          + "<PRICESTRHTM><![CDATA[" +  PriceView.getViewStr4Search( dba.getData(i, 6) ) + "]]></PRICESTRHTM>\n"
	                          + "</PLIST>\n");						
						}
					}
					sb.append("</PLISTS>\n</SLIST>\n</LISTS>\n</LIST_VIEW>");
					String restr = sb.toString();
					System.err.println( restr );
					return restr;
				}
			} 		
		}catch(Exception ex){				
			ex.printStackTrace();
		}
		return reStr;
	}
	/**
	 * 詳細出力リストハッシュマップ
	 */
	public HashMap getDetailHash(Object obj) {
		try{
			HotelSearchCondition searchCondition = (HotelSearchCondition)obj;
			searchCondition = modiftHotelSearchCondition( searchCondition );
			int checkin = Integer.parseInt( searchCondition.getCheckindate() );
			int night = Integer.parseInt( searchCondition.getNight() );
			return getCampainparam( searchCondition.supnbr, searchCondition.getAgtcode(), checkin, night);
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;		
	}
	/**
	 * 詳細用XML出力
	 */
	public String getDetail(Object obj) {
		try{
			System.err.println("196");
			Webaltp[] altps = getDetailList( obj );
			if( altps == null ){	System.err.println("198");
				return "";
			}
			System.err.println("201");
			PriceListMaker priceListMaker = new PriceListMaker( altps );
			return priceListMaker.getList();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	public Webaltp[] getDetailList(Object obj) {
		HotelSearchCondition searchCondition = (HotelSearchCondition)obj;
		//検索対象の出力
		String reStr = "";
		DataFormat df = null;
		int tolength = 4  ;
		int size = 7;
		String nowdate =  df.getNowDate(tolength, true);
		String to = df.getNowDate(tolength, true);
		String from = df.DelToDate( to, 7 );
		////
		String sql = "SELECT "
				    +"A.ALTDAT,"	//00
				    +"A.PRICE,"		//01
				    +"A.PRICE2,"	//02
				    +"A.PRICE3,"	//03
				    +"A.PRICE4,"	//04
				    +"A.PRICE5,"	//05
				    +"A.PRICEA1,"	//06
				    +"A.PRICEA2,"	//07
				    +"A.PRICEA3,"	//08
				    +"A.PRICEA4,"	//09
				    +"A.PRICEB1,"	//10
				    +"A.PRICEB2,"	//11
				    +"A.PRICEB3,"	//12
				    +"A.PRICEB4,"	//13
				    +"A.PRICEC1,"	//14
				    +"A.PRICED1,"	//15
				    +"A.PRICEE,"	//16
				    +"A.HAVEALT,"	//17
				    +"B.CAMPAIGN, "	//18
				    +"B.AGT_ROOMTYPE,"	//19
				    +"B.MEAL_CODE, "	//20
				    +"B.MAX_NR, "		//21
				    +"B.MIN_NR "		//22
				    +"FROM WEBALTP A,PRODUCT_ALTP B "
				    +"WHERE "
				    +"A.PRODUCT_SEQ_NO=B.PRODUCT_SEQ_NO AND "
				    +"TO_DATE(A.ALTDAT)<=SYSDATE+B.START_DAY AND "
				    +"TO_DATE(A.ALTDAT)>=SYSDATE+B.LAST_DAY AND "
				    + from +"<= A.ALTDAT AND A.ALTDAT<="+ to +" AND "
				    +"B.PRODUCT_ID='" + searchCondition.getProduct_id() +  "' "
				    +"ORDER BY ALTDAT";
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					dbDataAccesser accesser = new dbDataAccesser( rows );
					return Webaltp_handler.init( accesser, from, size );
				}
			} 
		return null;
	}
	private static String makeOrderNum( int from, int to ){
		try{
			StringBuffer sb = new StringBuffer();
			for(int i=from; i<=to; i++){
				sb.append("<option><value>" + i + "</value></option>\n");
			}
			return sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	private static String makeOptions( String str ){
		if( str.length() == 0 ){
			return "";	
		}
		try{
			StringTokenizer st = new StringTokenizer( str, ",");
			StringBuffer sb = new StringBuffer();
			while( st.hasMoreTokens() ){
				String value = st.nextToken();
				if( value.trim().length() > 0 ){
					sb.append("<option><value><![CDATA[" + value + "]]></value></option>\n");
				}
			}
			return sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	/**
	 * 在庫確認
	 */
	public int checkAllotment(Object obj) {
		int reInt = -1;
		try{
			ProductRegistrationFrom allotCheckCondition = (ProductRegistrationFrom)obj;
			/**String value00 = allotCheckCondition.getProduct_cd();
			String value01 = Integer.toString( allotCheckCondition.getProduct_type_cd() );
			String value02 = Integer.toString( allotCheckCondition.getAllot() );
			String[] values = { value02, value00 };
			String sql = Sqlmaker.strPrintf( allotchecksql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					dbDataAccesser accesser = new dbDataAccesser( rows );
					reInt = accesser.getDatabyInt(0, 0);
					if( reInt < 0 )	reInt = 0;
				}
			}
			*/
			
		}catch(Exception ex){}
		return reInt;
	}
	/**
	 * 在庫引き戻し
	 */
	public void returnAllotment(Object obj, int allot) {
		try{
			AllotCheckCondition allotCheckCondition = (AllotCheckCondition)obj;
			String value00 = allotCheckCondition.getProduct_cd();
			String value01 = Integer.toString( allotCheckCondition.getProduct_type_cd() );
			String value02 = Integer.toString( allot );
			String[] values = { value02, value00  };
			String sql = Sqlmaker.strPrintf( allotreturnsql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				db.dbUpdate( sql );
			} 
		}catch(Exception ex){}
	}
	/**
	 * 在庫引当
	 */
	public boolean getAllotment(Object obj, int allot) {
		boolean rebool = false;
		try{
			AllotCheckCondition allotCheckCondition = (AllotCheckCondition)obj;
			String value00 = allotCheckCondition.getProduct_cd();
			String value01 = Integer.toString( allotCheckCondition.getProduct_type_cd() );
			String value02 = Integer.toString( allotCheckCondition.getAllot() );
			String[] values = { value02, value00 };
			String sql = Sqlmaker.strPrintf( allotcatchsql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return rebool;
	}
	public boolean cancellOrder( int suborder ){
		return true;
	}
	public boolean memberUpdate( Sub_Order suborder){
		String[] values = new String[3];
		try{
			values[0] = "" + suborder.getPax()+ "";
			values[1] = suborder.getMemberStr();
			values[2] = "" + suborder.getSUB_ORDER_NO() + "";
			String sql = Sqlmaker.strPrintf( memberUpdatesql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	public boolean sendingUpdate( Sub_Order suborder, Product_Send sending ){
		return true;
		
	}
	public boolean suborderUpdate( Sub_Order suborder){
		String[] values = new String[2];
		try{
			values[0] = "" + suborder.getPax()+ "";
			values[1] = "" + suborder.getSUB_ORDER_NO() + "";
			String sql = Sqlmaker.strPrintf( suborderUpdatesql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	public Sub_Order bookingOrder( Sub_Order suborder,HashMap xmlhash ){
		//エージェントコード//プロダクトタイプを取得
		String agt_cd = suborder.getAGT_CD();
		int product_type_cd = suborder.getPRODUCT_TYPE_CD();
		XmlReader xreader = new XmlReader( xmlhash );
		String send_url = xreader.getSendurl( agt_cd, product_type_cd);
		String convxsl = xreader.getSendxsltAmount( agt_cd, product_type_cd );
		SupplyOrderResult result;
		XmlBeanBase xmlbeen = null;
		try{
			result = (SupplyOrderResult)XmlPostHelper.querySupplyer( send_url, 
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+"<ORDER><SUB_ORDER>"+  suborder.getXMLdocument() + "\n</SUB_ORDER></ORDER>", 
						convxsl, 
						"SupplyOrderResult");
			suborder.setReturnXmlStr( result.getXmldocument() );
			if(result.isSuccess() == true){
				suborder.setBooking( true );
				suborder.setAGT_ORDER_NO( result.getORDERNO() );
				suborder.setPRICE( Integer.parseInt( result.getPRICE() ) );
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return suborder;
	}
	/**
	 * Returns the totalPages.
	 * @return int
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets the totalPages.
	 * @param totalPages The totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * リストＳＱＬの取得
	 */
	public static String getListSql( HotelSearchCondition conditon ){
		String from = "";
		String where = "";
		if( conditon.state_cd.length() > 0){
			from = ", (" + getSqlSupnbrbyPostal( conditon ) + ") CC";
			where ="AA.SUPNBR=BB.SUPNBR AND "
				  +"AA.SUPNBR=CC.SUPNBR AND "
				  +"AA.AGT_CD=BB.AGT_CD AND "
				  +"AA.AGT_CD=CC.AGT_CD";
		}else if( conditon.local_area_code.length() > 0){
			from = ", (" + getSqlSupnbrbyLocalArea( conditon ) + ") CC";
			where ="AA.SUPNBR=BB.SUPNBR AND "
				  +"AA.SUPNBR=CC.SUPNBR AND "
				  +"AA.AGT_CD=BB.AGT_CD AND "
				  +"AA.AGT_CD=CC.AGT_CD";
		}else{
			where ="AA.SUPNBR=BB.SUPNBR AND "
				  +"AA.AGT_CD=BB.AGT_CD";
		}
		String orderby = "ORDER BY AA.JPNNAM,BB.PRICE"; 
		if( conditon.getSordtype().equals("1") ){
			orderby = "ORDER BY BB.PRICE,AA.JPNNAM"; 
		}
		String sql="SELECT "
				+ "BB.SUPNBR,"
				+ "BB.AGT_CD,"
				+ "BB.PRODUCT_ID,"
				+ "AA.JPNNAM,"
				+ "AA.CATCH_COPY,"
				+ "BB.PRICE,"
				+ "BB.PRICE_CATCH,"
				+ "BB.AGT_ROOMTYPE_NAME "
				+ "FROM "
				+ "MADRSP AA,(" + getSqlSupnbr( conditon ) + ") BB"
				+ from + " "
				+ "WHERE " + where + " "
				+ orderby;				
		return sql;
	}
	/**
	 * 検索リストのＳＱＬ出力 s_catidがない場合
	 */
	public static String getSqlSupnbr( HotelSearchCondition conditon ){
		if( conditon.getScatid().length() == 0 ){
			return "SELECT "
				   +"A.SUPNBR,"
				   +"A.AGT_CD,"
				   +"A.PRODUCT_ID,"
				   +"A.PRICE_CATCH,"
				   +"A.PRICE,"
				   +"A.AGT_ROOMTYPE_NAME "
				   +"FROM PRODUCT_ALTP A "
				   +"WHERE "
				   +"A.HAVEALT=0 AND "
				   + getLMJcampaign( conditon )
				   +"TO_DATE(A.ALTDAT_FROM)+A.LAST_DAY<=TO_DATE("+conditon.getCheckindate()+") AND "
				   +"SYSDATE+A.START_DAY>=TO_DATE("+conditon.getCheckindate()+"+"+conditon.getNight()+" AND"
				   +"TO_DATE( A.ALTDAT_TO )>=TO_DATE("+conditon.getCheckindate()+"+"+conditon.getNight();
		}
		return getSqlSupnbrbyScatid( conditon );
	}
	/**
	 * ＳＵＰＮＢＲ ＳＱＬ FROM s_catid
	 */
	public static String getSqlSupnbrbyScatid( HotelSearchCondition conditon ){
		return "SELECT DISTINCT "
				+"A.SUPNBR,"
				+"A.AGT_CD,"
				+"A.PRODUCT_ID,"
				+"A.PRICE_CATCH,"
				+"A.PRICE,"
				+"A.AGT_ROOMTYPE_NAME "
				+"FROM PRODUCT_ALTP A,SCATID_MARGE B "
				+"WHERE "
				+"A.HAVEALT=0 AND "
				+ getLMJcampaign( conditon )
				+"TO_DATE( A.ALTDAT_FROM )+A.LAST_DAY<=TO_DATE("+conditon.getCheckindate()+") AND "
				+"SYSDATE+A.START_DAY>="
				+"TO_DATE("+conditon.getCheckindate()+")+"
				+conditon.getNight()+" AND "
				+"TO_DATE( A.ALTDAT_TO )>=TO_DATE("+conditon.getCheckindate()+")+"
				+ conditon.getNight()+" AND "
				+"B.JAN_CD=A.PRODUCT_SEQ_NO AND "
				+"B.CATID=4 AND "
				+"B.S_CATID="+ conditon.getScatid();
	}
	private static String getLMJcampaign(HotelSearchCondition conditon){
		if( conditon.lmjcampaign.length() > 0 ){
			return "A.LMJCAMPAIGN=" + conditon.lmjcampaign + " AND ";
		}
		return "";
	}
	/**
	 * ＳＵＰＮＢＲ　ＳＱＬ FROM localarea
	 */
	public static String getSqlSupnbrbyLocalArea( HotelSearchCondition conditon ){
		String from = "LOCAL_AREA_SUPNBR_STORED A ";
		String where = "";
		if( conditon.local_area_code.length() == 0 ){
			conditon.local_area_code = "1";
		}
		if( conditon.htlcat.length() > 0 ){
			from = "LOCAL_AREA_SUPNBR_STORED A,HTLCAT_SUPNBR B ";
			where = "A.SUPNBR=B.SUPNBR AND A.AGT_CD=B.AGT_CD AND ";
		}
		return "SELECT A.AGT_CD, "
			   +"A.SUPNBR "
			   +"FROM " + from
			   +"WHERE "
			   + where
			   +"A.LOCAL_AREA_CODE=" + conditon.local_area_code;
	}
	/**
	 * ＳＵＰＮＢＲ　ＳＱＬ FROM POSTAL
	 */
	public static String getSqlSupnbrbyPostal( HotelSearchCondition conditon ){
		String from = "POSTAL_SUPNBR_STORED A, POSTAL_CD_MASTER B ";
		String where = "A.POSTAL_CD=B.POSTAL_CD AND ";
		String cityStr = "";
		if( conditon.city_cd.length() > 0 ){
			cityStr = " AND B.CITY_CD=" + conditon.city_cd;
		}
		if( conditon.state_cd.length() == 0 ){
			conditon.state_cd = "13";
		}
		if( conditon.htlcat.length() > 0 ){
			from = "POSTAL_SUPNBR_STORED A,POSTAL_CD_MASTER B,HTLCAT_SUPNBR C ";
			where = "A.SUPNBR=C.SUPNBR AND A.AGT_CD=C.AGT_CD AND "
				  + "A.POSTAL_CD=B.POSTAL_CD AND ";
		}
		return "SELECT A.AGT_CD, "
			   +"A.SUPNBR "
			   +"FROM " + from
			   +"WHERE "
			   + where
			   +"B.STATE_CD=" + conditon.state_cd
			   + cityStr;
	}
	/**
	 * キャンペーン・プランの取得
	 */
	public HashMap getCampainparam( String supnbr, String agt_cd, int checkindate, int night  ){
		HashMap remap = new HashMap();
		String sql = "SELECT PRODUCT_ID,"		//00
					+"AGTROOMTYPE,"				//01
					+"AGT_ROOMTYPE_NAME,"		//02
					+"AGT_CD,"					//03
					+"SUPNBR,"					//04
					+"CAMPAIGN,"				//05
					+"LMJCAMPAIGN,"				//06
					+"MEAL_CODE,"				//07
					+"HAVEALT,"					//08
					+"ALTDAT_FROM,"				//09
					+"ALTDAT_TO,"				//10
					+"ALLOTNUM,"				//11
					+"PRICE_TYPE,"				//12
					+"PRICE,"					//13
					+"MAX_NR,"					//14
					+"MIN_NR,"					//15
					+"AGR_AREA_CD,"				//16
					+"LAST_DAY,"				//17
					+"START_DAY,"				//18
					+"PRICE_CATCH,"				//19
					+"ROOM_CAPA, "				//20
					+"PRODUCT_SEQ_NO "			//21
					+"FROM PRODUCT_ALTP "
					+"WHERE "
					+"HAVEALT=0 AND "
					+"AGT_CD='" + agt_cd + "' AND "
					+"SUPNBR='" + supnbr + "' AND "
					+"TO_DATE(ALTDAT_FROM)+LAST_DAY<=TO_DATE("+checkindate+") AND "
					+"SYSDATE+START_DAY>=TO_DATE("+checkindate+")+"+night+ " AND " 
					+"TO_DATE(ALTDAT_TO)>=TO_DATE("+checkindate+")+"+night+" "
					+"ORDER BY AGT_ROOMTYPE_NAME";
		try{
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){					
					dbDataAccesser accesser = new dbDataAccesser( rows );
					OptionLabel[] reStrs = new OptionLabel[  accesser.getRowsize() ];
					Product_Altp[] altps = new Product_Altp[ accesser.getRowsize() ];					
					for(int i=0; i<accesser.getRowsize(); i++){
						OptionLabel option = new OptionLabel( accesser.getData(i, 2), accesser.getData(i, 0) );
						reStrs[i] = option;
						option = null;
						////
						Product_Altp altp = new Product_Altp();
						////
						altp.agt_cd = accesser.getData(i, 3);
						altp.supnbr = accesser.getData(i, 4);
						altp.product_id = accesser.getData(i, 0);
						altp.campaign = accesser.getData(i, 5);
						altp.agt_roomtype = accesser.getData(i, 7);
						altp.agt_roomtype_name = accesser.getData(i, 2);
						altp.meal_code = accesser.getData(i, 20);
						altp.room_capa = accesser.getData(i, 7);
						altp.max_nr = accesser.getDatabyInt(i, 14);
						altp.min_nr = accesser.getDatabyInt(i, 15);
						altp.last_day = accesser.getDatabyInt(i, 17);
						altp.start_day = accesser.getDatabyInt(i, 18);						
						altp.product_seq_no = accesser.getDatabyInt(i, 21);
						altp.altdat_from = accesser.getData(i, 9);
						altp.altdat_to = accesser.getData(i, 10);
						altp.price = accesser.getDatabyInt(i, 13);						
						altp.price_type = accesser.getDatabyInt(i, 12);
						altp.lmjcampaign = accesser.getDatabyInt(i, 6);						
						altp.price_catch = accesser.getData(i, 10);
						altps[ i ] = altp;
						altp = null;
					}
					remap.put( OptionLabels_ , reStrs);
					remap.put( ALtps_ , altps);
					return remap;
				}
			} 
		}catch(Exception ex){}
		return null;
	}
	/**
	 * 確認用データの取得
	 */
	public String confirmOrder( Sub_Order suborder ){
		try{
			CheckAllote chkcllote = (CheckAllote)Class.forName( "jp.co.lastminute.Hotel.allot.Agent." + suborder.getAGT_CD() ).newInstance();
			String url = chkcllote.getConfirmUrl();
			
			if( url.length() > 0 ){
				Vector paramsVector = new Vector();
				url = url + suborder.getAGT_ORDER_NO();
				SendClient postManager = new SendClient();
				return postManager.sendText(url, paramsVector);
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";	
	}
}
