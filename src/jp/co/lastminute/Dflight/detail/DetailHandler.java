package jp.co.lastminute.Dflight.detail;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.lastminute.Dflight.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class DetailHandler implements Serializable{
	
	public static String detailXml( Vector rows ){
		System.err.print( "Detail Handler VECOTR SIZE=" + rows.size() );
		String reStr = "";
		try{
			StringBuffer sub = new StringBuffer();
			sub.append("<Dflightdetail>\n");
			dbDataAccesser acesser = new dbDataAccesser( rows );
			
			sub.append("<product_name><![CDATA[" + acesser.getData(0, 0) + "]]></product_name>\n");
			sub.append("<flight_no><![CDATA[" + acesser.getData(0, 1) + "]]></flight_no>\n");
			sub.append("<departture><![CDATA[" + acesser.getData(0, 2) + "]]></departture>\n");
			sub.append("<arrival><![CDATA[" + acesser.getData(0, 3) + "]]></arrival>\n");
			sub.append("<agt_cd><![CDATA[" + acesser.getData(0, 4) + "]]></agt_cd>\n");
			sub.append("<commerce_comment><![CDATA[" + acesser.getData(0, 5) + "]]></commerce_comment>\n");
			sub.append("<price_comment><![CDATA[" + acesser.getData(0, 6) + "]]></price_comment>\n");
			sub.append("<agent_member_cd><![CDATA[" + acesser.getData(0, 7) + "]]></agent_member_cd>\n");
			sub.append("<product_type_cd><![CDATA[" + acesser.getData(0, 8) + "]]></product_type_cd>\n");
			sub.append("<allot_type><![CDATA[" + acesser.getData(0, 9) + "]]></allot_type>\n");
			sub.append("<agt_cd><![CDATA[" + acesser.getData(0, 10) + "]]></agt_cd>\n");
			sub.append("<cabin_class><![CDATA[" + acesser.getData(0, 11) + "]]></cabin_class>\n");
			sub.append("<booking_class><![CDATA[" + acesser.getData(0, 12) + "]]></booking_class>\n");
			sub.append("<max_member><![CDATA[" + acesser.getData(0, 13) + "]]></max_member>\n");
			sub.append("<min_member><![CDATA[" + acesser.getData(0, 14) + "]]></min_member>\n");
			sub.append("<stop_flg><![CDATA[" + acesser.getData(0, 15) + "]]></stop_flg>\n");
			sub.append("<price_base><![CDATA[" + acesser.getData(0, 16) + "]]></price_base>\n");
			sub.append("<price_child><![CDATA[" + acesser.getData(0, 17) + "]]></price_child>\n");
			sub.append("<price_infant><![CDATA[" + acesser.getData(0, 18) + "]]></price_infant>\n");
			sub.append("<price_normal><![CDATA[" + acesser.getData(0, 19) + "]]></price_normal>\n");
			sub.append("<price_pat_01><![CDATA[" + acesser.getData(0, 20) + "]]></price_pat_01>\n");
			sub.append("<price_pat_02><![CDATA[" + acesser.getData(0, 21) + "]]></price_pat_02>\n");
			sub.append("<price_pat_03><![CDATA[" + acesser.getData(0, 22) + "]]></price_pat_03>\n");
			sub.append("<price_pat_04><![CDATA[" + acesser.getData(0, 23) + "]]></price_pat_04>\n");
			sub.append("<price_pat_05><![CDATA[" + acesser.getData(0, 24) + "]]></price_pat_05>\n");
			sub.append("<price_pat_06><![CDATA[" + acesser.getData(0, 25) + "]]></price_pat_06>\n");
			sub.append("<price_pat_07><![CDATA[" + acesser.getData(0, 26) + "]]></price_pat_07>\n");
			sub.append("<price_pat_08><![CDATA[" + acesser.getData(0, 27) + "]]></price_pat_08>\n");
			sub.append("<price_pat_09><![CDATA[" + acesser.getData(0, 28) + "]]></price_pat_09>\n");
			sub.append("<price_pat_10><![CDATA[" + acesser.getData(0, 29) + "]]></price_pat_10>\n");
			sub.append("<allot_seq><![CDATA[" + acesser.getData(0, 30) + "]]></allot_seq>\n");
			sub.append("<target_hour><![CDATA[" + acesser.getData(0, 31) + "]]></target_hour>\n");
			sub.append("<date_target><![CDATA[" + acesser.getData(0, 32) + "]]></date_target>\n");
			sub.append("<weekday><![CDATA[" + acesser.getData(0, 33) + "]]></weekday>\n");
			sub.append("<sku_id><![CDATA[" + acesser.getData(0, 34) + "]]></sku_id>\n");
			sub.append("<jan_cd><![CDATA[" + acesser.getData(0, 35) + "]]></jan_cd>\n");
			sub.append("<allot_number><![CDATA[" + acesser.getData(0, 36) + "]]></allot_number>\n");
			sub.append("<before_start_days><![CDATA[" + acesser.getData(0, 37) + "]]></before_start_days>\n");
			sub.append("<open_column_flg><![CDATA[" + acesser.getData(0, 38) + "]]></open_column_flg>\n");
			sub.append("<last_sales_days><![CDATA[" + acesser.getData(0, 39) + "]]></last_sales_days>\n");
			sub.append("<last_sales_times><![CDATA[" + acesser.getData(0, 40) + "]]></last_sales_times>\n");
			sub.append("<departturecity><![CDATA[" + acesser.getData(0, 41) + "]]></departturecity>\n");
			sub.append("<arrivalcity><![CDATA[" + acesser.getData(0, 42) + "]]></arrivalcity>\n");
			sub.append("<first_name><![CDATA[" + acesser.getData(0, 43) + "]]></first_name>\n");
			sub.append("<target_hour_com><![CDATA[" + acesser.getData(0, 44) + "]]></target_hour_com>\n");
			sub.append("<lastsale>" + Property.getLastsaleTime( acesser.getData(0, 32), Property.endsale ) + "</lastsale>\n");
			sub.append("<endsale>" + Property.getCansellTime( acesser.getData(0, 32), Property.cancellsale ) + "</endsale>\n");
			sub.append("</Dflightdetail>\n");
			reStr = sub.toString();
			System.err.println( reStr );
		}catch(Exception ex){}
		return reStr;
	}
	private void test(){
		try{
		Vector vc =new Vector();
		StringReader sr = new StringReader( detailXml( vc ) ); 
		InputSource is = new InputSource(sr);
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		Element root = document.getDocumentElement();
		NodeList nodelist = root.getChildNodes();
		Element el =  (Element)nodelist;
		String el2 = el.getAttribute("arrivalcity" );
		//el2.getNodeValue();
		//NodeList list = document.getElementsByTagName("arrivalcity");
		
		//NodeList list = document.getElementsByTagName("arrivalcity");
		//Element el = (Element) list.item(0);
		//el.getAttribute( "arrivalcity");
		//el.get
		
		
		
		}catch(Exception ex){}
		
		
		
		
		
		
	}
}
