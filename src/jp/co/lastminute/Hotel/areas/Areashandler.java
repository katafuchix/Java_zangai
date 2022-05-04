package jp.co.lastminute.Hotel.areas;

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
public class Areashandler implements Serializable{
	private Areas areas = null;
	/**
	 * コンボの出力
	 */
	public String getHtmlVer( String name1, String name2, String align, String valign ){
		try{
			StringBuffer sb = new StringBuffer();
			
			if( areas.getState_cd().length() != 0 ){
				sb.append( "<td align='" + align + "' valign='" + valign + "' >\n" );
				StateParam state = areas.getState();
				sb.append( "<input type='hidden' name='" + name1 + "' value='" + state.state_cd + "'>\n" );
				sb.append( state.state_name );
				sb.append( "</td>\n" );
				sb.append( "<td align='" + align + "' valign='" + valign + "' >\n" );
				sb.append( getCitys( state, name2 ) );
				sb.append( "</td>\n" );
			}else{
				sb.append( "<td align='" + align + "' valign='" + valign + "' >\n" );
				Hashtable states = areas.states;
				sb.append( "<select name='" + name1 + "'>\n");
				//states.get( )
				for( int i=0; i<50; i++){
					if( states.get( "" + i + "" ) != null ){
						StateParam st = (StateParam)states.get( "" + i + "" );
						sb.append( "<option value='" + st.state_cd + "'>" + st.state_name + "</option>\n" );
					}					
				}
				sb.append( "</select>\n" );
				sb.append( "</td>\n" );
				sb.append( "<td align='" + align + "' valign='" + valign + "' >\n" );
				sb.append( "<input type='hidden' name='" + name2 + "' value=''/>\n" );
				sb.append( "---------" );
				sb.append( "</td>\n" );
			}
			
			return sb.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "";	
	}
	public String getCitys( StateParam state,String name2 ){
		try{
			StringBuffer sb = new StringBuffer();
			if( areas.getCity_cd().length() == 0 ){
				Vector citys = state.citys;
				sb.append( "<select name='" + name2 + "'>\n");
				for(int i=0; i<citys.size(); i++){
					CityParam city = (CityParam)citys.get( i );
					sb.append( "<option value='" + city.city_cd + "'>" + city.city_name + "</option>\n" );
					city = null;
				}
				sb.append( "</select>\n" );
			}else{
				Vector citys = state.citys;
				String citycd = areas.getCity_cd();
				for(int i=0; i<citys.size(); i++){
					CityParam city = (CityParam)citys.get( i );
					if( city.city_cd.equals( citycd )){
						sb.append( city.city_name + "\n" );
						break;
					}
					city = null;
				}
				sb.append( "<input type='hidden' name='" + name2 + "' value='" + citycd + "'/>\n" );
			}
			return sb.toString();
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return "";
	}
	/**
	 * コンストラクター
	 */
	public Areashandler(){
	}
	/**
	 * コンテキストを処理
	 */
	public void setServletContext( ServletContext context ){
		System.err.println("setServletContext 1:");
		if( context.getAttribute( ContextProperty.HAREAS_ ) == null ){
			System.err.println("setServletContext 2:");
			this.areas = getContextopbj();
			System.err.println("setServletContext 3:");
			context.setAttribute( ContextProperty.HAREAS_, areas );
		}else{
			areas = (Areas)context.getAttribute( ContextProperty.HAREAS_ ) ;
			System.err.println("setServletContext 4:");
		}
		System.err.println("setServletContext 5:");
	}
	/**
	 * リクエストを処理
	 */
	public Areas setRequest( HttpServletRequest req ){
		if( (req.getParameter( "state_cd" ) != null )&&
		 ( req.getParameter( "state_cd" ).length() != 0 )){
			areas.setState_cd( req.getParameter( "state_cd" ) );
		}else{
			areas.setState_cd( "" );
		 }
		if( (req.getParameter( "city_cd" ) != null )&&
		 ( req.getParameter( "city_cd" ).length() != 0 )){
			areas.setCity_cd( req.getParameter( "city_cd" ) );
		}else{
			areas.setCity_cd( "" );
		}
		return areas;
	}
	/**
	 * コンテキスト読む
	 */
	public Areas getContextopbj(){
		String fileName = ContextProperty.HAREASLocate_;
		Areas reObj = null;
		System.err.println( "fileName= " + fileName );
		try{
			FileInputStream fin = new FileInputStream(fileName);
			System.err.println( "fileName=step 1" );
			ObjectInputStream oos = new ObjectInputStream( fin );
			System.err.println( "fileName=step 2" );
			reObj = (Areas)oos.readObject();	
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
	 * Returns the areas.
	 * @return Areas
	 */
	public Areas getAreas() {
		return areas;
	}

	/**
	 * Sets the areas.
	 * @param areas The areas to set
	 */
	public void setAreas(Areas areas) {
		this.areas = areas;
	}
	public String getStateName( String state_cd ){
		StateParam st = this.areas.getStateParam( state_cd );
		return st.state_name;
	}
	public String getCityName( String state_cd, String city_cd){
		StateParam st = this.areas.getStateParam( state_cd );
		Vector ctvec = st.citys;
		for(int i=0; i<ctvec.size(); i++){
			CityParam ct = (CityParam)ctvec.get( i );
			if( ct.city_cd.equals( city_cd )){
				return ct.city_name;	
			}
		}
		return "";
	}
}
