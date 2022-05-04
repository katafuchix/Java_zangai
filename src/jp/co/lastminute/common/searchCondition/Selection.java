/*
 * Selection.java
 *
 * Created on 2002/04/08, 0:26
 */

package jp.co.lastminute.common.searchCondition;

import java.io.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class Selection implements Serializable {
    private int subcatid;
    /** Creates new Selection */
    public Selection() {
    }

    /** プロパティ subcatid の取得メソッド。
     * @return プロパティ subcatid の値。
     */
    public int getSubcatid() {
        return subcatid;
    }
    
    /** プロパティ subcatid の設定メソッド。
     * @param subcatid プロパティ subcatid の新しい値。
     */
    public void setSubcatid(int subcatid) {
        this.subcatid = subcatid;
    }
    
}
