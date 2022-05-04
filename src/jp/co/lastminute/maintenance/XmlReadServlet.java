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
public class XmlReadServlet extends HttpServlet {
	private static final String CONTEXTVALUE = "contextValue";
	private static final String CONTEXTNAME = "contextname";
	private static final String CONTEXTPATH = "contextpath";

	private ServletContext servletContext;
	private ResourceBundle resources;
	private static String basepath = ContextProperty.basepath;

	public void setServletConfig( ServletConfig servletConfig) throws ServletException{
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext(); //サーブレットテキスト
	}

	//private Properties allProps = null;x
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext(); //サーブレットテキスト
		try {
			//itHotel();
			initGIFT_CONTEXT();
			initTICKET_CONTEXT();
			initHOTEL_CONTEXT();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("text/html;charset=Shift_JIS");
		PrintWriter out = res.getWriter();
		if (req.getServerName().indexOf("192.168") != -1) {
			out.println("<script language=\"JavaScript\">\n");
			if( reset(req.getParameter("partName") )){
				out.println("alert('正常に変更しました。');\n");
			}else{
				out.println("alert('失敗しました。');\n");
			}
			out.println("</script>");
		}
		System.err.println("[ req.getServerName()] = " + req.getServerName());
		out.close();
		return;
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("text/html;charset=Shift_JIS");
		PrintWriter out = res.getWriter();
		if (req.getServerName().indexOf("192.168") != -1) {
			out.println("<script language=\"JavaScript\">\n");
			if( reset(req.getParameter("partName") )){
				out.println("alert('正常に変更しました。');\n");
			}else{
				out.println("alert('失敗しました。');\n");
			}
			out.println("</script>");
		}
		System.err.println("[ req.getServerName()] = " + req.getServerName());
		out.close();
		return;
	}
	public void destroy() {
	}
	/**
	 * ホテルの初期化
	 */
	public String initHOTEL_CONTEXT() {
		String xml = "";
		try {
			String[] mapKeys = ContextProperty.mapKeys_004; //
			String[] fileNames = ContextProperty.fileNames_004;
			String filePath = ContextProperty._hotel_resource_Dir;
			String attributeName = ContextProperty._Hotel;
			xml = load(mapKeys, fileNames, filePath, attributeName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return xml;

	}
	/**
	 * ギフトの初期化
	 */
	public String initGIFT_CONTEXT() {
		String xml = "";
		try {
			String[] mapKeys = ContextProperty.mapKeys_093;
			String[] fileNames = ContextProperty.fileNames_093;
			String attributeName = ContextProperty._Gift;
			String filePath = ContextProperty._gift_resource_Dir;
			xml += load(mapKeys, fileNames, filePath, attributeName);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Cannot load database from ");
		}
		return xml;
	}
	/**
	 * ギフトの初期化
	 */
	public String initTICKET_CONTEXT() {
		String xml = "";
		try {
			String[] mapKeys = ContextProperty.mapKeys_099;
			String[] fileNames = ContextProperty.fileNames_099;
			String attributeName = ContextProperty._TICKET;
			String filePath = ContextProperty._ticket_resource_Dir;
			xml += load(mapKeys, fileNames, filePath, attributeName);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Cannot load database from ");
		}
		return xml;
	}
	/**
	 * コンテキストのロード
	 */
	private String load(
		String[] mapKeys,
		String[] fileNames,
		String filePath,
		String attributeName)
		throws Exception {
		HashMap categoryTable = new HashMap();
		String[] keys = mapKeys;
		String[] _fileNames = fileNames;
		String _attributeName = attributeName;

		HashMap xmlStringsMap = new HashMap();
		InportFile inf = new InportFile();
		String readedXML = "";
		for (int i = 0; i < fileNames.length; i++) {
			System.out.println("attributeName/keys[i]: " + attributeName +  " " + keys[i]);
			System.out.println("fileNames[i]: " + fileNames[i]);
			System.out.println("getRealFilePath(filePath)): " + getRealFilePath(filePath));
			String amount = inf.getFileAmount(fileNames[i], getRealFilePath(filePath));
			//System.out.print( amount );
			xmlStringsMap.put( keys[i], amount );
		}
		servletContext.setAttribute(attributeName, xmlStringsMap);
		return "";
	}
	private String getRealFilePath(String filePath) {
		String realPath = ContextProperty.basepath;
		return (realPath + filePath);
	}
	/**
	 * リセットメソッド
	 */
	private boolean reset(String partName) {
		String readedXML = "";
		System.err.println("Start Reflection " + partName);
		try {
			Class clazz = this.getClass();
			Method methods[] = clazz.getDeclaredMethods();
			Method m = clazz.getDeclaredMethod("init" + partName, new Class[0]);
			m.invoke(this, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
