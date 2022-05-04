package jp.co.lastminute.maintenance.jdbc;

import java.io.*;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.jdbc.*;
import jp.co.lastminute.maintenance.model.*;
import jp.co.lastminute.maintenance.scatid.*;

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
public class dbAdapterScatidList {	
	private DataSource ds;
	/**
	 * コンストラクター
	 */
	public dbAdapterScatidList() {
	}
	public dbAdapterScatidList(DataSource ds) {
		this.ds = ds;
	}
	public void init(DataSource ds) {
		this.ds = ds;
	}
	/**
	 * リスト用XML出力
	 */
	public ScatidList[] getLIst( HandlerCondition condition ) {
		try{
			String[] values = new String[2];
			if(( condition.product_type.length() == 0 )||( condition.getS_catid().length() == 0 )){
				return null;
			}
			values[0] = condition.getS_catid();
			values[1] = condition.getProduct_type();
			String sql = Sqlmaker.strPrintf( selectsql, values );
			System.err.println( sql );
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){	
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acesser = new dbDataAccesser( rows );
				if( rows.size() > 0 ){
					ScatidList[] revector = new ScatidList[rows.size()];
					for(int j=0; j<rows.size(); j++){
						ScatidList scatid = new ScatidList();
						scatid.setSeqno( acesser.getDatabyInt(j, 0));
						scatid.setS_catid( acesser.getDatabyInt(j, 1) );
						scatid.setJan_cd(acesser.getData(j, 2));
						scatid.setTitle( acesser.getData(j, 3) );
						scatid.setCatid( acesser.getDatabyInt(j, 4) );
						scatid.setType_name( acesser.getData(j, 5) );
						scatid.setPriority( acesser.getDatabyInt(j, 6) );
						scatid.setCatch_copy( acesser.getData(j, 7) );
						
						revector[j] = scatid ;
						scatid = null;
					}
					if( db != null ){	db = null;	}
					return revector;
				}
			} 
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}		
		return null;
	}
	/**
	 * 追加
	 */
	synchronized public boolean addScatidList( ScatidList scatid ){ 
		try{
			String sql = addScatidListStr( scatid );
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
	synchronized public String addScatidListStr( ScatidList scatid ){
		String reStr = "";
		try{
			reStr = "INSERT INTO SCATID_MARGE("
				  + "SEQNO, "
				  + "S_CATID, "
				  + "JAN_CD, "
				  + "TITLE,"
				  + "CATID, "
				  + "PRIORITY,"
				  + "MAKE_DATE ) VALUES"
				  + "((SELECT MAX(SEQNO) + 1 FROM SCATID_MARGE),"
				  + scatid.s_catid + ","
				  + "'" + scatid.jan_cd + "',"
				  + "'" + scatid.title+ "',"
				  + scatid.catid + ","
				  + scatid.priority + ","
				  + "SYSDATE)";			
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 更新
	 */
	synchronized public boolean modifyScatidList( ScatidList scatid ){
		try{
			String sql = modifyScatidListStr( scatid );
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
	synchronized public String modifyScatidListStr(ScatidList scatid ){
		String reStr = "";
		try{
			reStr = "UPDATE SCATID_MARGE SET "
				  + "S_CATID=" + scatid.s_catid + ","
				  + "JAN_CD='" + scatid.jan_cd + "',"
				  + "TITLE='" + scatid.title + "',"
				  + "CATID=" + scatid.catid + ", "
				  + "PRIORITY=" + scatid.priority + " "
				  + "WHERE SEQNO=" + scatid.seqno;		
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 削除(物理）
	 */
	synchronized public boolean removeScatidList( String s_catid, String product_type_cd, String jan_cd ){
		if( s_catid.indexOf( "*" ) + s_catid.indexOf( "?" ) +  s_catid.indexOf( "&" ) != -3){
			return false;
		}
		if( product_type_cd.indexOf( "*" ) + product_type_cd.indexOf( "?" ) +  product_type_cd.indexOf( "&" ) != -3){
			return false;
		}
		try{
			String sql = "DELETE SCATID_MARGE "
						+"WHERE SEQNO=(SELECT SEQNO FROM SCATID_MARGE WHERE S_CATID="+s_catid+" AND CATID="+product_type_cd+" AND JAN_CD='"+ jan_cd +"')";
			JdbcAdapter db = new JdbcAdapter();
			System.err.println( sql );
			if( db.init( this.ds ) ){
				return db.removeSQL( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * 削除SQLの生成
	 */
	synchronized public String removeScatidListStr( ScatidList scatid ){
		String reStr = "";
		return reStr;
	}
	
	private static final String selectsql ="SELECT "
											+"A.SEQNO,"
											+"A.S_CATID,"
											+"A.JAN_CD,"
											+"A.TITLE,"
											+"A.CATID,"
											+"B.TYPE_NAME,"
											+"A.PRIORITY,"
											+"C.CATCH_COPY "
											+"FROM "
											+"SCATID_MARGE A,"
											+"TYPE_NAME_MASTER B,"
											+"CATID_SMALL_MASTER C "
											+"WHERE "
											+"A.CATID=B.CATID AND A.S_CATID=C.S_CATID AND "
											+"A.S_CATID=$1 AND A.CATID=$2 ORDER BY A.SEQNO";
}
