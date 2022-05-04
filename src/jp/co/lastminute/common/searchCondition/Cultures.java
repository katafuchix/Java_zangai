/*
 * Cultures.java
 *
 * Created on 2002/04/08, 0:25
 */

package jp.co.lastminute.common.searchCondition;

import java.io.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class Cultures implements Serializable {
    private String fromdate;    //検索FROM
    private String todate;      //検索TO
    private int weekday;        //曜日
    private int member;         //人数
    private int sex;            //性別
    private int subcat;         //サブカテゴリー
    private int citycd;         //都市コード
    /** コンストラクタ */
    public Cultures(){
        this.fromdate = "";
        this.todate = "";
        this.citycd = 0;
        this.member = 0;
        this.sex = 0;
        this.subcat = 0;
        this.weekday = 0;
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
    
    /** プロパティ member の取得メソッド。
     * @return プロパティ member の値。
     */
    public int getMember() {
        return member;
    }
    
    /** プロパティ member の設定メソッド。
     * @param member プロパティ member の新しい値。
     */
    public void setMember(int member) {
        this.member = member;
    }
    
    /** プロパティ sex の取得メソッド。
     * @return プロパティ sex の値。
     */
    public int getSex() {
        return sex;
    }
    
    /** プロパティ sex の設定メソッド。
     * @param sex プロパティ sex の新しい値。
     */
    public void setSex(int sex) {
        this.sex = sex;
    }
    
    /** プロパティ subcat の取得メソッド。
     * @return プロパティ subcat の値。
     */
    public int getSubcat() {
        return subcat;
    }
    
    /** プロパティ subcat の設定メソッド。
     * @param subcat プロパティ subcat の新しい値。
     */
    public void setSubcat(int subcat) {
        this.subcat = subcat;
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
    
    /** プロパティ weekday の取得メソッド。
     * @return プロパティ weekday の値。
     */
    public int getWeekday() {
        return weekday;
    }
    
    /** プロパティ weekday の設定メソッド。
     * @param weekday プロパティ weekday の新しい値。
     */
    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
    
}
