/*
 * DomesticHotel.java
 *
 * Created on 2002/04/08, 0:21
 */

package jp.co.lastminute.common.searchCondition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;


import java.io.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class DomesticHotel
    extends ActionForm
    implements Serializable

{
    //FOR SEARCH
    
    private int catid;
    private String[] state_cd;
    private int city_cd;
    private int nights;
    private int numberOfrooms;
    private int numberOfadult;
    private int numberOfkids;
    private String category;
    private int price;
    private int from_price;
    private int to_price;
    private String SYO_TYPE;
    private String xmlString;
    private int scatid;
    private String agt_cd;
    private String product_id;
    private String sup_id;
    private int checkoutdate;
    private int checkindate;
    //FOR BOOKING
    
    /** Creates new DomesticHotel */
    public DomesticHotel() {
        this.catid = 3;
        this.state_cd = null;
        this.city_cd = 0;
        this.checkindate = 0;
        this.checkoutdate = 0;
        this.nights = 0;
        this.numberOfrooms = 0;
        this.numberOfadult = 0;
        this.numberOfkids = 0;
        this.category = "";
        this.price = 0;
        this.from_price = 0;
        this.scatid = 0;
        this.SYO_TYPE = "";
    }

  	public void reset(ActionMapping mapping, HttpServletRequest request)
    {

        this.catid = 3;
        this.state_cd = null;
        this.city_cd = 0;
        this.checkindate = 0;
        this.checkoutdate = 0;
        this.nights = 0;
        this.numberOfrooms = 0;
        this.numberOfadult = 0;
        this.numberOfkids = 0;
        this.category = "";
        this.price = 0;
        this.from_price = 0;
        this.scatid = 0;
        this.SYO_TYPE = "";
    }
    
    /** プロパティ category の取得メソッド。
     * @return プロパティ category の値。
     */
    public String getCategory() {
        return category;
    }
    
    /** プロパティ category の設定メソッド。
     * @param category プロパティ category の新しい値。
     */
    public void setCategory(String category) {
        this.category = category;
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
    
    /** プロパティ checkindate の取得メソッド。
     * @return プロパティ checkindate の値。
     */
    public int getCheckindate() {
        return checkindate;
    }
    
    /** プロパティ checkindate の設定メソッド。
     * @param checkindate プロパティ checkindate の新しい値。
     */
    public void setCheckindate(int checkindate) {
        this.checkindate = checkindate;
    }
    
    /** プロパティ city_cd の取得メソッド。
     * @return プロパティ city_cd の値。
     */
    public int getCity_cd() {
        return city_cd;
    }
    
    /** プロパティ city_cd の設定メソッド。
     * @param city_cd プロパティ city_cd の新しい値。
     */
    public void setCity_cd(int city_cd) {
        this.city_cd = city_cd;
    }
    
    /** プロパティ from_price の取得メソッド。
     * @return プロパティ from_price の値。
     */
    public int getFrom_price() {
        return from_price;
    }
    
    /** プロパティ from_price の設定メソッド。
     * @param from_price プロパティ from_price の新しい値。
     */
    public void setFrom_price(int from_price) {
        this.from_price = from_price;
    }
    
    /** プロパティ nights の取得メソッド。
     * @return プロパティ nights の値。
     */
    public int getNights() {
        return nights;
    }
    
    /** プロパティ nights の設定メソッド。
     * @param nights プロパティ nights の新しい値。
     */
    public void setNights(int nights) {
        this.nights = nights;
    }
    
    /** プロパティ numberOfadul の取得メソッド。
     * @return プロパティ numberOfadul の値。
     */
    public int getNumberOfadult() {
        return numberOfadult;
    }
    
    /** プロパティ numberOfadul の設定メソッド。
     * @param numberOfadul プロパティ numberOfadul の新しい値。
     */
    public void setNumberOfadult(int numberOfadult) {
        this.numberOfadult = numberOfadult;
    }
    
    /** プロパティ numberOfkids の取得メソッド。
     * @return プロパティ numberOfkids の値。
     */
    public int getNumberOfkids() {
        return numberOfkids;
    }
    
    /** プロパティ numberOfkids の設定メソッド。
     * @param numberOfkids プロパティ numberOfkids の新しい値。
     */
    public void setNumberOfkids(int numberOfkids) {
        this.numberOfkids = numberOfkids;
    }
    
    /** プロパティ numberOfrooms の取得メソッド。
     * @return プロパティ numberOfrooms の値。
     */
    public int getNumberOfrooms() {
        return numberOfrooms;
    }
    
    /** プロパティ numberOfrooms の設定メソッド。
     * @param numberOfrooms プロパティ numberOfrooms の新しい値。
     */
    public void setNumberOfrooms(int numberOfrooms) {
        this.numberOfrooms = numberOfrooms;
    }
    
    /** プロパティ price の取得メソッド。
     * @return プロパティ price の値。
     */
    public int getPrice() {
        return price;
    }
    
    /** プロパティ price の設定メソッド。
     * @param price プロパティ price の新しい値。
     */
    public void setPrice(int price) {
        this.price = price;
    }    
    
    /** プロパティ state_cd の取得メソッド。
     * @return プロパティ state_cd の値。
     */
    public String[] getState_cd() { return state_cd; }
    
    /** プロパティ state_cd の設定メソッド。
     * @param state_cd プロパティ state_cd の新しい値。
     */
    public void setState_cd(String[] state_cd) { this.state_cd = state_cd; }
    
    /** プロパティ SYO_TYPE の取得メソッド。
     * @return プロパティ SYO_TYPE の値。
     */
    public String getSYO_TYPE() {
        return SYO_TYPE;
    }
    
    /** プロパティ SYO_TYPE の設定メソッド。
     * @param SYO_TYPE プロパティ SYO_TYPE の新しい値。
     */
    public void setSYO_TYPE(String SYO_TYPE) {
        this.SYO_TYPE = SYO_TYPE;
    }
    
    /** プロパティ to_price の取得メソッド。
     * @return プロパティ to_price の値。
     */
    public int getTo_price() {
        return to_price;
    }
    
    /** プロパティ to_price の設定メソッド。
     * @param to_price プロパティ to_price の新しい値。
     */
    public void setTo_price(int to_price) {
        this.to_price = to_price;
    }

    public String getXmlString(){ return xmlString; }    

    public void setXmlString(String xmlString){ this.xmlString = xmlString; }

    public int getScatid(){
            return scatid;
        }

    public void setScatid(int scatid){
            this.scatid = scatid;
        }

    public String getAgt_cd(){ return agt_cd; }

    public void setAgt_cd(String agt_cd){ this.agt_cd = agt_cd; }

    public String getProduct_id(){ return product_id; }

    public void setProduct_id(String product_id){ this.product_id = product_id; }

    public String getSup_id(){ return sup_id; }

    public void setSup_id(String sup_id){ this.sup_id = sup_id; }

    public int getCheckoutdate(){
            return checkoutdate;
        }

    public void setCheckoutdate(int checkoutdate){
            this.checkoutdate = checkoutdate;
        }
}
