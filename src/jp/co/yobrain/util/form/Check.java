package jp.co.yobrain.util.form;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.conf.*;

/**
* Formチェック用CLASS
*
*/

public class Check implements Serializable{
        private CheckError check;
        //スタティックパラメータ
        public static final int EORROR_0 = 0;
        public static final int EORROR_1 = 1;
        public static final int EORROR_2 = 2;
        public static final int EORROR_3 = 3;
        public static final int EORROR_4 = 4;
        public static final int EORROR_5 = 5;
        public static final int EORROR_6 = 6;
        public static final int EORROR_7 = 7;
        public static final int EORROR_8 = 8;
        public static final int EORROR_9 = 9;
        public static final int EORROR_10 = 10;
                
        /**
         * コンストラクター
         */
        public Check(){
            check = new CheckError();
        }
        /**
	* コンバート及びエラーチェック
	*
	* @return	CheckError	CheckErrorオブジェクト
	* @param	string		チェックする文字列
	* @param	type		チェックタイプ
	* @param	flag		必須flag trueで必須
	*/
	synchronized public CheckError offSet(String string, int type, boolean flag){	
            try{
                //Nullチェック&タイプチェック
		if( string == null || jp.co.yobrain.util.form.conf.FormCheck.MAX <= type){		
                    check.setError(35);
                    return check;
		}
		//必須チェック
		if(flag && string.length() < 1){
                    check.setError(37);
                    return check;
		} else if(!flag && string.length() == 0){
                    return new CheckError();
		}
		return setCheckList( type , string, check);
            }catch(Exception e){
                check.setError(999);
		check.setRstr(e.getMessage());
		return check;
            }
	}

	/**
	* エラーチェック(文字列長さチェック付)及びコンバート
	*
	* @return	CheckError	CheckErrorオブジェクト
	* @param	string		チェックする文字列
	* @param	type		チェックタイプ
	* @param	flag		必須flag trueで必須
	* @param	leng1		最小文字列数
	* @param	leng2		最大文字列数
	*/
	synchronized public CheckError offSet(String string, int type,  boolean flag, int leng1, int leng2){
            int stleng;
            try{
                //Nullチェック&タイプチェック
		if( string == null || jp.co.yobrain.util.form.conf.FormCheck.MAX <= type){
                    check.setError(35);
                    return check;
		}

		//コンバート及びエラーチェック
		check = offSet(string, type, flag);
		//すでにエラーあり
		if(check.getError() > 0)    return check;

		//コンバート後の文字列数
		stleng = check.getRstr().length();

		//文字列範囲チェック(長さ)
		if(stleng < leng1 || stleng > leng2){
                    check.setError(36);
                    return check;
		}

		return check;

            }catch(Exception e){
                check.setError(999);
		check.setRstr(e.getMessage());
		return check;
            }
	}

	/**
	* エラーチェック(文字列長さ&バイトチェック付)及びコンバート
	*
	* @return	CheckError	CheckErrorオブジェクト
	* @param	string		チェックする文字列
	* @param	type		チェックタイプ
	* @param	flag		必須flag trueで必須
	* @param	leng1		最小文字列数
	* @param	leng2		最大文字列数
	* @param	maxByte		最大バイト数
	*/
	synchronized public CheckError offSet(String string, int type,  boolean flag, int leng1, int leng2, int maxByte){
            int stleng;

            try{
                //Nullチェック&タイプチェック
		if( string == null || jp.co.yobrain.util.form.conf.FormCheck.MAX <= type){
                    check.setError(35);
                    return check;
		}

		//コンバート及びエラーチェック
		check = offSet(string, type, flag);

		//すでにエラーあり
		if(check.getError() > 0)    return check;

		//コンバート後の文字列数
		stleng = check.getRstr().length();

		//文字列範囲チェック(長さ)
		if(stleng < leng1 || stleng > leng2){
                    check.setError(36);
                    return check;
		}

		byte[] b = check.getRstr().getBytes();

		if(b.length > maxByte){
                    check.setError(32);
                    return check;
		}
		return check;

            }catch(Exception e){
                check.setError(999);
		check.setRstr(e.getMessage());
		return check;
            }
	}

	/**
	* エラーチェック(文字列長さ&バイト長&小数部チェック付)及びコンバート
	*
	* @return	CheckError	CheckErrorオブジェクト
	* @param	string		チェックする文字列
	* @param	type		チェックタイプ
	* @param	flag		必須flag trueで必須
	* @param	leng1		最小文字列数
	* @param	leng2		最大文字列数
	* @param	maxByte		最大バイト数
	* @param	maxDec		最大少数

	*/
	synchronized public CheckError offSet(String string, int type,  boolean flag, int leng1, int leng2, int maxByte, int maxDec){
            if( type != jp.co.yobrain.util.form.conf.FormCheck.TYPE_FLOAT_ ){
                return offSet(string, type,  flag, leng1, leng2, maxByte);
            }
                        
            int stleng;
            try{
			//Nullチェック&タイプチェック
		if( string == null || jp.co.yobrain.util.form.conf.FormCheck.MAX <= type){
                    check.setError(35);
                    return check;
		}

		//コンバート及びエラーチェック
		check = offSet(string, type, flag);

		//すでにエラーあり
		if(check.getError() > 0)    return check;

		//コンバート後の文字列数
		stleng = check.getRstr().length();

		//文字列範囲チェック(長さ)
		if(stleng < leng1 || stleng > leng2){
                    check.setError(36);
                    return check;
		}

		byte[] b = check.getRstr().getBytes();

		if(b.length > maxByte){
                    check.setError(32);
                    return check;
		}

		int in = check.getRstr().indexOf(".");
		if(in < 0)
                    return check;

                    if((stleng - ( in + 1)) > maxDec){
                    check.setError(33);
                    return check;
		}
		return check;

            }catch(Exception e){
                check.setError(999);
		check.setRstr(e.getMessage());
		return check;
            }
	}

	/**
	* エラーチェック(バイト長チェック付)及びコンバート
	*
	* @return	CheckError	CheckErrorオブジェクト
	* @param	string		チェックする文字列
	* @param	type		チェックタイプ
	* @param	flag		必須flag trueで必須
	* @param	leng		最大バイト数
	*/
	synchronized public CheckError offSetByte(String string, int type, boolean flag, int leng){
            try{
                //Nullチェック&タイプチェック
		if( string == null || jp.co.yobrain.util.form.conf.FormCheck.MAX <= type){
                    check.setError(35);
                    return check;
		}
                //コンバート及びエラーチェック
		check = offSet(string, type, flag);
		//すでにエラーあり
		if(check.getError() > 0)    return check;

		byte[] b = check.getRstr().getBytes();

		if(b.length > leng){
                    check.setError(32);
                    return check;
		}
		return check;

            }catch(Exception e){
		check = new CheckError();
		check.setError(999);
		check.setRstr(e.getMessage());
		return check;
            }
	}

	/**
	* エラーチェック(小数部の最大桁数チェック付)及びコンバート
	*
	* @return	CheckError	CheckErrorオブジェクト
	* @param	string		チェックする文字列
	* @param	type		チェックタイプ
	* @param	flag		必須flag trueで必須
	* @param	leng		小数部の最大桁数
	*/
	synchronized public CheckError offSetDecimal(String string, int type, boolean flag, int leng){
            try{
                //Nullチェック&タイプチェック
		if( string == null || jp.co.yobrain.util.form.conf.FormCheck.MAX <= type){
                    check.setError(35);
                    return check;
		}

		//コンバート及びエラーチェック
		check = offSet(string, type, flag);

		//すでにエラーあり
		if(check.getError() > 0)    return check;

		int  in = check.getRstr().indexOf(".");
		if(in < 0)  return check;

		if((check.getRstr().length() - ( in + 1)) > leng){
                    check.setError(33);
                    return check;
		}
                return check;
            }catch(Exception e){
                check.setError(999);
		check.setRstr(e.getMessage());
		return check;
            }
	}
        /** チェックオブジェクトセット */
	public CheckError setCheckList(int type, String stirng, CheckError checkerror){
                switch (type){
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_NUMBER_:
                    return new FormCheckNumber().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_STRING_:
                    return new FormCheckFull().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_FLOAT_:              
                    return new FormCheckNumber2().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_DATE_:
                    return new FormCheckDate().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_EMAIL_:
                    return new FormCheckEmail().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_MONEY_:
                    return new FormCheckMoney().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_ALPHABET_:
                    return new FormCheckAlphabet().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_ALPHABETNUMBER_:
                    return new FormCheckAlphabetNumber().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_DATETIME_:
                    return new FormCheckDateTime().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_PHONE_:
                    return new FormCheckTelphone().convertCheck(stirng, checkerror);
                case jp.co.yobrain.util.form.conf.FormCheck.TYPE_TWOBYTE_:
                    return new FormCheckDoublebyte().convertCheck(stirng, checkerror);
                default:
                    return new FormCheckFull().convertCheck(stirng, checkerror);
		}
	}

}