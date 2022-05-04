/*
 * dbAdapter.java
 *
 * Created on 2002/03/31, 22:41
 */

package jp.co.lastminute.supply.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.lastminute.supply.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class dbAdapterAgent implements Serializable{
    private DataSource ds;
    
    /**
     * コンストラクター
     */
    public dbAdapterAgent(DataSource dss){
        this.ds = dss;
    }
    /**
     * agt_cd に基づいた
     */
    synchronized public Agent findAgent( String agt_cd ){
        try{
            int agt_number = Integer.parseInt( agt_cd );
            return findAgent( agt_number );
        }catch(Exception ex){}
        try{
            String SQL = descSql + "AND A.AGT_CD='" + agt_cd + "'";  
            return findOrder( SQL );
        }catch(Exception ex){   ex.printStackTrace();   
            return null;
        }
    }
    synchronized public Agent findAgentOnlyStr( String agt_cd ){
        try{
            String SQL = descSql + "AND A.AGT_CD='" + agt_cd + "'";  
            return findOrder( SQL );
        }catch(Exception ex){   ex.printStackTrace();   
            return null;
        }
    }
    /**
     * Member_IDに基づいた
     */
    synchronized public Agent findAgent( int member_cd ){
        try{
            String SQL =  desclmj + "AND A.MEMBER_CD=" + member_cd ;  
            return findOrder( SQL );
        }catch(Exception ex){   ex.printStackTrace();   
            return null;
        }
    }
     synchronized private Agent findOrder( String SQL ){   
        Agent agent = new Agent();
        dbDataAccesser dbaccess;
        // SELECT 
        //JDBCAdapter起動
        //クエリー出力
        System.err.println( SQL );
        JdbcAdapter db = new JdbcAdapter();
       if( db.init(ds) ){
           Vector ors = db.dbSelect( SQL );
            if( ors.size() > 0){    System.err.println( "-----------SIZE:=" + ors.size() + "-------------");
                dbaccess = new dbDataAccesser( ors );
                agent.setAGT_CD( dbaccess.getData(0, 0) );
                agent.setTEL_NO( dbaccess.getData(0, 1) );
                agent.setFAX( dbaccess.getData(0, 2) );
                agent.setE_MAIL( dbaccess.getData(0, 3) );
                agent.setFIRST_NAME( dbaccess.getData(0, 4) );
                agent.setLASTNAME( dbaccess.getData(0, 5) );
                agent.setPOSTAL_CD( dbaccess.getData(0, 6) );
                agent.setSTATE_NAME( dbaccess.getData(0, 7) );
                agent.setCITY_NAME( dbaccess.getData(0, 8) );
                agent.setADDRESS( dbaccess.getData(0, 9) );
                agent.setMAILCOMMENT( dbaccess.getData(0, 12) );
                return agent;
            }else{                              System.err.println( "-----------SIZE <  -------------");
                agent.setAGT_CD("LMJ");
                agent.setTEL_NO("03-3526-3900");
                agent.setFAX("03-3526-6223");
                agent.setE_MAIL("s.kondo@lastminute.co.jp");
                agent.setFIRST_NAME("ラスト ミニット ドット コム");
                agent.setLASTNAME("");
                agent.setPOSTAL_CD("1010021");
                agent.setSTATE_NAME("東京都");
                agent.setCITY_NAME("千代田区");
                agent.setADDRESS("外神田　4-7-5 石川興産ビル");
                agent.setMAILCOMMENT( "ありがとうございました" );
                return agent;
            }
        }
        return null;
    }
    public static String descSql = "SELECT "
                                  + "A.AGT_CD,"         //00
                                  + "A.TEL_NO,"         //01
                                  + "A.FAX,"            //02
                                  + "A.E_MAIL,"         //03
                                  + "A.FIRST_NAME,"     //04
                                  + "A.LASTNAME,"       //05
                                  + "A.POSTAL_CD,"      //06
                                  + "B.STATE_NAME,"     //07
                                  + "A.CITY_NAME,"      //08
                                  + "A.ADDRESS,"        //09     
                                  + "A.NAME,"           //10
                                  + "A.STATE_CD, "       //11
                                  + "A.MAILCOMMENT "
                                  + "FROM AGENT_TBL A, STATE_MASTER B WHERE A.STATE_CD=B.STATE_CD ";
    public static String desclmj = "SELECT "     
                                  + "A.AGT_CD,"         //00  
                                  + "A.TEL_NO,"         //01    
                                  + "A.FAX,"            //02    
                                  + "A.E_MAIL,"         //03    
                                  + "A.FIRST_NAME,"     //04    
                                  + "A.LASTNAME,"       //05    
                                  + "A.POSTAL_CD,"      //06    
                                  + "B.STATE_NAME,"     //07    
                                  + "A.CITY_NAME,"      //08    
                                  + "A.ADDRESS,"        //09
                                  + "A.MEMBER_CD,"      //10
                                  + "A.STATE_CD, "       //11
                                  + "A.MAILCOMMENT "
                                  + "FROM AGENT_MEMBER_TBL A, STATE_MASTER B WHERE A.STATE_CD=B.STATE_CD ";
}
