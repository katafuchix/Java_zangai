package jp.co.lastminute.Hotel;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import jp.co.yobrain.util.ParseFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class PriceView implements Serializable{
	
	public static String getViewStr4Search( String str ){
		ParseFormat pf = null;
		if( str.length() == 0 ){	return "";	}
		try{
		String[] prices = PriceView.getPriceArray( str );
		String death = "txt10darkgray";
		String live = "txt10";
		String[] colors = {death, death, death, death};
		String[] priceview = {"--", "--", "--", "--"};
		for(int i=0; i<4; i++){
			if( !prices[ i ].equals("0") ){
				colors[ i ] = live;
				priceview[ i ] = pf.ToPriceFormat( prices[ i ] )+"円";
			}
		}
		return "<tr>\n"
				+"<td align='center' width='12%'>&nbsp;&nbsp;&nbsp;</td>\n"
				+"<td align='left' width='11%'><span class='"+ colors[0] +"'>1人利用 </span></td>\n"
				+"<td align='right' width='11%'><span class='"+ colors[0] +"'>"+priceview[0]+"</span></td>\n"
				+"<td align='left' width='11%'><span class='"+ colors[1] +"'>2人利用 </span></td>\n"
				+"<td align='right' width='11%'><span class='"+ colors[1] +"'>"+priceview[1]+"</span></td>\n"
				+"<td align='left' width='11%'><span class='"+ colors[2] +"'>3人利用 </span></td>\n"
				+"<td align='right' width='11%'><span class='"+ colors[2] +"'>"+priceview[2]+"</span></td>\n"
				+"<td align='left' width='11%'><span class='"+ colors[3] +"'>4人利用 </span></td>\n"
				+"<td align='right' width='11%'><span class='"+ colors[3] +"'>"+priceview[3]+"</span></td>\n"
				+"</tr>\n";
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	public static String[] getPriceArray( String str ){
		System.err.println( str );
		String[] reStrs = Pattern.compile(",").split( str );
		System.err.println( "reStrs.length :"+ reStrs.length );
		return reStrs;
	}
}
