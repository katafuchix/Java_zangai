/*
 * Agent.java
 *
 * Created on 2002/06/19, 11:54
 */

package jp.co.lastminute.supply;

import java.io.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class Agent implements Serializable{
    /**
     *
     *
     ///// agent_tbl
    AGT_CD                                    NOT NULL VARCHAR2(4)
    TEL_NO                                             VARCHAR2(20)
    FAX                                                VARCHAR2(20)
    E_MAIL                                    NOT NULL VARCHAR2(120)
    FIRST_NAME                                         VARCHAR2(30)
    LASTNAME                                           VARCHAR2(30)
    POSTAL_CD                                 NOT NULL VARCHAR2(8)
    STATE_CD                                           NUMBER(10)
    CITY_NAME                                          VARCHAR2(24)
    ADDRESS                                            VARCHAR2(80)
     ////agent_member_tbl
     MEMBER_CD                                 NOT NULL NUMBER(6)
     AGT_CD                                    NOT NULL VARCHAR2(4)
     TEL_NO                                             VARCHAR2(20)
     PASSWD                                             VARCHAR2(12)
     FAX                                                VARCHAR2(20)
     E_MAIL                                    NOT NULL VARCHAR2(120)
     FIRST_NAME                                         VARCHAR2(30)
     LASTNAME                                           VARCHAR2(30)
     POSTAL_CD                                 NOT NULL VARCHAR2(8)
     STATE_CD                                           NUMBER(10)
     CITY_NAME                                          VARCHAR2(24)
     ADDRESS                                            VARCHAR2(80)
     */
    /** Creates new Agent */
    
    public Agent() {
    }
    public String AGT_CD = "";
    public String TEL_NO = "";
    public String FAX    = "";
    public String E_MAIL = "";
    public String FIRST_NAME = "";
    public String LASTNAME = "";
    public String POSTAL_CD = "";
    public String STATE_NAME = "";
    public String CITY_NAME = "";
    public String ADDRESS = "";
    public String MAILCOMMENT = "";
    //////////////////////////////
    String lines = "------------------------------------------------------------\n";
    /** プロパティ AGT_CD の取得メソッド。
     * @return プロパティ AGT_CD の値。
     */
    public String getAGT_CD() {
        return AGT_CD;
    }
    
    /** プロパティ AGT_CD の設定メソッド。
     * @param AGT_CD プロパティ AGT_CD の新しい値。
     */
    public void setAGT_CD(String AGT_CD) {
        this.AGT_CD = AGT_CD;
    }
    
    /** プロパティ CITY_NAME の取得メソッド。
     * @return プロパティ CITY_NAME の値。
     */
    public String getCITY_NAME() {
        return CITY_NAME;
    }
    
    /** プロパティ CITY_NAME の設定メソッド。
     * @param CITY_NAME プロパティ CITY_NAME の新しい値。
     */
    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }
    
    /** プロパティ E_MAIL の取得メソッド。
     * @return プロパティ E_MAIL の値。
     */
    public String getE_MAIL() {
        return E_MAIL;
    }
    
    /** プロパティ E_MAIL の設定メソッド。
     * @param E_MAIL プロパティ E_MAIL の新しい値。
     */
    public void setE_MAIL(String E_MAIL) {
        this.E_MAIL = E_MAIL;
    }
    
    /** プロパティ FAX の取得メソッド。
     * @return プロパティ FAX の値。
     */
    public String getFAX() {
        return FAX;
    }
    
    /** プロパティ FAX の設定メソッド。
     * @param FAX プロパティ FAX の新しい値。
     */
    public void setFAX(String FAX) {
        this.FAX = FAX;
    }
    
    /** プロパティ FIRST_NAME の取得メソッド。
     * @return プロパティ FIRST_NAME の値。
     */
    public String getFIRST_NAME() {
        return FIRST_NAME;
    }
    
    /** プロパティ FIRST_NAME の設定メソッド。
     * @param FIRST_NAME プロパティ FIRST_NAME の新しい値。
     */
    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }
    
    /** プロパティ STATE_NAME の取得メソッド。
     * @return プロパティ STATE_NAME の値。
     */
    public String getSTATE_NAME() {
        return STATE_NAME;
    }
    
    /** プロパティ STATE_NAME の設定メソッド。
     * @param STATE_NAME プロパティ STATE_NAME の新しい値。
     */
    public void setSTATE_NAME(String STATE_NAME) {
        this.STATE_NAME = STATE_NAME;
    }
    
    /** プロパティ TEL_NO の取得メソッド。
     * @return プロパティ TEL_NO の値。
     */
    public String getTEL_NO() {
        return TEL_NO;
    }
    
    /** プロパティ TEL_NO の設定メソッド。
     * @param TEL_NO プロパティ TEL_NO の新しい値。
     */
    public void setTEL_NO(String TEL_NO) {
        this.TEL_NO = TEL_NO;
    }
    
    /** プロパティ LASTNAME の取得メソッド。
     * @return プロパティ LASTNAME の値。
     */
    public String getLASTNAME() {
        return LASTNAME;
    }
    
    /** プロパティ LASTNAME の設定メソッド。
     * @param LASTNAME プロパティ LASTNAME の新しい値。
     */
    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }
    
    /** プロパティ POSTAL_CD の取得メソッド。
     * @return プロパティ POSTAL_CD の値。
     */
    public String getPOSTAL_CD() {
        return POSTAL_CD;
    }
    
    /** プロパティ POSTAL_CD の設定メソッド。
     * @param POSTAL_CD プロパティ POSTAL_CD の新しい値。
     */
    public void setPOSTAL_CD(String POSTAL_CD) {
        this.POSTAL_CD = POSTAL_CD;
    }
    
    /** プロパティ ADDRESS の取得メソッド。
     * @return プロパティ ADDRESS の値。
     */
    public String getADDRESS() {
        return ADDRESS;
    }
    
    /** プロパティ ADDRESS の設定メソッド。
     * @param ADDRESS プロパティ ADDRESS の新しい値。
     */
    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }
    public String getFooter(){
        String reStr = lines + "[会社名]" + this.FIRST_NAME + "\n"
                    + "[責任者]" + this.LASTNAME + "\n"
                    + "[所在地]" + this.getShowPostal() + "\n"
                    + this.STATE_NAME + " " + this.CITY_NAME + " " + this.ADDRESS + "\n"
                    + "[電話番号]" + this.TEL_NO + "/" + this.FAX + "\n"
                    + lines;
        return reStr;
    }
    public String getShowPostal(){
        if( this.POSTAL_CD.indexOf('-') == -1){
            return this.POSTAL_CD.substring(0, 3) + "-" + this.POSTAL_CD.substring( 3 ); 
        }
        return this.POSTAL_CD;
    }
    
    /** プロパティ MAILCOMMENT の取得メソッド。
     * @return プロパティ MAILCOMMENT の値。
     */
    public String getMAILCOMMENT() {
        return MAILCOMMENT;
    }
    
    /** プロパティ MAILCOMMENT の設定メソッド。
     * @param MAILCOMMENT プロパティ MAILCOMMENT の新しい値。
     */
    public void setMAILCOMMENT(String MAILCOMMENT) {
        this.MAILCOMMENT = MAILCOMMENT;
    }
    
}
