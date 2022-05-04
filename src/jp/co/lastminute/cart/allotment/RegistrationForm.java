package jp.co.lastminute.cart.allotment;

import java.io.*;
import java.util.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RegistrationForm extends ActionForm{
	
	public String[] agtcode = {};;		//
    public String[] connectionStyle = {};	//new
    public String[] userId = {};			//new
    public String[] orderNumber = {};	//new
    public String[] subOrderNumber = {};	//
    public String[] productTypeCode = {};
    public String[] product_id = {};		//	
    public String[] agentproductcode = {};	//
    public String[] title = {};	
    public String[] salesdate = {};
    public String[] guaranteeFlag = {};	//new
    public String[] lastsale = {};
    public String[] endsale = {};
    public String[] price = {};
    public String[] throwtypeFlag = {};
    public String[] pax = {};
    public String[] etc = {};
    public String[] eta = {};
    public String[] tax = {};
    public String[] adult = {};
    public String[] infant = {};
    public String[] child  = {};
    public String[] sending = {};		//new
    public String[] sending_tax = {};	//new
    //エラーコメント
    protected Vector error_comm = new Vector();
    ///
    public String[] sending_calc_unit = {};
    
    /**
     *  サブオーダー部分を作成
     */
    public String[] getSuborderXmlGenalate(){
    	String[] reStr = null;
    	try{
    		int size = agtcode.length;
    		Vector reVec = new Vector();
    		int count =0;
    		for(int i=0; i<size ; i++ ){
    			if( this.agtcode[i].length() > 0 ){
		    		StringBuffer result = new StringBuffer();
		    		result.append("<SUB_ORDER_NO>" + getSubOrderNumber( i ) + "</SUB_ORDER_NO>\n");
		    		result.append("<ORDER_XSL_FILE></ORDER_XSL_FILE>\n");
		    		result.append("<ORDER_NO>" + getOrderNumber(i) + "</ORDER_NO>\n");
		    		result.append("<PRODUCT_TYPE_CD>" + getProductTypeCode(i) + "</PRODUCT_TYPE_CD>\n");
		    		result.append("<AGT_CD>" + this.agtcode[i] + "</AGT_CD>\n");
		    		result.append("<PROD_CD>" + this.product_id [i]+ "</PROD_CD>\n");
		    		result.append("<AGT_PROD_CD><![CDATA[" + getAgentproductcode( i ) + "]]></AGT_PROD_CD>\n");
		    		result.append("<TITLE><![CDATA[" + this.title[i] + "]]></TITLE>\n");
		    		result.append("<SALES_DATE>" + getSalesdate(i) + "</SALES_DATE>\n");
		    		result.append("<GUARANTEE_FLG>" + getGuaranteeFlag(i) + "</GUARANTEE_FLG>\n");
		    		result.append("<PRICE>" + this.price[i] + "</PRICE>\n");
		    		result.append("<THROW_FLG>" + getThrowtypeFlag(i) + "</THROW_FLG>\n");
		    		result.append("<LAST_SALE>" + getLastsaleStr( i ) + "</LAST_SALE>\n");
		    		result.append("<END_SALE>" + getEndsaleStr( i ) + "</END_SALE>\n");
		    		result.append("<PAX>" + getPax(i) + "</PAX>\n");
		    		result.append("<ADULT>" + getAdult(i) + "</ADULT>\n");
		    		result.append("<CHILD>" + getChild(i) + "</CHILD>\n");
		    		result.append("<INFANT>" + getInfant(i) + "</INFANT>\n");
		    		result.append("<ETC><![CDATA[" + getEtcStr( i ) + "]]></ETC>\n");
		    		result.append("<ETA><![CDATA[" + getEtaStr( i ) + "]]></ETA>\n");
		    		result.append("<TAX>" + this.tax[i] + "</TAX>\n");
		    		result.append("<SENDING>" + this.sending[i] + "</SENDING>\n");
		    		result.append("<SENDING_TAX>" + this.sending_tax[i] + "</SENDING_TAX>\n");
		    		result.append("<SENDING_CALC_UNIT>" + getSending_calc_unit( i ) + "</SENDING_CALC_UNIT>\n");
		    		reVec.add( result );
		    		count++;
    			}
    		}
    		reStr = new String[ count ];
    		for(int j=0; j<count; j++){
    			reStr[j] = ((StringBuffer)reVec.get(j)).toString();
    		}
    	}catch(Exception ex){	ex.printStackTrace();	}
    	return reStr;	
    }
    /**
     * 最終販売
     */
    public String getLastsaleStr( int num ){
    	String reStr = "";
    	if( lastsale.length != 0 ){
    		try{
    			reStr = lastsale[ num ];
    		}catch(Exception ex){}
    	}
    	return reStr;    	
    }
    /**
     * 手仕舞い？
     */
    public String getEndsaleStr( int num ){
    	String reStr = "";
    	if( endsale.length != 0 ){
    		try{
    			reStr = endsale[ num ];
    		}catch(Exception ex){}
    	}
    	return reStr;    	
    }
    public String getSending_calc_unit( int row ) throws Exception{
    	if( sending_calc_unit.length == 0 ){
    		return "0";
    	}
    	if( row > sending_calc_unit.length ){
    		return sending_calc_unit[0];	
    	}
    	return sending_calc_unit[row];
    	
    }
	public String getEtcStr( int row ) throws Exception{
		if( etc.length == 0 ){
			return "";
		}
		if( row > etc.length ){
			return this.etc[0];
		}
		return this.etc[row];
	}
	public String getEtaStr( int row ) throws Exception{
		if( eta.length == 0 ){
			return "";
		}
		if( row > eta.length ){
			return this.eta[0];
		}
		return this.eta[row];
	}
	
	public String getAgentproductcode( int row ){
		if( agentproductcode.length == 0 ){
			return "";	
		}
		if( row > subOrderNumber.length ){
			return agentproductcode[ 0 ];
		}
		return "";
	}
	public String getSubOrderNumber( int row ){
		if( subOrderNumber.length == 0 ){
			return "0";	
		}
		if( row > subOrderNumber.length ){
			return subOrderNumber[ 0 ];
		}
		return "0";
	}
	/**
	 * Returns the agentproductcode.
	 * @return String[]
	 */
	public String[] getAgentproductcode() {
		return agentproductcode;
	}

	/**
	 * Returns the agtcode.
	 * @return String[]
	 */
	public String[] getAgtcode() {
		return agtcode;
	}
	public String getAgtcodeTarget() throws IOException {
		return agtcode[0];
	}

	/**
	 * Returns the connectionStyle.
	 * @return String[]
	 */
	public String[] getConnectionStyle() {
		return connectionStyle;
	}
	public String getConnectionStyleTarget() throws IOException {
		return connectionStyle[0];
	}

	/**
	 * Returns the eta.
	 * @return String[]
	 */
	public String[] getEta() {
		return eta;
	}
	public String getEta( int num ) {
		try{
			return eta[num];
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	/**
	 * Returns the etc.
	 * @return String[]
	 */
	public String[] getEtc() {
		return etc;
	}
	public String getEtc( int num ) {
		try{
			return etc[num];
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	/**
	 * Returns the guaranteeFlag.
	 * @return String[]
	 */
	public String[] getGuaranteeFlag() {
		return guaranteeFlag;
	}

	/**
	 * Returns the lastsale.
	 * @return String[]
	 */
	public String[] getLastsale() {
		return lastsale;
	}

	/**
	 * Returns the orderNumber.
	 * @return String[]
	 */
	public String[] getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Returns the pax.
	 * @return String[]
	 */
	public String[] getPax() {
		return pax;
	}

	/**
	 * Returns the price.
	 * @return String[]
	 */
	public String[] getPrice() {
		return price;
	}

	/**
	 * Returns the product_id.
	 * @return String[]
	 */
	public String[] getProduct_id() {
		return product_id;
	}

	/**
	 * Returns the productTypeCode.
	 * @return String[]
	 */
	public String[] getProductTypeCode() {
		return productTypeCode;
	}
	public String getProductTypeCodeTarget() throws IOException{
		return productTypeCode[0];
	}
	/**
	 * Returns the sending.
	 * @return String[]
	 */
	public String[] getSending() {
		return sending;
	}

	/**
	 * Returns the sending_tax.
	 * @return String[]
	 */
	public String[] getSending_tax() {
		return sending_tax;
	}

	/**
	 * Returns the subOrderNumber.
	 * @return String[]
	 */
	public String[] getSubOrderNumber() {
		return subOrderNumber;
	}

	/**
	 * Returns the tax.
	 * @return String[]
	 */
	public String[] getTax() {
		return tax;
	}

	/**
	 * Returns the throwtypeFlag.
	 * @return String[]
	 */
	public String[] getThrowtypeFlag() {
		return throwtypeFlag;
	}

	/**
	 * Returns the title.
	 * @return String[]
	 */
	public String[] getTitle() {
		return title;
	}

	/**
	 * Returns the userId.
	 * @return String[]
	 */
	public String[] getUserId() {
		return userId;
	}

	/**
	 * Sets the agentproductcode.
	 * @param agentproductcode The agentproductcode to set
	 */
	public void setAgentproductcode(String[] agentproductcode) {
		this.agentproductcode = agentproductcode;
	}

	/**
	 * Sets the agtcode.
	 * @param agtcode The agtcode to set
	 */
	public void setAgtcode(String[] agtcode) {
		this.agtcode = agtcode;
	}

	/**
	 * Sets the connectionStyle.
	 * @param connectionStyle The connectionStyle to set
	 */
	public void setConnectionStyle(String[] connectionStyle) {
		this.connectionStyle = connectionStyle;
	}

	/**
	 * Sets the eta.
	 * @param eta The eta to set
	 */
	public void setEta(String[] eta) throws IOException{
		this.eta = getConv2Sjis( eta );
	}

	/**
	 * Sets the etc.
	 * @param etc The etc to set
	 */
	public void setEtc(String[] etc) throws IOException{
		this.etc = getConv2Sjis( etc ); 
	}

	/**
	 * Sets the guaranteeFlag.
	 * @param guaranteeFlag The guaranteeFlag to set
	 */
	public void setGuaranteeFlag(String[] guaranteeFlag) {
		this.guaranteeFlag = guaranteeFlag;
	}

	/**
	 * Sets the lastsale.
	 * @param lastsale The lastsale to set
	 */
	public void setLastsale(String[] lastsale) {
		this.lastsale = lastsale;
	}

	/**
	 * Sets the orderNumber.
	 * @param orderNumber The orderNumber to set
	 */
	public void setOrderNumber(String[] orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * Sets the pax.
	 * @param pax The pax to set
	 */
	public void setPax(String[] pax) {
		this.pax = pax;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(String[] price) {
		this.price = price;
	}

	/**
	 * Sets the product_id.
	 * @param product_id The product_id to set
	 */
	public void setProduct_id(String[] product_id) {
		this.product_id = product_id;
	}

	/**
	 * Sets the productTypeCode.
	 * @param productTypeCode The productTypeCode to set
	 */
	public void setProductTypeCode(String[] productTypeCode) {
		this.productTypeCode = productTypeCode;
	}

	/**
	 * Sets the sending.
	 * @param sending The sending to set
	 */
	public void setSending(String[] sending) {
		this.sending = sending;
	}

	/**
	 * Sets the sending_tax.
	 * @param sending_tax The sending_tax to set
	 */
	public void setSending_tax(String[] sending_tax) {
		this.sending_tax = sending_tax;
	}

	/**
	 * Sets the subOrderNumber.
	 * @param subOrderNumber The subOrderNumber to set
	 */
	public void setSubOrderNumber(String[] subOrderNumber) {
		this.subOrderNumber = subOrderNumber;
	}

	/**
	 * Sets the tax.
	 * @param tax The tax to set
	 */
	public void setTax(String[] tax) {
		this.tax = tax;
	}

	/**
	 * Sets the throwtypeFlag.
	 * @param throwtypeFlag The throwtypeFlag to set
	 */
	public void setThrowtypeFlag(String[] throwtypeFlag) {
		this.throwtypeFlag = throwtypeFlag;
	}

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String[] title) throws IOException{
		this.title = getConv2Sjis( title );
	}

	/**
	 * Sets the userId.
	 * @param userId The userId to set
	 */
	public void setUserId(String[] userId) {
		this.userId = userId;
	}
	/**
	 * 
	 */
	public String getConv2Sjis( String str ) throws IOException{
		return new String(str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }
    /**
	 * 
	 */
	public String[] getConv2Sjis( String[] str ) throws IOException{
		String[] reStr = new String[ str.length ];
		for(int i=0; i<reStr.length; i++){
			reStr[ i ] = new String(str[i].getBytes("ISO-8859-1"), "Shift_JIS" ) ;
		}
		return reStr;
    }
    /**
     * 
     */
    public String getConnectionStyle(int i) throws IOException{
 		return connectionStyle[0];
    }
    public String getUserId( int i)throws IOException{
    	return userId[0];	
    }
    public String getPax(int i) throws IOException{
 		return pax[0];
    }
    public String getOrderNumber(int i) throws IOException{
 		return orderNumber[0];
    }
    public String getThrowtypeFlag(int i) throws IOException{
    	if( throwtypeFlag.length == 0 ){
    		return "0";	
    	}
 		return throwtypeFlag[0];
    }
    public String getGuaranteeFlag(int i) throws IOException{
    	if ( guaranteeFlag.length == 0 ){
    		return "1";	
    	}
 		return guaranteeFlag[0];
    }
    public String getProductTypeCode(int i) throws IOException{
 		return productTypeCode[0];
    }
    public String getChild(int i){
    	try{
 			return child[0];
    	}catch(Exception ex){}
    	return "0";
    }
    public String getAdult(int i ){
    	try{
 			return adult[0];
    	}catch(Exception ex){}
    	return "0";
    }
    public String getInfant(int i){
    	try{
 			return infant[0];
 		}catch(Exception ex){}
 		return "0";
    }
	/**
	 * Returns the child.
	 * @return String[]
	 */
	public String[] getChild() {
		return child;
	}

	/**
	 * Returns the infant.
	 * @return String[]
	 */
	public String[] getInfant() {
		return infant;
	}

	/**
	 * Sets the child.
	 * @param child The child to set
	 */
	public void setChild(String[] child) {
		this.child = child;
	}

	/**
	 * Sets the infant.
	 * @param infant The infant to set
	 */
	public void setInfant(String[] infant) {
		this.infant = infant;
	}

	/**
	 * Returns the adult.
	 * @return String[]
	 */
	public String[] getAdult() {
		return adult;
	}

	/**
	 * Sets the adult.
	 * @param adult The adult to set
	 */
	public void setAdult(String[] adult) {
		this.adult = adult;
	}

	/**
	 * Returns the endsale.
	 * @return String[]
	 */
	public String[] getEndsale() {
		return endsale;
	}

	/**
	 * Sets the endsale.
	 * @param endsale The endsale to set
	 */
	public void setEndsale(String[] endsale) {
		this.endsale = endsale;
	}
		/**
	 * 
	 */
	public static String getDeptimeStr( String set){
		String reStr = "";
		try{
			reStr = set.substring(0, 2) + ":" + set.substring(2);
		}catch(Exception ex){}
		return reStr;		
	}

	/**
	 * Returns the checkinDate.
	 * @return String[]
	 */
	public String[] getSalesdate() {
		return salesdate;
	}
	protected String getSalesdate(int i){
		String reStr = "";
		if( salesdate.length != 0 ){
			try{
				return salesdate[i];
			}catch(Exception ex){	ex.printStackTrace();	}
		}
		return reStr;
	}
	/**
	 * Sets the checkinDate.
	 * @param checkinDate The checkinDate to set
	 */
	public void setSalesdate(String[] salesdate) {
		this.salesdate = salesdate;
	}

	/**
	 * Returns the sending_calc_unit.
	 * @return String[]
	 */
	public String[] getSending_calc_unit() {
		return sending_calc_unit;
	}

	/**
	 * Sets the sending_calc_unit.
	 * @param sending_calc_unit The sending_calc_unit to set
	 */
	public void setSending_calc_unit(String[] sending_calc_unit) {
		this.sending_calc_unit = sending_calc_unit;
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
