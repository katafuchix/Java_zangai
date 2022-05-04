package jp.co.lastminute.Hotel.detail.Agent;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.rpc.SendClient;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SendParam implements Serializable {
	public String resultresultFromWebsite(String url, Vector paramsVector) {
		SendClient postManager = new SendClient();
		String resultFromWebsite = postManager.sendText(url, paramsVector);
		try {
			if (resultFromWebsite == null || resultFromWebsite.length() == 0) {
				return null;
			}
			if (resultFromWebsite.indexOf("SUCCESS") != -1) {
				return resultFromWebsite;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	// 	TYPE_NUMBER_ = 0;
	//	TYPE_STRING_ = 1;
	//	TYPE_FLOAT_ = 2;
	//	TYPE_DATE_ =  3;
	//	TYPE_EMAIL_ = 4;
	//	TYPE_MONEY_ = 5;
	//	TYPE_ALPHABET_ = 6;
	//	TYPE_ALPHABETNUMBER_ = 7;
	//	TYPE_DATETIME_ =  8;
	//	TYPE_PHONE_ =  9;
	//	TYPE_TWOBYTE_ =  10;
	/**
	 * チェック
	 */
	public CheckError checkValue(String str,int type,boolean need) throws Exception{
		String reStr = toJISAutoDetect( str);
		CheckError chError = null;
		Check formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet( reStr, type, need );
		return chError;
	}
	private static String toJISAutoDetect(String s) {
		try {
			if (s == null){
				return null;
			}else{
				return new String(s.getBytes("ISO-8859-1"), "JISAutoDetect");
			}
		} catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return "";
		}
	}
	/**
	 * ハイフンを外す
	 */
	protected String modifyCourseNumber(String courseNumber) {
		if (courseNumber!= null && courseNumber.length() != 0) {
			return courseNumber.replaceAll("-", "");
		}
		return "";
	}
}
