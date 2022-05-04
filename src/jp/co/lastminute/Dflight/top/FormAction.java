package jp.co.lastminute.Dflight.top;

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
import jp.co.lastminute.cash.*;
import jp.co.lastminute.Dflight.*;
import jp.co.lastminute.Dflight.cash.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FormAction extends Action {
        
	private DataSource dataSource;
	
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
		this.dataSource = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		//トークンをセット
		
		//チェックルーチンを通過する
		CheckForm checkForm = new CheckForm( mapping, form, request, response);
        if( !checkForm.Check() ){
        	//トークン処理をリセット
           	errors = checkForm.getActionErrors();
            saveErrors( request, errors );
           return (new ActionForward(mapping.getInput()));
        }
		
		//遷移を制御する
		Form actionform = (Form)form;
		String throwflg = ((Form)form).getThrowflg();
		
		//インデックスの取得とコンテキストの保持/XMLを出力し、セッションに追加する。
		CashMaker cashMaker = null;
		String makedStr = "";
		if( servletContext.getAttribute( Property.listcash ) == null ){
			cashMaker = new CashMaker();
		}else{
			cashMaker = (CashMaker)servletContext.getAttribute( Property.listcash );
		}
		makedStr = cashMaker.getXML( dataSource, form );
		if( cashMaker.mustReload() ){
			servletContext.setAttribute( Property.listcash, cashMaker );
		}
		try{
			actionform.setXmlstring( makedStr );
		//System.err.println("<//// ----- Top FormAction----. 8>");
		//System.err.println("\n\n<!--XML" + makedStr + "-->\n\n");
			if( makedStr.length() == 0 ){	//レンジがない場合
				errors = checkForm.getActionErrors();
	            saveErrors( request, errors );
	           	return (new ActionForward(mapping.getInput()));	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			errors = checkForm.getActionErrors();
            saveErrors( request, errors );
           	return (new ActionForward(mapping.getInput()));			
		}
		//System.err.println( makedStr );
		//System.err.println("<//// ----- Top FormAction----. 9>");
		//トークンを解除する？
		return (mapping.findForward( "success" ));
	}
}
