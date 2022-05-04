package jp.co.lastminute.Entertainment.jdbc;

import jp.co.lastminute.*;
import jp.co.lastminute.Entertainment.*;
import jp.co.lastminute.Entertainment.detail.*;
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
public class dbAdapterEntertainment implements dbProductAdapter, Serializable {

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
	public dbAdapterEntertainment() {
	}
	
	public dbAdapterEntertainment(DataSource ds) {
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
			EntertainmentSearchCondition searchCondition = (EntertainmentSearchCondition)obj;
			String value00 = searchCondition.getProduct_id();
			String[] values = { value00 };
			String sql = Sqlmaker.strPrintf( detailsql, values );
			//クエリー発行
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				Vector rows = db.dbSelect( sql );
				System.err.print( "JDBC ADAPTER VECOTR SIZE=" + rows.size() );
				if( rows.size() > 0 ){
					DetailHandler detailHandler = new DetailHandler();
					reStr = detailHandler.detailXml( rows );
				}
			} 		
		}catch(Exception ex){	ex.printStackTrace();	}
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
				db.dbUpdate( sql );
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
