package jp.co.lastminute.Dflight.cash;

import java.io.*;
import java.util.*;
import javax.sql.DataSource;
import java.sql.SQLException;

import jp.co.lastminute.cash.*;
import jp.co.lastminute.Dflight.*;
import jp.co.lastminute.Dflight.jdbc.*;

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
	 * XMLƒXƒgƒŠƒ“ƒO‚ÌŽæ“¾
	 */
	public String newXMLString(DataSource dataSource, Object form){
		DflightSearchCondition 	dflightSearchCondition = (DflightSearchCondition)form;
		dbAdapterDflight db = new dbAdapterDflight( dataSource );
		return db.getLIst( dflightSearchCondition );
	}
	public String getXML(DataSource dataSource,Object form, int refreshDuration) {
		String reStr = "";
		try{
		String from = ((DflightSearchCondition)form).getFrom();
		String to = ((DflightSearchCondition)form).getTo();
		if( from.length() == 0 )	from = Property.from;
		if( to.length()  == 0 ) 	to = Property.to;
		Airports act = new Airports( from, to );
		MapEntrys entry = (MapEntrys) cache.get( act );
		if(entry != null){
            long lastModifiedLong=  entry.lastModified;
            long nowLong = System.currentTimeMillis();
            long refreshTimeLong = getRefreshTime(lastModifiedLong,refreshDuration);
            //System.err.println( "nowLong: " + nowLong);
			if (refreshTimeLong < nowLong) {
				isReload = false;
				entry = null;
			}
		}	
		if( entry == null ){		//System.err.println( "Refresh" );
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
