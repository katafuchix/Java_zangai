/*
 * dbAdapter.java
 *
 * Created on 2002/03/31, 22:41
 */

package jp.co.lastminute.cart.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.lastminute.cart.model.Order;
import jp.co.lastminute.cart.model.Sub_Order;
import jp.co.lastminute.cart.model.Card_Auth;
import jp.co.lastminute.cart.model.Product_Send;

/**
 *
 * @author  skondo
 * @version 
 */
public class dbAdapterOrder implements Serializable{
    private DataSource ds;
    
    /**
     * コンストラクター
     */
    public dbAdapterOrder(){
    }
    public dbAdapterOrder( DataSource dss ){
        this.ds = dss;
    }
    /**
     * バッチインサート
     */
    synchronized public boolean doBatchUpdate(String[] query){
    	JdbcAdapter db = new JdbcAdapter();
    	try{	
    		if( db.init(ds) ){
    			return db.doBatchUpdate( query );	
    		}
    	}catch(Exception ex){	ex.printStackTrace();	}
    	return false;
    }
    /**
     * ラストサブオーダーの取得
     *　CREATE　SEQUENCE　ORDER_NO
     */
    synchronized public int getOrderNO(){
        JdbcAdapter db = new JdbcAdapter();
        try{
        if( db.init(ds) ){
            Vector vc = db.dbSelect( "SELECT ORDER_NO.NEXTVAL FROM DUAL" );
            dbDataAccesser dba = new dbDataAccesser( vc );
            return dba.getDatabyInt(0,0);
        }
        }catch(Exception ex){}
        return -1;
    }
    /**
     * ユーザーIDに基いた注文を出力する。
     * @param int Order_No
     * @param  DataSource ds
     * @return Vector sub_Orders
     */
    synchronized public Order findOrderbyUser(int User_id ){
        String SQL;
        //SQL文を生成
        try{
            SQL = Order_Select_statement + "WHERE"
                + " A.ORDER_NO=B.ORDER_NO AND"
                + " A.ORDER_NO=C.ORDER_NO(+) AND"
                + " A.ORDER_NO=D.ORDER_NO(+) AND"
                + " B.STATUS=2 AND"
                + " A.DEL_FLG=0 AND B.DEL_FLG=0 AND"    
                + " A.USER_ID=" + User_id;
           	return findOrder( SQL );
        }catch(Exception ex){   
            ex.printStackTrace(); 
        }
        return null;
        
    }
    /**
     * オーダーNoに基づいた
     */
    synchronized public Order findOrder( int Order_No ){
        String SQL;
        //SQL文を生成
        try{
            SQL = Order_Select_statement + "WHERE"
                + " A.ORDER_NO=B.ORDER_NO AND"
                + " A.ORDER_NO=C.ORDER_NO(+) AND"
                + " A.ORDER_NO=D.ORDER_NO(+) AND"
                + " A.DEL_FLG=0 AND B.DEL_FLG=0 AND"
                + " B.STATUS=2 AND"
                + " A.ORDER_NO=" + Order_No;               
			return findOrder( SQL );
        }catch(Exception ex){   
            ex.printStackTrace();
        }
        return null;
    }
     synchronized private Order findOrder( String SQL ){   
        Order order = new Order();
        Card_Auth card = new Card_Auth();
        Product_Send prodect_send = new Product_Send();
        Sub_Order suborder ;
        dbDataAccesser dbaccess;
        // SELECT 
        //JDBCAdapter起動
        //クエリー出力
        JdbcAdapter db = new JdbcAdapter();
       if( db.init(ds) ){
           Vector ors = db.dbSelect( SQL );
            if( ors.size() > 0){
           
            dbaccess = new dbDataAccesser( ors );
            order.setORDER_NO( dbaccess.getDatabyInt(0,0) );
            order.setUSER_ID( dbaccess.getDatabyInt(0,1) );
            order.setPRICE( dbaccess.getDatabyInt(0,2) );
            order.setTAX( dbaccess.getDatabyInt(0,3) );
            order.setTOTAL( dbaccess.getDatabyInt(0,4) );
            order.setBUY_DATE( dbaccess.getData(0, 5) );
            order.setMAKE_DATE( dbaccess.getData(0, 6) );
            order.setMAKE_DATE( dbaccess.getData(0, 7) );
            order.initOrder();
            for(int i=0; i<dbaccess.getRowsize(); i++){
                suborder = new Sub_Order();
                suborder.setSUB_ORDER_NO( dbaccess.getDatabyInt(i, 8) );
                suborder.setORDER_NO( dbaccess.getDatabyInt(i, 0) );
                suborder.setPRODUCT_TYPE_CD( dbaccess.getDatabyInt(i, 10) );
                suborder.setORDER_XSL_FILE( dbaccess.getData(i, 9) );
                suborder.setPROD_CD( dbaccess.getData(i, 11) );
                suborder.setAGT_PROD_CD( dbaccess.getData(i, 12) ); 
                suborder.setAGT_CD( dbaccess.getData(i, 14) );
                suborder.setTITLE( dbaccess.getData(i, 15) );
                suborder.setBUY_PROP( dbaccess.getData(i, 16) );
                suborder.setGUARANTEE_FLG( dbaccess.getDatabyInt(i, 17) );
                suborder.setPRICE( dbaccess.getDatabyInt(i, 18) );
                suborder.setAGENT_ACTION_URL( dbaccess.getData(i, 19) );
                suborder.setMAKE_DATE( dbaccess.getData(i, 20) );
                suborder.setDEL_FLG( dbaccess.getDatabyInt(i, 21) );
                suborder.setLAST_SALE( dbaccess.getDatabyInt(i, 49) );
                suborder.setAGT_ORDER_NO( dbaccess.getData(i, 50) ); 
                suborder.setStatus(1);
                suborder.setAGR_AREA_CD( dbaccess.getData(i, 51) );
                order.setSubOrder( suborder );
            }
            
            card.setOrder_No( dbaccess.getDatabyInt(0, 0) );
            card.setCard_No( dbaccess.getData(0, 22) );
            card.setExpire( dbaccess.getData(0, 23) );
            card.setFirst_Name( dbaccess.getData(0, 24) );
            card.setCard_type( dbaccess.getData(0, 25) );
            card.setAuth_Code( dbaccess.getData(0, 26) );
            card.setLast_Name( dbaccess.getData(0, 27) );
            card.setMake_Date( dbaccess.getDatabyInt(0, 28) );
            card.setUp_Date( dbaccess.getDatabyInt(0, 29) );
            order.setCardAuth( card );
            prodect_send.setSEND_ID( dbaccess.getDatabyInt(0, 30) );
            prodect_send.setUSER_ID( dbaccess.getDatabyInt(0, 31) );
            prodect_send.setFIRST_NAME( dbaccess.getData(0, 32) );
            prodect_send.setLASTNAME( dbaccess.getData(0, 33) );
            prodect_send.setE_MAIL( dbaccess.getData(0, 34) );
            prodect_send.setORDER_NO( dbaccess.getDatabyInt(0, 35) );
            prodect_send.setPOSTAL_CD( dbaccess.getData(0, 36) );
            prodect_send.setSTATE_CD( dbaccess.getDatabyInt(0, 37) );
            prodect_send.setCITY_NAME( dbaccess.getData(0, 38) );
            prodect_send.setADDRESS( dbaccess.getData(0, 39) );
            prodect_send.setDELIVERYNAME( dbaccess.getData(0, 40) );
            prodect_send.setTEL_NO( dbaccess.getData(0, 41) );
            prodect_send.setFAX( dbaccess.getData(0, 42) );
            prodect_send.setUP_DATE( dbaccess.getData(0, 43) );
            prodect_send.setMAKE_DATE( dbaccess.getData(0, 44) );
            prodect_send.setMOBILE_E_MAIL( dbaccess.getData(0, 45) );
            prodect_send.setDEL_FLG( dbaccess.getDatabyInt(0, 46) );
            prodect_send.setADDRESSFLG( dbaccess.getDatabyInt(0, 47) );
            prodect_send.setSEND_TYPE_ID( dbaccess.getDatabyInt(0, 48) );
            order.setProductSend( prodect_send );
            return order;
            }
            return null;
        }
        return null;
    }
    /**
     * オーダーのセット
     */
    synchronized public int addOrder( Order order ){
        String SQL = "";
        int order_no = 0;
        try{
        	order_no = order.getORDER_NO();
        	if( order_no == 0 ){
        		order_no = getOrderNO();
        		order.setORDER_NO( order_no );
        	}
            SQL = getAddOrderStr( order );	System.err.println( SQL );
            //JDBCAdapter起動
        	JdbcAdapter db = new JdbcAdapter();
        	if( db.init(this.ds) ){
	            if( db.dbInsert( SQL ) ){
	            	return order_no;
	            }         
        	}
        }catch(Exception ex){   
            ex.printStackTrace();   
        }               
        return 0;
    }
    /**
     * オーダーのインサート
     */
    synchronized public static String getAddOrderStr( Order order ) throws Exception{
    	return "INSERT INTO ORDERS (ORDER_NO, USER_ID, PRICE, TAX, TOTAL, SENDING, SENDING_TAX, DEL_FLG, MAKE_DATE ) VALUES"
                + "(" + order.getORDER_NO() + ","
                + order.getUSER_ID() + ","
                + order.getPRICE() + ", "
                + order.getTAX() + ", "
                + order.getTOTAL() + ", "
                + order.getSENDING() + ", "
                + order.getSENDING_TAX() + ", "
                + 0 + ", SYSDATE)";    
	}
    /**
     * オーダーの詳細出力
     * @param int Order_No
     * @param Sub_Order_No
     * @param DataSource ds
     * @return boolean true/false
     */
    synchronized public boolean modifyOrder( Order order , boolean flg){
       //SQL文を生成
        String SQL = "";
        int fg = 0;
        try{
            if( !flg ){  fg = 1; }
            SQL = "UPDATE ORDERS SET DEL_FLG=" + fg + ", UP_DATE=SYSDATE WHERE ORDER_NO=" + order.getORDER_NO();
        	JdbcAdapter db = new JdbcAdapter();
        	if( db.init(this.ds) )	return db.dbInsert( SQL );  
        }catch(Exception ex){   
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * オーダーの詳細出力
     * @param int Order_No
     * @param Sub_Order_No
     * @param DataSource ds
     * @return boolean true/false
     */
    synchronized public boolean modifyOrder( Order order ){
       //SQL文を生成
        String SQL = "";
        try{
            SQL = getModifyOrderStr( order );
            //JDBCAdapter起動
        	JdbcAdapter db = new JdbcAdapter();
        	if( db.init(this.ds) )	return db.dbInsert( SQL );
        }catch(Exception ex){   
            ex.printStackTrace();
        }
        //クエリー出力
        return false;
    }
    /**
     *  オーダーSQLの出力
     */
    synchronized public static String getModifyOrderStr( Order order ) throws Exception{
    	return "UPDATE ORDERS SET PRICE =" + order.getTOTAL() + ", "
                + "TAX=" + order.getTAX() +  ", "
                + "SENDING=" + order.getSENDING() + ", "
                + "SENDING_TAX=" + order.getSENDING_TAX() + ", "
                + "TOTAL=" + order.getTOTAL() +  ", UP_DATE=SYSDATE WHERE ORDER_NO=" + order.getORDER_NO();
    }
    /**
     * サブオーダーのキャンセルデリートフラグを
     * @param int ourderNo
     * @param int SuborderNo
     */
    synchronized public boolean removeSubOrder( Order order ){
        //SQL文を生成
        String SQL = "";
        int fg = 9;
        try{
            SQL = "UPDATE ORDERS SET DEL_FLG=" + fg + ", UP_DATE=SYSDATE WHERE ORDER_NO=" + order.getORDER_NO();
        	JdbcAdapter db = new JdbcAdapter();
        	if( db.init(this.ds) )	return db.dbInsert( SQL );   
        }catch(Exception ex){   
            ex.printStackTrace();
        }
        return false; 
    }
    /**
     * サブオーダーのアップデート
     */
    synchronized public boolean modifyMultSubOrderStatus( Order order, int status, int target ){
        String SQL = "";
        try{
            SQL = "UPDATE SUB_ORDER SET STATUS=" + status + " WHERE ORDER_NO=" + order.getORDER_NO() + " AND STATUS=" + target ;
            System.err.println( SQL );
            JdbcAdapter db = new JdbcAdapter();
            if( db.init(this.ds) )	return db.dbInsert( SQL );  
        }catch(Exception ex){   
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * 
     */
    synchronized public boolean modifyTotalOrder( Order order ){
    	try{
    		String[] qruey = new String[order.getSubOrderCount() + order.getProductSend().size() + 1 ];
    		JdbcAdapter db = new JdbcAdapter();
    		if( db.init(this.ds) )	db.doBatchUpdate( qruey );
    	}catch(Exception ex){}
    	return false;
    }
    /**
     * SQL ステートメント
     */
    private static final String Order_Select_statement = "SELECT"
                + " A.ORDER_NO,"    //000
                + " A.USER_ID, "    //001
                + " A.PRICE, "      //002
                + " A.TAX, "        //003
                + " A.TOTAL, "      //004
                + " TO_CHAR(A.BUY_DATE, 'YYYYMMDD') BUY_DATE, "         //005
                + " TO_CHAR(A.MAKE_DATE, 'YYYYMMDD') MAKE_DATE, "       //006
                + " TO_CHAR(A.UP_DATE, 'YYYYMMDD') UP_DATE, "           //007
                + " B.SUB_ORDER_NO,"        //008
                + " B.ORDER_XSL_FILE,"      //009
                + " B.PRODUCT_TYPE_CD,"     //010
                + " B.PROD_CD,"             //011
                + " B.AGT_PROD_CD,"         //012
                + " B.AGT_PLAN_CD,"         //013
                + " B.AGT_CD,"              //014
                + " B.TITLE,"               //015
                + " B.BUY_PROP,"            //016
                + " B.GUARANTEE_FLG,"       //017
                + " B.PRICE,"               //018
                + " B.AGENT_ACTION_URL,"    //019
                + " TO_CHAR(B.MAKE_DATE, 'YYYYMMDD'), "     //020
                + " B.DEL_FLG,"             //021
                + " C.CARD_NO,"             //022
                + " C.EXPIRE,"              //023
                + " C.FIRST_NAME,"          //024
                + " C.LASTNAME,"            //025
                + " C.CARD_TYPE,"           //026
                + " C.AUTH_CODE,"           //027
                + " TO_CHAR(C.MAKE_DATE, 'YYYYMMDD'),"      //028
                + " TO_CHAR(C.UP_DATE, 'YYYYMMDD'),"        //029
                + " D.SEND_ID,"             //030
                + " D.USER_ID,"             //031
                + " D.FIRST_NAME,"          //032
                + " D.LASTNAME,"            //033
                + " D.E_MAIL,"              //034
                + " D.ORDER_NO,"            //035
                + " D.POSTAL_CD,"           //036
                + " D.STATE_CD,"            //037
                + " D.CITY_NAME,"           //038
                + " D.ADDRESS,"             //039
                + " D.DELIVERYNAME,"        //040
                + " D.TEL_NO,"              //041
                + " D.FAX,"                 //042
                + " TO_CHAR(D.MAKE_DATE, 'YYYYMMDD'),"      //043
                + " TO_CHAR(D.UP_DATE, 'YYYYMMDD'),"      //044
                + " D.MOBILE_E_MAIL,"       //045
                + " D.DEL_FLG,"             //046
                + " D.ADDRESSFLG,"          //047
                + " D.SEND_TYPE_ID,"        //048
                + " B.LAST_SALE, "            //049
                + " B.AGT_ORDER_NO, "           //50
                + " B.AGR_AREA_CD "             //51
                + " FROM ORDERS A, SUB_ORDER B, CARD_AUTH C, PRODUCT_SEND D ";
}
