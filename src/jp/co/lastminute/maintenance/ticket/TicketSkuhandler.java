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
public class TicketSkuhandler extends Commander {
	/**
	 * 詳細の取得
	 */
	public TicketSku getDetail( HandlerCondition condition  ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getDetailStr( condition ) ;
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
					//データ格納 
					TicketSku sku = new TicketSku();
					dbDataAccesser acceser = new dbDataAccesser( rows );
					sku.sku_id 		= acceser.getDatabyInt( 0, 0 );
					sku.agt_cd 		= acceser.getData( 0, 1 );
					sku.product_seq = acceser.getDatabyInt( 0, 2 );
					sku.salesfrom 	= acceser.getDatabyInt( 0, 3 );
					sku.sku_name 	= acceser.getData( 0, 4 );
					sku.sheatandso 	= acceser.getData( 0, 5 );
					sku.viewstart 	= acceser.getDatabyInt( 0, 6 );
					sku.optionalcomment = acceser.getData( 0, 7 );
					sku.salesto 	= acceser.getDatabyInt( 0, 8 );
					sku.price 		= acceser.getDatabyInt( 0, 9 );
					sku.tax			= acceser.getDatabyInt( 0, 10 );
					sku.sending 	= acceser.getDatabyInt( 0, 11 );
					sku.sending_tax = acceser.getDatabyInt( 0, 12 );
					sku.sending_calc_unit = acceser.getDatabyInt( 0, 13 );
					return sku;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * ＳＫＵリストの取得
	 */
	public TicketSku[] getList( HandlerCondition condition ){
		TicketSku[] skus = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = this.getListStr( condition );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					skus = new TicketSku[ rows.size() ];
					//データ格納 
					for(int i=0; i<rows.size(); i++){
						TicketSku sku = new TicketSku();
						dbDataAccesser acceser = new dbDataAccesser( rows );
						sku.sku_id 		= acceser.getDatabyInt( i, 0 );
						sku.agt_cd 		= acceser.getData( i, 1 );
						sku.product_seq = acceser.getDatabyInt( i, 2 );
						sku.salesfrom 	= acceser.getDatabyInt( i, 3 );
						sku.sku_name 	= acceser.getData( i, 4 );
						sku.viewstart 	= acceser.getDatabyInt( i, 5 );
						sku.salesto 	= acceser.getDatabyInt( i, 6 );
						sku.price 		= acceser.getDatabyInt( i, 7 );
						sku.tax			= acceser.getDatabyInt( i, 8 );
						sku.sending 	= acceser.getDatabyInt( i, 9 );
						sku.sending_tax = acceser.getDatabyInt( i, 10 );
						skus[i] = sku;
						sku = null;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return skus;
	}
	/**
	 * ストップフラグの更新
	 */
	public boolean setStopflg( int sku_id, int status){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE TICKETACTIVTY_SKU SET STOP_FLG=" + status + " WHERE SKU_ID=" + sku_id;
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
	public boolean updateTicketSku( TicketSku sku ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateTicketSkuStr( sku );
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
	public boolean addTicketSku(  TicketSku sku ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = addTicketSkuStr( sku );
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
		if( condition.getAgt_cd().length() > 0 ){
			where += "AGT_CD='" + condition.getAgt_cd() + "' AND ";
		}
		if( condition.getProduct_seq().length() > 0 ){
			where += "PRODUCT_SEQ=" + condition.getProduct_seq() + " AND ";
		}
		if( condition.getSku_id().length() > 0 ){
			where += "SKU_ID=" + condition.getSku_id() + " AND ";
		}
		return "SELECT "
				+"SKU_ID,"		//00
  				+"AGT_CD,"		//01
  				+"PRODUCT_SEQ,"	//02
  				+"SALESFROM,"	//03
  				+"SKU_NAME,"	//04
  				+"VIEWSTART,"	//05
  				+"SALESTO,"		//06
  				+"PRICE,"		//07
  				+"TAX,"			//08
  				+"SENDING,"		//09
  				+"SENDING_TAX "	//10
  				+"FROM TICKETACTIVTY_SKU "
  				+"WHERE " + where + " PRICE>=0"
  				+" ORDER BY SKU_ID";
	}
	/**
	 * 詳細表示
	 */
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		return "SELECT "
				+"SKU_ID,"		//00
  				+"AGT_CD,"		//01
  				+"PRODUCT_SEQ,"	//02
  				+"SALESFROM,"	//03
  				+"SKU_NAME,"	//04
  				+"SHEATANDSO,"	//05
  				+"VIEWSTART,"	//06
  				+"OPTIONALCOMMENT,"	//07
  				+"SALESTO,"		//08
  				+"PRICE,"		//09
  				+"TAX,"			//10
  				+"SENDING,"		//11
  				+"SENDING_TAX,"	//12
  				+"SENDING_CALC_UNIT "	//13
  				+"FROM TICKETACTIVTY_SKU "
  				+"WHERE SKU_ID=" + condition.sku_id;
	}
	/**
	 * インサート文の生成
	 */
	public String addTicketSkuStr( TicketSku sku ){
		return "INSERT INTO TICKETACTIVTY_SKU("
				+"SKU_ID,"
				+"AGT_CD,"
				+"PRODUCT_SEQ,"
				+"SALESFROM,"
				+"SKU_NAME,"
				+"SHEATANDSO,"
				+"VIEWSTART,"
				+"OPTIONALCOMMENT,"
				+"SALESTO,"
				+"PRICE,"
				+"TAX,"
				+"SENDING,"
				+"SENDING_TAX,"
				+"SENDING_CALC_UNIT,"
				+"MAKE_DATE)VALUES("
				+"TICKETACTIVTYSKU_SEQ.NEXTVAL,"
				+"'" + sku.agt_cd + "',"
				+ sku.product_seq +","
				+ sku.salesfrom +","
				+ "'" + sku.sku_name + "',"
				+"'" + sku.sheatandso + "',"
				+ sku.viewstart +","
				+ "'" + sku.optionalcomment +"',"
				+ sku.salesto +","
				+ sku.price +","
				+ sku.tax +","
				+ sku.sending +","
				+ sku.sending_tax +","
				+ sku.sending_calc_unit + ","
				+"SYSDATE)";
	}
	/**
	 * アップデート文の生成
	 */
	public String updateTicketSkuStr( TicketSku sku ){
		return "UPDATE TICKETACTIVTY_SKU SET "
				+"AGT_CD='" + sku.agt_cd + "',"
				+"PRODUCT_SEQ="+ sku.product_seq +","
				+"SALESFROM="+ sku.salesfrom + ","
				+"SKU_NAME='" + sku.sku_name + "',"
				+"SHEATANDSO='" + sku.sheatandso + "',"
				+"VIEWSTART="+ sku.viewstart +","
				+"OPTIONALCOMMENT='" + sku.optionalcomment +"',"
				+"SALESTO=" +sku.salesto +","
				+"PRICE=" + sku.price +","
				+"TAX=" + sku.tax + ","
				+"SENDING=" + sku.sending + ","
				+"SENDING_TAX=" + sku.sending +","
				+"SENDING_CALC_UNIT=" + sku.sending_calc_unit + ", "
				+"UP_DATE=SYSDATE WHERE SKU_ID=" + sku.sku_id;
	}
}
