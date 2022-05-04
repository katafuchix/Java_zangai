package jp.co.lastminute.maintenance.model;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import jp.co.yobrain.util.form.*;
import jp.co.lastminute.maintenance.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Categoryselect extends Models implements Serializable{
	public int seqno 		= 0;
	public int page_type Å@	= 0;
	public int s_catid 		= 0;
	public int startdate		= 0;
	public int stopdate 		= 0;
	public String description = "";
	public String prod_id_1  	= "";
	public String prod_id_2 	= "";
	public String prod_id_3 	= "";
	public String update_id 	= "";
	public String stop_flg 	= "0";
	public int catid 			= 0;
	// 	TYPE_NUMBER_ = 0;
	//	TYPE_STRING_ = 1;
	//	TYPE_FLOAT_ = 2;
	//	TYPE_DATE_ =  3;
	//	TYPE_EMAIL_ = 4;
	//	TYPE_MONEY_ = 5;
	//	TYPE_ALPHABET_ = 6;
	//	TYPE_ALPHABETNUMBER_ = 7;
	//	TYPE_DATETIME_ =  8;
	//	TYPE_PHONE_ =  9;
	//	TYPE_TWOBYTE_ =  10;
	public Categoryselect getRequest( HttpServletRequest req ){
		clear();
		this.seqno 		= checkInt( req, "seqno", 0, 0, true );
    	this.page_type		= checkInt( req, "page_type", 1, 0, true );
    	this.s_catid  		= checkInt( req, "s_catid", 2, 0, true );
    	this.startdate		= checkInt( req, "startdate", 3, 3, false );
    	this.stopdate		= checkInt( req, "stopdate", 4, 3, false );
    	this.description	= checkStr( req, "description", 5, 1, false );
    	this.prod_id_1		= checkStr( req, "prod_id_1", 6, 1, false );
    	this.prod_id_2		= checkStr( req, "prod_id_2", 8, 1, false );
    	this.prod_id_3		= checkStr( req, "prod_id_3", 9, 1, false );
    	this.catid			= checkInt( req, "catid", 10, 0, false );
		return this;
	}

	public Categoryselect(){
		error_flg = new int[ 11 ];
	}
	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the page_type.
	 * @return int
	 */
	public int getPage_type() {
		return page_type;
	}

	/**
	 * Returns the prod_id_1.
	 * @return String
	 */
	public String getProd_id_1() {
		return prod_id_1;
	}

	/**
	 * Returns the prod_id_2.
	 * @return String
	 */
	public String getProd_id_2() {
		return prod_id_2;
	}

	/**
	 * Returns the prod_id_3.
	 * @return String
	 */
	public String getProd_id_3() {
		return prod_id_3;
	}

	/**
	 * Returns the s_catid.
	 * @return int
	 */
	public int getS_catid() {
		return s_catid;
	}

	/**
	 * Returns the seqno.
	 * @return int
	 */
	public int getSeqno() {
		return seqno;
	}

	/**
	 * Returns the startdate.
	 * @return int
	 */
	public int getStartdate() {
		return startdate;
	}

	/**
	 * Returns the stop_flg.
	 * @return String
	 */
	public String getStop_flg() {
		return stop_flg;
	}

	/**
	 * Returns the stopdate.
	 * @return int
	 */
	public int getStopdate() {
		return stopdate;
	}

	/**
	 * Returns the update_id.
	 * @return String
	 */
	public String getUpdate_id() {
		return update_id;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the page_type.
	 * @param page_type The page_type to set
	 */
	public void setPage_type(int page_type) {
		this.page_type = page_type;
	}

	/**
	 * Sets the prod_id_1.
	 * @param prod_id_1 The prod_id_1 to set
	 */
	public void setProd_id_1(String prod_id_1) {
		this.prod_id_1 = prod_id_1;
	}

	/**
	 * Sets the prod_id_2.
	 * @param prod_id_2 The prod_id_2 to set
	 */
	public void setProd_id_2(String prod_id_2) {
		this.prod_id_2 = prod_id_2;
	}

	/**
	 * Sets the prod_id_3.
	 * @param prod_id_3 The prod_id_3 to set
	 */
	public void setProd_id_3(String prod_id_3) {
		this.prod_id_3 = prod_id_3;
	}

	/**
	 * Sets the s_catid.
	 * @param s_catid The s_catid to set
	 */
	public void setS_catid(int s_catid) {
		this.s_catid = s_catid;
	}

	/**
	 * Sets the seqno.
	 * @param seqno The seqno to set
	 */
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	/**
	 * Sets the startdate.
	 * @param startdate The startdate to set
	 */
	public void setStartdate(int startdate) {
		this.startdate = startdate;
	}

	/**
	 * Sets the stop_flg.
	 * @param stop_flg The stop_flg to set
	 */
	public void setStop_flg(String stop_flg) {
		this.stop_flg = stop_flg;
	}

	/**
	 * Sets the stopdate.
	 * @param stopdate The stopdate to set
	 */
	public void setStopdate(int stopdate) {
		this.stopdate = stopdate;
	}

	/**
	 * Sets the update_id.
	 * @param update_id The update_id to set
	 */
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}

}
