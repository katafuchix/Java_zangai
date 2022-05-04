package jp.co.yobrain.util.form.conf;

/**
* 通貨チェック用クラス
*
*/

import java.io.*;
import jp.co.yobrain.util.form.*;

public class FormCheckMoney implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckMoney(){
	}


	/**
	* 通貨として正しいか判定
	* @return	CheckError CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	synchronized public CheckError convertCheck(String valu, CheckError checkError){
		StringBuffer sb = new StringBuffer( valu );
		StringBuffer msb = new StringBuffer();
		
		char c;
		int counter = 0;
		int counter2 = 0;

		for( int i = 0; i < sb.length() ; i++ ) {
			c = sb.charAt( i );
                        
                        if( c == 0xfffd){
                            checkError.setError(8);
                            checkError.setRstr(valu);
                            return checkError;
			}

			//全角数字チェック
			if( 0xff10 <= c && c <= 0xff19 ) {

				//半角数字変換
				msb.append(NUMBER.charAt( c - 0xff0d));
				counter2++;
			//半角数字チェック
			} else if(0x0030 <= c && c <= 0x0039){

				msb.append(c);
				counter2++;

			//半角 , 取り除き
			} else if( c == 0x002c){

			//全角 ，取り除き
			} else if( c == 0xff0c){

			//エラー文字
			} else {
				checkError.setError(6);
				checkError.setRstr(valu);
				return checkError;
			}

		}

		if(counter2 < 1){
			checkError.setError(6);
			checkError.setRstr(valu);
			return checkError;
		}

		if(Long.parseLong(msb.toString()) < 1){
			checkError.setError(6);
			checkError.setRstr(valu);
			return checkError;
		}
		checkError.setRstr(msb.toString());
		return checkError;
	}

}