package jp.co.lastminute.maintenance.hotel;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import jp.co.lastminute.maintenance.HandlerCondition;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HotelhandlerCondition extends HandlerCondition implements Serializable{
	//êVãKí«â¡ï™
	public String local_area_cd = "";
	public String parent_code = "";
	public String state_cd = "";
	public String city_cd = "";
	public String supnbr = "";
	public String htlcat = "";
	
	public HotelhandlerCondition(){
	}
	public HandlerCondition getRequestBySearch( HttpServletRequest reqÅ@){
		if( req.getParameter("agt_cd") != null )	this.agt_cd = req.getParameter("agt_cd");
		if( req.getParameter("supnbr") != null )	this.supnbr = req.getParameter("supnbr");
		
		if( req.getParameter("local_area_cd") != null )	this.local_area_cd = req.getParameter("local_area_cd");
		if( req.getParameter("parent_code") != null )	this.parent_code = req.getParameter("parent_code");
		
		if( req.getParameter("state_cd") != null )	this.state_cd = req.getParameter("state_cd");
		if( req.getParameter("city_cd") != null )	this.city_cd = req.getParameter("city_cd");
		
		if( req.getParameter("htlcat") != null )	this.htlcat = req.getParameter("htlcat");
		return this;
	}
	
}
