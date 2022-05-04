package jp.co.lastminute.Hotel.detail;

import jp.co.lastminute.Hotel.detail.CheckAllote;

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

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckAllotAction extends Action {
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		//ActionForward resultForward; // For storing the result
		//ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		ServletContext servletContext = servlet.getServletContext();
		DataSource dss = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);

		//エージェントコードの取得
		Form actionForm = (Form) form;
		String agt_cd = "";	System.err.println("line 42");
		try {
			if (actionForm.getAgtcode().length() > 0) {
				agt_cd = actionForm.getAgtcode();
			}
		} catch (Exception ex) {
			ex.printStackTrace();	System.err.println("line 48");
			//エラー画面に遷移
			return (mapping.findForward("mappingfail"));
		}
		//クラス名の設定
		String classname = "jp.co.lastminute.Hotel.detail.Agent." + agt_cd;
		//クラスをアップさせる
		System.err.println("line 55");
		try {
			CheckAllote sendclient =
				(CheckAllote) Class.forName(classname).newInstance();
			String result = "";
			if ((result = sendclient.resultresultFromWebsite(	sendclient.setParameter(actionForm)))!= null) {
				actionForm.price = getPrice( result ); //価格の設定
				request.setAttribute("result", actionForm);	System.err.println("line 62");
				return (mapping.findForward("showallot"));
			} else {
				request.setAttribute("result", actionForm);	System.err.println("line 65");
				CreateDetailPage pages = new CreateDetailPage( mapping, form, request, response, servletContext, dss );
				return pages.returnPages();
				
				
				//return (mapping.findForward("noallote"));
			}
		} catch(ClassCastException cex){
			cex.printStackTrace();	System.err.println("line 69");
			return (mapping.findForward("mappingfail"));
		} catch (Exception ex) {
			ex.printStackTrace();	System.err.println("line 72");
			return (mapping.findForward("failure"));
		}
		//return (mapping.findForward("mappingfail"));
	}
	private String getPrice(String result) {
		if (result.indexOf("<PRICE>") != -1) {
			return result.substring(result.indexOf("<PRICE>") + 7,	result.indexOf("</PRICE>"));
		} else {
			return "0";
		}
	}
}
