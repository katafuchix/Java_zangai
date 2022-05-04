package jp.co.yobrain.util.form.conf;

import java.io.*;
import jp.co.yobrain.util.form.*;

/**
* 電話番号用クラス
*
*/

public class FormCheckTelphone implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckTelphone(){
	}

	/**
	* 全角数値を半角数値に変換及び整数か判定
	* @return	CheckError CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	synchronized public CheckError convertCheck(String valu, CheckError checkError){
		StringBuffer sb = new StringBuffer( valu );
		StringBuffer msb = new StringBuffer();
		
		char c;
		int counter2 = 0;
		for( int i = 0; i < sb.length() ; i++ ) {
			c = sb.charAt( i );
                        
                        if( c == 0xfffd){
                            checkError.setError(8);
                            checkError.setRstr(valu);
                            return checkError;
			}
                        
			//全角数字=>半角数字変換
			if( 0xff10 <= c && c <= 0xff19 ) {
				msb.append(NUMBER.charAt( c - 0xff0d));
				counter2++;
			//半角数字
			} else if(0x0030 <= c && c <= 0x0039){
				msb.append(c);
				counter2++;
			//全角―を半角-
			}else if( 0x2010 == c){
				msb.append((char)0x002d);
				counter2++;
			//全角―を半角-
			}else if( 0x2015 == c){
				msb.append((char)0x002d);
				counter2++;
			//全角―を半角-
			}else if( 0x2212 == c){
				msb.append((char)0x002d);
				counter2++;
			//全角ーを半角‐
			}else if( 0x30fc == c){
				msb.append((char)0x002d);
				counter2++;
			//半角‐
			}else if( 0x002d == c){
				msb.append((char)0x002d);
				counter2++;
			//エラー文字
			} else {
				int num;
				switch (c) {
					case 0x002e:			//全角 ．
						num = 1;
						break;
					case 0x002c:			//全角 ，
						num = 2;
						break;
					default :				//その他エラー文字
						num = 3;
				}
				checkError.setError(num);
				checkError.setRstr(valu);
				return checkError;
			}
		}
		if(counter2 < 1){
			checkError.setError(3);
			checkError.setRstr(valu);
			return checkError;
		}
		checkError.setRstr(msb.toString());
		return checkError;
	}
}