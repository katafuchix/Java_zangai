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
public class Mhfcwp extends Models implements Serializable {
	private static final int startnum = 59;
	///////
    public String supnbr = "";
    public String agt_cd = "";
    ////
	public String karaoke = "";
	public String gateball = "";
	public String landly = "";
	public String gamecorner = "";
	public String convshop = "";
	public String sauna_woman = "";
	public String sauna_man = "";
	public String dance = "";
	public String tennis = "";
	public String fittness = "";
	public String outdoor_pool = "";	//extention
	public String outdoor_pool_val = "";
	public String indooe_pool = "";	//extention
	public String indooe_pool_val = "";
	public String OD_child = "";
	public String ID_child = "";
	public String ladysroom = "";
	public String canference = "";
	public String nonsmoking = "";
	public String usebld = "";
	public String entrance = "";
	public String effect = "";
	public String handicapped_toilet = "";
	public String ice_machin = "";
	public String other = "";
	public String equipment_others = "";
	public String quality_of_spring = "";
	public String kasikiri_bath = "";
	public String bathroom_woman = "";
	public String bathroom_man = "";
	public String age_reconstruction = "";
	public String parking_of_one_night = "";
	public String type_of_parking = "";
	public String limit_of_parking = "";
	public String amount_of_parking = "";
	public String condition_of_parking = "";
	public String time_of_parking = "";
	public String reservation_of_parking = "";
	public String natural_spring = "";
	public String braille_guidance = "";
	public String open_air_spring_woman = "";
	public String open_air_spring_man = "";
	public String goods = "";	//extention
	public String GD_amenity = "";
	public String shower = "";		//extention
	public String SH_tiolet = "";
	public String SH_booth = "";
	public String presser = "";
	public String drier = "";
	public String both = "";	//extention
	public String BT_rowel = "";
	public String BT_robe = "";
	public String modulerjack = "";
	public String satelite = "";
	public String pot = "";
	public String video = "";
	public String refrigerator = ""; //extention
	public String refrigerator_val  = "";
	public String RF_inside = "";
	
	public String buildxml = "";
    
    public String makeoutdoor_pool(){
    	outdoor_pool ="<OUTDOOR_POOL>\n"
    			+ getTag( "value", outdoor_pool_val , true ) + "\n"
    			+ getTag( "child", OD_child , true ) + "\n"
    			+"</OUTDOOR_POOL>";    	
    	return outdoor_pool;
    }
    public String makeindoor_pool(){
    	outdoor_pool ="<INDOOR_POOL>\n"
    			+ getTag( "value", indooe_pool_val , true ) + "\n"
    			+ getTag( "child", ID_child , true ) + "\n"
    			+"</INDOOR_POOL>";    	
    	return outdoor_pool;
    }
    public String makegoods(){
    	goods = "<GOODS>" + getTag( "amenity", GD_amenity , true ) + "</GOODS>\n";
    	return goods;
    }
    public String makeshower(){
    	shower = "<SHOWER>\n"
    		   + getTag( "tiolet", SH_tiolet , true ) + "\n"
    		   + getTag( "booth", SH_booth , true ) + "\n"
    		   + "</SHOWER>";
    	return shower;
    }
    public String makerefrigerator(){
    	refrigerator = "<REFRIGERATOR>\n"
    				 + getTag( "value", refrigerator_val , true ) + "\n"
    				 + getTag( "inside", RF_inside , true ) + "\n"
    				 + "</REFRIGERATOR>";
    	return refrigerator;
    }
    public String makeBuldXml(){
    	buildxml = getTag( "karaoke", karaoke, true) + "\n"
				+ getTag( "gateball", gateball, true) + "\n"
				+ getTag( "landly", landly, true) + "\n"
				+ getTag( "gamecorner", gamecorner, true) + "\n"
				+ getTag( "convshop", convshop, true) + "\n"
				+ getTag( "sauna_woman", sauna_woman, true) + "\n"
				+ getTag( "sauna_man", sauna_man, true) + "\n"
				+ getTag( "dance", dance, true) + "\n"
				+ getTag( "tennis", tennis, true) + "\n"
				+ getTag( "fittness", fittness, true) + "\n"
				+ getTag( "ladysroom", ladysroom, true) + "\n"
				+ getTag( "canference", canference, true) + "\n"
				+ getTag( "nonsmoking", nonsmoking, true) + "\n"
				+ getTag( "usebld", usebld, true) + "\n"
				+ getTag( "entrance", entrance, true) + "\n"
				+ getTag( "effect", effect, true) + "\n"
				+ getTag( "handicapped_toilet", handicapped_toilet, true) + "\n"
				+ getTag( "ice_machin", ice_machin, true) + "\n"
				+ getTag( "other", other, true) + "\n"
				+ getTag( "equipment_others", equipment_others, true) + "\n"
				+ getTag( "quality_of_spring", quality_of_spring, true) + "\n"
				+ getTag( "kasikiri_bath", kasikiri_bath, true) + "\n"
				+ getTag( "bathroom_woman", bathroom_woman, true) + "\n"
				+ getTag( "bathroom_man", bathroom_man, true) + "\n"
				+ getTag( "age_reconstruction", age_reconstruction, true) + "\n"
				+ getTag( "parking_of_one_night", parking_of_one_night, true) + "\n"
				+ getTag( "type_of_parking", type_of_parking, true) + "\n"
				+ getTag( "limit_of_parking", limit_of_parking, true) + "\n"
				+ getTag( "amount_of_parking", amount_of_parking, true) + "\n"
				+ getTag( "condition_of_parking", condition_of_parking, true) + "\n"
				+ getTag( "time_of_parking", time_of_parking, true) + "\n"
				+ getTag( "reservation_of_parking", reservation_of_parking, true) + "\n"
				+ getTag( "natural_spring", natural_spring, true) + "\n"
				+ getTag( "braille_guidance", braille_guidance, true) + "\n"
				+ getTag( "open_air_spring_woman", open_air_spring_woman, true) + "\n"
				+ getTag( "open_air_spring_man", open_air_spring_man, true) + "\n"
				+ getTag( "presser", presser, true) + "\n"
				+ getTag( "drier", drier, true) + "\n"
				+ getTag( "modulerjack", modulerjack, true) + "\n"
				+ getTag( "satelite", satelite, true) + "\n"
				+ getTag( "pot", pot, true) + "\n"
				+ getTag( "video", video, true) + "\n"
				+"<OUTDOOR_POOL>"+outdoor_pool+"</OUTDOOR_POOL>\n"
				+"<INDOOE_POOL>"+indooe_pool+"</INDOOE_POOL>\n"
				+"<GOODS>"+goods+"</GOODS>\n"
				+"<SHOWER>"+shower+"</SHOWER>\n"
				+"<BOTH>"+both+"</BOTH>\n"
				+"<REFRIGERATOR>"+refrigerator+"</REFRIGERATOR>";
    	return buildxml;
    }
    public void parseString( String str ){
    	String[] temp = parseStrQuot( CharacterConverter.toJISAutoDetect( str ) );
    	//temp[0] = 
    	this.karaoke = temp[ startnum + 0 ] ;
		this.gateball = temp[ startnum + 1 ] ;
		this.landly = temp[ startnum + 2 ] ;
		this.gamecorner = temp[ startnum + 3 ] ;
		this.convshop = temp[ startnum + 4 ] ;
		this.sauna_woman = temp[ startnum + 5 ] ;
		this.sauna_man = temp[ startnum + 6 ] ;
		this.dance = temp[ startnum + 7 ] ;
		this.tennis = temp[ startnum + 8 ] ;
		this.fittness = temp[ startnum + 9 ] ;
		this.outdoor_pool_val = temp[ startnum + 10 ] ;
		this.indooe_pool_val = temp[ startnum + 11 ] ;
		this.OD_child = temp[ startnum + 12 ] ;
		this.ID_child = temp[ startnum + 13 ] ;
		this.ladysroom = temp[ startnum + 14 ] ;
		this.canference = temp[ startnum + 15 ] ;
		this.nonsmoking = temp[ startnum + 16 ] ;
		this.usebld = temp[ startnum + 17 ] ;
		this.entrance = temp[ startnum + 18 ] ;
		this.effect = temp[ startnum + 19 ] ;
		this.handicapped_toilet = temp[ startnum + 20 ] ;
		this.ice_machin = temp[ startnum + 21 ] ;
		this.other = temp[ startnum + 22 ] ;
		this.equipment_others = temp[ startnum + 23 ] ;
		this.quality_of_spring = temp[ startnum + 24 ] ;
		this.kasikiri_bath = temp[ startnum + 25 ] ;
		this.bathroom_woman = temp[ startnum + 26 ] ;
		this.bathroom_man = temp[ startnum + 27 ] ;
		this.age_reconstruction = temp[ startnum + 28 ] ;
		this.parking_of_one_night = temp[ startnum + 29 ] ;
		this.type_of_parking = temp[ startnum + 30 ] ;
		this.limit_of_parking = temp[ startnum + 31 ] ;
		this.amount_of_parking = temp[ startnum + 32 ] ;
		this.condition_of_parking = temp[ startnum + 33 ] ;
		this.time_of_parking = temp[ startnum + 34 ] ;
		this.reservation_of_parking = temp[ startnum + 35 ] ;
		this.natural_spring = temp[ startnum + 36 ] ;
		this.braille_guidance = temp[ startnum + 37 ] ;
		this.open_air_spring_woman = temp[ startnum + 38 ] ;
		this.open_air_spring_man = temp[ startnum + 39 ] ;
		this.GD_amenity = temp[ startnum + 40 ] ;
		this.SH_tiolet = temp[ startnum + 41 ] ;
		this.SH_booth = temp[ startnum + 42 ] ;
		this.presser = temp[ startnum + 43 ] ;
		this.drier = temp[ startnum + 44 ] ;
		this.BT_rowel = temp[ startnum + 45 ] ;
		this.BT_robe = temp[ startnum + 46 ] ;
		this.modulerjack = temp[ startnum + 47 ] ;
		this.satelite = temp[ startnum + 48 ] ;
		this.pot = temp[ startnum + 49 ] ;
		this.video = temp[ startnum + 50 ] ;
		this.refrigerator_val  = temp[ startnum + 51 ] ;
		this.RF_inside = temp[ startnum + 52 ] ;
    }    
    /**
	 * コンストラクター
	 */
	public Mhfcwp(){
		error_flg = new int[ 56 ];//48//
	}
	/**
	 * 追加用
	 */
	public Mhfcwp getNewReq( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		return this;
	}
	/**
	 * 更新用
	 */
	public Mhfcwp getRequest( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.karaoke = checkStr( req, "karaoke", 2, 1, false );
		this.gateball = checkStr( req, "gateball", 3, 1, false );
		this.landly = checkStr( req, "gamecorner", 4, 1, false );
		this.gamecorner = checkStr( req, "", 5, 1, false );
		this.convshop = checkStr( req, "convshop", 6, 1, false );
		this.sauna_woman = checkStr( req, "sauna_woman", 7, 1, false );
		this.sauna_man = checkStr( req, "sauna_man", 8, 1, false );
		this.dance = checkStr( req, "dance", 9, 1, false );
		this.tennis = checkStr( req, "tennis", 10, 1, false );
		this.fittness = checkStr( req, "fittness", 11, 1, false );
		this.outdoor_pool_val = checkStr( req, "outdoor_pool_val", 12, 1, false );
		this.indooe_pool_val = checkStr( req, "indooe_pool_val", 13, 1, false );
		this.OD_child = checkStr( req, "OD_child", 14, 1, false );
		this.ID_child = checkStr( req, "ID_child", 15, 1, false );
		this.ladysroom = checkStr( req, "ladysroom", 16, 1, false );
		this.canference = checkStr( req, "canference", 17, 1, false );
		this.nonsmoking = checkStr( req, "nonsmoking", 18, 1, false );
		this.usebld = checkStr( req, "usebld", 19, 1, false );
		this.entrance = checkStr( req, "entrance", 20, 1, false );
		this.effect = checkStr( req, "effect", 21, 1, false );
		this.handicapped_toilet = checkStr( req, "handicapped_toilet", 22, 1, false );
		this.ice_machin = checkStr( req, "ice_machin", 23, 1, false );
		this.other = checkStr( req, "equipment_others", 24, 1, false );
		this.equipment_others = checkStr( req, "", 25, 1, false );
		this.quality_of_spring = checkStr( req, "quality_of_spring", 26, 1, false );
		this.kasikiri_bath = checkStr( req, "kasikiri_bath", 27, 1, false );
		this.bathroom_woman = checkStr( req, "bathroom_woman", 28, 1, false );
		this.bathroom_man = checkStr( req, "bathroom_man", 29, 1, false );
		this.age_reconstruction = checkStr( req, "age_reconstruction", 30, 1, false );
		this.parking_of_one_night = checkStr( req, "parking_of_one_night", 31, 1, false );
		this.type_of_parking = checkStr( req, "type_of_parking", 32, 1, false );
		this.limit_of_parking = checkStr( req, "limit_of_parking", 33, 1, false );
		this.amount_of_parking = checkStr( req, "amount_of_parking", 34, 1, false );
		this.condition_of_parking = checkStr( req, "time_of_parking", 35, 1, false );
		this.time_of_parking = checkStr( req, "", 36, 1, false );
		this.reservation_of_parking = checkStr( req, "reservation_of_parking", 37, 1, false );
		this.natural_spring = checkStr( req, "natural_spring", 38, 1, false );
		this.braille_guidance = checkStr( req, "braille_guidance", 39, 1, false );
		this.open_air_spring_woman = checkStr( req, "open_air_spring_woman", 40, 1, false );
		this.open_air_spring_man = checkStr( req, "open_air_spring_man", 41, 1, false );
		this.GD_amenity = checkStr( req, "GD_amenity", 42, 1, false );
		this.SH_tiolet = checkStr( req, "SH_tiolet", 43, 1, false );
		this.SH_booth = checkStr( req, "SH_booth", 44, 1, false );
		this.presser = checkStr( req, "presser", 45, 1, false );
		this.drier = checkStr( req, "drier", 46, 1, false );
		this.BT_rowel = checkStr( req, "BT_rowel", 47, 1, false );
		this.BT_robe = checkStr( req, "BT_robe", 48, 1, false );
		this.modulerjack = checkStr( req, "modulerjack", 49, 1, false );
		this.satelite = checkStr( req, "satelite", 50, 1, false );
		this.pot = checkStr( req, "pot", 51, 1, false );
		this.video = checkStr( req, "video", 52, 1, false );
		this.refrigerator_val  = checkStr( req, "refrigerator_val", 53, 1, false );
		this.RF_inside = checkStr( req, "RF_inside", 54, 1, false );
		
		this.buildxml = checkStr( req, "buildxml", 55, 1, false );
		return this;
	}
}
