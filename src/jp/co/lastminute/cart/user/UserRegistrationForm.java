package jp.co.lastminute.cart.user;

import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.user.model.*;
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
public class UserRegistrationForm extends ActionForm{
    private String e_mail = "";         //メールアドレス（ユーザID）
    private String passwd = "";         //パスワード
    private String oncepasswd = "";     //パスワード再入力
    private String html_mail_ok = "1";   //HTMLメールの受信フラグ
    private String sei_kanji = "";      //名前漢字-姓
    private String na_kanji = "";       //名前漢字-名
    private String sex = "";            //性別
    private String postal_cda = "";     //郵便番号上
    private String postal_cdb = "";     //郵便番号下
    //
    private String sei_kana = "";
    private String na_kana = "";
    private String state_cd = "";
    private String city_name = "";
    private String address = "";
    private String tel_no = "";
    private String birthyear = "";
    private String birthmonth = "";
    private String throwflg = "";
    //
    private int user_id = 0;
	//エラーコメント
    protected Vector error_comm = new Vector();
    
    public UserRegistrationForm(){
    }
    /**
     * 
     */
    private String Conv2Sjis(String str) throws IOException {
        return new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }
    /** プロパティ E_mail の取得メソッド。
     * @param return プロパティ e_mail の値。
     */
    public String getE_mail() {
        return e_mail;
    }
    
    /** プロパティ E_mail の設定メソッド。
     * @param return プロパティ e_mail の新しい値。
     */
    public void setE_mail(String e_mail) throws IOException {
        this.e_mail = Conv2Sjis( e_mail ) ;
    }
    
    /** プロパティ E_mail の取得メソッド。
     * @param return プロパティ Passwd の値。
     */
    public String getPasswd() {
        return passwd;
    }
    
    /** プロパティ Passwd の設定メソッド。
     * @param return プロパティ passwd の新しい値。
     */
    public void setPasswd(String passwd)  throws IOException {
        this.passwd = Conv2Sjis( passwd ) ;
    }
    
    /** プロパティ Oncepasswd の取得メソッド。
     * @param return プロパティ oncepasswd の値。
     */
    public String getOncepasswd() {
        return oncepasswd;
    }
    
    /** プロパティ Oncepasswd の設定メソッド。
     * @param return プロパティ oncepasswd の新しい値。
     */
    public void setOncepasswd(String oncepasswd) throws IOException {
        this.oncepasswd = Conv2Sjis( oncepasswd ) ;
    }
    
    /** プロパティ Html_mail_ok の取得メソッド。
     * @param return プロパティ html_mail_ok の値。
     */
    public String getHtml_mail_ok() {
        return html_mail_ok;
    }
    
    /** プロパティ Html_mail_ok の設定メソッド。
     * @param Html_Mail_Ok プロパティ Html_Mail_Ok の新しい値。
     */
    public void setHtml_mail_ok(String html_mail_ok) throws IOException {
        this.html_mail_ok = Conv2Sjis( html_mail_ok ) ;
    }
    /** プロパティ Sei_kanji の取得メソッド。
     * @param return プロパティ sei_kanji の値。
     */
    public String getSei_kanji() {
        return sei_kanji;
    }
    
    /** プロパティ Sei_Kanji の設定メソッド。
     * @param return プロパティ sei_kanji の新しい値。
     */
    public void setSei_kanji(String sei_kanji) throws IOException {
        this.sei_kanji = Conv2Sjis( sei_kanji ) ;
    }
    
    /** プロパティ Na_kanji の取得メソッド。
     * @param return プロパティ na_kanji の値。
     */
    public String getNa_kanji() {
        return na_kanji;
    }
    
    /** プロパティ Na_kanji の設定メソッド。
     * @param return プロパティ na_kanji の新しい値。
     */
    public void setNa_kanji(String na_kanji) throws IOException {
        this.na_kanji = Conv2Sjis( na_kanji ) ;
    }
    
    /** プロパティ Sex の取得メソッド。
     * @param return プロパティ sex の値。
     */
    public String getSex() {
        return sex;
    }
    
    /** プロパティ Sex の設定メソッド。
     * @param return プロパティ sex の新しい値。
     */
    public void setSex(String sex) throws IOException {
        this.sex = Conv2Sjis( sex );
    }
    
    /** プロパティ Postal_CdA の取得メソッド。
     * @param return プロパティ postal_cda の値。
     */
    public String getPostal_cda() {
        return postal_cda;
    }
    
    /** プロパティ Postal_cda の設定メソッド。
     * @param return プロパティ postal_cda の新しい値。
     */
    public void setPostal_cda(String postal_cda) throws IOException {
        this.postal_cda = Conv2Sjis( postal_cda ) ;
    }
    
    /** プロパティ Postal_cdb の取得メソッド。
     * @param return プロパティ postal_cdb の値。
     */
    public String getPostal_cdb() {
        return postal_cdb;
    }
    
    /** プロパティ Postal_cdb の設定メソッド。
     * @param return プロパティ postal_cdb の新しい値。
     */
    public void setPostal_cdb(String postal_cdb) throws IOException {
        this.postal_cdb = Conv2Sjis( postal_cdb ) ;
    }
    
    /** プロパティ address の取得メソッド。
     * @return プロパティ address の値。
     */
    public String getAddress() {
        return address;
    }
    
    /** プロパティ address の設定メソッド。
     * @param address プロパティ address の新しい値。
     */
    public void setAddress(String address) throws IOException {
        this.address = Conv2Sjis( address ) ;
    }
    
    /** プロパティ sei_kana の取得メソッド。
     * @return プロパティ sei_kana の値。
     */
    public String getSei_kana() {
        return sei_kana;
    }
    
    /** プロパティ sei_kana の設定メソッド。
     * @param sei_kana プロパティ sei_kana の新しい値。
     */
    public void setSei_kana(String sei_kana) throws IOException {
        this.sei_kana = Conv2Sjis( sei_kana ) ;
    }
    
    /** プロパティ na_kana の取得メソッド。
     * @return プロパティ na_kana の値。
     */
    public String getNa_kana() {
        return na_kana;
    }
    
    /** プロパティ na_kana の設定メソッド。
     * @param na_kana プロパティ na_kana の新しい値。
     */
    public void setNa_kana(String na_kana) throws IOException {
        this.na_kana = Conv2Sjis( na_kana ) ;
    }
    
    /** プロパティ city_name の取得メソッド。
     * @return プロパティ city_name の値。
     */
    public String getCity_name() {
        return city_name;
    }
    
    /** プロパティ city_name の設定メソッド。
     * @param city_name プロパティ city_name の新しい値。
     */
    public void setCity_name(String city_name) throws IOException {
        this.city_name = Conv2Sjis( city_name ) ;
    }
    
    /** プロパティ state_cd の取得メソッド。
     * @return プロパティ state_cd の値。
     */
    public String getState_cd() {
        return state_cd;
    }
    
    /** プロパティ state_cd の設定メソッド。
     * @param state_cd プロパティ state_cd の新しい値。
     */
    public void setState_cd(String state_cd) {
        this.state_cd = state_cd;
    }
    
    /** プロパティ tel_no の取得メソッド。
     * @return プロパティ tel_no の値。
     */
    public String getTel_no() {
        return tel_no;
    }
    
    /** プロパティ tel_no の設定メソッド。
     * @param tel_no プロパティ tel_no の新しい値。
     */
    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }
    
    /** プロパティ birthmonth の取得メソッド。
     * @return プロパティ birthmonth の値。
     */
    public String getBirthmonth() {
        return birthmonth;
    }
    
    /** プロパティ birthmonth の設定メソッド。
     * @param birthmonth プロパティ birthmonth の新しい値。
     */
    public void setBirthmonth(String birthmonth) {
        this.birthmonth = birthmonth;
    }
    
    /** プロパティ birthyear の取得メソッド。
     * @return プロパティ birthyear の値。
     */
    public String getBirthyear() {
        return birthyear;
    }
    
    /** プロパティ birthyear の設定メソッド。
     * @param birthyear プロパティ birthyear の新しい値。
     */
    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }
    

	/**
	 * Returns the throwflg.
	 * @return String
	 */
	public String getThrowflg() {
		return throwflg;
	}

	/**
	 * Sets the throwflg.
	 * @param throwflg The throwflg to set
	 */
	public void setThrowflg(String throwflg) {
		this.throwflg = throwflg;
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
		//System.err.println( this.error_comm.size() );
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
	/**
	 * Returns the user_id.
	 * @return int
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user_id.
	 * @param user_id The user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setUser_id(String user_id) {
		try{
			this.user_id = Integer.parseInt( user_id );
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	public void setUserProfile( User us ){
		User_Tbl usertbl = us.getUser();
		Profile profile = us.getProfile();
		this.user_id = usertbl.getUSER_ID();
		this.e_mail = usertbl.getE_MAIL();
		this.html_mail_ok = "1";
		this.sei_kanji = profile.getSEI_KANJI();
		this.sei_kana = profile.getSEI_KANA();
		this.na_kanji = profile.getNA_KANJI();
		this.na_kana = profile.getNA_KANA();
		this.sex = "" + profile.getSEX() + "";
		this.postal_cda = profile.getPOSTAL_CD().substring(0, 3);
		this.postal_cdb = profile.getPOSTAL_CD().substring( 3 );
		this.state_cd = "" + profile.getSTATE_CD() + "";
		this.city_name = profile.getCITY_NAME();
		this.address = profile.getADDRESS();
		this.tel_no = profile.getTEL_NO();
		String birth = "" + profile.getBIRTH_DAY() + "";
		if( birth.length() == 6 ){
			this.birthyear = birth.substring(0, 4);
			this.birthmonth = birth.substring( 4 );
		}
	}
}
