package jp.co.lastminute.Restrant.allot;

import java.io.*;
import java.util.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

import jp.co.lastminute.cart.allotment.*;
import jp.co.yobrain.util.ParseFormat;
import jp.co.yobrain.util.DataFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ProductRegistrationFrom extends RegistrationForm implements ParameterValidationInterface{
	//必要なパラメータを記述
	private String[] cources = {};
	private String[] cources_count = {};
	private String[] cources_price = {};
	private String[] cources_tax = {};
	private String[] options = {};
	private String[] options_count = {};
	private String[] options_price = {};
	private String[] options_tax = {};
	private String[] chkIndate = {};
	private String[] arivaltime = {};
	private String[] eventcode = {};
	private String[] event_comment = {};
	private String[] main_person = {};
	private String[] depTimeCode = {};
	private String[] sup_person = {};
	private String[] mister = {};
	private String[] miss = {};
	private String[] child = {};
	private String[] infant = {};
	private String[] years = {};
	private String[] month = {};
	private String[] days = {};
	private String[] hours = {};
	private String[] times = {};
	private int sheat = 0;

	private ParseFormat pf = null;
	private DataFormat df = null;
	
	private String tempCourcesStr = "";
	private String tempOptionsStr = "";
	////
	private static final String title_co = "コース　";
	private static final String title_op = "オプション　";
	private static final String tempDemi_in = "/";
	/**
	 * 
	 */    /**
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
		    		result.append("<SUB_ORDER_NO>" + this.subOrderNumber[i] + "</SUB_ORDER_NO>\n");
		    		result.append("<ORDER_XSL_FILE></ORDER_XSL_FILE>\n");
		    		result.append("<ORDER_NO>" + getOrderNumber(i) + "</ORDER_NO>\n");
		    		result.append("<PRODUCT_TYPE_CD>" + getProductTypeCode(i) + "</PRODUCT_TYPE_CD>\n");
		    		result.append("<AGT_CD>" + this.agtcode[i] + "</AGT_CD>\n");
		    		result.append("<PROD_CD>" + this.product_id [i]+ "</PROD_CD>\n");
		    		result.append("<AGT_PROD_CD><![CDATA[" + this.agentproductcode[i] + "]]></AGT_PROD_CD>\n");
		    		result.append("<TITLE><![CDATA[" + this.title[i] + "]]></TITLE>\n");
		    		result.append("<SALES_DATE>" + getSalesdate(i) + "</SALES_DATE>\n");
		    		result.append("<GUARANTEE_FLG>" + getGuaranteeFlag(i) + "</GUARANTEE_FLG>\n");
		    		result.append("<PRICE>" + getRestrantPrice() + "</PRICE>\n");
		    		result.append("<THROW_FLG>" + getThrowtypeFlag(i) + "</THROW_FLG>\n");
		    		result.append("<LAST_SALE>" + this.lastsale[i] + "</LAST_SALE>\n");
		    		result.append("<END_SALE>" + this.endsale[i] + "</END_SALE>\n");
		    		result.append("<PAX>" + getPax(i) + "</PAX>\n");
		    		result.append("<SHEAT>" + getSheats() + "</SHEAT>\n");
		    		result.append("<ADULT>" + getAdult(i) + "</ADULT>\n");
		    		result.append("<CHILD>" + getChild(i) + "</CHILD>\n");
		    		result.append("<INFANT>" + getInfant(i) + "</INFANT>\n");
		    		result.append("<ETC><![CDATA[" + getEtc(i) + "]]></ETC>\n");
		    		result.append("<ETA><![CDATA[" + getEta(i) + "]]></ETA>\n");
		    		result.append("<TAX>" + getRestrantTax() + "</TAX>\n");
		    		result.append("<SENDING>" + "0" + "</SENDING>\n");
		    		result.append("<SENDING_TAX>" + "0" + "</SENDING_TAX>\n");
		    		
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
	public String generateXML( Object object, int userId ){
		try{
			ProductRegistrationFrom form = (ProductRegistrationFrom)object;
			//XMLを記述するコードを記述
			String[] buyprop = genBuyProp( form );
			StringBuffer result = new StringBuffer();
			result.append("<ORDER>\n");
			result.append("<ORDER_NO>" + this.getOrderNumber( 0 ) + "</ORDER_NO>\n");
    		result.append("<USER_ID>" + this.getUserId( 0 ) + "</USER_ID>\n");
    		for( int i=0; i<getSuborderXmlGenalate().length; i++ ){
    			if( form.cources[i].length() > 0 ){
		    		result.append("<SUB_ORDER>\n");
					result.append( getSuborderXmlGenalate()[i] );
					//BUY_PROPを作成
					result.append( "<BUY_PROP>\n" );
					result.append( buyprop[i] );
					result.append( "</BUY_PROP>\n" );
					result.append("</SUB_ORDER>\n");
    			}
    		}
			result.append("</ORDER>\n");
			System.err.println( "<-----------Regist Of Restrant ---->\n" + result + "<------>" ) ;
			return result.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";	
	}
	/**
	 * 
	 */
	public String[] genBuyProp(Object object) throws Exception{
		ProductRegistrationFrom form = (ProductRegistrationFrom)object;
		int size = this.pax.length;
		String[] reStr = new String[ size ];
		for(int i=0; i<size; i++){
			if(( this.pax[i] != null )&&( this.pax[i].length() > 0)){
				StringBuffer result = new StringBuffer();
				result.append("<RESTRANT>\n");
				result.append("<COURCES>" + getCourcesXmlStr() + "</COURCES>\n");
				result.append("<OPTIONS>" + getOptionsXmlStr() + "</OPTIONS>\n");
				result.append("<CHKINDATE>" + this.chkIndate[i] + "</CHKINDATE>\n");
				result.append("<ARIVALTIME>" + pf.ZeroUP( this.arivaltime[i], 4 ) + "</ARIVALTIME>\n");
				result.append("<EVENTCODE>" + getEventcode(i) + "</EVENTCODE>\n");
				result.append("<EVENT_COMMENT>" + getEvent_comment(i) + "</EVENT_COMMENT>\n");
				result.append("<CHKINDATE_STR>" + df.getDateTime4WWW( this.chkIndate[i] ) + "</CHKINDATE_STR>\n");
				result.append("<ARIVALTIME_STR>" + getDeptimeStr( pf.ZeroUP( this.arivaltime[i], 4 ) )+ "</ARIVALTIME_STR>\n");
				result.append("</RESTRANT>");
				result.append("<VIEW_DATE>" + df.getDateTime4WWW( this.chkIndate[i] ) + "</VIEW_DATE>");
				result.append("<VIEW_TIME>" + getDeptimeStr( pf.ZeroUP( this.arivaltime[i], 4 ) ) + "</VIEW_TIME>");
				if( this.tempOptionsStr.length() > 0 ){
					result.append("<VIEW_TITLE><![CDATA[("+ this.tempOptionsStr + ")]]></VIEW_TITLE>");
				}
				result.append("<VIEW_TITLES><![CDATA[("+ this.tempCourcesStr + ")]]></VIEW_TITLES>");
				reStr[i] = result.toString();
			}
		}
		return reStr;
	}
	/**
	 * 
	 */
	public String getCourcesXmlStr(){
		String reStr = "";
		try{
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<cources.length; i++){
				if(( cources[i] != null)
				&&( cources[i].length() > 0 )){
					sb.append( "<COURSE>\n" );
					sb.append( "<NAMES><![CDATA[" + this.cources[i] + "]]></NAMES>\n");
					sb.append( "<PRICES>" + pf.ToPriceFormat( this.cources_price[i]) + "</PRICES>\n");
					sb.append( "<TAX>" + pf.ToPriceFormat( this.cources_tax[i]) + "</TAX>\n");
					sb.append( "<DISHES>" + this.cources_count[i] + "</DISHES>\n");
					sb.append( "</COURSE>\n" );
					if( tempCourcesStr.length() > 0 ){
						tempCourcesStr += tempDemi_in;
					}else{
						tempCourcesStr = title_co;
					}
					tempCourcesStr += this.cources[i] + " "
								   + pf.ToPriceFormat( this.cources_price[i] ) + " x " 
								   + this.cources_count[i] + " ";
				}
			}
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 
	 */
	public String getOptionsXmlStr(){
		String reStr = "";
		try{
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<options.length; i++){
				if( (options[i] != null )
				&&(options[i].length() > 0 ) ){
					sb.append( "<OPTION>\n" );
					sb.append( "<NAMES><![CDATA[" + this.options[i] + "]]></NAMES>\n");
					sb.append( "<PRICES>" + pf.ToPriceFormat( this.options_price[i]) + "</PRICES>\n");
					sb.append( "<TAX>" + pf.ToPriceFormat(this.options_tax[i]) + "</TAX>\n");
					sb.append( "<DISHES>" + this.options_count[i] + "</DISHES>\n");
					sb.append( "</OPTION>\n" );
				if( tempOptionsStr.length() > 0 ){
					tempOptionsStr += tempDemi_in;
				}else{
					tempOptionsStr = title_op;
				}
				tempOptionsStr += this.options[i] + " "
							   + pf.ToPriceFormat( this.options_price[i] ) + " x " 
							   + this.options_count[i] + " ";
				}
			}
			reStr = sb.toString();			
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	///////////////////////////////////////////////////////////////
	/**
	 * シートの計算
	 */
	private int getSheats(){
		try{
			for(int pi = 0; pi<this.cources_count.length; pi++){
				if( cources_price[pi].length() > 0){
					sheat += Integer.parseInt( cources_count[pi] );
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return sheat;
	}
	/**
	 * 合計金額の計算
	 */
	private int getRestrantPrice() {
		int price = 0;
		try{
			for(int pi = 0; pi<this.cources_count.length; pi++){
				if( cources_price[pi].length() > 0){
				price += Integer.parseInt( cources_count[pi] )
					  * Integer.parseInt( this.cources_price[pi] );
				}
			}
			for(int oi = 0; oi<this.options_count.length; oi++){
				if( options_price[oi].length() > 0 ){
				price += Integer.parseInt( options_count[oi] )
					  * Integer.parseInt( this.options_price[oi] );
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return price;
	}
	/**
	 * 合計ＴＡＸ
	 */
	private int getRestrantTax() {
		int price = 0;
		try{
			for(int pi = 0; pi<this.cources_count.length; pi++){
				if( cources_tax[pi].length() > 0 ){
				price += Integer.parseInt( cources_count[pi] )
					  * Integer.parseInt( this.cources_tax[pi] );
				}
			}
			for(int oi = 0; oi<this.options_count.length; oi++){
				if( options_tax[oi].length() > 0 ){
				price += Integer.parseInt( options_count[oi] )
					  * Integer.parseInt( this.options_tax[oi] );
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return price;
	}
	/**
	 * 販売日の取得
	 */
	protected String getSalesdate(int i){
		String reStr = "00000000000";
		try{
			return this.chkIndate[i] + this.arivaltime[i];
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * ギャランティフラグ
	 */
	public String getGuaranteeFlag(int i) throws IOException{
 		return "1";
    }
	/**
	 * 
	 */
	public String getThrowtypeFlag(int i) throws IOException{
 		return "1";
    }
	
	///////////////////////////////////////////////////////////////
	/**
	 * Returns the arivaltime.
	 * @return String[]
	 */
	public String[] getArivaltime() {
		return arivaltime;
	}

	/**
	 * Returns the chkIndate.
	 * @return String[]
	 */
	public String[] getChkIndate() {
		return chkIndate;
	}

	/**
	 * Returns the cources.
	 * @return String[]
	 */
	public String[] getCources() {
		return cources;
	}

	/**
	 * Returns the depTimeCode.
	 * @return String[]
	 */
	public String[] getDepTimeCode() {
		return depTimeCode;
	}

	/**
	 * Returns the event_comment.
	 * @return String[]
	 */
	public String[] getEvent_comment() {
		return event_comment;
	}
	public String getEvent_comment(int num) {
		if(( this.event_comment == null )||(this.event_comment.length == 0)){
				return "";
		}
		try{
			return event_comment[num];
		}catch(Exception ex){}
		return "";
	}
	/**
	 * Returns the eventcode.
	 * @return String[]
	 */
	public String[] getEventcode() {
		return eventcode;
	}
	public String getEventcode(int num) {
		if(( this.eventcode == null )||(this.eventcode.length == 0)){
				return "";
		}
		try{
			return eventcode[num];
		}catch(Exception ex){}
		return "";
	}
	/**
	 * Returns the main_person.
	 * @return String[]
	 */
	public String[] getMain_person() {
		return main_person;
	}
	public String getMain_person(int num) {
		if(( this.main_person == null )||(this.main_person.length == 0)){
				return "0";
		}
		try{
			return main_person[num];
		}catch(Exception ex){}
		return "0";
	}
	/**
	 * Returns the options.
	 * @return String[]
	 */
	public String[] getOptions() {
		return options;
	}

	/**
	 * Returns the options_price.
	 * @return String[]
	 */
	public String[] getOptions_price() {
		return options_price;
	}

	/**
	 * Returns the sup_person.
	 * @return String[]
	 */
	public String[] getSup_person() {
		return sup_person;
	}
	public String getSup_person(int num) {
		if(( this.sup_person == null )||(this.sup_person.length == 0)){
				return "0";
		}
		try{
			return sup_person[num];
		}catch(Exception ex){}
		return "0";
	}
	/**
	 * Sets the arivaltime.
	 * @param arivaltime The arivaltime to set
	 */
	public void setArivaltime(String[] arivaltime) {
		this.arivaltime = arivaltime;
	}

	/**
	 * Sets the chkIndate.
	 * @param chkIndate The chkIndate to set
	 */
	public void setChkIndate(String[] chkIndate) {
		this.chkIndate = chkIndate;
	}

	/**
	 * Sets the cources.
	 * @param cources The cources to set
	 */
	public void setCources(String[] cources) {
		this.cources = cources;
	}

	/**
	 * Sets the depTimeCode.
	 * @param depTimeCode The depTimeCode to set
	 */
	public void setDepTimeCode(String[] depTimeCode) {
		this.depTimeCode = depTimeCode;
	}

	/**
	 * Sets the event_comment.
	 * @param event_comment The event_comment to set
	 */
	public void setEvent_comment(String[] event_comment) {
		this.event_comment = event_comment;
	}

	/**
	 * Sets the eventcode.
	 * @param eventcode The eventcode to set
	 */
	public void setEventcode(String[] eventcode) {
		this.eventcode = eventcode;
	}

	/**
	 * Sets the main_person.
	 * @param main_person The main_person to set
	 */
	public void setMain_person(String[] main_person) {
		this.main_person = main_person;
	}

	/**
	 * Sets the options.
	 * @param options The options to set
	 */
	public void setOptions(String[] options) {
		this.options = options;
	}

	/**
	 * Sets the options_price.
	 * @param options_price The options_price to set
	 */
	public void setOptions_price(String[] options_price) {
		this.options_price = options_price;
	}

	/**
	 * Sets the sup_person.
	 * @param sup_person The sup_person to set
	 */
	public void setSup_person(String[] sup_person) {
		this.sup_person = sup_person;
	}

	/**
	 * Returns the child.
	 * @return String[]
	 */
	public String[] getChild() {
		return child;
	}
	public String getChild(int num) {
		if(( this.child == null )||(this.child.length == 0)){
				return "0";
		}
		try{
			return child[num];
		}catch(Exception ex){}
		return "0";
	}
	/**
	 * Returns the infant.
	 * @return String[]
	 */
	public String[] getInfant() {
		return infant;
	}
	public String getInfant( int num ){
		if(( this.infant == null )||(this.infant.length == 0)){
				return "0";
		}
		try{
			return infant[num];
		}catch(Exception ex){}
		return "0";
	}
	/**
	 * Returns the miss.
	 * @return String[]
	 */
	public String[] getMiss() {
		return miss;
	}
	public String getMiss( int num ) {
		if(( this.miss == null )||(this.miss.length == 0)){
				return "0";
		}
		try{
			return miss[num];
		}catch(Exception ex){}
		return "0";
	}
	/**
	 * Returns the mister.
	 * @return String[]
	 */
	public String[] getMister() {
		return mister;
	}
	public String getMister( int num ) {
		if(( this.mister == null )||(this.mister.length == 0)){
			return "0";
		}
		try{
			return mister[num];
		}catch(Exception ex){}
		return "0";
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
	 * Sets the miss.
	 * @param miss The miss to set
	 */
	public void setMiss(String[] miss) {
		this.miss = miss;
	}

	/**
	 * Sets the mister.
	 * @param mister The mister to set
	 */
	public void setMister(String[] mister) {
		this.mister = mister;
	}

	/**
	 * Returns the days.
	 * @return String[]
	 */
	public String[] getDays() {
		return days;
	}

	/**
	 * Returns the hours.
	 * @return String[]
	 */
	public String[] getHours() {
		return hours;
	}

	/**
	 * Returns the month.
	 * @return String[]
	 */
	public String[] getMonth() {
		return month;
	}

	/**
	 * Returns the times.
	 * @return String[]
	 */
	public String[] getTimes() {
		return times;
	}

	/**
	 * Returns the years.
	 * @return String[]
	 */
	public String[] getYears() {
		return years;
	}

	/**
	 * Sets the days.
	 * @param days The days to set
	 */
	public void setDays(String[] days) {
		this.days = days;
	}

	/**
	 * Sets the hours.
	 * @param hours The hours to set
	 */
	public void setHours(String[] hours) {
		this.hours = hours;
	}

	/**
	 * Sets the month.
	 * @param month The month to set
	 */
	public void setMonth(String[] month) {
		this.month = month;
	}

	/**
	 * Sets the times.
	 * @param times The times to set
	 */
	public void setTimes(String[] times) {
		this.times = times;
	}

	/**
	 * Sets the years.
	 * @param years The years to set
	 */
	public void setYears(String[] years) {
		this.years = years;
	}

	/**
	 * Returns the cources_count.
	 * @return String[]
	 */
	public String[] getCources_count() {
		return cources_count;
	}

	/**
	 * Returns the cources_price.
	 * @return String[]
	 */
	public String[] getCources_price() {
		return cources_price;
	}

	/**
	 * Returns the cources_tax.
	 * @return String[]
	 */
	public String[] getCources_tax() {
		return cources_tax;
	}

	/**
	 * Returns the options_count.
	 * @return String[]
	 */
	public String[] getOptions_count() {
		return options_count;
	}

	/**
	 * Returns the options_tax.
	 * @return String[]
	 */
	public String[] getOptions_tax() {
		return options_tax;
	}

	/**
	 * Sets the cources_count.
	 * @param cources_count The cources_count to set
	 */
	public void setCources_count(String[] cources_count) {
		this.cources_count = cources_count;
	}

	/**
	 * Sets the cources_price.
	 * @param cources_price The cources_price to set
	 */
	public void setCources_price(String[] cources_price) {
		this.cources_price = cources_price;
	}

	/**
	 * Sets the cources_tax.
	 * @param cources_tax The cources_tax to set
	 */
	public void setCources_tax(String[] cources_tax) {
		this.cources_tax = cources_tax;
	}

	/**
	 * Sets the options_count.
	 * @param options_count The options_count to set
	 */
	public void setOptions_count(String[] options_count) {
		this.options_count = options_count;
	}

	/**
	 * Sets the options_tax.
	 * @param options_tax The options_tax to set
	 */
	public void setOptions_tax(String[] options_tax) {
		this.options_tax = options_tax;
	}

}
