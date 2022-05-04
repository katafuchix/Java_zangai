/*
 * dbAdapterPostalCode.java
 *
 * Created on 2002/04/19, 22:41
 */

package jp.co.lastminute.cart.user.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.lastminute.cart.user.model.*;

/**
 *
 * @author  Takashi Yamada
 * @version 1.0
 */
public class dbAdapterPostalCode implements Serializable{
    private PostalCode[] pocd;
    //private dbDataSource dbsource;
    private DataSource ds;

    /** 
     * コンストラクター
     */
    public dbAdapterPostalCode(DataSource dss){
        this.ds = dss;
        pocd = null;
    }

    /**
     * 郵便番号から住所の詳細を検索（入力が数値の場合）
     * @param int ZIPCODE
     * @param Vecter TRUE/FALSE
     */
    synchronized public PostalCode[] findAddress(int ZIPCODE){
        try{
            String zip = Integer.toString(ZIPCODE); // 数値から文字列に変換を行う
            return findAddress(zip);                // 実際に検索処理を行う
        }catch(Exception ex){   ex.printStackTrace();   }
        return null;
    }

    /**
     * 郵便番号から住所の詳細を検索（入力が文字列の場合）
     * @param String ZIPCODE
     * @param Vecter TRUE/FALSE
     */
    synchronized public PostalCode[] findAddress(String ZIPCODE){
        String  SQL = "";
        try{
            // 郵便番号データを検索するためのSQL文を設定
            SQL  = " SELECT ZC.POSTAL_CD,TN.STATE_CD,TN.STATE_NAME,SN.CITY_CD,SN.CITY_NAME,CN.STREETCD,CN.STREETNAME"
                 + " FROM POSTAL_CD_MASTER ZC,STATE_MASTER TN,CITY_MASTER SN,STREETMASTER CN"
                 + " WHERE ZC.POSTAL_CD = '" + ZIPCODE + "'"
                 + " AND   TN.STATE_CD  = ZC.STATE_CD"
                 + " AND   SN.CITY_CD   = ZC.CITY_CD"
                 + " AND   CN.STREETCD  = ZC.STREETCD";
        }catch(Exception ex){
            ex.printStackTrace();
        }
       
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        int gsize = 0;
        //DBに接続をする
        if( db.init(ds) ){
            Vector setd = db.dbSelect(SQL);
            dbDataAccesser dba = new dbDataAccesser(setd);
            gsize = dba.getRowsize();   // データのリストサイズを得る
            pocd = new PostalCode[gsize];   // リストのサイズに合わせバッファを確保
            //リスト分のデータを得る
            for(int loopcnt = 0 ; loopcnt < gsize ; loopcnt++){
                pocd[loopcnt] = new PostalCode();   // 初期化を行う
                pocd[loopcnt].setPOSTAL_CD(dba.getData(loopcnt,0));         // 郵便番号コード(String)
                pocd[loopcnt].setSTATE_CD(dba.getDatabyInt(loopcnt,1));     // 都道府県コード(int)
                pocd[loopcnt].setSTATE_NAME(dba.getData(loopcnt,2));        // 都道府県名(String)
                pocd[loopcnt].setCITY_CD(dba.getDatabyInt(loopcnt,3));      // 市区町村コード(int)
                pocd[loopcnt].setCITY_NAME(dba.getData(loopcnt,4));         // 市区町村名(String)
                pocd[loopcnt].setSTREETCD(dba.getDatabyInt(loopcnt,5));     // 町域コード(int)
                pocd[loopcnt].setSTREETNAME(dba.getData(loopcnt,6));        // 町域名(String)
            }
        }
        return pocd;
    }

    /**
     * POSTAL_CD_MASTERテーブルにデータを追加する処理を行う
     * @param PostalCode PostalCodeData
     * @param Vecter TRUE/FALSE
     */
    synchronized public boolean addPostalCode( PostalCode pc ){
        String  SQL = "";
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
            //POSTAL_CD_MASTERデータを追加する為のSQL文を設定
            SQL  = " INSERT INTO"
                 + "   POSTAL_CD_MASTER("
                 + "   POSTAL_CD,"
                 + "   CITY_CD,"
                 + "   STATE_CD,"
                 + "   STREETCD"
                 + "  )VALUES("
                 + " " + pc.getPOSTAL_CD() + ","    //郵便番号コード(int)
                 + " " + pc.getCITY_CD() + ","      //市区町村コード(int)
                 + " " + pc.getSTATE_CD() + ","     //都道府県コード(int)
                 + " " + pc.getSTREETCD()           //町域コード(int)
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
     * STREETMASTERテーブルにデータを追加する処理を行う
     * @param PostalCode PostalCodeData
     * @param Vecter TRUE/FALSE
     */
    synchronized public boolean addStreetCode( PostalCode pc ){
        String  SQL = "";
	//JDBCAdapter起動
	JdbcAdapter db = new JdbcAdapter();
	try{
            //STREETMASTERデータをを追加する為のSQL文を設定
	    SQL  = " INSERT INTO"
	         + "   STREETMASTER("
	         + "   STREETCD,"
	         + "   STREETNAME,"
	         + "   STATE_CD,"
	         + "   CITY_CD"
	         + "  )VALUES("
	         + " "  + pc.getSTREETCD() + ","                        //町域コード(int)
	         + " '" + db.convQuotation(pc.getSTREETNAME()) + "'," //町域名(String)
	         + " "  + pc.getSTATE_CD() + ","                        //都道府県コード(int)
	         + " "  + pc.getCITY_CD()                               //市区町村コード(int)
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
     * STATE_MASTERテーブルにデータを追加する処理を行う
     * @param PostalCode PostalCodeData
     * @param Vecter TRUE/FALSE
     */
    synchronized public boolean addStateCode( PostalCode pc ){
        String  SQL = "";
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
            //STATE_MASTERデータをを追加する為のSQL文を設定
            SQL  = " INSERT INTO"
                 + " STATE_MASTER( "
                 + "   STATE_CD,"
                 + "   STATE_NAME"
                 + "  )VALUES("
                 + " "  + pc.getSTATE_CD() + ","                              //都道府県コード(int)
                 + " '" + db.convQuotation(pc.getSTATE_NAME()) + "'"        //都道府県名(String)
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
     * CITY_MASTERテーブルにデータを追加する処理を行う
     * @param PostalCode PostalCodeData
     * @param Vecter TRUE/FALSE
     */
    synchronized public boolean addCityCode( PostalCode pc ){
        String  SQL = "";
        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        try{
            //CITY_MASTERデータを追加する為のSQL文を設定
            SQL  = " INSERT INTO"
                 + " CITY_MASTER("
                 + "   CITY_CD,"
                 + "   CITY_NAME,"
                 + "   STATE_CD"
                 + "  )VALUES("
                 + " "  + pc.getCITY_CD() + ","                             //市区町村コード(int)
                 + " '" + db.convQuotation(pc.getCITY_NAME()) + "',"      //市区町村名(String)
                 + " " 	+ pc.getSTATE_CD()                                  //都道府県コード(int)
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
     * STATE_CDから対応するCITY_CDとCITY_NAMEの検索を行う
     * @param String STATE_CD
     * @param Vecter TRUE/FALSE
     */
    synchronized public PostalCode[] findCity(int statecd){
        String  SQL = "";
        try{
            // STATE_CDからCITYデータを検索するためのSQL文を設定
            SQL  = " SELECT CITY_CD,CITY_NAME"
                 + " FROM CITY_MASTER"
                 + " WHERE STATE_CD = " + statecd;
        }catch(Exception ex){
            ex.printStackTrace();
        }

        //JDBCAdapter起動
        JdbcAdapter db = new JdbcAdapter();
        int gsize = 0;
        //DBに接続をする
        if( db.init(ds) ){
            Vector setd = db.dbSelect(SQL);
            dbDataAccesser dba = new dbDataAccesser(setd);
            gsize = dba.getRowsize();       // データのリストサイズを得る
            pocd = new PostalCode[gsize];    // リストのサイズに合わせバッファを確保
            //リスト分のデータを得る
            for(int loopcnt = 0 ; loopcnt < gsize ; loopcnt++){
                pocd[loopcnt] = new PostalCode();                            // 初期化を行う
                pocd[loopcnt].setCITY_CD(dba.getDatabyInt(loopcnt,0));      // 市区町村コード(int)
                pocd[loopcnt].setCITY_NAME(dba.getData(loopcnt,1));         // 市区町村名(String)
            }
        }
        return pocd;
    }
}