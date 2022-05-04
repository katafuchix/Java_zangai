package jp.co.lastminute.Dflight;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface PropResult {
	public static final  String listsql = "SELECT "
	+ "A.WEEKDAY,"
	+ "B.TARGET_REAL_HOUR,"
	+ "A.TARGET_HOUR,"
	+ "C.DEPARTTURE,"
	+ "C.ARRIVAL,"
	+ "D.CABIN_CLASS,"
	+ "D.BOOKING_CLASS,"
	+ "C.AGT_CD,"
	+ "A.ALLOT_NUMBER,"
	+ "D.MAX_MEMBER,"
	+ "D.MIN_MEMBER,"
	+ "D.PRICE_BASE,"
	+ "D.PRICE_CHILD,"
	+ "D.PRICE_INFANT,"
	+ "D.PRICE_NORMAL,"
	+ "A.ALLOT_SEQ,"
	+ "A.SKU_ID,"
	+ "A.JAN_CD,"
	+ "A.DATE_TARGET,"
	+ "E.AIRPORTNAME,"
	+ "F.AIRPORTNAME "
+ "FROM "
	+ "ALLOTMENT_OF_FLIGHT A,"
	+ "TARGET_HOUR_MASTER B,"
	+ "PRODUCTS_OF_FLIGHT C,"
	+ "FLIGHT_SKU D,"
	+ "DOMESTICAIRPORT E,"
	+ "DOMESTICAIRPORT F "
+ "WHERE "
	+ "A.TARGET_HOUR=B.TARGET_HOUR AND "
	+ "A.JAN_CD=C.JAN_CD AND "
	+ "A.SKU_ID=D.SKU_ID AND "
	+ "C.DEPARTTURE=E.LETTERCODE AND "
	+ "C.ARRIVAL=F.LETTERCODE AND "
	+ "A.DATE_TARGET>20021227 AND "
	+ "B.TARGET_REAL_HOUR>=800 AND "
	+ "C.DEPARTTURE IN ( 'HND','KOJ' ) AND "
	+ "C.ARRIVAL IN ( 'KOJ','HND' ) AND "
	+ "D.STOP_FLG=0";

}
