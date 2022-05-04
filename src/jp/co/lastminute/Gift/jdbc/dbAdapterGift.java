package jp.co.lastminute.Gift.jdbc;

import jp.co.lastminute.*;
import jp.co.lastminute.Gift.*;
import jp.co.lastminute.Gift.Property;
import jp.co.lastminute.Gift.detail.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.jdbc.*;


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

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class dbAdapterGift implements dbProductAdapter, Serializable {

	private static final int cat_id = Property.cat_id;
	private static final String detailsql = Property.detailsql;
	private static final String allotchecksql = Property.allotchecksql;
	private static final String allotcatchsql = Property.allotcatchsql;
	private static final String allotreturnsql = Property.allotreturnsql;
	private static final String listsql = Property.listsql;
	private static final String listsqlallot = Property.listsqlallot;
	private static final String listsqlscatid = Property.listsqlscatid;
	private static final String memberUpdatesql = Property.memberUpdatesql;
	private static final String suborderUpdatesql = Property.suborderUpdatesql;
	
	private int totalPages = 0;
	
	private DataSource ds;
	/**
	 * コンストラクター
	 */
	public dbAdapterGift() {
	}
	
	public dbAdapterGift(DataSource ds) {
		this.ds = ds;
	}
	public void init(DataSource ds) {
		this.ds = ds;
	}
	/**
	 * リスト用XML出力
	 */
	public String getLIst(Object obj) {
		String reStr = "";
		DataFormat dataformat = null;
		ParseFormat pf = null;
		try{
			GiftSearchCondition searchCondition = (GiftSearchCondition)obj;
			//SQL作成　{ 出発日, 出発時間, 出発地群, 到着地群};
			
			String value01 = searchCondition.getScatid();
			String value02 = searchCondition.getPages();
			String value03 = searchCondition.getSordtype();
			
			String[] ceateStr = { value01 };
			String sql = listsql;
			sql = Sqlmaker.strPrintf( sql, ceateStr );
					
			//ページカウント
			int from = 0;
			int to = Property._pagingsize;
			if(( value02.length() > 0 )&&( !value02.equals("0") )){
				from = Property._pagingsize * Integer.parseInt( value02 ) ;
				to = from + Property._pagingsize;
			}
			//一度、SQLをメーク
			if( value03.length() > 0 ){
				sql += "ORDER BY A." + value03.toUpperCase();
			}else{
				sql += "ORDER BY A.PRICE";
			}
			
			System.err.println(sql);
			/**
			 * もし在庫数を要求してきたら
			 */
			
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){	
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
					dbDataAccesser dba = new dbDataAccesser( rows );
					reStr ="<LIST_VIEW>"
				         + "<SEARCH_CONDITION>\n"
				         + "<CATID>" + Property.cat_id + "</CATID>\n"
						 + "<SCATID>\n"
						 + "<ID>" + value01 + "</ID>\n"
						 + "<NAME><![CDATA[" + dba.getData(0, 7) + "]]></NAME>\n"
						 + "</SCATID>\n"
						 + "</SEARCH_CONDITION>\n"
						 + "<ROWCOUNT>"+dba.getRowsize()+"</ROWCOUNT>\n<LISTS>\n";
					this.setTotalPages( ((dba.getRowsize() - dba.getRowsize()%Property._pagingsize))/Property._pagingsize );
					for(int i=from; i<to; i++){
						if( i< dba.getRowsize() ){
						reStr += "<LIST>\n"
	                          + "<SUMIMG>"+dba.getData(i, 0 ) + "</SUMIMG>\n"
	                          + "<LINK><![CDATA[" + dba.getData(i, 0 ) + "]]></LINK>\n"
	                          + "<TYPE><NAME><![CDATA[" +  dba.getData(i, 2 )+ "]]></NAME></TYPE>\n" 
	                          + "<COPY><![CDATA[ " + dba.getData(i, 3 ) + "]]></COPY>\n"
	                          + "<SALE_TERM><![CDATA[" +  dba.getData(i, 3 ) + "]]></SALE_TERM>\n" 
	                          + "<PRICE>" + dba.getData(i, 1 ) + "</PRICE>\n"
	                          + "<PRICESTR>" + pf.ToPriceFormat( dba.getData(i, 1 )) + "</PRICESTR>\n"
	                          + "</LIST>\n";						
						}
					}
					reStr += "</LISTS>\n</LIST_VIEW>";
					return reStr;
				}
			} 		
		}catch(Exception ex){				
			ex.printStackTrace();
		}
		return reStr;
	}
	private String[] modifydateTime( String[] strs, String nowdatetime ) throws Exception{
		if( strs[0].length() > 10 ){
			return strs;
		}
		DataFormat dataformat = null;
		String b_date_Str = dataformat.AddToDate( nowdatetime.substring(0, 8), 1 );
		String b_time_Str = nowdatetime.substring( 8, 12 );
		int b_time = Integer.parseInt( b_time_Str );
		int b_date = Integer.parseInt( b_date_Str );
		int t_date = Integer.parseInt( strs[0] );
		System.err.println( "b_time " + b_time );
		System.err.println( "b_date " + b_date );
		System.err.println( "t_date " + t_date );
		if( b_date >=  t_date){
			if( b_time - 10  > Integer.parseInt( Property.endsale ) ){
				strs[0] = dataformat.AddToDate( b_date_Str, 1 );
			}else{
				strs[0] = b_date_Str;	
			}
			strs[1] = "0000";
		}
		return strs;
	}
	/**
	 * 詳細用XML出力
	 */
	public String getDetail(Object obj) {
		String reStr = "";
		try{
			GiftSearchCondition searchCondition = (GiftSearchCondition)obj;
			StringBuffer sb = new StringBuffer();
			//SQL作成　{商品コード, 在庫コード};
			String value00 = searchCondition.getCatid();
			String value01 = searchCondition.getProduct_id();
			String[] values = { value00, value01 };
			String sql = Sqlmaker.strPrintf( detailsql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			System.err.print( "JDBC ADAPTER VECOTR NEW DB" );
			if( db.init( this.ds ) ){
				Vector rows = db.dbSelect( sql );
				System.err.print( "JDBC ADAPTER VECOTR SIZE=" + rows.size() );
				dbDataAccesser dba = new dbDataAccesser( rows );
				int maxorder = Property._maxorder;
				if( dba.getDatabyInt(0, 8) < Property._maxorder ){
					maxorder = dba.getDatabyInt(0, 8) ;
				}
				sb.append("<registform>\n");
					sb.append("<product_id>" + dba.getData(0, 0) + "</product_id>\n");
					sb.append("<product_name><![CDATA[" + dba.getData(0, 11) + "]]></product_name>\n");
					sb.append("<agt_cd>" + dba.getData(0, 1) + "</agt_cd>\n");
					sb.append("<price>" + dba.getDatabyInt(0, 3) + "</price>\n");
					sb.append("<tax>" + dba.getDatabyInt(0, 4) + "</tax>\n");
					sb.append("<sending>" + dba.getDatabyInt(0, 5) + "</sending>\n");
					sb.append("<sending_tax>" + dba.getDatabyInt(0, 6) + "</sending_tax>\n");
					sb.append("<sending_calc_unit>" + dba.getDatabyInt(0, 7) + "</sending_calc_unit>\n");
					sb.append("<minorder>" + Property._minorder + "</minorder>\n");
					sb.append("<maxorder>" + maxorder + "</maxorder>\n");
					sb.append("<allot>" + dba.getDatabyInt(0, 8) + "</allot>\n");
					sb.append("<orders>" + makeOrderNum( Property._minorder, maxorder) + "</orders>\n");
					sb.append("<options>" + makeOptions( dba.getData(0, 10)) + "</options>\n");
				sb.append("</registform>\n");
				reStr = sb.toString();
			} 		
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
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
			AllotCheckCondition allotCheckCondition = (AllotCheckCondition)obj;
			String value00 = allotCheckCondition.getProduct_cd();
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
	public Sub_Order bookingOrder( Sub_Order suborder, HashMap xmlhash ){
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
	public String confirmOrder( Sub_Order suborder ){
		return "";	
	}
}
