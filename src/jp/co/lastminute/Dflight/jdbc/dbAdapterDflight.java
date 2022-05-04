package jp.co.lastminute.Dflight.jdbc;

import jp.co.lastminute.*;
import jp.co.lastminute.Dflight.*;
import jp.co.lastminute.Dflight.detail.*;
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
import jp.co.yobrain.util.DataFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class dbAdapterDflight implements dbProductAdapter, Serializable {

	private static int cat_id = Property.cat_id;
	private static String detailsql = Property.detailsql;
	private static String allotchecksql = Property.allotchecksql;
	private static String allotcatchsql = Property.allotcatchsql;
	private static String allotreturnsql = Property.allotreturnsql;
	private static String listsql = Property.listsql;
	private static String listsqlallot = Property.listsqlallot;
	private static String listsqlscatid = Property.listsqlscatid;
	private static String memberUpdatesql = Property.memberUpdatesql;
	private static String suborderUpdatesql = Property.suborderUpdatesql;
	private DataSource ds;
	/**
	 * コンストラクター
	 */
	public dbAdapterDflight() {
	}
	
	public dbAdapterDflight(DataSource ds) {
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
		try{
			DflightSearchCondition searchCondition = (DflightSearchCondition)obj;
			//SQL作成　{ 出発日, 出発時間, 出発地群, 到着地群};
			String value01 = searchCondition.getDays();
			String nowdatetime = dataformat.getNowTimeDate("", "", "", "");
			//もし、value01が、今日ならは、現在時刻の取得
			String value02 = "0000";
			if( value01.equals( nowdatetime.substring(0, 8) ) ){
				value02 = nowdatetime.substring( 8, 12 );
			}
			String value03 = "";
			String value04 = "";
			String[] values = null;
			String sql = "";
			/**
			 * もし在庫数を要求してきたら
			 */
			if( searchCondition.getScatid().length() > 0){
				values = new String[4];
				sql = listsqlscatid;
				value03 = searchCondition.getScatid();
				value04 = "2";
			}else if( searchCondition.getAllot() > 0 ){
				values = new String[5];
				sql = listsqlallot;
				value02 = searchCondition.getFlight_time();
				value03 = "'"+searchCondition.getFrom()+"'";
				value04 = "'"+searchCondition.getTo()+"'";
				values[4] = searchCondition.getAllotment();
			}else{
				values = new String[4];
				sql = listsql;
				value03 = "'"+searchCondition.getFrom()+"','"+searchCondition.getTo()+"'";
				value04 = "'"+searchCondition.getTo()+"', '"+searchCondition.getFrom()+"'";
			}
			values[0] = value01;
			values[1] = value02;
			values[2] = value03; 
			values[3] = value04;
			System.err.println(sql);
			sql = Sqlmaker.strPrintf( sql, modifydateTime( values, nowdatetime ) );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){	
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
				//データ格納 <NodeHandlerを使って作成>
				NodeHandler nodeHandler = new NodeHandler();
				nodeHandler.setSqldata( rows );
				//データ出力
				reStr = nodeHandler.getXmlString();
				}
			} 		
		}catch(Exception ex){				
			ex.printStackTrace();
		}
		System.err.println( reStr );
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
		DetailHandler detailHandler = null;
		try{
			DflightSearchCondition searchCondition = (DflightSearchCondition)obj;
			//SQL作成　{商品コード, 在庫コード};
			String value00 = searchCondition.getProductioncode();
			String value01 = searchCondition.getProduct_id();
			String[] values = { value00, value01 };
			String sql = Sqlmaker.strPrintf( detailsql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			//System.err.print( "JDBC ADAPTER VECOTR NEW DB" );
			if( db.init( this.ds ) ){
				Vector rows = db.dbSelect( sql );
				//System.err.print( "JDBC ADAPTER VECOTR SIZE=" + rows.size() );
				if( rows.size() > 0 ){
					reStr = detailHandler.detailXml( rows );
				}
			} 
			db = null;	
		}catch(Exception ex){				}
		return reStr;
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
	public String confirmOrder( Sub_Order suborder ){
		return "";	
	}
}
