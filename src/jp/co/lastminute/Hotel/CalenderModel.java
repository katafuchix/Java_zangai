package jp.co.lastminute.Hotel;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import jp.co.lastminute.common.Models;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CalenderModel extends Models implements Serializable{
	public String yyyy = "";	//00
	public String mm = "";		//01
	public String dd = "";		//02
	public int yearmonth = 0;	//03
	public int year = 0;	//
	public int month = 0;	//
	
	public int AfterDays = 0;				//04
	public String displayFieldName = "";	//05
	public int stopsaledays = 0;		//06
	public String order = "";			//07
	public String formName = "";		//08

	/**
	 * コンストラクター
	 */
	public CalenderModel(){
		error_flg = new int[ 9 ];//48//
	}
	/**
	 * クリア
	 */
	public void clear(){
		for( int i=0; i<error_flg.length; i++){
			this.error_flg[i] = 0;
		}
		this.error_sum = 0;
	}
	/**
	 * バリューメーキング
	 */
	public void makeValue(){
		this.year = this.yearmonth/100;
		this.month = this.yearmonth%100;
	}
	/**
	 * リクエストゲッター
	 */
	public CalenderModel getRequest( HttpServletRequest req ){
		clear();
		this.yyyy = checkStr( req, "yyyy", 0, 0, false );
		this.mm = checkStr( req, "mm", 1, 0, false );
		this.dd  = checkStr( req, "dd", 2, 0, false );
		this.yearmonth  = checkInt( req, "yearmonth", 3, 0, false );
		this.AfterDays = checkInt( req, "AfterDays", 4, 0, false );
		this.displayFieldName  = checkStr( req, "displayFieldName", 5, 1, false );
		this.stopsaledays  = checkInt( req, "stopsaledays", 6, 0, false );
		this.order  = checkStr( req, "order", 7, 1, true );
		this.formName = checkStr( req, "formName", 8, 1, true );
		this.makeValue();
		return this;
	}
}
