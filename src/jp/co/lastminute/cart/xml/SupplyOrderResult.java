package jp.co.lastminute.cart.xml;

import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * サプライヤーから帰ってきたデータを保持する
 */
public class SupplyOrderResult extends XmlBeanBase implements Serializable{
  private String ORDERNO = "";
  private String PRICE = "";
  private String ERRCODE = "";
  private Element DISCRIPTION = null;
  private Vector CONTENT = null;
  public String XmlDocument = "";
  
  public SupplyOrderResult(){
    this.CONTENT = new Vector();
  }
  public SupplyOrderResult(String xml){
    try{fill(xml);} catch(Exception e){};
  }

  public String getORDERNO() {
    return ORDERNO;
  }
  public void setORDERNO(String ORDERNO) {
    this.ORDERNO = ORDERNO;
  }
  public void setPRICE(String PRICE) {
    this.PRICE = PRICE;
  }
  public void setERRCODE(String ERRCODE) {
    this.ERRCODE = ERRCODE;
  }
  public void setCONTENT( String CONTENT_ ){  
      this.CONTENT.add( CONTENT );
      System.err.println( "-------------------CONTENT:SupplyResult" + CONTENT + "CONTENTS.SIZE:" +  this.CONTENT.size());
      System.err.println( "CONTENT:SupplyResult" + CONTENT );
  }
  //public void setCONTENT( String CONTENT_ ){  
  //    this.CONTENT.add( CONTENT );
  //    System.err.println( "-------------------CONTENT:SupplyResult" + CONTENT + "CONTENTS.SIZE:" +  this.CONTENT.size());
  //    System.err.println( "CONTENT:SupplyResult" + CONTENT );
  //}
  public String getPRICE() {
    return this.PRICE;
  }
  //public void setDISCRIPTION( String DISCRIPTION ){
  //    this.
    //return DISCRIPTION;
  //}          
  public String getERRCODE(){
    return this.ERRCODE;
  }
  public String getCONTENT(){       System.err.println(  "GET_CONTENTS:------------------"  );
      String reStr = "";
      try{
      for(int i=0; i<this.CONTENT.size(); i++){
          String line = (String)CONTENT.elementAt( i );
          reStr += line.substring(9, line.indexOf("]]>") )+ "\n";
      }
      }catch(Exception ex){ ex.printStackTrace();   }
      return reStr;
  }
}
