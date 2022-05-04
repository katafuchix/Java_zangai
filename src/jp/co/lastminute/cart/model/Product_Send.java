/*
 * Product_Send_Model.java
 *
 * Created on 2002/03/31, 23:13
 */

package jp.co.lastminute.cart.model;

import java.io.*;
import jp.co.lastminute.common.StateToString;
import jp.co.lastminute.cart.user.model.Profile;
import jp.co.lastminute.cart.user.jdbc.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class Product_Send implements Serializable{
    /**
    SEND_ID              NUMBER(10, 0)    NOT NULL,
    USER_ID              NUMBER(10, 0)    NOT NULL,
    FIRST_NAME           VARCHAR2(30),
    LASTNAME             VARCHAR2(30),
    E_MAIL               VARCHAR2(120)    NOT NULL,
    ORDER_NO             NUMBER(10, 0)    NOT NULL,
    POSTAL_CD            CHAR(7)          NOT NULL,
    都道府県_STATE_CD    NUMBER(10, 0),
    CITY_NAME            VARCHAR2(24),
    ADDRESS              VARCHAR2(80),
    DELIVERＹNAME        VARCHAR2(80),
    TEL_NO               VARCHAR2(20),
    FAX                  VARCHAR2(20),
    MAKE_DATE            DATE,
    UP_DATE              DATE,
    MOBILE_E_MAIL        VARCHAR2(120)    NOT NULL,
    DEL_FLG              NUMBER(1, 0),
    ADDRESSFLG           NUMBER(2, 0),
    SEND_TYPE_ID         NUMBER(2, 0)
     *
     */
    private int SEND_ID;  
    private int USER_ID; 
    private String FIRST_NAME; 
    private String LASTNAME;
    private String E_MAIL; 
    private int ORDER_NO; 
    private String POSTAL_CD; 
    private int  STATE_CD;
    private String CITY_NAME;
    private String ADDRESS;
    private String DELIVERYNAME; 
    private String TEL_NO;
    private String FAX; 
    private String MAKE_DATE;
    private String UP_DATE;
    private String MOBILE_E_MAIL;
    private int DEL_FLG;
    private int ADDRESSFLG;
    private int SEND_TYPE_ID;
    private int exec_flg = 0;
    ////////////////////////////
    private String buildaddress = "";
    private String main_state = "";
    private String main_address = "";
    ///
    private String main_lastname_kana = "";
    private String main_firstname_kana = "";
    private String main_lastname_kanji = "";
    private String main_firstname_kanji = "";
    private String main_postalcode = "";
    
    private String ticketing = "1";
    private String payment_way ="1";
    
    private String PASSWD = "";
    ///////////////
    private String deriver_month = "";
    private String deriver_day = "";
    private String deriver_time = "";
    

    /** Creates new Product_Send_Model */
    public Product_Send() {
        this.SEND_ID = 0;
        this.USER_ID = 0;
        this.FIRST_NAME = "";
        this.LASTNAME ="";
        this.E_MAIL = "";
        this.ORDER_NO =0;
        this.POSTAL_CD = "";
        this.STATE_CD = 0;
        this.CITY_NAME = "";
        this.ADDRESS = "";
        this.DELIVERYNAME = "";
        this.TEL_NO = "";
        this.FAX = "";
        this.MOBILE_E_MAIL = "";
        this.ADDRESSFLG = 0;
        this.SEND_TYPE_ID = 0;
        this.buildaddress = "";
        this.main_state = "";
        this.main_address = "";
        this.main_lastname_kana = "";
        this.main_firstname_kana = "";
        this.main_lastname_kanji = "";
        this.main_firstname_kanji = "";
        this.main_postalcode = "";
        this.ticketing = "1";
        this.payment_way ="1";
        this.PASSWD = "";
        this.deriver_month = "";
    	this.deriver_day = "";
    	this.deriver_time = "";
    }
    /**
     * 配送IDの取得
     * @return String SEND_ID;
     */
    public int getSEND_ID(){
        try{
            return this.SEND_ID;
        }catch(Exception ex){}
        return 0;
    }
    /**
     * ユーザーIDの取得
     * @return int USER_ID
     */
    public int getUSER_ID(){
        try{
            return this.USER_ID;
        }catch(Exception ex){}
        return 0;
    }
    /**
     * ファーストネームを取得する
     * @return String FIRST_NAME;
     */
    public String getFIRST_NAME(){
        try{
            return this.FIRST_NAME;
        }catch(Exception ex){}
        return "";
    }
    /**
     * ラストネームを取得する
     * @param String LASTNAME
     */
    public String getLASTNAME(){
        try{
            return this.LASTNAME;
        }catch(Exception ex){}
        return "";
    }
    /**
     * E_MAILを取得する
     * @return String E_MAIL
     */
    public String getE_MAIL(){
        try{
            return this.E_MAIL;
        }catch(Exception ex){}
        return "";
    }
    /**
     * オーダーNOを取得する
     * @return int ORDERNO
     */
    public int getORDER_NO(){
        try{
            return this.ORDER_NO; 
        }catch(Exception ex){}
        return 0;
    }
    /**
     * 郵便番号を取得する
     * @return String POSTAL_CD
     */
    public String getPOSTAL_CD(){
        try{
            return this.POSTAL_CD;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 都道府県コードを取得する
     * @return int STATE_CD;
     */
    public int getSTATE_CD(){
        try{
            return this.STATE_CD;
        }catch(Exception ex){}
        return 0;
    }
    /**
     * 都市名を取得する
     * @return CITY_NAME;
     */
    public String getCITY_NAME(){
        try{
            return this.CITY_NAME;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 住所を取得する
     * @return String ADDRESS
     */
    public String getADDRESS(){
        try{
            return this.ADDRESS;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 配送者名を保持する
     * @return String DELIVERYNAME
     */
    public String getDELIVERYNAME(){
        try{
            return this.DELIVERYNAME;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 電話番号を取得する
     * @return String TEL_NO
     */
    public String getTEL_NO(){
        try{
            return this.TEL_NO;
        }catch(Exception ex){}
        return "";
    }
    /**
     * FAXを保持する
     * @return String FAX
     */
    public String getFAX(){
        try{
            return this.FAX;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 作成日を保持する
     * @return String MAKE_DATE
     */
    public String getMAKE_DATE(){
        try{
            return this.MAKE_DATE;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 修正日を取得する
     * @return String UP_DATE
     */
    public String getUP_DATE(){
        try{
            return this.UP_DATE;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 携帯電話番号を取得する
     * @return MOBILE_E_MAIL
     */
    public String getMOBILE_E_MAIL(){
        try{
            return this.MOBILE_E_MAIL;
        }catch(Exception ex){}
        return "";
    }
    /**
     * 削除フラグを取得する
     * @return int DEL_FLG
     */
    public int getDEL_FLG(){
        try{
            return this.DEL_FLG;
        }catch(Exception ex){}
        return 0;
    }
    /**
     * 連絡先フラグを取得する
     * @return ADDRESSFLG
     */
    public int getADDRESSFLG(){
        try{
            return this.ADDRESSFLG;
        }catch(Exception ex){}
        return 0;
    }
    /**
     * 配送種別ID
     * @return int SEND_TYPE_ID
     */
    public int  getSEND_TYPE_ID(){
        try{
            return this.SEND_TYPE_ID;
        }catch(Exception ex){}
        return 0;
    }
    /**
     * 配送IDを保持する。
     * @param int sendid
     */
    synchronized public void setSEND_ID( int sendid ){
        this.SEND_ID = sendid;
    }
    /**
     * ユーザーIDを保持する
     * @param int userid
     */
    synchronized public void setUSER_ID(int userid ){
        this.USER_ID = userid;
    }
    /**
     * ファーストネームを保持する
     * @param String firstname
     */
    synchronized public void setFIRST_NAME(String firstname ){
        this.FIRST_NAME = firstname;
    }
    /**
     * ラストネームを保持する
     * @param String firstname
     */
    synchronized public void setLASTNAME(String lastname){
        this.LASTNAME = lastname;
    }
    /**
     * E_MAILを保持する
     * @param String email
     */
    synchronized public void setE_MAIL(String email){
        this.E_MAIL = email;
    }
    /**
     * オーダーNOを保持する。
     */
    synchronized public void setORDER_NO(int orderno){
        this.ORDER_NO = orderno;
    }
    /**
     * 郵便番号を保持する。
     * @param String postalcode
     */
    synchronized public void setPOSTAL_CD(String postalcode){
        this.POSTAL_CD = postalcode;
    }
    /**
     * 塗装府県コードを保持する
     * @param int statecd
     */
    synchronized public void setSTATE_CD(int statecd){
        this.STATE_CD = statecd;
    }
    /**
     * 都市名を保持する
     * @param String cityname
     */
    synchronized public void setCITY_NAME(String cityname){
        this.CITY_NAME = cityname;
    }
    /**
     * 住所を保持する
     * @param Striing address
     */
    synchronized public void setADDRESS(String address){
        this.ADDRESS = address;
    }
    /**
     * 配送者名を保持する
     * @param String deliveryname
     */
    synchronized public void setDELIVERYNAME(String deliveryname){
        this.DELIVERYNAME = deliveryname;
    }
    /**
     * 電話番号を保持する
     * @param String telno
     */
    synchronized public void setTEL_NO(String telno){
        this.TEL_NO = telno;
    }
    /**
     * FAXを保持する
     * @param fax
     */
    synchronized public void setFAX(String fax){
        this.FAX = fax;
    }
    /**
     * 作成日を保持する
     * @param String makedate
     */
    synchronized public void setMAKE_DATE(String makedate){
        this.MAKE_DATE = makedate;
    }
    /**
     * 更新日を保持する
     * @param String update
     */ 
    synchronized public void setUP_DATE(String update){
        this.UP_DATE = update;
    }
    /**
     * 携帯電話を保持する
     * @param String mobile_e_mail
     */
    synchronized public void setMOBILE_E_MAIL(String mobile_e_mail){
        this.MOBILE_E_MAIL = mobile_e_mail;
    }
    /** 
     * デリートフラグを保持する
     * @param int delflag
     */
    synchronized public void setDEL_FLG(int delflag){
        this.DEL_FLG = delflag;
    }
    /**
     * アドレスフラグを保持する
     * @param int addressflg
     */
    synchronized public void setADDRESSFLG(int addressflg){
        this.ADDRESSFLG = addressflg;
    }
    synchronized public void setADDRESSFLG(String addressflg){
        try{
            this.ADDRESSFLG = Integer.parseInt(addressflg);
        }catch(Exception e){
            this.ADDRESSFLG = 0;
        }
    }
    /**
     * 配送種別を保持する
     * @param int sendtype
     */
    synchronized public void setSEND_TYPE_ID(int sendtype){
        this.SEND_TYPE_ID = sendtype;
    }
    
    public synchronized String getXMLdocument() {
        String xmldoc = "<SEND_ID>" + this.getSEND_ID() + "</SEND_ID>\n"
                        + "<PASSWD>" + this.PASSWD + "</PASSWD>\n"
                        + "<USER_ID>" + this.getUSER_ID() + "</USER_ID>\n"
                        + "<FIRST_NAME>" + this.getFIRST_NAME() + "</FIRST_NAME>\n"
                        + "<LASTNAME>" + this.getLASTNAME() + "</LASTNAME>\n"
                        + "<E_MAIL>" + this.getE_MAIL() + "</E_MAIL>\n"
                        + "<ORDER_NO>" + this.getORDER_NO() + "</ORDER_NO>\n"
                        + "<POSTAL_CD>" + this.getPOSTAL_CD() + "</POSTAL_CD>\n"
                        + "<POSTAL_CD_STR><![CDATA[" + getPostalStr( this.getPOSTAL_CD()) + "]]></POSTAL_CD_STR>\n"
                        + "<CITY_NAME>" + this.getCITY_NAME() + "</CITY_NAME>\n"
                        + "<ADDRESS>" + this.getADDRESS() + "</ADDRESS>\n"
                        + "<DELIVERYNAME>" + this.getDELIVERYNAME() + "</DELIVERYNAME>\n"
                        + "<TEL_NO>" + this.getTEL_NO() + "</TEL_NO>\n"
                        + "<FAX>" + this.getFAX() + "</FAX>\n"
                        + "<MAKE_DATE>" + this.getMAKE_DATE() + "</MAKE_DATE>\n"
                        + "<UP_DATE>" + this.getUP_DATE() + "</UP_DATE>\n"
                        + "<MOBILE_E_MAIL>" + this.getMOBILE_E_MAIL() + "</MOBILE_E_MAIL>\n"
                        + "<ADDRESSFLG>" + this.getADDRESSFLG() + "</ADDRESSFLG>\n"
                        + "<MAIN_POSTALCODE>" + this.getMain_postalcode() + "</MAIN_POSTALCODE>\n"
                        + "<MAIN_POSTALCODE_STR><![CDATA[" + getPostalStr( this.getMain_postalcode() ) + "]]></MAIN_POSTALCODE_STR>\n"
                        + "<SEND_TYPE_ID>" + this.getSEND_TYPE_ID() + "</SEND_TYPE_ID>\n"
                        + "<MAIN_STATE>" + this.getMain_state() + "</MAIN_STATE>\n"
                        + "<MAIN_STATE_NAME>" + StateToString.getStateCtr( this.getMain_state()) + "</MAIN_STATE_NAME>\n"
                        + "<MAIN_ADDRESS>" + this.getMain_address() + "</MAIN_ADDRESS>\n" 
                        + "<BUILDADDRESS>" + this.getBuildaddress() + "</BUILDADDRESS>\n"
                        + "<MAIN_LASTNAME_KANA>" + this.getMain_lastname_kana() + "</MAIN_LASTNAME_KANA>\n"
                        + "<MAIN_FIRSTNAME_KANA>" + this.getMain_firstname_kana() + "</MAIN_FIRSTNAME_KANA>\n"
                        + "<MAIN_LASTNAME_KANJI>" + this.getMain_lastname_kanji() + "</MAIN_LASTNAME_KANJI>\n"
                        + "<MAIN_FIRSTNAME_KANJI>" + this.getMain_firstname_kanji() + "</MAIN_FIRSTNAME_KANJI>\n"
                        + "<TICKETING>" + this.getTicketing() + "</TICKETING>\n"
                        + "<PAYMENT_WAY>" + this.getPayment_way() + "</PAYMENT_WAY>\n"
                        + "<DERIVER_MONTH>" + this.getDeriver_month() + "</DERIVER_MONTH>\n"
                        + "<DERIVER_DAY>" + this.getDeriver_day() + "</DERIVER_DAY>\n"
                        + "<DERIVER_TIME>" + this.getDeriver_time() + "</DERIVER_TIME>";
        return xmldoc;
    }
    /**
     * センディング用郵便番号
     */
    private String getPostalStr( String str ){
    	String restr = "";
    	try{
    		restr = str.substring(0, 3) + "-" + str.substring(3);
    	}catch(Exception ex){}
    	return restr;	
    }
    
    /** プロパティ buildaddress の取得メソッド。
     * @return プロパティ buildaddress の値。
     */
    public String getBuildaddress() {
        return buildaddress;
    }
    
    /** プロパティ buildaddress の設定メソッド。
     * @param buildaddress プロパティ buildaddress の新しい値。
     */
    public void setBuildaddress(String buildaddress) {
        this.buildaddress = buildaddress;
    }
    
    /** プロパティ main_address の取得メソッド。
     * @return プロパティ main_address の値。
     */
    public String getMain_address() {
        return main_address;
    }
    
    /** プロパティ main_address の設定メソッド。
     * @param main_address プロパティ main_address の新しい値。
     */
    public void setMain_address(String main_address) {
        this.main_address = main_address;
    }
    
    /** プロパティ main_state の取得メソッド。
     * @return プロパティ main_state の値。
     */
    public String getMain_state() {
        return main_state;
    }
    
    /** プロパティ main_state の設定メソッド。
     * @param main_state プロパティ main_state の新しい値。
     */
    public void setMain_state(String main_state) {
        this.main_state = main_state;
    }
    
    /** プロパティ main_firstname_kana の取得メソッド。
     * @return プロパティ main_firstname_kana の値。
     */
    public String getMain_firstname_kana() {
        return main_firstname_kana;
    }
    
    /** プロパティ main_firstname_kana の設定メソッド。
     * @param main_firstname_kana プロパティ main_firstname_kana の新しい値。
     */
    public void setMain_firstname_kana(String main_firstname_kana) {
        this.main_firstname_kana = main_firstname_kana;
    }
    
    /** プロパティ main_firstname_kanji の取得メソッド。
     * @return プロパティ main_firstname_kanji の値。
     */
    public String getMain_firstname_kanji() {
        return main_firstname_kanji;
    }
    
    /** プロパティ main_firstname_kanji の設定メソッド。
     * @param main_firstname_kanji プロパティ main_firstname_kanji の新しい値。
     */
    public void setMain_firstname_kanji(String main_firstname_kanji) {
        this.main_firstname_kanji = main_firstname_kanji;
    }
    
    /** プロパティ main_lastname_kana の取得メソッド。
     * @return プロパティ main_lastname_kana の値。
     */
    public String getMain_lastname_kana() {
        return main_lastname_kana;
    }
    
    /** プロパティ main_lastname_kana の設定メソッド。
     * @param main_lastname_kana プロパティ main_lastname_kana の新しい値。
     */
    public void setMain_lastname_kana(String main_lastname_kana) {
        this.main_lastname_kana = main_lastname_kana;
    }
    
    /** プロパティ main_lastname_kanji の取得メソッド。
     * @return プロパティ main_lastname_kanji の値。
     */
    public String getMain_lastname_kanji() {
        return main_lastname_kanji;
    }
    
    /** プロパティ main_lastname_kanji の設定メソッド。
     * @param main_lastname_kanji プロパティ main_lastname_kanji の新しい値。
     */
    public void setMain_lastname_kanji(String main_lastname_kanji) {
        this.main_lastname_kanji = main_lastname_kanji;
    }
    
    /** プロパティ main_postalcode の取得メソッド。
     * @return プロパティ main_postalcode の値。
     */
    public String getMain_postalcode() {
        return main_postalcode;
    }
    
    /** プロパティ main_postalcode の設定メソッド。
     * @param main_postalcode プロパティ main_postalcode の新しい値。
     */
    public void setMain_postalcode(String main_postalcode) {
        this.main_postalcode = main_postalcode;
    }
    
    /** プロパティ ticketing の取得メソッド。
     * @return プロパティ ticketing の値。
     */
    public String getTicketing() {
        return ticketing;
    }
    
    /** プロパティ ticketing の設定メソッド。
     * @param ticketing プロパティ ticketing の新しい値。
     */
    public void setTicketing(String ticketing) {
        this.ticketing = ticketing;
    }
    
    /** プロパティ payment_way の取得メソッド。
     * @return プロパティ payment_way の値。
     */
    public String getPayment_way() {
        return payment_way;
    }
    
    /** プロパティ payment_way の設定メソッド。
     * @param payment_way プロパティ payment_way の新しい値。
     */
    public void setPayment_way(String payment_way) {
        this.payment_way = payment_way;
    }
    /**
     * プロファイルの設定
     */
    public void setProfile( Profile profile ){
    	this.main_address = profile.getCITY_NAME();
    	this.buildaddress = profile.getADDRESS();
    	this.MOBILE_E_MAIL = profile.getTEL_NO();
    	this.main_firstname_kana = profile.getNA_KANA();
    	this.main_firstname_kanji = profile.getNA_KANJI();
    	this.main_lastname_kana = profile.getSEI_KANA();
    	this.main_lastname_kanji = profile.getSEI_KANJI();
    	this.main_postalcode = profile.getPOSTAL_CD();
    	this.main_state = "" + profile.getSTATE_CD() + "";
    }
    /**
     * プロファイルの同期
     */
    public Profile getUserProfile( Profile profile, Product_Send productsend){
        //
        if( productsend.getMain_address().length() > 0)			profile.setCITY_NAME( productsend.getMain_address() );
        if( productsend.getBuildaddress().length() > 0)			profile.setADDRESS( productsend.getBuildaddress() );
        if( productsend.getMOBILE_E_MAIL().length() > 0 )   		profile.setTEL_NO( productsend.getMOBILE_E_MAIL() );
        if( productsend.getMain_firstname_kana().length() > 0 )	profile.setNA_KANA( productsend.getMain_firstname_kana() );
        if( productsend.getMain_firstname_kanji().length() > 0 )	profile.setNA_KANJI( productsend.getMain_firstname_kanji() );
        if( productsend.getMain_lastname_kana().length() > 0 )		profile.setSEI_KANA( productsend.getMain_lastname_kana() );
        if( productsend.getMain_lastname_kanji().length() > 0)		profile.setSEI_KANJI( productsend.getMain_lastname_kanji() );
        if( productsend.getMain_postalcode().length() > 0)			profile.setPOSTAL_CD( productsend.getMain_postalcode() );
        if( productsend.getMain_state().length() > 0 ){
            try{
            profile.setSTATE_CD( Integer.parseInt( productsend.getMain_state() ) );
            }catch(Exception e){}
        }
        return profile;
    }
    /**
     *
     */
    public void setPASSWD(String passwd ){
        this.PASSWD = passwd;
    }
    /**
     *
     */
    public String getPASSWD(){
        return this.PASSWD;
    }
	/**
	 * Returns the deriver_day.
	 * @return String
	 */
	public String getDeriver_day() {
		return deriver_day;
	}

	/**
	 * Returns the deriver_month.
	 * @return String
	 */
	public String getDeriver_month() {
		return deriver_month;
	}

	/**
	 * Returns the deriver_time.
	 * @return String
	 */
	public String getDeriver_time() {
		return deriver_time;
	}

	/**
	 * Sets the deriver_day.
	 * @param deriver_day The deriver_day to set
	 */
	public void setDeriver_day(String deriver_day) {
		this.deriver_day = deriver_day;
	}

	/**
	 * Sets the deriver_month.
	 * @param deriver_month The deriver_month to set
	 */
	public void setDeriver_month(String deriver_month) {
		this.deriver_month = deriver_month;
	}

	/**
	 * Sets the deriver_time.
	 * @param deriver_time The deriver_time to set
	 */
	public void setDeriver_time(String deriver_time) {
		this.deriver_time = deriver_time;
	}

	/**
	 * Returns the exec_flg.
	 * @return int
	 */
	public int getExec_flg() {
		return exec_flg;
	}

	/**
	 * Sets the exec_flg.
	 * @param exec_flg The exec_flg to set
	 */
	public void setExec_flg(int exec_flg) {
		this.exec_flg = exec_flg;
	}

}
