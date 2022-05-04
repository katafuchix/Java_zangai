package jp.co.lastminute.cart.Cancell;

import java.io.*;
import java.util.*;
import org.apache.struts.action.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.user.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CancellForm extends ActionForm implements Serializable{
    protected String id = "";
    protected String passward = "";
    protected String order_no = "";
    protected String sub_order_no = "";
 	protected String previewflg = "";
    //
    protected String userid = "";
    protected String passwd = "";
    protected String title = "";
    protected String price = "";
    protected String makedate = "";
    //エラーコメント
    protected Vector error_comm = new Vector();
    
    protected Sub_Order suborder = null;
    protected Product_Send sending = null;
    protected User user = null;
    
	/**
	 * Returns the id.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the order_no.
	 * @return String
	 */
	public String getOrder_no() {
		return order_no;
	}

	/**
	 * Returns the passward.
	 * @return String
	 */
	public String getPassward() {
		return passward;
	}

	/**
	 * Returns the sub_order_no.
	 * @return String
	 */
	public String getSub_order_no() {
		return sub_order_no;
	}

	/**
	 * Sets the id.
	 * @param id The id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the order_no.
	 * @param order_no The order_no to set
	 */
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	/**
	 * Sets the passward.
	 * @param passward The passward to set
	 */
	public void setPassward(String passward) {
		this.passward = passward;
	}

	/**
	 * Sets the sub_order_no.
	 * @param sub_order_no The sub_order_no to set
	 */
	public void setSub_order_no(String sub_order_no) {
		this.sub_order_no = sub_order_no;
	}

	/**
	 * Returns the makedate.
	 * @return String
	 */
	public String getMakedate() {
		return makedate;
	}

	/**
	 * Returns the passwd.
	 * @return String
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * Returns the price.
	 * @return String
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the userid.
	 * @return String
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the makedate.
	 * @param makedate The makedate to set
	 */
	public void setMakedate(String makedate) {
		this.makedate = makedate;
	}

	/**
	 * Sets the passwd.
	 * @param passwd The passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the userid.
	 * @param userid The userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * Returns the previewflg.
	 * @return String
	 */
	public String getPreviewflg() {
		return previewflg;
	}

	/**
	 * Sets the previewflg.
	 * @param previewflg The previewflg to set
	 */
	public void setPreviewflg(String previewflg) {
		this.previewflg = previewflg;
	}

	/**
	 * Returns the suborder.
	 * @return Sub_Order
	 */
	public Sub_Order getSuborder() {
		return suborder;
	}

	/**
	 * Sets the suborder.
	 * @param suborder The suborder to set
	 */
	public void setSuborder(Sub_Order suborder) {
		this.suborder = suborder;
	}

	/**
	 * Returns the user.
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * @param user The user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * Returns the error_comm.
	 * @return String
	 */
	public Vector getError_comm() {
		return error_comm;
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

	/**
	 * Returns the sending.
	 * @return Product_Send
	 */
	public Product_Send getSending() {
		return sending;
	}

	/**
	 * Sets the sending.
	 * @param sending The sending to set
	 */
	public void setSending(Product_Send sending) {
		this.sending = sending;
	}

}
