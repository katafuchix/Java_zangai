/*
 * PostalCode.java
 *
 * Created on 2002/04/20, 20:26
 */
package jp.co.lastminute.cart.user.model;

/**
 *
 * @author  Takashi Yamada
 * @version 1.0
 */
public class PostalCode {
    /**
        POSTAL_CD                                          CHAR(7)
        STATE_CD                                           NUMBER(2)
        CITY_CD                                            NUMBER(7)
        STREETCD                                           NUMBER(8)
        STATE_NAME                                         VARCHAR2(18)
        CITY_NAME                                          VARCHAR2(24)
        STREETNAME                                         VARCHAR2(60)
    */
   
    /** 初期化 */
    private String POSTAL_CD;   // 郵便番号コード
    private int STATE_CD;       // 都道府県コード
    private String STATE_NAME;  // 都道府県名
    private int CITY_CD;        // 市区町村コード
    private String CITY_NAME;   // 市区町村名
    private int STREETCD;       // 町域名
    private String STREETNAME;  // 町域名
    
    /** コンストラクタ */
    public PostalCode() {
        this.POSTAL_CD = "";    // 郵便番号コード(リセット)
        this.STATE_CD = 0;      // 都道府県コード(リセット)
        this.STATE_NAME = "";   // 都道府県名(リセット)
        this.CITY_CD = 0;       // 市区町村コード(リセット)
        this.CITY_NAME = "";    // 市区町村名(リセット)
        this.STREETCD = 0;      // 町域コード(リセット)
        this.STREETNAME = "";   // 町域名(リセット)
    }

    /**
     * 郵便番号コードを取得する。
     * @param String POSTAL_CD
     */
    synchronized public String getPOSTAL_CD(){
        try{
            return this.POSTAL_CD;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 都道府県コードを取得する。
     * @param int STATE_CD
     */
    synchronized public int getSTATE_CD(){
        try{
            return this.STATE_CD;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 都道府県名を取得する。
     * @param String STATE_NAME
     */
    synchronized public String getSTATE_NAME(){
        try{
            return this.STATE_NAME;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 市区町村コードを取得する。
     * @param int CITY_CD
     */
    synchronized public int getCITY_CD(){
        try{
            return this.CITY_CD;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 市区町村名を取得する。
     * @param String CITY_NAME
     */
    synchronized public String getCITY_NAME(){
        try{
            return this.CITY_NAME;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 地域コードを取得する。
     * @param int STREETCD
     */
    synchronized public int getSTREETCD(){
        try{
            return this.STREETCD;
        }catch(Exception ex){}
        return 0;
    }

    /**
     * 地域名を取得する。
     * @param String STREETNAME
     */
    synchronized public String getSTREETNAME(){
        try{
            return this.STREETNAME;
        }catch(Exception ex){}
        return "";
    }

    /**
     * 郵便番号コードを保持する。
     * @param String POSTAL_CD
     */
    synchronized public void setPOSTAL_CD(String postal_Cd){
        this.POSTAL_CD = postal_Cd;
    }

    /**
     * 都道府県コードを保持する。
     * @param int STATE_CD
     */
    synchronized public void setSTATE_CD(int state_Code){
        this.STATE_CD = state_Code;
    }

    /**
     * 都道府県名を保持する。
     * @param String STATE_NAME
     */
    synchronized public void setSTATE_NAME(String state_Name){
        this.STATE_NAME = state_Name;
    }

    /**
     * 市区町村コードを保持する。
     * @param int CITY_CD
     */
    synchronized public void setCITY_CD(int city_Code ){
        this.CITY_CD = city_Code;
    }

    /**
     * 市区町村名を保持する。
     * @param String CITY_NAME
     */
    synchronized public void setCITY_NAME(String city_Name){
        this.CITY_NAME = city_Name;
    }

    /**
     * 地域コードを保持する。
     * @param int STREETCD
     */
    synchronized public void setSTREETCD(int street_Code){
        this.STREETCD = street_Code;
    }

    /**
     * 地域名を保持する。
     * @param String STREETNAME
     */
    synchronized public void setSTREETNAME(String street_Name){
        this.STREETNAME = street_Name;
    }
}
