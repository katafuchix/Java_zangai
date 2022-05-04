package jp.co.lastminute.Dflight.detail;

import java.io.*;
import java.text.*;
import java.util.*;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import jp.co.yobrain.util.*;
import jp.co.lastminute.Dflight.*;
import jp.co.lastminute.Dflight.jdbc.*;
import jp.co.lastminute.Dflight.cash.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FormAction extends Action{
	DataSource dss;
	public ActionForward perform(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException
    {
    	//環境変数を取り出す
    	////Session
		HttpSession session = request.getSession();
		ServletContext servletContext = servlet.getServletContext();
		this.dss = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
    	Form actionform = (Form)form;
    	
    	//データベースから値を取得
    	String xmlStr = "";
    	//System.err.println("XMLSTR_Detail_001\n" + xmlStr);
    	if(( actionform.getProductioncode().length() > 0 )&&
    	( actionform.getProduct_id().length() > 0)){

	    	try{
		    	dbAdapterDflight db = new dbAdapterDflight( this.dss );
		    	xmlStr = db.getDetail( actionform );
	    	}catch(Exception ex){	ex.printStackTrace();
	    		//System.err.println("XMLSTR_Detail_00\n" + xmlStr);
	    		return (mapping.findForward("dbfail"));
	    	}
    	
    		if( xmlStr.length() == 0 ){
    			return (mapping.findForward("dbfail"));
    		}
	    	//System.err.println("XMLSTR_Detail_002\n" + xmlStr);
	    	//リクエストを返す
			try{
				//System.err.println("XMLSTR_Detail_003\n" + xmlStr);
				actionform.setXmlstring( xmlStr );
				return (mapping.findForward("success"));
			}catch(Exception ex){	ex.printStackTrace();
				//エラー遷移
			}
		}
		return (mapping.findForward("mappingfail"));	   	
    }
}
