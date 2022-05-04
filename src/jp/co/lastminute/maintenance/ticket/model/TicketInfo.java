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
public class TicketInfo extends Models implements Serializable{
	public int product_seq = 0;			//00
	public String jan_cd = "";				//01
	public int group_cd = 0;				//02
	public int ticket_type = 0;			//03
	public String agt_cd = "";				//04
	public int product_type = 0;			//05

	public String product_name = "";		//06
	public String product_sub_title = "";	//07
	public String catchcopy = "";			//08
	public String price_catch = "";		//09
	public String opening = "";			//10
	public String actors = "";				//11
	public String produce = "";			//12
	public String play_place = "";			//13
	public String pointofaccess = "";		//14
	public String accesspoint_url = "";	//15
	public String description = "";		//16
	public String image_01_url = "";		//17
	public String image_01_url_1 = "";		//18
	public String image_01_url_2 = "";		//19
	public String image_01_url_3 = "";		//20
	public int stop_flg = 0;				//21
	public String linkedurl = "";			//22
	public String event_names = "";		//23
	public String type_names = "";			//24
	public String first_name = "";			//25
	public String lastname = "";			//26
	
	/**
	 * コンストラクタ
	 */
	public TicketInfo(){
		error_flg = new int[ 27 ];
	}
	public TicketInfo getNewReq( HttpServletRequest req ){
		clear();
		this.agt_cd = checkStr( req, "agt_cd", 4, 7, false );
		this.product_name 	= checkStr( req, "product_name", 6, 1, false );
		this.stop_flg = 1;
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
	public TicketInfo getRequest( HttpServletRequest req ){
		clear();
		this.product_seq 	= checkInt( req, "product_seq", 0, 0, false );
		this.jan_cd 		= checkStr( req, "jan_cd", 1, 1, false );
		this.group_cd 		= checkInt( req, "group_cd", 2, 0, false );
		this.ticket_type 	= checkInt( req, "ticket_type", 3, 0, true );
		this.agt_cd 		= checkStr( req, "agt_cd", 4, 7, true );
		this.product_type 	= checkInt( req, "product_type", 5, 0, true );
		this.product_name 	= checkStr( req, "product_name", 6, 1, true );
		this.product_sub_title = checkStr( req, "product_sub_title", 7, 1, true );
		this.catchcopy 	= checkStr( req, "catchcopy", 8, 1, true );
		this.price_catch 	= checkStr( req, "price_catch", 9, 1, false );
		this.opening 		= checkStr( req, "opening", 10, 1, false );
		this.actors 		= checkStr( req, "actors", 11, 1, false );
		this.produce 		= checkStr( req, "produce", 12, 1, false );
		this.play_place 	= checkStr( req, "play_place", 13, 1, false );
		this.pointofaccess = checkStr( req, "pointofaccess", 14, 1, false );
		this.accesspoint_url = checkStr( req, "accesspoint_url", 15, 1, false );
		this.description 	= checkStr( req, "description", 16, 1, false );
		this.image_01_url 	= checkStr( req, "image_01_url", 17, 1, true );
		this.image_01_url_1 = checkStr( req, "image_01_url_1", 18, 1, false );
		this.image_01_url_2 = checkStr( req, "image_01_url_2", 19, 1, false );
		this.image_01_url_3 = checkStr( req, "image_01_url_3", 20, 1, false );
		this.stop_flg 		= checkInt( req, "stop_flg", 21, 0, true );
		this.linkedurl 	= checkStr( req, "linkedurl", 22, 1, false );
		this.event_names 	= checkStr( req, "event_names", 23, 1, false );
		this.type_names 	= checkStr( req, "type_names", 24, 1, false );
		this.first_name 	= checkStr( req, "first_name", 25, 1, false );
		this.lastname 		= checkStr( req, "lastname", 26, 1, false );
		return this;
	}

}
