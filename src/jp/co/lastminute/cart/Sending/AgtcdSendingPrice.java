package jp.co.lastminute.cart.Sending;

import java.io.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AgtcdSendingPrice implements Serializable{
	public String AGT_CD = "";
	public int price 	= 0;
	public int tax = 0;
	public int pax = 0;
	public int sending = 0;
	public int sending_tax = 0;
	public int sending_calc_unit = 0;
	
	public AgtcdSendingPrice(){
	}
	/**
	 * コンストラクタ
	 */
	public AgtcdSendingPrice( String AGT_CD,
								int pax,
								int price,
								int tax,
								int sending,
								int sending_tax,
								int sending_calc_unit){
		this.AGT_CD = AGT_CD;
		this.pax	= pax;
		this.price	= price;
		this.tax	= tax;
		this.sending = sending;
		this.sending_tax = sending_tax;
		this.sending_calc_unit = sending_calc_unit;
	}
	/**
	 * Returns the sending.
	 * @return int
	 */
	public int getSending() {
		return sending;
	}

	/**
	 * Returns the sending_calc_unit.
	 * @return int
	 */
	public int getSending_calc_unit() {
		return sending_calc_unit;
	}

	/**
	 * Returns the sending_tax.
	 * @return int
	 */
	public int getSending_tax() {
		return sending_tax;
	}

	/**
	 * Sets the sending.
	 * @param sending The sending to set
	 */
	public void setSending(int sending) {
		this.sending = sending;
	}

	/**
	 * Sets the sending_calc_unit.
	 * @param sending_calc_unit The sending_calc_unit to set
	 */
	public void setSending_calc_unit(int sending_calc_unit) {
		this.sending_calc_unit = sending_calc_unit;
	}

	/**
	 * Sets the sending_tax.
	 * @param sending_tax The sending_tax to set
	 */
	public void setSending_tax(int sending_tax) {
		this.sending_tax = sending_tax;
	}

	/**
	 * Returns the aGT_CD.
	 * @return String
	 */
	public String getAGT_CD() {
		return AGT_CD;
	}

	/**
	 * Returns the pax.
	 * @return int
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Returns the price.
	 * @return int
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns the tax.
	 * @return int
	 */
	public int getTax() {
		return tax;
	}

	/**
	 * Sets the aGT_CD.
	 * @param aGT_CD The aGT_CD to set
	 */
	public void setAGT_CD(String aGT_CD) {
		AGT_CD = aGT_CD;
	}

	/**
	 * Sets the pax.
	 * @param pax The pax to set
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Sets the tax.
	 * @param tax The tax to set
	 */
	public void setTax(int tax) {
		this.tax = tax;
	}

}
