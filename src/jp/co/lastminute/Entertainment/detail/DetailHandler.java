package jp.co.lastminute.Entertainment.detail;

import java.io.*;
import java.util.*;

import jp.co.lastminute.Entertainment.node.*;
import jp.co.yobrain.util.dbDataAccesser;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DetailHandler implements Serializable{
	private String allotmentxml = "";
	private String detailxml = "";
	public DetailHandler(){
	}
	public static String detailXml( Vector rows ){
		dbDataAccesser access = new dbDataAccesser( rows ) ;
		return makeAllotmentxml( access );
		
	}
	/**
	 * XMLÇÃê∂ê¨
	 */
	public static String makeAllotmentxml( dbDataAccesser access ){
		Production production = new Production();
		try{
			for( int i=0; i<access.getRowsize(); i++){
				Sku_Amount temp = new Sku_Amount();
				temp.init( access, i );
				Sku_Amount sku = production.putMap( temp );
				temp = null;
				
				Allot_Amount allot = new Allot_Amount();
				allot.init( access, i );
				sku.setAllot( allot );
				//èâä˙âª
				sku = null;
				allot = null;
			}
			production.product_name = access.getData(0, 20);
			return production.generateXml();
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return "";	
	}
	/**
	 * Returns the allotmentxml.
	 * @return String
	 */
	public String getAllotmentxml() {
		return allotmentxml;
	}

	/**
	 * Returns the detailxml.
	 * @return String
	 */
	public String getDetailxml() {
		return detailxml;
	}

	/**
	 * Sets the allotmentxml.
	 * @param allotmentxml The allotmentxml to set
	 */
	public void setAllotmentxml(String allotmentxml) {
		this.allotmentxml = allotmentxml;
	}

	/**
	 * Sets the detailxml.
	 * @param detailxml The detailxml to set
	 */
	public void setDetailxml(String detailxml) {
		this.detailxml = detailxml;
	}

}
