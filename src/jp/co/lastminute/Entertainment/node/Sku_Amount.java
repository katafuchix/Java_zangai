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
public class Sku_Amount implements Serializable{
	public String sku_id 	= "0";
    public String agt_cd 	= "";
    public String product_seq = "0";
    public String salesfrom 	= "0";
    public String sku_name 	= "";
    public String sheatandso 	= "0";
    public String viewstart = "0";
    public String optionalcomment = "0";
    public String salesto 		= "0";
    public String price 		= "0";
    public String tax 			= "0";
    public String sending 		= "0";
    public String sending_tax 	= "0";
    public String sending_calc_unit = "0";
    
    public Vector allot_Amounts = new Vector();
    
    public Sku_Amount(){
    }
    /**
     * SKUÇÃèâä˙âª
     */
    public Sku_Amount init( dbDataAccesser access, int row ){
    	sku_id 	= access.getData(row, 0);
    	agt_cd 	= access.getData(row, 1);
    	product_seq = access.getData(row, 2);
    	salesfrom 	= access.getData(row, 3);
    	sku_name 	= access.getData(row, 4);
    	sheatandso 	= access.getData(row, 5);
    	viewstart 	= access.getData(row, 6);
    	optionalcomment = access.getData(row, 7);
    	salesto 		= access.getData(row, 8);
    	price 			= access.getData(row, 9);
    	tax 			= access.getData(row, 10);
   		sending 		= access.getData(row, 11);
    	sending_tax 	= access.getData(row, 12);
    	sending_calc_unit	= access.getData(row, 13);
    	return this; 	
    }
    /**
     * ç›å…ÇÃí«â¡
     */
    public void setAllot( Allot_Amount allot ){
    	allot_Amounts.add( allot );  	
    }
    public String generateXml(){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append( "<sku>\n" );
			sb.append( "<sku_id>" + sku_id + "</sku_id>\n" );
			sb.append( "<agt_cd>" + agt_cd + "</agt_cd>\n" );
			sb.append( "<product_seq>" + product_seq + "</product_seq>\n" );
			sb.append( "<salesfrom>" + salesfrom + "</salesfrom>\n" );
			sb.append( "<sku_name><![CDATA[" + sku_name + "]]></sku_name>\n" );
			sb.append( "<sheatandso><![CDATA[" + sheatandso + "]]></sheatandso>\n" );
			sb.append( "<viewstart>" + viewstart + "</viewstart>\n" );
			sb.append( "<optionalcomment><![CDATA[" + optionalcomment + "]]></optionalcomment>\n" );
			sb.append( "<salesto>" + salesto + "</salesto>\n" );
			sb.append( "<price>" + price + "</price>\n" );
			sb.append( "<tax>" +tax + "</tax>\n" );
			sb.append( "<sending>" + sending + "</sending>\n" );
			sb.append( "<sending_tax>" + sending_tax + "</sending_tax>\n" );
			sb.append( "<sending_calc_unit>" + sending_calc_unit + "</sending_calc_unit>\n" );
			sb.append( "<allots>\n" );
			for(int i=0; i<allot_Amounts.size(); i++){
				Allot_Amount allot = ( Allot_Amount )allot_Amounts.get( i );
				sb.append( allot.generateXml( this ) + "\n" );
			}
			sb.append( "</allots>\n" );
			sb.append( "</sku>" );
			return (sb.toString());
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}  
}
