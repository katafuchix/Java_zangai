package jp.co.yobrain.util.form.conf;

import java.io.*;
import jp.co.yobrain.util.form.*;

/**
* E-mailアドレスチェック用クラス
*
*/

public class FormCheckEmail implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckEmail(){
	}

	/**
	* 全角英数を半角英数に置換ごEmailアドレスか判定
	* @return	CheckError CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	synchronized public CheckError convertCheck(String valu, CheckError checkError){
		String value;
		StringBuffer sb = new StringBuffer( valu );
		
		int pointer = 0;
		char c;

		// 不正な文字がないか判定する
		for( int i = 0; sb.length() > i; i++ ){
			c = sb.charAt( i );
                        
                        if( c == 0xfffd){
                            checkError.setError(8);
                            checkError.setRstr(valu);
                            return checkError;
			}

			// 全角 ＿ ー ・ ＠ 半角変換
			if( c == 0xff3f || c == 0x2010 || c == 0x2015 || c == 0x2212 || c == 0x30fc || c == 0xff0e || c == 0xff20){

				switch (c) {
					case 0xff3f:
						sb.setCharAt(i, ALPHABET.charAt(31));		//全角 ＿ => 半角 _
						break;

					case 0x2010:
						sb.setCharAt(i, NUMBER.charAt(0));			//全角 ― => 半角 -
						break;

					case 0x2015:
						sb.setCharAt(i, NUMBER.charAt(0));			//全角 ― => 半角 -
						break;

					case 0x2212:
						sb.setCharAt(i, NUMBER.charAt(0));			//全角 ― => 半角 -
						break;

					case 0x30fc:
						sb.setCharAt(i, NUMBER.charAt(0));			//全角 ― => 半角 -
						break;

					case 0xff0e:
						sb.setCharAt(i, NUMBER.charAt(1));			//全角 ． => 半角 .
						break;

					case 0xff20:
						sb.setCharAt(i, ALPHABET.charAt(0));		//全角 ＠ => 半角 @
						break;

					default :
				}

			//全角アルファベッ=>ト半角アルファベット
			} else if( (0xff21 <= c && c <= 0xff3a) || (0xff41 <= c && c <= 0xff5a)){

				sb.setCharAt(i, ALPHABET.charAt( c - 0xff20));

			//全角数字=>半角数字
			} else if ( 0xff10 <= c && c <= 0xff19 ){

				sb.setCharAt(i, NUMBER.charAt( c - 0xff0d));

			//使用可能文字 半角アルファベット / 半角数字 / @ / _ / - / .
			} else if( (0x0030 <= c && c <= 0x0039) || (0x0040 <= c && c <= 0x005a) ||
					   (0x0061 <= c && c <= 0x007a) || c == 0x002d || c == 0x002e ||
						c == 0x005f){

			//その他エラー文字
			} else {
				checkError.setError(11);
				checkError.setRstr(valu);
				return checkError;
			}
		}

		//置換後の文字列
		value = sb.toString();

		// ユーザ名とホスト名に分ける
		int atpos = value.indexOf("@");
		if (atpos == -1){
			checkError.setError(12);
			checkError.setRstr(valu);
			return checkError;
		}
                int atpos_dot = value.indexOf("@.");
                if( atpos_dot > 0){
                    checkError.setError(12);
                    checkError.setRstr(valu);
                    return checkError;
                }
                /**
                int dot_atpos = value.indexOf(".@.");
                if( dot_atpos > 0){
                    checkError.setError(12);
                    checkError.setRstr(valu);
                    return checkError;
                }
                int dot_dot = value.indexOf("..");
                if( dot_dot > 0){
                    checkError.setError(12);
                    checkError.setRstr(valu);
                    return checkError;
                }
                 */
                //最後の文字列に.が含まれている。
                try{
                    String laststring = value.substring( value.length() - 2 );
                    if( laststring.indexOf(".") > 0){
                        checkError.setError(12);
                        checkError.setRstr(valu);
                        return checkError;                    
                    }
                }catch(Exception ex){   }
		// アドレス部
		String addr = value.substring(atpos + 1);
		if (addr.length() == 0){
			checkError.setError(12);
			checkError.setRstr(valu);
			return checkError;
		}

		// アカウント部
		String user = value.substring(0, atpos);
		if (user.length() == 0){
			checkError.setError(12);
			checkError.setRstr(valu);
			return checkError;
		}

		// アドレス部チェック
		if (addr.indexOf("@") >= 0 || addr.indexOf(".") < 0){
			checkError.setError(12);
			checkError.setRstr(valu);
			return checkError;
		}

		checkError.setRstr(value);
		return checkError;
	}
}
