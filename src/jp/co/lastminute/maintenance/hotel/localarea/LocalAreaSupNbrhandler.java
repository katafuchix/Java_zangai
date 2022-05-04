package jp.co.lastminute.maintenance.hotel.localarea;

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
public class LocalAreaSupNbrhandler extends Commander implements CommandIF{
	/**
	 * 削除
	 */
	public boolean removeLocalAreaSubNbr(LocalAreaSupNbr area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = "DELETE LOCAL_AREA_SUPNBR_STORED WHERE SEQ_NO=" + area.seq_no;
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
	public boolean updateLocalAreaSubNbr(LocalAreaSupNbr area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = updateLocalAreaSubNbrStr(area);
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
	public boolean addLocalAreaSubNbr(LocalAreaSupNbr area) {
		try {
			if( checkAddnew( area ) ){
				JdbcAdapter db = new JdbcAdapter();
				if (db.init(this.dataSource)) {
					String sql = addLocalAreaSubNbrStr(area);
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
	public LocalAreaSupNbr[] getList(LocalAreaSupNbr area) {
		LocalAreaSupNbr[] localAreaSupNbrs = null;
		try {
			JdbcAdapter db = new JdbcAdapter();
			String sql = getListStr(area);
			System.err.println(sql);
			if (sql.length() > 0) {
				if (db.init(this.dataSource)) {
					Vector rows = db.dbSelect(sql);
					if (rows.size() > 0) {
						localAreaSupNbrs = new LocalAreaSupNbr[rows.size()];
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser(rows);
						for (int i = 0; i < rows.size(); i++) {
							LocalAreaSupNbr localAreaSupNbr = new LocalAreaSupNbr();
							localAreaSupNbr.local_area_code = acceser.getDatabyInt(i, 0);
							localAreaSupNbr.local_area_code_name = acceser.getData(i, 1);
							localAreaSupNbr.supnbr = acceser.getData(i, 2);
							localAreaSupNbr.agt_cd = acceser.getData(i, 3);
							localAreaSupNbr.seq_no = acceser.getDatabyInt(i, 4);
							localAreaSupNbrs[i] = localAreaSupNbr;
							localAreaSupNbr = null;
						}
						return localAreaSupNbrs;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return localAreaSupNbrs;
	}
	public String updateLocalAreaSubNbrStr(LocalAreaSupNbr area) throws Exception {
		return "UPDATE LOCAL_AREA_SUPNBR_STORED "
			+ "SET "
			+ "LOCAL_AREA_CODE_NAME='" + area.local_area_code_name + "' "
			+ "WHERE SEQ_NO=" + area.seq_no;
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addLocalAreaSubNbrStr(LocalAreaSupNbr area) throws Exception {
		return "INSERT INTO LOCAL_AREA_SUPNBR_STORED ("
			+ "SEQ_NO,"
			+ "LOCAL_AREA_CODE,"
			+ "SUPNBR,"
			+ "AGT_CD) VALUES ( (SELECT MAX(SEQ_NO) + 1 FROM LOCAL_AREA_SUPNBR_STORED),"
			+ area.local_area_code + ","
			+ "'"+ area.supnbr	+ "',"
			+ "'" + area.agt_cd	+ "')";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr(LocalAreaSupNbr area) throws Exception {
		return "SELECT "
 				+"A.LOCAL_AREA_CODE,"	//00
 				+"A.LOCAL_AREA_CODE_NAME,"	//01
 				+"B.SUPNBR,"	//02
 				+"B.AGT_CD,"	//03
 				+"B.SEQ_NO "	//04
 				+"FROM "
				+"LOCAL_AREA_STORED A, LOCAL_AREA_SUPNBR_STORED B "
				+"WHERE A.LOCAL_AREA_CODE=B.LOCAL_AREA_CODE AND "
				+"B.SUPNBR='" + area.supnbr + "' AND B.AGT_CD='" + area.agt_cd + "' ORDER BY A.LOCAL_AREA_CODE_NAME";
	}

	/**
	 * 追加の際の確認
	 */
	public boolean checkAddnew(LocalAreaSupNbr area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql =
					"SELECT COUNT(*) FROM LOCAL_AREA_SUPNBR_STORED "
					+ "WHERE LOCAL_AREA_CODE="+ area.local_area_code + " AND"
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