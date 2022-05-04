package jp.co.lastminute.maintenance.gift.model;

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
public class GiftInfo extends Models implements Serializable{
	public int jan_cd = 0;				//00
	public String agt_product_cd = "";		//01
	public String agt_cd = "";				//02
	public int product_type = 0;			//03

	public String product_name = "";		//04
	public String product_sub_title = "";	//05
	public String catchcopy = "";			//06
	public String price_catch = "";		//07
	public String opening = "";			//08
	public String specword = "";			//09
	public String moreinfo = "";			//10
	
	public String c_coment = "";			//11
	public String body = "";				//12

	public int havealt = 0;				//13
	public int price = 0;					//14
	public int tax = 0;					//15
	public int sending = 0;				//16
	public int sending_tax = 0;			//17
	public int sending_calc_unit = 0;		//18
	public String first_name = "";			//19
	public String lastname = "";			//20
	
	public String mixed_types = "";		//21
	
	/**
	 * コンストラクタ
	 */
	public GiftInfo(){
		error_flg = new int[ 22 ];
	}
	public GiftInfo getNewReq( HttpServletRequest req ){
		clear();
		this.agt_cd = checkStr( req, "agt_cd", 4, 7, false );
		this.product_name 	= checkStr( req, "product_name", 6, 1, false );
		this.havealt = 1;
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
	public GiftInfo getRequest( HttpServletRequest req ){
		clear();
		this.jan_cd = checkInt( req, "jan_cd", 0, 0 ,false );
		this.agt_product_cd = checkStr( req, "agt_product_cd", 1, 1 ,true );
		this.agt_cd = checkStr( req, "agt_cd", 2, 7 ,true );
		this.product_type = checkInt( req, "product_type", 3, 0 ,true );
		this.product_name = checkStr( req, "product_name", 4, 1 ,true );
		this.product_sub_title = checkStr( req, "product_sub_title", 5, 1 ,true );
		this.catchcopy = checkStr( req, "catchcopy", 6, 1 ,false );
		this.price_catch = checkStr( req, "price_catch", 7, 1 ,false );
		this.opening = checkStr( req, "opening", 8, 1 ,false );
		this.specword  = checkStr( req, "specword", 9, 1 ,false );
		this.moreinfo = checkStr( req, "moreinfo", 10, 1 ,false );
		this.c_coment = checkStr( req, "c_coment", 11, 1 ,false );
		this.body = checkStr( req, "body", 12, 1 ,false );
		this.havealt = checkInt( req, "havealt", 13, 0 ,true );
		this.price = checkInt( req, "price", 14, 0 ,true );
		this.tax  = checkInt( req, "tax", 15, 0 ,true );
		this.sending = checkInt( req, "sending", 16, 0 ,true );
		this.sending_tax = checkInt( req, "sending_tax", 17, 0 ,true );
		this.sending_calc_unit = checkInt( req, "sending_calc_unit", 18, 0 ,true );
		this.first_name 	= checkStr( req, "first_name", 19, 1, false );
		this.lastname 		= checkStr( req, "lastname", 20, 1, false );
		this.mixed_types 	= checkStr( req, "mixed_types", 21, 1, false );
		
		return this;
	}

}
