package jp.co.lastminute.Hotel.localarea;

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
public class localareashandler implements Serializable{
	private Hashtable localareas = null;

	/**
	 * コンストラクター
	 */
	public localareashandler(){
	}
	public String getLocalareaStr( String key ){
		String reStr = "";
		try{
			if( this.localareas.get( key ) != null ){
				return (String)this.localareas.get( key );
			} 
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * コンテキストを処理
	 */
	public void setServletContext( ServletContext context ){
		System.err.println("setServletContext 1:");
		if( context.getAttribute( ContextProperty.LOCALAREAS_ ) == null ){
			System.err.println("setServletContext 2:");
			this.localareas = getContextopbj();
			System.err.println("setServletContext 3:");
			context.setAttribute( ContextProperty.LOCALAREASLocate_, localareas );
		}else{
			localareas = (Hashtable)context.getAttribute( ContextProperty.HAREAS_ ) ;
			System.err.println("setServletContext 4:");
		}
		System.err.println("setServletContext 5:");
	}
	/**
	 * コンテキスト読む
	 */
	public Hashtable getContextopbj(){
		String fileName = ContextProperty.LOCALAREASLocate_;
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
}
