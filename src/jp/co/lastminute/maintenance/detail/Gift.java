package jp.co.lastminute.maintenance.detail;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.jdbc.*;
import jp.co.lastminute.maintenance.model.*;

import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.jdbc.Sqlmaker;
import jp.co.yobrain.util.DataFormat;

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
public class Gift extends Detail implements PageDetail, Serializable{

	protected static final String detailstr = ContextProperty._gift_product_Dir + "/";
	
	
	public Gift(){
	}
	public Gift( DataSource ds) {
		this.dataSource = ds;
	}
	
	/**
	 * XMLÇÃê∂ê¨
	 */
	public String makeXml( String contextValue, String contextname ) throws Exception {
		String url = "http://" + target + "/lmj/maintenance/util/XmlDatabaseServlet";
		Vector vc = new Vector();
		PostString ps01 = new PostString( "contextValue", contextValue );
		PostString ps02 = new PostString( "contextname", contextname );
		PostString ps03 = new PostString( "contextpath", detailstr );
		vc.add( ps01 );
		vc.add( ps02 );
		vc.add( ps03 );
		SendClient sendclient = new SendClient();
		String result = sendclient.sendText( url, vc );
		//System.err.println( result );
		return result;
	}
	public boolean write( String contextValue, String contextname ){
		try{
			String result = makeXml( contextValue, contextname );
			System.err.println( result );
			if( result.indexOf("-1") == -1 ){
				return true;	
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return false;
	}
	/**
	 * XMLÇÃê∂ê¨
	 */
	public String generateXml( dbDataAccesser access ) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append( "<pageaomount>\n");
		sb.append("<jan_cd>"+access.getData(0,0)+"</jan_cd>\n");
		sb.append("<agt_product_cd>"+access.getData(0, 1)+"</agt_product_cd>\n");
		sb.append("<agt_cd>"+access.getData(0, 2)+"</agt_cd>\n");
		sb.append("<product_type>"+access.getData(0, 3)+"</product_type>\n");
		sb.append("<product_name><![CDATA["+access.getData(0, 4)+"]]></product_name>\n");
		sb.append("<product_sub_title><![CDATA["+access.getData(0, 5)+"]]></product_sub_title>\n");
		sb.append("<catchcopy><![CDATA["+access.getData(0, 6)+"]]></catchcopy>\n");
		sb.append("<price_catch><![CDATA["+access.getData(0, 7)+"]]></price_catch>\n");
		sb.append("<opening><![CDATA["+access.getData(0, 8)+"]]></opening>\n");
		sb.append("<specword><![CDATA["+access.getData(0, 9)+"]]></specword>\n");
		sb.append("<moreinfo><![CDATA["+access.getData(0, 10)+"]]></moreinfo>\n");
		sb.append("<c_coment><![CDATA["+access.getData(0, 11)+"]]></c_coment>\n");
		sb.append("<body><![CDATA["+access.getData(0, 12)+"]]></body>\n");
		sb.append("<havealt>"+access.getData(0, 13)+"</havealt>\n");
		sb.append("<price>"+access.getData(0, 14)+"</price>\n");
		sb.append("<tax>"+access.getData(0, 15)+"</tax>\n");
		sb.append("<sending>"+access.getData(0, 16)+"</sending>\n");
		sb.append("<sending_tax>"+access.getData(0, 17)+"</sending_tax>\n");
		sb.append("<sending_calc_unit>"+access.getData(0, 18)+"</sending_calc_unit>\n");
		sb.append("<first_name><![CDATA["+access.getData(0, 19)+"]]></first_name>\n");
		sb.append("<lastname><![CDATA["+access.getData(0, 20)+"]]></lastname>\n");
		sb.append( "</pageaomount>\n");
		return sb.toString();
	}
	
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		return "SELECT "
    			+"A.JAN_CD,"			//00
				+"A.AGT_PRODUCT_CD,"	//01
				+"A.AGT_CD,"			//02
				+"A.PRODUCT_TYPE_CD,"	//03
				+"A.PRODUCT_NAME,"		//04
				+"A.PRODUCT_SUB_TITLE,"	//05
				+"A.CATCHCOPY,"			//06
				+"A.PRICE_CATCH,"		//07
				+"A.OPEN_DATE,"			//08
				+"A.SPECWORD,"			//09
				+"A.MOREINFO,"			//10
				+"A.C_COMENT,"			//11
				+"A.BODY,"				//12
				+"A.HAVEALT,"			//13
				+"A.PRICE,"				//14
				+"A.TAX,"				//15
				+"A.SENDING,"			//16
				+"A.SENDING_TAX,"		//17
				+"A.SENDING_CALC_UNIT,"	//18
				+"C.FIRST_NAME,"		//19
				+"C.LASTNAME "			//20
     			+" FROM CALLEGEPRODUCT_TBL A,"
     			+"AGENT_TBL C "
     			+"WHERE "
     			+"A.AGT_CD=C.AGT_CD AND "
     			+"A.JAN_CD='" + condition.getProduct_seq() + "'";
	}
}
