/*
 * OrderModel.java
 *
 * Created on 2002/03/31, 23:08
 */

package jp.co.lastminute.cart.model;

import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class Order implements Serializable{
    /**
    ORDER_NO     NUMBER(10, 0)    NOT NULL,
    USER_ID      NUMBER(10, 0),
    PRICE        NUMBER(10, 0)    NOT NULL,
    TAX          NUMBER(10, 0),
    TOTAL        NUMBER(10, 0),
    BUY_DATE     DATE,
    DEL_FLG      NUMBER(1, 0),
    MAKE_DATE    DATE,
    UP_DATE      DATE,
     */
    private int ORDER_NO;
    private int USER_ID;
    private int PRICE;
    private int TAX;
    private int TOTAL;
    private int SENDING;
    private int SENDING_TAX;
    private String BUY_DATE;
    private String MAKE_DATE;
    private String UP_DATE;
    
    private Vector sub_order;       //サブオーダー
    private Card_Auth card_auth;    //カード認証
    private Vector product_send;    //配送先
    /////////////////////////////////////////////
    public int sending = 0;
    public int flow = 0;
    public int card = 0;
    public int fax = 0;
    public int profile = 0;
    /////////////////////////////////////////////
    private int Exec_type;
    ////////////////////////////////////////////
    //エラーコメント
    protected Vector error_comm = new Vector();
    /** コンストラクター */
    public Order() {
        this.sub_order = null;
        this.card_auth = null;
        this.product_send = null;
        //
        this.ORDER_NO = 0;
        this.USER_ID = 0;
        this.PRICE = 0;
        this.TAX = 0;
        this.SENDING = 0;
        this.SENDING_TAX = 0;
        this.TOTAL = 0;
        this.BUY_DATE = "";
        this.MAKE_DATE = "";
        this.UP_DATE ="";
        this.Exec_type = 0;
    }
    /**
     * イニシャライズ
     */
    public void initOrder(){
        this.sub_order = new Vector();
        this.card_auth = new Card_Auth();
        Product_Send ps = new Product_Send();
        this.product_send = new Vector();
        this.product_send.add( ps );
    }
    /**
     * 
     */
    public void calcTotal(){
    	try{
    		this.TOTAL = 0;
    		this.PRICE = 0;
    		this.TAX = 0;
	    	for( int i=0; i<sub_order.size(); i++){
	    		Sub_Order temp = (	Sub_Order )sub_order.get( i );
	    		if( temp.status < Constants.CANCLL_STATUS_ ){
	    			this.PRICE += temp.getPrices();
	    			this.TAX += temp.getTax();
	    		}
	    		temp = null;
	    	}
    	}catch(Exception ex){	ex.printStackTrace();	}
    	this.TOTAL = this.PRICE + this.TAX;	
    }
    /**
     * 配送先を保持保持する。
     * @param Product_Send ps
     */
    public boolean setProductSend( Product_Send ps ){
        try{
            if( this.product_send.size() == 0){
                this.product_send.add(ps);
            }else{
                this.product_send.setElementAt(ps ,0);
            }
            return true;
        }catch(Exception ex){   ex.printStackTrace();   }
        return false;
    }
    /**
     * 配送先を保持保持する。
     * @param Product_Send ps
     * @param int idx
     */
    public boolean setProductSend( Product_Send ps, int idx){
        try{
            this.product_send.insertElementAt(ps, idx);
            return true;
        }catch(Exception ex){ex.printStackTrace();   }
        return false;
    }
    /**
     * 配送先を取得する
     * @return Vector productsend
     */
    public Vector getProductSend(){
        try{
            return this.product_send;
        }catch(Exception ex){   ex.printStackTrace();   }
        return null;
    }
    /**
     * 配送先を取得する
     * @param int idx
     * @return Product_Send
     */
    public Product_Send getProductSend(int idx){
        try{
            return (Product_Send)this.product_send.elementAt(idx);
        }catch(Exception ex){   ex.printStackTrace();   }
        return null;
    }
    /**
     * カード情報を保持する
     * @param Card_Auth card
     */
    public boolean setCardAuth( Card_Auth card ){
        try{
            this.card_auth = card;
            return true;
        }catch(Exception ex){   ex.printStackTrace();   }
        return false;
    }
    /**
     * カード情報を取得する
     * @return Card_Auth card
     */
    public Card_Auth getCard_Auth(){
        try{
            return this.card_auth;
        }catch(Exception ex){   ex.printStackTrace();   }
        return null;
    }
    /**
     * サブオーダーを追加する。
     * @param Sub_Order so
     * @return boolean TRUE/FALSE
     */
    public boolean setSubOrder( Sub_Order so ){
        try{
        	for(int i=0; i<this.sub_order.size(); i++){
        		Sub_Order sub = this.getSubOrder( i );
        		if( sub.getSUB_ORDER_NO() == so.getSUB_ORDER_NO()){
        			this.sub_order.set( i, so );
        			return true;
        		}
        	}
            sub_order.add( so );
            return true;
        }catch(Exception e){    e.printStackTrace();    }
        return false;
    }
    /**
     * サブオーダーを保持する
     * @param Sub_Order so
     * @param int idx
     */
    public boolean setSubOrder(Sub_Order so, int idx){
        try{
            if( idx >= 0){
                sub_order.setElementAt(so, idx);
                return true;
            }else{
                return sub_order.add( so );
            }
        }catch(Exception ex){   ex.printStackTrace();   }
        return false;        
    }
    /*＊
     * サブオーダーを削除する
     * @param int idx
     */
    public void removeSubOrder( String suborder ){
    	try{
    		int sub_order_no = Integer.parseInt( suborder );
	    	for( int idx=0; idx<sub_order.size(); idx++){
	    		Sub_Order temp = (Sub_Order)sub_order.get( idx );
	    		if( temp.getSUB_ORDER_NO() == sub_order_no ){
	    			removeSubOrder( idx );
	    			return;
	    		}
	    	}
    	}catch(Exception ex){}
    }
    public void removeSubOrder(int idx ){
        try{
            sub_order.removeElementAt(idx);
        }catch(Exception ex){   ex.printStackTrace();   }
    }
    /**
     * サブオーダーをセットする
     */
    public void setSubOrders( Vector sub_order ){
        this.sub_order = sub_order;
    }
    /**
     * サブオーダーの数を数える
     */
    public int getSubOrderCount(){
        return this.getSubOrders().size();
    }
    /**
     * 全てのサブオーダーを取得する
     */
    public Vector getSubOrders(){
        return sub_order;
    }
    /**
     * サブオーダーを取得する
     * @param int idx
     */
    public Sub_Order getSubOrder(int idx){
        try{
            return (Sub_Order)sub_order.get(idx);
        }catch(Exception rx){   rx.printStackTrace();   }
        return null;
    }
    /**
     * サブオーダーのステータスを変更
     */
    public void setSubOrderStatus( int idx, int status ){
        Sub_Order so = (Sub_Order)this.sub_order.elementAt(idx);
        so.setStatus( status );
        setSubOrder( so, idx );
    }
    /**
     * オーダーNOを取得する
     * @return int ORDER_NO
     */
    public int getORDER_NO(){
        try{
        return this.ORDER_NO;
        }catch(Exception ex){   return 0;   }
    }
    /**
     * ユーザーIDを取得する
     * @return int USER_ID
     */
    public int getUSER_ID(){
        try{
            return this.USER_ID;
        }catch(Exception ex){   return 0;   }
    }
    /**
     * 価格を取得する
     * @return int PRICE
     */
    public int getPRICE(){
        try{
            return this.PRICE;
        }catch(Exception ex){   return 0;   }
    }
    /**
     * TAXを取得する
     * @return int TAX
     */
    public int getTAX(){
        try{
            return this.TAX;
        }catch(Exception ex){   return 0;   }
    }
    /**
     * トータルを取得する
     * @param int TOTAL
     */
    public int getTOTAL(){
        try{
            return this.TOTAL;
        }catch(Exception ex){   return 0;   }
    }
    /**
     * 購入日を取得する
     * @return String BUY_DATE
     */
    public String getBUY_DATE(){
        try{
            return this.BUY_DATE;
        }catch(Exception ex){   return "";   }
    }
    /**
     * 作成日の取得
     * @return String MAKE_DATE
     */
    public String getMAKE_DATE(){
        try{
            return this.MAKE_DATE;
        }catch(Exception ex){   return "";   }
    }
    /**
     * 更新日を取得する
     * @return String UP_DATE
     */
    public String getUP_DATE(){
        try{
            return this.UP_DATE;
        }catch(Exception ex){   return "";   }
    }
    
    /**
     * オーダーNOを保持する
     * @param int orderno
     */
    public void setORDER_NO(int orderno){
        this.ORDER_NO = orderno;
    }
    /**
     * ユーザーIDを保持する
     * @param int userid
     */
    synchronized public void setUSER_ID(int userid){
        this.USER_ID = userid;
    }
    /**
     * 価格を保持
     * @param int price
     */
    synchronized public void setPRICE(int price){
        this.PRICE = price;
    }
    /**
     * タックスを保持する
     * @param int tax
     */
    synchronized public void setTAX(int tax){
        this.TAX = tax;
    }
    /**
     * トータル価格を保持
     * @param int total
     */
    synchronized public void setTOTAL(int total){
        this.TOTAL = total;
    }
    /**
     * 購入終了予定日
     * @param String buydate
     */
    synchronized public void setBUY_DATE(String buydate){
        this.BUY_DATE = buydate;
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
    
    public synchronized String getXMLdocument() {
        String xmldoc = "<ORDER><ORDER_NO>" + this.getORDER_NO() + "</ORDER_NO>"
                    + "<USER_ID>" + this.getUSER_ID() + "</USER_ID>"
                    + "<PRICE>" + this.getPRICE() + "</PRICE>"
                    + "<TAX>" + this.getTAX() + "</TAX>"
                    + "<BUY_DATE>" + this.getBUY_DATE() + "</BUY_DATE>"
                    + "<MAKE_DATE>" + this.getMAKE_DATE() + "</MAKE_DATE>";
                    for(int i=0; i<this.getSubOrders().size(); i++){
                        xmldoc += "<SUB_ORDER>" + this.getSubOrder(i).getXMLdocument() + "</SUB_ORDER>";
                    }
                    xmldoc += "<PRODUCT_SEND>" + this.getProductSend(0).getXMLdocument() + "</PRODUCT_SEND></ORDER>";
        return xmldoc;
    }
    public synchronized String getXMLdocument( Sub_Order so ) {
        String xmldoc = "<ORDER><ORDER_NO>" + this.getORDER_NO() + "</ORDER_NO>"
                    + "<USER_ID>" + this.getUSER_ID() + "</USER_ID>"
                    + "<PRICE>" + this.getPRICE() + "</PRICE>"
                    + "<TAX>" + this.getTAX() + "</TAX>"
                    + "<BUY_DATE>" + this.getBUY_DATE() + "</BUY_DATE>"
                    + "<MAKE_DATE>" + this.getMAKE_DATE() + "</MAKE_DATE>"
                    + "<SUB_ORDER>" + so.getXMLdocument() + "</SUB_ORDER>"
                    + "<PRODUCT_SEND>" + this.getProductSend(0).getXMLdocument() + "</PRODUCT_SEND></ORDER>";
        return xmldoc;
    }
	/**
	 * Returns the sENDING.
	 * @return int
	 */
	public int getSENDING() {
		return SENDING;
	}

	/**
	 * Returns the sENDING_TAX.
	 * @return int
	 */
	public int getSENDING_TAX() {
		return SENDING_TAX;
	}

	/**
	 * Sets the sENDING.
	 * @param sENDING The sENDING to set
	 */
	public void setSENDING(int sENDING) {
		SENDING = sENDING;
	}

	/**
	 * Sets the sENDING_TAX.
	 * @param sENDING_TAX The sENDING_TAX to set
	 */
	public void setSENDING_TAX(int sENDING_TAX) {
		SENDING_TAX = sENDING_TAX;
	}

	/**
	 * Returns the exec_type.
	 * @return int
	 */
	public int getExec_type() {
		return Exec_type;
	}

	/**
	 * Returns the sub_order.
	 * @return Vector
	 */
	public Vector getSub_order() {
		return sub_order;
	}

	/**
	 * Sets the exec_type.
	 * @param exec_type The exec_type to set
	 */
	public void setExec_type(int exec_type) {
		Exec_type = exec_type;
	}

	/**
	 * Sets the sub_order.
	 * @param sub_order The sub_order to set
	 */
	public void setSub_order(Vector sub_order) {
		this.sub_order = sub_order;
	}
	/**
	 * 
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
				er_comment = (String)this.error_comm.get(eindex) + careturn;
			}
		}catch(Exception ex){}
		return er_comment;
	}
	/**
	 * エラーコメントの移動
	 */
	public cartForm modifyErrComment( cartForm cartform ){
		try{
			for(int i=0; i<this.error_comm.size(); i++ ){
				String err_com = (String)this.error_comm.get( i );
				cartform.addError_comm( err_com );
			}
			clearError_comm();
		}catch(Exception ex){}
		return cartform;
	}
	/**
	 * Returns the error_comm.
	 * @return Vector
	 */
	public Vector getError_comm() {
		return error_comm;
	}

	/**
	 * Returns the fax.
	 * @return int
	 */
	public int getFax() {
		return fax;
	}

	/**
	 * Returns the profile.
	 * @return int
	 */
	public int getProfile() {
		return profile;
	}

	/**
	 * Sets the fax.
	 * @param fax The fax to set
	 */
	public void setFax(int fax) {
		this.fax = fax;
	}

	/**
	 * Sets the profile.
	 * @param profile The profile to set
	 */
	public void setProfile(int profile) {
		this.profile = profile;
	}

	/**
	 * Returns the sending.
	 * @return int
	 */
	public int getSending() {
		return sending;
	}

	/**
	 * Sets the sending.
	 * @param sending The sending to set
	 */
	public void setSending(int sending) {
		this.sending = sending;
	}

	/**
	 * Returns the card.
	 * @return int
	 */
	public int getCard() {
		System.err.println("<------------card-------------> "+ card ) ;
		if( card == 1 ){
			System.err.println("<------------CARD Start------------->") ;
			if( card_auth == null ){	System.err.println("<------------CARD NULL------------->") ;
				this.card = 0;
			}else if(card_auth.getCard_No().length() == 0 ){
				System.err.println("<------------CARD LENGH 0------------->") ;
				this.card = 0;
			}
		}
		return card;
	}

	/**
	 * Returns the flow.
	 * @return int
	 */
	public int getFlow() {
		return flow;
	}

	/**
	 * Sets the card.
	 * @param card The card to set
	 */
	public void setCard(int card) {
		this.card = card;
	}

	/**
	 * Sets the flow.
	 * @param flow The flow to set
	 */
	public void setFlow(int flow) {
		this.flow = flow;
	}
	/**
	 * 最低ステータスの取得
	 */
	public int getMinStatus(){
		int reInt = Constants.CANCLL_STATUS_;
		for(int i=0; i<this.getSubOrderCount(); i++){
			if( this.getSubOrder( i ).status < reInt ){
				reInt = this.getSubOrder( i ).status;
			}
		}
		return reInt;
	}
}
