package jp.co.lastminute.cart.Cancell;

import jp.co.lastminute.cart.Constants;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Property {
	public static final String cancellsql = "SELECT A.USER_ID," //00
											 + " A.PASSWD, " 		//01
											 + " B.ORDER_NO, "		//02
											 + " C.SUB_ORDER_NO, " 	//03
											 + " C.TITLE, " 		//04
											 + " C.PRICE, " 		//05
											 + " TO_CHAR(C.MAKE_DATE,'YYYY/MM/DD HH24:MI'), " 	//06
											 + " C.AGT_ORDER_NO, " 	//07
											 + " C.AGT_CD, " 		//08
											 + " C.PRODUCT_TYPE_CD, "// 09
											 + " C.PAX, " 			//10
											 + " C.LAST_SALE, " 	//11
											 + " C.PROD_CD,"		//12
											 + " C.AGT_PROD_CD,"	//13
											 + " C.AGT_PLAN_CD,"	//14
											 + " C.BUY_PROP,"		//15
											 + " C.AGR_AREA_CD, "	//16
											 + " D.SEI_KANJI,"		//17
											 + " D.NA_KANJI, "		//18
											 + " D.SEI_KANA, "		//19
											 + " D.NA_KANA, "		//20
											 + " D.FIRST_NAME, "	//21
											 + " D.LASTNAME, "		//22
											 + " D.SEX, "			//23
											 + " D.BIRTH_DAY, "		//24
											 + " D.POSTAL_CD, "		//25
											 + " D.STATE_CD, "		//26
											 + " D.CITY_NAME, "		//27
											 + " D.ADDRESS, "		//28
											 + " D.TEL_NO, "		//29
											 + " D.FAX, "			//30
											 + " A.E_MAIL, "		//31
											 + " A.DEALWATCHID, "	//32
											 + " C.MEMBERS, "		//33
											 + " C.SALES_DATE, "	//34
											 + " C.INFANT, "		//35
											 + " C.SHEAT "			//36
											 + "FROM USER_TBL A, "
											 + "ORDERS B, "
											 + "SUB_ORDER C, "	
											 + "PROFILE D "
											 + "WHERE B.ORDER_NO=C.ORDER_NO AND "
											 + "A.USER_ID=B.USER_ID AND "
											 + "A.USER_ID=D.USER_ID AND "
											 + "A.E_MAIL='$1' AND "
											 + "A.PASSWD='$2' AND "
											 + "C.LAST_SALE > $3 AND "
											 + "C.STATUS<" + Constants.CANCLL_STATUS_ + " AND "
											 + "C.SUB_ORDER_NO=$4";
											 
}
