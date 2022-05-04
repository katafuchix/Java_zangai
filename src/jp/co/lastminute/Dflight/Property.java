package jp.co.lastminute.Dflight;

import jp.co.yobrain.util.DataFormat;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class Property {
	public static String tokki = "(q‹ó•ÛŒ¯Š„ˆø—¿‹à\\300-‚ðŠÜ‚Þ)";
	public static String getLastsaleTime( String str, String endsaletime ){
		DataFormat df = null;
		if( str.length() > 0 ){	
			return df.DelToDate( str, 1 ) + endsaletime;
		}else{	
			str = df.DelToDate( df.getNowDate(0, true), 1) + endsaletime;	
		}
		return str;	
	}
	public static String getCansellTime( String str, String cancelltime ){
		DataFormat df = null;
		if( str.length() > 0 ){	return str + cancellsale;
		}else{	str = df.getNowDate(0, true) + cancellsale;	}
		return str;	
	}
	public static final int nextlimit = 500;
	public static final String endsale = "1900";
	public static final String cancellsale = "2400";
	public static final int cat_id = 8;
	public static final String from = "HND"; 
	public static final String to = "KOJ"; 
	public static final String to_ext = "HND";
	public static final String toprealclasspath = "";
	public static final String productname = "Domesticflight";
	public static final String property = "Domesticflight.property";
	public static final String listcash = "Domestic.List.Cash";
	public static final String xmlsession = "Domestic.List.Session";
	public static final String listsql="SELECT "
										+ "A.WEEKDAY," 			//00
										+ "B.TARGET_REAL_HOUR," //01
										+ "A.TARGET_HOUR,"      //02
										+ "C.DEPARTTURE," 		//03
										+ "C.ARRIVAL," 			//04	
										+ "D.CABIN_CLASS," 		//05	
										+ "D.BOOKING_CLASS,"	//06	
										+ "C.AGT_CD,"			//07			
										+ "A.ALLOT_NUMBER,"		//08
										+ "D.MAX_MEMBER,"		//09	
										+ "D.MIN_MEMBER," 		//10	
										+ "D.PRICE_BASE,"		//11		
										+ "D.PRICE_CHILD,"		//12	
										+ "D.PRICE_INFANT," 	//13	
										+ "D.PRICE_NORMAL,"		//14	
										+ "A.ALLOT_SEQ," 		//15		
										+ "A.SKU_ID," 			//16		
										+ "A.JAN_CD,"			//17			
										+ "A.DATE_TARGET,"		//18	
										+ "E.AIRPORTNAME,"		//19	
										+ "F.AIRPORTNAME, "		//20
										+ "A.DEPUTURE_TIME, "	//21
										+ "A.ARRIVAL_TIME, "	//22
										+ "C.FLIGHT_NO, "		//23
										+ "B.TARGET_HOUR_COM, "	//24
										+ "C.PRODUCT_NAME "		//25
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
										+ "A.DATE_TARGET*10000+B.TARGET_REAL_HOUR>=$1$2 AND "
										+ "TO_DATE(A.DATE_TARGET)-D.LAST_DAY>=SYSDATE AND "
										+ "C.DEPARTTURE IN ( $3 ) AND "
										+ "C.ARRIVAL IN ( $4 ) AND "
										+ "A.ALLOT_NUMBER > 0 AND "
										+ "D.STOP_FLG=0 ORDER BY E.HAB_STATUS,A.DATE_TARGET";
	public static final String listsqlallot = "SELECT "
										+ "A.WEEKDAY," 			//00
										+ "B.TARGET_REAL_HOUR," //01
										+ "A.TARGET_HOUR,"      //02
										+ "C.DEPARTTURE," 		//03
										+ "C.ARRIVAL," 			//04	
										+ "D.CABIN_CLASS," 		//05	
										+ "D.BOOKING_CLASS,"	//06	
										+ "C.AGT_CD,"			//07			
										+ "A.ALLOT_NUMBER,"		//08
										+ "D.MAX_MEMBER,"		//09	
										+ "D.MIN_MEMBER," 		//10	
										+ "D.PRICE_BASE,"		//11		
										+ "D.PRICE_CHILD,"		//12	
										+ "D.PRICE_INFANT," 	//13	
										+ "D.PRICE_NORMAL,"		//14	
										+ "A.ALLOT_SEQ," 		//15		
										+ "A.SKU_ID," 			//16		
										+ "A.JAN_CD,"			//17			
										+ "A.DATE_TARGET,"		//18	
										+ "E.AIRPORTNAME,"		//19	
										+ "F.AIRPORTNAME, "		//20
										+ "A.DEPUTURE_TIME, "	//21
										+ "A.ARRIVAL_TIME, "	//22
										+ "C.FLIGHT_NO, "		//23
										+ "B.TARGET_HOUR_COM, "	//24
										+ "C.PRODUCT_NAME "		//25
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
										+ "A.DATE_TARGET*10000+B.TARGET_REAL_HOUR>$1$2 AND "
										+ "TO_DATE(A.DATE_TARGET)-D.LAST_DAY>=SYSDATE AND "
										+ "C.DEPARTTURE IN ( $3 ) AND "
										+ "C.ARRIVAL IN ( $4 ) AND "
										+ "A.ALLOT_NUMBER > $5 AND "
										+ "D.STOP_FLG=0 ORDER BY E.HAB_STATUS,A.DATE_TARGET";
	public static final String listsqlscatid = "SELECT "
										+ "A.WEEKDAY," 			//00
										+ "B.TARGET_REAL_HOUR," //01
										+ "A.TARGET_HOUR,"      //02
										+ "C.DEPARTTURE," 		//03
										+ "C.ARRIVAL," 			//04	
										+ "D.CABIN_CLASS," 		//05	
										+ "D.BOOKING_CLASS,"	//06	
										+ "C.AGT_CD,"			//07			
										+ "A.ALLOT_NUMBER,"		//08
										+ "D.MAX_MEMBER,"		//09	
										+ "D.MIN_MEMBER," 		//10	
										+ "D.PRICE_BASE,"		//11		
										+ "D.PRICE_CHILD,"		//12	
										+ "D.PRICE_INFANT," 	//13	
										+ "D.PRICE_NORMAL,"		//14	
										+ "A.ALLOT_SEQ," 		//15		
										+ "A.SKU_ID," 			//16		
										+ "A.JAN_CD,"			//17			
										+ "A.DATE_TARGET,"		//18	
										+ "E.AIRPORTNAME,"		//19	
										+ "F.AIRPORTNAME, "		//20
										+ "A.DEPUTURE_TIME, "	//21
										+ "A.ARRIVAL_TIME, "	//22
										+ "C.FLIGHT_NO, "		//23
										+ "B.TARGET_HOUR_COM, "	//24
										+ "C.PRODUCT_NAME "		//25
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
										+ "A.DATE_TARGET*10000+B.TARGET_REAL_HOUR>$1$2 AND "
										+ "TO_DATE(A.DATE_TARGET)-D.LAST_DAY>=SYSDATE AND "
										+ "C.STATUS<$3 AND "
										+ "A.ALLOT_NUMBER > $4 AND "
										+ "D.STOP_FLG=0 ORDER BY E.HAB_STATUS,A.DATE_TARGET";
	public static final String detailsql="SELECT "
									    + "A.PRODUCT_NAME,"		//00
									    + "A.FLIGHT_NO,"		//01
									    + "A.DEPARTTURE,"		//02
									    + "A.ARRIVAL,"			//03
									    + "A.AGT_CD,"			//04
									    + "A.COMMERCE_COMMENT,"	//05
									    + "A.PRICE_COMMENT,"	//06
									    + "A.AGENT_MEMBER_CD,"	//07
									    + "B.PRODUCT_TYPE_CD,"	//08
									    + "B.ALLOT_TYPE,"		//09
									    + "B.AGT_CD,"			//10
									    + "B.CABIN_CLASS,"		//11
									    + "B.BOOKING_CLASS,"	//12
									    + "B.MAX_MEMBER,"		//13
									    + "B.MIN_MEMBER,"		//14
									    + "B.STOP_FLG,"			//15
									    + "B.PRICE_BASE,"		//16
									    + "B.PRICE_CHILD,"		//17
									    + "B.PRICE_INFANT,"		//18
									    + "B.PRICE_NORMAL,"		//19
									    + "B.PRICE_PAT_01,"		//20
									    + "B.PRICE_PAT_02,"		//21
									    + "B.PRICE_PAT_03,"		//22
									    + "B.PRICE_PAT_04,"		//23
									    + "B.PRICE_PAT_05,"		//24
									    + "B.PRICE_PAT_06,"		//25
									    + "B.PRICE_PAT_07,"		//26
									    + "B.PRICE_PAT_08,"		//27
									    + "B.PRICE_PAT_09,"		//28
									    + "B.PRICE_PAT_10,"		//29
										+ "C.ALLOT_SEQ,"		//30
										+ "H.TARGET_REAL_HOUR,"	//31
									    //+ "C.TARGET_HOUR,"		//31
									    + "C.DATE_TARGET,"		//32
									    + "C.WEEKDAY,"			//33
									    + "C.SKU_ID,"			//34
									    + "C.JAN_CD,"
									    + "C.ALLOT_NUMBER,"
									    + "C.BEFORE_START_DAYS,"
									    + "C.OPEN_COLUMN_FLG,"
									    + "C.LAST_SALES_DAYS,"
									    + "C.LAST_SALES_TIMES,"
									    + "E.AIRPORTNAME DEPARTTURECITY,"
									    + "F.AIRPORTNAME ARRIVALCITY,"
									    + "G.FIRST_NAME, "
									    + "H.TARGET_HOUR_COM "
										+ "FROM "
										+ "PRODUCTS_OF_FLIGHT A, FLIGHT_SKU B, ALLOTMENT_OF_FLIGHT C, DOMESTICAIRPORT E, DOMESTICAIRPORT F, AGENT_TBL G, TARGET_HOUR_MASTER H "
										+ "WHERE "
									 	+ "A.JAN_CD=C.JAN_CD AND "
									 	+ "B.SKU_ID=C.SKU_ID AND "
									 	+ "E.LETTERCODE=A.DEPARTTURE AND "
									 	+ "F.LETTERCODE=A.ARRIVAL AND "
									 	+ "G.AGT_CD=A.AGT_CD AND "
									 	+ "H.TARGET_HOUR=C.TARGET_HOUR AND "
									 	+ "C.JAN_CD='$1' AND C.ALLOT_SEQ=$2";
	public static final String allotchecksql="SELECT (A.ALLOT_NUMBER - $1 ) ALLOT "
												+ "FROM ALLOTMENT_OF_FLIGHT A, FLIGHT_SKU B "
												+ "WHERE ALLOT_SEQ=$2 AND A.SKU_ID=B.SKU_ID AND B.STOP_FLG=0";
	public static final String allotcatchsql="UPDATE ALLOTMENT_OF_FLIGHT SET ALLOT_NUMBER=ALLOT_NUMBER-$1 WHERE ALLOT_SEQ=$2";
	public static final String allotreturnsql="UPDATE ALLOTMENT_OF_FLIGHT SET ALLOT_NUMBER=ALLOT_NUMBER+$1 WHERE ALLOT_SEQ=$2";
	public static final String memberUpdatesql = "UPDATE sub_order SET PAX=$1, MEMBERS='$2' WHERE SUB_ORDER_NO=$3";
	public static final String suborderUpdatesql = "UPDATE sub_order SET PAX=$1 WHERE SUB_ORDER_NO=$2";
}
