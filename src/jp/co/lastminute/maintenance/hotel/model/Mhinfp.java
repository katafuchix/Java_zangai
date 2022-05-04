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
public class Mhinfp extends Models implements Serializable {
	private static final int startnum = 59;
	//
	public String supnbr = "";
	public String agt_cd = "";
	//////////////////////////////
    public String rspnum = "";
    public String rsfnum = "";
    public String hemail = "";
    public String webadd = "";
    public String ofccat = "";
    public String flrnum = "";
    public String elevtr = "";
    public String elecap = "";
    public String lstrfb = "";
    public String rnvstr = "";
    public String rnvend = "";
    public String rnvdtl = "";
    public String traficxml = "";
    public String grpchk = "";
    public String laneng = "";
    public String lanjap = "";
    public String langxml = "";
    public String phnr2r = "";
    public String phonexml = "";
    public String totrst = "";
    public String totbar = "";
    public String totcon = "";
    public String imgfrn = "";
    public String imglob = "";
    public String imgrom = "";
    public String checkinxml = "" ;//extention
    public String CHX_inxml = "";
    public String CHX_early = "";
    public String CHX_checkin = "";
    public String CHX_checkout = "";
    public String servicexml = ""; //extention
    public String SVX_SV_cleaning = "";
    public String SVX_SV_copy = "";
    public String SVX_SV_silver = "";
    public String SVX_SV_dvcard = "";
    public String SVX_SV_fax	= "";
    public String SVX_SV_relax ="";
    public String SVX_SV_roomservice = "";
    public String SVX_SV_latecheckout = "";
    public String SVX_SV_foreign = "";
    public String SVX_SV_citymap = "";
    public String SVX_SV_wheelchair = "";
    public String SVX_SV_signlang = "";
    public String SVX_SV_latecheckin = "";
    public String SVX_SV_homederiverry = "";
    public String SVX_SV_morning_newspaper = "";
    public String SVX_SV_guidedog = "";
    public String SVX_SV_shatle = "";
    public String SVX_SV_evening_paper = "";
    public String SVX_BF_changeable = "";
    public String SVX_BF_roomserevie = "";
    public String SVX_BF_time = "";
    public String SVX_BF_place = "";
    public String SVX_BF_amount = "";
    public String SVX_BF_child = "";
    public String SVX_BF_supperrest = "";
    public String SVX_DN_others = "";
    public String SVX_DN_chinese_rest = "";
    public String SVX_DN_rest = "";
    public String SVX_DN_place = "";
    public String SVX_DN_time = "";
    public String SVX_DN_amount = "";
    public String SVX_DN_viking = "";
    public String SVX_DN_child = "";
    public String SVX_supperrest = "";
    public String SVX_meal_copy = "";
    public String SVX_pre_reservation_rest = "";
    public String SVX_mailcoupon = "";
    public String SVX_room_mael = "";
    
    public String makecheckinxml(){
    	checkinxml = "<CHECKINTIME>\n"
    			+ getTag( "early", CHX_early, true) + "\n"
    			+"</CHECKINTIME>\n"
				+ getTag( "checkin", CHX_checkin, true) + "\n"
				+ getTag( "checkout", CHX_checkout, true) + "\n";
		return checkinxml;
    }
    public String makeservicexm(){
    	servicexml = "<SERVICE>\n"
				+ getTag( "cleaning", SVX_SV_cleaning, true) + "\n"
				+ getTag( "copy", SVX_SV_copy, true) + "\n"
				+ getTag( "silver", SVX_SV_silver, true) + "\n"
				+ getTag( "dvcard", SVX_SV_dvcard, true) + "\n"
				+ getTag( "fax", SVX_SV_fax, true) + "\n"
				+ getTag( "relax",SVX_SV_relax, true) + "\n"
				+ getTag( "roomservice", SVX_SV_roomservice, true) + "\n"
				+ getTag( "latecheckout", SVX_SV_latecheckout, true) + "\n"
				+ getTag( "foreign", SVX_SV_foreign, true) + "\n"
				+ getTag( "citymap", SVX_SV_citymap, true) + "\n"
				+ getTag( "wheelchair", SVX_SV_wheelchair, true) + "\n"
				+ getTag( "signlang", SVX_SV_signlang, true) + "\n"
				+ getTag( "latecheckin", SVX_SV_latecheckin, true) + "\n"
				+ getTag( "homederiverry", SVX_SV_homederiverry, true) + "\n"
				+ getTag( "morning_newspaper", SVX_SV_morning_newspaper, true) + "\n"
				+ getTag( "guidedog", SVX_SV_guidedog, true) + "\n"
				+ getTag( "shatle", SVX_SV_shatle, true) + "\n"
				+ getTag( "evening_paper",SVX_SV_evening_paper, true) + "\n"
				+ "</SERVICE>\n"
				+ "<BREAKFAST>\n"
				+ getTag( "changeable", SVX_BF_changeable, true) + "\n"
				+ getTag( "roomserevie", SVX_BF_roomserevie, true) + "\n"
				+ getTag( "time", SVX_BF_time, true) + "\n"
				+ getTag( "place", SVX_BF_place, true) + "\n"
				+ getTag( "amount", SVX_BF_amount, true) + "\n"
				+ getTag( "child", SVX_BF_child, true) + "\n"
				+ "</BREAKFAST>\n"
				+ "<DINNER>\n"
				+ getTag( "supperrest", SVX_BF_supperrest, true) + "\n"
				+ getTag( "others", SVX_DN_others, true) + "\n"
				+ getTag( "chinese_rest", SVX_DN_chinese_rest, true) + "\n"
				+ getTag( "rest", SVX_DN_rest, true) + "\n"
				+ getTag( "place", SVX_DN_place, true) + "\n"
				+ getTag( "time", SVX_DN_time, true) + "\n"
				+ getTag( "amount", SVX_DN_amount, true) + "\n"
				+ getTag( "viking", SVX_DN_viking, true) + "\n"
				+ getTag( "child", SVX_DN_child, true) + "\n"
				+ "</DINNER>\n"
				+ getTag( "supperrest",SVX_supperrest, true) + "\n"
				+ getTag( "meal_copy", SVX_meal_copy, true) + "\n"
				+ getTag( "pre_reservation_rest", SVX_pre_reservation_rest, true) + "\n"
				+ getTag( "mailcoupon", SVX_mailcoupon, true) + "\n"			
				+ getTag( "room_mael", SVX_room_mael, true) + "\n";
		return servicexml;
    }
    public void parseString( String str ){
    	String[] temp = parseStrQuot( CharacterConverter.toJISAutoDetect( str ) );
    	//temp[0] = 
    	this.rspnum = temp[ startnum + 0 ];
		this.rsfnum = temp[ startnum + 1 ];
		this.hemail = temp[ startnum + 2 ];
		this.webadd = temp[ startnum + 3 ];
		this.ofccat = temp[ startnum + 4 ];
		this.flrnum = temp[ startnum + 5 ];
		this.elevtr = temp[ startnum + 6 ];
		this.elecap = temp[ startnum + 7 ];
		this.lstrfb = temp[ startnum + 8 ];
		this.rnvstr = temp[ startnum + 9 ];
		this.rnvend = temp[ startnum + 10 ];
		this.rnvdtl = temp[ startnum + 11 ];
		this.traficxml = temp[ startnum + 12 ];
		this.grpchk = temp[ startnum + 13 ];
		this.laneng = temp[ startnum + 14 ];
		this.lanjap = temp[ startnum + 15 ];
		this.langxml = temp[ startnum + 16 ];
		this.phnr2r = temp[ startnum + 17 ];
		this.phonexml = temp[ startnum + 18 ];
		this.totrst = temp[ startnum + 19 ];
		this.totbar = temp[ startnum + 20 ];
		this.totcon = temp[ startnum + 21 ];
		this.imgfrn = temp[ startnum + 22 ];
		this.imglob = temp[ startnum + 23 ];
		this.imgrom = temp[ startnum + 24 ];
		this.CHX_inxml = temp[ startnum + 25 ];
		this.CHX_early = temp[ startnum + 26 ];
		this.CHX_checkin = temp[ startnum + 27 ];
		this.CHX_checkout = temp[ startnum + 28 ];
		this.SVX_SV_cleaning = temp[ startnum + 29 ];
		this.SVX_SV_copy = temp[ startnum + 30 ];
		this.SVX_SV_silver = temp[ startnum + 31 ];
		this.SVX_SV_dvcard = temp[ startnum + 32 ];
		this.SVX_SV_fax	= temp[ startnum + 33 ];
		this.SVX_SV_relax =temp[ startnum + 34 ];
		this.SVX_SV_roomservice = temp[ startnum + 35 ];
		this.SVX_SV_latecheckout = temp[ startnum + 36 ];
		this.SVX_SV_foreign = temp[ startnum + 37 ];
		this.SVX_SV_citymap = temp[ startnum + 38 ];
		this.SVX_SV_wheelchair = temp[ startnum + 39 ];
		this.SVX_SV_signlang = temp[ startnum + 40 ];
		this.SVX_SV_latecheckin = temp[ startnum + 41 ];
		this.SVX_SV_homederiverry = temp[ startnum + 42 ];
		this.SVX_SV_morning_newspaper = temp[ startnum + 43 ];
		this.SVX_SV_guidedog = temp[ startnum + 44 ];
		this.SVX_SV_shatle = temp[ startnum + 45 ];
		this.SVX_SV_evening_paper = temp[ startnum + 46 ];
		this.SVX_BF_changeable = temp[ startnum + 47 ];
		this.SVX_BF_roomserevie = temp[ startnum + 48 ];
		this.SVX_BF_time = temp[ startnum + 49 ];
		this.SVX_BF_place = temp[ startnum + 50 ];
		this.SVX_BF_amount = temp[ startnum + 51 ];
		this.SVX_BF_child = temp[ startnum + 52 ];
		this.SVX_BF_supperrest = temp[ startnum + 53 ];
		this.SVX_DN_others = temp[ startnum + 54 ];
		this.SVX_DN_chinese_rest = temp[ startnum + 55 ];
		this.SVX_DN_rest = temp[ startnum + 56 ];
		this.SVX_DN_place = temp[ startnum + 57 ];
		this.SVX_DN_time = temp[ startnum + 58 ];
		this.SVX_DN_amount = temp[ startnum + 59 ];
		this.SVX_DN_viking = temp[ startnum + 60 ];
		this.SVX_DN_child = temp[ startnum + 61 ];
		this.SVX_supperrest = temp[ startnum + 62 ];
		this.SVX_meal_copy = temp[ startnum + 63 ];
		this.SVX_pre_reservation_rest = temp[ startnum + 64 ];
		this.SVX_mailcoupon = temp[ startnum + 65 ];
		this.SVX_room_mael = temp[ startnum + 66 ];
    }    
    /**
	 * コンストラクター
	 */
	public Mhinfp(){
		error_flg = new int[ 71 ];
	}
	/**
	 * 追加用
	 */
	public Mhinfp getNewReq( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		return this;
	}
	/**
	 * 更新用
	 */
	public Mhinfp getRequest( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.rspnum = checkStr( req, "rspnum", 2, 1, false );
		this.rsfnum = checkStr( req, "rsfnum", 3, 1, false );
		this.hemail = checkStr( req, "hemail", 4, 1, false );
		this.webadd = checkStr( req, "webadd", 5, 1, false );
		this.ofccat = checkStr( req, "ofccat", 6, 1, false );
		this.flrnum = checkStr( req, "flrnum", 7, 1, false );
		this.elevtr = checkStr( req, "elevtr", 8, 1, false );
		this.elecap = checkStr( req, "elecap", 9, 1, false );
		this.lstrfb = checkStr( req, "lstrfb", 10, 1, false );
		this.rnvstr = checkStr( req, "rnvstr", 11, 1, false );
		this.rnvend = checkStr( req, "rnvend", 12, 1, false );
		this.rnvdtl = checkStr( req, "rnvdtl", 13, 1, false );
		this.traficxml = checkStr( req, "traficxml", 14, 1, false );
		this.grpchk = checkStr( req, "grpchk", 15, 1, false );
		this.laneng = checkStr( req, "laneng", 16, 1, false );
		this.lanjap = checkStr( req, "lanjap", 17, 1, false );
		this.langxml = checkStr( req, "langxml", 18, 1, false );
		this.phnr2r = checkStr( req, "phnr2r", 19, 1, false );
		this.phonexml = checkStr( req, "phonexml", 20, 1, false );
		this.totrst = checkStr( req, "totrst", 21, 1, false );
		this.totbar = checkStr( req, "totbar", 22, 1, false );
		this.totcon = checkStr( req, "totcon", 23, 1, false );
		this.imgfrn = checkStr( req, "imgfrn", 24, 1, false );
		this.imglob = checkStr( req, "imglob", 25, 1, false );
		this.imgrom = checkStr( req, "imgrom", 26, 1, false );
		this.CHX_inxml = checkStr( req, "CHX_inxml", 27, 1, false );
		this.CHX_early = checkStr( req, "CHX_early", 28, 1, false );
		this.CHX_checkin = checkStr( req, "CHX_checkin", 29, 1, false );
		this.CHX_checkout = checkStr( req, "CHX_checkout", 30, 1, false );
		this.SVX_SV_cleaning = checkStr( req, "SVX_SV_cleaning", 31, 1, false );
		this.SVX_SV_copy = checkStr( req, "SVX_SV_copy", 32, 1, false );
		this.SVX_SV_silver = checkStr( req, "SVX_SV_silver", 33, 1, false );
		this.SVX_SV_dvcard = checkStr( req, "SVX_SV_dvcard", 34, 1, false );
		this.SVX_SV_fax	= checkStr( req, "SVX_SV_fax", 35, 1, false );
		this.SVX_SV_relax =checkStr( req, "SVX_SV_relax", 36, 1, false );
		this.SVX_SV_roomservice = checkStr( req, "SVX_SV_roomservice", 37, 1, false );
		this.SVX_SV_latecheckout = checkStr( req, "SVX_SV_latecheckout", 38, 1, false );
		this.SVX_SV_foreign = checkStr( req, "SVX_SV_foreign", 39, 1, false );
		this.SVX_SV_citymap = checkStr( req, "SVX_SV_citymap", 40, 1, false );
		this.SVX_SV_wheelchair = checkStr( req, "SVX_SV_wheelchair", 41, 1, false );
		this.SVX_SV_signlang = checkStr( req, "SVX_SV_signlang", 42, 1, false );
		this.SVX_SV_latecheckin = checkStr( req, "SVX_SV_latecheckin", 43, 1, false );
		this.SVX_SV_homederiverry = checkStr( req, "SVX_SV_homederiverry", 44, 1, false );
		this.SVX_SV_morning_newspaper = checkStr( req, "SVX_SV_morning_newspaper", 45, 1, false );
		this.SVX_SV_guidedog = checkStr( req, "SVX_SV_guidedog", 46, 1, false );
		this.SVX_SV_shatle = checkStr( req, "SVX_SV_shatle", 47, 1, false );
		this.SVX_SV_evening_paper = checkStr( req, "SVX_SV_evening_paper", 48, 1, false );
		this.SVX_BF_changeable = checkStr( req, "SVX_BF_changeable", 49, 1, false );
		this.SVX_BF_roomserevie = checkStr( req, "SVX_BF_roomserevie", 50, 1, false );
		this.SVX_BF_time = checkStr( req, "SVX_BF_time", 51, 1, false );
		this.SVX_BF_place = checkStr( req, "SVX_BF_place", 52, 1, false );
		this.SVX_BF_amount = checkStr( req, "SVX_BF_amount", 53, 1, false );
		this.SVX_BF_child = checkStr( req, "SVX_BF_child", 54, 1, false );
		this.SVX_BF_supperrest = checkStr( req, "SVX_BF_supperrest", 55, 1, false );
		this.SVX_DN_others = checkStr( req, "SVX_DN_others", 56, 1, false );
		this.SVX_DN_chinese_rest = checkStr( req, "SVX_DN_chinese_rest", 57, 1, false );
		this.SVX_DN_rest = checkStr( req, "SVX_DN_rest", 58, 1, false );
		this.SVX_DN_place = checkStr( req, "SVX_DN_place", 59, 1, false );
		this.SVX_DN_time = checkStr( req, "SVX_DN_time", 60, 1, false );
		this.SVX_DN_amount = checkStr( req, "SVX_DN_amount", 61, 1, false );
		this.SVX_DN_viking = checkStr( req, "SVX_DN_viking", 62, 1, false );
		this.SVX_DN_child = checkStr( req, "SVX_DN_child", 63, 1, false );
		this.SVX_supperrest = checkStr( req, "SVX_supperrest", 64, 1, false );
		this.SVX_meal_copy = checkStr( req, "SVX_meal_copy", 65, 1, false );
		this.SVX_pre_reservation_rest = checkStr( req, "SVX_pre_reservation_rest", 66, 1, false );
		this.SVX_mailcoupon = checkStr( req, "SVX_mailcoupon", 67, 1, false );
		this.SVX_room_mael = checkStr( req, "SVX_room_mael", 68, 1, false );
		
		this.checkinxml = checkStr( req, "checkinxml", 69, 1, false );
		this.servicexml = checkStr( req, "servicexml", 70, 1, false );
		
		
		return this;
	}
}
