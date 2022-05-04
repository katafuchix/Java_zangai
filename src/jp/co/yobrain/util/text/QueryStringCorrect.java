package jp.co.yobrain.util.text;

import java.io.*;
import java.util.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class QueryStringCorrect {
	/**
	 * タグにはさまれた文字列の変更
	 * 
	 */
	public static final String modifyStr( String target, String base, String modifyed ){
		try{
			if( base.indexOf( "<" + target + ">") != -1 ){
			base = base.substring( 0, base.indexOf( "<" + target + ">") )
					 + "<" + target +">" + modifyed
					 + base.substring( base.indexOf( "</" + target + ">" ));
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return base;
	}
    /**
     * * &を含むSELECT文はNG
     */
    public static boolean isCorrectFInd( String str ){
        if(( str.indexOf( '*' ) == -1 )&&( str.indexOf( '&' ) == -1 )&&( str.indexOf( '%' ) == -1 )){
            return true;
        }
        return false;
    }
    /**
     * 「'」 を 「''」に変換
     * @return 	String	変更データ
     * @param	String str
     */
    public static String convQuotation( String str ){
        String res = "";
        String letters = "&";
        try{
            StringBuffer sb = new StringBuffer();
            for( int i=0; i<str.length(); i++){
                char point = str.charAt( i );
                if(contains( point, letters)){
                    sb.append( "||\'" );
                    sb.append( point );
                    sb.append( "\'||" );
                }else{
                    sb.append( point );
                    if( point == '\'' ){
                        sb.append( point );
                    }
                }
            }
            res = sb.toString();
        }catch(Exception ex){  }
        return res;
    }
    private static boolean contains( char c , String letters){
        boolean flg;
        flg = false;
        for ( int j = 0; j < letters.length(); j ++ ){
            if ( c == letters.charAt(j) ){ 	flg = true;	break;    }
        }
        if ( !flg ) return false;
        return true;
    }
    /**
     * 「'」 を 「''」に変換
     * @return 	String	変更データ
     * @param	String str
     */
    public static String convQuotationNeo( String str ){
        String reStr = "";
        String sq = "\'";
        String dq = "\"";
        String aq = "&";
        try{
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<str.length(); i++){
                if( str.substring(i, i+1).equals(sq) ){ sb.append( sq );  sb.append( sq );
                }else if( str.substring(i, i+1).equals(aq) ){
                    sb.append(dq);  sb.append(aq); sb.append(dq);
                }else{  sb.append( str.substring(i, i+1) );    }
            }
            reStr =  sb.toString();
        }catch(Exception e){    e.printStackTrace();    }
        return reStr;
    }
    public static String convQuotationReover( String str ){
        String reStr = "";
        String sq = "\'";
        String dq = "\"";
        String aq = "&";
        try{
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<str.length(); i++){
                if( str.substring(i, i+1).equals(sq) ){ sb.append( sq );  sb.append( sq );
                }else if( str.substring(i, i+1).equals(aq) ){
                    sb.append(dq);  sb.append(aq); sb.append(dq);
                }else{  sb.append( str.substring(i, i+1) );    }
            }
            reStr =  sb.toString();
        }catch(Exception e){    e.printStackTrace();    }
        return reStr;
    }
    public static String convQuotationReoverReverse( String str ){
        String reStr = "";
        String dq = "\"";
        try{
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<str.length(); i++){
                if( !str.substring(i, i+1).equals(dq) ){
                    sb.append( str.substring(i, i+1) );
                }
            }
            reStr =  sb.toString();
        }catch(Exception e){    e.printStackTrace();    }
        return reStr;
    }

}
