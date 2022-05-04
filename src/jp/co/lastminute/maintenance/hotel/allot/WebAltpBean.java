package jp.co.lastminute.maintenance.hotel.allot;

import java.io.Serializable;

public class WebAltpBean implements Serializable{
  private int seqNo = 0;
  private int productSeqNo = 0;
	private String productId = "";
	private int altDat = 0;
	private int haveAlt = 0;
	private int altNum = 0;

  public int[] prices = new int[17];

  // KNT UPDATE ’Ç‰Á•ª
  private String canpaign = "";
  private String mealCode = "";
  private String altsts = "";
  private String syoType = "";
  private String agtRoomTypeName = "";
  private String supnbr = "";
  private int ctrnbr = 0;
  private int ctrnbr1 = 0;
  private String agrAreaCd= "";
  private String roomType = "";
  private String roomCapa = "";
  private int maxNr = 0;
  private int minNr = 0;
  private int lastDay = 0;
  private int startDay = 0;


	public boolean insertFlg = false;
	public boolean updateFlg = false;

  public WebAltpBean(){
  }
  public int getAltDat() {
    return altDat;
  }
  public void setAltDat(int altDat) {
    this.altDat = altDat;
  }
  public int getAltNum() {
    return altNum;
  }
  public void setAltNum(int altNum) {
    this.altNum = altNum;
  }
  public int getHaveAlt() {
    return haveAlt;
  }
  public void setHaveAlt(int haveAlt) {
    this.haveAlt = haveAlt;
  }
  public boolean isInsertFlg() {
    return insertFlg;
  }
  public void setInsertFlg(boolean insertFlg) {
    this.insertFlg = insertFlg;
  }
  public String getProductId() {
    return productId;
  }
  public void setProductId(String productId) {
    this.productId = productId;
  }
  public int getProductSeqNo() {
    return productSeqNo;
  }
  public void setProductSeqNo(int productSeqNo) {
    this.productSeqNo = productSeqNo;
  }
  public void setSeqNo(int seqNo) {
    this.seqNo = seqNo;
  }
  public int getSeqNo() {
    return seqNo;
  }
  public boolean isUpdateFlg() {
    return updateFlg;
  }
  public void setUpdateFlg(boolean updateFlg) {
    this.updateFlg = updateFlg;
  }
  public int[] getPrices() {
    return prices;
  }
  public void setPrices(int[] prices) {
    this.prices = prices;
  }
  public String getCanpaign() {
    return canpaign;
  }
  public void setCanpaign(String canpaign) {
    this.canpaign = canpaign;
  }
  public void setMealCode(String mealCode) {
    this.mealCode = mealCode;
  }
  public String getMealCode() {
    return mealCode;
  }
  public String getAgtRoomTypeName() {
    return agtRoomTypeName;
  }
  public void setAgtRoomTypeName(String agtRoomTypeName) {
    this.agtRoomTypeName = agtRoomTypeName;
  }
  public void setAltsts(String altsts) {
    this.altsts = altsts;
  }
  public String getAltsts() {
    return altsts;
  }
  public String getSyoType() {
    return syoType;
  }
  public void setSyoType(String syoType) {
    this.syoType = syoType;
  }
  public String getAgrAreaCd() {
    return agrAreaCd;
  }
  public void setAgrAreaCd(String agrAreaCd) {
    this.agrAreaCd = agrAreaCd;
  }
  public int getCtrnbr() {
    return ctrnbr;
  }
  public void setCtrnbr(int ctrnbr) {
    this.ctrnbr = ctrnbr;
  }
  public void setCtrnbr1(int ctrnbr1) {
    this.ctrnbr1 = ctrnbr1;
  }
  public int getCtrnbr1() {
    return ctrnbr1;
  }
  public int getMaxNr() {
    return maxNr;
  }
  public void setMaxNr(int maxNr) {
    this.maxNr = maxNr;
  }
  public int getMinNr() {
    return minNr;
  }
  public void setMinNr(int minNr) {
    this.minNr = minNr;
  }
  public String getRoomType() {
    return roomType;
  }
  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }
  public String getSupnbr() {
    return supnbr;
  }
  public void setSupnbr(String supnbr) {
    this.supnbr = supnbr;
  }
  public String getRoomCapa() {
    return roomCapa;
  }
  public void setRoomCapa(String roomCapa) {
    this.roomCapa = roomCapa;
  }
  public int getLastDay() {
    return lastDay;
  }
  public void setLastDay(int lastDay) {
    this.lastDay = lastDay;
  }
  public int getStartDay() {
    return startDay;
  }
  public void setStartDay(int startDay) {
    this.startDay = startDay;
  }
}
