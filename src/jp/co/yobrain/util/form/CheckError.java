package jp.co.yobrain.util.form;

import java.io.*;

/**
* Formチェック用Bean<BR>
*
*/

public class CheckError implements Serializable{

	/** チェック結果文字列 */
	private String str = "";
	/** エラーNo */
	private int eNumber = 0;
	/** 引数なしコンストラクタ */
	public CheckError(){
	}
	/**
	* エラーチェック後のStringを返します
	* @return	String	エラーチェック後のString
	*/
	synchronized public String getRstr(){
		return str;
	}

	/**
	* エラーNoを返します
	* @return	int	エラーNomber
	*/
	synchronized public int getError(){
		return eNumber;
	}

	/**
	* エラーチェック後のStringをセットします
	* @param	s	エラーチェック後のString
	*/
	synchronized public void setRstr(String s){
		str = s;
	}

	/**
	* エラーNoをセットします
	* @param	i	エラーNomber
	*/
	synchronized public void setError(int i){
		eNumber = i;
	}

}