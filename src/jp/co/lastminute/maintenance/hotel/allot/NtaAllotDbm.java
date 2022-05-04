package jp.co.lastminute.maintenance.hotel.allot;

import jp.co.yobrain.util.jdbc.DataSourceMaker;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.math.*;

public class NtaAllotDbm extends DataSourceMaker
{
  private Connection conn = null;

  private static final int PRODUCTROWS = 500;

	public NtaAllotDbm(){}

  public void init(){
    super.init();
    try {
      conn = getDatasource().getConnection();
    } catch(Exception e){ NtaAllotUpdate.logs(e); System.exit(3);}
  }

  public void release(){
    try {
      conn.close();
    } catch(SQLException e){ NtaAllotUpdate.logs(e);}
  }

  protected boolean doExec(String query){
NtaAllotUpdate.logs(query);
    Statement stmts = null; //ステートメント
    try{
      stmts = conn.createStatement();
      if (stmts.executeUpdate(query) > 0) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      NtaAllotUpdate.logs(e);
      return false;
    }
    finally {
      try{
        stmts.close();
        wait(10);
      } catch (Exception e) {}
    }
  }
  protected Vector doSelect(String query, int maxrows){
NtaAllotUpdate.logs(query);
    Statement stmt = null; //ステートメント
    ResultSet rset = null; //レコードセット
    ResultSetMetaData metaData; //一時保存メタデータ
    int numberOfColumns;
    Vector rows = new Vector(); //行
    try {
      stmt = conn.createStatement();
      if(maxrows > 0) stmt.setMaxRows(maxrows);
      rset = stmt.executeQuery(query);
      metaData = rset.getMetaData();
      numberOfColumns = metaData.getColumnCount();
      //////カラムのint配列を取得・カラム名のストリング配列を取得///////
      //////行ベクターにデータを格納/////
      while (rset.next()) {
        Vector newRow = new Vector();
        for (int i = 1; i <= numberOfColumns; i++) {
          newRow.add(rset.getObject(i));
        }
        rows.add(newRow);
      }
      //ステートメントをクローズする//
    } catch (Exception e) {
      NtaAllotUpdate.logs(e);
    }
    finally {
      try{
        rset.close();
        stmt.close();
      } catch (Exception e) {}
    }
    return rows;
  }

  public Vector getProductRecords(int produktKey){
    String sql = "SELECT PRODUCT_SEQ_NO, AGT_CD, AGR_AREA_CD, SUPNBR, PRODUCT_ID, CAMPAIGN, "
    + "LMJCAMPAIGN, AGT_ROOMTYPE, AGT_ROOMTYPE_NAME, ALTDAT_FROM, ALTDAT_TO, MEAL_CODE, "
    + "ROOM_CAPA, MAX_NR, MIN_NR, LAST_DAY, START_DAY, ALTSTS, SYO_TYPE, PRICE_CATCH "
    + "FROM PRODUCT_ALTP WHERE PRODUCT_SEQ_NO > " + produktKey + " ORDER BY PRODUCT_SEQ_NO";
    return doSelect(sql, PRODUCTROWS);
  }
  public int getProductSeq(){
    String sql = "SELECT PRODUCT_ALTP_SEQ.NEXTVAL FROM DUAL";
    Vector rslt = doSelect(sql, 1);
    if(rslt.size() == 0) return 0;
    return getIntCol(((Vector)rslt.firstElement()).firstElement());
  }
  public int getIntCol(Object obj){
    if(obj == null) return 0;
    try {
      if(obj instanceof BigDecimal)
        return ((BigDecimal)obj).intValue();
      if(obj instanceof Integer)
        return ((Integer)obj).intValue();
    } catch (Exception e){}
    return -99999;
  }
  public String getStringCol(Object obj){
    if(obj == null) return "";
    try {
      if(((String)obj).length() == 0) return null;
      return (String)obj;
    } catch (Exception e){}
    return "ERROR";
  }
  public String setStringCol(String str){
    if(str.length() == 0) return "null";
    return "\'" + str + "\'";
  }
  public boolean getWebRecord(String productid, int altdat){
    String sql = "SELECT COUNT(*) FROM WEBALTP WHERE PRODUCT_ID = "
               + setStringCol(productid) + " AND ALTDAT = " + altdat;
    Vector rslt = doSelect(sql, 0);
    if(rslt.size() == 0) return false;
    if(getIntCol(((Vector)rslt.firstElement()).firstElement()) > 0) return true;
    return false;
  }

  public void updateWebRecord(WebAltpBean webalt){
    String sql = "";
    if(webalt.isInsertFlg()){
      // INSERT処理
      sql = "INSERT INTO WEBALTP (SEQ_NO, PRODUCT_SEQ_NO, PRODUCT_ID, ALTDAT, "
          + "HAVEALT, ALLOTNUM, PRICE, PRICE2, PRICE3, PRICE4, PRICE5, PRICEA1, "
          + "PRICEA2, PRICEA3, PRICEA4, PRICEB1, PRICEB2, PRICEB3, PRICEB4, "
          + "PRICEC1, PRICED1, PRICEE, PRICE_RP, MAKE_DATE, UP_DATE) VALUES ("
      + "WEB_ALTP_SEQ.NEXTVAL, "
      + webalt.getProductSeqNo() + ","
      + setStringCol(webalt.getProductId()) + ","
      + webalt.getAltDat() + ","
      + webalt.getHaveAlt() + ","
      + webalt.getAltNum() + ",";
      for(int i1 = 0; i1 < webalt.prices.length; i1++)
        sql += webalt.prices[i1] + ",";
      sql += " SYSDATE , SYSDATE)";
      doExec(sql);
    } else if(webalt.isUpdateFlg()){
      // UPDATE処理
      sql = "UPDATE WEBALTP SET "
          + "HAVEALT = " + webalt.getHaveAlt()
          + ",ALLOTNUM = " + webalt.getAltNum()
          + ",PRICE = " + webalt.prices[0]
          + ",PRICE2 = " + webalt.prices[1]
          + ",PRICE3 = " + webalt.prices[2]
          + ",PRICE4 = " + webalt.prices[3]
          + ",PRICE5 = " + webalt.prices[4]
          + ",PRICEA1 = " + webalt.prices[5]
          + ",PRICEA2 = " + webalt.prices[6]
          + ",PRICEA3 = " + webalt.prices[7]
          + ",PRICEA4 = " + webalt.prices[8]
          + ",PRICEB1 = " + webalt.prices[9]
          + ",PRICEB2 = " + webalt.prices[10]
          + ",PRICEB3 = " + webalt.prices[11]
          + ",PRICEB4 = " + webalt.prices[12]
          + ",PRICEC1 = " + webalt.prices[13]
          + ",PRICED1 = " + webalt.prices[14]
          + ",PRICEE = " + webalt.prices[15]
          + ",PRICE_RP = " + webalt.prices[16]
          + ", UP_DATE = SYSDATE "
          + " WHERE PRODUCT_ID = " + setStringCol(webalt.getProductId())
          + " AND ALTDAT = " + webalt.getAltDat();
      doExec(sql);
    }
  }
  public void updateProductRecord(ProductAltpBean prdct){
    String sql = "";
    if(prdct.isInsertFlg()){
      sql = "INSERT INTO PRODUCT_ALTP (PRODUCT_SEQ_NO, AGT_CD, AGR_AREA_CD, "
          + "SUPNBR, PRODUCT_ID, CAMPAIGN, LMJCAMPAIGN, AGT_ROOMTYPE, "
          + "AGT_ROOMTYPE_NAME, ALTDAT_FROM, ALTDAT_TO, MEAL_CODE, ROOM_CAPA, "
          + "MAX_NR, MIN_NR, LAST_DAY, START_DAY, ALTSTS, SYO_TYPE, PRICE_CATCH, "
          + "MAKE_DATE, UP_DATE) VALUES ("
          + prdct.getProductSeqNo() + ","
          + setStringCol(prdct.getAgtCd()) + ","
          + setStringCol(prdct.getAgrAreaCd()) + ","
          + setStringCol(prdct.getSupNbr()) + ","
          + setStringCol(prdct.getProductId()) + ","
          + setStringCol(prdct.getCampaign()) + ","
          + prdct.getLmjCampaign() + ","
          + setStringCol(prdct.getAgtRroomtype()) + ","
          + setStringCol(prdct.getAgtRroomtypeName()) + ","
          + prdct.getAltdatFrom() + ","
          + prdct.getAltdatTo() + ","
          + setStringCol(prdct.getMealCode()) + ","
          + prdct.getRoomCapa() + ","
          + prdct.getMaxNr() + ","
          + prdct.getMinNr() + ","
          + prdct.getLastDay() + ","
          + prdct.getStartDay() + ","
          + setStringCol(prdct.getAltSts()) + ","
          + setStringCol(prdct.getSyoType()) + ","
          + setStringCol(prdct.getPriceCatch()) + ","
          + "SYSDATE, SYSDATE)";
      doExec(sql);
    } else if(prdct.isUpdateFlg()){
      sql = "UPDATE PRODUCT_ALTP SET "
          + "ALTDAT_FROM = " + prdct.getAltdatFrom() + ","
          + "ALTDAT_TO = " + prdct.getAltdatTo() + ","
          + "PRICE_CATCH = " + setStringCol(prdct.getPriceCatch())
          + ", UP_DATE = SYSDATE "
          + "WHERE PRODUCT_SEQ_NO = " + prdct.getProductSeqNo();
      doExec(sql);
    }
  }
}

