package jp.co.lastminute.cart.Cancell;

import java.io.*;
import org.apache.struts.action.*;
import jp.co.lastminute.cart.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ModifyForm extends CancellForm implements Serializable{
	protected String pax = "";
	protected String action_type = "";
	protected Sub_Order temp_suborder = null;
	protected Product_Send temp_sending = null;
	
	public ModifyForm(){
		
	}
	/**
	 * Returns the pax.
	 * @return String
	 */
	public String getPax() {
		return pax;
	}
	/**
	 * Sets the pax.
	 * @param pax The pax to set
	 */
	public void setPax(String pax) {
		this.pax = pax;
	}
	/**
	 * Returns the temp_suborder.
	 * @return Sub_Order
	 */
	public Sub_Order getTemp_suborder() {
		return temp_suborder;
	}

	/**
	 * Sets the temp_suborder.
	 * @param temp_suborder The temp_suborder to set
	 */
	public void setTemp_suborder(Sub_Order temp_suborder) {
		this.temp_suborder = temp_suborder;
	}

	/**
	 * Returns the action_type.
	 * @return String
	 */
	public String getAction_type() {
		return action_type;
	}

	/**
	 * Sets the action_type.
	 * @param action_type The action_type to set
	 */
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	/**
	 * Returns the temp_sending.
	 * @return Product_Send
	 */
	public Product_Send getTemp_sending() {
		return temp_sending;
	}

	/**
	 * Sets the temp_sending.
	 * @param temp_sending The temp_sending to set
	 */
	public void setTemp_sending(Product_Send temp_sending) {
		this.temp_sending = temp_sending;
	}
	
	synchronized public String getSelectedPax(){
		String reStr = "";
		if( this.suborder.getPax() == 1){
			return reStr;
		}
		for(int i=1; i<= this.suborder.getPax(); i++){
			String istr = "" + i + "";
			if( this.pax.equals( istr )){
				reStr += "<option valiue='" + istr + "' selected>"+ istr +"</option>\n";
			}else{
				reStr += "<option valiue='" + istr + "'>"+ istr +"</option>\n";
			}
		}
		return reStr;
	}
}
