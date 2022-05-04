/*
 * Menu.java
 *
 * Created on 2002/04/21, 16:10
 */

package jp.co.lastminute.menu;

import java.io.Serializable;
/**
 *
 * @author  skondo
 * @version 
 */
public final class Menu implements Serializable{
    private String catid = null;
    private String title = null;
    private String url = null;

    /** プロパティ catid の取得メソッド。
     * @return プロパティ catid の値。
     */
    public String getCatid() {
        return catid;
    }
    
    /** プロパティ catid の設定メソッド。
     * @param catid プロパティ catid の新しい値。
     */
    public void setCatid(String catid) {
        this.catid = catid;
    }
    
    /** プロパティ title の取得メソッド。
     * @return プロパティ title の値。
     */
    public String getTitle() {
        return title;
    }
    
    /** プロパティ title の設定メソッド。
     * @param title プロパティ title の新しい値。
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /** プロパティ url の取得メソッド。
     * @return プロパティ url の値。
     */
    public String getUrl() {
        return url;
    }
    
    /** プロパティ url の設定メソッド。
     * @param url プロパティ url の新しい値。
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
