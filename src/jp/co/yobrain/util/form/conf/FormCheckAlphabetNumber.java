package jp.co.yobrain.util.form.conf;

import jp.co.yobrain.util.form.*;

/**
* アルファベットチェック用クラス
*
*/

import java.io.*;

public class FormCheckAlphabetNumber implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckAlphabetNumber(){
	}


	/**
	* 全角英数字を半角英数字に変換及び英数字か判定<BR>
	* 英数字(アルファベット＆数字)
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

			//全角アルファベット
			if((0xff21 <= c && c <= 0xff3a) || (0xff41 <= c && c <= 0xff5a)) {
				//全角アルファベット=>半角アルファベット変換
				msb.append(ALPHABET.charAt( c - 0xff20));

			//半角アルファベット
			} else if((0x0041 <= c && c <= 0x005a) || (0x0061 <= c && c <= 0x007a)){

				msb.append(c);

			//全角数字
			} else if( 0xff10 <= c && c <= 0xff19 ) {
				//全角数字=>半角数字変換
				msb.append(NUMBER.charAt( c - 0xff0d));

			//半角数字
			} else if(0x0030 <= c && c <= 0x0039){

				msb.append(c);

			//英数字以外
			} else {
				checkError.setError(34);
				checkError.setRstr(valu);
				return checkError;
			}

		}
		checkError.setRstr(msb.toString());
		return checkError;
	}

}