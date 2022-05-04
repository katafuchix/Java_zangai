package jp.co.yobrain.util.form.conf;

import jp.co.yobrain.util.form.*;

/**
* アルファベットチェック用クラス
*
*/

import java.io.*;

public class FormCheckDoublebyte implements FormCheck{

	/**
	* 引数なしコンストラクタ
	*/
	public FormCheckDoublebyte(){
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
                        if( c > 127 ){
                            msb.append(c);
			//アルファベット以外
			} else {
                            checkError.setError(91);
                            checkError.setRstr(valu);
                            return checkError;
			}
		}
		checkError.setRstr(msb.toString());
		return checkError;
	}

}