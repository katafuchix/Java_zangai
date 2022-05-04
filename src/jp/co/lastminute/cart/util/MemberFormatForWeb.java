package jp.co.lastminute.cart.util;

import java.io.Serializable;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class MemberFormatForWeb implements Serializable{

	/**
	 * セレクトボックス
	 */
	public static String getSelectBox( String name, String[] value, String[] option, String max, String min, String selected){
		String reStr = "";
		int maxint = 0;
		int minint = 0;
		int size = 0;
		try{
			maxint = Integer.parseInt( max );
			minint = Integer.parseInt( min );
			if( maxint > value.length ){	size = value.length;	}else{	size=maxint-1;	}
			StringBuffer stb = new StringBuffer();
			stb.append("<SELECT NAME=\"" + name + "\">\n");
			for(int i=minint; i<size; i++){
				stb.append("<OPTION VALUE=\"" + value[i] + "\"");
				if( value[i].equals(selected ) ){
					stb.append(" SELECTED"); 
				}
				stb.append(">" + option[i] + "\n");
			}			
			stb.append("</SELECT>\n");
			reStr = stb.toString();
		}catch(Exception ex ){}
		return reStr;
	}
	public static String getSelectBox( String name, String max, String min, String selected){
		return getSelectBox( name, pax, pax, max, min, selected );
	}
	/**
	 * 選択されたものを出力する。
	 */
	public static String getSelectedBox( String name, String[] value, String[] option, String max, String min, String selected ){
		String reStr = "";
		try{
			for(int i=0; i<value.length; i++){
				if( value[i].equals(selected ) ){
					return  value[i];
				}
			}
		}catch(Exception ex){}
		return reStr;
	}
	public static String getSelectedBox( String name, String max, String min, String selected ){
		return getSelectedBox( name, pax, pax, max, min, selected );	
	}
	private static final String[] pax = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};	
}
