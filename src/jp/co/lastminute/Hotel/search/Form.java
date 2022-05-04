package jp.co.lastminute.Hotel.search;

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
	public int totalpages = 0;

	/**
	 * Returns the totalpages.
	 * @return int
	 */
	public int getTotalpages() {
		return totalpages;
	}

	/**
	 * Sets the totalpages.
	 * @param totalpages The totalpages to set
	 */
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
}
