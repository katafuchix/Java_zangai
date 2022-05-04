package jp.co.lastminute.Hotel;

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
public class HotelSearchCondition extends SeachCondition implements Serializable{
	public String supnbr = "";
	public String sordtype = "0";
	public String state_cd = "";
	public String city_cd = "";
	public String htlcat = "";
	public String local_area_code = "";
	public String prod_id = "";
	public String lmjcampaign = "";
	
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

	/**
	 * Returns the city_cd.
	 * @return String
	 */
	public String getCity_cd() {
		return city_cd;
	}

	/**
	 * Returns the state_cd.
	 * @return String
	 */
	public String getState_cd() {
		return state_cd;
	}

	/**
	 * Sets the city_cd.
	 * @param city_cd The city_cd to set
	 */
	public void setCity_cd(String city_cd) {
		this.city_cd = city_cd;
	}

	/**
	 * Sets the state_cd.
	 * @param state_cd The state_cd to set
	 */
	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}

	/**
	 * Returns the htlcat.
	 * @return String
	 */
	public String getHtlcat() {
		return htlcat;
	}

	/**
	 * Returns the local_area_code.
	 * @return String
	 */
	public String getLocal_area_code() {
		return local_area_code;
	}

	/**
	 * Returns the supnbr.
	 * @return String
	 */
	public String getSupnbr() {
		return supnbr;
	}

	/**
	 * Sets the htlcat.
	 * @param htlcat The htlcat to set
	 */
	public void setHtlcat(String htlcat) {
		this.htlcat = htlcat;
	}

	/**
	 * Sets the local_area_code.
	 * @param local_area_code The local_area_code to set
	 */
	public void setLocal_area_code(String local_area_code) {
		this.local_area_code = local_area_code;
	}

	/**
	 * Sets the supnbr.
	 * @param supnbr The supnbr to set
	 */
	public void setSupnbr(String supnbr) {
		this.supnbr = supnbr;
	}

	/**
	 * Returns the prod_id.
	 * @return String
	 */
	public String getProd_id() {
		return prod_id;
	}

	/**
	 * Sets the prod_id.
	 * @param prod_id The prod_id to set
	 */
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	/**
	 * Returns the lmjcampaign.
	 * @return String
	 */
	public String getLmjcampaign() {
		return lmjcampaign;
	}

	/**
	 * Sets the lmjcampaign.
	 * @param lmjcampaign The lmjcampaign to set
	 */
	public void setLmjcampaign(String lmjcampaign) {
		this.lmjcampaign = lmjcampaign;
	}

}
