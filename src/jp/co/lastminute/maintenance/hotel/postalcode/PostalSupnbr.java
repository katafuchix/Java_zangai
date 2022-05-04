package jp.co.lastminute.maintenance.hotel.postalcode;

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
public class PostalSupnbr extends Models implements Serializable{
	public int seq_no = 0;
	public String supnbr = "";
	public String agt_cd= "";
	public String postal_cd = "";
	
	public int city_cd = 0;
	public int state_cd = 0;
	
	public String city_name = "";
	public String state_name = "";
	
	/**
	 * コンストラクター
	 */
	public PostalSupnbr(){
		error_flg = new int[ 6 ];
	}
	/**
	 * 追加用
	 */
	public PostalSupnbr getNewReq( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 1, 1, true );
		this.agt_cd = checkStr( req, "agt_cd", 2, 1, true );
		this.postal_cd = checkStr( req, "postal_cd", 3, 1, true );
		return this;
	}
	/**
	 * 一覧用
	 */
	public PostalSupnbr getRequest( HttpServletRequest req ){
		clear();
		clear();
		this.city_cd = checkInt( req, "city_cd", 4, 0, false );
		this.state_cd = checkInt( req, "state_cd", 5, 0, false );
		return this;
	}
	/**
	 * 削除用
	 */
	public PostalSupnbr getRequestByUpdate( HttpServletRequest req ){
		clear();
		this.seq_no = checkInt( req, "seq_no", 0, 0, true );
		return this;
	}
}
