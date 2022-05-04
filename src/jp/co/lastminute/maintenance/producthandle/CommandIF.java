package jp.co.lastminute.maintenance.producthandle;

import java.util.Map;
import javax.sql.DataSource;
import jp.co.lastminute.maintenance.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface CommandIF {
	public void setDataSource(DataSource dataSource);
	public HandlerCondition getCondition() ;
	public void setCondition(HandlerCondition  condition) ;
}
