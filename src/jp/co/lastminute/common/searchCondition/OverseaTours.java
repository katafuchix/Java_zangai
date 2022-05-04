/*
 * OverseaTours.java
 *
 * Created on 2002/04/08, 0:22
 */

package jp.co.lastminute.common.searchCondition;

import java.io.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class OverseaTours implements Serializable {
    private int catid;
    private int subcatid;
    private int areacd;
    private int countrycd;
    private int citycd;
    private int pax;
    private int adult;
    private int child;
    private int infant;
    private String fromdate;
    private String todate;
    private int depcitycd;
    
    /** Creates new OverseaTours */
    public OverseaTours() {
    }

    /** プロパティ adult の取得メソッド。
     * @return プロパティ adult の値。
     */
    public int getAdult() {
        return adult;
    }
    
    /** プロパティ adult の設定メソッド。
     * @param adult プロパティ adult の新しい値。
     */
    public void setAdult(int adult) {
        this.adult = adult;
    }
    
    /** プロパティ areacd の取得メソッド。
     * @return プロパティ areacd の値。
     */
    public int getAreacd() {
        return areacd;
    }
    
    /** プロパティ areacd の設定メソッド。
     * @param areacd プロパティ areacd の新しい値。
     */
    public void setAreacd(int areacd) {
        this.areacd = areacd;
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
    
    /** プロパティ child の取得メソッド。
     * @return プロパティ child の値。
     */
    public int getChild() {
        return child;
    }
    
    /** プロパティ child の設定メソッド。
     * @param child プロパティ child の新しい値。
     */
    public void setChild(int child) {
        this.child = child;
    }
    
    /** プロパティ citycd の取得メソッド。
     * @return プロパティ citycd の値。
     */
    public int getCitycd() {
        return citycd;
    }
    
    /** プロパティ citycd の設定メソッド。
     * @param citycd プロパティ citycd の新しい値。
     */
    public void setCitycd(int citycd) {
        this.citycd = citycd;
    }
    
    /** プロパティ countrycd の取得メソッド。
     * @return プロパティ countrycd の値。
     */
    public int getCountrycd() {
        return countrycd;
    }
    
    /** プロパティ countrycd の設定メソッド。
     * @param countrycd プロパティ countrycd の新しい値。
     */
    public void setCountrycd(int countrycd) {
        this.countrycd = countrycd;
    }
    
    /** プロパティ depcitycd の取得メソッド。
     * @return プロパティ depcitycd の値。
     */
    public int getDepcitycd() {
        return depcitycd;
    }
    
    /** プロパティ depcitycd の設定メソッド。
     * @param depcitycd プロパティ depcitycd の新しい値。
     */
    public void setDepcitycd(int depcitycd) {
        this.depcitycd = depcitycd;
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
    
    /** プロパティ infant の取得メソッド。
     * @return プロパティ infant の値。
     */
    public int getInfant() {
        return infant;
    }
    
    /** プロパティ infant の設定メソッド。
     * @param infant プロパティ infant の新しい値。
     */
    public void setInfant(int infant) {
        this.infant = infant;
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
