package jp.co.lastminute.maintenance.detail;

import java.io.*;
import java.util.*;
import javax.sql.*;
import jp.co.lastminute.maintenance.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PageDetailHandler {
	private String classname = "";
	private DataSource dataSource;
	private static final String classpath ="jp.co.lastminute.maintenance.detail";
	public PageDetailHandler(){
		
	}
	public PageDetailHandler(String catClass, DataSource dataSource){
		this.classname = catClass;
		this.dataSource = dataSource;
	}
	/**
	 * XMLストリングの取得
	 */
	public String getXmlString( HandlerCondition condition ){
		try{
			PageDetail pageDetail = (PageDetail)Class.forName( classpath + "." + classname ).newInstance();
			pageDetail.init( this.dataSource );
			return pageDetail.getXML( condition );
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	} 
	
	/**
	 * ファイル書込み
	 */
	public boolean wriet( String contextValue, String contextname ){
		try{
			PageDetail pageDetail = (PageDetail)Class.forName( classpath + "." + classname ).newInstance();
			return  pageDetail.write( contextValue, contextname );
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;	
	}
}
