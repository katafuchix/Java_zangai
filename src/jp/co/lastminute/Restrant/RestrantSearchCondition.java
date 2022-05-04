package jp.co.lastminute.Restrant;

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
public class RestrantSearchCondition extends SeachCondition implements Serializable{
	protected String restrantname = "";
	protected String restrantid = "";
	protected String cookingtype = "";
	protected String pax = "";
	/**
	 * Returns the cookingtype.
	 * @return String
	 */
	public String getCookingtype() {
		return cookingtype;
	}

	/**
	 * Returns the pax.
	 * @return String
	 */
	public String getPax() {
		return pax;
	}

	/**
	 * Returns the restrantid.
	 * @return String
	 */
	public String getRestrantid() {
		return restrantid;
	}

	/**
	 * Returns the restrantname.
	 * @return String
	 */
	public String getRestrantname() {
		return restrantname;
	}

	/**
	 * Sets the cookingtype.
	 * @param cookingtype The cookingtype to set
	 */
	public void setCookingtype(String cookingtype) {
		this.cookingtype = cookingtype;
	}

	/**
	 * Sets the pax.
	 * @param pax The pax to set
	 */
	public void setPax(String pax) {
		this.pax = pax;
	}

	/**
	 * Sets the restrantid.
	 * @param restrantid The restrantid to set
	 */
	public void setRestrantid(String restrantid) {
		this.restrantid = restrantid;
	}

	/**
	 * Sets the restrantname.
	 * @param restrantname The restrantname to set
	 */
	public void setRestrantname(String restrantname) {
		this.restrantname = restrantname;
	}

}
