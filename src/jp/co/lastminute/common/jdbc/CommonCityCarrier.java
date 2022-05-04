/*
 * CommonCityCarrier.java
 *
 * Created on 2002/07/05, 20:19
 */

package jp.co.lastminute.common.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.dbDataAccesser;

/**
 *
 * @author  skondo
 * @version 
 */
public class CommonCityCarrier implements Serializable{
    private DataSource ds;

    /** Creates new CommonCityCarrier */
    public CommonCityCarrier( DataSource dss ) {
        this.ds = dss;
    }
    public CommonCityCarrier() {
    }
    
    /**
     *
     */
    synchronized public String getAirportName(String code){
        JdbcAdapter db = new JdbcAdapter();
        try{
        if( db.init(ds) ){
            Vector vc = db.dbSelect( "SELECT JAPAN_NAME FROM MAST_AIRPORT WHERE CODE='" + code + "'" );
            dbDataAccesser dba = new dbDataAccesser( vc );
            return dba.getData(0,0);
        }
        }catch(Exception ex){}
        return "";
    }
    /**
     *
     */
    synchronized public String getCarrierName(String code){
        JdbcAdapter db = new JdbcAdapter();
        try{
        if( db.init(ds) ){
            Vector vc = db.dbSelect( "SELECT JAPAN_NAME FROM MAST_CARRIER WHERE CODE='" + code + "'" );
            dbDataAccesser dba = new dbDataAccesser( vc );
            return dba.getData(0,0);
        }
        }catch(Exception ex){}
        return "";
    }
    /**
     *
     */
    synchronized public void changeStock( String jan_cd, boolean flg ){
        String add = "+1";
        if( flg ){  add = "-1"; }
        JdbcAdapter db = new JdbcAdapter();
        try{
        if( db.init(ds) ){
            db.dbInsert( "UPDATE CALLEGEPRODUCT_TBL SET ALLOTNUM=ALLOTNUM"+ add + " WHERE JAN_CD='" + jan_cd + "'" );            
                    //クエリー出力
        }
        db = null;
        }catch(Exception ex){}
    }
    synchronized public void changeStock( String jan_cd, int num, boolean flg ){
        String add = "+" +  num ;
        if( flg ){  add = "-" + num ; }
        JdbcAdapter db = new JdbcAdapter();
        try{
        if( db.init(ds) ){
            db.dbInsert( "UPDATE CALLEGEPRODUCT_TBL SET ALLOTNUM=ALLOTNUM"+ add + " WHERE JAN_CD='" + jan_cd + "'" );            
                    //クエリー出力
        }
        db = null;
        }catch(Exception ex){}
    }
    /**
     * 都道府県名を取得する。
     */
    synchronized public String getStateName(int num){
        try{
            return state_name_[num];
        }catch(Exception ex){}
        return "";
    }
    synchronized public String getStateName(String str){
        int num = 0;
        try{
            num = Integer.parseInt( str );
            return state_name_[num];
        }catch(Exception ex){}
        return "";
    }
    public static final String[] state_name_ = {"",
						  "\u5317\u6D77\u9053",//北海道",
						  "\u9752\u68EE\u770C",//青森県",
						  "\u5CA9\u624B\u770C", //岩手県",
						  "\u5BAE\u57CE\u770C",//宮城県",
						  "\u79CB\u7530\u770C",//秋田県",
						  "\u5C71\u5F62\u770C", //山形県",
						  "\u798F\u5CF6\u770C", //福島県",
						  "\u8328\u57CE\u770C", //茨城県",
						  "\u6803\u6728\u770C", //栃木県",
						  "\u7FA4\u99AC\u770C", //群馬県",
						  "\u57FC\u7389\u770C", //埼玉県",
						  "\u5343\u8449\u770C", //千葉県",
						  "\u6771\u4EAC\u90FD", //東京都",
						  "\u795E\u5948\u5DDD\u770C", //神奈川県",
						  "\u5C71\u68A8\u770C", //山梨県",
						  "\u9577\u91CE\u770C", //長野県",
						  "\u65B0\u6F5F\u770C", //新潟県",
						  "\u5BCC\u5C71\u770C", //富山県",
						  "\u77F3\u5DDD\u770C", //石川県",
						  "\u798F\u4E95\u770C", //福井県",
						  "\u5C90\u961C\u770C", //岐阜県",
						  "\u9759\u5CA1\u770C", //静岡県",
						  "\u611B\u77E5\u770C", //愛知県",
						  "\u4E09\u91CD\u770C", //三重県",
						  "\u6ECB\u8CC0\u770C", //滋賀県",
						  "\u4EAC\u90FD\u5E9C", //京都府",
						  "\u5927\u962A\u5E9C", //大阪府",
						  "\u5175\u5EAB\u770C", //兵庫県",
						  "\u5948\u826F\u770C", //奈良県",
						  "\u548C\u6B4C\u5C71\u770C", //和歌山県",
						  "\u9CE5\u53D6\u770C", //鳥取県",
						  "\u5CF6\u6839\u770C", //島根県",
						  "\u5CA1\u5C71\u770C", //岡山県",
						  "\u5E83\u5CF6\u770C", //広島県",
						  "\u5C71\u53E3\u770C", //山口県",
						  "\u5FB3\u5CF6\u770C", //徳島県",
						  "\u9999\u5DDD\u770C", //香川県",
						  "\u611B\u5A9B\u770C", //愛媛県",
						  "\u9AD8\u77E5\u770C", //高知県",
						  "\u798F\u5CA1\u770C", //福岡県",
						  "\u4F50\u8CC0\u770C", //佐賀県",
						  "\u9577\u5D0E\u770C", //長崎県",
						  "\u718A\u672C\u770C", //熊本県",
						  "\u5927\u5206\u770C", //大分県",
						  "\u5BAE\u5D0E\u770C", //宮崎県",
						  "\u9E7F\u5150\u5CF6\u770C", //鹿児島県",
						  "\u6C96\u7E04\u770C", //沖縄県",
                                                  "",
                                                  "",
                                                  "\u6D77\u5916"}; //海外"};
}
