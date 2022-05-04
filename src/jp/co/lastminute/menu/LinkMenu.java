/*
 * LinkMenu.java
 *
 * Created on 2002/04/21, 19:10
 */

package jp.co.lastminute.menu;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author  skondo
 * @version 
 */
public final class LinkMenu implements Serializable {
    public static String createLinkMenu(HashMap category, String key, String scoup){
        Menu menu = (Menu)category.get(key);
        if(menu.getCatid().equals(scoup)){
            return menu.getTitle();
        }else{
            return "<A HREF='" + menu.getUrl() + "?CATID=" + menu.getCatid() + "' target=_parent>" + menu.getTitle() + "</A>";
        }
    }
    public static String createLinkMenu(HashMap category, String key, String scoup, String cls){
        Menu menu = (Menu)category.get(key);
        if(menu.getCatid().equals(scoup)){
            return menu.getTitle();
        }else{
            return "<A HREF='" + menu.getUrl() + "?CATID=" + menu.getCatid() + "' target=_parent CLASS='" + cls + "'>" + menu.getTitle() + "</A>";
        }
    }
}
