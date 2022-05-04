package jp.co.lastminute.Entertainment.top;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import jp.co.yobrain.util.*;
import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.Entertainment.*;

import jp.co.lastminute.maintenance.XmlReadServlet;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FormAction extends Action {
	
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		ActionForward actionforward = null;
		ActionErrors errors = null; 
		actionforward = new ActionForward();
		////Session
		HttpSession session = request.getSession();
		ServletContext servletContext = servlet.getServletContext();
		//トークンをセット
		
		//チェックルーチンを通過するが、特にチェックルーチンはない。
		
		String xmlStrings = "";
		
		//遷移を制御する
		Form actionform = (Form)form;
		if( actionform.getAct().length() == 0){
			actionform.setAct( Property._index );
		}
		//コンテキストからXMLストリングの取得
		try{
			if( (xmlStrings = getContextString( actionform.getAct(), servletContext )).length() == 0){
				setContextValue( servlet.getServletConfig() );
				xmlStrings = getContextString( actionform.getAct(), servletContext );
			}
		}catch(Exception ex){	
			ex.printStackTrace();
		}
		if( xmlStrings.length() > 0 ){
			actionform.setXmlstring( xmlStrings );
			//System.err.println( xmlStrings );
			return (mapping.findForward( "success" ));
		}
		return (mapping.findForward( "failure" ));
	}
	/**
	 * コンテキストマップの取得
	 */
	private String getContextString( String act, ServletContext servletContext) {
		try{
			HashMap xmlStringsMap = (HashMap)servletContext.getAttribute( ContextProperty._TICKET );
			return (String)xmlStringsMap.get( act );
		}catch(Exception ex){	
			//エンタープライズログを実装
		}
		return "";
	}
	/**
	 * //コンテキストがNULLだったら再起動
	 */
	private void setContextValue( ServletConfig servletConfig ) throws Exception{
		XmlReadServlet xmlReadServlet = new XmlReadServlet();
		xmlReadServlet.setServletConfig( servletConfig );
		xmlReadServlet.initTICKET_CONTEXT();
	}
}
