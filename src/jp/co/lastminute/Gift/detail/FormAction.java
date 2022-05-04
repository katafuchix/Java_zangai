package jp.co.lastminute.Gift.detail;

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

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.Gift.*;
import jp.co.lastminute.Gift.jdbc.*;

import jp.co.yobrain.util.file.InportFile;
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
    	
    	//チェックルーチンを通過する
		CheckForm checkForm = new CheckForm( mapping, form, request, response);
        if( !checkForm.Check() ){
        	//トークンを無視してエラー画面を出力
           return ( mapping.findForward("mappingfail") );
        }
        actionform = ( Form )checkForm.getForm();
        //ファイルからXMLを読み込む
        String filename = actionform.getProduct_id() + ".xml";
        //
        String xmlstring = getXmlAmount( filename );
        if( xmlstring.length() == 0 ){ return (mapping.findForward("mappingfail")); }
        actionform.setXmlstring( xmlstring );
        System.err.println("XMLSTR_Detail_001\n" + xmlstring);
    	//データベースから取得する
    	try{
	    	dbAdapterGift db = new dbAdapterGift( this.dss );
	    	actionform.setAllotmentxml( db.getDetail( actionform ) );
	    	return (mapping.findForward("success"));
    	}catch(Exception ex){	ex.printStackTrace();
    		
    	}
    	return (mapping.findForward("dbfail"));    		
    }
    private static String getXmlAmount( String filename ){
    	try{
    		String filepath = ContextProperty.basepath + ContextProperty._gift_product_Dir;
    		InportFile inf = new InportFile();
			return inf.getFileAmount( filename, filepath );
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return "";
    }
}
