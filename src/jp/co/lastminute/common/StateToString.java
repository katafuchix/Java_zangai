package jp.co.lastminute.common;

import java.io.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class StateToString {
	private static final String[] States = {"",
											   "–kŠC“¹","ÂXŒ§","ŠâèŒ§","‹{éŒ§","H“cŒ§",
											"RŒ`Œ§","•Ÿ“‡Œ§","ˆïéŒ§","“È–ØŒ§","ŒQ”nŒ§",
											"é‹ÊŒ§","ç—tŒ§","“Œ‹“s","_“ŞìŒ§","R—œŒ§",
											"’·–ìŒ§","VŠƒŒ§","•xRŒ§","ÎìŒ§","•ŸˆäŒ§",
											"Šò•ŒŒ§","Ã‰ªŒ§","ˆ¤’mŒ§","OdŒ§"," ‰êŒ§",
											"‹“s•{","‘åã•{","•ºŒÉŒ§","“Ş—ÇŒ§","˜a‰ÌRŒ§",
											"’¹æŒ§","“‡ªŒ§","‰ªRŒ§","L“‡Œ§","RŒûŒ§",
											"“¿“‡Œ§","ìŒ§","ˆ¤•QŒ§","‚’mŒ§","•Ÿ‰ªŒ§",
											"²‰êŒ§","’·èŒ§","ŒF–{Œ§","‘å•ªŒ§","‹{èŒ§",
											"­™“‡Œ§","‰«“êŒ§","","","ŠCŠO"};
	private static final String[] Sex = {"", "’j«", "—«"};
	public static String getSexCtr( int num ){
		try{
			return Sex[num];
		}catch(Exception ex){}
		return "";
	}
	public static String getSexCtr( String num ){
		try{
			return Sex[ Integer.parseInt( num ) ];
		}catch(Exception ex){}
		return "";
	}
	public static String getStateCtr( int num ){
		try{
			return States[num];
		}catch(Exception ex){}
		return "";
	}
	public static String getStateCtr( String num ){
		try{
			return States[ Integer.parseInt( num ) ];
		}catch(Exception ex){}
		return "";
	}
}
