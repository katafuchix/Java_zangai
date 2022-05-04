/*
 * ConstentsXml.java
 *
 * Created on 2002/05/03, 1:22
 */

package jp.co.lastminute.common.xml;

import java.io.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class ConstantsXml implements Serializable{
    private int product_type_cd = 0;
    private String agt_cd = "";
    private String category = "";
    private String checkurl = "";
    private String templatexsd = "";
    private String templatexsl = "";
    private String sendurl = "";
    private String sendxslt = "";
    private String cancelurl = "";
    private String cancelxslt = "";
    private String descxslt = "";

    /** Creates new ConstentsXml */
    public ConstantsXml() {
    }

    /** プロパティ agt_cd の取得メソッド。
     * @return プロパティ agt_cd の値。
     */
    public String getAgt_cd() {
        return agt_cd;
    }
    
    /** プロパティ agt_cd の設定メソッド。
     * @param agt_cd プロパティ agt_cd の新しい値。
     */
    public void setAgt_cd(String agt_cd) {
        this.agt_cd = agt_cd;
    }
    
    /** プロパティ cancelurl の取得メソッド。
     * @return プロパティ cancelurl の値。
     */
    public String getCancelurl() {
        return cancelurl;
    }
    
    /** プロパティ cancelurl の設定メソッド。
     * @param cancelurl プロパティ cancelurl の新しい値。
     */
    public void setCancelurl(String cancelurl) {
        this.cancelurl = cancelurl;
    }
    
    /** プロパティ cancelxslt の取得メソッド。
     * @return プロパティ cancelxslt の値。
     */
    public String getCancelxslt() {
        return cancelxslt;
    }
    
    /** プロパティ cancelxslt の設定メソッド。
     * @param cancelxslt プロパティ cancelxslt の新しい値。
     */
    public void setCancelxslt(String cancelxslt) {
        this.cancelxslt = cancelxslt;
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
    
    /** プロパティ checkurl の取得メソッド。
     * @return プロパティ checkurl の値。
     */
    public String getCheckurl() {
        return checkurl;
    }
    
    /** プロパティ checkurl の設定メソッド。
     * @param checkurl プロパティ checkurl の新しい値。
     */
    public void setCheckurl(String checkurl) {
        this.checkurl = checkurl;
    }
    
    /** プロパティ descxslt の取得メソッド。
     * @return プロパティ descxslt の値。
     */
    public String getDescxslt() {
        return descxslt;
    }
    
    /** プロパティ descxslt の設定メソッド。
     * @param descxslt プロパティ descxslt の新しい値。
     */
    public void setDescxslt(String descxslt) {
        this.descxslt = descxslt;
    }
    
    /** プロパティ product_type_cd の取得メソッド。
     * @return プロパティ product_type_cd の値。
     */
    public int getProduct_type_cd() {
        return product_type_cd;
    }
    
    /** プロパティ product_type_cd の設定メソッド。
     * @param product_type_cd プロパティ product_type_cd の新しい値。
     */
    public void setProduct_type_cd(int product_type_cd) {
        this.product_type_cd = product_type_cd;
    }
    
    /** プロパティ sendxslt の取得メソッド。
     * @return プロパティ sendxslt の値。
     */
    public String getSendxslt() {
        return sendxslt;
    }
    
    /** プロパティ sendxslt の設定メソッド。
     * @param sendxslt プロパティ sendxslt の新しい値。
     */
    public void setSendxslt(String sendxslt) {
        this.sendxslt = sendxslt;
    }
    
    /** プロパティ templatexsd の取得メソッド。
     * @return プロパティ templatexsd の値。
     */
    public String getTemplatexsd() {
        return templatexsd;
    }
    
    /** プロパティ templatexsd の設定メソッド。
     * @param templatexsd プロパティ templatexsd の新しい値。
     */
    public void setTemplatexsd(String templatexsd) {
        this.templatexsd = templatexsd;
    }
    
    /** プロパティ sendurl の取得メソッド。
     * @return プロパティ sendurl の値。
     */
    public String getSendurl() {
        return sendurl;
    }
    
    /** プロパティ sendurl の設定メソッド。
     * @param sendurl プロパティ sendurl の新しい値。
     */
    public void setSendurl(String sendurl) {
        this.sendurl = sendurl;
    }
    
    /** プロパティ templatexsl の取得メソッド。
     * @return プロパティ templatexsl の値。
     */
    public String getTemplatexsl() {
        return templatexsl;
    }
    
    /** プロパティ templatexsl の設定メソッド。
     * @param templatexsl プロパティ templatexsl の新しい値。
     */
    public void setTemplatexsl(String templatexsl) {
        this.templatexsl = templatexsl;
    }
    
}
