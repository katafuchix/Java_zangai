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
public class Catid_small extends Models implements Serializable{
	public int s_catid 		= 0;
	public int catid 	 	= 0;
	public String catch_copy = "";
	public String description= "";
	public String update_id = "";
	public String makedate_id= "";
	public String stop_flg = "0";
	public int product_type_cd = 0;
	
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
	public Catid_small getRequest( HttpServletRequest req ){
		clear();
		this.s_catid 	= checkInt( req, "s_catid", 0, 0, false );
    	this.catid     	= checkInt( req, "catid", 1, 0, false );
    	this.catch_copy  = checkStr( req, "catch_copy", 2, 1, false );
    	this.description     = checkStr( req, "description", 3, 1, false );
    	this.update_id     = checkStr( req, "makedate_id", 4, 0, false );
    	this.makedate_id      = checkStr( req, "stop_flg", 5, 0, false );
    	this.stop_flg      = checkStr( req, "stop_flg", 6, 0, false );
    	this.product_type_cd      = checkInt( req, "catid", 1, 0, false );
		return this;
	}
	public Catid_small getRequestStopFlg( HttpServletRequest req ){
		clear();
		this.catid 	= checkInt( req, "catid", 0, 0, false );
		this.s_catid 	= checkInt( req, "s_catid", 0, 0, false );
		return this;
	}
	
	public static Catid_small[] getRequestGetList( HttpServletRequest req ){
		try{
			String values[] = req.getParameterValues( "chtarget" );
			Catid_small[] reCatid_small = new Catid_small[ values.length ];
			for(int i=0; i<values.length; i++){
				Catid_small tempscat = new Catid_small();
				int j = Integer.parseInt( req.getParameterValues( "chtarget" )[ i ] );
				tempscat.clear();
				tempscat.s_catid 	= tempscat.checkInt( req, "s_catid", 0, 0, false, j);
		    	tempscat.catid     	= tempscat.checkInt( req, "catid", 1, 0, false);
		    	tempscat.catch_copy  = tempscat.checkStr( req, "catch_copy", 2, 1, false, j );
		    	tempscat.description   = tempscat.checkStr( req, "description", 3, 1, false, j );
		    	tempscat.update_id     = tempscat.checkStr( req, "makedate_id", 4, 0, false, j );
		    	tempscat.makedate_id   = tempscat.checkStr( req, "stop_flg", 5, 0, false, j );
		    	tempscat.stop_flg      = tempscat.checkStr( req, "stop_flg", 6, 0, false, j );
		    	tempscat.product_type_cd      = tempscat.checkInt( req, "catid", 1, 0, false );
	    		reCatid_small[ i ] = tempscat;
	    		tempscat = null;
			}
			return reCatid_small;
		}catch( Exception ex ){	ex.printStackTrace();	}
		return null;
	}
	
	
	public Catid_small(){
		error_flg = new int[ 6 ];
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
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the makedate_id.
	 * @return String
	 */
	public String getMakedate_id() {
		return makedate_id;
	}

	/**
	 * Returns the product_type_cd.
	 * @return int
	 */
	public int getProduct_type_cd() {
		return product_type_cd;
	}

	/**
	 * Returns the s_catid.
	 * @return int
	 */
	public int getS_catid() {
		return s_catid;
	}

	/**
	 * Returns the stop_flg.
	 * @return String
	 */
	public String getStop_flg() {
		if( stop_flg.length() == 0 ){
			return "0";	
		}
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
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the makedate_id.
	 * @param makedate_id The makedate_id to set
	 */
	public void setMakedate_id(String makedate_id) {
		this.makedate_id = makedate_id;
	}

	/**
	 * Sets the product_type_cd.
	 * @param product_type_cd The product_type_cd to set
	 */
	public void setProduct_type_cd(int product_type_cd) {
		this.product_type_cd = product_type_cd;
	}

	/**
	 * Sets the s_catid.
	 * @param s_catid The s_catid to set
	 */
	public void setS_catid(int s_catid) {
		this.s_catid = s_catid;
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
