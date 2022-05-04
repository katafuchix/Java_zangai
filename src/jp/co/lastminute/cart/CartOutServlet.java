/*
 * CartOut.java
 *
 * Created on 2002/07/20, 16:30
 */

package jp.co.lastminute.cart;

import java.io.*;
import java.util.*;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSessionEvent;

import jp.co.lastminute.*;
import jp.co.lastminute.cart.model.Order;
import jp.co.lastminute.cart.model.Sub_Order;
import jp.co.lastminute.cart.model.Product_Send;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.jdbc.*;
import jp.co.lastminute.cart.prop.*;
import jp.co.lastminute.common.xml.XmlReader;

import jp.co.yobrain.util.*;

import org.apache.struts.action.*;

import jp.co.yobrain.util.DataFormat;
/**
 *
 * @author  skondo
 * @version 
 */
public class CartOutServlet implements HttpSessionListener{
    
    public void sessionCreated(javax.servlet.http.HttpSessionEvent ev) {
        HttpSession session = ev.getSession();
        long from = session.getLastAccessedTime();
        long now = System.currentTimeMillis();
        long term = session.getMaxInactiveInterval();
        /*
         * System.err.println("[SESSION FROM]" + from );
        System.err.println("[SESSION NOW]" + now );
        System.err.println("[SESSION INTERVAL]" + term );
        System.err.print("[SESSION START ID=]" + session.getId() );
        */
    }
    
    /** Creates new CartOut */
    public void sessionDestroyed( javax.servlet.http.HttpSessionEvent ev){
        HttpSession session = ev.getSession();
        ServletContext servletContext = session.getServletContext();
        DataSource dss = (DataSource)servletContext.getAttribute( Action.DATA_SOURCE_KEY );
        //System.err.println("[CART OUT STRAT]" + session.getId() );
        removeSubOrders( session.getId() , dss , servletContext);
    }
    /**
     * オーダーの削除
     */
    synchronized public void removeSubOrders( String sessionid, DataSource dss, ServletContext servletContext){
        String SQL = "SELECT SUB_ORDER_NO, "    //00
                    + "PROD_CD, "               //01
                    + "PAX, "                   //02
                    + "AGT_CD, "				//03
                    + "PRODUCT_TYPE_CD "		//04
                    + "FROM SUB_ORDER WHERE SESSION_ID='"+ sessionid + "'"
                    //+ " AND PRODUCT_TYPE_CD IN(93, 8)"
                    + " AND PRODUCT_TYPE_CD IN( 8 )"
                    + " AND STATUS < 2";
        JdbcAdapter db = new JdbcAdapter();
        //System.err.println( SQL );
        try{
        if( db.init( dss )){
            Vector vc = db.dbSelect( SQL );
            if( vc.size() > 0 ){
                dbDataAccesser dba = new dbDataAccesser( vc );
                for(int i=0; i<vc.size(); i++){
                	AllotCheckCondition allotCheckCondition = new AllotCheckCondition();
			    	allotCheckCondition.setProduct_cd( dba.getData(i, 1));
			    	allotCheckCondition.setAllot( dba.getDatabyInt(i, 2) );
                	HashMap xmlhmap = (HashMap)servletContext.getAttribute( Constants.Product_Type_Mapping_);
                	XmlReader xreader = new XmlReader( xmlhmap ); 
                	String targetnames = xreader.getTemplatexsd( dba.getData(i, 3), dba.getDatabyInt(i, 4) );
                	String classnames = ((Navigato)Class.forName( targetnames ).newInstance()).getAllotReturnClass();
                	dbProductAdapter dbdapter = (dbProductAdapter)Class.forName( classnames ).newInstance();
		    		dbdapter.init( dss );
		    		dbdapter.returnAllotment( allotCheckCondition, dba.getDatabyInt(i, 2) );
                }
            }
            vc = null;
            db = null;
        }
        }catch(Exception ex){   ex.printStackTrace();	if( db != null ){   db = null;  }   }
    }  
}