package jp.co.lastminute.maintenance.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

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
public class dbAdapterCategorySelect {
	private DataSource ds;
	/**
	 * コンストラクター
	 */
	public dbAdapterCategorySelect() {
	}
	public dbAdapterCategorySelect(DataSource ds) {
		this.ds = ds;
	}
	public void init(DataSource ds) {
		this.ds = ds;
	}
	/**
	 * リスト用XML出力
	 */
	public Vector getLIst( int pagetype ) {
		Vector revector = new Vector();
		try{
			String[] values = { "" + pagetype + ""};
			String sql = Sqlmaker.strPrintf( selectsql, values );
			
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.ds ) ){	
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acesser = new dbDataAccesser( rows );
				if( rows.size() > 0 ){
					for(int j=0; j<rows.size(); j++){
						Categoryselect cselect = new Categoryselect();
						cselect.setSeqno( acesser.getDatabyInt(j, 0));
						cselect.setPage_type( acesser.getDatabyInt(j, 1) );
						cselect.setS_catid(acesser.getDatabyInt(j, 2));
						cselect.setStartdate( acesser.getDatabyInt(j, 3) );
						cselect.setStopdate( acesser.getDatabyInt(j, 4) );
						cselect.setDescription( acesser.getData(j, 4) );
						cselect.setProd_id_1( acesser.getData(j, 5) );
						cselect.setProd_id_2( acesser.getData(j, 6) );
						cselect.setProd_id_3( acesser.getData(j, 7) );
						cselect.setUpdate_id( acesser.getData(j, 8) );
						cselect.setStop_flg( acesser.getData(j, 9) );
						revector.add( cselect );
						cselect = null;
					}
				}
			} 
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}		
		return revector;
	}
	/**
	 * 追加
	 */
	synchronized public boolean addCategoryselect( Categoryselect cselect ){ 
		try{
			String sql = addCategoryselectStr( cselect );
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
	synchronized public String addCategoryselectStr( Categoryselect cselect ){
		String reStr = "";
		try{
			reStr = "INSERT INTO CATEGORYSELECT("
				  + "SEQNO, "
				  + "PAGE_TYPE, "
				  + "S_CATID, "
				  + "STARTDATE,"
				  + "STOPDATE, "
				  + "DESCRIPTION,"
				  + "PROD_ID_1,"
				  + "PROD_ID_2,"
				  + "PROD_ID_3, "
				  + "UPDATE_ID, "
				  + "STOP_FLG, MAKE_DATE ) VALUES"
				  + "((SELECT MAX(SEQNO) + 1 FROM CATEGORYSELECT),"
				  + cselect.getPage_type() + ", "
				  + cselect.getS_catid() + ", "
				  + cselect.getStartdate() + ", "
				  + cselect.getStopdate() + ", "
				  + "'" + cselect.getDescription() + "',"
				  + "'" + cselect.getProd_id_1() + "', "
				  + "'" + cselect.getProd_id_2() + "', "
				  + "'" + cselect.getProd_id_3() + "', "
				  + "'" + cselect.getUpdate_id() + "', "
				  + "SYSDATE )";			
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 更新
	 */
	synchronized public boolean modifyCategoryselect( Categoryselect cselect ){
		try{
			String sql = modifyCategoryselectStr( cselect );
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
	synchronized public String modifyCategoryselectStr( Categoryselect cselect ){
		String reStr = "";
		try{
			if( cselect.getSeqno() == 0 ){
			reStr = "INSERT INTO CATEGORYSELECT ("
				 + "SEQNO, "
				 + "PAGE_TYPE,"
				 + "S_CATID, "
				 + "STARTDATE, "
				 + "STOPDATE, "
				 + "DESCRIPTION, "
				 + "PROD_ID_1, "
				 + "PROD_ID_2, "
				 + "PROD_ID_3, "
				 + "UPDATE_ID, "
				 + "STOP_FLG, "
				 + "MAKE_DATE) VALUES("
				 + "(SELECT MAX(SEQNO) + 1 FROM CATEGORYSELECT),"
				 + cselect.getPage_type() + ","
				 + cselect.getS_catid() + ","
				 + cselect.getStartdate() + ","
				 + cselect.getStopdate() + ", "
				 +"'" + cselect.getDescription() + "', "
				 +"'" + cselect.getProd_id_1() + "',"
				 +"'" + cselect.getProd_id_2() + "', "
				 +"'" + cselect.getProd_id_3() + "', "
				 +"'" + cselect.getUpdate_id() + "', "
				 +"'" + cselect.getStop_flg() + "', "
				 +"SYSDATE)";
				
			}else{
			reStr = "UPDATE CATEGORYSELECT SET "
				  + "PAGE_TYPE=" + cselect.getPage_type() + ","
				  + "S_CATID=" + cselect.getS_catid() + ","
				  + "STARTDATE=" + cselect.getStartdate() + ","
				  + "STOPDATE=" + cselect.getStopdate() + ", "
				  + "DESCRIPTION='" + cselect.getDescription() + "', "
				  + "PROD_ID_1='" + cselect.getProd_id_1() + "',"
				  + "PROD_ID_2='" + cselect.getProd_id_2() + "', "
				  + "PROD_ID_3='" + cselect.getProd_id_3() + "', "
				  + "UPDATE_ID='" + cselect.getUpdate_id() + "', "
				  + "STOP_FLG='" + cselect.getStop_flg() + "', "
				  + "UP_DATE=SYSDATE WHERE SEQNO=" + cselect.getSeqno();
			}	
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 削除(論理)
	 */
	synchronized public boolean removeCategoryselect( Categoryselect cselect ){
		try{
			String sql = removeCategoryselectStr( cselect );
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
	synchronized public String removeCategoryselectStr( Categoryselect cselect ){
		String reStr = "";
		try{
			reStr = "UPDATE CATEGORYSELECT SET "
				  + "STOP_FLG='" + Property._Stop + "', "
				  + "UP_DATE=SYSDATE WHERE=SEQNO" + cselect.getSeqno();		
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	
	private static final String selectsql ="SELECT "
											+ "SEQNO,"
											+ "PAGE_TYPE,"
											+ "S_CATID,"
											+ "STARTDATE,"
											+ "STOPDATE,"
											+ "DESCRIPTION,"
											+ "PROD_ID_1,"
											+ "PROD_ID_2,"
											+ "PROD_ID_3,"
											+ "UPDATE_ID,"
											+ "STOP_FLG "
											+ "FROM CATEGORYSELECT "
											+ "WHERE PAGE_TYPE=$1 "
											+ "ORDER BY SEQNO";

}
