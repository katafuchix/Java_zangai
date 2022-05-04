package jp.co.lastminute.cash;

import javax.sql.DataSource;
import java.sql.SQLException;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface XMLCashInterface {
	abstract public String newXMLString(DataSource dataSource, Object form)throws SQLException;
	abstract public String getXML(DataSource dataSource,Object form, int refreshDuration)
            throws SQLException;
}
