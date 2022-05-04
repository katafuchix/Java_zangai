/*
 * XmlDatabaseServlet.java
 *
 * Created on 2002/04/21, 15:55
 */

package jp.co.lastminute.menu;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import org.apache.struts.digester.Digester;
import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;
import jp.co.lastminute.*;

/**
 *
 * @author  skondo
 * @version 
 */
public final class XmlDatabaseServlet extends HttpServlet {
	private HashMap categoryTable = null;
	private MessageResources messages = null;
	private ServletContext servletContext = null;
	private String pathname;
	private int debug = 0;

	public void destroy() {
		servletContext.removeAttribute(Contents.CATEGORY_TABLE_KEY);
	} //end destroy

	public void init() throws ServletException {
		// Process our servlet initialization parameters
		servletContext = getServletContext();
		try {
			load();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UnavailableException("Cannot load database from ");
		}

	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		servletContext = getServletContext();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		try {
			load();
			out.println("<H2>Loading Is OK </H2>");
		} catch (Exception ex) {
			ex.printStackTrace();
			out.println("<H2>Loading Is BAD </H2>");
			throw new UnavailableException("Cannot load database from ");
		}
	}

	public void addMenu(Menu menu) {
		categoryTable.put(menu.getCatid(), menu);
	} //end addUser

	private synchronized void load() throws Exception {
		categoryTable = new HashMap();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(pathname());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		Digester digester = new Digester();
		digester.push(this);
		digester.setDebug(debug);
		digester.setValidating(false);
		//ダイジェスター　ロード
		digester.addObjectCreate("database/menu", "jp.co.lastminute.menu.Menu");
		digester.addSetProperties("database/menu");
		digester.addSetNext("database/menu", "addMenu");

		digester.parse(bis);
		bis.close();

		servletContext.setAttribute(Contents.CATEGORY_TABLE_KEY, categoryTable);

	}
	private String pathname() {
		String realPath = getServletContext().getRealPath("/");
		if (realPath == null) {
			throw new RuntimeException("realPath was null.  can not load database.xml to parse.");
		}
		if (pathname != null) {
			return pathname;
		} else {
			return (
				realPath
					+ "/WEB-INF/classes/jp/co/lastminute/menu/resources/database.xml");
		}
	} //end pathname
}
