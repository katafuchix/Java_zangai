package jp.co.lastminute.Gift.allot;

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
	//
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
    			if( form.pax[i].length() > 0 ){
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
		int size = this.pax.length;
		String[] reStr = new String[ size ];
		for(int i=0; i<size; i++){
			if(( this.pax[i] != null )&&( this.pax[i].length() > 0)){
				StringBuffer result = new StringBuffer();
				result.append("<GIFT>\n");
				result.append("<PRODCUT_NAME><![CDATA[" + this.title[i] + "]]></PRODCUT_NAME>\n");
				result.append("<ETA>" + getEtaStrforGift(i) + "</ETA>\n");
				result.append("</GIFT>");
				result.append("<VIEW_DATE></VIEW_DATE>");
				result.append("<VIEW_TIME></VIEW_TIME>");
				result.append("<VIEW_TITLE><![CDATA["+ getEtaStrforGift( i ) + "]]></VIEW_TITLE>");
				reStr[i] = result.toString();
			}
			
		}
		return reStr;
	}
	private String getEtaStrforGift( int num ){
		if( this.eta == null ){
			return "";	
		}
		if( this.eta.length == 0 ){
			return "";
		}
		try{
			if( this.eta[ num ].length() > 0 ){
				return "(" + this.eta[ num ] + ")";
			}
		}catch(Exception ex){}
		return "";
	}
}
