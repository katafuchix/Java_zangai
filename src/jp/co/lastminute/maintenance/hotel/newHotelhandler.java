package jp.co.lastminute.maintenance.hotel;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;
import javax.sql.DataSource;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.hotel.model.*;

import jp.co.yobrain.util.form.*;
import jp.co.lastminute.maintenance.util.*;

import jp.co.yobrain.util.rpc.PostString;
import jp.co.yobrain.util.rpc.SendClient;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class newHotelhandler extends Models implements Serializable{
	public String supnbr = "";
	public String agt_cd = "";
	public String postal_cd = "";
	public String url = "";
	public int flag = 0;
	/////
	public String state_name = "";
	public String city_name = "";
	public String csvdata = "";
	//
	public String city_cd = "";
	public String state_cd = "";
	//
	private DataSource dataSource;
	//
	private String err_comm = "";
	
	public newHotelhandler( DataSource dataSource ){
		this.dataSource = dataSource;
		error_flg = new int[ 6 ];
	}
	/**
	 * 検索条件をセットする
	 */
	public newHotelhandler setCondition( HttpServletRequest req ){
		this.err_comm = "";
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.postal_cd = checkStr( req, "postal_cd", 2, 1, true );
		this.url = checkStr( req, "url", 3, 1, true );
		if( isError() ){
			if( !getPostalFrom()){
				err_comm = "地方が選択できませんでした";
				error_flg[4] = 1;
			}
		}
		if( isError() ){
			if( !getCsvData() ){
				err_comm = "データが取得できませんでした";
				error_flg[5] = 1;	
			}
		}
		return this;
	}
	//public boolean isError(){
	//	if( error_sum > 0 ){
	//		return false;	
	//	}
	//	return true;
	//}
	/**
	 * コネクションを行いデータを取得
	 */
	public boolean getCsvData(){
		try{
			String url = this.url;
			Vector vc = new Vector();
			PostString ps01 = new PostString( "supnbr", this.supnbr );
			PostString ps02 = new PostString( "agt_cd", this.agt_cd );
			vc.add( ps01 );
			vc.add( ps02 );
			SendClient sendclient = new SendClient();
			this.csvdata = sendclient.sendText( url, vc );; //転送データを取得
			if( this.csvdata.length() > 0 ){	//もし、データが無かったら、エラー
				return true;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * データを格納する
	 */
	public boolean setdata(){
		if( checkNewSup() ){
			if( !setAreaStr() ){
				return false;
			}
			if( !addDB() ){
				return false;
			}
		}
		return updateDB();
	}
	/**
	 * エリアをセットする
	 */
	public boolean setAreaStr(){
		try{
			String sql = "SELECT SEQ_NO "
						+ "FROM POSTAL_SUPNBR_STORED "
						+ "WHERE SUPNBR='" + this.supnbr + "' AND AGT_CD='" + this.agt_cd + "'";
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				Vector vc = db.dbSelect( sql );
				if( vc.size() > 0){
					return db.dbInsert( getInsertStr() );
				}else{
					return db.dbUpdate( getUpdateStr() );
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return false;		
	}
	private String getInsertStr(){
		return "INSERT INTO POSTAL_SUPNBR_STORED (SEQ_NO, AGT_CD, SUPNBR, POSTAL_CD)"
				+ " VALUES ((SELECT MAX(SEQ_NO)+1 FROM POSTAL_SUPNBR_STORED),"
				+ "'" + this.agt_cd + "',"
				+ "'" + this.supnbr + "',"
				+ "'" + this.postal_cd + "')";
	}
	private String getUpdateStr(){
		return "UPDATE POSTAL_SUPNBR_STORED SET POSTAL_CD='" + this.postal_cd + "' "
			   +"WHERE AGT_CD='" + this.agt_cd + "' AND "
			   +"SUPNBR='" + this.supnbr + "'";			   
	}
	/**
	 * 郵便番号から設備
	 */
	public boolean getPostalFrom(){
		try{
			String sql = "SELECT "
						+ "B.STATE_NAME,"
						+ "C.CITY_NAME, "
						+ "A.STATE_CD, "
						+ "A.CITY_CD "
						+ "FROM "
						+ "POSTAL_CD_MASTER A,"
						+ "STATE_MASTER B,"
						+ "CITY_MASTER C "
						+ "WHERE "
						+ "A.STATE_CD=B.STATE_CD AND "
						+ "A.CITY_CD=C.CITY_CD AND "
						+ "A.POSTAL_CD=" + this.postal_cd;
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				Vector vc = db.dbSelect( sql );
				if( vc.size() > 0){
					dbDataAccesser accesser = new dbDataAccesser( vc );
					this.state_name = accesser.getData(0, 0);
					this.city_name = accesser.getData(0, 1);
					this.state_cd = accesser.getData(0, 2);
					this.city_cd = accesser.getData(0, 3);
					return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return false;	
	}
	/**
	 * 新規施設か否かをチェック
	 */
	public boolean checkNewSup(){
		try{
			String sql = "SELECT "
						+ "SUPNBR "
						+ "FROM MADRSP "
						+ "WHERE "
						+ "SUPNBR='" + this.supnbr + "' AND AGT_CD='" + this.agt_cd + "'";
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				Vector vc = db.dbSelect( sql );
				if( vc.size() > 0){
					return true;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		return false;	
	}
	/**
	 * 施設情報データベースを追加
	 */
	public boolean addDB(){
		try{
			String[] sql = new String[5];
			String apram = "(SUPNBR, AGT_CD, CTRNBR, CTRNBR_1)";
			String valu = "('"+this.supnbr+"','"+this.agt_cd+"',"+this.state_cd +","+this.city_cd+")";
			sql[0] = "INSERT INTO MADRSP "+ apram + " VALUES "+ valu;
			sql[0] = "INSERT INTO MHINFP "+ apram + " VALUES "+ valu;
			sql[0] = "INSERT INTO MHFCWP "+ apram + " VALUES "+ valu;
			sql[0] = "INSERT INTO MHRMSP "+ apram + " VALUES "+ valu;
			sql[0] = "INSERT INTO MHTLCP "
					+"(SUPNBR, AGT_CD, CTRNBR, CTRNBR_1, BATH1, MEAL1, MEAL2, MEAL3) VALUES "
					+"('"+this.supnbr+"','"+this.agt_cd+"',"+this.state_cd +","+this.city_cd+",0,0,0,0)";
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				return  db.doBatchUpdate( sql );
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * 施設情報データベースを更新
	 */
	public boolean updateDB(){
		try{
			Madrsp madrsp = new Madrsp();
			madrsp.parseString( this.csvdata );
			madrsp.makejpntxt();
			madrsp.agt_cd = this.agt_cd;
			madrsp.supnbr = this.supnbr;
			Madrsphandler madrsphandler = new Madrsphandler();
			madrsphandler.setDataSource( this.dataSource );
			//
			Mhinfp mhinfp = new Mhinfp();
			mhinfp.parseString( this.csvdata );
			mhinfp.makecheckinxml();
			mhinfp.makecheckinxml();
			mhinfp.agt_cd = this.agt_cd;
			mhinfp.supnbr = this.supnbr;
			Mhinfphandler mhinfphandler = new Mhinfphandler();
			mhinfphandler.setDataSource( this.dataSource );
			//
			Mhfcwp mhfcwp = new Mhfcwp();
			mhfcwp.parseString( this.csvdata );
			mhfcwp.makeoutdoor_pool();
			mhfcwp.makeindoor_pool();
			mhfcwp.makegoods();
			mhfcwp.makeshower();
			mhfcwp.makerefrigerator();
			mhfcwp.makeBuldXml();
			mhfcwp.agt_cd = this.agt_cd;
			mhfcwp.supnbr = this.supnbr;
			Mhfcwphandler mhfcwphandler = new Mhfcwphandler();
			mhfcwphandler.setDataSource( this.dataSource );
			//
			Mhtlcp mhtlcp = new Mhtlcp();
			mhtlcp.parseString( this.csvdata );
			mhtlcp.agt_cd = this.agt_cd;
			mhtlcp.supnbr = this.supnbr;
			Mhtlcphandler mhtlcphandler = new Mhtlcphandler();
			mhtlcphandler.setDataSource(  this.dataSource );
			if( madrsphandler.updateMadrsp( madrsp ) 
				&&( mhinfphandler.updateMhinfp( mhinfp ))
				&&( mhfcwphandler.updateMhfcwp( mhfcwp ))
				&&( mhtlcphandler.updateMhtlcp( mhtlcp ))){
					return true;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * Returns the err_comm.
	 * @return String
	 */
	public String getErr_comm() {
		return err_comm;
	}

	/**
	 * Sets the err_comm.
	 * @param err_comm The err_comm to set
	 */
	public void setErr_comm(String err_comm) {
		this.err_comm = err_comm;
	}

}
