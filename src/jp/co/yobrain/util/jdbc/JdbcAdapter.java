/*
 * JDBCAdapter.java
 *
 * Created on 2002/03/25, 10:11
 */
package jp.co.yobrain.util.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
/**
 *
 * @author	skondo
 * @version J2ee-2
 */
public class JdbcAdapter implements Serializable {
	//基本データタイプ
	private InitialContext ic; //接続プーリング
	private DataSource ds; //データソース


	private static boolean autoCommit = false;

	/** コンストラクター */
	public JdbcAdapter() {
		ic = null;
		ds = null;
	}
	/**
	 * イニシャライズ
	 */
	synchronized public boolean init(DataSource dss) {
		try {
			this.ds = dss;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * コネクションの取得
	 **/
	synchronized public Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * コネクションの切断
	*/
	synchronized public void close(Connection conn) {
		try {
			conn.close();
			return;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			if (conn != null)
				conn = null;
			return;
		}
	}
	/**
	 * セレクトを発行する。
	 * @return	Vector	結果
	 * @param	String	query	SQL
	 */
	synchronized public Vector dbSelect(String query) {
		return dbSelect(query, -1, -1);
	}
	/**
	 * セレクトを発行する。
	 * @return	Vector	結果
	 * @param	String	query	SQL
	 * @param	int start 開始行
	 * @param	int end		終了行
	 */
	synchronized public Vector dbSelect(String query, int start, int end) {
		//////executeQuery()を呼び出す///////////////////
		return executeQuery(query, start, end);
	}
	/**
	 ＊ アップデート
	 * @return boolean	true / flase	
	 * @param	String	query	SQ
	 */
	synchronized public boolean dbUpdate(String query) {
		return executeSQL(query);
	}
	/**
	 * インサート
	 * @return boolean	true / flase		
	 * @param	String	query	SQL
	*/
	synchronized public boolean dbInsert(String query) {
		return executeSQL(query);
	}

	/**
	 * ロールバック
	 */
	synchronized public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * コミット 
	 */
	synchronized public void Commit(Connection conn) {
		try {
			conn.commit(); //conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 検索処理の実行
	 */
	synchronized private Vector executeQuery(String query) {
		return executeQuery(query, -1, -1);
	}
	/**
	 * 検索処理の実行
	 */
	synchronized private Vector executeQuery(
		String query,
		int start,
		int end) {
		Statement stmt = null; //ステートメント
		ResultSet rset = null; //レコードセット
		Connection conn = null;
		String columnNames[] = {
		}; //カラムネーム集合
		Vector rows = new Vector(); //行
		ResultSetMetaData metaData; //一時保存メタデータ
		try {
			String queryUpper = query.toUpperCase();
			/////開始行の不正をキャッチ///////////////////////////////////////
			//クエリーがSELECTだったら処理を初期化して処理を実行,それ以外は通常実行/////
			if (query.indexOf("select") != -1
				|| query.indexOf("SELECT") != -1) {
				conn = getConnection();
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				metaData = rset.getMetaData();
				//////カラムのint配列を取得・カラム名のストリング配列を取得///////
				int numberOfColumns = metaData.getColumnCount();
				columnNames = new String[numberOfColumns];
				//////ダミーカウント分ループさせる////////////////////////////////
				if (start != -1) {
					for (int dmyCount = 0; dmyCount < start; dmyCount++) {
						if (rset.next() == false)
							break;
					}
				}
				//////行ベクターにデータを格納/////
				int rowCount = 0;
				while (rset.next()) {
					if (end != -1 && end == end) {
						break;
					}
					Vector newRow = new Vector();
					for (int i = 1; i <= columnNames.length; i++) {
						newRow.addElement(rset.getObject(i));
					}
					rows.addElement(newRow);
				}
				//ステートメントをクローズする//
				rset.close();
				stmt.close();
				close(conn);
			}
		} catch (Exception e) {
			//if( rset != null ){ rset.close();   }
			//if( stmt != null ){ stmt.close();   }
			if (conn != null) {
				close(conn);
			}
			e.printStackTrace();
		}
		return rows;
	}
	/**
	 * 更新系データベース処理
	 */
	synchronized private boolean executeSQL(String query) {
		Statement stmts; //ステートメント
		String queryUpper;
		Connection conn = null;
		try {
			conn = getConnection();
			queryUpper = query.toUpperCase();	//比較のためアッパーに設定
			//不正なSQLがあった場合は、ロールバック
			if ((queryUpper.indexOf("INSERT") == -1)
				&& (queryUpper.indexOf("UPDATE") == -1)) {
				close(conn);
				return false;
			}
			//正常な文字列をステートメントへ追加
			stmts = conn.createStatement();
			if (stmts.executeUpdate(query) > 0) {
				Commit(conn);
				queryUpper = "";
				stmts.close();
				close(conn);
				return true;
			} else {
				rollback(conn);
				queryUpper = "";
				stmts.close();
				close(conn);
				return false;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				close(conn);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				close(conn);
			}
			return false;
		}
	}
	/**
	 * 削除
	 */
	synchronized public boolean removeSQL(String query) {
		Statement stmts; //ステートメント
		String queryUpper;
		Connection conn = null;
		try {
			conn = getConnection();
			queryUpper = query.toUpperCase();	//比較のためアッパーに設定
			//不正なSQLがあった場合は、ロールバック
			if ((queryUpper.indexOf("UPDATE") == -1)
				&& (queryUpper.indexOf("DELETE") == -1)) {
				close(conn);
				return false;
			}
			//正常な文字列をステートメントへ追加
			stmts = conn.createStatement();
			if (stmts.executeUpdate(query) > 0) {
				Commit(conn);
				queryUpper = "";
				stmts.close();
				close(conn);
				return true;
			} else {
				rollback(conn);
				queryUpper = "";
				stmts.close();
				close(conn);
				return false;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				close(conn);
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				close(conn);
			}
			return false;
		}
	}
	/**
	 * 「'」 を 「''」に変換	
	 * @return 	String	変更データ
	 * @param	String str
	 */
	public static String convQuotation(String str) {
		String res = str;
		int wedge = 0;
		int point = 0;
		while ((point = res.indexOf("'", wedge)) > 0) {
			if (point == res.indexOf("''", wedge)) {
				res = res.substring(0, point) + "''" + res.substring(point + 1);
				wedge = point + 2;
			}
		}
		return res;
	}
	/**
	* 「'」 を 「''」に変換	
	* @return 	String	変更データ
	* @param	String str
	*/
	public static String convQuotationNeo(String str) {
		String reStr = "";
		String sq = "\'";
		String dq = "\"";
		String aq = "&";
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				if (str.substring(i, i + 1).equals(sq)) {
					sb.append(sq);
					sb.append(sq);
				} else if (str.substring(i, i + 1).equals(aq)) {
					sb.append(dq);
					sb.append(aq);
					sb.append(dq);
				} else {
					sb.append(str.substring(i, i + 1));
				}
			}
			reStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reStr;
	}
	public static String convQuotationReover(String str) {
		String reStr = "";
		String sq = "\'";
		String dq = "\"";
		String aq = "&";
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				if (str.substring(i, i + 1).equals(sq)) {
					sb.append(sq);
					sb.append(sq);
				} else if (str.substring(i, i + 1).equals(aq)) {
					sb.append(dq);
					sb.append(aq);
					sb.append(dq);
				} else {
					sb.append(str.substring(i, i + 1));
				}
			}
			reStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reStr;
	}
	public static String convQuotationReoverReverse(String str) {
		String reStr = "";
		String dq = "\"";
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				if (!str.substring(i, i + 1).equals(dq)) {
					sb.append(str.substring(i, i + 1));
				}
			}
			reStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reStr;
	}
	/**
	* バッチアップデート
	*/
	synchronized public boolean doBatchUpdate(String[] query) {
		Statement stmts = null; //ステートメント
		Connection conn = null;
		ArrayList result = null;
		int[] updateCounts = null;
		boolean reFlag = false;
		try {
			boolean update_flg = true;
			conn = getConnection();
			stmts = conn.createStatement();
			for (int i = 0; i < query.length; i++) {
				stmts.addBatch(query[i]);
				System.err.println( query[i] );
			}
			updateCounts = stmts.executeBatch();
			for (int i = 0; i < updateCounts.length; i++) {
				System.err.println( updateCounts[i] );
				if (updateCounts[i] == Statement.EXECUTE_FAILED) {
					update_flg = false;
					rollback(conn);
					break;
				}
			}
			if (update_flg) {
				Commit(conn);
				reFlag = true;
			}
			stmts.close();
		} catch (BatchUpdateException bue) {
			bue.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				close(conn);
			}
		}
		return reFlag;
	}
}
