package jp.co.lastminute.common.validation;

//import org.apache.log4j.Category;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

// com.matsushita.eTraffic.utils.LogHelper
/**
 * @author Ricardo Leon
 * @version 1.0
 * Helper Class for Wrting to the Log
 */
public class LogHelper {
	
	/**
	 * Writes all the parameters sent to the Log File
	 * @param className the name of the Class that calls this Function
	 * @param categoryName the category of Log4J that is going to be Used for writing to the Log
	 * @param request the request received by the JSP web page
	 */
	public static void log(String className, String categoryName, HttpServletRequest request) {
		//HttpSession session = request.getSession(false);
		// Get the custom session data from the user
		//SessionManagement userSession = null;
		//if (session != null) {
		//	userSession = (SessionManagement) session.getAttribute(SessionManagement.VAL_SESSION); // get the user Session object
		//}
		//Category logCategory = Category.getInstance(categoryName);
		/*StringBuffer userId = new StringBuffer();
		if (userSession != null) {
			userId.append("User Data:");
			if (userSession.getEnterpriseId() != null) {
				userId.append("(EnterpriseID=" + userSession.getEnterpriseId() + ")");
			}
			if (userSession.getUserId() != null) {
				userId.append("(UserId=" + userSession.getUserId() + ")");
			}
			if (userSession.getOperatorId() != null) {
				userId.append("(OperatorId=" + userSession.getOperatorId() + ")");
			}
		}
		*/
		StringBuffer requestParam = new StringBuffer("Request Params: ");
		for (Enumeration parameterNamesEnum = request.getParameterNames(); parameterNamesEnum.hasMoreElements() ;) {
			try {
				String parameterName = (String) parameterNamesEnum.nextElement();
				String value = new String(request.getParameter(parameterName).getBytes("8859_1"), "MS932");
				requestParam.append("(" + parameterName + "=" + value + ")");
			}
			catch (java.io.UnsupportedEncodingException uee) {
			}
		}
		//logCategory.info("[UID:" + ThreadContext.getUserId() + "][" + className + "] In From User[" + userId.toString() + "]\n[" + requestParam.toString() + "]");
	}
	
	
	//com.matsushita.eTraffic.utils.LogHelper.logOutput(className, categoryName, output.toString())
	/*
	public static void logOutput(String className, String categoryName, String output) {
		if (output == null || output.length() == 0) {
			return;
		}
		Category logCategory = Category.getInstance(categoryName);
		logCategory.info("[UID:" + ThreadContext.getUserId() + "][" + className + "] Output to the User\n[" + output + "]");
	}
	
	public static void logOutput(String className, String categoryName, java.util.Hashtable output) {
		if (output == null) {
			return;
		}
		logOutput(className, categoryName, getString(output));
	}

	public static String getString(java.util.Hashtable aHashtable) {
		StringBuffer requestParam = new StringBuffer();
		for (Enumeration parameterNamesEnum = aHashtable.keys(); parameterNamesEnum.hasMoreElements() ;) {
			String parameterName = (String) parameterNamesEnum.nextElement();
			requestParam.append("(" + parameterName + "=" + aHashtable.get(parameterName) + ")");
		}
		return requestParam.toString();
	}
	*/
		
}
