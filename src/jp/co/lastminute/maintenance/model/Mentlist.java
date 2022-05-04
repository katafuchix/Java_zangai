package jp.co.lastminute.maintenance.model;

import java.io.Serializable;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Mentlist implements Serializable {
	private Categoryselect select = null;
	private Catid_small small = null;
	private String product_name = "";
	/**
	 * コンストラクタ
	 */
	public Mentlist(){	
	}
	/**
	 * イニシャライズ
	 */
	public void init( Categoryselect select, Catid_small small ){
		this.select 	= select;
		this.small 	= small;		
	}
	/**
	 * Returns the select.
	 * @return Categoryselect
	 */
	public Categoryselect getSelect() {
		return select;
	}

	/**
	 * Returns the small.
	 * @return Catid_small
	 */
	public Catid_small getSmall() {
		return small;
	}

	/**
	 * Sets the select.
	 * @param select The select to set
	 */
	public void setSelect(Categoryselect select) {
		this.select = select;
	}

	/**
	 * Sets the small.
	 * @param small The small to set
	 */
	public void setSmall(Catid_small small) {
		this.small = small;
	}

	/**
	 * Returns the product_name.
	 * @return String
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * Sets the product_name.
	 * @param product_name The product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

}
