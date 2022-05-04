package jp.co.lastminute.maintenance.hotel.htlcat;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.hotel.model.*;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HtlcatSupNbrhandler extends Commander implements CommandIF{
	 /**
	 * コンテキストのリロード
	 */
	public boolean reLoloadComtext(){
		String contextname = ContextProperty.HTLCATS_  ;
		
		try{
			FileOutputStream fout = new FileOutputStream(ContextProperty.HTLCATSLocate_ );
			ObjectOutput oout = new ObjectOutputStream(fout);		
			oout.writeObject(  getLoalareas() );
			fout.flush();
			
			fout.close();
			return true;
		} catch (IOException e) {
      		e.printStackTrace();
    	}
    	return false;
	}
	public Hashtable getLoalareas( ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = "SELECT "
						  	+"HTLCAT,"
						  	+"HTLCAR_NAME "
						  	+"FROM HTLCAT_MASTER "
						  	+"ORDER BY HTLCAT";
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
	 * 削除
	 */
	public boolean removeHtlcatSubNbr(HtlcatSupNbr area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = "DELETE HTLCAT_SUPNBR WHERE SEQ_NO=" + area.seq_no;
				System.err.println(sql);
				return db.removeSQL(sql);
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
	 * 詳細の更新
	 */
	public boolean updateHtlcatSubNbr(HtlcatSupNbr area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = updateHtlcatSubNbrStr(area);
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
	public boolean addHtlcatSubNbr(HtlcatSupNbr area) {
		try {
			if( checkAddnew( area ) ){
				JdbcAdapter db = new JdbcAdapter();
				if (db.init(this.dataSource)) {
					String sql = addHtlcatSubNbrStr(area);
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
	public HtlcatSupNbr[] getList(HtlcatSupNbr area) {
		HtlcatSupNbr[] htlcatSupNbrs = null;
		try {
			JdbcAdapter db = new JdbcAdapter();
			String sql = getListStr(area);
			System.err.println(sql);
			if (sql.length() > 0) {
				if (db.init(this.dataSource)) {
					Vector rows = db.dbSelect(sql);
					if (rows.size() > 0) {
						htlcatSupNbrs = new HtlcatSupNbr[rows.size()];
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser(rows);
						for (int i = 0; i < rows.size(); i++) {
							HtlcatSupNbr htlcatSupNbr = new HtlcatSupNbr();
							htlcatSupNbr.seq_no = acceser.getDatabyInt(i, 0);
							htlcatSupNbr.htlcat = acceser.getDatabyInt(i, 1);
							htlcatSupNbr.supnbr = acceser.getData(i, 2);
							htlcatSupNbr.agt_cd = acceser.getData(i, 3);
							htlcatSupNbr.htlcat_name = acceser.getData(i, 4);
							htlcatSupNbrs[i] = htlcatSupNbr;
							htlcatSupNbr = null;
						}
						return htlcatSupNbrs;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return htlcatSupNbrs;
	}
	public String updateHtlcatSubNbrStr(HtlcatSupNbr area) throws Exception {
		return "UPDATE HTLCAT_SUPNBR "
			+ "SET "
			+ "HTLCAT=" + area.htlcat + " "
			+ "WHERE SEQ_NO=" + area.seq_no;
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addHtlcatSubNbrStr(HtlcatSupNbr area) throws Exception {
		return "INSERT INTO HTLCAT_SUPNBR ("
			+ "SEQ_NO,"
			+ "HTLCAT,"
			+ "SUPNBR,"
			+ "AGT_CD) VALUES ( (SELECT MAX(SEQ_NO) + 1 FROM HTLCAT_SUPNBR),"
			+ area.htlcat + ","
			+ "'"+ area.supnbr	+ "',"
			+ "'" + area.agt_cd	+ "')";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr(HtlcatSupNbr area) throws Exception {
		return "SELECT "
 				+"A.SEQ_NO,"	//00
 				+"A.HTLCAT,"	//01
 				+"A.SUPNBR,"	//02
 				+"A.AGT_CD,"		//03
 				+"B.HTLCAR_NAME "	//04
 				+"FROM "
				+"HTLCAT_SUPNBR A, HTLCAT_MASTER B "
				+"WHERE A.HTLCAT=B.HTLCAT AND "
				+"A.SUPNBR='" + area.supnbr + "' AND A.AGT_CD='" + area.agt_cd + "' ORDER BY A.HTLCAT";
	}

	/**
	 * 追加の際の確認
	 */
	public boolean checkAddnew(HtlcatSupNbr area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql =
					"SELECT COUNT(*) FROM HTLCAT_SUPNBR "
					+ "WHERE HTLCAT="+ area.htlcat + " AND"
					+ " SUPNBR='" + area.supnbr + "' AND "
					+ " AGT_CD='" + area.agt_cd + "'";
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