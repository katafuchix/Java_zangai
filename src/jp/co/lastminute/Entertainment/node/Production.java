package jp.co.lastminute.Entertainment.node;

import java.io.*;
import java.util.*;

import jp.co.yobrain.util.dbDataAccesser;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Production implements Serializable{
	public Vector skus = null;

	public String product_name = "";
	public Production(){
		this.skus = new Vector();
	}
	public Sku_Amount putMap( Sku_Amount temp_ ){
		try{
			String key = temp_.sku_id;
			for(int i=0; i<skus.size(); i++){
				Sku_Amount sku = ( Sku_Amount )skus.get( i );
				if(sku.sku_id.equals( key )){
					return sku;
				}
			}
			skus.add( temp_ );
			return temp_;
		}catch(Exception rx){
			rx.printStackTrace();
		}
		return null;
	}
	public String generateXml(){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append( "<production>\n" );
			sb.append( "<product_name><![CDATA[" + product_name + "]]></product_name>\n" );
			sb.append( "<skus>\n" );
			for(int i=0; i<skus.size(); i++){
				Sku_Amount sku = ( Sku_Amount )skus.get( i );
				sb.append( sku.generateXml() );
			}
			sb.append( "</skus>\n" );
			sb.append( "</production>" );
			return (sb.toString());
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
}
