package jp.co.lastminute.common.searchCondition;

import java.io.*;
import java.util.*;

public class Sector implements Serializable {
    
    String flght_no;
    
    String dep_citycd_;
    
    String dep_cityname_;
    
    int dep_time_;
    
    String arv_citycd_;
    
    String arv_cityname_;
    
    int arr_time_;
    
    int sector_type_;
    
    String carear_;
    
    String cabinclass_;
    
    int adult_;
    
    int child_;
    
    int infant_;
    
    int status_;
    
    Sector(String flght_no, String dep_citycd, String dep_cityname, int dep_time, String arv_citycd, String arv_cityname, int arr_time, int sector_type, String carear_, String cabinclass_, int adult, int child, int infant, int status) {
        this.flght_no = flght_no;
        this.dep_citycd_ = dep_citycd;
        this.dep_cityname_ = dep_cityname;
        this.dep_time_ = dep_time;
        this.arv_citycd_ = arv_citycd;
        this.arv_cityname_ = arv_cityname;
        this.arr_time_ = arr_time;
        this.sector_type_ = sector_type;
        this.carear_ = carear_;
        this.cabinclass_ = cabinclass_;
        this.adult_ = adult;
        this.child_ = child;
        this.infant_= infant;
        this.status_ = status;
    }
    
    /** プロパティ arr_time_ の取得メソッド。
     * @return プロパティ arr_time_ の値。
     */
    public int getArr_time_() {
        return arr_time_;
    }
    
    /** プロパティ arr_time_ の設定メソッド。
     * @param arr_time_ プロパティ arr_time_ の新しい値。
     */
    public void setArr_time_(int arr_time_) {
        this.arr_time_ = arr_time_;
    }
    
    /** プロパティ arv_citycd_ の取得メソッド。
     * @return プロパティ arv_citycd_ の値。
     */
    public String getArv_citycd_() {
        return arv_citycd_;
    }
    
    /** プロパティ dep_cityname_ の取得メソッド。
     * @return プロパティ dep_cityname_ の値。
     */
    public String getArv_cityname_() {
        return arv_cityname_;
    }
    
    /** プロパティ dep_cityname_ の設定メソッド。
     * @param dep_cityname_ プロパティ dep_cityname_ の新しい値。
     */
    public void setArv_cityname_(String arv_cityname_) {
        this.arv_cityname_ = arv_cityname_;
    }
    
    /** プロパティ arv_citycd_ の設定メソッド。
     * @param arv_citycd_ プロパティ arv_citycd_ の新しい値。
     */
    public void setArv_citycd_(String arv_citycd_) {
        this.arv_citycd_ = arv_citycd_;
    }
    
    /** プロパティ cabinclass_ の取得メソッド。
     * @return プロパティ cabinclass_ の値。
     */
    public String getCabinclass_() {
        return cabinclass_;
    }
    
    /** プロパティ cabinclass_ の設定メソッド。
     * @param cabinclass_ プロパティ cabinclass_ の新しい値。
     */
    public void setCabinclass_(String cabinclass_) {
        this.cabinclass_ = cabinclass_;
    }
    
    /** プロパティ dep_cityanme_ の取得メソッド。
     * @return プロパティ dep_cityanme_ の値。
     */
    public String getDep_cityname_() {
        return dep_cityname_;
    }
    
    /** プロパティ dep_cityanme_ の設定メソッド。
     * @param dep_cityanme_ プロパティ dep_cityanme_ の新しい値。
     */
    public void setDep_cityname_(String dep_cityanme_) {
        this.dep_cityname_ = dep_cityname_;
    }
    
    /** プロパティ dep_citycd_ の取得メソッド。
     * @return プロパティ dep_citycd_ の値。
     */
    public String getDep_citycd_() {
        return dep_citycd_;
    }
    
    /** プロパティ dep_citycd_ の設定メソッド。
     * @param dep_citycd_ プロパティ dep_citycd_ の新しい値。
     */
    public void setDep_citycd_(String dep_citycd_) {
        this.dep_citycd_ = dep_citycd_;
    }
    
    /** プロパティ dep_time_ の取得メソッド。
     * @return プロパティ dep_time_ の値。
     */
    public int getDep_time_() {
        return dep_time_;
    }
    
    /** プロパティ dep_time_ の設定メソッド。
     * @param dep_time_ プロパティ dep_time_ の新しい値。
     */
    public void setDep_time_(int dep_time_) {
        this.dep_time_ = dep_time_;
    }
    
    /** プロパティ sector_type_ の取得メソッド。
     * @return プロパティ sector_type_ の値。
     */
    public int getSector_type_() {
        return sector_type_;
    }
    
    /** プロパティ sector_type_ の設定メソッド。
     * @param sector_type_ プロパティ sector_type_ の新しい値。
     */
    public void setSector_type_(int sector_type_) {
        this.sector_type_ = sector_type_;
    }
    
    /** プロパティ adult_ の取得メソッド。
     * @return プロパティ adult_ の値。
     */
    public int getAdult_() {
        return adult_;
    }
    
    /** プロパティ adult_ の設定メソッド。
     * @param adult_ プロパティ adult_ の新しい値。
     */
    public void setAdult_(int adult_) {
        this.adult_ = adult_;
    }
    
    /** プロパティ child_ の取得メソッド。
     * @return プロパティ child_ の値。
     */
    public int getChild_() {
        return child_;
    }
    
    /** プロパティ child_ の設定メソッド。
     * @param child_ プロパティ child_ の新しい値。
     */
    public void setChild_(int child_) {
        this.child_ = child_;
    }
    
    /** プロパティ infant_ の取得メソッド。
     * @return プロパティ infant_ の値。
     */
    public int getInfant_() {
        return infant_;
    }
    
    /** プロパティ infant_ の設定メソッド。
     * @param infant_ プロパティ infant_ の新しい値。
     */
    public void setInfant_(int infant_) {
        this.infant_ = infant_;
    }
    
    /** プロパティ status_ の取得メソッド。
     * @return プロパティ status_ の値。
     */
    public int getStatus_() {
        return status_;
    }
    
    /** プロパティ status_の設定メソッド。
     * @param status_ プロパティ infant_ の新しい値。
     */
    public void setStatus_(int status_) {
        this.status_ = status_;
    }
    
    /** プロパティ infant_ の取得メソッド。
     * @return プロパティ infant_ の値。
     */
    public String getCarear_() {
        return carear_;
    }
    
    /** プロパティ infant_ の設定メソッド。
     * @param infant_ プロパティ infant_ の新しい値。
     */
    public void setCarear_(String carear_) {
        this.carear_ = carear_;
    }
    
    /** プロパティ flght_no の取得メソッド。
     * @return プロパティ flght_no の値。
     */
    public String getFlght_no() {
        return flght_no;
    }
    
    /** プロパティ flght_no の設定メソッド。
     * @param flght_no プロパティ flght_no の新しい値。
     */
    public void setFlght_no(String flght_no) {
        this.flght_no = flght_no;
    }
    
}

