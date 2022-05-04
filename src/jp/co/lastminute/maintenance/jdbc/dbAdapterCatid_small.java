package jp.co.lastminute.maintenance.jdbc;

import java.io.*;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.ContextProperty;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.jdbc.*;
import jp.co.lastminute.maintenance.model.*;

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
public class dbAdapterCatid_small {	
	private DataSource ds;
	/**
	 * コンストラクター
	 */
	public dbAdapterCatid_small() {
	}
	public dbAdapterCatid_small(DataSource ds) {
		this.ds = ds;
	}
	public void init(DataSource ds) {
		this.ds = ds;
	}
		/**
	 * コンテキストのリロード
	 */
	public boolean reLoloadComtext(){
		String contextname = ContextProperty.SCATIDS_  ;
		
		try{
			FileOutputStream fout = new FileOutputStream(ContextProperty.SCATIDSLocate_);
			ObjectOutput oout = new ObjectOutputStream(fout);		
			oout.writeObject(  getScatids() );
			fout.flush();
			
			fout.close();
			return true;
		} catch (IOException e) {
      		e.printStackTrace();
    	}
    	return false;
	}
	public Hashtable getScatids( ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.ds)) {
				String sql = "SELECT "
							+"S_CATID, "
							+"CATCH_COPY "
							+"FROM CATID_SMALL_MASTER "
							+"ORDER BY S_CATID";
				System.err.println( sql );
				Vector rows = db.dbSelect(sql);
				if (rows.size() > 0) {
					Hashtable localares = new Hashtable();
					//データ格納 
					dbDataAccesser acceser = new dbDataAccesser(rows);
					for (int i = 0; i < rows.size(); i++) {
						localares.put( "" + acceser.getData(i, 0) + "", acceser.getData(i, 1));
					}
					return localares;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * リスト用XML出力
	 */
	public Catid_small[] getLIst( HandlerCondition condition ) {
		try{
			String[] values = new String[1];
			if( condition.product_type.length() == 0 ){
				values[0] = "WHERE STOP_FLG<9";
			}else{
				values[0] = "WHERE CATID=" + condition.product_type + " AND STOP_FLG<9" ;
			}
			String sql = Sqlmaker.strPrintf( selectsql, values );
			System.err.println( sql );
			return getLIst( sql );
		}catch(Exception ex){	ex.printStackTrace();	}		
		return null;
	}
	/**
	 * ポップアップ用ウインドウのリスト取得
	 */
	public Catid_small[] getLIstByJanCd( HandlerCondition condition ) {
		try{
			String[] values = new String[2];
			if(( condition.product_type.length() == 0 )||(condition.jan_cd.length() == 0)) {
				return null;
			}
			values[0] = condition.jan_cd;
			values[1] = condition.product_type;
			
			String sql = Sqlmaker.strPrintf( selectsqlbyjancd, values );
			System.err.println( sql );
			return getLIst( sql );
		}catch(Exception ex){	ex.printStackTrace();	}		
		return null;
	}
	private Catid_small[] getLIst( String sql ) throws Exception{
		JdbcAdapter db = new JdbcAdapter();
		if( db.init( this.ds ) ){	
			Vector rows = db.dbSelect( sql );
			dbDataAccesser acesser = new dbDataAccesser( rows );
			if( rows.size() > 0 ){
				Catid_small[] revector = new Catid_small[rows.size()];
				for(int j=0; j<rows.size(); j++){
					Catid_small scatid = new Catid_small();
					scatid.setS_catid( acesser.getDatabyInt(j, 0));
					scatid.setCatid( acesser.getDatabyInt(j, 1) );
					scatid.setCatch_copy(acesser.getData(j, 2));
					scatid.setDescription( acesser.getData(j, 3) );
					scatid.setUpdate_id( acesser.getData(j, 4) );
					scatid.setMakedate_id( acesser.getData(j, 5) );
					scatid.setStop_flg( acesser.getData(j, 6) );
					scatid.setProduct_type_cd( acesser.getDatabyInt(j, 7) );
					
					revector[j] = scatid ;
					scatid = null;
				}
				if( db != null ){	db = null;	}
				return revector;
			}
		} 
		if( db != null ){	db = null;	}
		return null;	
	}
	/**
	 * 追加
	 */
	synchronized public boolean addSatid_small( Catid_small scatids ){ 
		try{
			String sql = addSatid_smallStr( scatids );
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){				
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 追加用SQL文の生成
	 */
	synchronized public String addSatid_smallStr( Catid_small scatids ){
		String reStr = "";
		try{
			reStr = "INSERT INTO CATID_SMALL_MASTER("
				  + "S_CATID, "
				  + "CATID, "
				  + "CATCH_COPY, "
				  + "DESCRIPTION,"
				  + "MAKE_DATE, "
				  + "STOP_FLG,"
				  + "PRODUCT_TYPE_CD ) VALUES"
				  + "((SELECT MAX(S_CATID) + 1 FROM CATID_SMALL_MASTER),"
				  + scatids.getCatid() + ","
				  + "'" + scatids.getCatch_copy() + "',"
				  + "'" + scatids.getDescription() + "',"
				  + "SYSDATE, "
				  + scatids.getStop_flg() + ", "
				  + scatids.getProduct_type_cd() + ")";			
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 更新
	 */
	synchronized public boolean modifySatid_small( Catid_small scatids ){
		try{
			String sql = modifyScatid_smallStr( scatids );
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){				
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 更新SQL文の取得
	 */
	synchronized public String modifyScatid_smallStr( Catid_small scatids ){
		String reStr = "";
		try{
			reStr = "UPDATE CATID_SMALL_MASTER SET "
				  + "CATID=" + scatids.getCatid() + ","
				  + "CATCH_COPY='" + scatids.getCatch_copy() + "',"
				  + "DESCRIPTION='" + scatids.getDescription() + "',"
				  + "STOP_FLG='" + scatids.getStop_flg() + "', "
				  + "PRODUCT_TYPE_CD=" + scatids.getProduct_type_cd() + ", "
				  + "UP_DATE=SYSDATE WHERE S_CATID=" + scatids.getS_catid();		
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 削除(論理)
	 */
	synchronized public boolean removeSatid_small( Catid_small scatids ){
		try{
			String sql = removeSatid_smallStr( scatids );
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){				
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 削除SQLの生成
	 */
	synchronized public String removeSatid_smallStr( Catid_small scatids ){
		String reStr = "";
		try{
			reStr = "UPDATE CATID_SMALL_MASTER SET "
				  + "STOP_FLG='9', "
				  + "UP_DATE=SYSDATE WHERE S_CATID=" + scatids.getS_catid();		
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	
	private static final String selectsql ="SELECT S_CATID, "	//00
											+ "CATID, "			//01
											+ "CATCH_COPY, "	//02
											+ "NVL(DESCRIPTION,' ') DESCRIPTION, "	//03
											+ "UPDATE_ID, "		//04
											+ "MAKEDATE_ID, "	//05
											+ "STOP_FLG, "		//06
											+ "PRODUCT_TYPE_CD "//07
											+ " FROM CATID_SMALL_MASTER $1 ORDER BY S_CATID";
	private static final String selectsqlbyjancd ="SELECT A.S_CATID, "	//00
											+ "A.CATID, "			//01
											+ "A.CATCH_COPY, "	//02
											+ "NVL(A.DESCRIPTION,' ') DESCRIPTION, "	//03
											+ "A.UPDATE_ID, "		//04
											+ "A.MAKEDATE_ID, "	//05
											+ "A.STOP_FLG, "		//06
											+ "A.PRODUCT_TYPE_CD "//07
											+ " FROM CATID_SMALL_MASTER A,"
											+ "(SELECT S_CATID FROM SCATID_MARGE WHERE JAN_CD='$1') B WHERE "
											+ "A.S_CATID IN B.S_CATID AND "
											+ "A.PRODUCT_TYPE_CD=$2 AND "
											+ "A.STOP_FLG='0' "
											+ "ORDER BY A.S_CATID";
}
