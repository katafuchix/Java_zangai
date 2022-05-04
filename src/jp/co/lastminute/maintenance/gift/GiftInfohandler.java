package jp.co.lastminute.maintenance.gift;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.gift.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class GiftInfohandler extends Commander implements CommandIF{
	//extends Commander implements CommandIF{
	/**
	 * ストップフラグの更新
	 */
	public boolean setStopflg( int product_seq, int status){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE CALLEGEPRODUCT_TBL SET HAVEALT=" + status + " WHERE JAN_CD=" + product_seq;
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
	public boolean updateGiftInfo( GiftInfo gift ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateGiftInfoStr( gift );
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
	public boolean addGiftInfo( GiftInfo gift ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = addGiftInfoStr( gift );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;	
	}
	/**
	 * リストの取得
	 */
	public GiftInfo[] getList( HandlerCondition condition ){
		GiftInfo[] giftInfos = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getListStr( condition ) ;
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					giftInfos = new GiftInfo[ rows.size() ];
					//データ格納 
					dbDataAccesser acceser = new dbDataAccesser( rows );
					for( int i=0; i<rows.size(); i++){
						GiftInfo gift = new GiftInfo();
						gift.jan_cd  	= Integer.parseInt( acceser.getData( i, 0 ) );
						gift.agt_product_cd	= acceser.getData( i, 1 );
						gift.agt_cd	= acceser.getData( i, 2 );
						gift.product_type	= acceser.getDatabyInt( i, 3 );
						gift.product_name	= acceser.getData( i, 4 );
						gift.product_sub_title	= acceser.getData( i, 5 );
						gift.catchcopy	= acceser.getData( i, 6 );
						gift.price_catch	= acceser.getData( i, 7 );
						gift.havealt	= acceser.getDatabyInt( i, 8 );
						gift.price	= acceser.getDatabyInt( i, 9 );
						gift.tax	= acceser.getDatabyInt( i, 10 );
						gift.sending	= acceser.getDatabyInt( i, 11 );
						gift.sending_tax	= acceser.getDatabyInt( i, 12 );
						gift.sending_calc_unit	= acceser.getDatabyInt( i, 13 );
						gift.first_name 	= acceser.getData( i, 14 );
						gift.lastname 	= acceser.getData( i, 15 );
						
						giftInfos[ i ] = gift;
						gift = null;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return giftInfos;
	}
	/**
	 * 詳細の出力
	 */
	public GiftInfo getDetail( HandlerCondition condition ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getDetailStr( condition );
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
				//データ格納 
					dbDataAccesser acceser = new dbDataAccesser( rows );
					GiftInfo gift = new GiftInfo();
					gift.jan_cd  	= Integer.parseInt( acceser.getData( 0, 0 ) );
					gift.agt_product_cd	= acceser.getData( 0, 1 );
					gift.agt_cd	= acceser.getData( 0, 2 );
					gift.product_type	= acceser.getDatabyInt( 0, 3 );
					gift.product_name	= acceser.getData( 0, 4 );
					gift.product_sub_title	= acceser.getData( 0, 5 );
					gift.catchcopy	= acceser.getData( 0, 6 );
					gift.price_catch	= acceser.getData( 0, 7 );
					gift.opening	= acceser.getData( 0, 8 );
					gift.specword	= acceser.getData( 0, 9 );
					gift.moreinfo	= acceser.getData( 0, 10 );
					gift.c_coment	= acceser.getData( 0, 11 );
					gift.body	= acceser.getData( 0, 12 );
					gift.havealt	= acceser.getDatabyInt( 0, 13 );
					gift.price	= acceser.getDatabyInt( 0, 14 );
					gift.tax	= acceser.getDatabyInt( 0, 15 );
					gift.sending	= acceser.getDatabyInt( 0, 16 );
					gift.sending_tax	= acceser.getDatabyInt( 0, 17 );
					gift.sending_calc_unit	= acceser.getDatabyInt( 0, 18 );
					gift.first_name 	= acceser.getData( 0, 19 );
					gift.lastname 	= acceser.getData( 0, 20 );
					gift.mixed_types	= acceser.getData( 0, 21 );
					return gift;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	public String updateGiftInfoStr( GiftInfo gift ) throws Exception{
		return "UPDATE CALLEGEPRODUCT_TBL "
				+"SET AGT_PRODUCT_CD='" + gift.agt_product_cd + "',"
				+"AGT_CD='" + gift.agt_cd + "', "
				+"PRODUCT_TYPE_CD=" + gift.product_type + ","
				+"PRODUCT_NAME='" + gift.product_name + "',"
				+"PRODUCT_SUB_TITLE='" + gift.product_sub_title + "',"
				+"CATCHCOPY='" + gift.catchcopy + "',"
				+"PRICE_CATCH='" + gift.price_catch + "',"
				+"OPEN_DATE='" + gift.opening + "',"
				+"SPECWORD='" + gift.specword + "', "
				+"MOREINFO='" + gift.moreinfo + "', "
				+"C_COMENT='" + gift.c_coment + "', "
				+"BODY='" + gift.body + "', "
				+"HAVEALT=" + gift.havealt + ","
				+"PRICE=" + gift.price + ","
				+"TAX=" + gift.tax + ", "
				+"SENDING=" + gift.sending + ","
				+"SENDING_TAX=" + gift.sending_tax + ", "
				+"SENDING_CALC_UNIT=" + gift.sending_calc_unit + ", "
				+"MIXED_TYPES='" + gift.mixed_types + "', "
				+"UP_DATE=SYSDATE "
				+"WHERE JAN_CD='" + gift.jan_cd  + "'";	
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addGiftInfoStr( GiftInfo gift ) throws Exception {
		return "INSERT INTO CALLEGEPRODUCT_TBL("
				+"JAN_CD,"
				+"AGT_PRODUCT_CD,"
				+"AGT_CD,"
				+"PRODUCT_TYPE_CD,"
				+"PRODUCT_NAME,"
				+"PRODUCT_SUB_TITLE,"
				+"CATCHCOPY,"
				+"PRICE_CATCH,"
				+"OPEN_DATE,"
				+"SPECWORD,"
				+"MOREINFO,"
				+"C_COMENT,"
				+"BODY,"
				+"HAVEALT,"
				+"PRICE,"
				+"TAX,"
				+"SENDING,"
				+"SENDING_TAX,"
				+"SENDING_CALC_UNIT,"
				+"MIXED_TYPES,"
				+"MAKE_DATE)VALUES"
				+"( to_char(jan_cd_seq.nextval) ,"
				+"'" + gift.agt_product_cd + "',"
				+"'" + gift.agt_cd + "', "
				+ gift.product_type + ","
				+"'" + gift.product_name + "',"
				+"'" + gift.product_sub_title + "',"
				+"'" + gift.catchcopy + "',"
				+"'" + gift.price_catch + "',"
				+"'" + gift.opening + "',"
				+"'" + gift.specword + "', "
				+"'" + gift.moreinfo + "', "
				+"'" + gift.c_coment + "', "
				+"'" + gift.body + "', "
				+ gift.havealt + ","
				+ gift.price + ","
				+ gift.tax + ", "
				+ gift.sending + ","
				+ gift.sending_tax + ", "
				+ gift.sending_calc_unit + ","
				+"'" + gift.mixed_types + "',"
				+"SYSDATE)";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr( HandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.getJan_cd().length() > 0 ){
			where += "A.AGT_PRODUCT_CD='" + condition.getJan_cd() + "' AND ";
		}
		if( condition.getAgt_cd().length() > 0 ){
			where += "A.AGT_CD='" + condition.getAgt_cd() + "' AND ";
		}
		if( condition.getProduct_name().length() > 0 ){
			where += "A.PRODUCT_NAME='%" + condition.getProduct_name() + "%' AND ";
		}
		if( condition.getS_catid().length() > 0 ){
			where += "A.JAN_CD IN (SELECT JAN_CD FROM SCATID_MARGE WHERE S_CATID='" + condition.getS_catid() + "') AND ";
		}
		String[] limitStr = {"A.HAVEALT=0 ", "A.HAVEALT=1 ", "HAVEALT<9 "};
		String[] sortStr = {"ORDER BY A.JAN_CD", 
							"ORDER BY A.PRODUCT_NAME", 
							"ORDER BY A.AGT_CD, A.JAN_CD", 
							"ORDER BY A.JAN_CD"}; 
		
		return   "SELECT "
	    		+"A.JAN_CD,"
				+"A.AGT_PRODUCT_CD,"
				+"A.AGT_CD,"
				+"A.PRODUCT_TYPE_CD,"
				+"A.PRODUCT_NAME,"
				+"A.PRODUCT_SUB_TITLE,"
				+"A.CATCHCOPY,"
				+"A.PRICE_CATCH,"
				+"A.HAVEALT,"
				+"A.PRICE,"
				+"A.TAX,"
				+"A.SENDING,"
				+"A.SENDING_TAX,"
				+"A.SENDING_CALC_UNIT,"
				+"B.FIRST_NAME,"
				+"B.LASTNAME "
     			+" FROM CALLEGEPRODUCT_TBL A,"
     			+"AGENT_TBL B "
     			+"WHERE A.AGT_CD=B.AGT_CD AND "
     			+ where
     			+ limitStr[ condition.getLimittype() ]
    			+ sortStr[ condition.getSorttype() ] ;
	}
	/**
	 * 詳細情報の取得
	 */
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.getProduct_seq().length() > 0 ){
			where += "A.JAN_CD='" + condition.getProduct_seq() + "' AND ";
		}
		if( condition.getJan_cd().length() > 0 ){
			where += "A.AGT_PRODUCT_CD='" + condition.getJan_cd() + "' AND ";
		}
		if( condition.getAgt_cd().length() > 0 ){
			where += "A.AGT_CD='" + condition.getAgt_cd() + "' AND ";
		}
		if( condition.getProduct_name().length() > 0 ){
			where += "A.PRODUCT_NAME='%" + condition.getProduct_name() + "%' AND ";
		}
		//String[] limitStr = {"A.STOP_FLG=0 AND ", "A.STOP_FLG=1 AND ", ""};
		String[] sortStr = {"ORDER BY A.JAN_CD, A.AGT_CD", 
							"ORDER BY A.PRODUCT_NAME", 
							"ORDER BY A.JAN_CD, A.AGT_CD", 
							"ORDER BY A.JAN_CD, A.AGT_CD"}; 
		
		return   "SELECT "
    			+"A.JAN_CD,"
				+"A.AGT_PRODUCT_CD,"
				+"A.AGT_CD,"
				+"A.PRODUCT_TYPE_CD,"
				+"A.PRODUCT_NAME,"
				+"A.PRODUCT_SUB_TITLE,"
				+"A.CATCHCOPY,"
				+"A.PRICE_CATCH,"
				+"A.OPEN_DATE,"
				+"A.SPECWORD,"
				+"A.MOREINFO,"
				+"A.C_COMENT,"
				+"A.BODY,"
				+"A.HAVEALT,"
				+"A.PRICE,"
				+"A.TAX,"
				+"A.SENDING,"
				+"A.SENDING_TAX,"
				+"A.SENDING_CALC_UNIT,"
				+"C.FIRST_NAME,"
				+"C.LASTNAME,"
				+"A.MIXED_TYPES "
     			+" FROM CALLEGEPRODUCT_TBL A,"
     			+"AGENT_TBL C "
     			+"WHERE "
     			+ where
     			+"A.AGT_CD=C.AGT_CD "
    			+ sortStr[ condition.getSorttype() ] ;
	}
}
