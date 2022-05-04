package jp.co.lastminute.maintenance.model;

import java.io.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class WindowList implements Serializable{
	public String jan_cd = "";
	public String agt_product_cd = "";
	public String product_name = "";
	public String agt_cd = "";
	public String price = "";
	public String allotnum = "";
	public String havealt = "0";
	public String first_name = "";

	public WindowList(){
		
	}
	/**
	 * Returns the agt_cd.
	 * @return String
	 */
	public String getAgt_cd() {
		return agt_cd;
	}

	/**
	 * Returns the agt_product_cd.
	 * @return String
	 */
	public String getAgt_product_cd() {
		return agt_product_cd;
	}

	/**
	 * Returns the allotnum.
	 * @return String
	 */
	public String getAllotnum() {
		return allotnum;
	}

	/**
	 * Returns the first_name.
	 * @return String
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Returns the havealt.
	 * @return String
	 */
	public String getHavealt() {
		return havealt;
	}

	/**
	 * Returns the jan_cd.
	 * @return String
	 */
	public String getJan_cd() {
		return jan_cd;
	}

	/**
	 * Returns the price.
	 * @return String
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Returns the product_name.
	 * @return String
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * Sets the agt_cd.
	 * @param agt_cd The agt_cd to set
	 */
	public void setAgt_cd(String agt_cd) {
		this.agt_cd = agt_cd;
	}

	/**
	 * Sets the agt_product_cd.
	 * @param agt_product_cd The agt_product_cd to set
	 */
	public void setAgt_product_cd(String agt_product_cd) {
		this.agt_product_cd = agt_product_cd;
	}

	/**
	 * Sets the allotnum.
	 * @param allotnum The allotnum to set
	 */
	public void setAllotnum(String allotnum) {
		this.allotnum = allotnum;
	}

	/**
	 * Sets the first_name.
	 * @param first_name The first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * Sets the havealt.
	 * @param havealt The havealt to set
	 */
	public void setHavealt(String havealt) {
		this.havealt = havealt;
	}

	/**
	 * Sets the jan_cd.
	 * @param jan_cd The jan_cd to set
	 */
	public void setJan_cd(String jan_cd) {
		this.jan_cd = jan_cd;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Sets the product_name.
	 * @param product_name The product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

}
