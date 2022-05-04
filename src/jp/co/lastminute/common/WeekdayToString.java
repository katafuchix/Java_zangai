package jp.co.lastminute.common;


import java.lang.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class WeekdayToString {
	public static final int _Engllish = 1;
	public static final int _Japan = 0;
	
	public static final String[] _Weekdays_JP = {"", "“ú", "Œ", "‰Î", "…", "–Ø", "‹à", "“y","“ú" };
	public static final String[] _Weekdays_Eng = {"", "Sun", "Mon", "Tue", "Web", "Thu", "Fri", "Sat" ,"Sun" };
	
	public static String getWeekdayStr( int weekday, int type ){
		try{
			if( type == 1){
				return _Weekdays_Eng[ weekday ];
			}
			return _Weekdays_JP[ weekday ];
		}catch(Exception ex){	ex.printStackTrace();	}	
		return "";
	}
}
