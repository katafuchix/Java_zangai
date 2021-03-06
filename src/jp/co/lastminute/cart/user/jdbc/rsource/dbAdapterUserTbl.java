/*
 * dbAdapterUserTbl.java
 *
 * Created on 2002/04/05, 21:14
 */

package jp.co.lastminute.cart.user.jdbc.rsource;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;
import jp.co.lastminute.cart.user.jdbc.dbAdapterProfile;
/**
 *
 * @author  Takashi Yamada
 * @version 1.0
 */
public class dbAdapterUserTbl implements Serializable{
    private User us;
    private User_Tbl ut;
    //private dbDataSource dbsource;
    private DataSource ds;

    /**
     * コンストラクター
     */
    public dbAdapterUserTbl(DataSource dss) {
        this.ds = dss;
        us = null;
        ut = null;
    }

    /**
     * ユーザーが有効か否かを判断する
     * @param String e_mail
     * @return int USER_ID
     */
    synchronized public int checkEmail( String e_mail ){
        String  SQL = "";
        try{
            // ユーザデータを検索するためのSQL文を設定
            SQL  = " SELECT"
                 + " USER_ID"
                 + " FROM USER_TBL"
                 + " WHERE E_MAIL='" + e_mail + "'"
                 + " AND   DEL_FLG=0";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        int gsize = 0;
        int seruserid = 0;
        //DBに接続をする
        if( db.init(ds) ){
            Vector setd = db.dbSelect(SQL);
            dbDataAccesser dba = new dbDataAccesser(setd);
            gsize = dba.getRowsize();   // データのリストサイズを得る
            if(gsize != 0){
                seruserid = dba.getDatabyInt(0,0);
            }
        }
        if(gsize == 0){
            return gsize;
        }
        return seruserid;
     }

     /**
     * ユーザーが有効か否かを判断する
     * @param String e_mail
     * @param String passwd
     */
    synchronized public int checkUser( String e_mail, String passwd ){
        String  SQL = "";
        try{
            // ユーザデータを検索するためのSQL文を設定
            SQL  = " SELECT"
                 + " USER_ID"
                 + " FROM USER_TBL"
                 + " WHERE E_MAIL='" + e_mail + "'"
                 + " AND   PASSWD='" + passwd + "'"
                 + " AND   DEL_FLG=0";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        int gsize = 0;
        int seruserid = 0;
        //DBに接続をする
        if( db.init(ds) ){
            Vector setd = db.dbSelect(SQL);
            dbDataAccesser dba = new dbDataAccesser(setd);
            gsize = dba.getRowsize();   // データのリストサイズを得る
            if(gsize != 0){
                seruserid = dba.getDatabyInt(0,0);
            }
        }
        if(gsize == 0){
            return gsize;
        }
        return seruserid;
     }

     /**
     * ユーザーＩＤに基づき、基本的な情報を抽出する
     * @param int userid
     * @return User_Tbl 
     */
    synchronized public User_Tbl findUserTbl( int userid ){
        User us = new User();
        dbAdapterProfile fp = new dbAdapterProfile(ds);
        us = fp.findProfile(userid);
        return us.getUser();
    }

    /**
     * ユーザーの新規登録
     * @param User_Tbl
     * @return boolean TRUE/FALSE
     */
    synchronized public boolean addUserTbl( User_Tbl ut ){
        String  SQL = "";
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
             // ユーザテーブルデータを追加する為のSQL文を設定
            SQL  = " INSERT INTO"
                 + "   USER_TBL("
                 + "   USER_ID,"
                 + "   E_MAIL,"
                 + "   PASSWD,"
                 + "   AUTH_FLG,"
                 + "   DEL_FLG,"
                 + "   HTML_MAIL_OK,"
                 + "   MAIL_MAG_OK,"
                 + "   CAMPAIGNUSERFLAG,"
                 + "   DEALWATCHID,"
                 + "   MAKE_DATE"
                 + "  )VALUES("
                 + " "  + ut.getUSER_ID()                           + ","   //ユーザーID(int)
                 + " '" + db.convQuotation(ut.getE_MAIL())          + "',"  //E_MAIL(String)
                 + " '" + db.convQuotation(ut.getPASSWD())          + "',"  //パスワード(String)
                 + " '" + db.convQuotation(ut.getAUTH_FLG())        + "',"  //認証フラグ(String)
                 + " 0,"                                                    //削除フラグ(int)
                 + " '" + db.convQuotation(ut.getHTML_MAIL_OK())    + "',"  //HTMLメールOKフラグ(String)
                 + " '" + db.convQuotation(ut.getMAIL_MAG_OK())     + "',"  //メールマガジンOKフラグ(String)
                 + " "  + ut.getCAMPAIGNUSERFLAG()                  + ","   //キャンペーンユーザーフラグ(int)
                 + " '" + db.convQuotation(ut.getDEALWATCHID())     + "',"  //ディールウォッチID(String)
                 + " SYSDATE"                                               //作成日(String)
                 + " )";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        boolean rfrg = false;
         //DBに接続をする
        if( db.init(ds) ){
            rfrg = db.dbInsert(SQL);
        }
        return rfrg;
    }

    /**
     * ユーザー情報の更新
     * @param User_Tbl ut
     * @return boolean TRUE/ FALSE
     */
    synchronized public boolean modifyUserTbl( User_Tbl ut ){
        String  SQL = "";
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
            // ユーザテーブルデータを更新する為のSQL文を設定
            SQL  = " UPDATE USER_TBL"
                 + " SET"
                 + "   USER_ID="          + ut.getUSER_ID()                         + ","  //ユーザーID(int)
                 + "   E_MAIL='"          + db.convQuotation(ut.getE_MAIL())        + "'," //E_MAIL(String)
                 + "   PASSWD='"          + db.convQuotation(ut.getPASSWD())        + "'," //パスワード(String)
                 + "   AUTH_FLG='"        + db.convQuotation(ut.getAUTH_FLG())      + "'," //認証フラグ(String)
                 + "   DEL_FLG=0,"                                                         //削除フラグ(int)
                 + "   HTML_MAIL_OK='"    + db.convQuotation(ut.getHTML_MAIL_OK())  + "'," //HTMLメールOKフラグ(String)
                 + "   MAIL_MAG_OK='"     + db.convQuotation(ut.getMAIL_MAG_OK())   + "'," //メールマガジンOKフラグ(String)
                 + "   CAMPAIGNUSERFLAG=" + ut.getCAMPAIGNUSERFLAG()                + ","  //キャンペーンユーザーフラグ(int)
                 + "   DEALWATCHID='"     + db.convQuotation(ut.getDEALWATCHID())   + "'," //ディールウォッチID(String)
                 + "   UP_DATE=SYSDATE"                                                    //修正日(SYSDATE)
                 + " WHERE USER_ID="      + ut.getUSER_ID();                               //ユーザーIDで検索
        }catch(Exception ex){
            ex.printStackTrace();
        }
        boolean rfrg = false;
        //DBに接続をする
        if( db.init(ds) ){
            rfrg = db.dbUpdate(SQL);
        }
        return rfrg;
    }

    /**
     * ユーザーの使用停止
     * @param int userid
     * @return boolean TRUE / FALSE
     */
    synchronized public boolean stopUser( int userid ){
        String  SQL = "";
        try{
            // ユーザテーブルデータを削除する為のSQL文を設定
            SQL  = " UPDATE USER_TBL"
                 + " SET"
                 + "   DEL_FLG=1,"                //削除フラグ(int)
                 + "   UP_DATE=SYSDATE"           //修正日(SYSDATE)
                 + " WHERE USER_ID=" + userid;    //ユーザーIDで検索
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        boolean rfrg = false;
        //DBに接続をする
        if( db.init(ds) ){
            rfrg = db.dbUpdate(SQL);
        }
        return rfrg;
    }

    /**
     * ユーザーID作成する
     * @return int USER_ID
     */
    synchronized public int genUserid(){
        String  SQL = "";
        try{
            // ユーザIDを発生するためのSQL文を設定
            SQL  = " SELECT seq_user_id.NEXTVAL FROM DUAL ";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        int ucount = 0;
        //DBに接続をする
        if(db.init(ds)){
            Vector setd = db.dbSelect(SQL);
            dbDataAccesser dba = new dbDataAccesser(setd);
            ucount = dba.getDatabyInt(0,0);
        }
        return ucount;
    }
}
