package jp.co.lastminute.Restrant.cash;

import java.io.*;
import java.util.*;
import javax.sql.DataSource;
import java.sql.SQLException;

import jp.co.lastminute.cash.*;
import jp.co.lastminute.Restrant.*;
import jp.co.lastminute.Restrant.jdbc.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CashMaker extends XMLCache implements XMLCashInterface{
	
	private static final int refreshDuration = 20;
	/**
	 * XMLストリングの取得
	 */
	public String newXMLString(DataSource dataSource, Object form){
		RestrantSearchCondition 	resttrantSearchCondition = (RestrantSearchCondition)form;
		dbAdapterRestrant db = new dbAdapterRestrant( dataSource );
		return db.getLIst( resttrantSearchCondition );
	}
	public String getXML(DataSource dataSource,Object form, int refreshDuration) {
		String reStr = "";
		try{
		String act = "";//リクエストの種類小カテゴリー
		MapEntrys entry = (MapEntrys) cache.get( act );
		if(entry != null){
            long lastModifiedLong=  entry.lastModified;
            long nowLong = System.currentTimeMillis();
            long refreshTimeLong =
                getRefreshTime(lastModifiedLong,refreshDuration);
			if (refreshTimeLong < nowLong) {
				entry = null;
			}
		}
		if( entry == null ){
            String xmlString = newXMLString(dataSource, form);
            entry = new MapEntrys(System.currentTimeMillis(), xmlString);
            cache.put(act, entry);
            isReload = true;
        }
        reStr = entry.xmlString;
		}catch(Exception ex){	ex.printStackTrace();	}
        return reStr;
    }
    public String getXML(DataSource dataSource, Object form) {
        return getXML( dataSource, form, refreshDuration);
    }
}
