package jp.co.lastminute.maintenance.hotel.localarea;

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
public class LocalAreahandler extends Commander implements CommandIF {
	/**
	 * コンテキストのリロード
	 */
	public boolean reLoloadComtext(){
		String contextname = ContextProperty.LOCALAREAS_  ;
		
		try{
			FileOutputStream fout = new FileOutputStream(ContextProperty.LOCALAREASLocate_ );
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
							+"DISTINCT "
							+"A.LOCAL_AREA_CODE,"
							+"A.LOCAL_AREA_CODE_NAME "
							+"FROM LOCAL_AREA_STORED A,"
							+"LOCAL_AREA_SUPNBR_STORED B "
							+"WHERE "
							+"A.LOCAL_AREA_CODE=B.LOCAL_AREA_CODE "
							+"ORDER BY A.LOCAL_AREA_CODE_NAME";
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
	 * 詳細の更新
	 */
	public boolean updateLocalArea(LocalArea area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = updateLocalAreaStr(area);
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
	public boolean addLocalArea(LocalArea area) {
		try {
			if( checkAddnew( area ) ){
				JdbcAdapter db = new JdbcAdapter();
				if (db.init(this.dataSource)) {
					String sql = addLocalAreaStr(area);
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
	public LocalArea[] getList(LocalArea area) {
		LocalArea[] localAreas = null;
		try {
			JdbcAdapter db = new JdbcAdapter();
			String sql = getListStr(area);
			System.err.println(sql);
			if (sql.length() > 0) {
				if (db.init(this.dataSource)) {
					Vector rows = db.dbSelect(sql);
					if (rows.size() > 0) {
						localAreas = new LocalArea[rows.size()];
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser(rows);
						for (int i = 0; i < rows.size(); i++) {
							LocalArea localarea = new LocalArea();
							localarea.local_area_code =
								acceser.getDatabyInt(i, 0);
							localarea.parent_code = acceser.getDatabyInt(i, 1);
							localarea.local_area_code_name =
								acceser.getData(i, 2);
							localAreas[i] = localarea;
							localarea = null;
						}
						return localAreas;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return localAreas;
	}
	public String updateLocalAreaStr(LocalArea area) throws Exception {
		return "UPDATE LOCAL_AREA_STORED "
			+ "SET "
			+ "PARENT_CODE="
			+ area.parent_code
			+ ","
			+ "LOCAL_AREA_CODE_NAME='"
			+ area.local_area_code_name
			+ "' "
			+ "WHERE LOCAL_AREA_CODE="
			+ area.local_area_code;
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addLocalAreaStr(LocalArea area) throws Exception {
		return "INSERT INTO LOCAL_AREA_STORED ("
			+ "LOCAL_AREA_CODE,"
			+ "PARENT_CODE,"
			+ "LOCAL_AREA_CODE_NAME ) VALUES ( "
			+ area.local_area_code + ", "+ area.parent_code	+ ", '" + area.local_area_code_name	+ "')";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr(LocalArea area) throws Exception {
		return "SELECT "
			+ "LOCAL_AREA_CODE,"
			+ "PARENT_CODE,"
			+ "LOCAL_AREA_CODE_NAME FROM LOCAL_AREA_STORED WHERE PARENT_CODE="
			+ area.parent_code
			+ " ORDER BY LOCAL_AREA_CODE";
	}

	/**
	 * 追加の際の確認
	 */
	public boolean checkAddnew(LocalArea area) {
		try {
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql =
					"SELECT COUNT(*) FROM LOCAL_AREA_STORED WHERE LOCAL_AREA_CODE="
						+ area.local_area_code;
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
