package jp.co.lastminute.Entertainment.allot;

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
	private String[] sku_name = {};
	private String[] times = {};
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
    			if( form.sku_name[i].length() > 0 ){
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
		int size = this.sku_name.length;
		String[] reStr = new String[ size ];
		for(int i=0; i<size; i++){
			if(( this.sku_name[i] != null )&&( this.sku_name[i].length() > 0)){
				StringBuffer result = new StringBuffer();
				result.append("<TICKET>\n");
				result.append("<SKU_NAME><![CDATA[" + this.sku_name[i] + "]]></SKU_NAME>\n");
				result.append("<TIMES>" + this.times[0] + "</TIMES>\n");
				result.append("<SALEDATE>" + this.salesdate[0] + "</SALEDATE>\n");
				result.append("</TICKET>");
				result.append("<VIEW_DATE>" + df.getDateTime4WWW( this.salesdate[0] ) + "</VIEW_DATE>");
				result.append("<VIEW_TIME>" + getDeptimeStr( pf.ZeroUP( this.times[0], 4 ) ) + "</VIEW_TIME>");
				result.append("<VIEW_TITLE><![CDATA[("+ this.sku_name[ 0 ] + ")]]></VIEW_TITLE>");
				reStr[i] = result.toString();
			}
			
		}
		return reStr;
	}


	/**
	 * Returns the sku_name.
	 * @return String[]
	 */
	public String[] getSku_name() {
		return sku_name;
	}

	/**
	 * Returns the times.
	 * @return String[]
	 */
	public String[] getTimes() {
		return times;
	}

	/**
	 * Sets the sku_name.
	 * @param sku_name The sku_name to set
	 */
	public void setSku_name(String[] sku_name) throws IOException{
		this.sku_name = getConv2Sjis( sku_name );
	}

	/**
	 * Sets the times.
	 * @param times The times to set
	 */
	public void setTimes(String[] times) {
		this.times = times;
	}

}
