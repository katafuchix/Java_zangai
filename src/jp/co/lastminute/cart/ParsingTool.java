package jp.co.lastminute.cart;

import java.io.*;
import java.util.*;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class ParsingTool {
	private final static String BUY_PROP_TAG = "<BUY_PROP>";
	private final static String BUY_PROP_TAG_END = "</BUY_PROP>";
	
	public static BuyPropDivid dividBuyProp( String str){
		try{
			BuyPropDivid divid = new BuyPropDivid();
			String[] result = removeBuyProp( str );
			while( (result[0].length() != 0) ){
				divid.addBuyProp( result[0] );
				result = removeBuyProp( result[1] );
			}
			divid.setOthers( result[1] );
			return divid;
		}catch(Exception ex){	ex.printStackTrace();
		}
		return null;
	}
	/**
	 * BUY_PROPを分離する
	 */
	public static String[] removeBuyProp( String str ){
		String[] reStr = {"", ""};
		String buyprop = "";
		String others = "";	
		int startpositon = str.indexOf( BUY_PROP_TAG );
		int endpositon = str.indexOf( BUY_PROP_TAG_END );
		if(( startpositon != -1)&&( endpositon != -1)){
			buyprop = str.substring( startpositon + BUY_PROP_TAG.length(), endpositon);
			others = str.substring(0, startpositon)
				   + str.substring( endpositon + BUY_PROP_TAG_END.length() );
			reStr[ 0 ] = buyprop.trim();
			reStr[ 1 ] = others.trim();
		}else{
			reStr[ 1 ] = str;
		}
		return reStr;
	}
	/**
	 * XMLをドキュメントにするメソッド
	 */
	public static Document XMLToDoc(Reader in) {
		BufferedReader reader = null;
		Document doc = null;
		try {
			reader = new BufferedReader(in);
			SAXBuilder builder = new SAXBuilder();
			doc = builder.build(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * ヘッダーの取得
	 */
	public static String getHeader(String str) {
		String xmlversion1 = "<?xml version=\"1.0\"";
		String xmlversion = "<?xml version=\"1.0\" encoding=\"Shift_JIS\"?>";
		if (str.indexOf("<ORDER>") == -1) {
			str = "<ORDER>" + str + "</ORDER>";
		}
		if (str.indexOf(xmlversion1) == -1) {
			str = xmlversion + str;
		}
		return str;
	}
}
