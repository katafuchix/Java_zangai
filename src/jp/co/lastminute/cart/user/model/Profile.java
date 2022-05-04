/*
 * Profile.java
 *
 * Created on 2002/04/05, 19:08
 */
package jp.co.lastminute.cart.user.model;

/**
 *
 * @author  skondo & Takashi Yamada
 * @version 1.0
 */
public class Profile {
    /**
        USER_ID                                   NOT NULL NUMBER(10)
        SEI_KANA                                           VARCHAR2(30)
        SEI_KANJI                                          VARCHAR2(30)
        NA_KANA                                            VARCHAR2(30)
        NA_KANJI                                           VARCHAR2(30)
        FIRST_NAME                                         VARCHAR2(30)
        LASTNAME                                           VARCHAR2(30)
        SEX                                                NUMBER(10)
        BIRTH_DAY                                          NUMBER(8)
        POSTAL_CD                                 NOT NULL CHAR(7)
        STATE_CD                                           NUMBER(10)
        CITY_NAME                                          VARCHAR2(24)
        ADDRESS                                            VARCHAR2(80)
        TEL_NO                                             VARCHAR2(20)
        FAX                                                VARCHAR2(20)
    */

    /** 初期化 */
    private int USER_ID;        //ユーザーＩＤ
    private String SEI_KANA;    //姓-かな
    private String SEI_KANJI;   //姓-漢字
    private String NA_KANA;     //名-かな
    private String NA_KANJI;    //名-漢字
    private String FIRST_NAME;  //ファーストネーム
    private String LASTNAME;    //ラストネーム
    private int SEX;            //性別
    private int BIRTH_DAY;      //誕生日
    private String POSTAL_CD;   //郵便番号
    private int STATE_CD;       //都道府県コード
    private String CITY_NAME;   //都市名
    private String ADDRESS;     //住所
    private String TEL_NO;      //電話番号
    private String FAX;         //FAX

    /** コンストラクタ */
    public Profile() {
         this.USER_ID = 0;      //ユーザーＩＤ(リセット)
         this.SEI_KANA = "";    //姓-かな(リセット)
         this.SEI_KANJI = "";   //姓-漢字(リセット)
         this.NA_KANA = "";     //名-かな(リセット)
         this.NA_KANJI = "";    //名-漢字(リセット)
         this.FIRST_NAME = "";  //ファーストネーム(リセット)
         this.LASTNAME = "";    //ラストネーム(リセット)
         this.SEX = 0;          //性別(リセット)
         this.BIRTH_DAY = 0;    //誕生日(リセット)
         this.POSTAL_CD = "";   //郵便番号(リセット)
         this.STATE_CD = 0;     //都道府県コード(リセット)
         this.CITY_NAME = "";   //都市名(リセット)
         this.ADDRESS = "";     //住所(リセット)
         this.TEL_NO = "";      //電話番号(リセット)
         this.FAX = "";         //FAX(リセット)
    }

    /**
     * ユーザーＩＤの取得
     * @param int USER_ID
     */
    synchronized public int getUSER_ID(){
        try{
            return this.USER_ID;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 姓-かなの取得
     * @param String SEI_KANA
     */
    synchronized public String getSEI_KANA(){
        try{
            return this.SEI_KANA;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 姓-漢字の取得
     * @param String SEI_KANJI
     */
    synchronized public String getSEI_KANJI(){
        try{
            return this.SEI_KANJI;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 名-かなの取得
     * @param String NA_KANA
     */
    synchronized public String getNA_KANA(){
        try{
            return this.NA_KANA;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 名-漢字の取得
     * @param String NA_KANJI
     */
    synchronized public String getNA_KANJI(){
        try{
            return this.NA_KANJI;
        }catch(Exception ex){}
        return "";
    }

    /**
     * ファーストネームの取得
     * @param String FIRST_NAME
     */
    synchronized public String getFIRST_NAME(){
        try{
            return this.FIRST_NAME;
        }catch(Exception ex){}
        return "";
    }

    /**
     * ラストネームの取得
     * @param String LASTNAME
     */
    synchronized public String getLASTNAME(){
        try{
            return this.LASTNAME;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 性別の取得
     * @param int SEX
     */
    synchronized public int getSEX(){
        try{
            return this.SEX;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 誕生日の取得
     * @param int BIRTH_DAY
     */
    synchronized public int getBIRTH_DAY(){
        try{
            return this.BIRTH_DAY;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 郵便番号の取得
     * @param char POSTAL_CD
     */
    synchronized public String getPOSTAL_CD(){
        try{
            return this.POSTAL_CD;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 都道府県コードの取得
     * @param int STATE_CD
     */
    synchronized public int getSTATE_CD(){
        try{
            return this.STATE_CD;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 都市名の取得
     * @param String CITY_NAME
     */
    synchronized public String getCITY_NAME(){
        try{
            return this.CITY_NAME;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 住所の取得
     * @param String ADDRESS
     */
    synchronized public String getADDRESS(){
        try{
            return this.ADDRESS;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 電話番号の取得
     * @param String TEL_NO
     */
    synchronized public String getTEL_NO(){
        try{
            return this.TEL_NO; 
        }catch(Exception ex){}
        return "";
    }

    /**
     * FAXの取得
     * @param String FAX
     */
    synchronized public String getFAX(){
        try{
            return this.FAX; 
        }catch(Exception ex){}
        return "";
    }

    /**
     * ユーザーＩＤの保持
     * @param int USER_ID
     */
    synchronized public void setUSER_ID(int userid){
        this.USER_ID = userid;
    }

    /**
     * 姓-かなの保持
     * @param String SEI_KANA
     */
    synchronized public void setSEI_KANA(String sei_kana){
        this.SEI_KANA = sei_kana;
    }

    /**
     * 姓-漢字の保持
     * @param String SEI_KANJI
     */
    synchronized public void setSEI_KANJI(String sei_kanji){
        this.SEI_KANJI = sei_kanji;
    }

    /**
     * 名-かなの保持
     * @param String NA_KANA
     */
    synchronized public void setNA_KANA(String na_kana){
        this.NA_KANA = na_kana;
    }

    /**
     * 名-漢字の保持
     * @param String NA_KANJI
     */
    synchronized public void setNA_KANJI(String na_kanji){
        this.NA_KANJI = na_kanji;
    }

    /**
     * ファーストネームの保持
     * @param String FIRST_NAME
     */
    synchronized public void setFIRST_NAME(String first_name){
        this.FIRST_NAME = first_name;
    }


    /**
     * ラストネームの保持
     * @param String LASTNAME
     */
    synchronized public void setLASTNAME(String lastname){
        this.LASTNAME = lastname;
    }

    /**
     * 性別の保持
     * @param int SEX
     */
    synchronized public void setSEX(int sex){
        this.SEX = sex;
    }

    /**
     * 誕生日の保持
     * @param int BIRTH_DAY
     */
    synchronized public void setBIRTH_DAY(int birth_day){
        this.BIRTH_DAY = birth_day;
    }

    /**
     * 郵便番号の保持
     * @param char POSTAL_CD
     */
    synchronized public void setPOSTAL_CD(String postalcd){
        this.POSTAL_CD = postalcd;
    }

    /**
     * 都道府県コードの保持
     * @param int STATE_CD
     */
    synchronized public void setSTATE_CD(int statecd){
        this.STATE_CD = statecd;
    }

    /**
     * 都市名の保持
     * @param String CITY_NAME
     */
    synchronized public void setCITY_NAME(String cityname){
        this.CITY_NAME = cityname;
    }

    /**
     * 住所の保持
     * @param String ADDRESS
     */
    synchronized public void setADDRESS(String address){
        this.ADDRESS = address;
    }

    /**
     * 電話番号の保持
     * @param String TEL_NO
     */
    synchronized public void setTEL_NO(String telno){
        this.TEL_NO = telno;
    }

    /**
     * FAXの保持
     * @param String FAX
     */
    synchronized public void setFAX(String fax){
        this.FAX = fax;
    }
}
