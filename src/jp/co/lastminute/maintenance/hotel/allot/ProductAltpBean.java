package jp.co.lastminute.maintenance.hotel.allot;

import java.io.Serializable;
public class ProductAltpBean implements Serializable{
	private int productSeqNo = 0;
	private String agtCd = null;
	private String agrAreaCd = null;
	private String supNbr = null;
	private String productId = "";
	private String campaign = "NONE";
	private int lmjCampaign = 0;
	private String agtRroomtype = null;
	private String agtRroomtypeName = null;
	private int altdatFrom = 99999999;
	private int altdatTo = 0;
	private String mealCode = null;
	private int roomCapa = 0;
	private int maxNr = 0;
	private int minNr = 0;
	private int lastDay = 0;
	private int startDay = 0;
	private String altSts = null;
  private String syoType = null;
  private String priceCatch = "999999,999999,999999,999999,999999,999999,999999,999999,999999,999999,"
                           + "999999,999999,999999,999999,999999,999999";

	public boolean insertFlg = false;
	public boolean updateFlg = false;

  public ProductAltpBean(){}
  public String getAgrAreaCd() {
    return agrAreaCd;
  }
  public void setAgrAreaCd(String agrAreaCd) {
    this.agrAreaCd = agrAreaCd;
  }
  public String getAgtCd() {
    return agtCd;
  }
  public void setAgtCd(String agtCd) {
    this.agtCd = agtCd;
  }
  public String getAgtRroomtype() {
    return agtRroomtype;
  }
  public void setAgtRroomtype(String agtRroomtype) {
    this.agtRroomtype = agtRroomtype;
  }
  public String getAgtRroomtypeName() {
    return agtRroomtypeName;
  }
  public void setAgtRroomtypeName(String agtRroomtypeName) {
    this.agtRroomtypeName = agtRroomtypeName;
  }
  public String getAltSts() {
    return altSts;
  }
  public void setAltSts(String altSts) {
    this.altSts = altSts;
  }
  public String getCampaign() {
    return campaign;
  }
  public void setCampaign(String campaign) {
    this.campaign = campaign;
  }
  public void setInsertFlg(boolean insertFlg) {
    this.insertFlg = insertFlg;
  }
  public boolean isInsertFlg() {
    return insertFlg;
  }
  public void setMealCode(String mealCode) {
    this.mealCode = mealCode;
  }
  public String getMealCode() {
    return mealCode;
  }
  public void setProductId(String productId) {
    this.productId = productId;
  }
  public String getProductId() {
    return productId;
  }
  public void setSupNbr(String supNbr) {
    this.supNbr = supNbr;
  }
  public String getSupNbr() {
    return supNbr;
  }
  public String getSyoType() {
    return syoType;
  }
  public void setSyoType(String syoType) {
    this.syoType = syoType;
  }
  public void setUpdateFlg(boolean updateFlg) {
    this.updateFlg = updateFlg;
  }
  public boolean isUpdateFlg() {
    return updateFlg;
  }
  public String getPriceCatch() {
    return priceCatch;
  }
  public void setPriceCatch(String priceCatch) {
    this.priceCatch = priceCatch;
  }
  public String toString(){
    String ret = productSeqNo +","+ agtCd +","+ agrAreaCd +","+
                 supNbr +","+ productId +","+ campaign  +","+ lmjCampaign +","+
                 agtRroomtype +","+ agtRroomtypeName +","+ altdatFrom +","+
                 altdatTo +","+ mealCode +","+ roomCapa +","+ maxNr +","+ minNr +","+
                 lastDay +","+ startDay +","+ altSts +","+ syoType +","+
                 priceCatch +","+ insertFlg +","+ updateFlg;
    return ret;
  }
  public int getAltdatFrom() {
    return altdatFrom;
  }
  public void setAltdatFrom(int altdatFrom) {
    this.altdatFrom = altdatFrom;
  }
  public void setAltdatTo(int altdatTo) {
    this.altdatTo = altdatTo;
  }
  public int getAltdatTo() {
    return altdatTo;
  }
  public int getLastDay() {
    return lastDay;
  }
  public void setLastDay(int lastDay) {
    this.lastDay = lastDay;
  }
  public void setLmjCampaign(int lmjCampaign) {
    this.lmjCampaign = lmjCampaign;
  }
  public void setMaxNr(int maxNr) {
    this.maxNr = maxNr;
  }
  public int getLmjCampaign() {
    return lmjCampaign;
  }
  public int getMaxNr() {
    return maxNr;
  }
  public int getMinNr() {
    return minNr;
  }
  public void setMinNr(int minNr) {
    this.minNr = minNr;
  }
  public void setProductSeqNo(int productSeqNo) {
    this.productSeqNo = productSeqNo;
  }
  public int getProductSeqNo() {
    return productSeqNo;
  }
  public int getRoomCapa() {
    return roomCapa;
  }
  public int getStartDay() {
    return startDay;
  }
  public void setStartDay(int startDay) {
    this.startDay = startDay;
  }
  public void setRoomCapa(int roomCapa) {
    this.roomCapa = roomCapa;
  }
}
