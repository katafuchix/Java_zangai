/*
 * Topdestination.java
 *
 * Created on 2002/04/08, 0:26
 */

package jp.co.lastminute.common.searchCondition;

/**
 *
 * @author  skondo
 * @version 
 */
public class Topdestination {
    private int countrycd;
    private int citycd;
    private String fromdate;
    private String todate;
    private int catid;
    private int subcatid;
    private int pax;
    
    /** Creates new Topdestination */
    public Topdestination() {
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
