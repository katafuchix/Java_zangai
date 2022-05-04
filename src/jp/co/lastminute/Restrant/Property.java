package jp.co.lastminute.Restrant;

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
	public static final String endsale = "1900";
	public static final String cancellsale = "1900";
	public static final int cat_id = 91;
	public static int detail_days_length = 8;
	public static int list_days_length = 8;
	public static final String toprealclasspath = "";
	public static final String productname = "Restrant";
	public static final String property = "Restrant.property";
	public static final String listcash = "Restrant.List.Cash";
	public static final String xmlsession = "Restrant.List.Session";
	public static final String listsql="";
	public static final String listsqlallot = "";
	public static final String listsqlscatid = "";
	public static final String detailsql="SELECT"
										   +" A.ALLOTID," 		//00
										   +" A.RESTAURANT,"	//01
										   +" A.TIMEID,"		//02 
										   +" A.SKUID,"			//03 
										   +" A.ALLOTDATE,"		//04
										   +" A.ALLOTTENT,"		//05
										   +" B.RESTAURANT,"	//06
										   +" B.SCATID,"		//07
										   +" B.SKUNAME,"		//08
										   +" B.SKUID,"			//09
										   +" B.MINORDER,"		//10
										   +" B.MAXORDER,"		//11
										   +" B.RESERVEFROM,"	//12
										   +" B.RESERVETO,"		//13
										   +" B.LIMITTIME,"		//14
										   +" B.CNACELLSTARTTIME,"//15
										   +" B.RESERVTIMESTR,"	//16
										   +" A.STOP_FLG "		//17
										   +"FROM RESTAURANTALLOT A,"
										   +" RESTAURANTSKU B "
										   +"WHERE"
										   +" A.SKUID=B.SKUID AND"
										   +" A.STOP_FLG=0 AND" 
										   +" B.STOP_FLG=0 AND"
										   +" TO_DATE(A.ALLOTDATE,'YYYYMMDD')-B.DATEOFVIEWSTART <= SYSDATE AND"
										   +" A.RESTAURANT='$1' AND "
										   +" A.ALLOTDATE>=$2 AND A.ALLOTDATE<=$3 ORDER BY A.SKUID, A.ALLOTDATE";
	public static final String courcesql = "SELECT "
										  +" A.COURSEID,"	//00
										  +" A.RESTAURANT,"	//01
										  +" A.COURSECD,"	//02
										  +" A.PRIORITY,"	//03
										  +" A.VIEWFROM,"	//04
										  +" A.VIEWTO,"		//05
										  +" A.COURSENAME,"	//06
										  +" A.DISHES,"		//07
										  +" A.PRICE,"		//08
										  +" A.TAX,"		//09
										  +" A.C_COMMENT,"	//10
										  +" A.ONEPOINT,"	//11
										  +" A.IMAGEURI, "	//12
										  +" A.AGT_CD "		//13
										  +"FROM "
										  +" RESTRANTMENU A, "
										  +" RESTAURANTINFO B "
										  +"WHERE "
										  +" A.RESTAURANT=B.RESTAURANT AND "
										  +" RESTAURANT='$1' AND"
										  +" A.VIEWFROM>=$2 AND"
										  +" A.VIEWTO<=$3 ORDER BY A.COURSECD, A.PRIORITY";
	public static final String allotchecksql="SELECT (ALLOTTENT - $1 ) ALLOT FROM RESTAURANTALLOT WHERE ALLOTID=$2 AND STOP_FLG=0";
	public static final String allotcatchsql="UPDATE RESTAURANTALLOT SET ALLOTTENT=ALLOTTENT-$1 WHERE ALLOTID=$2";
	public static final String allotreturnsql="UPDATE RESTAURANTALLOT SET ALLOTTENT=ALLOTTENT+$1 WHERE ALLOTID=$2";
	public static final String memberUpdatesql = "UPDATE sub_order SET PAX=$1, MEMBERS='$2' WHERE SUB_ORDER_NO=$3";
	public static final String suborderUpdatesql = "UPDATE sub_order SET PAX=$1 WHERE SUB_ORDER_NO=$2";
}
