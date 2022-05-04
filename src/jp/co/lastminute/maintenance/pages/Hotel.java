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
import jp.co.lastminute.maintenance.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Hotel extends IndexPageMaker implements Serializable{

	public static int[][] pagemetrix = jp.co.lastminute.maintenance.hotel.Property.pagemetrix;
	public static int[] page_types = jp.co.lastminute.maintenance.hotel.Property.page_types;
	//
	public static String[] page_type_names = jp.co.lastminute.maintenance.hotel.Property.page_type_names;
	//
	public static String page_paths = jp.co.lastminute.maintenance.hotel.Property.page_paths + "/";
	public static String targetname = jp.co.lastminute.maintenance.hotel.Property.targetname;
	private static int catid = jp.co.lastminute.maintenance.hotel.Property.product_type_cd;
	
	public void setpageamount( int type ) throws Exception {
		
		System.err.print( "type = " + type );
		System.err.print(" pagemetrix[type][0] = " + pagemetrix[type][0] );
		System.err.print(" pagemetrix[type][0] = " + pagemetrix[type][0] );
		System.err.println(" pagemetrix[type][1] = " + pagemetrix[type][1] );
		String sql = getSelectSql( page_types[ type ] );
		System.err.println( sql );	
		this.setpageamount( type, pagemetrix[type][0], pagemetrix[type][1], sql, this.catid );
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
			product.prod_id = accesser.getData( i, 0 ); //
			product.name = accesser.getData( i, 10 );
			product.price = accesser.getData( i, 9 );
			////
			product.agt_cd = accesser.getData( i, 12 );
			product.supnbr = accesser.getData( i, 13 );
			////
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
	
	
	public String getSelectSql( int type ){
		String[] values = { "" + type + "" };
		String sql = Sqlmaker.strPrintf( _selectsql, values );
		return sql;
	}
	public String getViewSql( int type ){
		String[] values = { "" + type + "" };
		String sql = Sqlmaker.strPrintf( _viewsql, values );
		return sql;
	}
	/**
	 * XMLの生成
	 */
	public String makeXml( String contextValue, int type ){
		try{
			
			if( super.makeXml( this.xmlString(), page_type_names[ type ], page_paths ).indexOf( "-1" ) == -1 ){
				return "<script language=\"JavaScript\">\n alert('成功しました。');\n</script>";
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "<script language=\"JavaScript\">\n alert('失敗しました。');\n</script>";
	}
	/**
	 * コンテキストのロード
	 */
	public String changeContextStr(  ){
		if(  super.changeContext( targetname ) ){
			return "<script language=\"JavaScript\">\n alert('成功しました。');\n</script>";
		}
		return "<script language=\"JavaScript\">\n alert('失敗しました。');\n</script>";
	}
	/**
	 * カテゴリーセレクトを取得する
	 */
	public Vector getMaintenanceView( int type ){
		Vector reVec = new Vector();
		String sql = getViewSql( type );
		System.err.println( sql );
		dbDataAccesser accesser = getSqlData( sql );
		for( int i=0; i<accesser.getRowsize(); i++){
			Mentlist mentlist = new Mentlist();
			//
			Categoryselect select 	= new Categoryselect();
			Catid_small small 		= new Catid_small();
			////////
			select.setSeqno( accesser.getDatabyInt( i, 5 ) );
			select.setPage_type( accesser.getDatabyInt( i, 8 ) );
			select.setS_catid( accesser.getDatabyInt( i, 0 ) );
			select.setStartdate( accesser.getDatabyInt( i, 3 ) );
			select.setStopdate( accesser.getDatabyInt( i, 4 ) );
			select.setDescription( accesser.getData( i, 1 ) );
			select.setProd_id_1( accesser.getData( i, 2 ) );
			
			small.setS_catid( accesser.getDatabyInt( i, 0) );
			small.setCatid( this.catid );
			small.setCatch_copy( accesser.getData( i, 7 ) );
			small.setDescription( accesser.getData( i, 1 ) );
			/////
			mentlist.setProduct_name( accesser.getData( i, 6 ) );
			mentlist.init( select, small );
			/////
			reVec.add( mentlist );
			mentlist = null;	small= null;	select = null;
		}
		return reVec;
	}
	/**
	 * ウインドウリストを出力する
	 */
	public WindowList[] getWindowList( String scatid ){
		String sql = getWindowListSql( scatid );
		System.err.println( sql );
		try{
		dbDataAccesser accesser = getSqlData( sql );
		WindowList[] windolist = new WindowList[ accesser.getRowsize() ];
			for( int i=0; i<accesser.getRowsize(); i++){
				WindowList window = new WindowList();
				window.jan_cd = accesser.getData( i, 0 );
				window.agt_product_cd = accesser.getData( i, 1 );
				window.product_name = accesser.getData( i, 2 );
				window.agt_cd = accesser.getData( i, 3 );
				window.price = accesser.getData( i, 4 );
				window.allotnum = accesser.getData( i, 5 );
				window.havealt= accesser.getData( i, 6 );
				window.first_name= accesser.getData( i, 7 );
				windolist[i] = window;
				window = null;
			}
			return windolist;
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	public String getWindowListSql( String scarid ){
		String[] values = { scarid };
		String sql = Sqlmaker.strPrintf( _windowsql, values );
		return sql;
	}
	public static final String _windowsql = "SELECT "
 										+"B.PRODUCT_SEQ_NO,"	//00
 										+"B.PRODUCT_ID, "		//01
 										+"A.TITLE,"				//02
 										+"B.AGT_CD,"			//03
 										+"B.PRICE,"				//04
 										+"'10',"				//05
 										+"B.HAVEALT, "			//06
 										+"C.FIRST_NAME "		//07
										+"FROM "
 										+"PRODUCT_ALTP B, "
 										+ "(SELECT JAN_CD, TITLE FROM SCATID_MARGE WHERE S_CATID=$1) A,"
 										+ "AGENT_TBL C "
 										+"WHERE A.JAN_CD=B.PRODUCT_SEQ_NO AND C.AGT_CD=B.AGT_CD "
 										+"ORDER BY HAVEALT,AGT_CD,JAN_CD";
 										
 	public static final String _selectsql ="SELECT "
										//+"A.PROD_ID_1, "				//00
										+"E.PRODUCT_ID, "				//00
										+"A.S_CATID, "					//01
										+"A.STARTDATE, "				//02
										+"A.STOPDATE, "					//03
										+"A.SEQNO, "					//04
										+"A.PAGE_TYPE, "				//05
										+"E.AGT_ROOMTYPE_NAME, "		//06
										+"C.CATCH_COPY, "				//07
										+"B.DESCRPTION, "				//08
										+"'', "							//09
										+"F.TITLE, "					//10
										+"NVL( A.DESCRIPTION, '' ) DESCRIPTION,"	//11
										+"E.AGT_CD,"					//12
										+"E.SUPNBR"						//13
										+" FROM CATEGORYSELECT A,"
										+"PAGETYPE_MASTER B,"
										+"CATID_SMALL_MASTER C,"
										+"PRODUCT_ALTP E, "
										+"SCATID_MARGE F "
										+"WHERE "
										+"A.S_CATID=F.S_CATID AND "
										+"F.JAN_CD=A.PROD_ID_1 AND "
										+"F.JAN_CD=E.PRODUCT_SEQ_NO AND "
										+"A.S_CATID=C.S_CATID AND "
										+"A.PAGE_TYPE=B.PAGE_TYPE AND "
										+"A.PAGE_TYPE=$1 ORDER BY SEQNO";

	public static final String _viewsql = "SELECT  "
 											+ "B.S_CATID,"								//00
 											+ "NVL(B.DESCRIPTION, ' ') DESCRIPTION,"	//01
 											+ "B.PROD_ID_1,"							//02
 											+ "B.STARTDATE,"							//03
 											+ "B.STOPDATE,"								//04
 											+ "B.SEQNO,"								//05
 											+ "E.TITLE,"								//06
 											+ "A.CATCH_COPY, "							//07 
 											+ "B.PAGE_TYPE,"							//08
 											+ "D.JPNNAM "								//09
 											+ " FROM "
 											+ "CATID_SMALL_MASTER A,"
 											+ "CATEGORYSELECT B,"
 											+ "PRODUCT_ALTP C,"
 											+ "MADRSP D,"
 											+ "SCATID_MARGE E"
 											+ " WHERE "
 											+ "B.S_CATID=E.S_CATID AND "
 											+ "E.JAN_CD=C.PRODUCT_SEQ_NO AND "
 											+ "C.SUPNBR=D.SUPNBR AND "
											+ "C.AGT_CD=D.AGT_CD AND "
 											+ "B.PROD_ID_1=E.JAN_CD AND "
 											+ "B.STOP_FLG='0' AND "
 											+ "A.S_CATID=B.S_CATID AND "
 											+ "B.PAGE_TYPE=$1 "
 											+ "ORDER BY SEQNO";
}
