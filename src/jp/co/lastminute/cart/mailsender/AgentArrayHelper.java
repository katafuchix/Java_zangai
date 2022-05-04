package jp.co.lastminute.cart.mailsender;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.model.*;

import jp.co.lastminute.supply.Agent;
import jp.co.lastminute.supply.jdbc.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class AgentArrayHelper {
	public static final int NEWARRAY = 1;
	public static final int ADDARRAY = 0;
	public static final int NOTACTION = -1;
	public static final String DEMIRITER_STR = "----body----";
	protected static final String header_Str = "------------------------------------------------------------\n"
												+ "下記、問合せ番号はラストミニット社の問合せ番号です\n"
												+ "------------------------------------------------------------\n";
	protected static final String footer_Str = "\n------------------------------------------------------------\n"
												+ "do something lastminute.com\n"
												+ "http：//www.lastminute.co.jp\n";
	public static final String Otder_DEMI = "\n<<------------------------------------------------------->>\n";
	
	public static final String templateMailPath = Property.BASE_DIR + "/dir/";
	public static final String templateHeaderPath = Property.BASE_DIR + "/header/";	
	/**
	 * メールの送信判断
	 */
	protected static Vector AddAgentArray( Vector rows, Order order, DataSource dss, String classname ){
		for(int i=0; i<order.getSubOrderCount(); i++){
			Sub_Order sub = order.getSubOrder( i );
			rows = isAdd( rows, order, sub, dss, classname);
		}
		return rows;
	}
	/**
	 * メールの送信判断
	 */
	private static Vector isAdd( Vector rows, Order order, Sub_Order sub, DataSource dss, String classname ){
		try{
			if( sub.getStatus() == Constants.NOT_CONFIRM_ ){
				for(int i=0; i<rows.size(); i++){	
					MailArray array = (MailArray)rows.get( i );
					if( array.isGoodToAdd( sub ) ){
						array.addSuborder( sub, array.getXsltpath( sub ) );
						rows.set( i, array );
						return rows;
					}	
				}
				///追加ロジック
				MailArray array = (MailArray)Class.forName( classname ).newInstance();
				if( array.init( order, sub, dss) ){
					rows.add( array );
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return rows;
	}
	/**
	 * XSL + XMLの取得
	 */
	protected static String getparseString( String xml, String xsltPath){
		try{
			Reader xmlReader = new StringReader("<MAILORDER>" + xml + "</MAILORDER>");
			StringWriter sw = new StringWriter();
			InputStreamReader xslReader =new InputStreamReader(new FileInputStream(xsltPath),"Shift_JIS");
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			transformer = tFactory.newTransformer(new StreamSource(xslReader));
			transformer.setOutputProperty("omit-xml-declaration", "yes");
			transformer.setOutputProperty("indent", "yes");	
			transformer.transform(new StreamSource(xmlReader),	new StreamResult((java.io.Writer) sw));
			return sw.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	/**
	 * エージェントの取得
	 */
	synchronized protected static Agent getAgent(String agt_cd, DataSource dss ) {
		dbAdapterAgent db = new dbAdapterAgent(dss);
		Agent reagent = db.findAgentOnlyStr(agt_cd);
		db = null;
		return reagent;
	}
}
