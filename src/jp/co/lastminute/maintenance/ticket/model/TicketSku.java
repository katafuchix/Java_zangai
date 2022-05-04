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
public class TicketSku extends Models implements Serializable{
	public int sku_id 		= 0;	//00
	public String agt_cd   	= "";	//01
	public int    product_seq	= 0;	//02
	public int    salesfrom	= 0;	//03
	public String sku_name    	= "";	//04
	public String sheatandso   = "";	//05
	public int    viewstart    = 0;	//06
	public String optionalcomment  = "";	//07
	public int    salesto 	= 0;	//08
	public int    price 		= 0;	//09
	public int    tax          = 0;	//10
	public int    sending      = 0;	//11
	public int    sending_tax  = 0;	//12
	public int    sending_calc_unit  = 0;	//13
	/**
	 * コンストラクタ
	 */
	public TicketSku(){
		error_flg = new int[ 14 ];
	}
	public TicketSku getNewReq( HttpServletRequest req ){
		clear();
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.product_seq 	= checkInt( req, "product_seq", 2, 0, true );
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
	public TicketSku getRequest( HttpServletRequest req ){
		clear();
		this.sku_id 		= checkInt( req, "sku_id", 0, 0, false );
		this.agt_cd   		= checkStr( req, "agt_cd", 1, 7, true );
		this.product_seq	= checkInt( req, "product_seq", 2, 0, true );
		this.salesfrom		= checkInt( req, "salesfrom", 3, 3, true );
		this.sku_name    	= checkStr( req, "sku_name", 4, 1, true );
		this.sheatandso   	= checkStr( req, "sheatandso", 5, 1, false );
		this.viewstart    	= checkInt( req, "viewstart", 6, 0, true );
		this.optionalcomment  = checkStr( req, "optionalcomment", 7, 1, false );
		this.salesto 		= checkInt( req, "salesto", 8, 3, false );
		this.price 		= checkInt( req, "price", 9, 0, true );
		this.tax          	= checkInt( req, "tax", 10, 0, true );
		this.sending      	= checkInt( req, "sending", 11, 0, false );
		this.sending_tax  	= checkInt( req, "sending_tax", 12, 0, false );
		this.sending_calc_unit  = checkInt( req, "sending_calc_unit", 13, 0, false );
		return this;
	}
}
