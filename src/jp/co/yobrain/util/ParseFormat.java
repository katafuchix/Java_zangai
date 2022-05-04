/*
 * ParseFormat.java
 *
 * Created on 2002/04/14, 22:42
 */

package jp.co.yobrain.util;

import java.io.*;
import java.util.*;

public class ParseFormat{

	/**
         * 0詰め処理を行う
         * @param String 対象文字列
         * @param int 桁数
         */
	public static String ZeroUP(String str, int n){
		String reStr =  str;
		for(int i=0; i < n - str.length(); i++){	reStr = "0" + reStr;	}
		return reStr;
	}
        /**
         * 0詰め処理を行う
         * @param int 数字
         * @param int 桁数
         */
	public static String ZeroUP(int num, int n){
		String reStr = Integer.toString(num);
		for(int i=0; i < n - reStr.length(); i++){	reStr = "0" + reStr;	}
		return reStr;
	}
      /**
       * データベース用に文字列変換を変換する。
       * @param String 対象文字列
       * 
       */
      public static String toggleWord(String str){
                    String letters = "'&";
                    String reStr = "";
                    int len = str.length();
              char c;
              for ( int i = 0; i < len; i ++ ){
                c = str.charAt( i );
                if(contains(c, letters)){
                    reStr += "||\'" + c + "\'||" ;
                            }else{
                                    reStr += c;
                            }
                    }
		return reStr;
	}
	public static boolean contains(String str , String letters){
            int len = str.length();
            char c;
            boolean flg;
            for ( int i = 0; i < len; i ++ ){
                                flg = false;
                    c = str.charAt( i );
                    for ( int j = 0; j < letters.length(); j ++ ){
                                        if ( c == letters.charAt(j) ){ 	flg = true;	break;	}
                                }
                                if ( !flg ) return false;
                        }
                  return true;
          }
          public static boolean contains( char c , String letters){
                        boolean flg;
                        flg = false;
                  for ( int j = 0; j < letters.length(); j ++ ){
                                if ( c == letters.charAt(j) ){ 	flg = true;	break;    }
                        }
                        if ( !flg ) return false;
                  return true;
          }
  	/**
         * 通貨表示を行う
         * @param String 変換文字列
         */
	public static String ToPriceFormat(String str){
		String reStr = "";
		try{
			for(int i=str.length(); i > 0; i--){
				if(((str.length() - i)%3 == 0)&&(i != str.length())){
					reStr = str.charAt(i - 1) + "," + reStr;
				}else{	reStr = str.charAt(i - 1) + reStr;	}
			}
		return reStr;
		}catch(Exception e){	return "";		}
	}
	/**
         * 通貨表示を行う
         * @param int 変換文字列
         */
	public static String ToPriceFormat(int num){
		String reStr = "";
		String str = "";
		try{
			str = Integer.toString( num );
			for(int i=str.length(); i > 0; i--){
				if(((str.length() - i)%3 == 0)&&(i != str.length())){
					reStr = str.charAt(i - 1) + "," + reStr;
				}else{	reStr = str.charAt(i - 1) + reStr;	}
			}
		return reStr;
		}catch(Exception e){	return "";		}
	}
	/**
         * 値段表示のデータを整数に戻す
         * @param String 対象文字列
         */
	public static int PriceToInt(String str){
		String reStr = "";
		int len = str.length();
		String letters = "0123456789.";
		boolean flg;
		for(int i=0; i<len; i++){
			char c = str.charAt( i );
			flg = false;
			for ( int j = 0; j < letters.length(); j ++ ){
				if ( c == letters.charAt(j) ){
			    	flg = true;
			        break;
			    }
			}
			if(flg)	reStr += c;
		}
		try{	return Integer.parseInt(reStr);
		}catch(Exception e){	return 0;	}
	}

}
