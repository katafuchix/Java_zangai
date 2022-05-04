package jp.co.lastminute.Restrant.model;

import java.io.*;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Menus implements Serializable{
	private int courseid 		= 0;
	private String restaurant 	= "";
	private int coursecd 		= 0;
	private int priority 		= 0;
	private int viewfrom 		= 0;
	private int viewto   	 	= 0;
	private String coursename 	= "";
	private int dishes		= 0;
	private int price			= 0;
	private int tax			= 0;
	private String c_comment 	= "";
	private String onepoint 	= "";
	private String imageuri 	= "";
	/**
	 * コンストラクタ
	 */
	public Menus(){
		
	}
	/**
	 * XMLストリング
	 */
	public String getXmlString(){
		String reStr = "";
		try{
			StringBuffer sb = new StringBuffer();
			sb.append( "<COURCES>\n" );
			sb.append( "<COURSEID>"+ this.courseid +"</COURSEID>\n" );
			sb.append( "<RESTAURANT>"+ this.restaurant +"</RESTAURANT>\n" );
			sb.append( "<COURSECD>"+ this.coursecd +"</COURSECD>\n" );
			sb.append( "<PRIORITY>"+ this.priority +"</PRIORITY>\n" );
			sb.append( "<VIEWFROM>"+ this.viewfrom +"</VIEWFROM>\n" );
			sb.append( "<VIEWTO>"+ this.viewto +"</VIEWTO>\n" );
			sb.append( "<COURSENAME><![CDATE[" + this.coursename + "]]></COURSENAME>\n" );
			sb.append( "<DISHES>"+ this.dishes +"</DISHES>\n" );
			sb.append( "<PRICE>"+ this.price +"</PRICE>\n" );
			sb.append( "<TAX>"+ this.tax +"</TAX>\n" );
			sb.append( "<C_COMMENT><![CDATE[" + this.c_comment + "]]></C_COMMENT>\n" );
			sb.append( "<ONEPOINT><![CDATE["+ this.onepoint +"]]></ONEPOINT>\n" );
			sb.append( "<IMAGEURI><![CDATE["+ this.imageuri + "]]></IMAGEURI>\n" );
			sb.append( "</OURCES>\n" );
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * Returns the c_comment.
	 * @return String
	 */
	public String getC_comment() {
		return c_comment;
	}

	/**
	 * Returns the coursecd.
	 * @return int
	 */
	public int getCoursecd() {
		return coursecd;
	}

	/**
	 * Returns the courseid.
	 * @return int
	 */
	public int getCourseid() {
		return courseid;
	}

	/**
	 * Returns the coursename.
	 * @return String
	 */
	public String getCoursename() {
		return coursename;
	}

	/**
	 * Returns the dishes.
	 * @return int
	 */
	public int getDishes() {
		return dishes;
	}

	/**
	 * Returns the imageuri.
	 * @return String
	 */
	public String getImageuri() {
		return imageuri;
	}

	/**
	 * Returns the onepoint.
	 * @return String
	 */
	public String getOnepoint() {
		return onepoint;
	}

	/**
	 * Returns the price.
	 * @return int
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns the priority.
	 * @return int
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Returns the restaurant.
	 * @return String
	 */
	public String getRestaurant() {
		return restaurant;
	}

	/**
	 * Returns the tax.
	 * @return int
	 */
	public int getTax() {
		return tax;
	}

	/**
	 * Returns the viewfrom.
	 * @return int
	 */
	public int getViewfrom() {
		return viewfrom;
	}

	/**
	 * Returns the viewto.
	 * @return int
	 */
	public int getViewto() {
		return viewto;
	}

	/**
	 * Sets the c_comment.
	 * @param c_comment The c_comment to set
	 */
	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}

	/**
	 * Sets the coursecd.
	 * @param coursecd The coursecd to set
	 */
	public void setCoursecd(int coursecd) {
		this.coursecd = coursecd;
	}

	/**
	 * Sets the courseid.
	 * @param courseid The courseid to set
	 */
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	/**
	 * Sets the coursename.
	 * @param coursename The coursename to set
	 */
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	/**
	 * Sets the dishes.
	 * @param dishes The dishes to set
	 */
	public void setDishes(int dishes) {
		this.dishes = dishes;
	}

	/**
	 * Sets the imageuri.
	 * @param imageuri The imageuri to set
	 */
	public void setImageuri(String imageuri) {
		this.imageuri = imageuri;
	}

	/**
	 * Sets the onepoint.
	 * @param onepoint The onepoint to set
	 */
	public void setOnepoint(String onepoint) {
		this.onepoint = onepoint;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Sets the priority.
	 * @param priority The priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Sets the restaurant.
	 * @param restaurant The restaurant to set
	 */
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Sets the tax.
	 * @param tax The tax to set
	 */
	public void setTax(int tax) {
		this.tax = tax;
	}

	/**
	 * Sets the viewfrom.
	 * @param viewfrom The viewfrom to set
	 */
	public void setViewfrom(int viewfrom) {
		this.viewfrom = viewfrom;
	}

	/**
	 * Sets the viewto.
	 * @param viewto The viewto to set
	 */
	public void setViewto(int viewto) {
		this.viewto = viewto;
	}

}
