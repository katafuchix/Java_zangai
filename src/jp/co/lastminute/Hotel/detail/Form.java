package jp.co.lastminute.Hotel.detail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import jp.co.lastminute.Hotel.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Form extends HotelSearchCondition{
	private String productaltpxml = "";
	private String selectionStr = "";
	private String allotmentxml = "";
	private String formHooter = "";
	//
	public String numberOfAdults = "";
	public String numberOfMales = ""; //
	public String numberOfFemales = "";
	public String numberOfKids = "";
	public String numberOfYoji1 = "";
	public String numberOfYoji2 = "";
	public String nights = "0";
	public String numberOfRooms = "1";
	//
	public String supnbr = "";
	public String checkInDate = "";
	public String checkInMonth = "";
	public String checkInDay = "";
	public String roomtype_cd = "";
	public String meal_cd = "00";
	//
	public String price = "";
	/**
	 * Returns the allotmentxml.
	 * @return String
	 */
	public String getAllotmentxml() {
		return allotmentxml;
	}

	/**
	 * Sets the allotmentxml.
	 * @param allotmentxml The allotmentxml to set
	 */
	public void setAllotmentxml(String allotmentxml) {
		this.allotmentxml = allotmentxml;
	}

	/**
	 * Returns the productaltpxml.
	 * @return String
	 */
	public String getProductaltpxml() {
		return productaltpxml;
	}

	/**
	 * Sets the productaltpxml.
	 * @param productaltpxml The productaltpxml to set
	 */
	public void setProductaltpxml(String productaltpxml) {
		this.productaltpxml = productaltpxml;
	}

	/**
	 * Returns the selectionStr.
	 * @return String
	 */
	public String getSelectionStr() {
		return selectionStr;
	}

	/**
	 * Sets the selectionStr.
	 * @param selectionStr The selectionStr to set
	 */
	public void setSelectionStr(String selectionStr) {
		this.selectionStr = selectionStr;
	}

	/**
	 * Returns the formHooter.
	 * @return String
	 */
	public String getFormHooter() {
		return formHooter;
	}

	/**
	 * Sets the formHooter.
	 * @param formHooter The formHooter to set
	 */
	public void setFormHooter(String formHooter) {
		this.formHooter = formHooter;
	}

}

