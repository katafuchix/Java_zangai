package jp.co.lastminute.maintenance.util;

import java.util.*;
import java.io.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class WebUtil {
		/**
		 * HaveAltの色取得
		 */
		public static String getWindowListHavaltColor( String str ){
			if( str.equals( "0" )){
				return "blue";
			}
			return "red";
		}
		/**
		 * エラーカラーの取得
		 */
		public static String getErrorColor( int error_flg ){
			if( error_flg == 0 ){
				return "bgcolor='#CCCCCC'";
			}
			return "bgcolor='#FFCC33'";
		}
		/**
		* A method to return JavaScriptString to show alert
		* Dialog
		* @param message to show
		* @return JavaScript String
		*/
		public static String getAlert(String msg) {
			return "<script language=JavaScript>\nalert(\'" + msg + "\');\n</script>\n";
		}
		/**
		 * 
		 */
		public static String getComboByList( List lists, String target ){
			try{
				StringBuffer sb = new StringBuffer();
				for(int i=0; i<lists.size(); i++){
					ComboKey combo = (ComboKey)lists.get( i );
					if( combo.code.equals(target )){
						sb.append( "<option value='" + combo.code + "' selected>" );
					}else{
						sb.append( "<option value='" + combo.code + "'>" );
					}
					sb.append( combo.value );
					sb.append( "</option>\n" );
				}
			return sb.toString();
			}catch(Exception ex){	ex.printStackTrace();	}
			return "";
		}
		public static String getComboByList( String[] lists, String[] value, String target ){
			try{
				StringBuffer sb = new StringBuffer();
				for(int i=0; i<lists.length; i++){
					if( lists[i].equals(target )){
						sb.append( "<option value='" + lists[i] + "' selected>" );
					}else{
						sb.append( "<option value='" + lists[i] + "'>" );
					}
					sb.append( value[i] );
					sb.append( "</option>\n" );
				}
			return sb.toString();
			}catch(Exception ex){	ex.printStackTrace();	}
			return "";
		}
		/**
		 * 
		 */
		public static String getStopOption( String condition ){
        	String reStr = "";
        	reStr = "<option value='0'"+ makeOptionValue(condition, "0") + ">表示</option>\n"
                　+ "<option value='1'"+ makeOptionValue(condition, "1") + ">非表示</option>"
                　+ "<option value='9'"+ makeOptionValue(condition, "9") + ">削除</option>";
            return reStr;
		}
		/**
		 * 
		 */
		public static String getStopOptionStr( String condition ){
			if( condition.equals( "0" )){
				return "表示";
			}
			return "非表示";
		}
		/**
		 * <pre>
		 * &lt;option <%= WebUtil.makeOptionValue("0","0") %>&gt;
		 */
		public static String makeOptionValue(String condition, String value) {
			if (condition.equals(value))
				return " value='" + value + "' selected ";
			else
				return " value='" + value + "'";
		}
		public static String getWarnString(
			String className,
			String condition,
			String value) {
			return getWarnString(className, condition, value, null, null);
		}

		public static String getWarnString(
			String className,
			String condition,
			String value,
			String trueReplaceWord,
			String falseReplaceWord) {
			boolean isWarn = condition.equals(value);
			if (isWarn) {
				if (trueReplaceWord == null)
					return "<span class='"+ className+ "'>"+ value	+ "</span>";
				else
					return "<span class='"	+ className+ "'>"+ trueReplaceWord+ "</span>";
			} else {
				if (falseReplaceWord == null)
					return value;
				else
					return falseReplaceWord;
			}
		}

		public static void main(String args[]) {
			System.out.println(
			WebUtil.getWarnString("red", "0", "0"));
		}
		
	}
