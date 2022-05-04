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
public class Madrsphandler extends Commander implements CommandIF{
	/**
	 * 詳細の更新
	 */
	public boolean updateMadrsp( Madrsp madrsp ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql01 = updateMadrspStr( madrsp );
				String sql02 = updatePostal_Supnbr_StoredStr( madrsp );
				System.err.println( sql01 );
				System.err.println( sql02 );
				String[] sql = { sql01, sql02 };
				return db.doBatchUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * 詳細のインサート
	 */
	public boolean addMadrsp( Madrsp madrsp ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql01 = addMadrspStr( madrsp );
				String sql02 = addPostal_Supnbr_StoredStr( madrsp );
				System.err.println( sql01 );
				System.err.println( sql02 );
				String[] sql = { sql01, sql02 };
				return db.doBatchUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;	
	}
	/**
	 * 詳細の取得
	 */
	public Madrsp getDetail( HotelhandlerCondition condition ){
		try{
			Madrsp madrsp = new Madrsp();
			JdbcAdapter db = new JdbcAdapter();
			String sql = getDetailStr( condition ) ;
			if( sql.length() > 0 ){
				if( db.init( this.dataSource ) ){
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					if( rows.size() > 0 ){
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser( rows );
						madrsp.supnbr = acceser.getData( 0, 0 );
						madrsp.agt_cd = acceser.getData( 0, 1 );
						madrsp.supnm1 = acceser.getData( 0, 2 );
						madrsp.jpnnam = acceser.getData( 0, 3 );
						madrsp.supad1 = acceser.getData( 0, 4 );
						madrsp.supad2 = acceser.getData( 0, 5 );
						madrsp.supad3 = acceser.getData( 0, 6 );
						madrsp.promise = acceser.getData( 0, 7 );
						madrsp.promiseuri = acceser.getData( 0, 8 );
						madrsp.suptel = acceser.getData( 0, 9 );
						madrsp.supfax = acceser.getData( 0, 10 );
						madrsp.jpntxt = acceser.getData( 0, 11 );
						madrsp.agt_name = acceser.getData( 0, 12 );
						madrsp.city_name = acceser.getData( 0, 13 );
						madrsp.state_name = acceser.getData( 0, 14 );
												
						return madrsp;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * リストの取得
	 */
	public Madrsp[] getList( HotelhandlerCondition condition ){
		Madrsp[] madrsps = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			String sql = getListStr( condition ) ;
			if( sql.length() > 0 ){
				if( db.init( this.dataSource ) ){
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					if( rows.size() > 0 ){
						madrsps = new Madrsp[ rows.size() ];
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser( rows );
						for( int i=0; i<rows.size(); i++){
							Madrsp madrsp = new Madrsp();
							madrsp.supnbr = acceser.getData( i, 0 );
							madrsp.agt_cd = acceser.getData( i, 1 );
							madrsp.supnm1 = acceser.getData( i, 2 );
							madrsp.jpnnam = acceser.getData( i, 3 );
							madrsp.agt_name = acceser.getData( i, 4 );
							madrsp.city_name = acceser.getData( i, 5 );
							madrsp.state_name = acceser.getData( i, 6 );
							madrsps[ i ] = madrsp;
							madrsp = null;
						}
						return madrsps;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return madrsps;
	}
	/**
	 * 
	 */
	public String updateMadrspStr( Madrsp madrsp ) throws Exception{
		return "UPDATE MADRSP "
				+"SET SUPNM1='" + madrsp.supnbr + "',"
				+ "JPNNAM='" + madrsp.jpnnam + "',"
				+ "SUPAD1='" + madrsp.supad1 + "',"
				+ "SUPAD2='" + madrsp.supad2 + "',"
				+ "SUPAD3='" + madrsp.supad3 + "',"
				+ "PROMISE='" + madrsp.promise + "',"
				+ "PROMISEURI='" + madrsp.promiseuri + "',"
				+ "SUPTEL='" + madrsp.suptel + "',"
				+ "SUPFAX='" + madrsp.supfax + "',"
				+ "JPNTXT='" + madrsp.jpntxt + "' "
				+"WHERE SUPNBR='" + madrsp.supnbr +"' AND AGT_CD='" + madrsp.agt_cd + "'";	
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addMadrspStr( Madrsp madrsp ) throws Exception {
		return "INSERT MADRSP ("
				+ "SUPNBR,"
				+ "AGT_CD,"
				+ "SUPNM1,"
				+ "JPNNAM,"
				+ "SUPAD1,"
				+ "SUPAD2,"
				+ "PROMISE,"
				+ "PROMISEURI, "
				+ "SUPTEL,"
				+ "SUPFAX, "
				+ "JPNTXT) VALUES ( "
				+ "'" + madrsp.supnbr + "', "
				+ "'" + madrsp.agt_cd + "', "
				+ "'" + madrsp.supnbr + "',"
				+ "'" + madrsp.jpnnam + "',"
				+ "'" + madrsp.supad1 + "',"
				+ "'" + madrsp.supad2 + "',"
				+ "'" + madrsp.supad3 + "',"
				+ "'" + madrsp.promise + "',"
				+ "'" + madrsp.promiseuri + "',"
				+ "'" + madrsp.suptel + "',"
				+ "'" + madrsp.supfax + "',"
				+ "'" + madrsp.jpntxt + "')";
	}
	public String addPostal_Supnbr_StoredStr( Madrsp madrsp ){
		return "INSERT INTO POSTAL_SUPNBR_STORED ("
				+ "SEQ_NO,"
				+ "SUPNBR,"
				+ "AGT_CD, "
				+ "POSTAL_CD)VALUES("
				+ "(SELECT MAX(SEQ_NO) + 1 FROM POSTAL_SUPNBR_STORED),"
				+ "'" + madrsp.supnbr + "', "
				+ "'" + madrsp.agt_cd + "', "
				+ madrsp.supad1 + ")";
	}
	public String updatePostal_Supnbr_StoredStr( Madrsp madrsp ){
		return "UPDATE POSTAL_SUPNBR_STORED SET POSTAL_CD=" + madrsp.supad1
				+ " WHERE SUPNBR='" + madrsp.supnbr + "' AND AGT_CD='" + madrsp.agt_cd + "'";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getDetailStr( HotelhandlerCondition condition ) throws Exception{
		return "SELECT "
				+ "A.SUPNBR, "
				+ "A.AGT_CD,"
				+ "A.SUPNM1,"
				+ "A.JPNNAM,"
				+ "A.SUPAD1, "
				+ "A.SUPAD2, "
				+ "A.SUPAD3, "
				+ "A.PROMISE, "
				+ "A.PROMISEURI, "
				+ "A.SUPTEL, "
				+ "A.SUPFAX, "
				+ "A.JPNTXT, "
				+ "B.FIRST_NAME,"
				+ "F.CITY_NAME,"
				+ "E.STATE_NAME"
				+ " FROM MADRSP A, AGENT_TBL B,"
				+ "STATE_MASTER E, CITY_MASTER F, "
				+ "POSTAL_CD_MASTER G, POSTAL_SUPNBR_STORED X "
				+ "WHERE "
				+ " A.AGT_CD=B.AGT_CD AND "
				+ " G.STATE_CD=E.STATE_CD AND "
				+ " G.CITY_CD=F.CITY_CD AND " 
				+ " G.POSTAL_CD=X.POSTAL_CD AND "
				+ " A.SUPNBR=X.SUPNBR(+) AND "
				+ " A.AGT_CD=X.AGT_CD(+) AND "
				+ " A.AGT_CD='"+ condition.agt_cd + "' AND "
				+ " A.SUPNBR='" + condition.supnbr + "'";	
	}
	/**
	 * りすと情報の取得
	 */
	public String getListStr( HotelhandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.supnbr.length() > 0 ){
			where += " AND A.SUPNBR='" + condition.supnbr + "'";
		}else{
			if( condition.local_area_cd.length() > 0 ){
				where += " AND A.SUPNBR IN "
					+ "(SELECT SUPNBR FROM LOCAL_AREA_SUPNBR_STORED "
					+ "WHERE LOCAL_AREA_CODE=" + condition.local_area_cd + ")";
			}else{
				if( condition.state_cd.length() > 0 ){
					where += " AND A.SUPNBR IN (SELECT A.SUPNBR FROM POSTAL_SUPNBR_STORED A, "
						  + "POSTAL_CD_MASTER B WHERE A.POSTAL_CD=B.POSTAL_CD AND B.STATE_CD="
						  + condition.state_cd ;
					if( condition.city_cd.length() > 0 ){
						where += " AND B.CITY_CD=" + condition.city_cd;
					}
					where += ")";
				}
			}
		}
		if( where.length() == 0){	return "";	}	
		return   "SELECT "
				+ "A.SUPNBR, "
				+ "A.AGT_CD,"
				+ "A.SUPNM1,"
				+ "A.JPNNAM,"
				+ "B.FIRST_NAME,"
				+ "F.CITY_NAME,"
				+ "E.STATE_NAME"
				+ " FROM MADRSP A, AGENT_TBL B,"
				+ "STATE_MASTER E, CITY_MASTER F, "
				+ "POSTAL_CD_MASTER G, POSTAL_SUPNBR_STORED X "
				+ "WHERE "
				+ " A.AGT_CD=B.AGT_CD AND "
				+ " G.STATE_CD=E.STATE_CD AND "
				+ " G.CITY_CD=F.CITY_CD AND " 
				+ " G.POSTAL_CD=X.POSTAL_CD AND "
				+ " A.SUPNBR=X.SUPNBR(+) AND "
				+ " A.AGT_CD=X.AGT_CD(+) AND "
				+ " A.AGT_CD='"+ condition.agt_cd + "'"
				+ where 
				+ " ORDER BY A.JPNNAM, A.AGT_CD";
	}
	
	/**
	 * 追加の際の確認
	 */
	public boolean checkAddnew( Madrsp madrsp ){
		try{	
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = "SELECT COUNT(*) FROM MADRSP WHERE SUPNBR='"
						  + madrsp.supnbr + "' AND AGT_CD='" 
						  + madrsp.agt_cd + "'";
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					dbDataAccesser acceser = new dbDataAccesser( rows );
					if(acceser.getDatabyInt(0, 0) == 0){
						return true;	
					}
				}
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
