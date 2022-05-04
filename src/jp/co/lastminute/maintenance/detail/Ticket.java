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
public class Ticket extends Detail implements PageDetail, Serializable{

	protected static final String detailstr = ContextProperty._ticket_product_Dir + "/";
	
	
	public Ticket(){
	}
	public Ticket( DataSource ds) {
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
		sb.append("<product_seq>"+access.getData(0,0)+"</product_seq>\n");
		sb.append("<jan_cd>"+access.getData(0, 1)+"</jan_cd>\n");
		sb.append("<group_cd>"+access.getData(0, 2)+"</group_cd>\n");
		sb.append("<ticket_type>"+access.getData(0, 3)+"</ticket_type>\n");
		sb.append("<agt_cd>"+access.getData(0, 4)+"</agt_cd>\n");
		sb.append("<product_type>"+access.getData(0, 5)+"</product_type>\n");
		sb.append("<type_names><![CDATA["+access.getData(0, 6)+"]]></type_names>\n");
		sb.append("<product_name><![CDATA["+access.getData(0, 7)+"]]></product_name>\n");
		sb.append("<product_sub_title><![CDATA["+access.getData(0, 8)+"]]></product_sub_title>\n");
		sb.append("<catchcopy><![CDATA["+access.getData(0, 9)+"]]></catchcopy>\n");
		sb.append("<price_catch><![CDATA["+access.getData(0, 10)+"]]></price_catch>\n");
		sb.append("<opening><![CDATA["+access.getData(0,11)+"]]></opening>\n");
		sb.append("<actors><![CDATA["+access.getData(0, 12)+"]]></actors>\n");
		sb.append("<produce><![CDATA["+access.getData(0, 13)+"]]></produce>\n");
		sb.append("<play_place><![CDATA["+access.getData(0, 14)+"]]></play_place>\n");
		sb.append("<pointofaccess><![CDATA["+access.getData(0, 15)+"]]></pointofaccess>\n");
		sb.append("<accesspoint_url><![CDATA["+access.getData(0, 16)+"]]></accesspoint_url>\n");
		sb.append("<description><![CDATA["+access.getData(0, 17)+"]]></description>\n");
		sb.append("<image_01_url><![CDATA["+access.getData(0, 18)+"]]></image_01_url>\n");
		sb.append("<image_01_url_1><![CDATA["+access.getData(0, 19)+"]]></image_01_url_1>\n");
		sb.append("<image_01_url_2><![CDATA["+access.getData(0, 20)+"]]></image_01_url_2>\n");
		sb.append("<image_01_url_3><![CDATA["+access.getData(0, 21)+"]]></image_01_url_3>\n");
		sb.append("<stop_flg>"+access.getData(0, 22)+"</stop_flg>\n");
		sb.append("<linkedurl><![CDATA["+access.getData(0, 23)+"]]></linkedurl>\n");
		sb.append("<event_names><![CDATA["+access.getData(0, 24)+"]]></event_names>\n");
		sb.append("<type_names><![CDATA["+access.getData(0, 25)+"]]></type_names>\n");
		sb.append("<first_name><![CDATA["+access.getData(0, 26)+"]]></first_name>\n");
		sb.append("<lastname><![CDATA["+access.getData(0, 27)+"]]></lastname>\n");
		sb.append( "</pageaomount>\n");
		return sb.toString();
	}
	
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		return "SELECT "
    			+"A.PRODUCT_SEQ,"		//00
        		+"A.JAN_CD,"			//01
        		+"A.GROUP_CD,"			//02
        		+"A.TICKET_TYPE,"		//03
        		+"A.AGT_CD,"			//04
        		+"A.PRODUCT_TYPE,"		//05
        		+"A.TYPE_NAMES,"		//06
        		+"A.PRODUCT_NAME,"		//07
        		+"A.PRODUCT_SUB_TITLE,"	//08
        		+"A.CATCHCOPY,"			//09
        		+"A.PRICE_CATCH,"		//10
        		+"A.OPENING,"			//11
        		+"A.ACTORS,"			//12
        		+"A.PRODUCE,"			//13
        		+"A.PLAY_PLACE,"		//14
        		+"A.POINTOFACCESS,"		//15
        		+"A.ACCESSPOINT_URL,"	//16
        		+"A.DESCRIPTION,"		//17
        		+"A.IMAGE_01_URL,"		//18
        		+"A.IMAGE_01_URL_1,"	//19
        		+"A.IMAGE_01_URL_2,"	//20
        		+"A.IMAGE_01_URL_3,"	//21
        		+"A.STOP_FLG,"			//22
        		+"A.LINKEDURL,"			//23
        		+"A.EVENT_NAMES,"		//24
        		+"B.TYPE_NAMES,"		//25
        		+"C.FIRST_NAME,"		//26
        		+"C.LASTNAME "			//27
     			+" FROM TICKETSACTIVTY A,"
     			+"TICKETACTIVITY_TYPE_MASTAER B,"
     			+"AGENT_TBL C "
     			+"WHERE A.TICKET_TYPE=B.TICKET_TYPE AND "
     			+"A.PRODUCT_TYPE=B.PRODUCT_TYPE AND "
     			+"A.PRODUCT_SEQ=" + condition.getProduct_seq() + " AND "
     			+"A.AGT_CD=C.AGT_CD ";
	}
}
