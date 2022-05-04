/*
 * SubOrderModel.java
 *
 * Created on 2002/03/31, 23:08
 */

package jp.co.lastminute.cart.model;

import java.io.*;
import jp.co.yobrain.util.ParseFormat;
import jp.co.lastminute.cart.members.*;

import jp.co.lastminute.cart.prop.*;
import jp.co.lastminute.cart.Constants;

/**
 *
 * @author  skondo
 * @version 
 */
public class Sub_Order implements Serializable{
    /**
    SUB_ORDER_NO       NUMBER(10, 0)     NOT NULL,
    ORDER_NO           NUMBER(10, 0),
    PRODUCT_TYPE_CD    NUMBER(10, 0)     NOT NULL,
    ORDER_XSL_FILE     VARCHAR2(80)
    PROD_CD            CHAR(10),
    AGT_CD             CHAR(4)           NOT NULL,
    TITLE              VARCHAR2(30),
    BUY_PROP           VARCHAR2(2000),
    GUARANTEE_FLG      NUMBER(1, 0),
    PRICE              NUMBER(10, 0)     NOT NULL,
    AGENT_ACTION_URL   VARCHAR2(256)
    MAKE_DATE          DATE,
    DEL_FLG            NUMBER(1, 0),
     */
    private int SUB_ORDER_NO;
    private int ORDER_NO;
    private String AGT_ORDER_NO;
    private int PRODUCT_TYPE_CD;
    private String ORDER_XSL_FILE;
    private String PROD_CD;
    private String AGT_PLAN_CD;
    private String AGT_PROD_CD;
    private String AGR_AREA_CD;
    private String AGT_CD;
    private String TITLE;
    private String BUY_PROP;
    private int GUARANTEE_FLG;
    private int PRICE;
    private String AGENT_ACTION_URL;
    private String MAKE_DATE;
    private int DEL_FLG;
    private long LAST_SALE;
    private long END_SALE;
    ////
    private String SALES_DATE;
    ////
    private String authnumber = "";
    ////
    private String sessionid = "";
    private int pax = 1;
    private int adult = 0;
    private int child = 0;
    private int infant = 0;
    private String etc = "";
   	//
   	private int sending = 0;
   	private int sending_tax = 0;
   	private int tax = 0;
   	//
   	private int sending_calc_unit = 0;
	//
	private int fax = 0;
    public int status;
    //
    public int sheat = 0;
    //
    private Object actionclass = null;
    //
    private Node member = null;
    private boolean booking = false;
    private String returnXmlStr = "";
    
    private ParseFormat pf = null;
    /** コンストラクタ */ 
    public Sub_Order() {
        SUB_ORDER_NO = 0;
        ORDER_NO = 0;
        AGT_ORDER_NO = "";
        PRODUCT_TYPE_CD = 0;
        ORDER_XSL_FILE = "";
        PROD_CD = "";
        AGT_PLAN_CD = "";
        AGT_PROD_CD = "";
        AGR_AREA_CD = "";
        AGT_CD = "";
        TITLE = "";
        BUY_PROP = "";
        GUARANTEE_FLG = 0;
        PRICE = 0;
        AGENT_ACTION_URL = "";
        LAST_SALE = 0;
        END_SALE  = 0;
        MAKE_DATE = "";
        //////////
        SALES_DATE = "";
        status = 0;
        ///////////
        sending = 0;
   		sending_tax = 0;
   		tax = 0;
        
        etc = "";
        sessionid = "";
        pax = 1;
    }
    /**
     * リンクストリング
     */
    public String getLinkStr( Order orders ){
    	try{
	    	if((this.getStatus() < Constants.NOT_CONFIRM_ )
	    	&&( this.getActionclass() != null ) 
	    	&&( ((Navigato)this.getActionclass()).isThrow() == Constants.IS_THROW_NG )){
	    		return "<A HREF='/lmj/cart/CartIn.do;jsessionid=" + sessionid
	    				+ "?previewflg="+Constants.STEP01
	    				+ "&order_NO="+ orders.getORDER_NO()
	    				+ "&sub_order_no=" + this.getSUB_ORDER_NO()
	    				+ "&action_type=" + Constants.CANCELL
						+ "&throwflg=" + Constants.STEP01_CANCELL
						+ "&product_type_cd=" + this.getPRODUCT_TYPE_CD()
						+ "&agtcode=" + this.getAGT_CD() + "'>"
						+ "<img src='/img/icon_stop.gif' alt='キャンセル' width='10' height='10' border='0'/></A>";
	    	}
    	}catch(Exception ex){	ex.printStackTrace();	}
    	return "";	
    }
    public int getPrices(){
        return this.pax * this.PRICE;
    }
    /**
     * サブオーダーを保持する
     * @param int suborderno
     */
    synchronized public void setSUB_ORDER_NO(int suborderno){
        this.SUB_ORDER_NO = suborderno;
    }
    /**
     * オーダーNOを保持する
     * @param int orderno
     */
    synchronized public void setORDER_NO(int orderno){
        this.ORDER_NO = orderno;
    }
    /**
     * 商品種別コードを保持する
     * @param int producttypecd
     */
    synchronized public void setPRODUCT_TYPE_CD(int producttypecd){
        this.PRODUCT_TYPE_CD = producttypecd;
    }
    /**
     * オーダーXSLファイルを保持する
     * @param String orderxslfile
     */
    synchronized public void setORDER_XSL_FILE(String orderxslfile){
        this.ORDER_XSL_FILE = orderxslfile;
    }
    /**
     * 商品コードを保持する
     * @param String prodcd
     */
    synchronized public void setPROD_CD(String prodcd){
        this.PROD_CD = prodcd;
    }
    /** 
     * エージェント商品コードを保持
     * @param String agtprodcd
     */
    synchronized public void setAGT_PROD_CD(String agtprodcd){
        this.AGT_PROD_CD = agtprodcd;
    }
    /**
     * エージェントコードを保持
     * @param String agtcd
     */
    synchronized public void setAGT_CD(String agtcd){
        this.AGT_CD = agtcd;
    }
    /**
     * タイトルを取得
     * @param String title
     */
    synchronized public void setTITLE(String title){
        this.TITLE = title;
    }
    /**
     * 購買属性情報を保持する
     * @param String buyprop
     */
    synchronized public void setBUY_PROP(String buyprop){
        this.BUY_PROP = buyprop;
    }
    /**
     * ギャランティフラグを保持する
     * @param int guaanteeflg
     */
    synchronized public void setGUARANTEE_FLG(int guaanteeflg){
        this.GUARANTEE_FLG = guaanteeflg;
    }
    /**
     * 価格を保持する
     * @param int price
     */
    synchronized public void setPRICE(int price){
        this.PRICE = price;
    }
    /**
     * エージェントアクションURL
     * @param String agetactionurl
     */
    synchronized public void setAGENT_ACTION_URL(String agetactionurl){
        this.AGENT_ACTION_URL = agetactionurl;
    }
    /**
     * 成日を保持する
     * @param String agent_action_url
     */
    synchronized public void setMAKE_DATE(String agent_action_url){
        this.AGENT_ACTION_URL = agent_action_url;
    }
    /**
     * 削除フラグを保持する
     * @param int delflg
     */
    synchronized public void setDEL_FLG(int delflg){
        this.DEL_FLG = delflg;
    }
    /**
     * 手仕舞い日を取得する
     * @param int lastsale
     */
    synchronized public void setLAST_SALE(long lastsale){
        this.LAST_SALE = lastsale;
    }
    /**
     * オーダーNOを取得する
     * @return int SUB_ORDER_NO
     */
    public int getSUB_ORDER_NO(){
        try{
            return this.SUB_ORDER_NO;
        }catch(Exception e){    return 0;   }
    }
    /**
     * オーダーNoを取得する
     * @return int ORDER_NO
     */
    public int getORDER_NO(){
        try{
            return this.ORDER_NO;
        }catch(Exception e){    return 0;   }
    }
    /**
     * 商品種別を取得する
     * @return int PRODUCT_TYPE_CD
     */
    public int getPRODUCT_TYPE_CD(){
        try{
            return this.PRODUCT_TYPE_CD;
        }catch(Exception e){    return 0;   }
    }
    /**
     * オーダーXSLファイルを取得する
     * @return String ORDER_XSL_FIlE
     */
    public String getORDER_XSL_FILE(){
        try{
            return this.ORDER_XSL_FILE;
        }catch(Exception e){    return "";   }
    }
    /**
     * 商品コードを取得する
     * @return String PROD_CD
     */
    public String getPROD_CD(){
        try{
            return this.PROD_CD;
        }catch(Exception e){    return "";   }
    }
    /**
     * エージェント商品コードを取得
     * @return String AGT_PROD_CD
     */
    public String getAGT_PROD_CD(){
        try{
            return this.AGT_PROD_CD;
        }catch(Exception e){    return "";   }
    }
    /**
     * エージェントコードを取得
     * @return String AGT_CD
     */
    public String getAGT_CD(){
        try{
            return this.AGT_CD;
        }catch(Exception e){    return "";   }
    }
    /**
     * 商品名を取得
     * @return String TITLE
     */
    public String getTITLE(){
        try{
            return this.TITLE;
        }catch(Exception e){    return "";   }
    }
    /**
     * 購買属性情報
     * @return String BUY_PROP
     */
    public String getBUY_PROP(){
        try{
            return this.BUY_PROP;
        }catch(Exception e){    return "";   }
    }
    /**
     * ギャランティフラグを取得する
     * @return int GUARANTEE_FLG
     */
    public int getGUARANTEE_FLG(){
        try{
            return this.GUARANTEE_FLG;
        }catch(Exception e){    return 0;   }
    }
    /**
     * 価格を取得する
     * @return int PRICE
     */
    public int getPRICE(){
        try{
            return this.PRICE;
        }catch(Exception e){    return 0;   }
    }
    /**
     * エージェントアクションURL
     * @return AGENT_ACATION_URL
     */
    public String getAGENT_ACTION_URL(){
        try{
            return this.AGENT_ACTION_URL;
        }catch(Exception e){    return "";   }
    }
    /**
     * 作成日を取得する
     * @return String MAKE_DATE
     */
    public String getMAKE_DATE(){
        try{
            return this.MAKE_DATE;
        }catch(Exception e){    return "";   }
    }
    /**
     * 削除フラグを取得する
     * @return int DEL_FLG
     */
    public int getDEL_FLG(){
        try{
            return this.DEL_FLG;
        }catch(Exception e){    return 0;   }
    }
    /**
     * 手仕舞い日を取得する
     * @return int LAST_SALE
     */
    public long getLAST_SALE(){
        try{
            return this.LAST_SALE;
        }catch(Exception e){    return 0;   }
    }
    /**
     * ハイフンなし
     */
    public String getPROD_CDdel(){
        String newStr = "";
        String oldStr = this.getPROD_CD();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<oldStr.length(); i++){
            char ch = oldStr.charAt(i);
            if(ch != '-'){      sb.append( ch );    }
        }
        newStr = sb.toString();
        return newStr;
    }
    /**
     * XML出力
     */
    public synchronized String getXMLdocument() {
        String xmldoc = "<SUB_ORDER_NO>" + this.getSUB_ORDER_NO() + "</SUB_ORDER_NO>\n"
                      + "<ORDER_XSL_FILE>" + this.getORDER_XSL_FILE() + "</ORDER_XSL_FILE>\n"
                      + "<AGT_ORDER_NO><![CDATA[" + this.getAGT_ORDER_NO() + "]]></AGT_ORDER_NO>\n"
                      + "<ORDER_NO>" + this.getORDER_NO() + "</ORDER_NO>\n"
                      + "<PRODUCT_TYPE_CD>" + this.getPRODUCT_TYPE_CD() + "</PRODUCT_TYPE_CD>\n"
                      + "<AGT_CD>" + this.getAGT_CD() + "</AGT_CD>\n"
                      + "<PROD_CD>" + this.getPROD_CD() + "</PROD_CD>\n"
                      + "<PROD_CD_B>" + this.getPROD_CDdel() + "</PROD_CD_B>\n"
                      + "<AGT_PROD_CD><![CDATA[" + this.getAGT_PROD_CD() + "]]></AGT_PROD_CD>\n"
                      + "<AGT_PLAN_CD><![CDATA[" + this.getAGT_PLAN_CD() + "]]></AGT_PLAN_CD>\n"
                      + "<AGR_AREA_CD>" + this.getAGR_AREA_CD() + "</AGR_AREA_CD>\n"
                      + "<TITLE><![CDATA[" + this.getTITLE() + "]]></TITLE>"
                      + "<GUARANTEE_FLG>" + this.getGUARANTEE_FLG() + "</GUARANTEE_FLG>\n"
                      + "<PRICE>" + this.getPRICE() + "</PRICE>\n"
                      + "<PRICE_STR>" + pf.ToPriceFormat(this.getPRICE()) + "</PRICE_STR>\n"
                      + "<AGENT_ACTION_URL>" + this.getAGENT_ACTION_URL() + "</AGENT_ACTION_URL>\n"
                      + "<MAKE_DATE>" + this.getMAKE_DATE() + "</MAKE_DATE>\n"
                      + "<LAST_SALE>" + this.getLAST_SALE() + "</LAST_SALE>\n"
                      + "<END_SALE>" + this.getEND_SALE() + "</END_SALE>\n"
                      + "<SENDING>" + this.sending + "</SENDING>\n"
					  + "<SENDING_TAX>" + this.sending_tax + "</SENDING_TAX>\n"
					  + "<TAX>" + this.tax + "</TAX>\n"
					  + "<FAX>" + this.fax + "</FAX>\n"
					  + "<PAX>" + this.pax + "</PAX>\n"
					  + "<ADLUT>" + this.adult + "</ADLUT>\n"
					  + "<CHILD>" + this.child + "</CHILD>\n"
					  + "<INFANT>" + this.infant + "</INFANT>\n"
					  + "<SHEAT><![CDATA[" + this.sheat	+ "]]></SHEAT>\n"
					  + "<SESSION_ID><![CDATA[" + this.sessionid + "]]></SESSION_ID>\n"
                      + "<BUY_PROP>" + this.getBUY_PROP() + "</BUY_PROP>\n"
                      + "<CARD>" + this.authnumber + "</CARD>\n"
                      + getMemberStr() + "\n";
        return xmldoc;
    }
    public String getMemberStr(){
    	String memberxml = "";
    	if( this.member != null ){
    		memberxml = this.member.getXmlString();
    	}
    	return memberxml;
    }
    /** プロパティ status の取得メソッド。
     * @return プロパティ status の値。
     */
    public int getStatus() {
        return status;
    }
    
    /** プロパティ status の設定メソッド。
     * @param status プロパティ status の新しい値。
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /** プロパティ AGT_ORDER_NO の取得メソッド。
     * @return プロパティ AGT_ORDER_NO の値。
     */
    public String getAGT_ORDER_NO() {
        return AGT_ORDER_NO;
    }
    
    /** プロパティ AGT_ORDER_NO の設定メソッド。
     * @param AGT_ORDER_NO プロパティ AGT_ORDER_NO の新しい値。
     */
    public void setAGT_ORDER_NO(String AGT_ORDER_NO) {
        this.AGT_ORDER_NO = AGT_ORDER_NO;
    }
    
    /** プロパティ AGT_PLAN_CD の取得メソッド。
     * @return プロパティ AGT_PLAN_CD の値。
     */
    public String getAGT_PLAN_CD() {
        return AGT_PLAN_CD;
    }
    
    /** プロパティ AGT_PLAN_CD の設定メソッド。
     * @param AGT_PLAN_CD プロパティ AGT_PLAN_CD の新しい値。
     */
    public void setAGT_PLAN_CD(String AGT_PLAN_CD) {
        this.AGT_PLAN_CD = AGT_PLAN_CD;
    }
    
    /** プロパティ AGR_AREA_CD の取得メソッド。
     * @return プロパティ AGR_AREA_CD の値。
     */
    public String getAGR_AREA_CD() {
        return AGR_AREA_CD;
    }
    
    /** プロパティ AGR_AREA_CD の設定メソッド。
     * @param AGR_AREA_CD プロパティ AGR_AREA_CD の新しい値。
     */
    public void setAGR_AREA_CD(String AGR_AREA_CD) {
        this.AGR_AREA_CD = AGR_AREA_CD;
    }
    
    /** プロパティ sessionid の取得メソッド。
     * @return プロパティ sessionid の値。
     */
    public String getSessionid() {
        return sessionid;
    }
    
    /** プロパティ sessionid の設定メソッド。
     * @param sessionid プロパティ sessionid の新しい値。
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    
    /** プロパティ pax の取得メソッド。
     * @return プロパティ pax の値。
     */
    public int getPax() {
        return pax;
    }
    
    /** プロパティ pax の設定メソッド。
     * @param pax プロパティ pax の新しい値。
     */
    public void setPax(int pax) {
        this.pax = pax;
    }
    
    /** プロパティ etc の取得メソッド。
     * @return プロパティ etc の値。
     */
    public String getEtc() {
        return etc;
    }
    
    /** プロパティ etc の設定メソッド。
     * @param etc プロパティ etc の新しい値。
     */
    public void setEtc(String etc) {
        this.etc = etc;
    }
    
	/**
	 * Returns the sending.
	 * @return int
	 */
	public int getSending() {
		return sending;
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
	 * Sets the sending.
	 * @param sending The sending to set
	 */
	public void setSending(int sending) {
		this.sending = sending;
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
	/**
	 *  ユーティリティ系
	 */
	public static String convBuyProp(String str){
        String addbuyprop = "";
        try{
            int start = str.indexOf("<BUY_PROP>");
            int end = str.indexOf("</BUY_PROP>");
            if(( start == -1 )&&( end == -1 )) {
                addbuyprop = str;
            }else if(( start != -1 )&&( end != -1 )){
                addbuyprop = str.substring(start + 10, end );
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return addbuyprop;
    }
	/**
	 * Returns the fax.
	 * @return int
	 */
	public int getFax() {
		return fax;
	}

	/**
	 * Sets the fax.
	 * @param fax The fax to set
	 */
	public void setFax(int fax) {
		this.fax = fax;
	}

	/**
	 * Returns the member.
	 * @return Node
	 */
	public Node getMember() {
		return member;
	}

	/**
	 * Sets the member.
	 * @param member The member to set
	 */
	public void setMember(Node member) {
		this.member = member;
	}

	/**
	 * Returns the actionclass.
	 * @return Object
	 */
	public Object getActionclass() {
		return actionclass;
	}

	/**
	 * Sets the actionclass.
	 * @param actionclass The actionclass to set
	 */
	public void setActionclass(Object actionclass) {
		this.actionclass = actionclass;
	}

	/**
	 * Returns the child.
	 * @return int
	 */
	public int getChild() {
		return child;
	}

	/**
	 * Returns the infant.
	 * @return int
	 */
	public int getInfant() {
		return infant;
	}

	/**
	 * Sets the child.
	 * @param child The child to set
	 */
	public void setChild(int child) {
		this.child = child;
	}

	/**
	 * Sets the infant.
	 * @param infant The infant to set
	 */
	public void setInfant(int infant) {
		this.infant = infant;
	}

	/**
	 * Returns the adult.
	 * @return int
	 */
	public int getAdult() {
		return adult;
	}

	/**
	 * Sets the adult.
	 * @param adult The adult to set
	 */
	public void setAdult(int adult) {
		this.adult = adult;
	}

	/**
	 * Returns the eND_SALE.
	 * @return double
	 */
	public long getEND_SALE() {
		return END_SALE;
	}

	/**
	 * Sets the eND_SALE.
	 * @param eND_SALE The eND_SALE to set
	 */
	public void setEND_SALE(long eND_SALE) {
		END_SALE = eND_SALE;
	}

	/**
	 * Returns the sALES_DATE.
	 * @return String
	 */
	public String getSALES_DATE() {
		return SALES_DATE;
	}

	/**
	 * Sets the sALES_DATE.
	 * @param sALES_DATE The sALES_DATE to set
	 */
	public void setSALES_DATE(String sALES_DATE) {
		SALES_DATE = sALES_DATE;
	}

	/**
	 * Returns the sheat.
	 * @return int
	 */
	public int getSheat() {
		return sheat;
	}

	/**
	 * Sets the sheat.
	 * @param sheat The sheat to set
	 */
	public void setSheat(int sheat) {
		this.sheat = sheat;
	}

	/**
	 * Returns the sending_calc_unit.
	 * @return int
	 */
	public int getSending_calc_unit() {
		return sending_calc_unit;
	}

	/**
	 * Sets the sending_calc_unit.
	 * @param sending_calc_unit The sending_calc_unit to set
	 */
	public void setSending_calc_unit(int sending_calc_unit) {
		this.sending_calc_unit = sending_calc_unit;
	}

	/**
	 * Returns the booking.
	 * @return boolean
	 */
	public boolean isBooking() {
		return booking;
	}

	/**
	 * Sets the booking.
	 * @param booking The booking to set
	 */
	public void setBooking(boolean booking) {
		this.booking = booking;
	}

	/**
	 * Returns the returnXmlStr.
	 * @return String
	 */
	public String getReturnXmlStr() {
		return returnXmlStr;
	}

	/**
	 * Sets the returnXmlStr.
	 * @param returnXmlStr The returnXmlStr to set
	 */
	public void setReturnXmlStr(String returnXmlStr) {
		this.returnXmlStr = returnXmlStr;
	}

	/**
	 * Returns the authnumber.
	 * @return String
	 */
	public String getAuthnumber() {
		return authnumber;
	}

	/**
	 * Sets the authnumber.
	 * @param authnumber The authnumber to set
	 */
	public void setAuthnumber(String authnumber) {
		this.authnumber = authnumber;
	}
}
