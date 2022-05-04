package jp.co.lastminute.maintenance.hotel.localarea;

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
public class LocalArea extends Models implements Serializable{
	public int local_area_code = 0;
	public int parent_code = 0;
	public String local_area_code_name = "";
	
	public String code ="";
	
	/**
	 * コンストラクター
	 */
	public LocalArea(){
		error_flg = new int[ 4 ];
	}
	/**
	 * 追加用
	 */
	public LocalArea getNewReq( HttpServletRequest req ){
		clear();
		this.local_area_code = checkInt( req, "local_area_code", 0, 1, true );
		this.parent_code = checkInt( req, "parent_code", 1, 1, true );
		this.local_area_code_name = checkStr( req, "local_area_code_name", 2, 1, true );
		return this;
	}
	/**
	 * 一覧用
	 */
	public LocalArea getRequest( HttpServletRequest req ){
		clear();
		try{
			if(( req.getParameter("parent_code") != null )
				&&( req.getParameter("parent_code").length() > 0 )){
				this.parent_code = Integer.parseInt( req.getParameter("parent_code") );
			}else{
				this.parent_code = 0;
			}
		}catch(Exception ex){	ex.printStackTrace();	
			this.parent_code = 0;
		}
		return this;
	}
	/**
	 * 更新用
	 */
	public LocalArea getRequestByUpdate( HttpServletRequest req ){
		clear();
		this.local_area_code = checkInt( req, "local_area_code", 0, 0, true );
		this.parent_code = checkInt( req, "parent_code", 1, 0, true );
		this.local_area_code_name = checkStr( req, "local_area_code_name", 2, 1, true );
		return this;
	}
	/**
	 * スプリッタ
	 */
	private boolean setSprit( HttpServletRequest req){
		try{
			this.code = checkStr( req, "code", 4, 1, true );
			int postion = (this.code).indexOf(":");
			this.local_area_code = Integer.parseInt( (this.code).substring(0, postion) );
			this.parent_code = Integer.parseInt( (this.code).substring(postion + 1) );
			return true;
		}catch(Exception ex){	ex.printStackTrace();	}
		this.error_flg[ 0 ] = 1;
		this.error_flg[ 1 ] = 1;
		return false;
	}

}