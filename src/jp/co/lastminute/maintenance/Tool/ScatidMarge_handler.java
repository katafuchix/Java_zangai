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
public class ScatidMarge_handler {
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
		//while(stop){
			ScatidMarge_handler handler = new ScatidMarge_handler( maker.getDatasource() );
			handler.setSeq_no( num );
			
			handler.StartMoving( );
			//
			if(handler.getTempSize() == 0 ){
				stop = false;
			}
			num = handler.getScatidMarge_seq();
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
	public ScatidMarge_handler( DataSource dataSource ){
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
		setScatid_Marge( getMrackStr );
		
		for(int i=0; i<temp.size(); i++){
			Scatid_marge scat = (Scatid_marge)temp.get( i );
			temp.set( i, insertScatidmarge( scat ) );
		}
	}
	public Scatid_marge insertScatidmarge( Scatid_marge scat ){
		try{
			if( db.init( this.dataSource ) ){
				if( check( scat ) ){
					scat.seq_no = getScatidMarge_seq();
					String sql = "INSERT INTO SCATID_MARGE ("
							+ "SEQNO,"
							+ "S_CATID,"
							+ "JAN_CD,"
							+ "CATID,"
							+ "TITLE, "
							+ "PRIORITY, "
							+ "MAKE_DATE) VALUES("
							+ scat.seq_no+ ","
							+ scat.scatid + ","
							+ "'" + scat.jan_cd + "',"
							+  scat.catid + ","
							+ "'" + scat.title + "',"
							+ scat.priority + ","
							+ "SYSDATE)";
					System.err.println( sql );
					scat.sunncess = db.dbInsert( sql );
					return scat;
				}
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
		return scat;
	}
	public void setScatid_Marge(String sql){
		try{
			if( db.init( this.dataSource ) ){
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				for(int i=0; i<acceser.getRowsize(); i++){
					Scatid_marge scat = new Scatid_marge();
					scat.seq_no = 0;
					scat.scatid = acceser.getDatabyInt( i, 1 );
					scat.jan_cd = "" + acceser.getDatabyInt( i, 0 ) + "";
					scat.catid = acceser.getDatabyInt( i, 2 );
					String temptitle = acceser.getData( i, 3 ) + "/" + acceser.getData( i, 4 );
					if( temptitle.length() > 30 ){
						scat.title = temptitle.substring( 0, 30 );
					}else{
						scat.title = temptitle;
					}
					scat.priority = 0;
					temp.add( scat );
					scat = null;					
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
	public boolean check( Scatid_marge scat ){
		try{	
			if( db.init( this.dataSource ) ){
				String sql = "SELECT COUNT(*) FROM SCATID_MARGE"
							+ " WHERE "
							+ "S_CATID="+ scat.scatid + " AND "
							+ "JAN_CD='" + scat.jan_cd + "'";
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				dbDataAccesser acceser = new dbDataAccesser( rows );
				if( rows.size() > 0 && acceser.getDatabyInt(0, 0) == 0 ){
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
	 * シーケンスの保持
	 */
	public void setSeq_no( int num ){
		if( num == 0 ){
			try{
				if( db.init( this.dataSource ) ){
					String sql = "SELECT MAX(SEQNO) + 1 FROM SCATID_MARGE";
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
	public int getScatidMarge_seq(){
		this.seq_no++;
		return this.seq_no;
	}
	public static final String getMrackStr = "SELECT 	DISTINCT "
											 +"B.PRODUCT_SEQ_NO,"
											 +"A.S_CATID,"
											 +"A.PRODUCT_TYPE_CD,"
											 +"C.JPNNAM,"
											 +"B.AGT_ROOMTYPE_NAME "
											 +"FROM "
											 +"M_RACKRATE_S A, PRODUCT_ALTP B, MADRSP C "
											 +"WHERE "
											 +"B.SUPNBR=C.SUPNBR AND B.AGT_CD=C.AGT_CD AND "
											 +"A.PRODUCT_ID=B.PRODUCT_ID AND A.AGT_CD=B.AGT_CD AND "
											 +"ROWNUM<="+ row_size_ ;
											

}
