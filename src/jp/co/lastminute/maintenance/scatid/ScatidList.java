package jp.co.lastminute.maintenance.scatid;

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
public class ScatidList extends Models implements Serializable{
	public int seqno = 0;
	public int s_catid = 0;
	public String jan_cd = "";
	public String title = "";
	public int catid = 0;
	public String type_name = "";
	public int priority = 0;
	public String catch_copy = "";
		
	public ScatidList(){
		error_flg = new int[ 8 ];
	}
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
	public ScatidList getRequest( HttpServletRequest req ){
		clear();
		this.seqno 	= checkInt( req, "seqno", 0, 0, false );
    	this.s_catid     	= checkInt( req, "s_catid", 1, 0, true );
    	this.jan_cd  = checkStr( req, "jan_cd", 2, 1, true );
    	this.title     = checkStr( req, "title", 3, 1, false );
    	this.catid     = checkInt( req, "catid", 4, 0, true );
    	this.type_name      = checkStr( req, "type_name", 5, 1, false );
    	this.priority      = checkInt( req, "priority", 6, 0, false );
    	this.catch_copy      = checkStr( req, "catch_copy", 7, 1, false );
		return this;
	}
	/**
	 * Returns the catch_copy.
	 * @return String
	 */
	public String getCatch_copy() {
		return catch_copy;
	}

	/**
	 * Returns the catid.
	 * @return int
	 */
	public int getCatid() {
		return catid;
	}

	/**
	 * Returns the jan_cd.
	 * @return String
	 */
	public String getJan_cd() {
		return jan_cd;
	}

	/**
	 * Returns the priority.
	 * @return int
	 */
	public int getPriority() {
		return priority;
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
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the type_name.
	 * @return String
	 */
	public String getType_name() {
		return type_name;
	}

	/**
	 * Sets the catch_copy.
	 * @param catch_copy The catch_copy to set
	 */
	public void setCatch_copy(String catch_copy) {
		this.catch_copy = catch_copy;
	}

	/**
	 * Sets the catid.
	 * @param catid The catid to set
	 */
	public void setCatid(int catid) {
		this.catid = catid;
	}

	/**
	 * Sets the jan_cd.
	 * @param jan_cd The jan_cd to set
	 */
	public void setJan_cd(String jan_cd) {
		this.jan_cd = jan_cd;
	}

	/**
	 * Sets the priority.
	 * @param priority The priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the type_name.
	 * @param type_name The type_name to set
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}
