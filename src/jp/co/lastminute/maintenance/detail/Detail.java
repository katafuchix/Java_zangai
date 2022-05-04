package jp.co.lastminute.maintenance.detail;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.jdbc.*;
import jp.co.lastminute.maintenance.model.*;

import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.jdbc.Sqlmaker;
import jp.co.yobrain.util.DataFormat;

import jp.co.yobrain.util.rpc.PostString;
import jp.co.yobrain.util.rpc.SendClient;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Detail implements Serializable{
	protected DataSource dataSource;
	protected String xmlString = "";
	protected static String target;

	public static String ConvertTag( String str ){
		try{
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	private static ResourceBundle resources;
	static {
        try {
            resources = ResourceBundle.getBundle("jp.co.lastminute.maintenance.pages.resources.Server", Locale.getDefault());
            target    = resources.getString( "target" );
        }catch (MissingResourceException mre){
            resources = null;
        }
    }
    public void init(  DataSource ds) {
		this.dataSource = ds;
	}
	
	public String generateXml( dbDataAccesser access ) throws Exception {
		return "";	
	}
	/**
	 * �ꊇ�ō쐬
	 */
	public String getXML( HandlerCondition condition ){
		try{
			String xml = generateXml ( getDetail( condition ) );
			this.xmlString = xml;
			return xml;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";
	}
	/**
	 * �ڍׂ̎擾
	 */
	public dbDataAccesser getDetail( HandlerCondition condition ){
		dbDataAccesser access = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getDetailStr( condition );
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
					return ( new dbDataAccesser( rows ) );
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return access;	
	}
	/** Returns the xmlString.
	 * @return String
	 */
	public String getXmlString() {
		return xmlString;
	}

	/**
	 * Sets the xmlString.
	 * @param xmlString The xmlString to set
	 */
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		return "";	
	}
}
