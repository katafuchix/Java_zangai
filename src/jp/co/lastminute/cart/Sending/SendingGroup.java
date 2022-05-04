package jp.co.lastminute.cart.Sending;

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
public class SendingGroup implements Serializable{
	private Vector groups = new Vector();
	private String AGT_CD = "";
	private int price = 0;
	private int tax = 0;
	private int sending = 0;
	private int sending_tax = 0;
	private int counter = 0;
	private int sending_calc_unit = 0;
	
	//カウンター
	public SendingGroup(){
	}
	
	/**
	 * 追加
	 */
	public void add( AgtcdSendingPrice prices ){
		groups.add( prices );
	}
	/**
	 * 計算
	 */
	public void calc(){
		for(int i=0; i<groups.size(); i++){
			AgtcdSendingPrice price = (AgtcdSendingPrice)groups.get( i );
			int[] Prices = getSendingPrice( price.price,
											price.tax,
											price.sending,
											price.sending_tax,
											price.pax,
											price.sending_calc_unit );
			this.price 	+= Prices[0];
			this.tax 		+= Prices[1];
			this.sending	+= Prices[2];
			this.sending_tax	+= Prices[3];
			this.counter		+= Prices[4];
		}
	}
	/**
	 * 配送料計算
	 */
	public int[] getSendingPrice(int _price,
								int _tax,
								int _sending, 
								int _sending_tax, 
								int _pax, 
								int _sending_calc_unit){
		int[] reInt = {0, 0, 0, 0, 0};
		int counter_p = _pax * _sending_calc_unit;
		int totalcounter =  (( counter_p - (counter_p%100) )/100 ) + 1;
		reInt[0] = _price * _pax;
		reInt[1] = _tax * _pax;
		reInt[2] = totalcounter * _sending;
		reInt[3] = totalcounter * _sending_tax;
		reInt[4] = totalcounter;
		return reInt;
	}
	/**
	 * Returns the aGT_CD.
	 * @return String
	 */
	public String getAGT_CD() {
		return AGT_CD;
	}

	/**
	 * Returns the counter.
	 * @return int
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Returns the groups.
	 * @return Vector
	 */
	public Vector getGroups() {
		return groups;
	}

	/**
	 * Returns the price.
	 * @return int
	 */
	public int getPrice() {
		return price;
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
	 * Sets the counter.
	 * @param counter The counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Sets the groups.
	 * @param groups The groups to set
	 */
	public void setGroups(Vector groups) {
		this.groups = groups;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(int price) {
		this.price = price;
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
	 * Sets the tax.
	 * @param tax The tax to set
	 */
	public void setTax(int tax) {
		this.tax = tax;
	}

}
