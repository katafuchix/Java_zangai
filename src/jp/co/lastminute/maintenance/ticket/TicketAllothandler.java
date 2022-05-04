package jp.co.lastminute.maintenance.ticket;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.ticket.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TicketAllothandler extends Commander{
	/**
	 * 詳細の取得
	 */
	public TicketAllot getDetail( HandlerCondition condition  ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getDetailStr( condition );
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
					//データ格納 
					TicketAllot allot = new TicketAllot();
					dbDataAccesser acceser = new dbDataAccesser( rows );
					allot.allot_seq 	= acceser.getDatabyInt( 0, 0 );
					allot.sku_id 		= acceser.getDatabyInt( 0, 1 );
					allot.allotcounter 	= acceser.getDatabyInt( 0, 2 );
					allot.allotdate 	= acceser.getDatabyInt( 0, 3 );
					allot.allottime		= acceser.getDatabyInt( 0, 4 );
					allot.stop_flg  	= acceser.getDatabyInt( 0, 5 );
					return allot;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * ＳＫＵリストの取得
	 */
	public TicketAllot[] getList( HandlerCondition condition ){
		TicketAllot[] allots = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getListStr( condition );
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					allots = new TicketAllot[ rows.size() ];
					//データ格納 
					for(int i=0; i<rows.size(); i++){
						TicketAllot allot = new TicketAllot();
						dbDataAccesser acceser = new dbDataAccesser( rows );
						allot.allot_seq 	= acceser.getDatabyInt( i, 0 );
						allot.sku_id 		= acceser.getDatabyInt( i, 1 );
						allot.allotcounter 	= acceser.getDatabyInt( i, 2 );
						allot.allotdate 	= acceser.getDatabyInt( i, 3 );
						allot.allottime		= acceser.getDatabyInt( i, 4 );
						allot.stop_flg  	= acceser.getDatabyInt( i, 5 );
						allot.product_name	= acceser.getData( i, 6 );
						allot.sku_name		= acceser.getData( i, 7 );
						
						allots[i] = allot;
						allot = null;
					}
					return allots;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return allots;
	}
	/**
	 * ストップフラグの更新
	 */
	public boolean setStopflg( int sku_id, int status){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE ALLOTEMENT_OF_TICKETSACTIVITY SET STOP_FLG=" + status + " WHERE SKU_ID=" + sku_id;
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
		
	}
	/**
	 * 詳細の更新
	 */
	public boolean updateTicketAllot( TicketAllot allot ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateTicketAllotStr( allot );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * 詳細のインサート
	 */
	public boolean addTicketAllot(  TicketAllot allot ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = addTicketAllotStr( allot );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;	
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr( HandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.getSku_id().length() > 0 ){
			where += "A.SKU_ID=" + condition.getSku_id() + " AND ";
		}
		if( condition.getAllotfrom().length() > 0){
			where += "A.ALLOTDATE=>" + condition.getAllotfrom() + " AND ";
		}
		if( condition.getAllotto().length() > 0){
			where += "A.ALLOTDATE=<" + condition.getAllotto() + " AND ";
		}
		if( condition.getAllot_seq().length() > 0){
			where += "A.ALLOT_SEQ=" + condition.getAllot_seq() + " AND ";
		}
		String[] limitStr = {"A.STOP_FLG=0", "A.STOP_FLG=1", "A.STOP_FLG<9"};
		return "SELECT "
				+"A.ALLOT_SEQ,"	//00
  				+"A.SKU_ID,"		//01
  				+"A.ALLOTCOUNTER,"//02
  				+"A.ALLOTDATE,"	//03
  				+"A.ALLOTTIME,"	//04
  				+"A.STOP_FLG,  "	//05
  				+"B.PRODUCT_NAME, "//06
  				+"B.SKU_NAME "//07
  				+"FROM ALLOTEMENT_OF_TICKETSACTIVITY A, "
  				+"(SELECT D.SKU_ID,C.PRODUCT_NAME,D.SKU_NAME FROM TICKETSACTIVTY C, TICKETACTIVTY_SKU D"
  				+ " WHERE C.PRODUCT_SEQ=D.PRODUCT_SEQ ) B "
  				+"WHERE A.SKU_ID=B.SKU_ID AND " + where 
  				+ limitStr[ condition.getLimittype() ]
  				+" ORDER BY A.ALLOT_SEQ";
	}
	/**
	 * 詳細表示
	 */
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.getSku_id().length() > 0 ){
			where += "A.SKU_ID=" + condition.getSku_id() + " AND ";
		}
		if( condition.getAllotfrom().length() > 0){
			where += "A.ALLOTDATE=>" + condition.getAllotfrom() + " AND ";
		}
		if( condition.getAllotto().length() > 0){
			where += "A.ALLOTDATE=<" + condition.getAllotto() + " AND ";
		}
		if( condition.getAllot_seq().length() > 0){
			where += "A.ALLOT_SEQ=" + condition.getAllot_seq() + " AND ";
		}
		String[] limitStr = {"A.STOP_FLG=0", "A.STOP_FLG=1", "A.STOP_FLG<9"};
		return "SELECT "
				+"A.ALLOT_SEQ,"	//00
  				+"A.SKU_ID,"		//01
  				+"A.ALLOTCOUNTER,"//02
  				+"A.ALLOTDATE,"	//03
  				+"A.ALLOTTIME,"	//04
  				+"A.STOP_FLG,  "	//05
  				+"B.PRODUCT_NAME, "//06
  				+"B.SKU_NAME "//07
  				+"FROM ALLOTEMENT_OF_TICKETSACTIVITY A, "
  				+"(SELECT D.SKU_ID,C.PRODUCT_NAME,D.SKU_NAME FROM TICKETSACTIVTY C, TICKETACTIVTY_SKU D"
  				+ " WHERE C.PRODUCT_SEQ=D.PRODUCT_SEQ ) B "
  				+"WHERE A.SKU_ID=B.SKU_ID AND " + where 
  				+ limitStr[ condition.getLimittype() ]
  				+" ORDER BY A.ALLOT_SEQ";
	}
	/**
	 * インサート文の生成
	 */
	public String addTicketAllotStr( TicketAllot allot ){
		return "INSERT INTO ALLOTEMENT_OF_TICKETSACTIVITY("
				+"ALLOT_SEQ,"
				+"SKU_ID,"
				+"ALLOTCOUNTER,"
				+"ALLOTDATE,"
				+"ALLOTTIME,"
				+"STOP_FLG,"
				+"MAKE_DATE)VALUES("
				+"TICKETACTIVTYALLOT_SEQ.NEXTVAL,"
				+ allot.sku_id + ","
				+ allot.allotcounter +","
				+ allot.allotdate + ","
				+ allot.allottime + ","
				+ allot.stop_flg + ", "
				+"SYSDATE)";
	}
	/**
	 * アップデート文の生成
	 */
	public String updateTicketAllotStr( TicketAllot allot ){
		return "UPDATE ALLOTEMENT_OF_TICKETSACTIVITY SET "
				+"SKU_ID="+ allot.sku_id +","
				+"ALLOTCOUNTER="+ allot.allotcounter + ","
				+"ALLOTDATE=" + allot.allotdate + ","
				+"ALLOTTIME=" + allot.allottime + ","
				+"STOP_FLG="+ allot.stop_flg +","
				+"UP_DATE=SYSDATE WHERE ALLOT_SEQ=" + allot.allot_seq;
	}
}
