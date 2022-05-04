package jp.co.lastminute.maintenance.pages;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.yobrain.util.jdbc.*;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.dbDataAccesser;

import jp.co.lastminute.maintenance.Property;
import jp.co.lastminute.maintenance.xmlstruct.*;
import jp.co.yobrain.util.rpc.PostString;
import jp.co.yobrain.util.rpc.SendClient;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class IndexPageMaker implements Serializable{
	protected DataFormat df = null;
	protected DataSource ds;
	protected Pageamount pageamount = null;
	protected static String target;
	protected static String host1;
	protected static String host2;
	private static ResourceBundle resources;
	static {
        try {
            resources = ResourceBundle.getBundle("jp.co.lastminute.maintenance.pages.resources.Server", Locale.getDefault());
            target    = resources.getString( "target" );
            host1	  = resources.getString( "host1" );
            host2 	　=	resources.getString( "host2" );
        }catch (MissingResourceException mre){
            resources = null;
        }
    }
	/**
	 * イニシャラクィズ
	 */
	public void init( DataSource ds ){
		this.ds = ds;	
	}
	/**
	 * データの取得
	 */
	public dbDataAccesser getSqlData( String sql ){
		JdbcAdapter db = new JdbcAdapter();
		try{
	        if( db.init( this.ds ) ){
	            Vector vc = db.dbSelect( sql );
	            return ( new dbDataAccesser( vc ) );
	        }
        }catch(Exception ex){}
        return null;
	}
	/**
	 * Xmlストリングの取得
	 */
	public String xmlString(){
		return pageamount.generateXml();
	}
	/**
	 * XMLの生成
	 */
	public String makeXml( String contextValue, String contextname, String contextpath) throws Exception {
		String url = "http://" + target + "/lmj/maintenance/util/XmlDatabaseServlet";
		Vector vc = new Vector();
		PostString ps01 = new PostString( "contextValue", contextValue );
		PostString ps02 = new PostString( "contextname", contextname );
		PostString ps03 = new PostString( "contextpath", contextpath );
		vc.add( ps01 );
		vc.add( ps02 );
		vc.add( ps03 );
		SendClient sendclient = new SendClient();
		String result = sendclient.sendText( url, vc );
		//System.err.println( result );
		return result;
	}
	/**
	 * コンテキストのロード
	 */
	public boolean changeContext( String targetname ){
		try{
			String[] urls = { host1, host2 };
			for(int i=0; i<urls.length; i++){
				if(( host1 != null )&&(host1.length() > 0 )){
					System.err.println(  urls[i] + "          " +  targetname );
					String url = "http://" + urls[i] + "/lmj/maintenance/util/XmlReadServlet";
					Vector vc = new Vector();
					PostString ps01 = new PostString( "partName", targetname );
					vc.add( ps01 );
					SendClient sendclient = new SendClient();
					sendclient.sendText( url, vc );
					sendclient = null;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
		
	}
	/**
	 * ページアマウントの取得
	 */
	public void setpageamount( int type, int topix, int lists, String sql, int catid ) throws Exception {
		this.pageamount = new Pageamount( topix, lists );
		dbDataAccesser accesser = getSqlData( sql );
		
		for(int i=0; i<accesser.getRowsize(); i++){
			Plan plan = new Plan();
			Product product = new Product();
			Production producttion = new Production();
			plan.scatid = accesser.getDatabyInt(i, 1);
			plan.catid = catid;
			plan.scatid_catch_copy = accesser.getData(i, 7);
			plan.scatid_description = accesser.getData(i, 11);
			plan.title = accesser.getData( i, 7 );
			product.prod_id = accesser.getData( i, 0 );
			product.name = accesser.getData( i, 10 );
			product.price = accesser.getData( i, 9 );
			producttion.setPlan( plan );
			producttion.setProduct( product );
			System.err.println( "Pageamount IndexPagemaker" + i);
			if( i < topix ){
				this.pageamount.setTopix( producttion );
			}
			if( i >= topix ){
				this.pageamount.setList( producttion );
			}
			plan = null;
			product = null;
			producttion = null;
		}
		this.pageamount.types = type;//accesser.getDatabyInt( 0, 11 );
		this.pageamount.title = accesser.getData( 0, 8 );
		this.pageamount.today = df.getNowDate(0);
		this.pageamount.todaynumber = df.getNowDate(0, true);
	}
	
	public static final String restrant_selectsel ="SELECT "
												+ "A.PROD_ID_1, " 		//00
												+ "A.S_CATID,"	  		//01	
												+ "A.STARTDATE,"		//02
												+ "A.STOPDATE,"			//03
												+ "A.SEQNO,"			//04
												+ "A.PAGE_TYPE,"		//05
												+ "D.MOOD,"				//06
												+ "C.CATCH_COPY,"		//07
												+ "B.DESCRPTION,"		//08
												+ "D.BUDGETEXSAMPLE,"	//09
												+ "D.RESTAURANTNAME,"	//10
												+ "NVL( C.DESCRIPTION, '' )"	//11
												+ " FROM  CATEGORYSELECT A,"
												+ "PAGETYPE_MASTER B,"
												+ "CATID_SMALL_MASTER C,"
												+ "RESTAURANTINFO D"
												+ " WHERE "
												+ "A.PROD_ID_1=D.RESTAURANT AND "
												+ "A.S_CATID = C.S_CATID AND "
												+ "A.PAGE_TYPE = B.PAGE_TYPE AND "
												+ "A.PAGE_TYPE = $1 ORDER BY SEQNO";
													
	public static final String gift_selectsql ="SELECT "
											+ "A.PROD_ID_1, "			//00
											+ "A.S_CATID, "				//01
											+ "A.STARTDATE, "			//02
											+ "A.STOPDATE, "			//03
											+ "A.SEQNO, "				//04
											+ "A.PAGE_TYPE, "			//05
											+ "E.CATCHCOPY, "			//06
											+ "C.CATCH_COPY, "			//07
											+ "B.DESCRPTION, "			//08
											+ "E.PRICE, "				//09
											+ "E.PRODUCT_NAME, "		//10
											+ "NVL( C.DESCRIPTION, '' ),"	//11	
											+ ""
											+ " FROM  CATEGORYSELECT A,"
											+ "PAGETYPE_MASTER B,"
											+ "CATID_SMALL_MASTER C,"
											+ "CALLEGEPRODUCT_TBL_S D,"
											+ "CALLEGEPRODUCT_TBL E"
											+ " WHERE "
											+ "D.JAN_CD=E.JAN_CD AND "
											+ "A.PROD_ID_1=D.JAN_CD AND "
											+ "A.S_CATID=D.S_CATID AND "
											+ "A.S_CATID = C.S_CATID AND "
											+ "A.PAGE_TYPE = B.PAGE_TYPE AND "
											+ "A.PAGE_TYPE = $1 ORDER BY SEQNO";
	public static final String gift_viewsql = "SELECT  "
 											+ "B.S_CATID,"
 											+ "NVL(A.DESCRIPTION, ' ') DESCRIPTION,"
 											+ "B.PROD_ID_1,"
 											+ "B.STARTDATE,"
 											+ "B.STOPDATE,"
 											+ "B.SEQNO,"
 											+ "C.PRODUCT_NAME,"
 											+ "A.CATCH_COPY "
 											+ " FROM "
 											+ "CATID_SMALL_MASTER A,"
 											+ "CATEGORYSELECT B,"
 											+ "CALLEGEPRODUCT_TBL C"
 											+ " WHERE "
 											+ "B.PROD_ID_1=C.JAN_CD AND "
 											+ "B.STOP_FLG='0' AND "
 											+ "A.S_CATID=B.S_CATID AND "
 											+ "B.PAGE_TYPE=$1 "
 											+ "ORDER BY SEQNO";
}
