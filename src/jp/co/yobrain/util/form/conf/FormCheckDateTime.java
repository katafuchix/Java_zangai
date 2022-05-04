package jp.co.yobrain.util.form.conf;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.form.*;

/**
* 日付チェック用クラス
*
*/
public class FormCheckDateTime implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckDateTime(){
	}

	/**
	*  日時チェック(yyyymmdd24mi)対応
	* @return	CheckError CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	synchronized public CheckError convertCheck(String valu, CheckError checkError){
		String day;
		
		StringBuffer sb;
		char c;

		sb = new StringBuffer( valu );

		for( int i = 0; i < sb.length() ; i++ ) {
			c = sb.charAt( i );
                        
                        if( c == 0xfffd){
                            checkError.setError(8);
                            checkError.setRstr(valu);
                            return checkError;
			}

			//全角数字=>半角数字変換
			if( 0xff10 <= c && c <= 0xff19 ) {

				sb.setCharAt(i, NUMBER.charAt( c - 0xff0d));

			//半角数字
			} else if(0x0030 <= c && c <= 0x0039){

			//エラー文字
			} else {
				checkError.setError(9);
				checkError.setRstr(valu);
				return checkError;
			}
		}
		day = sb.toString();

		if(day.length() > 11 && day.length() < 13){
			int yy = Integer.parseInt(day.substring(0,4));
			int mm = Integer.parseInt(day.substring(4,6));
			int dd = Integer.parseInt(day.substring(6,8));
			int hh = Integer.parseInt(day.substring(8,10));
			int mi = Integer.parseInt(day.substring(10,12));

			if( (yy < 1 ) || (mm < 1 || mm > 12) || ( dd < 1 || dd > daysget( yy, mm) ) ){
				checkError.setError(10);
				checkError.setRstr(valu);
				return checkError;
			}

			if( hh < 0 || hh > 23 || mi < 0 || mi > 59){
				checkError.setError(38);
				checkError.setRstr(valu);
				return checkError;
			}

		} else {
			checkError.setError(9);
			checkError.setRstr(valu);
			return checkError;
		}
		checkError.setRstr(day);
		return checkError;
	}

	/**
	*  指定された月の日数を返すメゾット
	* @return	int 日数
	* @param	yy	西暦
	* @param	mm	月
	*/
	private static int daysget(int yy, int mm){

		switch(mm)
		{
		case 2:
			if( (yy % 400 == 0) || (yy % 4 == 0 && yy % 100 != 0) ) {	return 29;	} else {	return 28;	}
		case 4:
			return 30;
		case 6:
			return 30;
		case 9:
			return 30;
		case 11:
			return 30;
		default:
			return 31;
		}

	}

}
