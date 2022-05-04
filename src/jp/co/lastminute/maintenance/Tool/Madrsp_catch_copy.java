package jp.co.lastminute.maintenance.Tool;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;
import jp.co.yobrain.util.DataFormat;

import javax.sql.DataSource;

import jp.co.lastminute.maintenance.hotel.model.Madrsp;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Madrsp_catch_copy {
	private static int row_size_ = 10000;
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
		boolean agt = true;
		Madrsp_catch_copy handler = new Madrsp_catch_copy( maker.getDatasource() );
		handler.StartMoving();
		handler.distroy();
		handler = null;
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
	public Madrsp_catch_copy( DataSource dataSource ){
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
	public void StartMoving( ){
		setMadrsp();
		for(int i=0; i<temp.size(); i++){
			Madrsp madrsp = (Madrsp)temp.get( i );
			madrsp = setPriceCatch( madrsp );
			setMadrspNew( madrsp );
		}
	}
	public Madrsp setPriceCatch( Madrsp madrsp ){
		try{
			if( madrsp.jpntxt.length() >30 ){
				int position = madrsp.jpntxt.indexOf("]]>");
				if( position > 120 ){
					position = 120;
				}
				String c_copy = madrsp.jpntxt.substring( 23, position);
				madrsp.catch_copy = c_copy;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return madrsp;
	}
	public void setMadrspNew( Madrsp madrsp ){
		try{
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE MADRSP SET "
						   + "CATCH_COPY='" + madrsp.catch_copy + "' "
						   + " WHERE "
						   + "SUPNBR='" + madrsp.supnbr + "' AND "
						   + "AGT_CD='" + madrsp.agt_cd + "'";
				System.err.println( sql );
				db.dbUpdate( sql );
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
	}
	public void setMadrsp(){
		try{
			String sql = "SELECT SUPNBR, AGT_CD, JPNTXT FROM MADRSP";
			if( db.init( this.dataSource ) ){
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				for(int i=0; i<acceser.getRowsize(); i++){
					Madrsp madrsp = new Madrsp();
					madrsp.supnbr = acceser.getData( i, 0 );
					madrsp.agt_cd = acceser.getData( i, 1 );
					madrsp.jpntxt = acceser.getData( i, 2 );
					temp.add( madrsp );
					madrsp = null;					
				}
			}
		}catch(Exception ex){	
			ex.printStackTrace();
			System.exit( 0 );	
		}
	}
}
