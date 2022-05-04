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
public class Product_altp_catch_copy {
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
		Product_altp_catch_copy handler = new Product_altp_catch_copy( maker.getDatasource() );
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
	public Product_altp_catch_copy( DataSource dataSource ){
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
		setProduct_altp();
		for(int i=0; i<temp.size(); i++){
			Product_Altp altp = (Product_Altp)temp.get( i );
			altp = setPriceCatch( altp );
			setAltdat( altp );
		}
	}
	public Product_Altp setPriceCatch( Product_Altp altp ){
		try{
			String sql ="SELECT "
					   +"(SELECT MIN(PRICE) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICE,"		//00
					   +"(SELECT MIN(PRICE2) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICE2,"	//01
					   +"(SELECT MIN(PRICE3) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICE3,"	//02
					   +"(SELECT MIN(PRICE4) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICE4,"	//03
					   +"(SELECT MIN(PRICE5) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICE5,"	//04
					   +"(SELECT MIN(PRICEA1) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEA1,"	//05
					   +"(SELECT MIN(PRICEA2) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEA2,"	//06
					   +"(SELECT MIN(PRICEA3) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEA3,"	//07
					   +"(SELECT MIN(PRICEA4) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEA4,"	//08
					   +"(SELECT MIN(PRICEB1) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEB1,"	//09
					   +"(SELECT MIN(PRICEB2) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEB2,"	//10
					   +"(SELECT MIN(PRICEB3) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEB3,"	//11
					   +"(SELECT MIN(PRICEB4) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEB4,"	//12
					   +"(SELECT MIN(PRICEC1) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEC1,"	//13
					   +"(SELECT MIN(PRICED1) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICED1,"	//13
					   +"(SELECT MIN(PRICEE) FROM WEBALTP WHERE PRODUCT_SEQ_NO="+altp.product_seq_no+") PRICEE "	//14
					   + "FROM DUAL";
			System.err.println( sql );
			if( db.init( this.dataSource ) ){
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				if (acceser.getRowsize() > 0 ){
					altp.price_catch ="" + acceser.getDatabyInt(0, 0) + ","
									 +"" + acceser.getDatabyInt(0, 1) + ","
									 +"" + acceser.getDatabyInt(0, 2) + ","
									 +"" + acceser.getDatabyInt(0, 3) + ","
									 +"" + acceser.getDatabyInt(0, 4) + ","
									 +"" + acceser.getDatabyInt(0, 5) + ","
									 +"" + acceser.getDatabyInt(0, 6) + ","
									 +"" + acceser.getDatabyInt(0, 7) + ","
									 +"" + acceser.getDatabyInt(0, 8) + ","
									 +"" + acceser.getDatabyInt(0, 9) + ","
									 +"" + acceser.getDatabyInt(0, 10) + ","
									 +"" + acceser.getDatabyInt(0, 11) + ","
									 +"" + acceser.getDatabyInt(0, 12) + ","
									 +"" + acceser.getDatabyInt(0, 13) + ","
									 +"" + acceser.getDatabyInt(0, 14) + "";
				}				
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return altp;
	}
	public void setAltdat( Product_Altp altp ){
		try{
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE PRODUCT_ALTP SET "
						   + "PRICE_CATCH='" + altp.price_catch + "' "
						   + " WHERE "
						   + "PRODUCT_SEQ_NO=" + altp.product_seq_no ;
				System.err.println( sql );
				altp.websuccess = db.dbUpdate( sql );
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
	}
	public void setProduct_altp(){
		try{
			String sql = "SELECT PRODUCT_SEQ_NO FROM PRODUCT_ALTP WHERE HAVEALT=0";
			if( db.init( this.dataSource ) ){
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				for(int i=0; i<acceser.getRowsize(); i++){
					Product_Altp altp = new Product_Altp();
					altp.product_seq_no = acceser.getDatabyInt(i, 0);
					temp.add( altp );
					altp = null;					
				}
			}
		}catch(Exception ex){	
			ex.printStackTrace();
			System.exit( 0 );	
		}
	}
	/**
	 * チェック
	 */
	public boolean check( Product_Altp altp ){
		try{	
			if( db.init( this.dataSource ) ){
				String sql = "SELECT COUNT(*) FROM PRODUCT_ALTP"
							+ " WHERE "
							+ "AGT_CD='"+ altp.agt_cd + "' AND "
							+ "SUPNBR='" + altp.supnbr + "' AND "
							+ "PRODUCT_ID='" + altp.product_id + "' AND "
							+ "CAMPAIGN='" + altp.campaign + "' AND "
							+ "AGT_ROOMTYPE='" + altp.agt_roomtype + "' AND "
							+ "MEAL_CODE='" + altp.meal_code + "'";
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				if( acceser.getDatabyInt(0, 0) == 0 ){
					return true;	
				}
			}		
		}catch(Exception ex){
			ex.printStackTrace();
			return false;	
		}
		return false;
	}
}
