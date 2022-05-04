/*
 * CardForm.java
 *
 * Created on 2002/04/28, 13:10
 */

package jp.co.lastminute.cart.form;

import java.io.*;
import org.apache.struts.action.*;

import javax.servlet.*;
import javax.servlet.http.*;

import jp.co.lastminute.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class ProductSendForm extends ActionForm{
    private String lastname = "";
    //
    private String lastname_1 = "";
    private String lastname_2 = "";
    //
    private String first_name = "";
    //
    private String first_name1 = "";
    private String first_name2 = "";
    //
    private String state_cd = "";
    private String postal_cd = "";
    //
    private String postal_cd1 = "";
    private String postal_cd2 = "";
    //
    private String city_name = "";
    private String address = "";
    private String deliveryname = "";
    private String tel_no= "";
    private String fax ="";
    private String mobile_e_mail= "";
    private String addressflg = "1";
    ///////
    private String buildaddress = "";
    private String main_state = "";
    private String main_address = "";
    //////
    private String main_lastname_kana = "";
    private String main_firstname_kana = "";
    private String main_lastname_kanji = "";
    private String main_firstname_kanji = "";
    private String main_postalcode = "";
    //
    private String main_postalcode1 = "";
    private String main_postalcode2 = "";
    //
    //////
    private String ticketing = "1";
    private String payment_way ="1";
    //////
    private String update_flg = "";
    
    //////////////////////////////////////////
    private String deriver_month = "";
    private String deriver_day = "";
    private String deriver_time = "";

    public String getBuildaddress(){
        return this.buildaddress;
    }
    public void setBuildaddress(String buildaddress)throws IOException{
        this.buildaddress = getConv2Sjis( buildaddress ) ;
        return ;
    }
    public void setBuildaddress(String buildaddress, boolean flg)throws IOException{
        this.buildaddress = buildaddress ;
    }
    public String getMain_state(){
        return this.main_state;
    }
    public void setMain_state(String main_state)throws IOException{
        this.main_state = getConv2Sjis( main_state ) ;
    }
    public String getMain_address(){
        return this.main_address;
    }
    public void setMain_address(String main_address)throws IOException{
        this.main_address = getConv2Sjis( main_address ) ;
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
        this.address = getConv2Sjis( address ) ;
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
        this.city_name = getConv2Sjis( city_name ) ;
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
        this.deliveryname = getConv2Sjis( deliveryname ) ;
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
        this.fax = getConv2Sjis( fax ) ;
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
        this.first_name = getConv2Sjis( first_name ) ;;
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
        this.lastname = getConv2Sjis( lastname ) ;
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
        this.mobile_e_mail = getConv2Sjis( mobile_e_mail ) ;
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
        this.postal_cd = getConv2Sjis( postal_cd ) ;
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
        this.tel_no = getConv2Sjis( tel_no ) ;
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
        this.main_firstname_kana = getConv2Sjis(main_firstname_kana);
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
        this.main_lastname_kana = getConv2Sjis( main_lastname_kana ) ;
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
        this.main_lastname_kanji = getConv2Sjis( main_lastname_kanji ) ;
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
        this.first_name1 = getConv2Sjis( first_name1 ) ;
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
        this.first_name2 = getConv2Sjis( first_name2 ) ;
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
        this.lastname_1 = getConv2Sjis( lastname_1 ) ;
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
	/////
	public String getConv2Sjis( String str ) throws IOException{
		return new String(str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }

}
