package jp.co.lastminute;

import java.io.*;
import java.util.*;

import org.apache.struts.action.ActionForm;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AllotCheckCondition implements Serializable{
	private String product_cd = "";
	private int product_type_cd = 0;
	private int allot = 0;
	//
	//エラーコメント
    protected Vector error_comm = new Vector();
	/**
	 * Constructor for AllotCheckCondition.
	 */
	public AllotCheckCondition() {
		super();
	}


	/**
	 * Returns the allot.
	 * @return int
	 */
	public int getAllot() {
		return allot;
	}

	/**
	 * Returns the product_cd.
	 * @return String
	 */
	public String getProduct_cd() {
		return product_cd;
	}

	/**
	 * Returns the product_type_cd.
	 * @return int
	 */
	public int getProduct_type_cd() {
		return product_type_cd;
	}

	/**
	 * Sets the allot.
	 * @param allot The allot to set
	 */
	public void setAllot(int allot) {
		this.allot = allot;
	}

	/**
	 * Sets the product_cd.
	 * @param product_cd The product_cd to set
	 */
	public void setProduct_cd(String product_cd) {
		this.product_cd = product_cd;
	}

	/**
	 * Sets the product_type_cd.
	 * @param product_type_cd The product_type_cd to set
	 */
	public void setProduct_type_cd(int product_type_cd) {
		this.product_type_cd = product_type_cd;
	}
	/**
	 * Sets the error_comm.
	 * @param error_comm The error_comm to set
	 */
	public void setError_comm(Vector error_comm) {
		this.error_comm = error_comm;
	}
	public void clearError_comm() {
		this.error_comm = null;
		this.error_comm = new Vector();
	}
	public void addError_comm(String error_comm) {
		this.error_comm.add( error_comm );
	}
	public String getViewErrorCopmment( String careturn ){
		String er_comment = "";
		try{
			for(int eindex=0; eindex<this.error_comm.size(); eindex++){
				er_comment += (String)this.error_comm.get(eindex) + careturn;
			}
		}catch(Exception ex){}
		return er_comment;
	}

}