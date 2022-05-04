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
public class Mhtlcp extends Models implements Serializable {
	private static final int startnum = 59;
    public String supnbr = "";
    public String agt_cd = "";
    //////////
    public String htltyp = "";
	public String htlcat = "";
	public String hrlloc = "";
	public String aptcde = "";
	public String aptdst = "";
	public String htlrst = "";
	public String htlbth = "";
	public String htlair = "";
	public String htlcfr = "";
	public String cfrmax = "";
	public String htlbqt = "";
	public String bqtmax = "";
	public String htlpkg = "";
	public String arectr = "";
	public String arecty = "";
	public String aredis = "";
	public String htlest = "";
	public String htlrnv = "";
	public String htlrms = "";
	public String sutrms = "";
	public String twnrms = "";
	public String dblrms = "";
	public String sglrms = "";
	public String card1 = "";
	public String card2 = "";
	public String card3 = "";
	public String card4 = "";
	public String card5 = "";
	public String card6 = "";
	public String cardnm = "";
	public String nerstn = "";
	public String acspnt = "";
	public String farcty = "";
	
	public String bath1 = "0";
	public String mail1 = "0";
	public String mail2 = "0";
	public String mail3 = "0";
    
    public void parseString( String str ){
    	String[] temp = parseStr( CharacterConverter.toJISAutoDetect( str ) );
    	//temp[0] = 
    	this.htltyp = temp[ startnum + 0 ];
		this.htlcat = temp[ startnum + 1 ];
		this.hrlloc = temp[ startnum + 2 ];
		this.aptcde = temp[ startnum + 3 ];
		this.aptdst = temp[ startnum + 4 ];
		this.htlrst = temp[ startnum + 5 ];
		this.htlbth = temp[ startnum + 6 ];
		this.htlair = temp[ startnum + 7 ];
		this.htlcfr = temp[ startnum + 8 ];
		this.cfrmax = temp[ startnum + 9 ];
		this.htlbqt = temp[ startnum + 10 ];
		this.bqtmax = temp[ startnum + 11 ];
		this.htlpkg = temp[ startnum + 12 ];
		this.arectr = temp[ startnum + 13 ];
		this.arecty = temp[ startnum + 14 ];
		this.aredis = temp[ startnum + 15 ];
		this.htlest = temp[ startnum + 16 ];
		this.htlrnv = temp[ startnum + 17 ];
		this.htlrms = temp[ startnum + 18 ];
		this.sutrms = temp[ startnum + 19 ];
		this.twnrms = temp[ startnum + 20 ];
		this.dblrms = temp[ startnum + 21 ];
		this.sglrms = temp[ startnum + 22 ];
		this.card1 = temp[ startnum + 23 ];
		this.card2 = temp[ startnum + 24 ];
		this.card3 = temp[ startnum + 25 ];
		this.card4 = temp[ startnum + 26 ];
		this.card5 = temp[ startnum + 27 ];
		this.card6 = temp[ startnum + 28 ];
		this.cardnm = temp[ startnum + 29 ];
		this.nerstn = temp[ startnum + 30 ];
		this.acspnt = temp[ startnum + 31 ];
		this.farcty = temp[ startnum + 32 ];
    }    
    /**
	 * コンストラクター
	 */
	public Mhtlcp(){
		error_flg = new int[ 35 ];
	}
	/**
	 * 追加用
	 */
	public Mhtlcp getNewReq( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		return this;
	}
	/**
	 * 更新用
	 */
	public Mhtlcp getRequest( HttpServletRequest req ){
		clear();
		this.supnbr = checkStr( req, "supnbr", 0, 7, true );
		this.agt_cd = checkStr( req, "agt_cd", 1, 7, true );
		this.htltyp = checkStr( req, "htltyp", 2, 1, false);
		this.htlcat = checkStr( req, "htlcat", 3, 1, false);
		this.hrlloc = checkStr( req, "hrlloc", 4, 1, false);
		this.aptcde = checkStr( req, "aptcde", 5, 1, false);
		this.aptdst = checkStr( req, "aptdst", 6, 1, false);
		this.htlrst = checkStr( req, "htlrst", 7, 1, false);
		this.htlbth = checkStr( req, "htlbth", 8, 1, false);
		this.htlair = checkStr( req, "htlair", 9, 1, false);
		this.htlcfr = checkStr( req, "htlcfr", 10, 1, false);
		this.cfrmax = checkStr( req, "cfrmax", 11, 1, false);
		this.htlbqt = checkStr( req, "htlbqt", 12, 1, false);
		this.bqtmax = checkStr( req, "bqtmax", 13, 1, false);
		this.htlpkg = checkStr( req, "htlpkg", 14, 1, false);
		this.arectr = checkStr( req, "arectr", 15, 1, false);
		this.arecty = checkStr( req, "arecty", 16, 1, false);
		this.aredis = checkStr( req, "aredis", 17, 1, false);
		this.htlest = checkStr( req, "htlest", 18, 1, false);
		this.htlrnv = checkStr( req, "htlrnv", 19, 1, false);
		this.htlrms = checkStr( req, "htlrms", 20, 1, false);
		this.sutrms = checkStr( req, "sutrms", 21, 1, false);
		this.twnrms = checkStr( req, "twnrms", 22, 1, false);
		this.dblrms = checkStr( req, "dblrms", 23, 1, false);
		this.sglrms = checkStr( req, "sglrms", 24, 1, false);
		this.card1 = checkStr( req, "card1", 25, 1, false);
		this.card2 = checkStr( req, "card2", 26, 1, false);
		this.card3 = checkStr( req, "card3", 27, 1, false);
		this.card4 = checkStr( req, "card4", 28, 1, false);
		this.card5 = checkStr( req, "card5", 29, 1, false);
		this.card6 = checkStr( req, "card6", 30, 1, false);
		this.cardnm = checkStr( req, "cardnm", 31, 1, false);
		this.nerstn = checkStr( req, "nerstn", 32, 1, false);
		this.acspnt = checkStr( req, "acspnt", 33, 1, false);
		this.farcty = checkStr( req, "farcty", 34, 1, false);
		return this;
	}
}