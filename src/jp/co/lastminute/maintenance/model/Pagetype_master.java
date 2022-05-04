package jp.co.lastminute.maintenance.model;

import java.io.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Pagetype_master implements Serializable {
	 public int page_type			= 0;
	 public String descrption		= "";
	 public String update_id		= "";
	 public String makedate_id 	= "";
	 public String stop_flg 		= "";
	 /**
	  * コンストラクタ
	  */
	 public Pagetype_master(){	
	 }
	/**
	 * Returns the descrption.
	 * @return String
	 */
	public String getDescrption() {
		return descrption;
	}

	/**
	 * Returns the makedate_id.
	 * @return String
	 */
	public String getMakedate_id() {
		return makedate_id;
	}

	/**
	 * Returns the page_type.
	 * @return int
	 */
	public int getPage_type() {
		return page_type;
	}

	/**
	 * Returns the stop_flg.
	 * @return String
	 */
	public String getStop_flg() {
		return stop_flg;
	}

	/**
	 * Returns the update_id.
	 * @return String
	 */
	public String getUpdate_id() {
		return update_id;
	}

	/**
	 * Sets the descrption.
	 * @param descrption The descrption to set
	 */
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	/**
	 * Sets the makedate_id.
	 * @param makedate_id The makedate_id to set
	 */
	public void setMakedate_id(String makedate_id) {
		this.makedate_id = makedate_id;
	}

	/**
	 * Sets the page_type.
	 * @param page_type The page_type to set
	 */
	public void setPage_type(int page_type) {
		this.page_type = page_type;
	}

	/**
	 * Sets the stop_flg.
	 * @param stop_flg The stop_flg to set
	 */
	public void setStop_flg(String stop_flg) {
		this.stop_flg = stop_flg;
	}

	/**
	 * Sets the update_id.
	 * @param update_id The update_id to set
	 */
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}

}
