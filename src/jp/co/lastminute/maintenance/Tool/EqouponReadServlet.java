package jp.co.lastminute.maintenance.Tool;

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
public class EqouponReadServlet extends HttpServlet {
	private static final String paramname = "filename";

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
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("text/html;charset=Shift_JIS");
		PrintWriter out = res.getWriter();
		if (req.getServerName().indexOf("192.168") != -1) {
			if( req.getParameter( paramname ).length() > 0 ){
				try{
					out.print( load( req.getParameter( paramname ) ) );
				}catch(Exception ex){	ex.printStackTrace();	}
			}
		}
		out.close();
		return;
	}
	/**
	 * コンテキストのロード
	 */
	private String load( String filename )	throws Exception {
		String result = "";
		StringWriter swriter = null;
		PrintWriter pwriter = null;
		FileReader sr = null;
		BufferedInputStream bis = null;
		try {
			String path = "/home/alice/knt";
			bis = new BufferedInputStream( new FileInputStream(new File(path + "/" + filename)));
			int availableLength = bis.available();
			byte[] bt = new byte[availableLength];
			int len = 0;
			while ((len = bis.read(bt, 0, availableLength)) != -1) {
				result += new String(bt, "UTF-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
		return (result);
	}
	private String getRealFilePath(String filePath) {
		String realPath = ContextProperty.basepath;
		return (realPath + filePath);
	}
}
