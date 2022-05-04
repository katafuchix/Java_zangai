package jp.co.lastminute.cart.user;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
/*
 * User.java
 *
 * Created on 2002/04/27, 17:45
 */


import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;


/**
 *
 * @author  skondo
 * @version 
 */
public class User implements Serializable{
    private User_Tbl user;
    private Profile profile;
    
    /** コンストラクタ */
    public User() {
        user = null;
        profile = null;
    }
    /**
     * イニシャライズ
     */
    public void init(){
    	this.user = new User_Tbl();
    	this.profile = new Profile();
    }
    /** プロパティ profile の取得メソッド。
     * @return プロパティ profile の値。
     */
    public Profile getProfile() {
        return profile;
    }
    
    /** プロパティ profile の設定メソッド。
     * @param profile プロパティ profile の新しい値。
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    /** プロパティ user の取得メソッド。
     * @return プロパティ user の値。
     */
    public User_Tbl getUser() {
        return user;
    }
    
    /** プロパティ user の設定メソッド。
     * @param user プロパティ user の新しい値。
     */
    public void setUser(User_Tbl user) {
        this.user = user;
    }
    
}
