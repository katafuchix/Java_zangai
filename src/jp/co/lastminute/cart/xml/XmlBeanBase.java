package jp.co.lastminute.cart.xml;

import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 * ＸＭＬデータをクラスメンバーに入力する規定クラス
 */

public class XmlBeanBase implements Serializable{
    public String xmlstring = "";
    public String rootName = null;
    private String SUCCESS = null;
    private String FAIL = null;
    private String ERRORCODE = "";

  public void fill(String xml) throws Exception {
    try {
      SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
      Handler handler = new Handler();
      parser.parse(new InputSource(new StringReader(xml)), handler);
      handler = null;
      this.xmlstring = xml;
    } catch (Exception e) {
      throw e;
    }
  }
  public String getXmldocument(){
    return xmlstring;
  }
  public void callSetter(String name, Object value) {
    try {
      // obj = Class.forName(classname).newInstance();
      char first = Character.toUpperCase(name.charAt(0));
      String methodName = "set" + first + name.substring(1);
      Class [] pTypes = new Class[1];
      pTypes[0] = value.getClass();
      Method method = this.getClass().getMethod(methodName, pTypes);
      Object [] params = new Object[1];
      params[0] = value;
      method.invoke(this, params);
    } catch(Exception e){
      System.out.println(e);
      System.out.println(this);
    }
  }

  public boolean isSuccess(){
    if(SUCCESS == null) return false;
    return true;
  }
  public boolean isParse(){
    if(rootName == null) return false;
    return true;
  }
  public String getSUCCESS() {
    return SUCCESS;
  }
  public void setSUCCESS(String SUCCESS) {
    this.SUCCESS = SUCCESS;
  }
  public String getFAIL() {
    return FAIL;
  }
  public void setFAIL(String FAIL) {
    this.FAIL = FAIL;
  }
  public void setERRORCODE(String ERRORCODE) {
    this.ERRORCODE = ERRORCODE;
  }
  public String getERRORCODE() {
    return ERRORCODE;
  }
  /**
   * SAXで解析しながら 処理する内部クラス。
   **/
  private class Handler extends DefaultHandler {
    private int level_ = 0;
    StringBuffer buffer_ = null;

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
      level_++;
      if (level_ > 1) {
        buffer_ = new StringBuffer(50);
      } else {
        rootName = qName;
      }
    }
    public void characters(char[] ch, int start, int length) {
      if (level_ > 1) {
        buffer_.append(ch, start, length);
      }
    }

    public void endElement(String namespaceURI, String localName, String qName) {
      if (level_ > 1) {
        callSetter(qName, buffer_.toString());
      }
      level_--;
    }
  }
}
