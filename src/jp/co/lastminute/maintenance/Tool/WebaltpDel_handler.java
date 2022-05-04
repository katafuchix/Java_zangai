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
public class WebaltpDel_handler {
	private static int row_size_ = 10;
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
		DataFormat dfd = null;
		
		boolean stop = true;
		//while(stop){
		WebaltpDel_handler handler = new WebaltpDel_handler( maker.getDatasource() );
		
		String targetdate = dfd.DelToDate( dfd.getNowDate(0, true) , 10 );
		handler.StartMoving( targetdate );
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
	public WebaltpDel_handler( DataSource dataSource ){
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
	public void StartMoving(String targetdate ){
		for(int i=0; i<row_size_; i++){
			System.err.print("Count: " + i );
			updateWebaltp( "KNT", targetdate );
		}
		for(int i=0; i<row_size_; i++){
			System.err.print("Count: " + i );
			updateWebaltp( "NTA", targetdate );
		}
	}
	public boolean updateWebaltp(String agt_cd, String targetdate ){
		try{
			if( db.init( this.dataSource ) ){
					String sql = "DELETE WEBALTP WHERE AGT_CD='" + agt_cd + "' AND " 
								+ " ALTDAT<" + targetdate + " AND ROWNUM<" + row_size_;
				System.err.println( sql );
				return db.removeSQL( sql );
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
		return false;
	}
}
