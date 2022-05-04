package jp.co.lastminute.cart;


import java.io.*;
import java.util.*;
import org.apache.struts.action.*;

import javax.servlet.*;
import javax.servlet.http.*;

import jp.co.lastminute.cart.form.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;

import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.yobrain.util.DataFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class cartForm extends ActionForm {
	//カートアクション
	protected String order_NO = "";
    protected String sub_order_no = "";
    protected String action_type = "";
    protected String product_type_cd = "";
    protected String agtcode = "";
    protected String throwflg = "";
	protected String previewflg = "";
	
	//プロダクトセンド
    protected String lastname = "";
    //
    protected String lastname_1 = "";
    protected String lastname_2 = "";
    //
    protected String first_name = "";
    //
    protected String first_name1 = "";
    protected String first_name2 = "";
    //
    protected String state_cd = "";
    protected String postal_cd = "";
    //
    protected String postal_cd1 = "";
    protected String postal_cd2 = "";
    //
    protected String city_name = "";
    protected String address = "";
    protected String deliveryname = "";
    protected String tel_no= "";
    protected String fax ="";
    protected String mobile_e_mail= "";
    protected String addressflg = "1";
    ///////
    protected String buildaddress = "";
    protected String main_state = "";
    protected String main_address = "";
    //////
    protected String main_lastname_kana = "";
    protected String main_firstname_kana = "";
    protected String main_lastname_kanji = "";
    protected String main_firstname_kanji = "";
    protected String main_postalcode = "";
    //
    protected String main_postalcode1 = "";
    protected String main_postalcode2 = "";
    //
    //////
    protected String ticketing = "1";
    protected String payment_way ="1";
    //////
    protected String update_flg = "";
	
	///カードアクション
	protected String card_No = "";
    protected String expire = "";
    protected String expireMonth = "";
    protected String first_Name = "";
    protected String card_type = "";
    
    protected String sending_month = "";
    protected String sending_day = "";
    protected String sending_time = "";
    //エラーコメント
    protected Vector error_comm = new Vector();
    //配送先エラー配列
    public String getError_Color( int posion ){
    	if( ch_send_array[ posion ] == 0 ){
    		return Constants.colors[0];
    	}
    	return Constants.colors[1];
    }
    public String getError_Color( int posion1, int posion2 ){
    	if(( ch_send_array[ posion1 ] == 0 )&&( ch_send_array[ posion2 ] == 0 )){
    		return Constants.colors[0];
    	}
    	return Constants.colors[1];
    }
    public int[] ch_send_array = new int[33];
    //配送先の初期設定// 配送日の初期値設定：
    
    public void setProfile( User user ){
    	setProfileOnly( user );
    	setSendingPredata();
    }
    public void setProfile( User user, String sending_date ){
    	setProfileOnly( user );
    	sending_month = sending_date.substring(4, 6);
    	sending_day = sending_date.substring(4, 6);
    }
    public void setSendingPredata(){
    	//日付の設定 本日より三日後でかつ、本日が、金.土.日曜日の場合は、4日後、
    	DataFormat df = null;
	    	try{
	    	String nowdate = df.getNowDate(0, true);
	    	String sending_date = "";
	    	if( df.getWeekDay( nowdate ) == 0 ){
	    		sending_date = df.AddToDate(nowdate,  4);
	    	}else if( df.getWeekDay( nowdate ) == 5 ){
	    		sending_date = df.AddToDate(nowdate,  5);
	    	}else if( df.getWeekDay( nowdate ) == 6 ){
	    		sending_date = df.AddToDate(nowdate,  4);
	    	}else{
	    		sending_date = df.AddToDate(nowdate,  3);
	    	}
	    	sending_month = sending_date.substring(4, 6);
    		sending_day = sending_date.substring(6, 8);
    	}catch( Exception ex){
	    }
    }
    
    public void setProfileOnly( User user ){
    	User_Tbl user_tbl = (User_Tbl)user.getUser();
    	Profile profile = (Profile)user.getProfile();

    	this.mobile_e_mail= profile.getTEL_NO();
    	///////
    	this.buildaddress = profile.getADDRESS();
    	this.main_state = "" + profile.getSTATE_CD() + "";
    	this.main_address = profile.getCITY_NAME();
    	//////
    	this.main_lastname_kana = profile.getSEI_KANA();
    	this.main_firstname_kana = profile.getNA_KANA();
    	this.main_lastname_kanji = profile.getSEI_KANJI();
    	this.main_firstname_kanji = profile.getNA_KANJI();
  		this.main_postalcode = profile.getPOSTAL_CD();
    	//
    	if( main_postalcode.length() > 6 ){
	    	try{
		    	this.main_postalcode1 = main_postalcode.substring(0, 3);
		    	this.main_postalcode2 = main_postalcode.substring(3);
	    	}catch(Exception ex){}
    	}
    	
    }
	//カートアクション
	/** プロパティ action_type の取得メソッド。
     * @return プロパティ action_type の値。
     */
    public String getAction_type() {
        return action_type;
    }    
    
    /** プロパティ action_type の設定メソッド。
     * @param action_type プロパティ action_type の新しい値。
     */
    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }    
    
    /** プロパティ order_NO の取得メソッド。
     * @return プロパティ order_NO の値。
     */
    public String getOrder_NO() {
        return order_NO;
    }    
    
    /** プロパティ order_NO の設定メソッド。
     * @param order_NO プロパティ order_NO の新しい値。
     */
    public void setOrder_NO(String order_NO) {
        this.order_NO = order_NO;
    }
    
    /** プロパティ sub_order_no の取得メソッド。
     * @return プロパティ sub_order_no の値。
     */
    public String getSub_order_no() {
        return sub_order_no;
    }
    
    /** プロパティ sub_order_no の設定メソッド。
     * @param sub_order_no プロパティ sub_order_no の新しい値。
     */
    public void setSub_order_no(String sub_order_no) {
        this.sub_order_no = sub_order_no;
    }
    
    /** プロパティ idx の取得メソッド。
     * @return プロパティ idx の値。
     */
    public String getProduct_type_cd() {
        return product_type_cd;
    }
    
    /** プロパティ idx の設定メソッド。
     * @param idx プロパティ idx の新しい値。
     */
    public void setProduct_type_cd(String product_type_cd) {
        this.product_type_cd = product_type_cd;
    }
    public String getBuildaddress(){
        return this.buildaddress;
    }
    public void setBuildaddress(String buildaddress)throws IOException{
        this.buildaddress = getConv2Sjis( buildaddress ) ;
    }
    public void setBuildaddress(String buildaddress, boolean flg)throws IOException{
        this.buildaddress = buildaddress ;
    }
    public String getMain_state(){
        return this.main_state;
    }
    public void setMain_state(String main_state)throws IOException{
        this.main_state = getConv2Sjis( main_state );
    }
    public String getMain_address(){
        return this.main_address;
    }
    public void setMain_address(String main_address)throws IOException{
        this.main_address = getConv2Sjis( main_address );
    }
    public void setMain_address(String main_address, boolean flg)throws IOException{
        this.main_address = main_address ;
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
    public void setAddress(String address) throws IOException{
        this.address = getConv2Sjis( address );
    }
    public void setAddress(String address, boolean flg) throws IOException{
        this.address = address;
    }
    
    /** プロパティ addressflg の取得メソッド。
     * @return プロパティ addressflg の値。
     */
    public String getAddressflg() {
        return addressflg;
    }
    
    /** プロパティ addressflg の設定メソッド。
     * @param addressflg プロパティ addressflg の新しい値。
     */
    public void setAddressflg(String addressflg){
        this.addressflg = addressflg;
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
    public void setCity_name(String city_name) throws IOException{
        this.city_name = getConv2Sjis( city_name );
    }
    public void setCity_name(String city_name, boolean flg) throws IOException{
        this.city_name = city_name;
    }
    
    /** プロパティ deliveryname の取得メソッド。
     * @return プロパティ deliveryname の値。
     */
    public String getDeliveryname() {
        return deliveryname;
    }
    
    /** プロパティ deliveryname の設定メソッド。
     * @param deliveryname プロパティ deliveryname の新しい値。
     */
    public void setDeliveryname(String deliveryname) throws IOException{
        this.deliveryname = getConv2Sjis( deliveryname );
    }
    public void setDeliveryname(String deliveryname, boolean flg) throws IOException{
        this.deliveryname = deliveryname;
    }
    
    /** プロパティ fax の取得メソッド。
     * @return プロパティ fax の値。
     */
    public String getFax() {
        return fax;
    }
    
    /** プロパティ fax の設定メソッド。
     * @param fax プロパティ fax の新しい値。
     */
    public void setFax(String fax) throws IOException{
        this.fax =getConv2Sjis( fax );
    }
    
    /** プロパティ first_name の取得メソッド。
     * @return プロパティ first_name の値。
     */
    public String getFirst_name() {
        return first_name;
    }
    
    /** プロパティ first_name の設定メソッド。
     * @param first_name プロパティ first_name の新しい値。
     */
    public void setFirst_name(String first_name) throws IOException{
        this.first_name = getConv2Sjis( first_name );
    }
    public void setFirst_name(String first_name, boolean flg) throws IOException{
        this.first_name = first_name;
    }
    /** プロパティ lastname の取得メソッド。
     * @return プロパティ lastname の値。
     */
    public String getLastname() {
        return lastname;
    }
    
    /** プロパティ lastname の設定メソッド。
     * @param lastname プロパティ lastname の新しい値。
     */
    public void setLastname(String lastname) throws IOException{
        this.lastname = getConv2Sjis( lastname );
    }
    public void setLastname(String lastname, boolean flg) throws IOException{
        this.lastname = lastname;
    }
    
    /** プロパティ mobile_e_mail の取得メソッド。
     * @return プロパティ mobile_e_mail の値。
     */
    public String getMobile_e_mail() {
        return mobile_e_mail;
    }
    
    /** プロパティ mobile_e_mail の設定メソッド。
     * @param mobile_e_mail プロパティ mobile_e_mail の新しい値。
     */
    public void setMobile_e_mail(String mobile_e_mail) throws IOException{
        this.mobile_e_mail = getConv2Sjis( mobile_e_mail );
    }
    public void setMobile_e_mail(String mobile_e_mail, boolean flg) throws IOException{
        this.mobile_e_mail = mobile_e_mail ;
    }
    /** プロパティ postal_cd の取得メソッド。
     * @return プロパティ postal_cd の値。
     */
    public String getPostal_cd() {
        return postal_cd;
    }
    
    /** プロパティ postal_cd の設定メソッド。
     * @param postal_cd プロパティ postal_cd の新しい値。
     */
    public void setPostal_cd(String postal_cd) throws IOException{
        this.postal_cd = getConv2Sjis( postal_cd );
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
    public void setTel_no(String tel_no) throws IOException{
        this.tel_no = getConv2Sjis( tel_no );
    }
    public void setTel_no(String tel_no, boolean flg) throws IOException{
        this.tel_no = tel_no;
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
    ////////////////////////////////////////////////////////////////////////////////
    /** プロパティ main_firstname_kana の取得メソッド。
     * @return プロパティ main_firstname_kana の値。
     */
    public String getMain_firstname_kana() {
        return main_firstname_kana;
    }
    
    /** プロパティ main_firstname_kana の設定メソッド。
     * @param main_firstname_kana プロパティ main_firstname_kana の新しい値。
     */
    public void setMain_firstname_kana(String main_firstname_kana) throws IOException{
        this.main_firstname_kana = getConv2Sjis( main_firstname_kana ) ;
    }
    public void setMain_firstname_kana(String main_firstname_kana, boolean flg) throws IOException{
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
    public void setMain_firstname_kanji(String main_firstname_kanji) throws IOException{
        this.main_firstname_kanji = getConv2Sjis( main_firstname_kanji ) ;
    }
    public void setMain_firstname_kanji(String main_firstname_kanji, boolean flg) throws IOException{
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
    public void setMain_lastname_kana(String main_lastname_kana) throws IOException{
        this.main_lastname_kana = getConv2Sjis( main_lastname_kana );
    }
    public void setMain_lastname_kana(String main_lastname_kana, boolean flg ) throws IOException{
        this.main_lastname_kana = main_lastname_kana ;
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
    public void setMain_lastname_kanji(String main_lastname_kanji) throws IOException{
        this.main_lastname_kanji = getConv2Sjis( main_lastname_kanji );
    }
    public void setMain_lastname_kanji(String main_lastname_kanji, boolean flg ) throws IOException{
        this.main_lastname_kanji = main_lastname_kanji ;
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
    
    /** プロパティ main_postalcode1 の取得メソッド。
     * @return プロパティ main_postalcode1 の値。
     */
    public String getMain_postalcode1() {
        return main_postalcode1;
    }
    
    /** プロパティ main_postalcode1 の設定メソッド。
     * @param main_postalcode1 プロパティ main_postalcode1 の新しい値。
     */
    public void setMain_postalcode1(String main_postalcode1) {
        this.main_postalcode1 = main_postalcode1;
    }
    
    /** プロパティ main_postalcode2 の取得メソッド。
     * @return プロパティ main_postalcode2 の値。
     */
    public String getMain_postalcode2() {
        return main_postalcode2;
    }
    
    /** プロパティ main_postalcode2 の設定メソッド。
     * @param main_postalcode2 プロパティ main_postalcode2 の新しい値。
     */
    public void setMain_postalcode2(String main_postalcode2) {
        this.main_postalcode2 = main_postalcode2;
    }
    
    /** プロパティ postal_cd1 の取得メソッド。
     * @return プロパティ postal_cd1 の値。
     */
    public String getPostal_cd1() {
        return postal_cd1;
    }
    
    /** プロパティ postal_cd1 の設定メソッド。
     * @param postal_cd1 プロパティ postal_cd1 の新しい値。
     */
    public void setPostal_cd1(String postal_cd1) {
        this.postal_cd1 = postal_cd1;
    }
    
    /** プロパティ postal_cd2 の取得メソッド。
     * @return プロパティ postal_cd2 の値。
     */
    public String getPostal_cd2() {
        return postal_cd2;
    }
    
    /** プロパティ postal_cd2 の設定メソッド。
     * @param postal_cd2 プロパティ postal_cd2 の新しい値。
     */
    public void setPostal_cd2(String postal_cd2) {
        this.postal_cd2 = postal_cd2;
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
    
    /** プロパティ first_name1 の取得メソッド。
     * @return プロパティ first_name1 の値。
     */
    public String getFirst_name1() {
        return first_name1;
    }
    
    /** プロパティ first_name1 の設定メソッド。
     * @param first_name1 プロパティ first_name1 の新しい値。
     */
    public void setFirst_name1(String first_name1) throws IOException{
        this.first_name1 = getConv2Sjis( first_name1 );
    }
    public void setFirst_name1(String first_name1, boolean flg) throws IOException{
        this.first_name1 = first_name1 ;
    }
    
    /** プロパティ first_name2 の取得メソッド。
     * @return プロパティ first_name2 の値。
     */
    public String getFirst_name2() {
        return first_name2;
    }
    
    /** プロパティ first_name2 の設定メソッド。
     * @param first_name2 プロパティ first_name2 の新しい値。
     */
    public void setFirst_name2(String first_name2) throws IOException{
        this.first_name2 = getConv2Sjis( first_name2 );
    }
    public void setFirst_name2(String first_name2, boolean flg) throws IOException{
        this.first_name2 = first_name2;
    }
    
    /** プロパティ lastname_1 の取得メソッド。
     * @return プロパティ lastname_1 の値。
     */
    public String getLastname_1() {
        return lastname_1;
    }
    
    /** プロパティ lastname_1 の設定メソッド。
     * @param lastname_1 プロパティ lastname_1 の新しい値。
     */
    public void setLastname_1(String lastname_1) throws IOException{
        this.lastname_1 = getConv2Sjis( lastname_1 );
    }
    public void setLastname_1(String lastname_1, boolean flg ) throws IOException{
        this.lastname_1 = lastname_1 ;
    }
    
    /** プロパティ lastname_2 の取得メソッド。
     * @return プロパティ lastname_2 の値。
     */
    public String getLastname_2() {
        return lastname_2;
    }
    
    /** プロパティ lastname_2 の設定メソッド。
     * @param lastname_2 プロパティ lastname_2 の新しい値。
     */
    public void setLastname_2(String lastname_2) throws IOException{
        this.lastname_2 = getConv2Sjis( lastname_2 );
    }
    public void setLastname_2(String lastname_2, boolean flg ) throws IOException{
        this.lastname_2 = lastname_2 ;
        return;
    }
    
    /** プロパティ update_flg の取得メソッド。
     * @return プロパティ update_flg の値。
     */
    public String getUpdate_flg() {
        return update_flg;
    }
    
    /** プロパティ update_flg の設定メソッド。
     * @param update_flg プロパティ update_flg の新しい値。
     */
    public void setUpdate_flg(String update_flg) {
        this.update_flg = update_flg;
    }
    
    //カード
        /** プロパティ Card_No の取得メソッド。
     * @return プロパティ Card_No の値。
     */
    public String getCard_No() {
        return card_No;
    }    

    /** プロパティ Card_No の設定メソッド。
     * @param Card_No プロパティ Card_No の新しい値。
     */
    public void setCard_No(String Card_No) throws IOException {
        this.card_No = getConv2Sjis( Card_No );
    }
    
    /** プロパティ Card_type の取得メソッド。
     * @return プロパティ Card_type の値。
     */
    public String getCard_type() {
        return card_type;
    }
    
    /** プロパティ Card_type の設定メソッド。
     * @param Card_type プロパティ Card_type の新しい値。
     */
    public void setCard_type(String Card_type)  throws IOException {
        this.card_type = getConv2Sjis( Card_type );
    }
    
    /** プロパティ Expire の取得メソッド。
     * @return プロパティ Expire の値。
     */
    public String getExpire() {
        return expire;
    }
    
    /** プロパティ Expire の設定メソッド。
     * @param Expire プロパティ Expire の新しい値。
     */
    public void setExpire(String Expire)  throws IOException {
        this.expire = getConv2Sjis( Expire );
    }
    
    /** プロパティ ExpireMonth の取得メソッド。
     * @return プロパティ ExpireMonth の値。
     */
    public String getExpireMonth() {
        return expireMonth;
    }
    
    /** プロパティ ExpireMonth の設定メソッド。
     * @param ExpireMonth プロパティ ExpireMonth の新しい値。
     */
    public void setExpireMonth(String ExpireMonth) {
        this.expireMonth = ExpireMonth;
    }
    
    /** プロパティ First_Name の取得メソッド。
     * @return プロパティ First_Name の値。
     */
    public String getFirst_Name() {
        return first_Name;
    }
    
    /** プロパティ First_Name の設定メソッド。
     * @param First_Name プロパティ First_Name の新しい値。
     */
    public void setFirst_Name(String First_Name) throws IOException {
        this.first_Name = getConv2Sjis( First_Name );
    }
    
    //変換ルーチン
    private static String getConv2Sjis( String str ) throws IOException {
    	str = new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    	Check formchk;
		CheckError chError;
		//ラスト
		formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet(str.trim(),1,false);
		str = chError.getRstr();
		chError = null;
		formchk = null;
    	return str;
    }
	/**
	 * Returns the agtcode.
	 * @return String
	 */
	public String getAgtcode() {
		return agtcode;
	}

	/**
	 * Sets the agtcode.
	 * @param agtcode The agtcode to set
	 */
	public void setAgtcode(String agtcode) {
		this.agtcode = agtcode;
	}

	/**
	 * Returns the previewflg.
	 * @return String
	 */
	public String getPreviewflg() {
		return previewflg;
	}

	/**
	 * Returns the throwflg.
	 * @return String
	 */
	public String getThrowflg() {
		return throwflg;
	}

	/**
	 * Sets the previewflg.
	 * @param previewflg The previewflg to set
	 */
	public void setPreviewflg(String previewflg) {
		this.previewflg = previewflg;
	}

	/**
	 * Sets the throwflg.
	 * @param throwflg The throwflg to set
	 */
	public void setThrowflg(String throwflg) {
		this.throwflg = throwflg;
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
	 * Returns the sending_day.
	 * @return String
	 */
	public String getSending_day() {
		return sending_day;
	}

	/**
	 * Returns the sending_month.
	 * @return String
	 */
	public String getSending_month() {
		return sending_month;
	}

	/**
	 * Returns the sending_time.
	 * @return String
	 */
	public String getSending_time() {
		return sending_time;
	}

	/**
	 * Sets the sending_day.
	 * @param sending_day The sending_day to set
	 */
	public void setSending_day(String sending_day) {
		this.sending_day = sending_day;
	}

	/**
	 * Sets the sending_month.
	 * @param sending_month The sending_month to set
	 */
	public void setSending_month(String sending_month) {
		this.sending_month = sending_month;
	}

	/**
	 * Sets the sending_time.
	 * @param sending_time The sending_time to set
	 */
	public void setSending_time(String sending_time) throws IOException {
        this.sending_time = getConv2Sjis( sending_time );
	}
	public void setSending_time(String sending_time, boolean flag) throws IOException {
        this.sending_time = sending_time ;
	}
	//
	public static int ch_lastname = 0;
    //
    public static int ch_lastname_1 = 1;
    public static int ch_lastname_2 = 2;
    //
    public static int ch_first_name = 3;
    //
    public static int ch_first_name1 = 4;
    public static int ch_first_name2 = 5;
    //
    public static int ch_state_cd = 6;
    public static int ch_postal_cd =  7;
    //
    public static int ch_postal_cd1 = 8;
    public static int ch_postal_cd2 = 9;
    //
    public static int ch_city_name = 10;
    public static int ch_address = 11;
    public static int ch_deliveryname =  12;
    public static int ch_tel_no=  13;
    public static int ch_fax = 14;
    public static int ch_mobile_e_mail= 15;
    public static int ch_addressflg = 16;
    ///////
    public static int ch_buildaddress = 17;
    public static int ch_main_state = 18;
    public static int ch_main_address = 19;
    //////
    public static int ch_main_lastname_kana = 20;
    public static int ch_main_firstname_kana = 21;
    public static int ch_main_lastname_kanji = 22;
    public static int ch_main_firstname_kanji = 23;
    public static int ch_main_postalcode = 24;
    //
    public static int ch_main_postalcode1 = 25;
   	public static int ch_main_postalcode2 = 26;
    //
    public static int ch_ticketing = 27;
    public static int ch_payment_way = 28;
    
    public static int ch_sending_month =  29;
    public static int ch_sending_day = 30;
    public static int ch_sending_time =  31;
}
