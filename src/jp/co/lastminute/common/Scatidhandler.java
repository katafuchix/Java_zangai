package jp.co.lastminute.common;

import java.io.*;
import java.util.*;
import javax.servlet.*;

import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import jp.co.lastminute.ContextProperty;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Scatidhandler implements Serializable{
	private Hashtable scatids = null;

	public String getScatidStr( String key ){
		String reStr = "";
		try{
			if( this.scatids.get( key ) != null ){
				return (String)this.scatids.get( key );
			} 
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * コンストラクター
	 */
	public Scatidhandler(){
	}
	/**
	 * コンテキストを処理
	 */
	public void setServletContext( ServletContext context ){
		System.err.println("setServletContext 1:");
		if( context.getAttribute( ContextProperty.HTLCATS_ ) == null ){
			System.err.println("setServletContext 2:");
			scatids = getContextopbj();
			System.err.println("setServletContext 3:");
			context.setAttribute( ContextProperty.SCATIDS_, scatids );
		}else{
			this.scatids = (Hashtable)context.getAttribute( ContextProperty.SCATIDS_ ) ;
			System.err.println("setServletContext 4:");
		}
		System.err.println("setServletContext 5:");
	}

	/**
	 * コンテキスト読む
	 */
	public Hashtable getContextopbj(){
		String fileName = ContextProperty.SCATIDSLocate_;
		Hashtable reObj = null;
		System.err.println( "fileName= " + fileName );
		try{
			FileInputStream fin = new FileInputStream(fileName);
			System.err.println( "fileName=step 1" );
			ObjectInputStream oos = new ObjectInputStream( fin );
			System.err.println( "fileName=step 2" );
			reObj = (Hashtable)oos.readObject();	
			System.err.println( "fileName=step 3" );
			fin.close();
			System.err.println( "fileName=step 4" );
			return reObj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Returns the htlcats.
	 * @return Hashtable
	 */
	public Hashtable getScatids() {
		return scatids;
	}

	/**
	 * Sets the htlcats.
	 * @param htlcats The htlcats to set
	 */
	public void setScatids(Hashtable scatids) {
		this.scatids = scatids;
	}

}
