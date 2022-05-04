package jp.co.lastminute.maintenance;

import java.io.*;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.lang.reflect.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import org.apache.struts.digester.Digester;
import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;



import javax.naming.*;
import javax.naming.directory.*;
import jp.co.lastminute.maintenance.util.*;
import jp.co.lastminute.ContextProperty;

import jp.co.yobrain.util.file.InportFile;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XmlDatabaseServlet extends HttpServlet {
	private static final String CONTEXTVALUE = "contextValue";
	private static final String CONTEXTNAME = "contextname";
	private static final String CONTEXTPATH = "contextpath";

	private ServletContext servletContext;
	private ResourceBundle resources;
	private static String basepath = ContextProperty.basepath;

	//private Properties allProps = null;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext(); //サーブレットテキスト
	}
	/**
	 * リクエスト：　name = contextValue / String xmlcontext
	 * 				name = contextname	/ String contextname
	 * 				name = mode			/ Browser or System
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		req.setCharacterEncoding("Shift_JIS");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		//パラメータの取得
		String contextValue = req.getParameter(CONTEXTVALUE);
		String contextname = req.getParameter(CONTEXTNAME);
		String contextpath = req.getParameter(CONTEXTPATH);
		//IP制限
		if (req.getServerName().indexOf("192.168") != -1) {
			//エラー制御
			if ((contextValue.length() > 0) && (contextname.length() > 0)) {
				//コンテキスト保持
				if( writeContextValue(
					basepath + contextpath + contextname,
					contextValue) ){
						out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
						out.println("<CXLBOKIN>");
						out.println("<SUCCESS>");
						out.println("<ERRORCODE>0</ERRORCODE>");
						out.println("</SUCCESS>");
						out.println("</CXLBOKIN>");
						out.close();
						return;
				}
			}
		}
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<CXLBOKIN>");
		out.println("<FAIL>");
		out.println("<ERRORCODE>-1</ERRORCODE>");
		out.println("</FAIL>");
		out.println("</CXLBOKIN>");
		out.close();
	}
	/**
	 * コンテキストの保持 
	 */
	public boolean writeContextValue(String contextname, String contextValue) {
		XML2File xml2file = new XML2File();
		try {
			System.err.println(" File Write : " + contextname );
			xml2file.write(contextValue, contextname);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	public void destroy() {
	}

}
