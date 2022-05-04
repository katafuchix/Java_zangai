package jp.co.lastminute.maintenance.xmlstruct;

import java.io.*;
import jp.co.yobrain.util.text.StringUtil;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Plan implements Serializable {
	public int catid = 0;
	public int scatid = 0;
	public String title = "";
	public String scatid_catch_copy = "";
	public String scatid_description = "";
	public Plan(){	
	}
	/**
	 * XMLジェネレート
	 */
	synchronized public String generateXml() throws Exception{
		return  "<plan>\n<catid>" + this.catid + "</catid>\n"
				+"<scatid>" + this.scatid + "</scatid>\n"
				+"<title><![CDATA[" + replaceInString( this.title ) + "]]></title>\n"
				+"<scatid_catch_copy><![CDATA[" + replaceInString( this.scatid_catch_copy ) + "]]></scatid_catch_copy>\n"
				+"<scatid_description><![CDATA[" + replaceInString( this.scatid_description ) + "]]></scatid_description>\n</plan>";
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
