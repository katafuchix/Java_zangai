package jp.co.lastminute.maintenance.hotel;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.hotel.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Mhfcwphandler extends Commander implements CommandIF{
	/**
	 * 詳細の更新
	 */
	public boolean updateMhfcwp( Mhfcwp mhfcwp ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateMhfcwpStr( mhfcwp );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}

	/**
	 * 詳細の取得
	 */
	public Mhfcwp getDetail( HotelhandlerCondition condition ){
		try{
			Mhfcwp mhfcwp = new Mhfcwp();
			JdbcAdapter db = new JdbcAdapter();
			String sql = getDetailStr( condition ) ;
			if( sql.length() > 0 ){
				if( db.init( this.dataSource ) ){
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					if( rows.size() > 0 ){
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser( rows );
						mhfcwp.supnbr = acceser.getData( 0, 1 );	
						mhfcwp.agt_cd = acceser.getData( 0, 0 );	
						mhfcwp.buildxml = acceser.getData( 0, 2 );						
						return mhfcwp;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * 
	 */
	public String updateMhfcwpStr( Mhfcwp mhfcwp ) throws Exception{
		return "UPDATE MHFCWP "
				+ "SET BUILDXML='" + mhfcwp.buildxml + "' "
				+"WHERE SUPNBR='" + mhfcwp.supnbr +"' AND AGT_CD='" + mhfcwp.agt_cd + "'";	
	}
	/**
	 * 詳細情報の取得
	 */
	public String getDetailStr( HotelhandlerCondition condition ) throws Exception{
		return "SELECT "
				+ "AGT_CD, "
				+ "SUPNBR, "
				+ "BUILDXML "
				+ "FROM MHFCWP "
				+ "WHERE "
				+ " AGT_CD='"+ condition.agt_cd + "' AND "
				+ " SUPNBR='" + condition.supnbr + "'";	
	}
}
