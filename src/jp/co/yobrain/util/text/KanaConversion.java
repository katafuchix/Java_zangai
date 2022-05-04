/*
 * KanaConversion.java
 *
 * Created on 2002/11/01, 19:12
 */

package jp.co.yobrain.util.text;

import java.io.*;
import java.util.*;

/**
 * ひらがなをカタカナに変換する
 * @author  skondo
 */
public final class KanaConversion extends Hiragana_Katakana{
    
    /** Creates a new instance of KanaConversion */
//    public KanaConversion() {
//        initHiragana_katakana();
//    }
    /**
     * コンバージョン
     */
    public static String getConversionString( String string ){
        try{
            StringBuffer str = new StringBuffer();
            HashMap base = new Hiragana_Katakana().getKanaMapping();
            for(int i=0; i<string.length(); i++){
                String point = string.substring(i, i+1);
                Object obj = base.get( point );
                if( isNull( obj ) ){
                    str.append( (String) obj );
                }else{
                    str.append( point );
                }
            }
            return str.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    private static boolean isNull( Object obj ){
        if( obj != null ){
            return true;
        }else{
            return false;
        }
    }
}
