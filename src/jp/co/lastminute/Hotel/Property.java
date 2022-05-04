package jp.co.lastminute.Hotel;

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
	public static final String pagt_cd = "KNT";
	
	public static final String _index = "index";
	public static final String _purpose = "purpose";
	public static final String _price = "area";
	
	public static final int _minorder = 1;
	public static final int _maxorder = 8;
	public static final int _pagingsize = 12;
	
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
	public static final int cat_id = 4;
	public static final String toprealclasspath = "";
	public static final String xmlsession = "Ticket.List.Session";
	public static final String listsqlallot = "";
	public static final String listsqlscatid = "";
	public static final String detailsql="SELECT "
    									+ "A.JAN_CD,"
    									+ "A.AGT_CD,"
    									+ "A.AGT_PRODUCT_CD,"
    									+ "A.PRICE,"
    									+ "A.TAX,"
    									+ "A.SENDING,"
    									+ "A.SENDING_TAX,"
    									+ "A.SENDING_CALC_UNIT,"
    									+ "A.ALLOTNUM,"
    									+ "A.HAVEALT,"
    									+ "A.MIXED_TYPES, "
    									+ "A.PRODUCT_NAME "
										+ "FROM "
										+ "CALLEGEPRODUCT_TBL A "
										+ "WHERE "
										+ "A.PRODUCT_TYPE_CD=$1 AND "
										+ "A.HAVEALT=0 AND "
  										+ "A.JAN_CD='$2'";
	public static final String allotchecksql="SELECT (ALLOTNUM - $1) ALLOT "
												+ "FROM CALLEGEPRODUCT_TBL "
												+ "WHERE HAVEALT=0 AND JAN_CD='$2'";
	public static final String allotcatchsql="UPDATE CALLEGEPRODUCT_TBL SET ALLOTNUM=ALLOTNUM-$1 WHERE JAN_CD='$2'";
	public static final String allotreturnsql="UPDATE CALLEGEPRODUCT_TBL SET ALLOTNUM=ALLOTNUM+$1 WHERE JAN_CD='$2'";
	public static final String memberUpdatesql = "UPDATE sub_order SET PAX=$1, MEMBERS='$2' WHERE SUB_ORDER_NO=$3";
	public static final String suborderUpdatesql = "UPDATE sub_order SET PAX=$1 WHERE SUB_ORDER_NO=$2";
}
