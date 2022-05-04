package jp.co.lastminute.maintenance;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HandlerCondition implements Serializable{
	public String jan_cd = "";
	public String sku_id = "";
	public String agt_cd = "";
	public String agt_name = "";
	public String product_name = "";
	public String product_seq = "";
	public String allotfrom = "";
	public String allotto = "";
	public String allotcounter = "";
	public String allot_seq = "";
	
	public String supnbr = "";
	
	public String product_type = "";
	public String s_catid = "";
	public String catClass = "";
	
	public int sorttype = 0; //0:jan_cd 1:product_name; 
	public int limittype = 0; //0=stopf_flg = 0; 1=stofflag =1;
	
	public HandlerCondition(){
	}
	public HandlerCondition getRequestByScatid( HttpServletRequest req　){
		if( req.getParameter("catid") != null )	this.product_type = req.getParameter("catid");
		if( req.getParameter("s_catid") != null )	this.s_catid = req.getParameter("s_catid");
		return this;
	}
	public HandlerCondition getRequestByJancd( HttpServletRequest req　){
		if( req.getParameter("catid") != null )	this.product_type = req.getParameter("catid");
		if( req.getParameter("jan_cd") != null )	this.jan_cd = req.getParameter("jan_cd");
		return this;
	}
	public HandlerCondition getRequestByProductSeq( HttpServletRequest req　){
		if( req.getParameter("product_seq") != null )	this.product_seq = req.getParameter("product_seq");
		if( req.getParameter("catClass") != null )	this.catClass = req.getParameter("catClass");
		return this;
	}
	
	public HandlerCondition getRequestForHotel( HttpServletRequest req　){
		if( req.getParameter("agt_cd") != null )	this.agt_cd = req.getParameter("agt_cd");
		if( req.getParameter("supnbr") != null )	this.supnbr = req.getParameter("supnbr");
		if( req.getParameter("catClass") != null )	this.catClass = req.getParameter("catClass");
		return this;
	}
	
	/**
	 * 種類を選ぶための検索
	 */
	public HandlerCondition getRequestByType( HttpServletRequest req ){
		if( req.getParameter("catid") != null )	this.product_type = req.getParameter("catid");
		return this;
	}
	/**
	 * リクエストのセット
	 * 
	 */
	public HandlerCondition getRequest( HttpServletRequest req, String product_type ){
		HttpSession session = req.getSession();
		String[] reqStr = {"", "", "", "", "", "", "", "", "", "", ""};
		if( req.getParameter("jan_cd") != null )	reqStr[0] = req.getParameter("jan_cd");
		if( req.getParameter("sku_id") != null )	reqStr[1] = req.getParameter("sku_id");
		if( req.getParameter("agt_cd") != null )	reqStr[2] = req.getParameter("agt_cd");
		if( req.getParameter("agt_name") != null )	reqStr[3] = req.getParameter("agt_name");
		if( req.getParameter("product_name") != null )	reqStr[4] = req.getParameter("product_name");
		if( req.getParameter("product_seq") != null )	reqStr[5] = req.getParameter("product_seq");
		if( req.getParameter("allotfrom") != null )	reqStr[6] = req.getParameter("allotfrom");
		if( req.getParameter("allotcounter") != null )	reqStr[7] = req.getParameter("allotcounter");
		if( req.getParameter("allot_seq") != null )	reqStr[8] = req.getParameter("allot_seq");
		
		if( req.getParameter("sorttype") != null )		reqStr[9] = req.getParameter("sorttype");
		if( req.getParameter("limittype") != null )	reqStr[10] = req.getParameter("limittype");
		
		this.product_type = product_type;
		int stlength = 0;
		for(int i=0; i<reqStr.length; i++){	stlength += reqStr[i].length();	}
		if( stlength > 0 ){
			this.jan_cd = reqStr[0];
			this.sku_id = reqStr[1];
			this.agt_cd = reqStr[2];
			this.agt_name = reqStr[3];
			this.product_name = reqStr[4];
			this.product_seq = reqStr[5];
			this.allotfrom	= reqStr[6];
			this.allotcounter = reqStr[7];
			this.allot_seq = reqStr[8];
			try{
				if(( reqStr[9] != null )&&( reqStr[9].length() > 0) ){
					this.sorttype = Integer.parseInt(  reqStr[9] );
				}
				if(( reqStr[10] != null )&&( reqStr[10].length() > 0) ){
					this.limittype = Integer.parseInt(  reqStr[10] );
				}
			}catch(Exception ex){	ex.printStackTrace();	}	
		}else{
			if( session.getAttribute("HandlerCondition") != null ){
				try{
					HandlerCondition condition = (HandlerCondition)session.getAttribute("HandlerCondition");
					condition.product_type = product_type;
					return condition;
				}catch(Exception ex){	ex.printStackTrace();	}
			}
		}
		return this;
	}
	/**
	 * Returns the agt_cd.
	 * @return String
	 */
	public String getAgt_cd() {
		return agt_cd;
	}

	/**
	 * Returns the agt_name.
	 * @return String
	 */
	public String getAgt_name() {
		return agt_name;
	}

	/**
	 * Returns the jan_cd.
	 * @return String
	 */
	public String getJan_cd() {
		return jan_cd;
	}

	/**
	 * Returns the limittype.
	 * @return int
	 */
	public int getLimittype() {
		return limittype;
	}

	/**
	 * Returns the product_name.
	 * @return String
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * Returns the sorttype.
	 * @return int
	 */
	public int getSorttype() {
		return sorttype;
	}

	/**
	 * Sets the agt_cd.
	 * @param agt_cd The agt_cd to set
	 */
	public void setAgt_cd(String agt_cd) {
		this.agt_cd = agt_cd;
	}

	/**
	 * Sets the agt_name.
	 * @param agt_name The agt_name to set
	 */
	public void setAgt_name(String agt_name) {
		this.agt_name = agt_name;
	}

	/**
	 * Sets the jan_cd.
	 * @param jan_cd The jan_cd to set
	 */
	public void setJan_cd(String jan_cd) {
		this.jan_cd = jan_cd;
	}

	/**
	 * Sets the limittype.
	 * @param limittype The limittype to set
	 */
	public void setLimittype(int limittype) {
		this.limittype = limittype;
	}

	/**
	 * Sets the product_name.
	 * @param product_name The product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	/**
	 * Sets the sorttype.
	 * @param sorttype The sorttype to set
	 */
	public void setSorttype(int sorttype) {
		this.sorttype = sorttype;
	}

	/**
	 * Returns the product_type.
	 * @return String
	 */
	public String getProduct_type() {
		return product_type;
	}

	/**
	 * Sets the product_type.
	 * @param product_type The product_type to set
	 */
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	/**
	 * Returns the sku_id.
	 * @return int
	 */
	public String getSku_id() {
		return sku_id;
	}

	/**
	 * Sets the sku_id.
	 * @param sku_id The sku_id to set
	 */
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	/**
	 * Returns the product_seq.
	 * @return int
	 */
	public String getProduct_seq() {
		return product_seq;
	}

	/**
	 * Sets the product_seq.
	 * @param product_seq The product_seq to set
	 */
	public void setProduct_seq(String product_seq) {
		this.product_seq = product_seq;
	}

	/**
	 * Returns the allotcounter.
	 * @return String
	 */
	public String getAllotcounter() {
		return allotcounter;
	}

	/**
	 * Returns the allotfrom.
	 * @return String
	 */
	public String getAllotfrom() {
		return allotfrom;
	}

	/**
	 * Returns the allotto.
	 * @return String
	 */
	public String getAllotto() {
		return allotto;
	}

	/**
	 * Sets the allotcounter.
	 * @param allotcounter The allotcounter to set
	 */
	public void setAllotcounter(String allotcounter) {
		this.allotcounter = allotcounter;
	}

	/**
	 * Sets the allotfrom.
	 * @param allotfrom The allotfrom to set
	 */
	public void setAllotfrom(String allotfrom) {
		this.allotfrom = allotfrom;
	}

	/**
	 * Sets the allotto.
	 * @param allotto The allotto to set
	 */
	public void setAllotto(String allotto) {
		this.allotto = allotto;
	}

	/**
	 * Returns the allot_seq.
	 * @return String
	 */
	public String getAllot_seq() {
		return allot_seq;
	}

	/**
	 * Sets the allot_seq.
	 * @param allot_seq The allot_seq to set
	 */
	public void setAllot_seq(String allot_seq) {
		this.allot_seq = allot_seq;
	}

	/**
	 * Returns the s_catid.
	 * @return String
	 */
	public String getS_catid() {
		return s_catid;
	}

	/**
	 * Sets the s_catid.
	 * @param s_catid The s_catid to set
	 */
	public void setS_catid(String s_catid) {
		this.s_catid = s_catid;
	}

}
