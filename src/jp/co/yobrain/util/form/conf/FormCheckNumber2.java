package jp.co.yobrain.util.form.conf;

import java.io.*;
import jp.co.yobrain.util.form.*;

/**
* 数値チェック用クラス
*
*/

public class FormCheckNumber2 implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckNumber2(){
	}

	/**
	* 全角数字を半角数字に変換及び数値か判定
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
			//半角 . & 全角 ．チェック
			} else if( c == 0xff0e || c == 0x002e ){

				// . の個数及び位置チェック
				if( counter == 0 && i > 0 && i < (sb.length() -1)){

					//全角 ．=> 半角. 変換
					if( c == 0xff0e ) c = NUMBER.charAt( c - 0xff0d);
					msb.append(c);
					counter++;

				} else if( i == (sb.length() - 1) ){

				}else{
					checkError.setError(5);
					checkError.setRstr(valu);
					return checkError;
				}

			//エラー文字
			} else {
				int num;
				switch (c) {

					case 0x002c:			//全角 ,
						num = 2;
						break;

					default :				//その他
						num = 3;
				}
				checkError.setError(num);
				checkError.setRstr(valu);
				return checkError;
			}

		}

		if(counter2 < 1){
			checkError.setError(5);
			checkError.setRstr(valu);
			return checkError;
		}

		checkError.setRstr(msb.toString());
		return checkError;
	}

}