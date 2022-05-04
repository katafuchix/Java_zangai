/*
 * UriParam.java
 *
 * Created on 2002/05/03, 13:08
 */

package jp.co.lastminute.common.xml;

import java.io.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class UriParam{
    private int product_type_cd = 0;
    private String agt_cd = "";
    
    /** Creates new UriParam */
    public UriParam() {
    }
    
    public UriParam(int producttypecd, String agtcd) {
        this.agt_cd = agtcd;
        this.product_type_cd = producttypecd;
    }


    /** プロパティ agt_cd の取得メソッド。
     * @return プロパティ agt_cd の値。
     */
    public String getAgt_cd() {
        return agt_cd;
    }
    
    /** プロパティ agt_cd の設定メソッド。
     * @param agt_cd プロパティ agt_cd の新しい値。
     */
    public void setAgt_cd(String agt_cd) {
        this.agt_cd = agt_cd;
    }
    
    /** プロパティ product_type_cd の取得メソッド。
     * @return プロパティ product_type_cd の値。
     */
    public int getProduct_type_cd() {
        return product_type_cd;
    }
    
    /** プロパティ product_type_cd の設定メソッド。
     * @param product_type_cd プロパティ product_type_cd の新しい値。
     */
    public void setProduct_type_cd(int product_type_cd) {
        this.product_type_cd = product_type_cd;
    }
    /**
     * ハッシュ出力
     */
    public int hashCode(){
        return 13 * this.agt_cd.hashCode() + 17 * this.product_type_cd;
    }
    /**
     *
     */
    public final boolean equals( Object urlparam ){
        //System.err.println("GO_BOOLEAN_URLPARAM_GOGOG");
        try{
            //System.err.println("GO_BOOLEAN_URLPARAM");
        if((this.getAgt_cd().equals( ((UriParam)urlparam).getAgt_cd()))&&(this.getProduct_type_cd() == ((UriParam)urlparam).getProduct_type_cd())){
            return true;
        }
        return false;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }   
}
