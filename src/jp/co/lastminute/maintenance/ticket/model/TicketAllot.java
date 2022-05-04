package jp.co.lastminute.maintenance.ticket.model;

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
public class TicketAllot extends Models implements Serializable {
	public int allot_seq 		= 0;	//00
    public int sku_id     	= 0;	//01
    public int allotcounter  	= 0;	//02
    public int allotdate     	= 0;	//03
    public int allottime       = 0;	//04
    public int stop_flg        = 0;	//05
    public String product_name	= "";	//06
    public String sku_name		= "";	//07
    /**
     * コンストラクター
     */
    public TicketAllot(){
    	error_flg = new int[ 6 ];
    }
    public TicketAllot getNewReq( HttpServletRequest req ){
		clear();
		this.sku_id = checkInt( req, "sku_id", 1, 0, true );
		return this;
	}
	/**
	 * Httpの保持
	 */
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
	public TicketAllot getRequest( HttpServletRequest req ){
		clear();
		this.allot_seq 	= checkInt( req, "allot_seq", 0, 0, false );
    	this.sku_id     	= checkInt( req, "sku_id", 1, 0, false );
    	this.allotcounter  = checkInt( req, "allotcounter", 2, 0, false );
    	this.allotdate     = checkInt( req, "allotdate", 3, 3, false );
    	this.allottime     = checkInt( req, "allottime", 4, 0, false );
    	this.stop_flg      = checkInt( req, "stop_flg", 5, 0, false );
		return this;
	}
}
