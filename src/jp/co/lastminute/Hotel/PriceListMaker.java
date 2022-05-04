package jp.co.lastminute.Hotel;

import java.io.*;
import java.util.*;
import jp.co.lastminute.Hotel.jdbc.*;
import jp.co.lastminute.common.HtmlpartMaker;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.DataFormat;
import jp.co.yobrain.util.ParseFormat;

import jp.co.lastminute.Hotel.Property;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PriceListMaker {
	private Webaltp[] altps = null;	
	private int min_nr = 0;
	private int max_nr = 3;
	private static int maxnight = 3;
	private static int maxrooms = 2;
	private String campaign = "";
	
	public String agt_cd = "";
	private static String pagt_cd = Property.pagt_cd;
	public int numberOfMales = 0;
	public int numberOfFemales = 0;
	public int numberOfAdults = 0;
	public int numberOfKids = 0;
	public int numberOfYoji1 = 0;
	public int nights = 0;
	public int numberOfRooms = 0;
	
	public static final String lntryokan = "2917";
	public PriceListMaker( Webaltp[] altps ){
		this.altps = altps;
		this.max_nr = altps[0].max_nr;
		this.min_nr = altps[0].min_nr;
		this.campaign = altps[0].campaing;
	}
	public PriceListMaker(){
		
		
	}
	public String getList(){		
		if( this.agt_cd.equals( pagt_cd ) ){
			boolean cp_flg = false;
			if( this.campaign.equals( lntryokan ) ){
				cp_flg = true;
			}
			return getListByKnt( cp_flg );
		}
		return getListByNta();	
	}
	public String getHoot(){		
		if( this.agt_cd.equals( pagt_cd ) ){
			boolean cp_flg = false;
			if( this.campaign.equals( lntryokan ) ){
				cp_flg = true;
			}
			return getFootKnt( cp_flg );
		}
		return getFootNta();
	}
	private static String getChecked(int ch){
		if( ch == -1 ){
			return " checked";
		}
		return "";
	}

	public String getListByNta(){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<table border='0' cellspacing='1' cellpadding='0' width='100%'>\n");
			sb.append( getHeaderNta() );
			for(int i=0; i<altps.length; i++){
				sb.append("<tr bgcolor='#ffffff'>\n<td align='center'>\n");
				if( altps[ i ].haveal == 0){					
					sb.append("<input type='radio' name='checkindate' value='"+altps[ i ].altdat+"'"+ getChecked( altps[ i ].haveal )+"/>");
				}else if(altps[ i ].haveal == -1){
					sb.append("<span class='txt10'>--</span>");
				}else{
					sb.append("<span class='txt10'>満室</span>");
				}
				sb.append("</td>\n");
				sb.append( getPriceListNta( altps[ i ] ) );
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
			return sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	public String getListByKnt(boolean flg){	//2917 = true
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<table border='0' cellspacing='1' cellpadding='0' width='100%'>\n");
			sb.append( getHeaderKnt( flg ) );
			for(int i=0; i<altps.length; i++){
				sb.append("<tr bgcolor='#ffffff'>\n<td align='center'>\n");
				if( altps[ i ].haveal == 0){					
					sb.append("<input type='radio' name='checkindate' value='"+altps[ i ].altdat+"'"+ getChecked( altps[ i ].haveal )+"/>");
				}else if(altps[ i ].haveal == -1){
					sb.append("<span class='txt10'>--</span>");
				}else{
					sb.append("<span class='txt10'>満室</span>");
				}
				sb.append("</td>\n");
				sb.append( getPriceListKnt( altps[ i ], flg ) );
				sb.append("</tr>\n");
			}
			sb.append("</table>\n");
			return sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	private static String getPriceListNta( Webaltp altp ){
		DataFormat df = null;
		StringBuffer sb = new StringBuffer();
			sb.append("<td width='10%' bgcolor='#ffffff' align='center' height='28'>"+ df.getDateTime4WWW( altp.altdat ).substring(5) + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.priceStr + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.price2Str + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.price3Str + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.price4Str + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.price5Str + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.pricea2Str + "<br/>\n");
			sb.append("            " + altp.priceb2Str + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.pricea3Str + "<br/>\n");
			sb.append("           " + altp.priceb4Str + "</td>\n");
			sb.append("<td align='right' width='10%' height='28'>" + altp.pricea4Str + "<br/>\n");
			sb.append("           " + altp.priceb4Str + "</td>\n");
		return sb.toString();
	}
	private static String getPriceListKnt( Webaltp altp, boolean flg ){
		StringBuffer sb = new StringBuffer();
		if( flg ){
			sb.append("<td width='10%' bgcolor='#ffffff' align='center'>"+altp.altdatStr+"</td>\n");
			sb.append("<td align='right' width='9%'>"+altp.price2Str+"</td>\n");	//PRICE2
			sb.append("<td align='right' width='9%'>"+altp.price3Str+"</td>\n");	//PRICE3
			sb.append("<td align='right' width='9%'>"+altp.price4Str+"</td>\n");	//PRICE4
			sb.append("<td align='right' width='9%'>"+altp.pricea2Str+"</td>\n");	//PRICEA2
			sb.append("<td align='right' width='9%'>"+altp.pricea3Str+"</td>\n");	//PRICEA3
			sb.append("<td align='right' width='9%'>"+altp.pricea4Str+"</td>\n");	//PRICEA4
			sb.append("<td align='right' width='9%'>"+altp.priceb2Str+"</td>\n");	//PRICEB2
			sb.append("<td align='right' width='9%'>"+altp.priceb3Str+"</td>\n");	//PRICEB3
			sb.append("<td align='right' width='9%'>"+altp.priceb4Str+"</td>\n");	//PRICEB4
		}else{
			if (altp.min_nr <= 1 && 1 <= altp.max_nr){	// single
				altp.price2Str = "--";
				altp.price3Str = "--";
			}else if (altp.min_nr <= 2 && 2 <= altp.max_nr){		// twin
				altp.priceStr = "--";
				altp.price3Str = "--";
			}else if (altp.min_nr <= 3 && 3 <= altp.max_nr){		// tripple
				altp.priceStr = "--";
				altp.price2Str = "--";
			}
			sb.append("<td width='25%' align='center'>"+altp.altdatStr.substring(5)+"</td>\n");
			sb.append("<td width='25%' align='right'>"+altp.priceStr+"</td>\n");	//PRICE0
			sb.append("<td width='25%' align='right'>"+altp.price2Str+"</td>\n");	//PRICE2
			sb.append("<td width='25%' align='right'>"+altp.price3Str+"</td>\n");	//PRICE3
		}
		return sb.toString();
	}
	private static String getHeaderNta(){
		return "<tr bgcolor='#ffffff'>\n"
		+"	<td width='8%' bgcolor='#eeeeee'>&nbsp;</td>\n"
		+"  <td width='11%' bgcolor='#eeeeee' align='center' class='txt10b'>宿泊日</td>\n"
		+"  <td colspan='5' align='center' class='txt10b' bgcolor='#eeeeee'>おとな</td>\n"
		+"  <td colspan='3' align='center' class='txt10b' bgcolor='#eeeeee'>上段：子供A／下段：子供B</td>\n"
		+"</tr>\n"
		+"<tr bgcolor='#ffffff'>\n"
		+" <td width='9%' bgcolor='#ffffff'>　 </td>\n"
		+" <td width='11%'>　</td>\n"
		+" <td width='10%' class='txt10b'>1名様<br>1室</td>\n"
		+" <td width='10%' class='txt10b'>2名様<br>1室</td>\n"
		+" <td width='10%' class='txt10b'>3名様<br>1室<br></td>\n"
		+" <td width='10%' class='txt10b'>4名様<br>1室</td>\n"
		+" <td width='10%' class='txt10b'>5名様<br>1室</td>\n"
		+" <td width='10%' class='txt10b'>2名様<br>1室</td>\n"
		+" <td width='10%' class='txt10b'>3名様<br>1室</td>\n"
		+" <td width='10%' class='txt10b'>4名様<br>1室</td>\n"
		+"</tr>\n";
	}
	private static String getHeaderKnt( boolean flg ){
		StringBuffer sb = new StringBuffer();
		if( flg ){
			return "<tr bgcolor='#ffffff'>\n"
			+"<td width='8%' bgcolor='#eeeeee'>&nbsp;</td>\n"
			+"<td width='10%' bgcolor='#eeeeee' align='center' class='txt10b'>宿泊日</td>\n"
			+"<td colspan='3' align='center' class='txt10b' bgcolor='#eeeeee'>おとな</td>\n"
			+"<td colspan='3' align='center' class='txt10b' bgcolor='#eeeeee'>子供A</td>\n"
			+"<td colspan='3' align='center' class='txt10b' bgcolor='#eeeeee'>子供B</td>\n"
			+"</tr>\n"
			+"<tr bgcolor='#ffffff'>\n"
			+"<td width='9%' bgcolor='#ffffff'></td>\n"
			+"<td width='10%'>　</td>\n"
			+"<td width='9%' class='txt10b'>2名様<br>1室</td>\n"
			+"<td width='9%' class='txt10b'>3名様<br>1室</td>\n"
			+"<td width='9%' class='txt10b'>4名様<br>以上1室</td>\n"
			+"<td width='9%' class='txt10b'>2名様<br>1室</td>\n"
			+"<td width='9%' class='txt10b'>3名様<br>1室</td>\n"
			+"<td width='9%' class='txt10b'>4名様<br>以上1室</td>\n"
			+"<td width='9%' class='txt10b'>2名様<br>1室</td>\n"
			+"<td width='9%' class='txt10b'>3名様<br>1室</td>\n"
			+"<td width='9%' class='txt10b'>4名様<br>以上1室</td>\n"
			+"</tr>\n";
		}else{
			return "<tr bgcolor='#ffffff'>\n"
			+"<td width='15%' bgcolor='#eeeeee'>&nbsp;</td>\n"
			+"<td width='10%' align='center' bgcolor='#eeeeee' class='txt10b'>宿泊日</td>\n"
			+"<td colspan='3' align='center' bgcolor='#eeeeee' class='txt10b'>おとな</td>\n"
			+"</tr>\n"
			+"<tr bgcolor='#ffffff'>\n"
			+"<td width='15%' bgcolor='#ffffff'>	</td>\n"
			+"<td width='10%'>　</td>\n"
			+"<td align='center' width='25%' class='txt10b'>1名様1室</td>\n"
			+"<td align='center' width='25%' class='txt10b'>2名様1室</td>\n"
			+"<td align='center' width='25%' class='txt10b'>3名様以上1室</td>\n"
			+"</tr>\n";
		}
	}
	public String getFootKnt( boolean flg ){
		if( flg ){
			return getFootKnt2917();
		}
		return getFootKntNormal();
	}
	private String getFootNta(){
		StringBuffer sb = new StringBuffer();
		sb.append("<TABLE border='0' cellspacing='0' cellpadding='0' width='100%'>\n");
		sb.append("	<input type='hidden' name='min_nr' value='"+ this.min_nr +"'/>\n");
		sb.append("	<tr bgcolor='#ffffff'>\n");
		sb.append("	<td width='60'>男性：(人)</td>\n");
		sb.append("	<td width='60'>女性：(人)</td>\n");
		sb.append("	<td width='67'>子供A：(人)</td>\n");
		sb.append("	<td width='67'>子供B：(人)</td>\n");
		sb.append("	<td width='60'>泊数：(泊)</td>\n");
		sb.append("	<td width='60'>部屋数：</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr bgcolor='#ffffff'>\n");
		sb.append("	<td width='60'>\n");
		sb.append("  <select name='numberOfMales'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfMales, 0, this.max_nr ) + "\n");
		sb.append("	</select>\n");
		sb.append("</td>\n");
		sb.append("<td width='60'>\n");
		sb.append("<select name='numberOfFemales'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfFemales, 0, this.max_nr ) + "\n");
		sb.append("</select>\n");
		sb.append("</td>\n");
		sb.append("	<td width='67'>\n");
		sb.append("		<select name='numberOfKids'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfKids, 0, this.max_nr ) + "\n");
		sb.append("		</select>\n");
		sb.append("	</td>\n");
		sb.append("	<td width='67'>\n");
		sb.append("	<select name='numberOfYoji1'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfYoji1, 0, this.max_nr ) + "\n");
		sb.append("	</select>\n");
		sb.append(" </td>\n");
		sb.append("	<td width='60'>\n");
		sb.append("		<select name='nights'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.nights, 1, this.maxnight ) + "\n");
		sb.append("		</select>\n");
		sb.append(" </td>\n");
		sb.append("	<td width='60'>\n");
		sb.append("		<select name='numberOfRooms'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfRooms,  1, this.maxrooms ) + "\n");
		sb.append("		</select>\n");
		sb.append("	</td>\n");
		sb.append("</tr>\n");
		sb.append("</TABLE>\n");
		return sb.toString();
	}
	private String getFootKntNormal(){
		StringBuffer sb = new StringBuffer();
		sb.append("<TABLE BORDER='0' CELLSPACING='0' CELLPADDING='0' width='100%'>\n");
		sb.append("	<input type='hidden' name='min_nr' value='"+ this.min_nr +"'/>\n");
		sb.append("	<input type='hidden' name='numberOfKids' value='0'/>\n");
		sb.append("	<input type='hidden' name='numberOfMales' value='0'>\n");
		sb.append("	<tr bgcolor='#ffffff'>\n");
		sb.append("		<td width='60'>大人：(人)</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select NAME='numberOfAdults'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfAdults, this.min_nr, this.max_nr ) + "\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("		<td width='60'>泊数：</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select NAME='nights'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.nights, 1, this.maxnight ) + "\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("		<td width='75'>部屋数：</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select NAME='numberOfRooms'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.nights, 1, this.maxrooms ) + "\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("	</tr>\n");
		sb.append("</TABLE>\n");
		return sb.toString();
	}
	private String getFootKnt2917(){
		StringBuffer sb = new StringBuffer();		
		sb.append("<TABLE border='0' cellspacing='0' cellpadding='0' width='100%'>\n");
		sb.append("	<input type='hidden' name='min_nr' value='"+ this.min_nr +"'/>\n");
		sb.append("	<input type='hidden' name='numberOfMales' value='0'>\n");
		sb.append("	<tr bgcolor='#ffffff'>\n");
		sb.append("		<td width='60'>大人：(人)</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select name='numberOfAdults'>\n");
		sb.append( HtmlpartMaker.getNumOptionString( this.numberOfAdults, 0, this.max_nr )+"\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("		<td width='67'>子供A：(人)</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select name='numberOfKids'>\n");
		sb.append( HtmlpartMaker.getNumOptionString( this.numberOfKids, 0, this.max_nr )	+"\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("		<td width='67'>子供B：(人)</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select name='numberOfYoji1'>\n");
		sb.append( HtmlpartMaker.getNumOptionString( this.numberOfYoji1, 0, this.max_nr ) + "\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("		<td width='60'>泊数：</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select name='nights'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.nights, 1, this.maxnight ) + "\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("		<td width='60'>部屋数：</td>\n");
		sb.append("		<td width='60'>\n");
		sb.append("			<select name='numberOfRooms'>\n");
		sb.append(HtmlpartMaker.getNumOptionString( this.numberOfRooms, 1, this.maxrooms ) + "\n");
		sb.append("			</select>\n");
		sb.append("		</td>\n");
		sb.append("	</tr>\n");
		sb.append("</TABLE>\n");				
		return sb.toString();
	}
}
