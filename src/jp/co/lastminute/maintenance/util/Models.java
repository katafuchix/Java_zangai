package jp.co.lastminute.maintenance.util;

import java.io.*;
import java.util.regex.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import jp.co.yobrain.util.form.*;
import jp.co.lastminute.maintenance.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Models {

	public int[] error_flg = null;
	public int error_sum = 0;
	/*
	 * タグメーカ
	 */
	public static String getTag(String name, String val, boolean flg){
		String tname = name.toUpperCase();
		if( val.length() > 0 && flg ){
			val = "<![CDATA[" + CharacterConverter.htmlPreEncode( val ) + "]]>";
		}
		return "<" + tname + ">" + val + "</" + tname + ">";
	}
	/**
	 * スプリッタ
	 */
	public static String[] parseStr( String str ){
		return Pattern.compile(",").split( str );
	}
	public static String[] parseStrQuot( String str ){
		str.trim();
		str = str.substring(1, str.length() - 1);
		return Pattern.compile("\",\"").split( str );
	}
	public boolean isError(){
		if( error_sum > 0 ){
			return false;	
		}
		return true;
	}
	/**
	 * クリア
	 */
	public void clear(){
		for( int i=0; i<error_flg.length; i++){
			this.error_flg[i] = 0;
		}
		this.error_sum = 0;
	}
	// 	TYPE_NUMBER_ = 0;
	//	TYPE_STRING_ = 1;
	//	TYPE_FLOAT_ = 2;
	//	TYPE_DATE_ =  3;
	//	TYPE_EMAIL_ = 4;
	//	TYPE_MONEY_ = 5;
	//	TYPE_ALPHABET_ = 6;
	//	TYPE_ALPHABETNUMBER_ = 7;
	//	TYPE_DATETIME_ =  8;
	//	TYPE_PHONE_ =  9;
	//	TYPE_TWOBYTE_ =  10;
	public String checkStr( HttpServletRequest req, String name, int num, int type, boolean need ) {
		String reStr = "";
		if(!(( req.getParameter( name ) == null ) &&( !need ))){
			reStr = CharacterConverter.toJISAutoDetect( req.getParameter( name ) );
			CheckError chError = null;
		    Check formchk = new jp.co.yobrain.util.form.Check();
		    chError = formchk.offSet( reStr, type, need );
		    reStr = chError.getRstr();
		    if(chError.getError() > 0){
		    	error_sum++;
		    	error_flg[ num ] = 1;
		  	}else{
		  		error_flg[ num ] = 0;
		    }
		    System.err.println( "Param_name = " + name + "/ Error = " + chError.getError() + " Type = " + type);
		    chError = null;
		    formchk = null;
		}
		return reStr;
	}
	public int checkInt( HttpServletRequest req, String name, int num, int type, boolean need ){
		String reStr = "";
		int reint = 0;
		if(!(( req.getParameter( name ) == null ) &&( !need ))){
			reStr = CharacterConverter.toJISAutoDetect( req.getParameter( name ) );
			CheckError chError = null;
		    Check formchk = new jp.co.yobrain.util.form.Check();
		    chError = formchk.offSet( reStr,type, need );
		    reStr = chError.getRstr();
		    if(chError.getError() > 0){
		    	error_sum++;
		    	error_flg[ num ] = 1;
		    	reint = 0;
		  	}else{
		  		error_flg[ num ] = 0;
		  		try{
		    		reint = Integer.parseInt( reStr );
		    	}catch(Exception ex){	
		    		ex.printStackTrace();	
		    		error_sum++;
		    		error_flg[ num ] = 1;
		    	}
		    }
		    System.err.println( "Param_name = " + name + "/ Error = " + chError.getError() + " Type = " + type);
		    chError = null;
		    formchk = null;
		}
	    return reint;
	}
	/**
	 * 
	 */
	public String checkStr( HttpServletRequest req, String name, int num, int type, boolean need, int target ) {
		String reStr = "";
		try{
			if(!(( req.getParameter( name ) == null ) &&( !need ))){
				reStr = CharacterConverter.toJISAutoDetect( req.getParameterValues( name )[ target ] );
				CheckError chError = null;
			    Check formchk = new jp.co.yobrain.util.form.Check();
			    chError = formchk.offSet( reStr, type, need );
			    reStr = chError.getRstr();
			    if(chError.getError() > 0){
			    	error_sum++;
			    	error_flg[ num ] = 1;
			  	}else{
			  		error_flg[ num ] = 0;
			    }
			    System.err.println( "Param_name = " + name + " target:" + target + " / Error = " + chError.getError() + " Type = " + type);
			    chError = null;
			    formchk = null;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	public int checkInt( HttpServletRequest req, String name, int num, int type, boolean need, int target ){
		String reStr = "";
		int reint = 0;
		try{
			if(!(( req.getParameter( name ) == null ) &&( !need ))){
				reStr = CharacterConverter.toJISAutoDetect( req.getParameterValues( name )[ target ] );
				CheckError chError = null;
			    Check formchk = new jp.co.yobrain.util.form.Check();
			    chError = formchk.offSet( reStr,type, need );
			    reStr = chError.getRstr();
			    if(chError.getError() > 0){
			    	error_sum++;
			    	error_flg[ num ] = 1;
			    	reint = 0;
			  	}else{
			  		error_flg[ num ] = 0;
			  		try{
			    		reint = Integer.parseInt( reStr );
			    	}catch(Exception ex){	
			    		ex.printStackTrace();	
			    		error_sum++;
			    		error_flg[ num ] = 1;
			    	}
			    }
			    System.err.println( "Param_name = " + name + " target:" + target + " / Error = " + chError.getError() + " Type = " + type);
			    chError = null;
			    formchk = null;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
	    return reint;
	}
}
