/*
 * dbAdapterProfile.java
 *
 * Created on 2002/04/05, 21:15
 */

package jp.co.lastminute.cart.user.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;

/**
 *
 * @author  Takashi Yamada
 * @version 1.0
 */
public class dbAdapterProfile implements Serializable{
    private User_Tbl uscd;
    private Profile prcd;
    private DataSource ds;

    /**
     * コンストラクター
     */
    public dbAdapterProfile(DataSource dss) {
        this.ds = dss;
        prcd = null;
        uscd = null;
    }

    /**
     * ユーザー情報を検索する
     * @param int userid
     */
    synchronized public User findProfile( String email ){
        User us = new User();
        String  SQL = "";	System.err.println("<------dbAdapterProfile:44----->");
        try{				System.err.println("<------dbAdapterProfile:45----->");
            // ユーザデータを検索するためのSQL文を設定
            SQL  = "SELECT"
                 + " A.USER_ID,"
                 + " A.E_MAIL,"
                 + " A.PASSWD,"
                 + " A.AUTH_FLG,"
                 + " A.DEL_FLG,"
                 + " A.HTML_MAIL_OK,"
                 + " A.MAIL_MAG_OK,"
                 + " A.CAMPAIGNUSERFLAG,"
                 + " A.DEALWATCHID,"
                 + " TO_CHAR(A.MAKE_DATE, 'YYYYMMDD'),"
                 + " TO_CHAR(A.UP_DATE, 'YYYYMMDD'),"
                 + " B.SEI_KANA,"
                 + " B.SEI_KANJI,"
                 + " B.NA_KANA,"
                 + " B.NA_KANJI,"
                 + " B.FIRST_NAME,"
                 + " B.LASTNAME,"
                 + " B.SEX,"
                 + " B.BIRTH_DAY,"
                 + " B.POSTAL_CD,"
                 + " B.STATE_CD,"
                 + " B.CITY_NAME,"
                 + " B.ADDRESS,"
                 + " B.TEL_NO,"
                 + " B.FAX"
                 + " FROM USER_TBL A, PROFILE B"
                 + " WHERE A.E_MAIL='" + email + "' AND B.USER_ID=A.USER_ID AND A.DEL_FLG=0 AND A.CAMPAIGNUSERFLAG=1";
     		System.err.println("<------dbAdapterProfile:75----->");
	        //JDBCAdapter起動
	        JdbcAdapter db = new JdbcAdapter();	System.err.println("<------dbAdapterProfile:77----->");
	        int gsize = 0;		////
	        System.err.println( SQL );
	        //DBに接続をする
	        if( db.init(ds) ){
	            Vector setd = db.dbSelect(SQL);
	            dbDataAccesser dba = new dbDataAccesser(setd);
	
	            //データを得る
	            uscd = new User_Tbl();  // User_Tblの初期化を行う  
	            prcd = new Profile();   // Profileの初期化を行う
	
	            //User_Tbl
	            uscd.setUSER_ID(dba.getDatabyInt(0,0));             //ユーザーID(int)
	            uscd.setE_MAIL(dba.getData(0,1));                   //E_MAIL(String)
	            uscd.setPASSWD(dba.getData(0,2));                   //パスワード(String)
	            uscd.setAUTH_FLG(dba.getData(0,3));                 //認証フラグ(String)
	            uscd.setDEL_FLG(dba.getDatabyInt(0,4));             //削除フラグ(int)
	            uscd.setHTML_MAIL_OK(dba.getData(0,5));             //HTMLメールOKフラグ(String)
	            uscd.setMAIL_MAG_OK(dba.getData(0,6));              //メールマガジンOKフラグ(String)
	            uscd.setCAMPAIGNUSERFLAG(dba.getDatabyInt(0,7));    //キャンペーンユーザーフラグ(int)
	            uscd.setDEALWATCHID(dba.getData(0,8));              //ディールウォッチID(String)
	            uscd.setMAKE_DATE(dba.getData(0,9));                //作成日(String)
	            uscd.setUP_DATE(dba.getData(0,10));                 //修正日(String)
	            us.setUser(uscd);        
	
	            //Profile
	            prcd.setUSER_ID(uscd.getUSER_ID());         //ユーザーID(int)
	            prcd.setSEI_KANA(dba.getData(0,11));        //姓-かな(String)
	            prcd.setSEI_KANJI(dba.getData(0,12));       //姓-漢字(String)
	            prcd.setNA_KANA(dba.getData(0,13));         //名-かな(String)
	            prcd.setNA_KANJI(dba.getData(0,14));        //名-漢字(String)
	            prcd.setFIRST_NAME(dba.getData(0,15));      //ファーストネーム(String)
	            prcd.setLASTNAME(dba.getData(0,16));        //ラストネーム(String)
	            prcd.setSEX(dba.getDatabyInt(0,17));        //性別(int)
	            prcd.setBIRTH_DAY(dba.getDatabyInt(0,18));  //誕生日(int)
	            prcd.setPOSTAL_CD(dba.getData(0,19));       //郵便番号(String)
	            prcd.setSTATE_CD(dba.getDatabyInt(0,20));   //都道府県コード(int)
	            prcd.setCITY_NAME(dba.getData(0,21));       //都市名(String)
	            prcd.setADDRESS(dba.getData(0,22));         //住所(String)
	            prcd.setTEL_NO(dba.getData(0,23));          //電話番号(String)
	            prcd.setFAX(dba.getData(0,24));             //FAX(String)
	            us.setProfile(prcd);
	            return us;
	        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    synchronized public User findProfile( int userid ){
        User us = new User();
        String  SQL = "";
        try{
            // ユーザデータを検索するためのSQL文を設定
            SQL  = " SELECT"
                 + "   A.USER_ID,"
                 + "   A.E_MAIL,"
                 + "   A.PASSWD,"
                 + "   A.AUTH_FLG,"
                 + "   A.DEL_FLG,"
                 + "   A.HTML_MAIL_OK,"
                 + "   A.MAIL_MAG_OK,"
                 + "   A.CAMPAIGNUSERFLAG,"
                 + "   A.DEALWATCHID,"
                 + "   TO_CHAR(A.MAKE_DATE, 'YYYYMMDD'),"
                 + "   TO_CHAR(A.UP_DATE, 'YYYYMMDD'),"
                 + "   B.SEI_KANA,"
                 + "   B.SEI_KANJI,"
                 + "   B.NA_KANA,"
                 + "   B.NA_KANJI,"
                 + "   B.FIRST_NAME,"
                 + "   B.LASTNAME,"
                 + "   B.SEX,"
                 + "   B.BIRTH_DAY,"
                 + "   B.POSTAL_CD,"
                 + "   B.STATE_CD,"
                 + "   B.CITY_NAME,"
                 + "   B.ADDRESS,"
                 + "   B.TEL_NO,"
                 + "   B.FAX"
                 + " FROM USER_TBL A, PROFILE B"
                 + " WHERE A.USER_ID=" + userid + " AND B.USER_ID=A.USER_ID AND A.DEL_FLG=0";
        
	        //JDBCAdapter起動
	        JdbcAdapter db = new JdbcAdapter();
	        int gsize = 0;
	        //DBに接続をする
	        if( db.init(ds) ){
	            Vector setd = db.dbSelect(SQL);
	            dbDataAccesser dba = new dbDataAccesser(setd);
	
	            //データを得る
	            uscd = new User_Tbl();  // User_Tblの初期化を行う  
	            prcd = new Profile();   // Profileの初期化を行う
	
	            //User_Tbl
	            uscd.setUSER_ID(dba.getDatabyInt(0,0));             //ユーザーID(int)
	            uscd.setE_MAIL(dba.getData(0,1));                   //E_MAIL(String)
	            uscd.setPASSWD(dba.getData(0,2));                   //パスワード(String)
	            uscd.setAUTH_FLG(dba.getData(0,3));                 //認証フラグ(String)
	            uscd.setDEL_FLG(dba.getDatabyInt(0,4));             //削除フラグ(int)
	            uscd.setHTML_MAIL_OK(dba.getData(0,5));             //HTMLメールOKフラグ(String)
	            uscd.setMAIL_MAG_OK(dba.getData(0,6));              //メールマガジンOKフラグ(String)
	            uscd.setCAMPAIGNUSERFLAG(dba.getDatabyInt(0,7));    //キャンペーンユーザーフラグ(int)
	            uscd.setDEALWATCHID(dba.getData(0,8));              //ディールウォッチID(String)
	            uscd.setMAKE_DATE(dba.getData(0,9));                //作成日(String)
	            uscd.setUP_DATE(dba.getData(0,10));                 //修正日(String)
	            us.setUser(uscd);        
	
	            //Profile
	            prcd.setUSER_ID(uscd.getUSER_ID());         //ユーザーID(int)
	            prcd.setSEI_KANA(dba.getData(0,11));        //姓-かな(String)
	            prcd.setSEI_KANJI(dba.getData(0,12));       //姓-漢字(String)
	            prcd.setNA_KANA(dba.getData(0,13));         //名-かな(String)
	            prcd.setNA_KANJI(dba.getData(0,14));        //名-漢字(String)
	            prcd.setFIRST_NAME(dba.getData(0,15));      //ファーストネーム(String)
	            prcd.setLASTNAME(dba.getData(0,16));        //ラストネーム(String)
	            prcd.setSEX(dba.getDatabyInt(0,17));        //性別(int)
	            prcd.setBIRTH_DAY(dba.getDatabyInt(0,18));  //誕生日(int)
	            prcd.setPOSTAL_CD(dba.getData(0,19));       //郵便番号(String)
	            prcd.setSTATE_CD(dba.getDatabyInt(0,20));   //都道府県コード(int)
	            prcd.setCITY_NAME(dba.getData(0,21));       //都市名(String)
	            prcd.setADDRESS(dba.getData(0,22));         //住所(String)
	            prcd.setTEL_NO(dba.getData(0,23));          //電話番号(String)
	            prcd.setFAX(dba.getData(0,24));             //FAX(String)
	            us.setProfile(prcd);
	        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return us;
    }
	/**
     *  SQLの取得
     */
	synchronized static public String getAddProflieStr( Profile prcd, JdbcAdapter db ) throws Exception {
		String SQL;
		SQL  = " INSERT INTO"
                 + " PROFILE("
                 + "   USER_ID,"
                 + "   SEI_KANA,"
                 + "   SEI_KANJI,"
                 + "   NA_KANA,"
                 + "   NA_KANJI,"
                 + "   FIRST_NAME,"
                 + "   LASTNAME,"
                 + "   SEX,"
                 + "   BIRTH_DAY,"
                 + "   POSTAL_CD,"
                 + "   STATE_CD,"
                 + "   CITY_NAME,"
                 + "   ADDRESS,"
                 + "   TEL_NO,"
                 + "   FAX"
                 + " )VALUES("
                 + " "  + prcd.getUSER_ID()                        + ","    //ユーザーID(int)
                 + " '" + db.convQuotation(prcd.getSEI_KANA())   + "',"   //姓-かな(String)
                 + " '" + db.convQuotation(prcd.getSEI_KANJI())  + "',"   //姓-漢字(String)
                 + " '" + db.convQuotation(prcd.getNA_KANA())    + "',"   //名-かな(String)
                 + " '" + db.convQuotation(prcd.getNA_KANJI())   + "',"   //名-漢字(String)
                 + " '" + db.convQuotation(prcd.getFIRST_NAME()) + "',"   //ファーストネーム(String)
                 + " '" + db.convQuotation(prcd.getLASTNAME())   + "',"   //ラストネーム(String)
                 + " "  + prcd.getSEX()                            + ","    //性別(int)
                 + " "  + prcd.getBIRTH_DAY()                     + ","    //誕生日(int)
                 + " '" + db.convQuotation(prcd.getPOSTAL_CD())  + "',"   //郵便番号(String)
                 + " "  + prcd.getSTATE_CD()                       + ","    //都道府県コード(int)
                 + " '" + db.convQuotation(prcd.getCITY_NAME())  + "',"   //都市名(String)
                 + " '" + db.convQuotation(prcd.getADDRESS())    + "',"   //住所(String)
                 + " '" + db.convQuotation(prcd.getTEL_NO())     + "',"   //電話番号(String)
                 + " '" + db.convQuotation(prcd.getFAX())         + "'"   //FAX(String)
                 + " )";
		return SQL;		
	}
    /**
     * ユーザー情報を追加する
     * @param Profile profile
     */
    synchronized public boolean addProfile( Profile prcd ){
        String  SQL = "";
        boolean rfrg = false;
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
             // プロファイルデータを追加する為のSQL文を設定
            SQL  = getAddProflieStr( prcd, db );
            //DBに接続をする
        	if( db.init(ds) )	rfrg = db.dbInsert(SQL);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rfrg;
    }
	/**
	 *  SQL更新の取得
	 */
	synchronized public static String getModifyProfileStr( Profile prcd, JdbcAdapter db ) throws Exception {
    	return "UPDATE PROFILE"
                 + " SET"
                 //+ "   USER_ID="       + prcd.getUSER_ID()                        + ","     //ユーザーID(int)
                 + "   SEI_KANA='"     + db.convQuotation(prcd.getSEI_KANA())   + "',"    //姓-かな(String)
                 + "   SEI_KANJI='"    + db.convQuotation(prcd.getSEI_KANJI())  + "',"    //姓-漢字(String)
                 + "   NA_KANA='"      + db.convQuotation(prcd.getNA_KANA())    + "',"    //名-かな(String)
                 + "   NA_KANJI='"     + db.convQuotation(prcd.getNA_KANJI())   + "',"    //名-漢字(String)
                 + "   FIRST_NAME='"   + db.convQuotation(prcd.getFIRST_NAME()) + "',"    //ファーストネーム(String)
                 + "   LASTNAME='"     + db.convQuotation(prcd.getLASTNAME())   + "',"    //ラストネーム(String)
                 + "   SEX="           + prcd.getSEX()                            + ","     //性別(int)
                 + "   BIRTH_DAY="     + prcd.getBIRTH_DAY()                     + ","     //誕生日(int)
                 + "   POSTAL_CD='"    + db.convQuotation(prcd.getPOSTAL_CD())  + "',"   //郵便番号(String)
                 + "   STATE_CD="      + prcd.getSTATE_CD()                       + ","    //都道府県コード(int)
                 + "   CITY_NAME='"    + db.convQuotation(prcd.getCITY_NAME())  + "',"   //都市名(String)
                 + "   ADDRESS='"      + db.convQuotation(prcd.getADDRESS())    + "',"   //住所(String)
                 + "   TEL_NO='"       + db.convQuotation(prcd.getTEL_NO())     + "',"   //電話番号(String)
                 + "   FAX='"          + db.convQuotation(prcd.getFAX())         + "'"   //FAX(String)
                 + " WHERE USER_ID="   + prcd.getUSER_ID();                               //ユーザーIDで検索(int)
	}
    /**
     * ユーザー情報を更新する。
     * @param Profile profile
     */
    synchronized public boolean modifyProfile( Profile prcd ){
        String  SQL = "";
        boolean rfrg = false;
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
            // プロファイルデータを更新する為のSQL文を設定
        	SQL = getModifyProfileStr( prcd, db );
        	 //DBに接続をする
        	if( db.init(ds) )	rfrg = db.dbUpdate(SQL);
        	System.err.println( SQL );
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rfrg;
    }

    /**
     * ユーザー情報を表示停止する
     * @param int userid
     */
    synchronized public boolean stopProfile( int userid ){
        return true;
    }
}
