package jp.co.lastminute.cart;

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
public class BuyPropDivid implements Serializable{
	private String others = "";
	private Vector buyprops = null;
	
	public BuyPropDivid(){
		buyprops = new Vector();
	}
	public void addBuyProp( String str ){
		buyprops.add( str );
	}
	public int getSize(){
		return buyprops.size();	
	}
	/**
	 * Returns the buyprops.
	 * @return Vector
	 */
	public Vector getBuyprops() {
		return buyprops;
	}

	/**
	 * Returns the others.
	 * @return String
	 */
	public String getOthers() {
		return others;
	}

	/**
	 * Sets the buyprops.
	 * @param buyprops The buyprops to set
	 */
	public void setBuyprops(Vector buyprops) {
		this.buyprops = buyprops;
	}

	/**
	 * Sets the others.
	 * @param others The others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}

}
