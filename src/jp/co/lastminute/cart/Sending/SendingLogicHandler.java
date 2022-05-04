package jp.co.lastminute.cart.Sending;

import java.io.*;
import java.util.*;
import java.lang.reflect.Method;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import jp.co.lastminute.cart.Constants;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.prop.*;

import jp.co.lastminute.cart.cartForm;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SendingLogicHandler implements Serializable{
	//リクエストの保持
	private HttpServletRequest req = null;
	//サーブレットコンフィグ
	private ServletConfig servletConfig = null;
	//配送先ありなしフラグ
	private boolean sending_ok = false;
	//配送料金配列
	private Vector agtprices = new Vector();
	//
	private SendingGroup[] SendingGroups = null;
	//
	private int total = 0;
	private int tax = 0;
	private int sending = 0;
	private int sending_tax = 0;
	private int alltotal = 0;
	
	/**
	/*コンストラクター
	 */
	public SendingLogicHandler( ServletConfig servletConfig, HttpServletRequest req){
		this.servletConfig = servletConfig;
		this.req = req;
		
	}
	/**
	 * センディングのセット
	 */
	public void setSending(){
		this.setSending( Constants.CON_CONFIRM_, Constants.IS_SENDING_NEED);
	}
	/**
	 * 計算を行う
	 */
	public void allCalc(){
		if( agtprices.size() > 0 ){
			this.SendingGroups = new SendingGroup[ agtprices.size() ];
			for( int i=0; i<agtprices.size(); i++){
				this.SendingGroups[ i ] = (SendingGroup)agtprices.get( i );
				this.SendingGroups[ i ].calc();
				sending = sending + this.SendingGroups[ i ].getSending();
				sending_tax = sending_tax + this.SendingGroups[ i ].getSending_tax();
			}
		}
	}
	/**
	 * 配送先の確認
	 * Orderstatus = Constants.CON_CONFIRM_ 
	 * sending_flg = Constants.IS_SENDING_NEED
	 */
	public void setSending(int Orderstatus, int sending_flg ){
		HttpSession session = req.getSession();
		try{
			Order orders = (Order) session.getAttribute( Constants.CartOrder );
			User users = (User)session.getAttribute( Constants.CartUser );
			for(int i=0; i<orders.getSubOrderCount(); i++){
				Sub_Order suborder = ((Sub_Order)orders.getSub_order().get( i ));
				if( suborder.status < Orderstatus　){	//オーダーステータス
					Navigato navig = (Navigato)suborder.getActionclass();
					if( navig.isSending() == sending_flg ){	//センディングフラグ
						sending_ok = true;
						setAgtpricesDivide( suborder );
					}
					navig = null;
				}
				if( suborder.status < Constants.CANCLL_STATUS_ ){
					total += suborder.getPrices();
					tax += suborder.getTax()*suborder.getPax();		
				}
			}
			makeTotal();
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	private void makeTotal(){
		this.alltotal = this.total + this.tax;
	}
	/**
	 * 料金の保持
	 */
	public void setAgtpricesDivide( Sub_Order suborder ){
		//
		String agt_cd = suborder.getAGT_CD();
		SendingGroup pricegroups = null;
		int postion = -1;
		//ポジション
		postion = getPostion( agt_cd );
		AgtcdSendingPrice agtcdSendingPrice
		 = new AgtcdSendingPrice( agt_cd,
		 						suborder.getPax(),
		 						suborder.getPRICE(),
		 						suborder.getTax(),
		 						suborder.getSending(),
		 						suborder.getSending_tax(),
		 						suborder.getSending_calc_unit() );
		 System.err.print("Pax " + suborder.getPax() + "  Price " + suborder.getPRICE() );
		 System.err.print("Tax " + suborder.getTax() + "  Sending " + suborder.getSending() );
		 System.err.println("S Tax " + suborder.getSending_tax() + "  Unit " + suborder.getSending_calc_unit() );
		if( postion == -1 ){
			//新しいデータ項目
			pricegroups = new SendingGroup();
			pricegroups.setAGT_CD( agt_cd );
			pricegroups.add( agtcdSendingPrice );
			this.agtprices.add(  pricegroups );
			pricegroups = null;
			System.err.println(" New Agt Add ");
		}else{
			pricegroups = (SendingGroup)agtprices.get( postion );
			pricegroups.add( agtcdSendingPrice );
			this.agtprices.set( postion , pricegroups);
			System.err.println("Modify Agt Add Postion " + postion);
		}
		
	}
	/**
	 * ポジションの取得
	 */
	private int getPostion( String agt_cd ){
		if( this.agtprices.size() != 0){
			for(int i=0; i<this.agtprices.size(); i++){
				SendingGroup groups = (SendingGroup)this.agtprices.get( i );
				if( agt_cd.equals(groups.getAGT_CD())){
					return i;
				}
			}
		}
		return -1;
	}
	/**
	 * Returns the sendingGroups.
	 * @return SendingGroup[]
	 */
	public SendingGroup[] getSendingGroups() {
		return SendingGroups;
	}

	/**
	 * Sets the sendingGroups.
	 * @param sendingGroups The sendingGroups to set
	 */
	public void setSendingGroups(SendingGroup[] sendingGroups) {
		SendingGroups = sendingGroups;
	}
	/**
	 * カートフォームからの生成
	 */
	public static Product_Send getProductSend( cartForm cart, Product_Send sending ){
		try{
			sending.setFIRST_NAME( cart.getFirst_name1() + "\t" + cart.getFirst_name2() );
			sending.setLASTNAME( cart.getLastname_1() + "\t" + cart.getLastname_2() );
			sending.setPOSTAL_CD( cart.getPostal_cd1() + cart.getPostal_cd2() );
			sending.setSTATE_CD( Integer.parseInt( cart.getState_cd() ) );
			sending.setCITY_NAME( cart.getCity_name() );
			sending.setADDRESS( cart.getAddress() );
			sending.setDELIVERYNAME( cart.getDeliveryname() );
			sending.setTEL_NO( cart.getTel_no() );
			sending.setFAX( cart.getFax() );
			sending.setADDRESS( cart.getAddress() );
			sending.setMOBILE_E_MAIL( cart.getMobile_e_mail() );
			sending.setADDRESSFLG( cart.getAddressflg() );
			
			sending.setBuildaddress( cart.getBuildaddress() );
			sending.setMain_state( cart.getMain_state() );
			sending.setMain_address( cart.getMain_address() );
			sending.setMain_lastname_kana( cart.getMain_lastname_kana() );
			sending.setMain_firstname_kana( cart.getMain_firstname_kana() );
			sending.setMain_lastname_kanji( cart.getMain_lastname_kanji() );
			sending.setMain_firstname_kanji( cart.getMain_firstname_kanji() );
			sending.setMain_postalcode( cart.getMain_postalcode1() + cart.getMain_postalcode2() );
			sending.setTicketing( cart.getTicketing());
			sending.setPayment_way( cart.getPayment_way() );
			sending.setDeriver_month( cart.getSending_month() );
			sending.setDeriver_day( cart.getSending_day() );
			sending.setDeriver_time( cart.getSending_time() );
		
		}catch(Exception ex){	ex.printStackTrace();	}
		return sending;
	}
	/**
	 * カートフォーム生成の補助
	 */
	public static cartForm getCartForm( cartForm cart, Order order ) {
		try{
			Product_Send sending = order.getProductSend( 0 );
			cart.setLastname( sending.getLASTNAME() );
			cart.setLastname_1( getDivide( sending.getLASTNAME() )[0], true );
			cart.setLastname_2( getDivide( sending.getLASTNAME() )[1], true  );
			
			cart.setFirst_name( sending.getFIRST_NAME() );
			cart.setFirst_name1( getDivide( sending.getFIRST_NAME() )[0], true  );
			cart.setFirst_name2( getDivide( sending.getFIRST_NAME() )[1], true  );
			
			cart.setState_cd( "" + sending.getSTATE_CD() + "");
			cart.setPostal_cd( sending.getPOSTAL_CD() );
			cart.setPostal_cd1( getDivideStr( sending.getPOSTAL_CD(), 3 )[0] );
			cart.setPostal_cd2( getDivideStr( sending.getPOSTAL_CD(), 3 )[1] );
			
			cart.setCity_name( sending.getCITY_NAME(), true  );
			cart.setAddress( sending.getADDRESS(), true  );
			cart.setDeliveryname( sending.getDELIVERYNAME(), true  );
			
			cart.setTel_no( sending.getTEL_NO(), true  );
			cart.setMobile_e_mail( sending.getMOBILE_E_MAIL(), true  );
			
			cart.setMain_address( sending.getMain_address(), true  );	
			cart.setBuildaddress( sending.getBuildaddress(), true  );
			cart.setMain_state( sending.getMain_state() );
		
			cart.setMain_lastname_kana( sending.getMain_lastname_kana() , true );
			cart.setMain_firstname_kana( sending.getMain_firstname_kana() , true );
			cart.setMain_lastname_kanji( sending.getMain_lastname_kanji() , true );
			cart.setMain_firstname_kanji( sending.getMain_firstname_kanji() , true );
			
			cart.setMain_postalcode( sending.getMain_postalcode() );
			cart.setMain_postalcode1( getDivideStr( sending.getMain_postalcode(), 3)[0] );
			cart.setMain_postalcode2( getDivideStr( sending.getMain_postalcode(), 3)[1] );
			
			cart.setTicketing( sending.getTicketing() );
			cart.setPayment_way( sending.getPayment_way() );
			
			cart.setSending_month( sending.getDeriver_month() );
			cart.setSending_day( sending.getDeriver_day() );
			cart.setSending_time( sending.getDeriver_time(), true);
		}catch(Exception ex){	ex.printStackTrace();	}
		return cart;
	}
	private static String[] getDivide( String str ){
		String[] reStr = {"", ""};
		try{
			String t1 = str.substring(0, str.indexOf('\t'));
			String t2 = str.substring( str.lastIndexOf( '\t' )).trim();
			reStr[0] = t1;
			reStr[1] = t2;
		}catch(Exception ex){}
		return reStr;
	}
	private static String[] getDivideStr( String str, int position){
		String[] reStr = {"", ""};
		try{
			String t1 = str.substring(0, position );
			String t2 = str.substring( position ).trim();
			reStr[0] = t1;
			reStr[1] = t2;
		}catch(Exception ex){}
		return reStr;
	}
	/**
	 * Returns the alltotal.
	 * @return int
	 */
	public int getAlltotal() {
		return alltotal;
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
	 * Returns the total.
	 * @return int
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Sets the alltotal.
	 * @param alltotal The alltotal to set
	 */
	public void setAlltotal(int alltotal) {
		this.alltotal = alltotal;
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
	 * Sets the total.
	 * @param total The total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * Returns the sending_ok.
	 * @return boolean
	 */
	public boolean isSending_ok() {
		return sending_ok;
	}

	/**
	 * Sets the sending_ok.
	 * @param sending_ok The sending_ok to set
	 */
	public void setSending_ok(boolean sending_ok) {
		this.sending_ok = sending_ok;
	}

}
