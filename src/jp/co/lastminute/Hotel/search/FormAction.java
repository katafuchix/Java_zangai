package jp.co.lastminute.Hotel.search;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.lastminute.Hotel.*;
import jp.co.lastminute.Hotel.jdbc.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FormAction extends Action {
	DataSource dss;
	/**
	 * リスト要求処理 
	 */
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		////Session
		HttpSession session = request.getSession();
		ServletContext servletContext = servlet.getServletContext();
		
		this.dss = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);

		String xmlStrings = "";

		//遷移を制御する
		Form actionform = (Form) form;
		//SCATIDの確認
		CheckError chError = null;
		Check formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet(actionform.getScatid(), 0, false);
		if (chError.getError() > 0) {
			System.err.println(" Hotel Search mappingfail 01 " );
			return (mapping.findForward("mappingfail"));
		}
		chError = null;
		formchk = null;
		formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet(actionform.getState_cd(), 0, false);
		if (chError.getError() > 0) {
			System.err.println(" Hotel Search mappingfail 01 " );
			return (mapping.findForward("mappingfail"));
		}
		chError = null;
		formchk = null;
		
		//データの取得
		dbAdapterHotel db = new dbAdapterHotel( this.dss );
		db.setContext( servletContext );
		xmlStrings = db.getLIst( actionform );
		actionform.setTotalpages( db.getTotalPages() );
		db = null;
		if (xmlStrings.length() > 0) {
			actionform.setXmlstring( xmlStrings );
			return (new ActionForward(mapping.getInput()));
		}
		System.err.println(" Gfit Search mappingfail 02 " );
		return (mapping.findForward("nodata"));
	}
}
