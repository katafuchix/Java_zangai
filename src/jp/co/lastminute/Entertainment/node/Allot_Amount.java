package jp.co.lastminute.Entertainment.node;

import java.io.*;

import jp.co.yobrain.util.dbDataAccesser;

import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.ParseFormat;
import java.lang.*;
import jp.co.lastminute.common.WeekdayToString;
import jp.co.lastminute.Entertainment.Property;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Allot_Amount implements Serializable{
	public String allot_seq = "0";
    public String sku_id	 = "0";
    public String allotcounter	 = "0";
    public String allotdate	 = "0";
    public String allottime	 = "0";
    public String stop_flg		 = "0";
    
    public Allot_Amount(){
    }
    /**
     * èâä˙âª
     */
    public Allot_Amount init( dbDataAccesser access, int row ){
    	allot_seq 	= access.getData(row, 14);
    	sku_id		= access.getData(row, 15);
    	allotcounter	= access.getData(row, 16);
    	allotdate	= access.getData(row, 17);
    	allottime	= access.getData(row, 18);
    	stop_flg	= access.getData(row, 19);
    	return this;
    }
    public String generateXml( Sku_Amount sku ){
    	DataFormat df = null;
    	ParseFormat pf = null;
		try{
			StringBuffer sb = new StringBuffer();
			sb.append( "<allot>\n<allot_seq>" + allot_seq + "</allot_seq>\n" );
			sb.append( "<sku_id>" + sku_id + "</sku_id>\n" );
			//////
			sb.append( "<agt_cd>" + sku.agt_cd + "</agt_cd>\n" );
			sb.append( "<product_seq>" + sku.product_seq + "</product_seq>\n" );
			sb.append( "<salesfrom>" + sku.salesfrom + "</salesfrom>\n" );
			sb.append( "<sku_name><![CDATA[" + sku.sku_name + "]]></sku_name>\n" );
			sb.append( "<viewstart>" + sku.viewstart + "</viewstart>\n" );
			sb.append( "<salesto>" + sku.salesto + "</salesto>\n" );
			sb.append( "<price>" + sku.price + "</price>\n" );
			sb.append( "<tax>" + sku.tax + "</tax>\n" );
			sb.append( "<sending>" + sku.sending + "</sending>\n" );
			sb.append( "<sending_tax>" + sku.sending_tax + "</sending_tax>\n" );
			sb.append( "<sending_calc_unit>" + sku.sending_calc_unit + "</sending_calc_unit>\n" );
			////
			sb.append( "<sku_id>" + sku_id + "</sku_id>\n" );
			sb.append( "<allotcounter>" + allotcounter + "</allotcounter>\n" );
			sb.append( "<allotdate>" + allotdate + "</allotdate>\n" );
			sb.append( "<maxorder>" + Maxorder() + "</maxorder>\n" );
			////<<<
			sb.append( "<allotdatestr><![CDATA[" + df.getDateTime4WWW( allotdate ).substring(5) );
			sb.append( " (" + WeekdayToString.getWeekdayStr( getWeekDay(), WeekdayToString._Japan ) + ")]]></allotdatestr>\n" );
			sb.append( "<pricestr><![CDATA[" + pf.ToPriceFormat( sku.price ) + "]]></pricestr>\n");
			////<<<
			sb.append( "<allottime>" + allottime + "</allottime>\n" );
			sb.append( "<stop_flg>" + stop_flg + "</stop_flg>\n</allot>" );
			return (sb.toString());
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	private int getWeekDay(){
		DataFormat df = null;
		return df.getWeekDay( allotdate );
	}
	private int Maxorder(){
		int counter = 0;
		try{
			counter = Integer.parseInt( allotcounter );
		}catch(Exception ex){	ex.printStackTrace();	}
		if( Property._maxorder < counter ){
			return Property._maxorder;
		}
		return counter;
	}
}
