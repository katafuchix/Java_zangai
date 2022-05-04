package jp.co.lastminute.Restrant;

import java.io.*;
import java.lang.*;
import java.util.*;

import jp.co.yobrain.util.*;
import jp.co.lastminute.Restrant.node.*;
import jp.co.lastminute.Restrant.Property;


/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class NodeHandler implements Serializable {
	private String allotxml = "";
	public NodeHandler(){
	}
	/**
	 * ÉfÅ[É^ÇÃï€éù
	 */
	public void setSqldata( Vector rows, int targetdate, String restrant ){
		try{
			dbDataAccesser accesser = new dbDataAccesser( rows );
			Vector shop_of_sku = new Vector();
			for( int i=0; i<accesser.getRowsize(); i++){
				boolean sku_up = false;
				for( int s=0; s<shop_of_sku.size(); s++){
					String compare = ((String[])shop_of_sku.get(s))[0];
					if( compare.equals( accesser.getData( i, 3 ) ) ){
						sku_up = true;
					}
					compare = null;
				}
				if( !sku_up ){
					String[] input = { accesser.getData( i, 3 ), accesser.getData( i, 8 ) };
					shop_of_sku.add( input );
					input = null;
				}
			}
			int rnodesize = shop_of_sku.size();
			RShop shop = new RShop( rnodesize );
			for( int i=0; i<rnodesize; i++){
				String[] skuidstr = (String[])shop_of_sku.get( i );
				int skuid = Integer.parseInt( skuidstr[0] );
				shop.setSkuid( skuid, skuidstr[1] );
			}
			RDays rDays = new RDays( shop );
			for( int i=0; i<accesser.getRowsize(); i++) {
				RStocks rstock = new RStocks();
				rstock.allotid = accesser.getDatabyInt( i, 0 );
				rstock.restaurant = accesser.getData( i, 1 );
				rstock.timeid = accesser.getDatabyInt( i, 2 );
				int skuidcd = accesser.getDatabyInt( i, 3 );
				rstock.skuid = skuidcd;
				int allotdate = accesser.getDatabyInt( i, 4 );
				rstock.allotdate = allotdate;
				rstock.allottent = accesser.getDatabyInt( i, 5 );
				///// /////
				Rnode rnode = rDays.getRnodeBydate( allotdate ,rnodesize );
				int skuposition = shop.getPosition( skuidcd );
				if( skuposition != -1 ){
					rnode.setRnode(skuposition, rstock);
				}
				rDays.setRnode( rnode );
				rstock = null;
			}
			///////////ç›å…ånÇÃÇwÇlÇkêßçÏ//////////
			this.allotxml = rDays.getXmlString( targetdate ) + shop.getXmlString();
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	
	/**
	 * XMLÉfÅ[É^ÇÃéÊìæ
	 */
	public String getXmlString(){
		String reStr = "";
		try{
			StringBuffer sub = new StringBuffer();
			sub.append("<restrant>\n" );
			sub.append(  this.allotxml );
			sub.append("</restrant>\n" );
			reStr = sub.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return reStr;
	}
}
