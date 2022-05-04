/*
 * Entertaiment.java
 *
 * Created on 2002/04/08, 0:33
 */

package jp.co.lastminute.common.searchCondition;

import java.io.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class Entertaiment implements Serializable {
    
    private int catid;
    private int subcatid;
    private int cityid;
    private int pax;
    private String fromdate;
    private String todate;
        
    /** Creates new Entertaiment */
    public Entertaiment() {
    }

    /** プロパティ catid の取得メソッド。
     * @return プロパティ catid の値。
     */
    public int getCatid() {
        return catid;
    }
    
    /** プロパティ catid の設定メソッド。
     * @param catid プロパティ catid の新しい値。
     */
    public void setCatid(int catid) {
        this.catid = catid;
    }
    
    /** プロパティ cityid の取得メソッド。
     * @return プロパティ cityid の値。
     */
    public int getCityid() {
        return cityid;
    }
    
    /** プロパティ cityid の設定メソッド。
     * @param cityid プロパティ cityid の新しい値。
     */
    public void setCityid(int cityid) {
        this.cityid = cityid;
    }
    
    /** プロパティ fromdate の取得メソッド。
     * @return プロパティ fromdate の値。
     */
    public String getFromdate() {
        return fromdate;
    }
    
    /** プロパティ fromdate の設定メソッド。
     * @param fromdate プロパティ fromdate の新しい値。
     */
    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }
    
    /** プロパティ pax の取得メソッド。
     * @return プロパティ pax の値。
     */
    public int getPax() {
        return pax;
    }
    
    /** プロパティ pax の設定メソッド。
     * @param pax プロパティ pax の新しい値。
     */
    public void setPax(int pax) {
        this.pax = pax;
    }
    
    /** プロパティ subcatid の取得メソッド。
     * @return プロパティ subcatid の値。
     */
    public int getSubcatid() {
        return subcatid;
    }
    
    /** プロパティ subcatid の設定メソッド。
     * @param subcatid プロパティ subcatid の新しい値。
     */
    public void setSubcatid(int subcatid) {
        this.subcatid = subcatid;
    }
    
    /** プロパティ todate の取得メソッド。
     * @return プロパティ todate の値。
     */
    public String getTodate() {
        return todate;
    }
    
    /** プロパティ todate の設定メソッド。
     * @param todate プロパティ todate の新しい値。
     */
    public void setTodate(String todate) {
        this.todate = todate;
    }
    
}
