package jp.co.lastminute.cash;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.servlet.ServletContext;

import javax.sql.DataSource;

import java.sql.SQLException;

import org.apache.struts.action.Action;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface CashedXMLMaker {
	public void setServletContext(ServletContext context);
	public String getCachedFlightXML( Object form,int refreshDuration)throws SQLException;
	public void flushAll();
	public void flush(Object act);
	public DataSource getDataSource();
}
