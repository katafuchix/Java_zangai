package jp.co.lastminute.cart.allotment;

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
public interface ParameterValidationInterface {
	/**
	 * フォーム内容の確認
	 */
	//public ActionErrors validate(ActionMapping mapping, HttpServletRequest request);
	/**
	 * XMLの作成
	 */
	public String generateXML( Object obj, int userId) throws Exception;
	/**
	 * BUYPROPの作成
	 */
	public String[] genBuyProp( Object obj ) throws Exception;
}
