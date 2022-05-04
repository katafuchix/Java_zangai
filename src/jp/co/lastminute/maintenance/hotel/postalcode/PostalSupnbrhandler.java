package jp.co.lastminute.maintenance.hotel.postalcode;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;

import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.Hotel.areas.*;

import jp.co.lastminute.ContextProperty;
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
public class PostalSupnbrhandler extends Commander implements CommandIF {
	/**
	 * コンテキストのリロード
	 */
	public boolean reLoloadComtext(){
		String contextname = ContextProperty.HAREAS_  ;
		
		try{
			FileOutputStream fout = new FileOutputStream(ContextProperty.HAREASLocate_ );
			ObjectOutput oout = new ObjectOutputStream(fout);		
			oout.writeObject(  getAreas() );
			fout.flush();
			
			fout.close();
			return true;
		} catch (IOException e) {
      		e.printStackTrace();
    	}
    	return false;
	}
	public Areas getAreas( ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = "SELECT DISTINCT "
							+"A.STATE_CD,"
							+"A.CITY_CD,"
							+"B.STATE_NAME,"
							+"C.CITY_NAME "
							+"FROM POSTAL_CD_MASTER A,"
							+"STATE_MASTER B,"
							+"CITY_MASTER C,"
							+"(SELECT DISTINCT POSTAL_CD FROM POSTAL_SUPNBR_STORED) D "
							+"WHERE "
							+"A.STATE_CD=B.STATE_CD AND "
							+"A.CITY_CD=C.CITY_CD AND "
							+"A.POSTAL_CD=D.POSTAL_CD "
							+"ORDER BY STATE_CD,CITY_CD";
				System.err.println( sql );
				Vector rows = db.dbSelect(sql);
				if (rows.size() > 0) {
					Areas areas = new Areas();
					//データ格納 
					dbDataAccesser acceser = new dbDataAccesser(rows);
					String state_key = "";
					StateParam state = null;
					for (int i = 0; i < rows.size(); i++) {
						String state_cd =  "" + acceser.getDatabyInt(i, 0) + "";
						String state_name = acceser.getData(i, 2);
						if( !state_key.equals( state_cd ) ){
							if( i != 0 ){
								areas.putState( state );
								System.err.println("State End:" + state.state_cd );
								state = null;
							}
							state = areas.getStateParam( state_cd );
							state.state_cd = state_cd;
							state.state_name = state_name;
							
							state_key = state_cd;							
							
							System.err.println("State Start:" + state.state_cd );
						}
						////////////////////////////////////////////////////
						CityParam cityParam = new CityParam();
						cityParam.state_cd = state_cd;
						cityParam.city_cd = "" + acceser.getDatabyInt(i, 1) + "";
						cityParam.state_name = state_name;
						cityParam.city_name = acceser.getData(i, 3);
						state.addCity( cityParam );
						System.err.println("\tCity state/city:" + cityParam.state_cd + "/" + cityParam.city_cd );
						cityParam = null;
					}
					areas.putState( state );
					System.err.println("State End:" + state.state_cd );
					state = null;
					return areas;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * 詳細の更新
	 */
	public boolean updatePostalSupnbr(PostalSupnbr postal) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = updatePostalSupnbrStr(postal);
				System.err.println(sql);
				return db.dbUpdate(sql);
			}
			if (db != null) {
				db = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 詳細のインサート
	 */
	public boolean addPostalSupnbr(PostalSupnbr postal) {
		try {
			if( checkAddnew( postal ) ){
				JdbcAdapter db = new JdbcAdapter();
				if (db.init(this.dataSource)) {
					String sql = addPostalSupnbrStr(postal);
					System.err.println(sql);
					return db.dbUpdate(sql);
				}
				if (db != null) {
					db = null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * リストの取得
	 */
	public PostalSupnbr[] getList(PostalSupnbr postal) {
		PostalSupnbr[] postalSupnbrs = null;
		try {
			JdbcAdapter db = new JdbcAdapter();
			String sql = getListStr( postal );
			System.err.println(sql);
			if (sql.length() > 0) {
				if (db.init(this.dataSource)) {
					Vector rows = db.dbSelect(sql);
					if (rows.size() > 0) {
						postalSupnbrs = new PostalSupnbr[rows.size()];
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser(rows);
						for (int i = 0; i < rows.size(); i++) {
							PostalSupnbr postalSupnbr = new PostalSupnbr();
							postalSupnbr.state_cd = acceser.getDatabyInt(i, 0);
							postalSupnbr.city_cd = acceser.getDatabyInt(i, 1);							
							postalSupnbr.state_name =  acceser.getData(i, 2);
							postalSupnbr.city_name =  acceser.getData(i, 3);
							postalSupnbrs[i] = postalSupnbr;
							postalSupnbr = null;
						}
						return postalSupnbrs;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return postalSupnbrs;
	}
	/**
	 * リストの取得
	 */
	public PostalSupnbr[] getListBySupNbr(PostalSupnbr postal) {
		PostalSupnbr[] postalSupnbrs = null;
		try {
			JdbcAdapter db = new JdbcAdapter();
			String sql = getListBySupNbrStr( postal );
			System.err.println(sql);
			if (sql.length() > 0) {
				if (db.init(this.dataSource)) {
					Vector rows = db.dbSelect(sql);
					if (rows.size() > 0) {
						postalSupnbrs = new PostalSupnbr[rows.size()];
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser(rows);
						for (int i = 0; i < rows.size(); i++) {
							PostalSupnbr postalSupnbr = new PostalSupnbr();
							postalSupnbr.seq_no =  acceser.getDatabyInt(i, 0);
							postalSupnbr.supnbr =  acceser.getData(i, 1);
							postalSupnbr.agt_cd =  acceser.getData(i, 2);	
							postalSupnbr.postal_cd =  acceser.getData(i, 3);
							postalSupnbr.state_cd = acceser.getDatabyInt(i, 4);
							postalSupnbr.city_cd = acceser.getDatabyInt(i, 5);
							postalSupnbr.state_name =  acceser.getData(i, 6);
							postalSupnbr.city_name =  acceser.getData(i, 7);
							postalSupnbrs[i] = postalSupnbr;
							postalSupnbr = null;
						}
						return postalSupnbrs;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return postalSupnbrs;
	}
	public String updatePostalSupnbrStr( PostalSupnbr postal ) throws Exception {
		return "DELETE POSTAL_SUPNBR_STORED WHERE SEQ_NO=" + postal.seq_no;
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addPostalSupnbrStr( PostalSupnbr postal ) throws Exception {
		return "INSERT INTO POSTAL_SUPNBR_STORED ("
			+ "SEQ_NO,"
			+ "SUPNBR,"
			+ "AGT_CD,"
			+ "POSTAL_CD ) VALUES ((SELECT MAX(SEQ_NO) + 1 FROM POSTAL_SUPNBR_STORED ),"
			+ "'" + postal.supnbr + "', '" + postal.agt_cd + "', '" + postal.postal_cd + "')";
	}
	/**
	 * リスト情報
	 */
	public String getListStr( PostalSupnbr postal ) throws Exception {
		String sql = "SELECT DISTINCT "
 					+"B.STATE_CD,"	// 00
 					+"B.CITY_CD,"	// 01
 					+"C.STATE_NAME," //02
 					+"D.CITY_NAME "	 //03
 					+"FROM "
 					+"POSTAL_CD_MASTER B,"
 					+"STATE_MASTER C,"
 					+"CITY_MASTER D "
 					+"WHERE "
 					+"C.STATE_CD=B.STATE_CD AND "
 					+"D.CITY_CD=B.CITY_CD AND ";
		if( postal.state_cd > 0 ){
			if( postal.city_cd > 0 ){
				return sql + " B.STATE_CD=" + postal.state_cd + " AND B.CITY_CD="+ postal.city_cd;
			}else{
				return sql + " B.STATE_CD="+ postal.state_cd;
			}			
		}
		return "";	
	}
	/**
	 * リスト情報の取得
	 */
	public String getListBySupNbrStr( PostalSupnbr postal ) throws Exception {
		String sql = "SELECT DISTINCT "
					+"A.SEQ_NO,"	//00
					+"A.SUPNBR,"	//01
					+"A.AGT_CD,"	//02
					+"A.POSTAL_CD,"	//03
					+"B.STATE_CD,"	//04
					+"B.CITY_CD,"	//05
					+"C.STATE_NAME,"	//06
					+"D.CITY_NAME "		//07
					+"FROM POSTAL_SUPNBR_STORED A,"
					+"POSTAL_CD_MASTER B,"
					+"STATE_MASTER C,"
					+"CITY_MASTER D "
					+"WHERE"
					+" A.POSTAL_CD=B.POSTAL_CD AND "
					+" C.STATE_CD=B.STATE_CD AND "
					+" D.CITY_CD=B.CITY_CD AND ";
		if( postal.supnbr.length() > 0 ){
			return sql 
					+" A.SUPNBR='"+ postal.supnbr + "' AND "
	 				+" A.AGT_CD='"+ postal.agt_cd + "'";
		}
		if( postal.state_cd > 0 ){
			if( postal.city_cd > 0 ){
				return sql + " B.STATE_CD=" + postal.state_cd + " AND B.CITY_CD="+ postal.city_cd;
			}else{
				return sql + " B.STATE_CD="+ postal.state_cd;
			}			
		}
		return "";
	}

	/**
	 * 追加の際の確認
	 */
	public boolean checkAddnew( PostalSupnbr postal ) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql =
					"SELECT COUNT(*) FROM POSTAL_SUPNBR_STORED WHERE"
					 + " SUPNBR='" + postal.supnbr + "' AND "
					 + " AGT_CD='" + postal.agt_cd + "' AND "
					 + " POSTAL_CD="  + postal.postal_cd ;
				System.err.println(sql);
				Vector rows = db.dbSelect(sql);
				if (rows.size() > 0) {
					dbDataAccesser acceser = new dbDataAccesser(rows);
					if (acceser.getDatabyInt(0, 0) == 0) {
						return true;
					}
				}
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
