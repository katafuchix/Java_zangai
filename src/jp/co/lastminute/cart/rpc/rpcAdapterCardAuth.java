/*
 * rpcAdapterCardAuth.java
 *
 * Created on 2002/04/02, 1:01
 */

package jp.co.lastminute.cart.rpc;

import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cardauth.CardAuthClient;

/**
 *
 * @author  skondo
 * @version
 */
public class rpcAdapterCardAuth{
	
	public rpcAdapterCardAuth(){
	}	
    /**
     * カード有効性のチェックを行う
     * @param String cardno
     * @param String expire
     * @param String name
     * @param String price
     * @return int ErrorCord 0=OK, 1<ErrorCode
     */
    synchronized public String checkCard(String cardno, String expire, String name, String price){
        CardAuthClient cl = new CardAuthClient();
        String result= cl.cardCheck(cardno, expire, true).trim();
        return result;
    }
    synchronized public String checkCard(String cardno, String expire, String name, int price){
        CardAuthClient cl = new CardAuthClient();
        String result= cl.cardCheck(cardno, expire, true).trim();
        return result;
    }
    synchronized public String checkCard(String cardno, String expire, String name, int price, boolean flg){
        if( flg ){
            CardAuthClient cl = new CardAuthClient();
            String result= cl.cardCheck(cardno, expire, name, price, true ).trim();
            return result;
        }else{
            return checkCard(cardno, expire, name, price, true);
        }
    }
}
