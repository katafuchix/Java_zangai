package jp.co.lastminute.maintenance.util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSessionEvent;

import java.io.*;
import java.util.*;



/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FileUploadServlet extends HttpServlet {

	private ServletContext servletContext;
	private ServletConfig servletConfig;
	private ResourceBundle resources;
	private String basepath;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		servletContext = servletConfig.getServletContext(); //サーブレットテキスト
		try {
	      resources = ResourceBundle.getBundle("jp.co.lastminute.maintenance.util.resources.FileUpload", Locale.getDefault());
	      basepath = resources.getString( "basepath" );
	    } catch(Exception e){
	      System.out.println(e);
	    }
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		HttpSession session = req.getSession();
		String fileName = (String)session.getAttribute( "fileName" );
		String pathName = (String)session.getAttribute( "pathName" );
		
		res.setContentType("text/html");
		try{
			PrintWriter out = res.getWriter();
			
			if( writeFile( req, fileName, pathName ) ){
				out.println( "<html><body>");
				out.println( "<img src='/" + pathName + "/" + fileName + "'/>");
				out.println( "</body></html>");

			} else {
				out.println( "<html><body>");
				out.println( "<h4> Fail　</h4>");
				out.println( "</body></html>");
			}
			session.removeAttribute( "fileName" );
			session.removeAttribute( "pathName" ); 
			out.close();
		}catch(Exception ex){	ex.printStackTrace();	
		}
	}
	public boolean writeFile( HttpServletRequest req, String fileName, String pathName ){
		try{
			MultipartBean mb = new MultipartBean(req);
			mb.setDir( basepath + pathName );
			mb.recvData();
			mb.setFilename( fileName );
			mb.saveData();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
