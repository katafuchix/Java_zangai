/*
 * Gift.java
 *
 * Created on 2002/04/08, 0:32
 */

package jp.co.lastminute.common.searchCondition;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import java.io.*;
/**
 *
 * @author  skondo
 * @version 
 */
public class Culture extends ActionForm implements Serializable
{
    private int catid;
    private int scatid;
    private String jan_cd;
    //for Order
    int numberOforder;

    /** reset properties when user do action */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
         this.catid = 0;
        this.scatid = 0;
        this.jan_cd = "";
        this.numberOforder = 0;
    }
    /** Creates new Gift */
    public Culture() {
        this.catid = 0;
        this.scatid = 0;
        this.jan_cd = "";
        this.numberOforder = 0;
    }

    /** プロパティ catid の取得メソッド。
     * @return プロパティ catid の値。
     */
    public int getCatid() {
        return catid;
    }
    
    /** プロパティ catid の設定メソッド。
     * @param catid プロパティ catid の新しい値。
     */
    public void setCatid(int catid) {
        this.catid = catid;
    }
    
    /** プロパティ jan_cd の取得メソッド。
     * @return プロパティ jan_cd の値。
     */
    public String getJan_cd() {
        return jan_cd;
    }    
    
    /** プロパティ jan_cd の設定メソッド。
     * @param jan_cd プロパティ jan_cd の新しい値。
     */
    public void setJan_cd(String jan_cd) {
        this.jan_cd = jan_cd;
    }
    
    /** プロパティ scatid の取得メソッド。
     * @return プロパティ scatid の値。
     */
    public int getScatid() {
        return scatid;
    }
    
    /** プロパティ scatid の設定メソッド。
     * @param scatid プロパティ scatid の新しい値。
     */
    public void setScatid(int scatid) {
        this.scatid = scatid;
    }
    
    /** プロパティ numberOforder の取得メソッド。
     * @return プロパティ numberOforder の値。
     */
    public int getNumberOforder() {
        return numberOforder;
    }
    
    /** プロパティ numberOforder の設定メソッド。
     * @param numberOforder プロパティ numberOforder の新しい値。
     */
    public void setNumberOforder(int numberOforder) {
        this.numberOforder = numberOforder;
    }
    
}
