package jp.co.lastminute.Hotel.jdbc;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.ParseFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class Webaltp_handler {
	/**
	 * イニシャライズ
	 */
	public static Webaltp[] init(
		dbDataAccesser accesser,
		String from,
		int size) {
		DataFormat df = null;
		Webaltp[] reWebaltp = new Webaltp[size];
		Hashtable temp = new Hashtable();
		Webaltp tempaltp = null;
		for (int i = 0; i < accesser.getRowsize(); i++) {
			Webaltp altp = new Webaltp();
			altp.altdat = accesser.getData(i, 0);
			altp.price = accesser.getDatabyInt(i, 1);
			altp.price2 = accesser.getDatabyInt(i, 2);
			altp.price3 = accesser.getDatabyInt(i, 3);
			altp.price4 = accesser.getDatabyInt(i, 4);
			altp.price5 = accesser.getDatabyInt(i, 5);
			altp.pricea1 = accesser.getDatabyInt(i, 6);
			altp.pricea2 = accesser.getDatabyInt(i, 7);
			altp.pricea3 = accesser.getDatabyInt(i, 8);
			altp.pricea4 = accesser.getDatabyInt(i, 9);
			altp.priceb1 = accesser.getDatabyInt(i, 10);
			altp.priceb2 = accesser.getDatabyInt(i, 11);
			altp.priceb3 = accesser.getDatabyInt(i, 12);
			altp.priceb4 = accesser.getDatabyInt(i, 13);
			altp.pricec1 = accesser.getDatabyInt(i, 14);
			altp.priced1 = accesser.getDatabyInt(i, 15);
			altp.pricee = accesser.getDatabyInt(i, 16);
			altp.haveal = accesser.getDatabyInt(i, 17);
			altp.roomtype = accesser.getData(i, 18);
			altp.campaing = accesser.getData(i, 19);
			altp.mailcode = accesser.getData(i, 20);
			altp.max_nr = accesser.getDatabyInt(i, 21);
			altp.min_nr = accesser.getDatabyInt(i, 22);
			////////////////////////////////////////////
			altp = setPricesStr(altp);
			temp.put(altp.altdat, altp);
			if( i == 0 ){
				tempaltp = altp;
			}
			altp = null;
		}
		for (int i = 0; i < size; i++) {
			String target = df.AddToDate(from, i);
			reWebaltp[i] = isMatchAndGet(temp, target, tempaltp);
		}
		return reWebaltp;
	}
	private static Webaltp isMatchAndGet(Hashtable temp, String target, Webaltp tempaltp) {
		if (temp.get(target) != null) {
			return (Webaltp) temp.get(target);
		}
		return getnewAltdta(target, tempaltp);
	}

	private static Webaltp getnewAltdta(String target, Webaltp tempaltp) {
		DataFormat df = null;
		Webaltp altp = new Webaltp();
		altp.haveal = -1 ;
		altp.altdat = target;
		altp.roomtype = tempaltp.roomtype;
		altp.campaing = tempaltp.campaing;
		altp.mailcode = tempaltp.mailcode;
		altp.max_nr = tempaltp.max_nr;
		altp.min_nr = tempaltp.min_nr;
		altp.altdatStr = df.getDateTime4WWW(target);
		return altp;
	}

	private static Webaltp setPricesStr(Webaltp altp) {
		DataFormat df = null;
		int intflg = altp.haveal;
		altp.altdatStr = df.getDateTime4WWW(altp.altdat);
		altp.priceStr = setPriceStr(altp.price, intflg);
		altp.price2Str = setPriceStr(altp.price2, intflg);
		altp.price3Str = setPriceStr(altp.price3, intflg);
		altp.price4Str = setPriceStr(altp.price4, intflg);
		altp.price5Str = setPriceStr(altp.price5, intflg);
		altp.pricea1Str = setPriceStr(altp.pricea1, intflg);
		altp.pricea2Str = setPriceStr(altp.pricea2, intflg);
		altp.pricea3Str = setPriceStr(altp.pricea3, intflg);
		altp.pricea4Str = setPriceStr(altp.pricea4, intflg);
		altp.priceb1Str = setPriceStr(altp.priceb1, intflg);
		altp.priceb2Str = setPriceStr(altp.priceb2, intflg);
		altp.priceb3Str = setPriceStr(altp.priceb3, intflg);
		altp.priceb4Str = setPriceStr(altp.priceb4, intflg);
		altp.pricec1Str = setPriceStr(altp.pricec1, intflg);
		altp.priced1Str = setPriceStr(altp.priced1, intflg);
		altp.priceeStr = setPriceStr(altp.pricee, intflg);
		altp.haveaStrl =
			"<input type='radio' name='checkindate' value='"
				+ altp.altdat
				+ "'>";
		return altp;
	}
	private static String setPriceStr(int pr, int flg) {
		if (flg == 1) {
			return "--";
		}
		if (pr == 0) {
			return "--";
		}
		ParseFormat pf = null;
		return pf.ToPriceFormat(pr);
	}
}
