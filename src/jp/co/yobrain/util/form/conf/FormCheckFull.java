package jp.co.yobrain.util.form.conf;

import java.io.*;
import jp.co.yobrain.util.form.*;

/**
* 文字列チェック用クラス
*
*/
public class FormCheckFull implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckFull(){
	}

	/**
	* 使用不可の文字を変換
	* @return	CheckError	CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	synchronized public CheckError convertCheck(String valu, CheckError checkError){
		StringBuffer sb = new StringBuffer( valu );
		StringBuffer msb = new StringBuffer();
		
		char c;
		char ch ;

		for( int i = 0; i < sb.length() ; i++ ) {
			c = sb.charAt( i );
                        
                        if( c == 0xfffd){
                            checkError.setError(8);
                            checkError.setRstr(valu);
                            return checkError;
			}

			//半角かな全角カナに変換
			if( 0xff60 < c && c <= 0xff9d ) {

				if( (i +1) < sb.length()){
					ch = sb.charAt( i + 1 );
				} else{
					ch = ' ';
				}
				switch (ch) {
					case 0xff9e:
						msb.append(KANA[c - 0xff61][1]);
						++i;
						break;

					case 0xff9f:
						msb.append(KANA[c - 0xff61][2]);
						++i;
						break;

					default :
						msb.append(KANA[c - 0xff61][0]);
				}

			//全角アルファベット=>半角アルファベット変換
			} else if( (0xff21 <= c && c <= 0xff3a) || (0xff41 <= c && c <= 0xff5a)){

				msb.append(ALPHABET.charAt( c - 0xff20));

			//全角数字=>半角数字変換
			} else if ( 0xff10 <= c && c <= 0xff19 ){

				msb.append(NUMBER.charAt( c - 0xff0d));

			//その他の文字
			} else {

				//代用文字に置換
				switch (c) {
					case 0x301c: 					//波線(SJIS)を半角-
						msb.append((char)0x002d);
						break;

					case 0xff5e:					//波線(MS932)を半角-
						msb.append((char)0x002d);
						break;

					case 0x2010:					//全角―を半角-
						msb.append((char)0x002d);
						break;

					case 0x2015:					//全角―を半角-
						msb.append((char)0x002d);
						break;

					case 0x2212:					//全角―を半角-
						msb.append((char)0x002d);
						break;

					case 0x30fc:					//全角ーを半角‐
						msb.append((char)0x002d);
						break;

					case 0x003c:					// < をエスケープ文字に
						msb.append("&lt;");
						break;

					case 0x003e:					// > をエスケープ文字に
						msb.append("&gt;");
						break;

					case 0x0026:					// & をエスケープ文字に
						msb.append("&amp;");
						break;

					case 0x0022:					// " をエスケープ文字に
						msb.append("&quot;");
						break;

					case 0x0027:					// ' をエスケープ文字に
						msb.append("&#180;");
						break;

					default :						//置換せず
						msb.append(c);
				}
			}
		}
		checkError.setRstr(msb.toString());
		return checkError;
	}
}