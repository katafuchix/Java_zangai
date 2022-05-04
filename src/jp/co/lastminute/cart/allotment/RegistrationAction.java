package jp.co.lastminute.cart.allotment;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.util.MessageResources;

import javax.servlet.ServletException;

import javax.xml.transform.TransformerConfigurationException;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.user.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RegistrationAction extends Action {

	/**
	 * This contains the name which should be Used for storing the Response from the Domestic Hotel Cart Registration
	 */
	public static final String SESSION_NAME_RESULT_XML =
		"cartRegistrationResultXML";
	/**
	 * Action for the Allotments Check
	 * @param form a CartRegistrationForm
	 */
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		/////
		ActionForward resultForward; // For storing the result
		ActionErrors errors = new ActionErrors();

		int userId = 0;
            if( request.getSession().getAttribute( Constants.CartUser ) != null ){
            	userId = ((User)request.getSession().getAttribute( Constants.CartUser )).getUser().getUSER_ID();
            }
		String jSessionId = request.getSession().getId();
		String registrationAddress = "";
		try {
			// Checking for availability
			registrationAddress = getCartInAddress( request.getServerName(), jSessionId);
			SendCart sendCart =
				new SendCart(registrationAddress);
			String xmlString =
				((ParameterValidationInterface)form).generateXML( form, userId );
			System.err.println("<-----Registration:64--->" + "\n" + xmlString + "\n" + "<---->");
			String status = sendCart.sendOrder( form , xmlString);
			if (status == null) {
				resultForward = mapping.findForward("failure");
				return resultForward;
			}

			request.setAttribute(SESSION_NAME_RESULT_XML, status);
			resultForward = mapping.findForward("success");
			return resultForward;
		} catch (
			javax.xml.transform.TransformerConfigurationException jxttce1) {
			jxttce1.printStackTrace();
			resultForward = mapping.findForward("failure");
			return resultForward;
		}catch( Exception ex){	ex.printStackTrace();	
			resultForward = mapping.findForward("failure");
			return resultForward;
		}
		
	}
	public static String getCartInAddress( String str, String jSessionId){
		String footer = "/lmj/servlet/CartInStream;jsessionid=" + jSessionId;
		if( str.indexOf( "test" ) != -1){
           return "http://" + "192.168.10.12" + footer;
        }else if( str.indexOf( "10.11" )  != -1 ){
           return "http://" + "192.168.10.11" + footer;
        }else if( str.indexOf( "10.3" )  != -1 ){
           return "http://" + "192.168.10.3" + footer;
        }else if( str.indexOf( "mente" )  != -1 ){
           return "http://" + "192.168.10.3" + footer;
        }else if( str.indexOf( "www1" )  != -1 ){
           return "http://" + "192.168.10.1" + footer;
        }else if( str.indexOf( "www2" )  != -1 ){
        	return "http://" + "192.168.10.2" + footer;
        }
        return "";
	}
	public RegistrationAction() {
	}
}
