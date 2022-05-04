package jp.co.yobrain.util.form.conf;

import jp.co.yobrain.util.form.*;

/**
* アルファベットチェック用クラス
*
*/

import java.io.*;

public class FormCheckAlphabet implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckAlphabet(){
	}

	/**
	* 全角アルファベットを半角アルファベットに変換及びアルファベットか判定
	* @return	CheckError CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	synchronized public CheckError convertCheck(String valu, CheckError checkError){
		StringBuffer sb = new StringBuffer( valu );
		StringBuffer msb = new StringBuffer();
		char c;

		for( int i = 0; i < sb.length() ; i++ ) {
			c = sb.charAt( i );
                        
                        if( c == 0xfffd){
                            checkError.setError(8);
                            checkError.setRstr(valu);
                            return checkError;
			}

			//全角アルファベット=>半角アルファベット変換
			if((0xff21 <= c && c <= 0xff3a) || (0xff41 <= c && c <= 0xff5a)) {

				msb.append(ALPHABET.charAt( c - 0xff20));

			//半角アルファベット
			} else if((0x0041 <= c && c <= 0x005a) || (0x0061 <= c && c <= 0x007a)){

				msb.append(c);

			//アルファベット以外
			} else {
				checkError.setError(31);
				checkError.setRstr(valu);
				return checkError;
			}

		}

		checkError.setRstr(msb.toString());
		return checkError;
	}

}