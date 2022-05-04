package jp.co.lastminute.cart.check;

import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import jp.co.lastminute.cart.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface CheckInterface{
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Order orders, ServletContext servletContext ) ;
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response );
	public boolean Check();
	public ActionErrors getActionErrors();
	public Order getOrder();
	public ActionForm getForm();
	public boolean CancellCheck();
}
