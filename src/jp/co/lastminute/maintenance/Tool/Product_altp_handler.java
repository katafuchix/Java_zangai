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
public class Product_altp_handler{
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
		while(stop){
			Product_altp_handler handler = new Product_altp_handler( maker.getDatasource() );
			handler.setSeq_no( num );
			
			handler.StartMoving( agt );
			//
			if(handler.getTempSize() == 0 ){
				if( agt ){	
					agt = false;	
				}else{
					stop = false;
				}
			}
			num = handler.getPriduct_altp_seq();
			//終る
			handler.distroy();
			handler = null;
		}
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
	public Product_altp_handler( DataSource dataSource ){
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
	public void StartMoving(boolean flg ){
		if( flg ){
			setProduct_altp( this.getAltpKntStr );
		}else{
			setProduct_altp( this.getAltpNtaStr );
		}
		for(int i=0; i<temp.size(); i++){
			Product_Altp altp = (Product_Altp)temp.get( i );
			temp.set( i, insertAltp( altp ) );
		}
		
		for(int i=0; i<temp.size(); i++){
			Product_Altp altp = (Product_Altp)temp.get( i );
			if( altp.sunncess ){
				temp.set( i, updateWebaltp( altp, flg ) );
			}
		}
		for(int i=0; i<temp.size(); i++){
			Product_Altp altp = (Product_Altp)temp.get( i );
			if( altp.sunncess && altp.websuccess ){
				altp = getALTDAT_FROM( altp );
				altp = getALTDAT_TO( altp );
				setAltdat( altp );
			}
		}
	}
	public void setAltdat( Product_Altp altp ){
		try{
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE PRODUCT_ALTP SET "
						   + "ALTDAT_FROM=" + altp.altdat_from + ","
						   + "ALTDAT_TO=" + altp.altdat_to + " "
						   + " WHERE "
						   + "PRODUCT_SEQ_NO=" + altp.product_seq_no ;
				System.err.println( sql );
				altp.websuccess = db.dbUpdate( sql );
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
	}
	public Product_Altp getALTDAT_FROM( Product_Altp altp ){
		try{
			if( db.init( this.dataSource ) ){
					String sql = "SELECT MIN( ALTDAT ) FROM WEBALTP WHERE PRODUCT_SEQ_NO=" + altp.product_seq_no;
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					dbDataAccesser acceser = new dbDataAccesser( rows );
					if( acceser.getDatabyInt(0, 0) != 0){
						altp.altdat_from = "" + acceser.getDatabyInt(0, 0) + "";
						return altp;
					}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		altp.altdat_from = df.getNowDate(0, true);
		return altp;
	}
	
	public Product_Altp getALTDAT_TO( Product_Altp altp ){
		try{
			if( db.init( this.dataSource ) ){
					String sql = "SELECT MAX( ALTDAT ) FROM WEBALTP WHERE PRODUCT_SEQ_NO=" + altp.product_seq_no;
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					dbDataAccesser acceser = new dbDataAccesser( rows );
					if( acceser.getDatabyInt(0, 0) != 0){
						altp.altdat_to = "" + acceser.getDatabyInt(0, 0) + "";
						return altp;
					}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		altp.altdat_from = df.getNowDate(0, true);
		return altp;
	}
	
	public Product_Altp updateWebaltp( Product_Altp altp, boolean flg ){
		try{
			if( altp.sunncess ){
				if( db.init( this.dataSource ) ){
					String sql = "";
					if(flg){
						sql = "UPDATE WEBALTP SET "
							   + "PRODUCT_SEQ_NO=" + altp.product_seq_no 
							   + " WHERE "
							   + "AGT_CD='"+ altp.agt_cd + "' AND "
							   + "SUPNBR='" + altp.supnbr + "' AND "
							   + "PRODUCT_ID='" + altp.product_id + "' AND "
							   + "CAMPAIGN='" + altp.campaign + "' AND "
							   + "ROOM_TYPE='" + altp.agt_roomtype + "' AND "
							   + "MEAL_CODE='" + altp.meal_code + "'";
					}else{
						sql = "UPDATE WEBALTP SET "
							   + "PRODUCT_SEQ_NO=" + altp.product_seq_no 
							   + " WHERE "
							   + "AGT_CD='"+ altp.agt_cd + "' AND "
							   + "SUPNBR='" + altp.supnbr + "' AND "
							   + "PRODUCT_ID='" + altp.product_id + "' AND "
							   + "CAMPAIGN='" + altp.campaign + "' AND "
							   + "AGTROOMTYPE='" + altp.agt_roomtype + "' AND "
							   + "MEAL_CODE='" + altp.meal_code + "'";
					}
					System.err.println( sql );
					altp.websuccess = db.dbUpdate( sql );
					return altp;
				}
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
		return altp;
	}
	public Product_Altp insertAltp( Product_Altp altp ){
		try{
			if( check( altp ) ){
				if( db.init( this.dataSource ) ){
					altp.product_seq_no = getPriduct_altp_seq();
					String sql = "INSERT INTO PRODUCT_ALTP ("
							+ "PRODUCT_SEQ_NO,"
							+ "AGT_CD,"
							+ "SUPNBR,"
							+ "PRODUCT_ID,"
							+ "CAMPAIGN, "
							+ "AGT_ROOMTYPE, "
							+ "AGT_ROOMTYPE_NAME, "
							+ "MEAL_CODE, "
							+ "HAVEALT, "
							+ "MAX_NR, "
							+ "MIN_NR, "
							+ "LAST_DAY,"
							+ "START_DAY) VALUES("
							+ altp.product_seq_no+ ","
							+ "'" + altp.agt_cd + "',"
							+ "'" + altp.supnbr + "',"
							+ "'" + altp.product_id + "',"
							+ "'" + altp.campaign + "',"
							+ "'" + altp.agt_roomtype + "',"
							+ "'" + altp.agt_roomtype_name + "',"
							+ "'" + altp.meal_code + "',"
							+ 0 + "," 
							+ altp.max_nr + ","
							+ altp.min_nr+ ","
							+ altp.last_day+ ","
							+ altp.start_day + ")";
					System.err.println( sql );
					altp.sunncess = db.dbInsert( sql );
					return altp;
				}
			}else{
				int seq_no = getProductSeqatProduct_Altp( altp );
				if(seq_no > 0) {
					altp.product_seq_no = seq_no;
					altp.sunncess = true;
					return altp;
				}
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
		return altp;
	}
	public void setProduct_altp(String sql){
		try{
			if( db.init( this.dataSource ) ){
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				for(int i=0; i<acceser.getRowsize(); i++){
					Product_Altp altp = new Product_Altp();
					altp.product_seq_no = 0; //getPriduct_altp_seq();
					altp.agt_cd = acceser.getData(i, 0);
					altp.supnbr = acceser.getData(i, 1);
					altp.product_id = acceser.getData(i, 2);
					altp.campaign = acceser.getData(i, 3);
					altp.agt_roomtype = acceser.getData(i, 4);
					altp.agt_roomtype_name = acceser.getData(i, 5);
					altp.meal_code = acceser.getData(i, 6);
					altp.room_capa = acceser.getData(i, 7);
					altp.max_nr = acceser.getDatabyInt(i, 8);
					altp.min_nr = acceser.getDatabyInt(i, 9);
					altp.last_day = acceser.getDatabyInt(i, 11);
					altp.start_day = acceser.getDatabyInt(i, 12);
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
	/**
	 * checl_2
	 */
	public int getProductSeqatProduct_Altp( Product_Altp altp ){
		try{	
			if( db.init( this.dataSource ) ){
				String sql = "SELECT PRODUCT_SEQ_NO FROM PRODUCT_ALTP"
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
				if( acceser.getDatabyInt(0, 0) > 0 ){
					return acceser.getDatabyInt(0, 0);
				}
			}		
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;	
		}
		return 0;
	}
	/**
	 * シーケンスの保持
	 */
	public void setSeq_no( int num ){
		if( num == 0 ){
			try{
				if( db.init( this.dataSource ) ){
					//String sql = "SELECT PRODUCT_ALTP_SEQ.NEXTVAL FROM DUAL";
					String sql = "SELECT MAX( PRODUCT_SEQ_NO )+1 FROM PRODUCT_ALTP";
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					dbDataAccesser acceser = new dbDataAccesser( rows );
					this.seq_no = acceser.getDatabyInt(0, 0);
				}
			}catch(Exception ex){	
				ex.printStackTrace();	
				System.exit( 0 );
			}
		}else{
			this.seq_no = num + 1;
		}
	}
	public int getPriduct_altp_seq(){
		this.seq_no++;
		return this.seq_no;
	}
	public static final String getAltpKntStr = "SELECT 	DISTINCT "
											+"A.AGT_CD,"	//00
											+"A.SUPNBR,"	//01
											+"A.PRODUCT_ID,"	//02
											+"A.CAMPAIGN,"		//03
											+"A.ROOM_TYPE,"		//04
											+"CONCAT ( B.ROOM_TYPE_TXT || ' ', C.TYPE_NAME ), " //05
											//+"B.ROOM_TYPE_NAME, "	//05
											+"A.MEAL_CODE,"		//06
											+"A.ROOM_CAPA,"		//07
											+"A.MAX_NR,"		//07
											+"A.MIN_NR,"		//08
											+"A.AGTROOMTYPE,"	//09
											+"A.LAST_DAY,"
											+"A.START_DAY "
											+"FROM "
										    +"WEBALTP A, ECRBMST B, MEALTYPE_MASTER C "
											+"WHERE "
											+"A.MEAL_CODE=C.MEAL_TYPE_CD AND "
											+"A.ROOM_TYPE=B.ROOM_TYPE AND "
											+"A.AGT_CD='KNT' AND "
											+"PRODUCT_SEQ_NO IS NULL AND "
											+"ROWNUM<="+ row_size_ ;
											
	public static final String getAltpNtaStr = "SELECT 	DISTINCT "
											+"A.AGT_CD,"	//00
											+"A.SUPNBR,"	//01	
											+"A.PRODUCT_ID,"	//02
											+"A.CAMPAIGN,"		//03
											+"A.AGTROOMTYPE,"	//04
											+"B.AGT_ROOMTYPE_NAME, "	//05
											+"A.MEAL_CODE,"		//06
											+"A.ROOM_CAPA,"		//07
											+"A.MAX_NR,"		//08
											+"A.MIN_NR,"		//09
											+"A.AGTROOMTYPE,"	//10
											+"A.LAST_DAY,"		//11
											+"A.START_DAY "		//12
											+"FROM "
										    +"WEBALTP A, YDPLMST B "
											+"WHERE "
											+"A.PRODUCT_ID=B.PRODUCT_ID(+) AND "
											+"A.SUPNBR=B.SUPNBR AND "
											+"A.CAMPAIGN=B.CAMPAIGN(+) AND "
											+"A.AGT_CD='NTA' AND "
											+"PRODUCT_SEQ_NO IS NULL AND "
											+"ROWNUM<="+ row_size_ ;

}
