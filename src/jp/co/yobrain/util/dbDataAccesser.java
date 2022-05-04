/*
 * dbDataAccesser.java
 *
 * Created on 2002/04/20, 19:36
 */

package jp.co.yobrain.util;

import java.io.*;
import java.util.*;
import java.math.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class dbDataAccesser implements Serializable{
    private Vector rows;
    /** コンストラクター 
     * @param Vector Valurs
     */
    public dbDataAccesser(Vector Values) {
        this.rows = Values;
    }
    /**
      * データをデータベースより直接取得する
      * @param 	int i	行
    　* @param  int  col	列
　　　* @return 	String	取得データ
    　*/
    synchronized public String getData(int i, int j){
        try{
            if( getValueAt(i,j) == null ){
                return "";
            }
            return getValueAt(i,j).toString();	
	}catch(Exception e){
            e.printStackTrace();
            return "";
    	}
    }
    /**
      * データをデータベースより直接取得する
      * @param 	int i	行
    　* @param  int  col	列
　　　* @return int 取得データ
    　*/
    synchronized public int getDatabyInt(int i, int j){
        try{
            if( getValueAt(i, j) == null ){ return 0;   }
            Integer inte = (Integer)getValueAt(i,j);
            return inte.intValue();
	}catch(ClassCastException e){
            try{
                BigDecimal bigdec = (BigDecimal)getValueAt(i,j);
                return bigdec.intValue();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return 0;
    	}catch(Exception ex){
            return 0;
        }
    }
    /**
      * データをデータベースより直接取得する
      * @param 	int i	行
    　* @param  int  col	列
　　　* @return int 取得データ
    　*/
    synchronized public double getDatabydouble(int i, int j){
        try{
            if( getValueAt(i, j) == null ){ return 0;   }
            Double inte = (Double)getValueAt(i,j);
            return inte.doubleValue();
	}catch(ClassCastException e){
            try{
                BigDecimal bigdec = (BigDecimal)getValueAt(i,j);
                return bigdec.intValue();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return 0;
    	}catch(Exception ex){
            return 0;
        }
    }
    /**
      * データをデータベースより直接取得する
      * @param 	int i	行
    　* @param  int  col	列
　　　* @return long 取得データ
    　*/
    synchronized public long getDatabylong(int i, int j){
        try{
            Long lg = (Long)getValueAt(i,j);
            return lg.longValue();
	}catch(Exception e){
            e.printStackTrace();
            return 0;
    	}
    }
    /**
      * データをデータベースより直接取得する
      * @param 	int i	行
    　* @param  int  col	列
　　　* @return float 取得データ
    　*/
    synchronized public float getDatabyfloat(int i, int j){
        try{
            Long lg = (Long)getValueAt(i,j);
            return lg.floatValue();
	}catch(Exception e){
            e.printStackTrace();
            return 0;
    	}
    }
    /**
      * データをデータベースより直接取得する
      * @param 	int i	行
    　* @param  int  col	列
　　　* @return boolean 取得データ
    　*/
    synchronized public boolean getDatabyboolean(int i, int j){
        try{
            Boolean lg = (Boolean)getValueAt(i,j);
            return lg.booleanValue();
	}catch(Exception e){
            e.printStackTrace();
            return false;
    	}
    }
    //指定された、行－列 のオブジェクトの取得////////////////////////
    synchronized private Object getValueAt(int aRow, int aColumn){
	Vector row = (Vector)rows.elementAt(aRow);
	return row.elementAt(aColumn);
    }
    /**　データサイズの取得
     * @return int ROWSIZE
     */
    synchronized public int getRowsize(){
        return rows.size();
    }
}
