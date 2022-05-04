package jp.co.lastminute;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import java.io.*;
import java.util.*;


/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SeachCondition extends ActionForm implements Serializable{
	private String act = "";
	private String catid = "";
	private String scatid = "";
	private String product_id = "";
	private String agtcode = "";
	private String from = "";
	private String to = "";
	private String days = "";
	private String productioncode = "";
	private String checkindate = "";
	private String checkintime = "";
	private String checkintimecode = "";
	private String maxnumber = "";
	private String minnumber = "";
	private String night = "";
	private String areacode = "";
	private String citycode = "";
	private String title = "";
	private String price = "";
	private String pages = "0";
	private String throwflg = "";
	//////////////////////////////////////
	private String xmlstring = "";
	private String xsltstring = "";
	private String allotment = "";
	//////////////////////////////////////
	//エラーコメント
    protected Vector error_comm = new Vector();
	/**
	 * Returns the act.
	 * @return String
	 */
	public String getAct() {
		return act;
	}

	/**
	 * Returns the agtcode.
	 * @return String
	 */
	public String getAgtcode() {
		return agtcode;
	}

	/**
	 * Returns the areacode.
	 * @return String
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * Returns the catid.
	 * @return String
	 */
	public String getCatid() {
		return catid;
	}

	/**
	 * Returns the checkindate.
	 * @return String
	 */
	public String getCheckindate() {
		return checkindate;
	}

	/**
	 * Returns the citycode.
	 * @return String
	 */
	public String getCitycode() {
		return citycode;
	}

	/**
	 * Returns the days.
	 * @return String
	 */
	public String getDays() {
		return days;
	}

	/**
	 * Returns the from.
	 * @return String
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Returns the night.
	 * @return String
	 */
	public String getNight() {
		return night;
	}

	/**
	 * Returns the product_id.
	 * @return String
	 */
	public String getProduct_id() {
		return product_id;
	}

	/**
	 * Returns the productioncode.
	 * @return String
	 */
	public String getProductioncode() {
		return productioncode;
	}

	/**
	 * Returns the scatid.
	 * @return String
	 */
	public String getScatid() {
		return scatid;
	}

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the to.
	 * @return String
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets the act.
	 * @param act The act to set
	 */
	public void setAct(String act) {
		this.act = act;
	}

	/**
	 * Sets the agtcode.
	 * @param agtcode The agtcode to set
	 */
	public void setAgtcode(String agtcode) {
		this.agtcode = agtcode;
	}

	/**
	 * Sets the areacode.
	 * @param areacode The areacode to set
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * Sets the catid.
	 * @param catid The catid to set
	 */
	public void setCatid(String catid) {
		this.catid = catid;
	}

	/**
	 * Sets the checkindate.
	 * @param checkindate The checkindate to set
	 */
	public void setCheckindate(String checkindate) {
		this.checkindate = checkindate;
	}

	/**
	 * Sets the citycode.
	 * @param citycode The citycode to set
	 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	/**
	 * Sets the days.
	 * @param days The days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * Sets the from.
	 * @param from The from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Sets the night.
	 * @param night The night to set
	 */
	public void setNight(String night) {
		this.night = night;
	}

	/**
	 * Sets the product_id.
	 * @param product_id The product_id to set
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	/**
	 * Sets the productioncode.
	 * @param productioncode The productioncode to set
	 */
	public void setProductioncode(String productioncode) {
		this.productioncode = productioncode;
	}

	/**
	 * Sets the scatid.
	 * @param scatid The scatid to set
	 */
	public void setScatid(String scatid) {
		this.scatid = scatid;
	}

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) throws IOException{
		this.title = getConv2Sjis( title );
	}

	/**
	 * Sets the to.
	 * @param to The to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Returns the pages.
	 * @return String
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * Sets the pages.
	 * @param pages The pages to set
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}

	/**
	 * Returns the checkintime.
	 * @return String
	 */
	public String getCheckintime() {
		return checkintime;
	}

	/**
	 * Sets the checkintime.
	 * @param checkintime The checkintime to set
	 */
	public void setCheckintime(String checkintime) {
		this.checkintime = checkintime;
	}

	/**
	 * Returns the checkintimecode.
	 * @return String
	 */
	public String getCheckintimecode() {
		return checkintimecode;
	}

	/**
	 * Sets the checkintimecode.
	 * @param checkintimecode The checkintimecode to set
	 */
	public void setCheckintimecode(String checkintimecode) {
		this.checkintimecode = checkintimecode;
	}

	/**
	 * Returns the maxnumber.
	 * @return String
	 */
	public String getMaxnumber() {
		return maxnumber;
	}

	/**
	 * Sets the maxnumber.
	 * @param maxnumber The maxnumber to set
	 */
	public void setMaxnumber(String maxnumber) {
		this.maxnumber = maxnumber;
	}

	/**
	 * Returns the minnumber.
	 * @return String
	 */
	public String getMinnumber() {
		return minnumber;
	}

	/**
	 * Sets the minnumber.
	 * @param minnumber The minnumber to set
	 */
	public void setMinnumber(String minnumber) {
		this.minnumber = minnumber;
	}

	/**
	 * Returns the price.
	 * @return String
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Returns the throwflg.
	 * @return String
	 */
	public String getThrowflg() {
		return throwflg;
	}

	/**
	 * Sets the throwflg.
	 * @param throwflg The throwflg to set
	 */
	public void setThrowflg(String throwflg) {
		this.throwflg = throwflg;
	}

	/**
	 * Returns the xmlstring.
	 * @return String
	 */
	public String getXmlstring() {
		return xmlstring;
	}

	/**
	 * Returns the xsltstring.
	 * @return String
	 */
	public String getXsltstring() {
		return xsltstring;
	}

	/**
	 * Sets the xmlstring.
	 * @param xmlstring The xmlstring to set
	 */
	public void setXmlstring(String xmlstring) {
		this.xmlstring = xmlstring;
	}

	/**
	 * Sets the xsltstring.
	 * @param xsltstring The xsltstring to set
	 */
	public void setXsltstring(String xsltstring) {
		this.xsltstring = xsltstring;
	}

	/**
	 * Returns the allotment.
	 * @return String
	 */
	public String getAllotment() {
		return allotment;
	}
	/**
	 * 
	 */
	public int getAllot() {
		try{
			return Integer.parseInt( allotment );
		}catch( Exception ex ){	}
		return 0;
	}
	/**
	 * Sets the allotment.
	 * @param allotment The allotment to set
	 */
	public void setAllotment(String allotment) {
		this.allotment = allotment;
	}
	//変換ルーチン
    private static String getConv2Sjis( String str ) throws IOException {
    	return new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }

	/**
	 * Sets the error_comm.
	 * @param error_comm The error_comm to set
	 */
	public void setError_comm(Vector error_comm) {
		this.error_comm = error_comm;
	}
	public void clearError_comm() {
		this.error_comm = null;
		this.error_comm = new Vector();
	}
	public void addError_comm(String error_comm) {
		this.error_comm.add( error_comm );
	}
	public String getViewErrorCopmment( String careturn ){
		String er_comment = "";
		try{
			for(int eindex=0; eindex<this.error_comm.size(); eindex++){
				er_comment += (String)this.error_comm.get(eindex) + careturn;
			}
		}catch(Exception ex){}
		return er_comment;
	}
}
