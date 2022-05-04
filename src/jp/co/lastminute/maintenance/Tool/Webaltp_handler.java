package jp.co.lastminute.maintenance.Tool;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;
import jp.co.yobrain.util.DataFormat;

import javax.sql.DataSource;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Webaltp_handler {
	private static int row_size_ = 50000;
	private int size = 100;
	private int seq_no = 0;
	private DataSource dataSource = null;
	private JdbcAdapter db = null;
	private DataFormat df = null;
	private Vector temp = null;
	
	public static void main(String[] args) throws IOException{
		//データソース取得
		DataSourceMaker maker = new DataSourceMaker();
		int num = 0;
		maker.init();
		//
		boolean stop = true;
		//while(stop){
		Webaltp_handler handler = new Webaltp_handler( maker.getDatasource() );
		handler.StartMoving( );
		//
		if(handler.getTempSize() == 0 ){
			stop = false;
		}
		//終る
		handler.distroy();
		handler = null;
		//}
		maker.distroy();
	}
	/**
	 * サイズの取得
	 */
	public int getTempSize(){
		return temp.size();
	}
	/**
	 * データソースの初期化
	 */
	public Webaltp_handler( DataSource dataSource ){
		this.dataSource = dataSource;
		db = new JdbcAdapter();
		temp = new Vector( );
	}
	/**
	 * 破棄
	 */
	public void distroy(){
		this.db = null;
		this.dataSource = null;
		this.temp = null;
	}
	/**
	 * ここがメイン
	 */
	public void StartMoving(){
		for(int i=0; i<row_size_; i++){			
			System.out.println(i);
			updateWebaltp();
			//System.err.println( updateWebaltp() );
		}
	}
	public boolean updateWebaltp(){
		try{
			if( db.init( this.dataSource ) ){
					String sql = "UPDATE WEBALTP "
								+ "SET SEQ_NO=( SELECT MAX(SEQ_NO)+1 FROM WEBALTP) "
								+ "WHERE SEQ_NO IS NULL AND ROWNUM=1";
				//System.err.println( sql );
				return db.dbUpdate( sql );
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
		return false;
	}
}
