package jp.co.yobrain.util.jdbc;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import oracle.jdbc.driver.*;
import oracle.jdbc.pool.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DataSourceMaker implements Serializable{
	private ResourceBundle resources;
	private String URL = "";
	private String User = "";
	private String Password = "";
	
	private DataSource datasource = null;
	/**
	 * イニシャライズ
	 */
	public void init(){
		try {
	      resources = ResourceBundle.getBundle("jp.co.yobrain.util.jdbc.resources.Datasource", Locale.getDefault());
	      URL = resources.getString("URL");
	      User = resources.getString("User");
	      Password = resources.getString("Password");
	      //
	      OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
	      ocpds.setURL(URL);
	      ocpds.setUser(User);
	      ocpds.setPassword(Password);
	      
	      this.datasource = ocpds;
		}catch( SQLException ex ){	ex.printStackTrace();
	    } catch(Exception e){	e.printStackTrace();	}
	}
	/**
	 * ディストロイ
	 */
	public void distroy(){
		try{
			this.datasource = null;
		}catch(Exception ex){
			ex.printStackTrace();	
		}
	}
	/**
	 * コンストラクター
	 */
	public DataSourceMaker(){
	}
	/**
	 * Returns the datasource.
	 * @return DataSource
	 */
	public DataSource getDatasource() {
		return datasource;
	}

	/**
	 * Sets the datasource.
	 * @param datasource The datasource to set
	 */
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
