package jp.co.lastminute.maintenance.xmlstruct;

import java.io.*;
import jp.co.yobrain.util.ParseFormat;

import jp.co.yobrain.util.text.StringUtil;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Product implements Serializable{
	private ParseFormat pf = null;
	public String prod_id = "";
	public String name = "";
	
	public String comment = "";
	public String price = "";
	
	public String agt_cd = "";
	public String supnbr = "";
	
	
	public Product(){
	}
	/**
	 * XMLジェネレート
	 */
	synchronized public String generateXml() throws Exception{
		return  "<product>\n<prod_id>" + this.prod_id + "</prod_id>\n"
				+"<agt_cd>" + this.agt_cd + "</agt_cd>\n"
				+"<supnbr>" + this.supnbr + "</supnbr>\n"
				+"<name><![CDATA[" + replaceInString( this.name ) + "]]></name>\n"
				+"<copy>"
				+"<comment><![CDATA[" + replaceInString(  this.comment ) + "]]></comment>\n"
				+"<price><![CDATA[" + replaceInString(  this.price ) + "]]></price>\n"
				+"<pricestr><![CDATA[" + pf.ToPriceFormat( this.price ) + "]]></pricestr>\n"
				+"</copy>\n</product>";
	}
	private static String replaceInString( String str ){
		str = StringUtil.replaceInString( str, "&","&amp;" );
		str = StringUtil.replaceInString( str,"<","&lt;" );
		str = StringUtil.replaceInString( str,">","&gt;" );
		str = StringUtil.replaceInString( str,"'","&rsquo" );
		str = StringUtil.replaceInString( str,"\"","&rdquo" );
		return str;
	}
}
