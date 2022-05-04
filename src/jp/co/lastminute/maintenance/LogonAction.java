package jp.co.lastminute.maintenance;

import java.io.*;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LogonAction extends Action {

	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		// Extract attributes we will need
		Locale locale = getLocale(request);
		MessageResources messages = getResources();

		boolean isValidIP = false;
		if (request.getRemoteAddr().indexOf("192.168") > -1
			|| request.getRemoteAddr().indexOf("172.18") > -1 
			|| request.getRemoteAddr().indexOf("220.97.55.150") > -1){
			isValidIP = true;
		}
		if (!isValidIP)
			return (new ActionForward(mapping.getInput()));

		// Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();
		String user_id = ((LogonForm) form).getUser_id();
		String password = ((LogonForm) form).getPassword();

		UserInfo user = new Authenticater().getAuthentication(user_id);

		if ((user != null) && !user.getPassword().equals(password))
			user = null;
		if (user == null)
			errors.add(	ActionErrors.GLOBAL_ERROR,new ActionError("error.password.mismatch",
					"Do not match Password"));

		// Report any errors we have discovered back to the original form
		if (!errors.empty()) {
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}

		// Save our logged-in user in the session
		HttpSession session = request.getSession(true);
		//session.setAttribute(Constants.USER_KEY, user);
		session.setAttribute("SESSION.USER_INFO", user);

		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope()))
				request.removeAttribute(mapping.getAttribute());
			else
				session.removeAttribute(mapping.getAttribute());
		}

		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	} //end perform

}
