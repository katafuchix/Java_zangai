package jp.co.lastminute.Restrant.detail;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.lastminute.Dflight.*;
import jp.co.yobrain.util.*;
import jp.co.lastminute.Restrant.node.*;
import jp.co.lastminute.Restrant.model.Menus;
import jp.co.lastminute.Restrant.Property;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DetailHandler implements Serializable {
	private String allotxml = "";
	private String courcexml = "";
	private String restrant = "";
	private int targetdate = 0;
	/**
	 * コンストラクタ
	 */
	public DetailHandler(int targetdate, String restrant) {
		this.allotxml = "";
		this.courcexml = "";
		this.targetdate = targetdate;
		this.restrant = restrant;
	}
	/**
	 * 詳細の取得
	 */
	public String detailXml() {
		//System.err.print("Detail Handler VECOTR SIZE=" + rows.size());
		String reStr = "";
		try {
			StringBuffer sub = new StringBuffer();
			sub.append("<Restantdetail>\n");
			sub.append("<ALLOTS>" + getAllotxml() + "</ALLOTS>\n");
			sub.append("<COURCES>" +  getCourcexml() + "</COURCES>\n");
			sub.append("</Restantdetail>\n");
			reStr = sub.toString();
			System.err.println(reStr);
		} catch (Exception ex) {
		}
		return reStr;
	}
	/**
	 * コースデータの取得
	 */
	public void setSqldataCource( Vector courcerows  ){
		try{
			dbDataAccesser accesser = new dbDataAccesser( courcerows );
			for(int i=0; i<accesser.getRowsize(); i++){
				Menus menu = new Menus();
				menu.setCourseid( accesser.getDatabyInt(i, 0));
				menu.setRestaurant( accesser.getData(i, 1));
				menu.setCoursecd( accesser.getDatabyInt(i, 2) );
				menu.setPriority( accesser.getDatabyInt(i, 3 ));
				menu.setViewfrom( accesser.getDatabyInt(i, 4));
				menu.setViewto( accesser.getDatabyInt(i, 5) );
				menu.setCoursename( accesser.getData(i, 6 ));
				menu.setDishes( accesser.getDatabyInt(i, 7));
				menu.setPrice( accesser.getDatabyInt(i, 8));
				menu.setTax( accesser.getDatabyInt(i, 9) );
				menu.setC_comment( accesser.getData(i, 10));
				menu.setOnepoint( accesser.getData(i, 11) );
				menu.setImageuri( accesser.getData(i, 12));
				menu = null;
				courcexml += "<COURCE>\n" + menu.getXmlString() + "\n"
						  + "<AGT_CT>" + accesser.getData(i, 13) + "</AGT_CD>\n</COURCE>\n";
			}
			accesser = null;
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	/**
	 * 在庫データの取得
	 */
	public void setSqldata(Vector allotrows ) {
		try {
			dbDataAccesser accesser = new dbDataAccesser( allotrows );
			Vector shop_of_sku = new Vector();
			for (int i = 0; i < accesser.getRowsize(); i++) {
				boolean sku_up = false;
				for (int s = 0; s < shop_of_sku.size(); s++) {
					String compare = ((String[]) shop_of_sku.get(s))[0];
					if (compare.equals(accesser.getData(i, 3))) {
						sku_up = true;
					}
					compare = null;
				}
				if (!sku_up) {
					String[] input =
						{ accesser.getData(i, 3), accesser.getData(i, 8)};
					shop_of_sku.add(input);
					input = null;
				}
			}
			int rnodesize = shop_of_sku.size();
			RShop shop = new RShop(rnodesize);
			for (int i = 0; i < rnodesize; i++) {
				String[] skuidstr = (String[]) shop_of_sku.get(i);
				int skuid = Integer.parseInt(skuidstr[0]);
				shop.setSkuid(skuid, skuidstr[1]);
			}
			RDays rDays = new RDays(shop);
			for (int i = 0; i < accesser.getRowsize(); i++) {
				RStocks rstock = new RStocks();
				rstock.allotid = accesser.getDatabyInt(i, 0);
				rstock.restaurant = accesser.getData(i, 1);
				rstock.timeid = accesser.getDatabyInt(i, 2);
				int skuidcd = accesser.getDatabyInt(i, 3);
				rstock.skuid = skuidcd;
				int allotdate = accesser.getDatabyInt(i, 4);
				rstock.allotdate = allotdate;
				rstock.allottent = accesser.getDatabyInt(i, 5);
				///// /////
				Rnode rnode = rDays.getRnodeBydate(allotdate, rnodesize);
				int skuposition = shop.getPosition(skuidcd);
				if (skuposition != -1) {
					rnode.setRnode(skuposition, rstock);
				}
				rDays.setRnode(rnode);
				rstock = null;
			}
			///////////在庫系のＸＭＬ制作//////////
			this.allotxml = rDays.getXmlString(this.targetdate) + shop.getXmlString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the allotxml.
	 * @return String
	 */
	public String getAllotxml() {
		return allotxml;
	}

	/**
	 * Returns the restrant.
	 * @return String
	 */
	public String getRestrant() {
		return restrant;
	}

	/**
	 * Returns the targetdate.
	 * @return int
	 */
	public int getTargetdate() {
		return targetdate;
	}

	/**
	 * Sets the allotxml.
	 * @param allotxml The allotxml to set
	 */
	public void setAllotxml(String allotxml) {
		this.allotxml = allotxml;
	}

	/**
	 * Sets the restrant.
	 * @param restrant The restrant to set
	 */
	public void setRestrant(String restrant) {
		this.restrant = restrant;
	}

	/**
	 * Sets the targetdate.
	 * @param targetdate The targetdate to set
	 */
	public void setTargetdate(int targetdate) {
		this.targetdate = targetdate;
	}

	/**
	 * Returns the courcexml.
	 * @return String
	 */
	public String getCourcexml() {
		return courcexml;
	}

	/**
	 * Sets the courcexml.
	 * @param courcexml The courcexml to set
	 */
	public void setCourcexml(String courcexml) {
		this.courcexml = courcexml;
	}

}
