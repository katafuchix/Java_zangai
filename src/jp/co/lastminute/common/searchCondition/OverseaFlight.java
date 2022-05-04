/*
 * OverseaFlight.java
 *
 * Created on 2002/04/08, 0:20
 */

package jp.co.lastminute.common.searchCondition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import java.io.*;
import java.util.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class OverseaFlight extends ActionForm implements Serializable {
    public static final int sec_dep_ = 0;
    public static final int sec_con_ = 1;
    public static final int sec_oj_ = 2;
    
    protected String agentCode;
    protected ArrayList Segment;
    protected String citycd;
    protected String countrycd;
    protected String fromcity;
    protected String carear;
    protected int pax;
    protected int adult;
    protected int child;
    protected int infant;
    protected int depdate;
    protected int arridate;
    protected int deptime;
    protected int arritime;
    int status; 
    ////////////////////////////
    protected int branch_id = 1;
    protected String flex_id;                 
    public static final String[] BRANCH = {"", "TYO", "NGO", "KIX", "FUK"};     //add by skonbdo
    ////////////////////////
    /** Creates new OverseaFlight */
    public OverseaFlight() {
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request){
        
       Segment = new ArrayList();
       agentCode = "";
       citycd = "";
       countrycd = "";
       fromcity = "";
       carear = "";
       pax = 0;
       adult = 0;
       child = 0;
       infant = 0;
       depdate = 0;
       arridate = 0;
       deptime = 0;
       arritime = 0;
       status = 0;
       branch_id = 1;
       flex_id = "";
    }
    public void setAgentCode(String agentCode){
        this.agentCode = agentCode;
    }
    public String getAgentCode(){
        return this.agentCode;
    }
    /**
     * 
     */
    public void addSector( Sector sector_){
        this.Segment.add( sector_ );
    }
    /**
     *
     */
    public void removeSector( int idx){
        this.Segment.remove( idx );
    }
    /**
     *
     */
    public void setSector( Sector sector_, int idx ){
        this.Segment.set( idx, sector_ );
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
    
    /** プロパティ arridate の取得メソッド。
     * @return プロパティ arridate の値。
     */
    public int getArridate() {
        return this.arridate;
    }    
    
    /** プロパティ arridate の設定メソッド。
     * @param arridate プロパティ arridate の新しい値。
     */
    public void setArridate(int arridate) {
        this.arridate = arridate;
    }
    
    /** プロパティ arritime の取得メソッド。
     * @return プロパティ arritime の値。
     */
    public int getArritime() {
        return arritime;
    }
    
    /** プロパティ arritime の設定メソッド。
     * @param arritime プロパティ arritime の新しい値。
     */
    public void setArritime(int arritime) {
        this.arritime = arritime;
    }
    
    /** プロパティ carear の取得メソッド。
     * @return プロパティ carear の値。
     */
    public String getCarear() {
        return carear;
    }
    
    /** プロパティ carear の設定メソッド。
     * @param carear プロパティ carear の新しい値。
     */
    public void setCarear(String carear) {
        this.carear = carear;
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
    public String getCitycd() {
        return citycd;
    }
    
    /** プロパティ citycd の設定メソッド。
     * @param citycd プロパティ citycd の新しい値。
     */
    public void setCitycd(String citycd) {
        this.citycd = citycd;
    }
    
    /** プロパティ countrycd の取得メソッド。
     * @return プロパティ countrycd の値。
     */
    public String getCountrycd() {
        return countrycd;
    }
    
    /** プロパティ countrycd の設定メソッド。
     * @param countrycd プロパティ countrycd の新しい値。
     */
    public void setCountrycd(String countrycd) {
        this.countrycd = countrycd;
    }
    
    /** プロパティ depdate の取得メソッド。
     * @return プロパティ depdate の値。
     */
    public int getDepdate() {
        return depdate;
    }
    
    /** プロパティ depdate の設定メソッド。
     * @param depdate プロパティ depdate の新しい値。
     */
    public void setDepdate(int depdate) {
        this.depdate = depdate;
    }
    
    /** プロパティ deptime の取得メソッド。
     * @return プロパティ deptime の値。
     */
    public int getDeptime() {
        return deptime;
    }
    
    /** プロパティ deptime の設定メソッド。
     * @param deptime プロパティ deptime の新しい値。
     */
    public void setDeptime(int deptime) {
        this.deptime = deptime;
    }
    
    /** プロパティ fromcity の取得メソッド。
     * @return プロパティ fromcity の値。
     */
    public String getFromcity() {
        return fromcity;
    }
    
    /** プロパティ fromcity の設定メソッド。
     * @param fromcity プロパティ fromcity の新しい値。
     */
    public void setFromcity(String fromcity) {
        this.fromcity = fromcity;
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
    
    /** プロパティ Segment の取得メソッド。
     * @return プロパティ Segment の値。
     */
    public ArrayList getSegment() {
        return Segment;
    }
    
    /** プロパティ Segment の設定メソッド。
     * @param Segment プロパティ Segment の新しい値。
     */
    public void setSegment(ArrayList Segment) {
        this.Segment = Segment;
    }
    
    /** プロパティ status の取得メソッド。
     * @return プロパティ status の値。
     */
    public int getStatus() {
        return status;
    }
    
    /** プロパティ status の設定メソッド。
     * @param status プロパティ status の新しい値。
     */
    public void setStatus(int status) {
        this.status = status;
    }
    ////////////////////////////////////////////////
    /** プロパティ flex_id の取得メソッド。
     * @return プロパティ flex_id の値。
     */
    public String getFlex_id() {
        return flex_id;
    }
    
    /** プロパティ flex_id の設定メソッド。
     * @param flex_id プロパティ flex_id の新しい値。
     */
    public void setFlex_id(String flex_id) {
        this.flex_id = flex_id;
    }
    
    /** プロパティ branch_id の取得メソッド。
     * @return プロパティ branch_id の値。
     */
    public int getBranch_id() {
        return branch_id;
    }
    
    /** プロパティ branch_id の設定メソッド。
     * @param branch_id プロパティ branch_id の新しい値。
     */
    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }
    //////////////////////////////////////add by skonbdo
}
