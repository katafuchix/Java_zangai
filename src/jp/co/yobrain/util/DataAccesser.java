package jp.co.yobrain.util;

import java.io.*;
import java.util.*;

public class DataAccesser implements Serializable{
    private Vector jdbcVector;
    /**
     * コンストラクター
     *　データベースの値を取得
     */
    public DataAccesser(Vector jdbcvector){
        this.jdbcVector = jdbcvector;
    }
    /**
     * 行数を取得
     * @return int 行数
     */
    public int getRowCount(){	return jdbcVector.size();	}
    /**
     * ベクターよりデータを取得する
     *　@return 	String	取得データ
     *　@param 	int	i	行
     *　@param	int j	列
     */
    public String getData(int i, int j){	
	try{
            return getValueAt(i,j).toString();	
	}catch(Exception e){
            return "";
	}
    }
    private Object getValueAt(int aRow, int aColumn){
        Vector row = (Vector)jdbcVector.elementAt(aRow);
        return row.elementAt(aColumn);
    }
}

