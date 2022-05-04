package jp.co.lastminute.Gift;

import java.io.*;
import java.io.Serializable;
import jp.co.lastminute.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class GiftSearchCondition extends SeachCondition implements Serializable{
	public String sordtype = "";
	//ïœä∑ÉãÅ[É`Éì
    private static String getConv2Sjis( String str ) throws IOException {
    	return new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }

	/**
	 * Returns the sordtype.
	 * @return String
	 */
	public String getSordtype() {
		return sordtype;
	}

	/**
	 * Sets the sordtype.
	 * @param sordtype The sordtype to set
	 */
	public void setSordtype(String sordtype) {
		this.sordtype = sordtype;
	}

}
