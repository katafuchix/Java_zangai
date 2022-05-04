/*
 * CheckImage.java
 *
 * Created on 2002/04/08, 20:57
 */

package jp.co.yobrain.util.file;

import java.io.*;
import java.util.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class CheckImage {
    private static ResourceBundle resources;		
    private static String basedir;
    
	/*リソースファイルが存在する場合リソースを取得する*/
	static {
            try {
		resources   = ResourceBundle.getBundle("jp.co.yobrain.util.file.resource.FileSettrer", Locale.getDefault());
		basedir    = resources.getString("basedir");
	}catch (MissingResourceException mre){	resources = null;	}
	}
        
        /**チェックファイル後、イメージのリンクを出力
         * @param String imagefile
         * @param int width
         * @param int height
         * @param int border
         * @return を出力  
         */
         public static String getImgUrl(String imagefile, int width, int height, int border){
             try{
            File filename = new File(basedir + "/" + imagefile);
            if( filename.exists() ){
                return "<IMG SRC=" + imagefile + "\" WIDTH=\"" + width + "\" HEIGHT=\"" + height + "\" + BORDER=\"" + border + "\">";
            }
             }catch(Exception ex){
                ex.printStackTrace();
             }
            return "";
         }
         /**チェックファイル後、イメージのリンクを出力
         * @param String imagefile
         * @return を出力  
         */
         public static String getImgUrl(String imagefile ){
             try{
            File filename = new File(basedir + "/" + imagefile);
            if( filename.exists() ){
                return "<IMG SRC=" + imagefile + "\" BORDER=\"0\">";
            }
             }catch(Exception ex){
                ex.printStackTrace();
             }
            return "";
         }
}
