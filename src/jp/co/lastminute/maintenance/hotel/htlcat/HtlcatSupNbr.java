package jp.co.lastminute.maintenance.hotel.htlcat;

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
public class HtlcatSupNbr extends Models implements Serializable{
	public int seq_no = 0;
	public String supnbr = "";
	public String agt_cd = "";
	public int htlcat = 0;
	public String htlcat_name = "";
	
	public String code ="";
	
	/**
	 * コンストラクター
	 */
	public HtlcatSupNbr(){
		error_flg = new int[ 4 ];
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
	/**
	 * 追加用
	 */
	public HtlcatSupNbr getNewReq( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 1, 1, true );
		this.agt_cd = checkStr( req, "agt_cd", 2, 7, true );
		this.htlcat = checkInt( req, "htlcat", 3, 0, true);
		return this;
	}
	/**
	 * 更新用
	 */
	public HtlcatSupNbr getRequestByUpdate( HttpServletRequest req ){
		clear();
		this.seq_no = checkInt( req, "seq_no", 0, 0, true );
		this.supnbr = checkStr( req, "supnbr", 1, 1, true );
		this.agt_cd = checkStr( req, "agt_cd", 2, 7, true );
		this.htlcat = checkInt( req, "htlcat", 3, 0, true);
		return this;
	}
	/**
	 * 一覧用
	 */
	public HtlcatSupNbr getRequest( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 1, 1, true );
		this.agt_cd = checkStr( req, "agt_cd", 2, 7, true );
		return this;
	}
	
}