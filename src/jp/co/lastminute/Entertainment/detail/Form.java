package jp.co.lastminute.Entertainment.detail;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import jp.co.lastminute.Entertainment.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Form extends EntertainmentSearchCondition{
	private String allotmentxml = "";

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

}

