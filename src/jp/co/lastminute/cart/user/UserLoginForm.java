package jp.co.lastminute.cart.user;

import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.model.Order;
import org.apache.struts.action.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class UserLoginForm extends ActionForm{
    private String e_mail = ""; //メールアドレス（ユーザID）
    private String passwd = ""; //パスワード
	//エラーコメント
    protected Vector error_comm = new Vector();
    /** プロパティ E_Mail の取得メソッド。
     * @return プロパティ e_mail の値。
     */
    public String getE_mail() {
        return e_mail;
    }    

    /** プロパティ E_Mail の設定メソッド。
     * @param e_mail プロパティ e_mail の新しい値。
     */
    public void setE_mail(String e_mail) throws IOException {
        this.e_mail = new String( e_mail.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }
    
    /** プロパティ Passwd の取得メソッド。
     * @return プロパティ Passwd の値。
     */
    public String getPasswd() {
        return passwd;
    }
    
    /** プロパティ Passwd の設定メソッド。
     * @param passwd プロパティ passwd の新しい値。
     */
    public void setPasswd(String passwd)  throws IOException {
        this.passwd = new String( passwd.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
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
	 * エラーコメントの移動
	 */
	public void modifyErrComment( Order order ){
		try{
			Vector order_er_ = order.getError_comm();
			for(int i=0; i<order_er_.size(); i++ ){
				String err_com = (String)order_er_.get( i );
				addError_comm( err_com );
			}
		}catch(Exception ex){}
	} 
}