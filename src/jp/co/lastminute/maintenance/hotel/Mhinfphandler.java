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
public class Mhinfphandler extends Commander implements CommandIF{
	/**
	 * 詳細の更新
	 */
	public boolean updateMhinfp( Mhinfp mhinfp ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateMhinfpStr( mhinfp );
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
	public Mhinfp getDetail( HotelhandlerCondition condition ){
		try{
			Mhinfp mhinfp = new Mhinfp();
			JdbcAdapter db = new JdbcAdapter();
			String sql = getDetailStr( condition ) ;
			if( sql.length() > 0 ){
				if( db.init( this.dataSource ) ){
					System.err.println( sql );
					Vector rows = db.dbSelect( sql );
					if( rows.size() > 0 ){
						//データ格納 
						dbDataAccesser acceser = new dbDataAccesser( rows );
						mhinfp.supnbr = acceser.getData( 0, 0 );
						mhinfp.agt_cd = acceser.getData( 0, 1 );
						mhinfp.rspnum = acceser.getData( 0, 2 );
						mhinfp.rsfnum = acceser.getData( 0, 3 );
						mhinfp.hemail = acceser.getData( 0, 4 );
						mhinfp.webadd = acceser.getData( 0, 5 );
						mhinfp.ofccat = acceser.getData( 0, 6 );
						mhinfp.flrnum = acceser.getData( 0, 7 );
						mhinfp.elevtr = acceser.getData( 0, 8 );
						mhinfp.elecap = acceser.getData( 0, 9 );
						mhinfp.lstrfb = acceser.getData( 0, 10 );
						mhinfp.rnvstr = acceser.getData( 0, 11 );
						mhinfp.rnvend = acceser.getData( 0, 12 );
						mhinfp.rnvdtl = acceser.getData( 0, 13 );
						mhinfp.traficxml = acceser.getData( 0, 14 );
						mhinfp.grpchk = acceser.getData( 0, 15 );
						mhinfp.laneng = acceser.getData( 0, 16 );
						mhinfp.lanjap = acceser.getData( 0, 17 );
						mhinfp.langxml = acceser.getData( 0, 18 );
						mhinfp.phnr2r = acceser.getData( 0, 19 );
						mhinfp.phonexml = acceser.getData( 0, 20 );
						mhinfp.totrst = acceser.getData( 0, 21 );
						mhinfp.totbar = acceser.getData( 0, 22 );
						mhinfp.totcon = acceser.getData( 0, 23 );
						mhinfp.imgfrn = acceser.getData( 0, 24 );
						mhinfp.imglob = acceser.getData( 0, 25 );
						mhinfp.imgrom = acceser.getData( 0, 26 );
						mhinfp.checkinxml = acceser.getData( 0, 27 );
						mhinfp.servicexml = acceser.getData( 0, 28 );						
						return mhinfp;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * 
	 */
	public String updateMhinfpStr( Mhinfp mhinfp ) throws Exception{
		return "UPDATE MHINFP SET "
				+"RSPNUM='" + mhinfp.rspnum + "',"
				+"RSFNUM='" + mhinfp.rsfnum + "',"
				+"HEMAIL='" + mhinfp.hemail + "',"
				+"WEBADD='" + mhinfp.webadd + "',"
				+"OFCCAT='" + mhinfp.ofccat + "',"
				+"FLRNUM='" + mhinfp.flrnum + "',"
				+"ELEVTR='" + mhinfp.elevtr + "',"
				+"ELECAP='" + mhinfp.elecap + "',"
				+"LSTRFB='" + mhinfp.lstrfb + "',"
				+"RNVSTR='" + mhinfp.rnvstr + "',"
				+"RNVEND='" + mhinfp.rnvend + "',"
				+"RNVDTL='" + mhinfp.rnvdtl + "',"
				+"TRAFICXML='" + mhinfp.traficxml + "',"
				+"GRPCHK='" + mhinfp.grpchk + "',"
				+"LANENG='" + mhinfp.laneng + "',"
				+"LANJAP='" + mhinfp.lanjap + "',"
				+"LANGXML='" + mhinfp.langxml + "',"
				+"PHNR2R='" + mhinfp.phnr2r + "',"
				+"PHONEXML='" + mhinfp.phonexml + "',"
				+"TOTRST='" + mhinfp.totrst + "',"
				+"TOTBAR='" + mhinfp.totbar + "',"
				+"TOTCON='" + mhinfp.totcon + "',"
				+"IMGFRN='" + mhinfp.imgfrn + "',"
				+"IMGLOB='" + mhinfp.imglob + "',"
				+"IMGROM='" + mhinfp.imgrom + "',"
				+"CHECKINXML='" + mhinfp.checkinxml + "',"
				+"SERVICEXML='" + mhinfp.servicexml + "' "
				+"WHERE SUPNBR='" + mhinfp.supnbr +"' AND AGT_CD='" + mhinfp.agt_cd + "'";	
	}
	/**
	 * 詳細情報の取得
	 */
	public String getDetailStr( HotelhandlerCondition condition ) throws Exception{
		return "SELECT "
				+ "SUPNBR,"
				+ "AGT_CD,"
				+ "RSPNUM,"
				+ "RSFNUM,"
				+ "HEMAIL,"
				+ "WEBADD,"
				+ "OFCCAT,"
				+ "FLRNUM,"
				+ "ELEVTR,"
				+ "ELECAP,"
				+ "LSTRFB,"
				+ "RNVSTR,"
				+ "RNVEND,"
				+ "RNVDTL,"
				+ "TRAFICXML,"
				+ "GRPCHK,"
				+ "LANENG,"
				+ "LANJAP,"
				+ "LANGXML,"
				+ "PHNR2R,"
				+ "PHONEXML,"
				+ "TOTRST,"
				+ "TOTBAR,"
				+ "TOTCON,"
				+ "IMGFRN,"
				+ "IMGLOB,"
				+ "IMGROM,"
				+ "CHECKINXML,"
				+ "SERVICEXML "
				+ "FROM MHINFP WHERE "
				+ " AGT_CD='"+ condition.agt_cd + "' AND "
				+ " SUPNBR='" + condition.supnbr + "'";	
	}
}
