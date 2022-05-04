/*
 * dbAdapter.java
 *
 * Created on 2002/03/31, 22:41
 */

package jp.co.lastminute.cart.jdbc;

import java.io.*;
import java.math.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.lastminute.cart.model.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class dbAdapterProductSend implements Serializable{
    private DataSource ds;
    /**
     * コンストラクター
     */
    public dbAdapterProductSend(){
    }
    public dbAdapterProductSend(DataSource dss ){
        this.ds = dss;
    }
    /**
     * 配送番号を取得
     * CREATE　SEQUENCE　SEND_ID
     */
    synchronized public int getSEND_ID(){
        JdbcAdapter db = new JdbcAdapter();
        try{
        if( db.init(ds) ){
            Vector vc = db.dbSelect( "SELECT SEND_ID.NEXTVAL FROM DUAL" );
            dbDataAccesser dba = new dbDataAccesser( vc );
            return dba.getDatabyInt(0,0);
        }
        }catch(Exception ex){}
        return -1;
    }
    /**
     * 配送情報追加
     * @param Product_Send　prs
     * @param DataSource ds
     * @return boolean true/false
     */
    synchronized public boolean addProductSend( Product_Send prs ){
        int orderNo;
        //SQL文を生成
        String SQL = "";
        try{
            SQL = getAddProductSendStr( prs );
            JdbcAdapter db = new JdbcAdapter();
            System.err.println(SQL);
            if( db.init(this.ds) )	return db.dbInsert( SQL );
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //JDBCAdapter起動
        
        //クエリー出力
        return false;
    }
    /**
     * 配送先のSQL追加
     */
    synchronized public String getAddProductSendStr( Product_Send prs ) throws Exception{
    	return " INSERT INTO PRODUCT_SEND( "
                + " SEND_ID, "
                + " USER_ID, "
                + " FIRST_NAME, "
                + " LASTNAME, "
                + " E_MAIL,"
                + " ORDER_NO, "
                + " POSTAL_CD, "
                + " STATE_CD, "
                + " CITY_NAME, "
                + " ADDRESS, "
                + " DELIVERYNAME, "
                + " TEL_NO, "
                + " FAX, "
                + " MOBILE_E_MAIL, "
                + " DEL_FLG, "
                + " ADDRESSFLG, "
                + " SEND_TYPE_ID, "
                + " BUILDADDRESS, "
                + " MAIN_STATE, "
                + " MAIN_ADDRESS, "
                + " MAIN_LASTNAME_KANA, "
                + " MAIN_FIRSTNAME_KANA, "
                + " MAIN_LASTNAME_KANJI, "
                + " MAIN_FIRSTNAME_KANJI, "
                + " MAIN_POSTALCODE, "
                + " TICKETING, "
                + " PAYMENT_WAY, "
                + " DERIVER_MONTH, "
                + " DERIVER_DAY, "
                + " DERIVER_TIME, "
                + " MAKE_DATE) "
                + " VALUES ( "
                + getSEND_ID() + ", "
                +  prs.getUSER_ID() + ", "
                + " '" + prs.getFIRST_NAME() + "', "
                + " '" + prs.getLASTNAME() + "', "
                + " '" + prs.getE_MAIL() + "', "
                + " '" + prs.getORDER_NO() + "', "
                + " '" + prs.getPOSTAL_CD() + "', "
                + prs.getSTATE_CD() + ", "
                + " '" + prs.getCITY_NAME() + "', "
                + " '" + prs.getADDRESS() + "', "
                + " '" + prs.getDELIVERYNAME() + "', "
                + " '" + prs.getTEL_NO() + "', "
                + " '" + prs.getFAX() + "', "
                + " '" + prs.getMOBILE_E_MAIL() + "', "
                + 0 + ", "									// DEL_FLG
                + prs.getADDRESSFLG() + ", "
                + 0 + ", "
                + "'" + prs.getBuildaddress() + "', " 
                + prs.getMain_state()  + ", "
                + "'" + prs.getMain_address() + "', "
                + "'" + prs.getMain_lastname_kana() + "',"
                + "'" + prs.getMain_firstname_kana() + "', "
                + "'" + prs.getMain_lastname_kanji() + "',"
                + "'" + prs.getMain_firstname_kanji() + "',"
                + "'" + prs.getMain_postalcode() + "',"
                + "'" + prs.getTicketing() + "', "
                + "'" + prs.getPayment_way() + "',"
                + "'" + prs.getDeriver_month() + "',"
                + "'" + prs.getDeriver_day() + "', "
                + "'" + prs.getDeriver_time() + "', "
            	+ "SYSDATE)";    	
    }
    /**
     * 配送情報のモディファイ
     * @param Product_Send　ps
     * @param DataSource ds
     * @return boolean true/false
     */
    synchronized public boolean modifyProductSend( Product_Send prs ){
        //SQL文を生成
        String SQL = "";
        try{
            SQL = getModifyProductSendStr( prs );
            JdbcAdapter db = new JdbcAdapter();
            System.err.println(SQL);
            if( db.init(this.ds) )	return db.dbInsert( SQL );
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * 更新の
     */
    synchronized public static String getModifyProductSendStr( Product_Send prs ) throws Exception{
    	return "UPDATE PRODUCT_SEND SET "
                + " USER_ID=" + prs.getUSER_ID()  + ", "
                + " FIRST_NAME='" +  prs.getFIRST_NAME()  + "', "
                + " LASTNAME='" + prs.getLASTNAME()  + "', "
                + " E_MAIL='" + prs.getE_MAIL() + "', "
                + " ORDER_NO=" + prs.getORDER_NO()  + ", "
                + " POSTAL_CD='" + prs.getPOSTAL_CD() + "', "
                + " STATE_CD=" + prs.getSTATE_CD() + ", "
                + " CITY_NAME='" + prs.getCITY_NAME()  + "', "
                + " ADDRESS='" + prs.getADDRESS() + "', "
                + " DELIVERYNAME='" + prs.getDELIVERYNAME() + "', "
                + " TEL_NO='" + prs.getTEL_NO() + "', "
                + " FAX='" + prs.getFAX() + "', "
                + " MOBILE_E_MAIL='" + prs.getMOBILE_E_MAIL() + "', "
                + " ADDRESSFLG=" + prs.getADDRESSFLG() + ", "
                + " BUILDADDRESS='" + prs.getBuildaddress() + "', "
                + " MAIN_STATE=" + prs.getMain_state() + ", "
                + " MAIN_ADDRESS='" + prs.getMain_address() + "', "
                + " MAIN_LASTNAME_KANA='" + prs.getMain_lastname_kana() + "', "
                + " MAIN_FIRSTNAME_KANA='" + prs.getMain_firstname_kana() + "', "
                + " MAIN_LASTNAME_KANJI='" + prs.getMain_lastname_kanji() + "', "
                + " MAIN_FIRSTNAME_KANJI='" + prs.getMain_firstname_kanji() + "', "
                + " MAIN_POSTALCODE='" + prs.getMain_postalcode() + "', "
                + " TICKETING='" + prs.getTicketing() + "', "
                + " PAYMENT_WAY='" + prs.getPayment_way() + "', "
                + " DERIVER_MONTH='" + prs.getDeriver_month() + "', "
                + " DERIVER_DAY='" + prs.getDeriver_day() + "', "
                + " DERIVER_TIME='" + prs.getDeriver_time() + "', "
                + " UP_DATE=SYSDATE "
                + " WHERE SEND_ID=" + prs.getSEND_ID();
    }
}
