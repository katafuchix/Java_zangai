package jp.co.lastminute.maintenance.producthandle;

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
public class Commander{
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public HandlerCondition getCondition() {
		return condition;
	}
	public void setCondition(HandlerCondition condition) {
		this.condition = condition;
	}

	protected DataSource dataSource;
	protected HandlerCondition condition;
}
