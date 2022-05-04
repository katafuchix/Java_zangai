package jp.co.lastminute.maintenance.detail;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.maintenance.util.CharacterConverter;
import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.jdbc.*;
import jp.co.lastminute.maintenance.model.*;

import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.jdbc.Sqlmaker;
import jp.co.yobrain.util.DataFormat;

import jp.co.yobrain.util.rpc.PostString;
import jp.co.yobrain.util.rpc.SendClient;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Hotel extends Detail implements PageDetail, Serializable{

	protected static final String detailstr = ContextProperty._hotel_product_Dir + "/";
	
	
	public Hotel(){
	}
	public Hotel( DataSource ds) {
		this.dataSource = ds;
	}
	
	/**
	 * XMLÇÃê∂ê¨
	 */
	public String makeXml( String contextValue, String contextname ) throws Exception {
		String url = "http://" + target + "/lmj/maintenance/util/XmlDatabaseServlet";
		Vector vc = new Vector();
		PostString ps01 = new PostString( "contextValue", contextValue );
		PostString ps02 = new PostString( "contextname", contextname );
		PostString ps03 = new PostString( "contextpath", detailstr );
		vc.add( ps01 );
		vc.add( ps02 );
		vc.add( ps03 );
		SendClient sendclient = new SendClient();
		String result = sendclient.sendText( url, vc );
		//System.err.println( result );
		return result;
	}
	public boolean write( String contextValue, String contextname ){
		try{
			String result = makeXml( contextValue, contextname );
			System.err.println( result );
			if( result.indexOf("-1") == -1 ){
				return true;	
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return false;
	}
	/**
	 * XMLÇÃê∂ê¨
	 */
	public String generateXml( dbDataAccesser access ) throws Exception {
		int i=0;
		return "<ROWSET>\n"
                +"<ROW>\n"
                +"<SUPNBR>" + access.getData(i, 0) + "</SUPNBR>\n"
                +"<CTRNBR>" + access.getData(i, 1) + "</CTRNBR>\n"
                +"<CTRNBR_1>" + access.getData(i, 2) + "</CTRNBR_1>\n"
                +"<AGT_CD>" + access.getData(i, 3) + "</AGT_CD>\n"
                +"<SUPNM1><![CDATA[" + access.getData(i, 4) + "]]></SUPNM1>\n"
                +"<JPNNAM><![CDATA[" + access.getData(i, 5) + "]]></JPNNAM>\n"
                +"<SUPAD1>" + access.getData(i, 6) + "</SUPAD1>\n"
                +"<SUPAD2>" + access.getData(i, 7) + "</SUPAD2>\n"
                +"<SUPAD3>" + access.getData(i, 8) + "</SUPAD3>\n"
                +"<PROMISE>" + access.getData(i, 9) + "</PROMISE>\n"
                +"<PROMISEURI>" + access.getData(i, 10) + "</PROMISEURI>\n"
                +"<SUPTEL>" + access.getData(i, 11) + "</SUPTEL>\n"
                +"<SUPFAX>" + access.getData(i, 12) + "</SUPFAX>\n"
                +"<JPNTXT>" + CharacterConverter.xmlEncode( access.getData(i, 13) ) + "</JPNTXT>\n"
                +"<HTLTYP>" + access.getData(i, 14) + "</HTLTYP>\n"
                +"<HTLCAT>" + access.getData(i, 15) + "</HTLCAT>\n"
                +"<HRLLOC>" + access.getData(i, 16) + "</HRLLOC>\n"
                +"<APTCDE>" + access.getData(i, 17) + "</APTCDE>\n"
                +"<APTDST>" + access.getData(i, 18) + "</APTDST>\n"
                +"<HTLRST>" + access.getData(i, 19) + "</HTLRST>\n"
                +"<HTLBTH>" + access.getData(i, 20) + "</HTLBTH>\n"
                +"<HTLAIR>" + access.getData(i, 21) + "</HTLAIR>\n"
                +"<HTLCFR>" + access.getData(i, 22) + "</HTLCFR>\n"
                +"<CFRMAX>" + access.getData(i, 23) + "</CFRMAX>\n"
                +"<HTLBQT>" + access.getData(i, 24) + "</HTLBQT>\n"
                +"<BQTMAX>" + access.getData(i, 25) + "</BQTMAX>\n"
                +"<HTLPKG>" + access.getData(i, 26) + "</HTLPKG>\n"
                +"<ARECTR>" + access.getData(i, 27) + "</ARECTR>\n"
                +"<ARECTY>" + access.getData(i, 28) + "</ARECTY>\n"
                +"<AREDIS>" + access.getData(i, 29) + "</AREDIS>\n"
                +"<HTLEST>" + access.getData(i, 30) + "</HTLEST>\n"
                +"<HTLRNV>" + access.getData(i, 31) + "</HTLRNV>\n"
                +"<HTLRMS>" + access.getData(i, 32) + "</HTLRMS>\n"
                +"<SUTRMS>" + access.getData(i, 33) + "</SUTRMS>\n"
                +"<TWNRMS>" + access.getData(i, 34) + "</TWNRMS>\n"
                +"<DBLRMS>" + access.getData(i, 35) + "</DBLRMS>\n"
                +"<SGLRMS>" + access.getData(i, 36) + "</SGLRMS>\n"
                +"<CARD1>" + access.getData(i, 37) + "</CARD1>\n"
                +"<CARD2>" + access.getData(i, 38) + "</CARD2>\n"
                +"<CARD3>" + access.getData(i, 39) + "</CARD3>\n"
                +"<CARD4>" + access.getData(i, 40) + "</CARD4>\n"
                +"<CARD5>" + access.getData(i, 41) + "</CARD5>\n"
                +"<CARD6>" + access.getData(i, 42) + "</CARD6>\n"
                +"<CARDNM>" + access.getData(i, 43) + "</CARDNM>\n"
                +"<NERSTN>" + access.getData(i, 44) + "</NERSTN>\n"
                +"<ACSPNT>" + access.getData(i, 45) + "</ACSPNT>\n"
                +"<FARCTY>" + access.getData(i, 46) + "</FARCTY>\n"
                +"<RSPNUM>" + access.getData(i, 47) + "</RSPNUM>\n"
                +"<RSFNUM>" + access.getData(i, 48) + "</RSFNUM>\n"
                +"<HEMAIL>" + access.getData(i, 49) + "</HEMAIL>\n"
                +"<WEBADD>" + access.getData(i, 50) + "</WEBADD>\n"
                +"<OFCCAT>" + access.getData(i, 51) + "</OFCCAT>\n"
                +"<FLRNUM>" + access.getData(i, 52) + "</FLRNUM>\n"
                +"<ELEVTR>" + access.getData(i, 53) + "</ELEVTR>\n"
                +"<ELECAP>" + access.getData(i, 54) + "</ELECAP>\n"
                +"<LSTRFB>" + access.getData(i, 55) + "</LSTRFB>\n"
                +"<RNVSTR>" + access.getData(i, 56) + "</RNVSTR>\n"
                +"<RNVEND>" + access.getData(i, 57) + "</RNVEND>\n"
                +"<RNVDTL>" + access.getData(i, 58) + "</RNVDTL>\n"
                +"<TRAFICXML>" + CharacterConverter.xmlEncode( access.getData(i, 59) ) + "</TRAFICXML>\n"
                +"<GRPCHK>" + access.getData(i, 60) + "</GRPCHK>\n"
                +"<CHECKINXML>" + CharacterConverter.xmlEncode( access.getData(i, 61) ) + "</CHECKINXML>\n"
                +"<LANENG>" + access.getData(i, 62) + "</LANENG>\n"
                +"<LANJAP>" + access.getData(i, 63) + "</LANJAP>\n"
                +"<LANGXML>" + access.getData(i, 64) + "</LANGXML>\n"
                +"<PHNR2R>" + access.getData(i, 65) + "</PHNR2R>\n"
                +"<PHONEXML>" + access.getData(i, 66) + "</PHONEXML>\n"
                +"<SERVICEXML>" + CharacterConverter.xmlEncode( access.getData(i, 67)) + "</SERVICEXML>\n"
                +"<TOTRST>" + access.getData(i, 68) + "</TOTRST>\n"
                +"<TOTBAR>" + access.getData(i, 69) + "</TOTBAR>\n"
                +"<TOTCON>" + access.getData(i, 70) + "</TOTCON>\n"
                +"<IMGFRN>" + access.getData(i, 71) + "</IMGFRN>\n"
                +"<IMGLOB>" + access.getData(i, 72) + "</IMGLOB>\n"
                +"<IMGROM>" + access.getData(i, 73) + "</IMGROM>\n"
                +"<BUILDXML>" + CharacterConverter.xmlEncode( access.getData(i, 74) ) + "</BUILDXML>\n"
                +"<MNTRMS>" + access.getData(i, 75) + "</MNTRMS>\n"
                +"<TOTANR>" + access.getData(i, 76) + "</TOTANR>\n"
                +"<SGLTOT>" + access.getData(i, 77) + "</SGLTOT>\n"
                +"<SINGLEXML>" + CharacterConverter.xmlEncode( access.getData(i, 78) ) + "</SINGLEXML>\n"
                +"<TWNTOT>" + access.getData(i, 79) + "</TWNTOT>\n"
                +"<TWINXML>" + CharacterConverter.xmlEncode( access.getData(i, 80) ) + "</TWINXML>\n"
                +"<DBFTOT>" + access.getData(i, 81) + "</DBFTOT>\n"
                +"<DOUBLEXML>" + CharacterConverter.xmlEncode( access.getData(i, 82) ) + "</DOUBLEXML>\n"
                +"<DBGTOT>" + access.getData(i, 83) + "</DBGTOT>\n"
                +"<DOUBLEGXML>" + CharacterConverter.xmlEncode( access.getData(i, 84) ) + "</DOUBLEGXML>\n"
                +"<TPLTOT>" + access.getData(i, 85) + "</TPLTOT>\n"
                +"<TRIPLEXML>" + access.getData(i, 86) + "</TRIPLEXML>\n"
                +"<QADTOT>" + access.getData(i, 87) + "</QADTOT>\n"
                +"<QADTOTXML>" +CharacterConverter.xmlEncode( access.getData(i, 88) ) + "</QADTOTXML>\n"
                +"<FAMTOT>" + access.getData(i, 89) + "</FAMTOT>\n"
                +"<FAMILYXML>" + CharacterConverter.xmlEncode( access.getData(i, 90) ) + "</FAMILYXML>\n"
                +"<EXETOT>" + access.getData(i, 91) + "</EXETOT>\n"
                +"<SUPTOT>" + access.getData(i, 92) + "</SUPTOT>\n"
                +"<DLXTOT>" + access.getData(i, 93) + "</DLXTOT>\n"
                +"<OTHTOT>" + access.getData(i, 94) + "</OTHTOT>\n"
                +"<OTHRNM>" + access.getData(i, 95) + "</OTHRNM>\n"
                +"<JNRSUT>" + access.getData(i, 96) + "</JNRSUT>\n"
                +"<STDSUT>" + access.getData(i, 97) + "</STDSUT>\n"
                +"<DLXSUT>" + access.getData(i, 98) + "</DLXSUT>\n"
                +"<PRSSUT>" + access.getData(i, 99) + "</PRSSUT>\n"
                +"<OTHSUT>" + access.getData(i, 100) + "</OTHSUT>\n"
                +"<OTHSNM>" + access.getData(i, 101) + "</OTHSNM>\n"
                +"<NONRMS>" + access.getData(i, 102) + "</NONRMS>\n"
                +"<NONFLR>" + access.getData(i, 103) + "</NONFLR>\n"
                +"<WHLACS>" + access.getData(i, 104) + "</WHLACS>\n"
                +"<DSLRMS>" + access.getData(i, 105) + "</DSLRMS>\n"
                +"<DSLSIZ>" + access.getData(i, 106) + "</DSLSIZ>\n"
                +"<DSLBAT>" + access.getData(i, 107) + "</DSLBAT>\n"
                +"<DSLTWN>" + access.getData(i, 108) + "</DSLTWN>\n"
                +"<DSLDBL>" + access.getData(i, 109) + "</DSLDBL>\n"
                +"<DSLSGL>" + access.getData(i, 110) + "</DSLSGL>\n"
                +"<DSLTPL>" + access.getData(i, 111) + "</DSLTPL>\n"
                +"<DSLTSU>" + access.getData(i, 112) + "</DSLTSU>\n"
                +"<DSLRDW>" + access.getData(i, 113) + "</DSLRDW>\n"
                +"<DSLBD>" + access.getData(i, 114) + "</DSLBD>\n"
				+"</ROW>\n"
                +"</ROWSET>";
	}
	
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		return "SELECT DISTINCT "
                +"A.SUPNBR,"
                +"A.CTRNBR,"
                +"A.CTRNBR_1,"
                +"A.AGT_CD,"
                +"A.SUPNM1,"
                +"A.JPNNAM,"
                +"A.SUPAD1,"
                +"A.SUPAD2,"
                +"A.SUPAD3,"
                +"A.PROMISE,"
                +"A.PROMISEURI,"
                +"A.SUPTEL,"
                +"A.SUPFAX,"
                +"A.JPNTXT,"
                +"B.HTLTYP,"
                +"B.HTLCAT,"
                +"B.HRLLOC,"
                +"B.APTCDE,"
                +"B.APTDST,"
                +"B.HTLRST,"
                +"B.HTLBTH,"
                +"B.HTLAIR,"
                +"B.HTLCFR,"
                +"B.CFRMAX,"
                +"B.HTLBQT,"
                +"B.BQTMAX,"
                +"B.HTLPKG,"
                +"B.ARECTR,"
                +"B.ARECTY,"
                +"B.AREDIS,"
                +"B.HTLEST,"
                +"B.HTLRNV,"
                +"B.HTLRMS,"
                +"B.SUTRMS,"
                +"B.TWNRMS,"
                +"B.DBLRMS,"
                +"B.SGLRMS,"
                +"B.CARD1,"
                +"B.CARD2,"
                +"B.CARD3,"
                +"B.CARD4,"
                +"B.CARD5,"
                +"B.CARD6,"
                +"B.CARDNM,"
                +"B.NERSTN,"
                +"B.ACSPNT,"
                +"B.FARCTY,"
                +"C.RSPNUM,"
                +"C.RSFNUM,"
                +"C.HEMAIL,"
                +"C.WEBADD,"
                +"C.OFCCAT,"
                +"C.FLRNUM,"
                +"C.ELEVTR,"
                +"C.ELECAP,"
                +"C.LSTRFB,"
                +"C.RNVSTR,"
                +"C.RNVEND,"
                +"C.RNVDTL,"
                +"C.TRAFICXML,"
                +"C.GRPCHK,"
                +"C.CHECKINXML,"
                +"C.LANENG,"
                +"C.LANJAP,"
                +"C.LANGXML,"
                +"C.PHNR2R,"
                +"C.PHONEXML,"
                +"C.SERVICEXML,"
                +"C.TOTRST,"
                +"C.TOTBAR,"
                +"C.TOTCON,"
                +"C.IMGFRN,"
                +"C.IMGLOB,"
                +"C.IMGROM,"
                +"D.BUILDXML,"
                +"E.MNTRMS,"
                +"E.TOTANR,"
                +"E.SGLTOT,"
                +"E.SINGLEXML,"
                +"E.TWNTOT,"
                +"E.TWINXML,"
                +"E.DBFTOT,"
                +"E.DOUBLEXML,"
                +"E.DBGTOT,"
                +"E.DOUBLEGXML,"
                +"E.TPLTOT,"
                +"E.TRIPLEXML,"
                +"E.QADTOT,"
                +"E.QADTOTXML,"
                +"E.FAMTOT,"
                +"E.FAMILYXML,"
                +"E.EXETOT,"
                +"E.SUPTOT,"
                +"E.DLXTOT,"
                +"E.OTHTOT,"
                +"E.OTHRNM,"
                +"E.JNRSUT,"
                +"E.STDSUT,"
                +"E.DLXSUT,"
                +"E.PRSSUT,"
                +"E.OTHSUT,"
                +"E.OTHSNM,"
                +"E.NONRMS,"
                +"E.NONFLR,"
                +"E.WHLACS,"
                +"E.DSLRMS,"
                +"E.DSLSIZ,"
                +"E.DSLBAT,"
                +"E.DSLTWN,"
                +"E.DSLDBL,"
                +"E.DSLSGL,"
                +"E.DSLTPL,"
                +"E.DSLTSU,"
                +"E.DSLRDW, "
                +"E.DSLBDW "
                +"FROM MADRSP A, MHTLCP B, MHINFP C, MHFCWP D, MHRMSP E "
                +"WHERE "
                +"A.SUPNBR=B.SUPNBR AND "
                +"A.SUPNBR=C.SUPNBR AND "
                +"A.SUPNBR=D.SUPNBR AND "
                +"A.SUPNBR=E.SUPNBR AND "
                +"A.AGT_CD=B.AGT_CD AND "
                +"A.AGT_CD=C.AGT_CD AND "
                +"A.AGT_CD=D.AGT_CD AND "
                +"A.AGT_CD=E.AGT_CD AND "
                +"A.AGT_CD='" + condition.getAgt_cd() + "' AND "
                +"A.SUPNBR='" + condition.supnbr + "'" ;
	}
}
