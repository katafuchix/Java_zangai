package jp.co.lastminute.maintenance.hotel;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.hotel.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Mhtlcphandler extends Commander implements CommandIF{
	/**
	 * 詳細の更新
	 */
	public boolean updateMhtlcp( Mhtlcp mhtlcp ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateMhtlcpStr( mhtlcp );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}

	/**
	 * 詳細の取得
	 */
	public Mhtlcp getDetail( HotelhandlerCondition condition ){
		try{
			Mhtlcp mhtlcp = new Mhtlcp();
			JdbcAdapter db = new JdbcAdapter();
			String sql = getDetailStr( condition ) ;
			if( sql.length() > 0 ){
				if( db.init( this.dataSource ) ){
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					if( rows.size() > 0 ){
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser( rows );
						mhtlcp.supnbr = acceser.getData( 0, 0 );
						mhtlcp.agt_cd = acceser.getData( 0, 1 );
						mhtlcp.htltyp = acceser.getData( 0, 2 );
						mhtlcp.htlcat = acceser.getData( 0, 3 );
						mhtlcp.hrlloc = acceser.getData( 0, 4 );
						mhtlcp.aptcde = acceser.getData( 0, 5 );
						mhtlcp.aptdst = acceser.getData( 0, 6 );
						mhtlcp.htlrst = acceser.getData( 0, 7 );
						mhtlcp.htlbth = acceser.getData( 0, 8 );
						mhtlcp.htlair = acceser.getData( 0, 9 );
						mhtlcp.htlcfr = acceser.getData( 0, 10 );
						mhtlcp.cfrmax = acceser.getData( 0, 11 );
						mhtlcp.htlbqt = acceser.getData( 0, 12 );
						mhtlcp.bqtmax = acceser.getData( 0, 13 );
						mhtlcp.htlpkg = acceser.getData( 0, 14 );
						mhtlcp.arectr = acceser.getData( 0, 15 );
						mhtlcp.arecty = acceser.getData( 0, 16 );
						mhtlcp.aredis = acceser.getData( 0, 17 );
						mhtlcp.htlest = acceser.getData( 0, 18 );
						mhtlcp.htlrnv = acceser.getData( 0, 19 );
						mhtlcp.htlrms = acceser.getData( 0, 20 );
						mhtlcp.sutrms = acceser.getData( 0, 21 );
						mhtlcp.twnrms = acceser.getData( 0, 22 );
						mhtlcp.dblrms = acceser.getData( 0, 23 );
						mhtlcp.sglrms = acceser.getData( 0, 24 );
						mhtlcp.card1 = acceser.getData( 0, 25 );
						mhtlcp.card2 = acceser.getData( 0, 26 );
						mhtlcp.card3 = acceser.getData( 0, 27 );
						mhtlcp.card4 = acceser.getData( 0, 28 );
						mhtlcp.card5 = acceser.getData( 0, 29 );
						mhtlcp.card6 = acceser.getData( 0, 30 );
						mhtlcp.cardnm = acceser.getData( 0, 31 );
						mhtlcp.nerstn = acceser.getData( 0, 32 );
						mhtlcp.acspnt = acceser.getData( 0, 33 );
						mhtlcp.farcty = acceser.getData( 0, 34 );				
						return mhtlcp;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * 
	 */
	public String updateMhtlcpStr( Mhtlcp mhtlcp ) throws Exception{
		return "UPDATE MHTLCP SET "
				+"HTLTYP='" + mhtlcp.htltyp + "', "
				+"HTLCAT='" + mhtlcp.htlcat + "', "
				+"HRLLOC='" + mhtlcp.hrlloc + "', "
				+"APTCDE='" + mhtlcp.aptcde + "', "
				+"APTDST='" + mhtlcp.aptdst + "', "
				+"HTLRST='" + mhtlcp.htlrst + "', "
				+"HTLBTH='" + mhtlcp.htlbth + "', "
				+"HTLAIR='" + mhtlcp.htlair + "', "
				+"HTLCFR='" + mhtlcp.htlcfr + "', "
				+"CFRMAX='" + mhtlcp.cfrmax + "', "
				+"HTLBQT='" + mhtlcp.htlbqt + "', "
				+"BQTMAX='" + mhtlcp.bqtmax + "', "
				+"HTLPKG='" + mhtlcp.htlpkg + "', "
				+"ARECTR='" + mhtlcp.arectr + "', "
				+"ARECTY='" + mhtlcp.arecty + "', "
				+"AREDIS='" + mhtlcp.aredis + "', "
				+"HTLEST='" + mhtlcp.htlest + "', "
				+"HTLRNV='" + mhtlcp.htlrnv + "', "
				+"HTLRMS='" + mhtlcp.htlrms + "', "
				+"SUTRMS='" + mhtlcp.sutrms + "', "
				+"TWNRMS='" + mhtlcp.twnrms + "', "
				+"DBLRMS='" + mhtlcp.dblrms + "', "
				+"SGLRMS='" + mhtlcp.sglrms + "', "
				+"CARD1='" + mhtlcp.card1 + "', "
				+"CARD2='" + mhtlcp.card2 + "', "
				+"CARD3='" + mhtlcp.card3 + "', "
				+"CARD4='" + mhtlcp.card4 + "', "
				+"CARD5='" + mhtlcp.card5 + "', "
				+"CARD6='" + mhtlcp.card6 + "', "
				+"CARDNM='" + mhtlcp.cardnm + "', "
				+"NERSTN='" + mhtlcp.nerstn + "', "
				+"ACSPNT='" + mhtlcp.acspnt + "', "
				+"FARCTY='" + mhtlcp.farcty + "' "
				+"WHERE SUPNBR='" + mhtlcp.supnbr +"' AND AGT_CD='" + mhtlcp.agt_cd + "'";	
	}
	/**
	 * 詳細情報の取得
	 */
	public String getDetailStr( HotelhandlerCondition condition ) throws Exception{
		return "SELECT "
				+"SUPNBR,"
				+"AGT_CD,"
				+"HTLTYP,"
				+"HTLCAT,"
				+"HRLLOC,"
				+"APTCDE,"
				+"APTDST,"
				+"HTLRST,"
				+"HTLBTH,"
				+"HTLAIR,"
				+"HTLCFR,"
				+"CFRMAX,"
				+"HTLBQT,"
				+"BQTMAX,"
				+"HTLPKG,"
				+"ARECTR,"
				+"ARECTY,"
				+"AREDIS,"
				+"HTLEST,"
				+"HTLRNV,"
				+"HTLRMS,"
				+"SUTRMS,"
				+"TWNRMS,"
				+"DBLRMS,"
				+"SGLRMS,"
				+"CARD1,"
				+"CARD2,"
				+"CARD3,"
				+"CARD4,"
				+"CARD5,"
				+"CARD6,"
				+"CARDNM,"
				+"NERSTN,"
				+"ACSPNT,"
				+"FARCTY "
				+ "FROM MHTLCP WHERE "
				+ " AGT_CD='"+ condition.agt_cd + "' AND "
				+ " SUPNBR='" + condition.supnbr + "'";	
	}
}
