package jp.co.lastminute.Restrant.node;

import java.io.*;
import jp.co.lastminute.Restrant.Property;
import jp.co.yobrain.util.DataFormat;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RStocks implements Serializable{
	private DataFormat df = null;
	public int allotid = 0;
	public String restaurant = "";
	public int timeid = 0;
	public int skuid = 0;
	public int allotdate = 0;
	public int allottent = 0;
	public String lastsale = "";
	public String endsalse = "";
	public RStocks(){
		
	}
	/**
	 * ’Ç‰Á‰Â”\‚©”Û‚©‚ð”»’f
	 */
	public static boolean isAddok( RStocks rsocks ){
		if(( rsocks.allotid > 0 )&&( rsocks.restaurant.length() > 0 )&&
		( rsocks.skuid > 0 )&&( rsocks.allotdate > 0 )&&
		( rsocks.allottent > 0 )){
			return true;
		}
		return false;
	}
	synchronized public String getXmlString(){
		return "<ALLOTID>" + allotid + "</ALLOTID>\n"
				+ "<RESTAURANT>" + restaurant + "</RESTAURANT>\n"
				+ "<TIMEID>" + timeid + "</TIMEID>\n"
				+ "<SKUID>" + skuid + "</SKUID>\n"
				+ "<ALLOTDATE>" + allotdate + "</ALLOTDATE>\n"
				+ "<ALLOTTENT>" + allottent + "</ALLOTTENT>\n"
				+ "<ENDSALE>" + getEndsale() + "</ENDSALE>\n"
				+ "<LASTSALE>" + getLastsale() + "</LASTSALE>";
	}
	public String getLastsale(){
		try{
			return df.DelToDate( "" + allotdate + "", 1) + Property.cancellsale;
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	public String getEndsale(){
		try{
			return df.getNowDate(0, true) + Property.endsale;
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}	
}
