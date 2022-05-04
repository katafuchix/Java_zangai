/*
 * User_Tbl.java
 *
 * Created on 2002/04/05, 13:37
 */
package jp.co.lastminute.cart.user.model;

/**
 *
 * @author  skondo & Takashi Yamada
 * @version 1.0
 */
public class User_Tbl {
    /**
        USER_ID                                   NOT NULL NUMBER(10)
        E_MAIL                                    NOT NULL VARCHAR2(120)
        PASSWD                                    NOT NULL VARCHAR2(10)
        AUTH_FLG                                           CHAR(1)
        DEL_FLG                                            NUMBER(2)
        HTML_MAIL_OK                                       CHAR(1)
        MAIL_MAG_OK                                        CHAR(1)
        CAMPAIGNUSERFLAG                                   NUMBER(2)
        DEALWATCHID                               NOT NULL VARCHAR2(4)
        MAKE_DATE                                          DATE
        UP_DATE                                            DATE
     */

    /** 初期化 */
    private int USER_ID;            //ユーザーID
    private String E_MAIL;          //E_MAIL
    private String PASSWD;          //パスワード
    private String AUTH_FLG;        //認証フラグ
    private int DEL_FLG;            //削除フラグ
    private String HTML_MAIL_OK;    //HTMLメールＯＫフラグ
    private String MAIL_MAG_OK;     //メールマガジンＯＫフラグ
    private int CAMPAIGNUSERFLAG;   //キャンペーンユーザーフラグ
    private String DEALWATCHID;     //ディールウォッチＩＤ
    private String MAKE_DATE;       //作成日
    private String UP_DATE;         //修正日

    /** コントラクター */
    public User_Tbl() {
         this.USER_ID = 0;          //ユーザーID(リセット)
         this.E_MAIL = "";          //E_MAIL(リセット)
         this.PASSWD = "";          //パスワード(リセット)
         this.AUTH_FLG = "";        //認証フラグ(リセット)
         this.DEL_FLG = 0;          //削除フラグ(リセット)
         this.HTML_MAIL_OK = "";    //HTMLメールＯＫフラグ(リセット)
         this.MAIL_MAG_OK = "";     //メールマガジンＯＫフラグ(リセット)
         this.CAMPAIGNUSERFLAG = 0; //キャンペーンユーザーフラグ(リセット)
         this.DEALWATCHID = "";     //ディールウォッチＩＤ(リセット)
         this.MAKE_DATE = "";       //作成日(リセット)
         this.UP_DATE = "";         //修正日(リセット)
    }

    /**
     * ユーザーIDの保持をする
     * @param int USER_ID
     */
    synchronized public void setUSER_ID(int userid){
        this.USER_ID = userid;        
    }

    /**
     * E_MAILの保持をする
     * @return String E_MAIL
     */
    synchronized public void setE_MAIL(String email){
        this.E_MAIL = email;
    }

    /**
     * パスワードの保持をする
     * @param String PASSWD
     */
    synchronized public void setPASSWD(String passwd){
        this.PASSWD = passwd;
    }

    /**
     * 認証フラグの保持をする
     * @param char AUTH_FLG
     */
    synchronized public void setAUTH_FLG(String authflg){
        this.AUTH_FLG = authflg;
    }

    /**
     * 削除フラグの保持する
     * @param char DEL_FLG 
     */
    synchronized public void setDEL_FLG(int delflg){
        this.DEL_FLG = delflg;
    }

    /**
     * HTMLメールＯＫフラグの保持をする
     * @param char HTML_MAIL_OK
     */
    synchronized public void setHTML_MAIL_OK(String htmlmailkok){
        this.HTML_MAIL_OK = htmlmailkok;
    }

    /**
     * メールマガジンＯＫフラグの保持をする
     * @param char MAIL_MAG_OK
     */
    synchronized public void setMAIL_MAG_OK(String mailmagok){
        this.MAIL_MAG_OK = mailmagok;
    }

    /**
     * キャンペーンユーザーフラグの保持をする
     * @param int CAMPAIGNUSERFLAG
     */
    synchronized public void setCAMPAIGNUSERFLAG(int campaignuserflag){
        this.CAMPAIGNUSERFLAG = campaignuserflag;
    }

    /**
     * ディールウォッチＩＤの保持をする
     * @param String DEALWATCHID
     */
    synchronized public void setDEALWATCHID(String dealwatchid){
        this.DEALWATCHID = dealwatchid;
    }

    /**
     * 作成日を保持する
     * @param String MAKE_DATE
     */
    synchronized public void setMAKE_DATE(String makedate){
        this.MAKE_DATE = makedate;
    }

    /**
     * 修正日を保持する
     * @param String UP_DATE
     */
    synchronized public void setUP_DATE(String update){
        this.UP_DATE = update;        
    }

    /**
     * ユーザーIDを取得する
     * @return int USER_ID 
     */
    synchronized public int getUSER_ID(){
        try{
            return this.USER_ID;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * E_MAILを取得する
     * @retrun String E_MAIL
     */
    synchronized public String getE_MAIL(){
        try{
            return this.E_MAIL;
        }catch(Exception ex){}
        return "";
    }

    /**
     * パスワードを取得する
     * @return String PASSWD
     */
    synchronized public String getPASSWD(){
        try{
            return this.PASSWD;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 認証フラグを取得する
     * @return String AUTH_FLG
     */
    synchronized public String getAUTH_FLG(){
        try{
            return this.AUTH_FLG;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 削除フラグを取得する
     * @return int DEL_FLG
     */
    synchronized public int getDEL_FLG(){
        try{
            return this.DEL_FLG;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * メール種別フラグを取得する
     * @return String HTML_MAIL_OK
     */
    synchronized public String getHTML_MAIL_OK(){
        try{
            return this.HTML_MAIL_OK;        
        }catch(Exception ex){}
        return "";
    }

    /**
     * メールマガジン発行可能フラグを取得する
     * @return String MAIL_MAG_OK
     */
    synchronized public String getMAIL_MAG_OK(){
        try{
            return this.MAIL_MAG_OK;
        }catch(Exception ex){}
        return "";
    }

    /**
     * キャンペーンフラグを取得する
     * @return int CAMPAIGNUSERFLAG
     */
    synchronized public int getCAMPAIGNUSERFLAG(){
        try{
            return this.CAMPAIGNUSERFLAG;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * ディールウォッチIDを取得する
     * @return String DEALWATCHID
     */
    synchronized public String getDEALWATCHID(){
        try{
            return this.DEALWATCHID;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 作成日の取得する
     * @return String MAKE_DATE
     */
    synchronized public String getMAKE_DATE(){
        try{
            return this.MAKE_DATE; 
        }catch(Exception ex){}
        return "";
    }

    /**
     * 修正日の取得する
     * @return String UP_DATE
     */
    synchronized public String getUP_DATE(){
        try{
            return this.UP_DATE;
        }catch(Exception ex){}
        return "";
    }
}
