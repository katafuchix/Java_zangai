package jp.co.lastminute.Entertainment;

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
	public static final int ticket = 99;
	public static final int activty = 94;
	
	public static final String _index = "index";
	public static final String _purpose = "purpose";
	public static final String _price = "price";
	
	public static final int _maxorder = 8;
	
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
	public static final int cat_id = 99;
	public static final String toprealclasspath = "";
	public static final String xmlsession = "Ticket.List.Session";
	public static final String listsql="";
	public static final String listsqlallot = "";
	public static final String listsqlscatid = "";
	public static final String detailsql="SELECT "
    									+ "A.SKU_ID,"
    									+ "A.AGT_CD,"
    									+ "A.PRODUCT_SEQ,"
    									+ "A.SALESFROM,"
    									+ "A.SKU_NAME,"
    									+ "A.SHEATANDSO,"
    									+ "A.VIEWSTART,"
    									+ "A.OPTIONALCOMMENT,"
    									+ "A.SALESTO,"
    									+ "A.PRICE,"
    									+ "A.TAX,"
    									+ "A.SENDING,"
    									+ "A.SENDING_TAX,"
    									+ "A.SENDING_CALC_UNIT,"
    									+ "B.ALLOT_SEQ,"
    									+ "B.SKU_ID,"
    									+ "B.ALLOTCOUNTER,"
    									+ "B.ALLOTDATE,"
    									+ "B.ALLOTTIME,"
    									+ "B.STOP_FLG,"
    									+ "C.PRODUCT_NAME "
										+ "FROM "
										+ "TICKETACTIVTY_SKU A, "
										+ "ALLOTEMENT_OF_TICKETSACTIVITY B, "
										+ "TICKETSACTIVTY C "
										+ "WHERE "
  										+ "A.PRODUCT_SEQ=C.PRODUCT_SEQ AND "
										+ "B.SKU_ID=A.SKU_ID AND "
										+ "B.STOP_FLG=0 AND "
										+ "B.ALLOTCOUNTER>0 AND "
										+ "TO_DATE(A.SALESTO)>=SYSDATE AND "
										+ "TO_DATE(B.ALLOTDATE)-A.VIEWSTART <=SYSDATE AND "
										//+ "A.SALESTO>=TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) AND "
										//+ "B.ALLOTDATE<=TO_NUMBER(TO_CHAR(SYSDATE+A.VIEWSTART ,'YYYYMMDD')) AND "
										+ "A.PRODUCT_SEQ=$1 "
										+ "ORDER BY A.SKU_ID,B.ALLOTDATE,B.ALLOTTIME";
	public static final String allotchecksql="SELECT (A.ALLOTCOUNTER - $1 ) ALLOT "
												+ "FROM ALLOTEMENT_OF_TICKETSACTIVITY A, TICKETACTIVTY_SKU B "
												+ "WHERE ALLOT_SEQ=$2 AND A.SKU_ID=B.SKU_ID AND A.STOP_FLG=0";
	public static final String allotcatchsql="UPDATE ALLOTEMENT_OF_TICKETSACTIVITY SET ALLOTCOUNTER=ALLOTCOUNTER-$1 WHERE ALLOT_SEQ=$2";
	public static final String allotreturnsql="UPDATE ALLOTEMENT_OF_TICKETSACTIVITY SET ALLOTCOUNTER=ALLOTCOUNTER+$1 WHERE ALLOT_SEQ=$2";
	public static final String memberUpdatesql = "UPDATE sub_order SET PAX=$1, MEMBERS='$2' WHERE SUB_ORDER_NO=$3";
	public static final String suborderUpdatesql = "UPDATE sub_order SET PAX=$1 WHERE SUB_ORDER_NO=$2";
}
