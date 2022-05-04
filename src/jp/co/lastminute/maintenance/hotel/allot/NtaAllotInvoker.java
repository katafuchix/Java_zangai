package jp.co.lastminute.maintenance.hotel.allot;

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class NtaAllotInvoker
{
	private NtaAllotDbm dbm;
  private List productList;
  private final int CSVCOLCOUNT = 55;

  public NtaAllotInvoker(){}

  public void init(NtaAllotDbm dbm){
    this.dbm = dbm;
    productList = new ArrayList();
  }
  //　PRODUCT_ALTPの全データのリストを作成
  public void createPrductList(){
    int produktKey = 0;
    ListIterator it = productList.listIterator();
    while(true){
      ProductAltpBean prdct = null;
      Vector rslt = null;

      rslt = dbm.getProductRecords(produktKey);
      int count = rslt.size();
      if(count == 0) break;
      for(int i1 = 0; i1 < count; i1++){
        prdct = makeProductObj(new ProductAltpBean(), (Vector)rslt.get(i1));
        it.add(prdct);
      }
      produktKey = prdct.getProductSeqNo();
NtaAllotUpdate.logs("SEQ" + produktKey);
    }
    it = null;
  }
  // PRODUCT_ALTPのデータの作成
  private ProductAltpBean makeProductObj(ProductAltpBean prdct, Vector list){
    prdct.setProductSeqNo(dbm.getIntCol(list.get(0)));
    prdct.setAgtCd(dbm.getStringCol(list.get(1)));
    prdct.setAgrAreaCd(dbm.getStringCol(list.get(2)));
    prdct.setSupNbr(dbm.getStringCol(list.get(3)));
    prdct.setProductId(dbm.getStringCol(list.get(4)));
    prdct.setCampaign(dbm.getStringCol(list.get(5)));
    prdct.setLmjCampaign(dbm.getIntCol(list.get(6)));
    prdct.setAgtRroomtype(dbm.getStringCol(list.get(7)));
    prdct.setAgtRroomtypeName(dbm.getStringCol(list.get(8)));
    prdct.setAltdatFrom(dbm.getIntCol(list.get(9)));
    prdct.setAltdatTo(dbm.getIntCol(list.get(10)));
    prdct.setMealCode(dbm.getStringCol(list.get(11)));
    prdct.setRoomCapa(dbm.getIntCol(list.get(12)));
    prdct.setMaxNr(dbm.getIntCol(list.get(13)));
    prdct.setMinNr(dbm.getIntCol(list.get(14)));
    prdct.setLastDay(dbm.getIntCol(list.get(15)));
    prdct.setStartDay(dbm.getIntCol(list.get(16)));
    prdct.setAltSts(dbm.getStringCol(list.get(17)));
    prdct.setSyoType(dbm.getStringCol(list.get(18)));
    prdct.setPriceCatch(dbm.getStringCol(list.get(19)));
    return prdct;
  }

  // 在庫更新ファイルを読み込みPRODUCT_ALTPデータのリストの更新
  // WEBALTPの更新・インサート
  public void updateWebAltp(String filename){
    try{
      BufferedReader in = new BufferedReader(new FileReader(filename));
      String record = null;
      in.readLine();// 一行目は捨てる
      while((record = in.readLine()) != null){
        // Length 0 及び ＃ コメントは削除
        if(record.length() == 0 || record.startsWith("#")) continue;
        String[] csvpart = spritRecord(record, ",");
        if(csvpart == null){
          NtaAllotUpdate.logs("spritRecord fail:" + record);
          continue;
        }
        if(csvpart.length != CSVCOLCOUNT){
          NtaAllotUpdate.logs("CSV Record illigal:" + record);
          NtaAllotUpdate.logs("Count=" + csvpart.length);
          continue;
        }
        // PRODUCT_IDの新規作成
        csvpart[1] = csvpart[3] + "-" + csvpart[15];
        // PRODUCT_ALTPのチェック
        ProductAltpBean prodct = checkProductAltp(csvpart);
        // WEB_ALTPのチェック
        checkWebAltp(prodct, csvpart);
      }
      in.close();
    } catch(Exception ex){}
  }
  //　文字列を分割する
  private static String[] spritRecord(String record, String input){
    String[] ret = null;
    try {
      ret = Pattern.compile(input).split(record, -1);
      for(int i1 = 0; i1 < ret.length; i1++){
        if(ret[i1].length() > 0){
          ret[i1] = Pattern.compile("\"").matcher(ret[i1]).replaceAll("");
          ret[i1].trim();
        }
      }
    } catch(Exception ex){
NtaAllotUpdate.logs(ex);
    }
    return ret;
  }
  //　文字列からIntegerクラスを作成
  private static int getIntValue(String str){
    if(str.length() == 0) return 0;
    try{
      return new Integer(str).intValue();
    } catch(Exception ex){}
    return 0;
  }
  //　最安値表示の更新
  private static String checkPriceCatch(String prdctstr, String[] csvpart){
    String[] prdctval = spritRecord(prdctstr, ",");
    for(int i1 = 0; i1 < prdctval.length; i1++){
      if(getIntValue(prdctval[i1]) > getIntValue(csvpart[i1 + 17])){
        prdctval[i1] = new Integer(getIntValue(csvpart[i1 + 17])).toString();
      }
    }
    String ret = "";
    for(int i1 = 0; i1 < prdctval.length; i1++){
      ret += prdctval[i1] + ",";
    }
    return ret.substring(0, ret.length() -1);
  }

  //　CSVファイルの１レコードからPRODUCT_ALTPデータのリストの更新
  private ProductAltpBean checkProductAltp(String[] csvpart){

    ListIterator it = productList.listIterator();
    while(it.hasNext()){
      ProductAltpBean prdct = (ProductAltpBean)it.next();
      if(prdct.getAgtCd().equals(csvpart[7]) &&
         prdct.getSupNbr().equals(csvpart[3]) &&
         prdct.getProductId().equals(csvpart[1]) &&
         prdct.getCampaign().equals(csvpart[2]) &&
         prdct.getAgtRroomtype().equals(csvpart[15]) &&
         prdct.getMealCode().equals(csvpart[16])){
        //　該当データ有り、更新
        int altdat = getIntValue(csvpart[9]);
        //　在庫開始日の更新
        if( altdat < prdct.getAltdatFrom())
          prdct.setAltdatFrom(altdat);
        //　在庫終了日の更新
        if( altdat > prdct.getAltdatTo())
          prdct.setAltdatTo(altdat);
        //　最安値表示の更新
        prdct.setPriceCatch(checkPriceCatch(prdct.getPriceCatch(), csvpart));
        prdct.setUpdateFlg(true);
        return prdct;
      }
    }
    // 新規登録
    int product_seq = dbm.getProductSeq();
    ProductAltpBean prdct = new ProductAltpBean();
    prdct.setProductSeqNo(product_seq);
    prdct.setAgtCd(csvpart[7]);
    prdct.setAgrAreaCd(csvpart[6]);
    prdct.setSupNbr(csvpart[3]);
    prdct.setProductId(csvpart[1]);
    prdct.setCampaign(csvpart[2]);
    prdct.setLmjCampaign(0);
    prdct.setAgtRroomtype(csvpart[15]);
    prdct.setAgtRroomtypeName(csvpart[49]);
    prdct.setAltdatFrom(getIntValue(csvpart[9]));
    prdct.setAltdatTo(getIntValue(csvpart[9]));
    prdct.setMealCode(csvpart[16]);
    prdct.setRoomCapa(getIntValue(csvpart[12]));
    prdct.setMaxNr(getIntValue(csvpart[13]));
    prdct.setMinNr(getIntValue(csvpart[14]));
    prdct.setLastDay(getIntValue(csvpart[34]));
    prdct.setStartDay(getIntValue(csvpart[35]));
    prdct.setAltSts(csvpart[38]);
    prdct.setSyoType(csvpart[39]);
    prdct.setPriceCatch(checkPriceCatch(prdct.getPriceCatch(), csvpart));
    prdct.setInsertFlg(true);
    productList.add(prdct);
    return prdct;
  }
  // WEBALTPの更新・インサート
  private void checkWebAltp(ProductAltpBean prodct, String[] csvpart){
    WebAltpBean webalt = new WebAltpBean();
    // PRODUCT_ID + ALTDAT で該当のレコードが存在するかどうかのチェック
    if(dbm.getWebRecord(csvpart[1], getIntValue(csvpart[9]))){
      // 更新
      webalt.setUpdateFlg(true);
    } else {
      // 新規追加
      webalt.setInsertFlg(true);
    }
    // データの設定
    webalt.setProductSeqNo(prodct.getProductSeqNo());
    webalt.setProductId(csvpart[1]);
    webalt.setAltDat(getIntValue(csvpart[9]));
    webalt.setHaveAlt(getIntValue(csvpart[8]));
    webalt.setAltNum(getIntValue(csvpart[11]));
    for(int i1 = 0;i1 < webalt.prices.length; i1 ++){
      webalt.prices[i1] = getIntValue(csvpart[17 + i1]);
    }
    dbm.updateWebRecord(webalt);
  }

  public void updateProductAltp(){
    ListIterator it = productList.listIterator();
    while(it.hasNext()){
      ProductAltpBean prdct = (ProductAltpBean)it.next();
      if(prdct.isInsertFlg() || prdct.isUpdateFlg())
        dbm.updateProductRecord(prdct);
    }
  }
  public void release(){
    productList.clear();
    productList = null;
  }
}
