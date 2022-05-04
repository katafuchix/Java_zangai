/*
 * PostString.java
 *
 * Created on 2002/04/29, 19:34
 */

package jp.co.yobrain.util.rpc;

import java.io.*;

/**
 *
 * @author  skondo
 * @version 0.1
 */
public class PostString implements Serializable{
    private String name = "";
    private String value = "";

    /** Creates new PostString */
    public PostString(String nam, String val) {
        try{
            this.name = nam;
        }catch(Exception ex){
            this.name = "";
        }
        try{
            this.value = val;
        }catch(Exception ex){
            this.value = "";
        }
        //System.err.println("Nam: " + nam + ", val: " + val);
    }

    /** プロパティ name の取得メソッド。
     * @return プロパティ name の値。
     */
    public String getName() {
        return name;
    }
    
    /** プロパティ name の設定メソッド。
     * @param name プロパティ name の新しい値。
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /** プロパティ value の取得メソッド。
     * @return プロパティ value の値。
     */
    public String getValue() {
        return value;
    }
    
    /** プロパティ value の設定メソッド。
     * @param value プロパティ value の新しい値。
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    public String geteqName() {
        return this.name + "=";
    }

    /**
     * @author Ricardo Leon
     * @returns a String with the name and the value of the PostString object
     */
    public String toString() {
    	return name + "=" + value;
    }
}
