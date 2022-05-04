package jp.co.lastminute.Dflight.allot;

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
import jp.co.lastminute.Dflight.Property;


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
	private String[] from = {};
	private String[] to = {};
	private String[] flightNo = {};
	private String[] flightName = {};
	private String[] fromName = {};
	private String[] toName = {};
	private String[] depdate = {};
	private String[] depTime = {};
	private String[] depTimeCode = {};
	private String[] arvTime = {};

	private ParseFormat pf = null;
	private DataFormat df = null;
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
    			if( form.flightNo[i].length() > 0 ){
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
			
			
			System.err.println( "<-----------Cart In ---->" + result + "<------>" ) ;
			return result.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";	
	}
	/**
	 * 
	 */
	public String[] genBuyProp(Object object) throws Exception{
		ProductRegistrationFrom form = (ProductRegistrationFrom)object;
		int size = this.from.length;
		String[] reStr = new String[ size ];
		for(int i=0; i<size; i++){
			if(( this.flightNo[i] != null )&&( this.flightNo[i].length() > 0)){
				StringBuffer result = new StringBuffer();
				result.append("<FLIGHT>\n");
				result.append("<FROM>" + this.from[i] + "</FROM>\n");
				result.append("<TO>" + this.to[i] + "</TO>\n");
				result.append("<FLIGHTNO>" + this.flightNo[i] + "</FLIGHTNO>\n");
				result.append("<FLIGHTNAME>" + this.flightName[i] + "</FLIGHTNAME>\n");
				result.append("<FROMNAME>" + this.fromName[i] + "</FROMNAME>\n");
				result.append("<TONAME>" + this.toName[i] + "</TONAME>\n");
				result.append("<DEPDATE>" + this.depdate[i] + "</DEPDATE>\n");
				result.append("<DEPDATE_STR>" + df.getDateTime4WWW( this.depdate[i] ) + "</DEPDATE_STR>\n");
				result.append("<DEPTIME>" + pf.ZeroUP( this.depTime[i], 4 )+ "</DEPTIME>\n");
				result.append("<DEPTIME_STR>" + getDeptimeStr( pf.ZeroUP( this.depTime[i], 4 ) ) + "</DEPTIME_STR>\n");
				result.append("<DEPTIMECODE>" + this.depTimeCode[i] + "</DEPTIMECODE>\n");
				result.append("<ARVTIME>" + this.arvTime[i] + "</ARVTIME>\n");
				result.append("<ADULT>" + this.getAdult( 0 ) + "</ADULT>\n");
				result.append("<CHILD>" + this.getChild( 0 ) + "</CHILD>\n");
				result.append("<INFANT>" + this.getInfant( 0 ) + "</INFANT>\n");
				result.append("</FLIGHT>");
				result.append("<VIEW_DATE>" + df.getDateTime4WWW( this.depdate[i] ) + "</VIEW_DATE>");
				result.append("<VIEW_TIME><![CDATA[" + getDeptimeStr( pf.ZeroUP( this.depTime[i], 4 ) ) + "発]]></VIEW_TIME>");
				result.append("<TOKKI><![CDATA[" + Property.tokki + "]]></TOKKI>");
				result.append("<VIEW_TITLE><![CDATA[("+ this.flightNo[i] + ":" + this.fromName[i] + " - " + this.toName[i] + ")]]></VIEW_TITLE>");
				reStr[i] = result.toString();
			}
			
		}
		return reStr;
	}
	/**
	 * Returns the arvTime.
	 * @return String[]
	 */
	public String[] getArvTime() {
		return arvTime;
	}

	/**
	 * Returns the depdate.
	 * @return String[]
	 */
	public String[] getDepdate() {
		return depdate;
	}

	/**
	 * Returns the depTime.
	 * @return String[]
	 */
	public String[] getDepTime() {
		return depTime;
	}

	/**
	 * Returns the depTimeCode.
	 * @return String[]
	 */
	public String[] getDepTimeCode() {
		return depTimeCode;
	}

	/**
	 * Returns the flightName.
	 * @return String[]
	 */
	public String[] getFlightName() {
		return flightName;
	}

	/**
	 * Returns the flightNo.
	 * @return String[]
	 */
	public String[] getFlightNo() {
		return flightNo;
	}

	/**
	 * Returns the from.
	 * @return String[]
	 */
	public String[] getFrom() {
		return from;
	}

	/**
	 * Returns the fromName.
	 * @return String[]
	 */
	public String[] getFromName() {
		return fromName;
	}

	/**
	 * Returns the to.
	 * @return String[]
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * Returns the toName.
	 * @return String[]
	 */
	public String[] getToName() {
		return toName;
	}

	/**
	 * Sets the arvTime.
	 * @param arvTime The arvTime to set
	 */
	public void setArvTime(String[] arvTime) {
		this.arvTime = arvTime;
	}

	/**
	 * Sets the depdate.
	 * @param depdate The depdate to set
	 */
	public void setDepdate(String[] depdate) {
		this.depdate = depdate;
	}

	/**
	 * Sets the depTime.
	 * @param depTime The depTime to set
	 */
	public void setDepTime(String[] depTime) {
		this.depTime = depTime;
	}

	/**
	 * Sets the depTimeCode.
	 * @param depTimeCode The depTimeCode to set
	 */
	public void setDepTimeCode(String[] depTimeCode) {
		this.depTimeCode = depTimeCode;
	}

	/**
	 * Sets the flightName.
	 * @param flightName The flightName to set
	 */
	public void setFlightName(String[] flightName) throws Exception{
		this.flightName = getConv2Sjis( flightName );
	}

	/**
	 * Sets the flightNo.
	 * @param flightNo The flightNo to set
	 */
	public void setFlightNo(String[] flightNo) {
		this.flightNo = flightNo;
	}

	/**
	 * Sets the from.
	 * @param from The from to set
	 */
	public void setFrom(String[] from) {
		this.from = from;
	}

	/**
	 * Sets the fromName.
	 * @param fromName The fromName to set
	 */
	public void setFromName(String[] fromName) throws Exception{
		this.fromName = getConv2Sjis( fromName );
	}

	/**
	 * Sets the to.
	 * @param to The to to set
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * Sets the toName.
	 * @param toName The toName to set
	 */
	public void setToName(String[] toName) throws Exception{
		this.toName = getConv2Sjis( toName );
	}
}
