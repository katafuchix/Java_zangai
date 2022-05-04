package jp.co.lastminute.maintenance.hotel.model;

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
public class Madrsp extends Models implements Serializable {
    public String supnbr = "";
    public String agt_cd = "";
    public String supnm1 = "";
    public String jpnnam = "";
    public String supad1 = "";
    public String supad2 = "";
    public String supad3 = "";
    public String promise = "";
    public String promiseuri = "";
    public String suptel = "";
    public String supfax = "";
    public String jpntxt = "";	//extention
    
    public String tokki = "";
    public String tokusyoku = "";
    public String tokusyoku_ = "";
    public String ladys = "";
    public String onlycharge = "";
    public String charge_and_bed = "";
    public String nochargeage = "";
    public String sogei_guide = "";
    public String parking_guide = "";
    public String attrubute = "";
    
    public String agt_name = "";
    public String htlcat = "";
    public String htlcar_name = "";
    public String localareacode = "";
    public String local_area_code_name = "";
    
    public String catch_copy = "";
    
    public String city_name = "";
    public String state_name = "";
    
    public String makejpntxt(){
    	jpntxt ="<OTHER>\n"
    			+ getTag( "tokki", tokki , true ) + "\n"
    			+ getTag( "tokusyoku", tokusyoku , true ) + "\n"
    			+ getTag( "tokusyoku", tokusyoku_ , true ) + "\n"
    			+ getTag( "ladys", ladys , true ) + "\n"
    			+"</OTHER>\n"
    			+"<BABY>\n"
    			+ getTag( "onlycharge", onlycharge , true ) + "\n"
    			+ getTag( "charge_and_bed", charge_and_bed , true ) + "\n"
    			+ getTag( "nochargeage", nochargeage , true ) + "\n"
    			+ getTag( "asogei_guidedys", sogei_guide , true ) + "\n"
    			+ getTag( "parking_guide", parking_guide , true ) + "\n"
    			+ getTag( "attrubute", attrubute , true ) + "\n"
    			+"</BABY>";    	
    	return jpntxt;
    }
    public void parseString( String str ){
    	String[] temp = parseStrQuot( CharacterConverter.toJISAutoDetect( str ) );
    	//temp[0] = 
    	this.supad1 = temp[ 0 ];
    	this.supnm1 = temp[ 9 ];
    	this.jpnnam = temp[ 10 ];
    	this.supad2 = temp[ 12 ];
    	this.supad3 = temp[ 13 ];
    	this.suptel = temp[ 14 ];
    	this.supfax = temp[ 15 ];
    	this.tokki  = temp[ 16 ];
    	this.tokusyoku 	= temp[ 17 ];
    	this.tokusyoku_ 	= temp[ 18 ];
    	this.ladys = temp[ 19 ];
    	this.onlycharge 	= temp[ 20 ];
    	this.charge_and_bed  = temp[ 21 ];
    	this.nochargeage 	= temp[ 22 ];
    	this.sogei_guide 	= temp[ 23 ];
    	this.parking_guide	= temp[ 24 ];
    	this.attrubute 	= temp[ 25 ];
    }    
    /**
	 * コンストラクター
	 */
	public Madrsp(){
		error_flg = new int[ 13 ];
	}
	/**
	 * 追加用
	 */
	public Madrsp getNewReq( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.supnm1 = checkStr( req, "supnm1", 2, 1, true );
		this.jpnnam = checkStr( req, "jpnnam", 3, 1, true );
		return this;
	}
	/**
	 * 更新用
	 */
	public Madrsp getRequest( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.supnm1 = checkStr( req, "supnm1", 2, 1, true );
		this.jpnnam = checkStr( req, "jpnnam", 3, 1, true );
		this.supad1 = checkStr( req, "supad1", 4, 0, true );
		this.supad2 = checkStr( req, "supad2", 5, 1, true );
		this.supad3 = checkStr( req, "supad3", 6, 1, false );
		this.promise = checkStr( req, "promise", 7, 0, false );
		this.promiseuri = checkStr( req, "promiseuri", 8, 1, false );
		this.suptel = checkStr( req, "suptel", 9, 1, true );
		this.supfax = checkStr( req, "supfax", 10, 1, true );
		this.jpntxt = checkStr( req, "jpntxt", 11, 1, false );
		this.catch_copy = checkStr( req, "catch_copy", 12, 1, false );
		return this;
	}
}
