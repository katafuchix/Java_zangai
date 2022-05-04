package jp.co.lastminute.maintenance.detail;

import java.io.*;
import java.util.*;
import javax.sql.*;

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.maintenance.*;

import jp.co.yobrain.util.dbDataAccesser;


/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface PageDetail {
	public void init(  DataSource ds ) ;
	public boolean write( String contextValue, String contextname );
	public String makeXml( String contextValue, String contextname ) throws Exception  ;
	public String getXML( HandlerCondition condition )throws Exception;
	public String generateXml( dbDataAccesser access )throws Exception;
	public dbDataAccesser getDetail( HandlerCondition condition );
	public String getDetailStr( HandlerCondition condition )throws Exception;
	public String getXmlString();
	public void setXmlString(String xmlString);
}
