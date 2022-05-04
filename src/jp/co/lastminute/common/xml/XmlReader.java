/*
 * XmlReader.java
 *
 * Created on 2002/05/03, 13:21
 */

package jp.co.lastminute.common.xml;

import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.Constants;
import jp.co.yobrain.util.file.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class XmlReader implements Serializable{
    private HashMap xMLMappingObj;
    private static String path = Constants.path;
    private static String encoding = "UTF-8";

    /** コンストラクター */
    public XmlReader(HashMap xmlmapiing) {
        this.xMLMappingObj = xmlmapiing;
    }
    
    /** 
     * マッピングオブジェクトの取得
     * @param  UriParam マッピングキー
     */
    public ConstantsXml getMapping( UriParam uriparam ){
        return (ConstantsXml)this.xMLMappingObj.get( (UriParam)uriparam );
    }
    /** 
     * マッピングファイルの取得
     */
    public ConstantsXml getMapping( String agt_cd, int product_type_cd ){
        UriParam uriparam = new UriParam();
        uriparam.setAgt_cd(agt_cd);
        uriparam.setProduct_type_cd(product_type_cd);
        return (ConstantsXml)this.xMLMappingObj.get( uriparam );
    }
    /**
     * キャンセルURL
     */
     public String getCancelurl(String agt_cd, int product_type_cd){
         return getMapping(agt_cd, product_type_cd).getCancelurl();
     }
     /**
      * キャンセルXSL
      */
     public String getCancelxslt(String agt_cd, int product_type_cd){
         return getMapping(agt_cd, product_type_cd).getCancelxslt();
     }
     /**
      * キャンセルXSL内容
      */
     public String getCancelxsltAmount(String agt_cd, int product_type_cd){
         InportFile inf = null;
         try{
         return inf.getFileAmount(getMapping(agt_cd, product_type_cd).getCancelxslt(), path, encoding) ;
         }catch(Exception ex){  ex.printStackTrace(); }
         return "";
     }
     /**
      * 在庫照会URL
      */
     public String getCheckurl(String agt_cd, int product_type_cd){
         return getMapping(agt_cd, product_type_cd).getCheckurl();
     }
     /**
      * 詳細XSL
      */
     public String getDescxslt(String agt_cd, int product_type_cd){
         return getMapping(agt_cd, product_type_cd).getDescxslt();
     }
     /**
      * 詳細XSL内容
      */
     public String getDescxsltAmount(String agt_cd, int product_type_cd){
         InportFile inf = null;
         try{
         return inf.getFileAmount(getMapping(agt_cd, product_type_cd).getDescxslt(), path, encoding) ;
         }catch(Exception ex){  ex.printStackTrace(); }
         return "";
     }
     /**
      * 
      */
     public String getSendurl( String agt_cd, int product_type_cd ){
         return getMapping(agt_cd, product_type_cd).getSendurl();
     }
     /**
      * 注文XSL
      */
     public String getSendxslt(String agt_cd, int product_type_cd){
         return getMapping(agt_cd, product_type_cd).getSendxslt();
     }
     /**
      * 注文XSL内容
      */
     public String getSendxsltAmount(String agt_cd, int product_type_cd){
         InportFile inf = null;
         try{
         return inf.getFileAmount(getMapping(agt_cd, product_type_cd).getSendxslt(), path, encoding) ;
         }catch(Exception ex){  ex.printStackTrace(); }
         return "";
     }
     /**
      * フォーム生成XSD
      */
     public String getTemplatexsd(String agt_cd, int product_type_cd){
     	if( product_type_cd == Constants.Gift_ ){
     		return getMapping(Constants.KeyW, product_type_cd).getTemplatexsd();
     	}
        return getMapping(agt_cd, product_type_cd).getTemplatexsd();
     }
     /**
      * フォーム生成XSDの内容
      */
     public String getTemplatexsdAmount(String agt_cd, int product_type_cd){
         InportFile inf = null;
         try{
         return inf.getFileAmount(getMapping(agt_cd, product_type_cd).getTemplatexsd(), path, encoding) ;
         }catch(Exception ex){  ex.printStackTrace(); }
         return "";
     }
     /**
      * クラス取得
      */
     public String getFlowClass( String agt_cd, int product_type_cd){
     	if( product_type_cd == Constants.Gift_ ){
     		return getMapping(Constants.KeyW, product_type_cd).getTemplatexsl();
     	}
     	return getMapping(agt_cd, product_type_cd).getTemplatexsl();
     }
}