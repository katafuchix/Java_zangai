/*
 * DataFormat.java
 *
 * Created on 2002/04/14, 22:35
 */

package jp.co.yobrain.util;

/**
 *
 * @author  skondo
 * @version 
 */
import java.io.*;
import java.util.*;

public class DataFormat{
	private static final String charset = jp.co.yobrain.util.conf.Format_Conf.charset;
	/**
	* 現在の年を出力
	*/
	public static int getNowYear(){
		Calendar calender = Calendar.getInstance();
		return calender.get(Calendar.YEAR);
	}
	private static int getNowYear( Calendar calender ){
		return calender.get(Calendar.YEAR);
	}
	/**
	* 現在の月を出力
	*/
	public static int getNowMonth(){
		Calendar calender = Calendar.getInstance();
		return calender.get(Calendar.MONTH);
	}
	private static int getNowMonth( Calendar calender ){
		return calender.get(Calendar.MONTH);
	}
	/**
	* 現在の日を出力
	*/
	public static int getNowDay(){
		Calendar calender = Calendar.getInstance();
		return calender.get(Calendar.DATE);
	}
	private static int getNowDay( Calendar calender ){
		return calender.get(Calendar.DATE);
	}
	/**
	* 現在の時間を出力
	*/
	private static int getNowHour( Calendar calender ){
		return calender.get(Calendar.HOUR_OF_DAY);
	}
	/**
	* 現在の分を出力
	*/
	private static int getNowMinute( Calendar calender ){
		return calender.get(Calendar.MINUTE);
	}
	/**
	* 現在の秒を出力
	*/
	private static int getNowSeconds( Calendar calender ){
		return calender.get(Calendar.SECOND);
	}
	/**
	* 現在のミリ秒を出力
	*/
	private static int getNowMiliSeconds( Calendar calender ){
		return calender.get(Calendar.MILLISECOND);
	}
	/**
	 * 現在の時刻を取得
         * @param String デミリター　A
         * @param String デミリター　B
         * @param String デミリター　C
         * @param String デミリター　D
	 */
	public static String getNowTimeDate( String A, String B, String C, String D){
		Calendar st = Calendar.getInstance();
		jp.co.yobrain.util.ParseFormat ps = null;
		try{
			return ps.ZeroUP( Integer.toString( getNowYear( st ) ), 2) + A
				+ ps.ZeroUP( Integer.toString( getNowMonth( st ) + 1), 2) + A
				+ ps.ZeroUP( Integer.toString( getNowDay( st ) ), 2) + B
				+ ps.ZeroUP( Integer.toString( getNowHour( st ) ), 2) + C
				+ ps.ZeroUP( Integer.toString( getNowMinute( st ) ), 2) + C
				+ ps.ZeroUP( Integer.toString( getNowSeconds( st ) ), 2) + D
				+ ps.ZeroUP( Integer.toString( getNowMiliSeconds( st ) ), 3);
		}catch(Exception e){}
		return "";
		

	}
	/**
         * 月の最初の曜日を取得
         * @param Strint YYYY
         * @param String MM
         */
	public static int MonthOfFirst(String y, String m){
		Calendar st = Calendar.getInstance();
		st.set(Integer.parseInt(y), Integer.parseInt(m)-1, 1);
		return st.get(Calendar.DAY_OF_WEEK);
	}
	/*＊
         * 現在+ N日の日付を表示
         ＊ @param int n
	*/
	public static String getNowDate(int n){
		String yy =  "";
		String mm = "";
		String dd =  "";
		String reStr = "";
		Calendar calender = Calendar.getInstance();
		int d_yy = jp.co.yobrain.util.DataFormat.getNowYear( calender );
		int d_mm = jp.co.yobrain.util.DataFormat.getNowMonth( calender ) + 1;
		int d_dd = jp.co.yobrain.util.DataFormat.getNowDay( calender );
		if((n + d_dd) > jp.co.yobrain.util.DataFormat.IsMonthday(d_yy, d_mm)){
			d_dd = d_dd + n - jp.co.yobrain.util.DataFormat.IsMonthday(d_yy, d_mm);
			d_mm++;
			if(d_mm > 12){	d_mm = 1;	d_yy++;		}
		}else{	d_dd = d_dd + n;	}
		yy = String.valueOf(d_yy);
		if(String.valueOf(d_mm).length() == 1){	mm = "0" + String.valueOf(d_mm);	}
		else{	mm =  String.valueOf(d_mm);		}
		if(String.valueOf(d_dd).length() == 1){	dd = "0" + String.valueOf(d_dd);	}
		else{	dd = String.valueOf(d_dd);		}
		reStr = yy + "/" + mm + "/" + dd;
		return reStr;
	}
	/**
	* 今日の日付の取得
	*/
	public static String getNowDate(int n, boolean flg){
		String yy =  "";
		String mm = "";
		String dd =  "";
		String reStr = "";
		Calendar calender = Calendar.getInstance();
		int d_yy = jp.co.yobrain.util.DataFormat.getNowYear( calender );
		int d_mm = jp.co.yobrain.util.DataFormat.getNowMonth( calender ) + 1;
		int d_dd = jp.co.yobrain.util.DataFormat.getNowDay( calender );
		if((n + d_dd) > jp.co.yobrain.util.DataFormat.IsMonthday(d_yy, d_mm)){
			d_dd = d_dd + n - jp.co.yobrain.util.DataFormat.IsMonthday(d_yy, d_mm);
			d_mm++;
			if(d_mm > 12){	d_mm = 1;	d_yy++;		}
		}else{	d_dd = d_dd + n;	}
		yy = String.valueOf(d_yy);
		if(String.valueOf(d_mm).length() == 1){	mm = "0" + String.valueOf(d_mm);	}
		else{	mm =  String.valueOf(d_mm);		}
		if(String.valueOf(d_dd).length() == 1){	dd = "0" + String.valueOf(d_dd);	}
		else{	dd = String.valueOf(d_dd);		}
		reStr = yy + mm + dd;
		return reStr;
	}
	/**
         * 月による日付の計算 
         */
	public static int IsMonthday(int y, int m){
		switch(m)
		{
		case 2:
			if( (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0) ) {	return 29;	} else {	return 28;	}
		case 4:
			return 30;
		case 6:
			return 30;
		case 9:
			return 30;
		case 11:
			return 30;
		default:
			return 31;
		}
	}
	/** 
         * 日付の差を取得
         * @param String from 
         * @param String to
         */
	// ex 20000501 20000504 //
	public static int getDiffrenceDate(String from, String to){
		try{
			if(Integer.parseInt(from) > Integer.parseInt(to))	return -1;
			int fy = Integer.parseInt(from.substring(0, 4));
			int fm = Integer.parseInt(from.substring(4, 6));
			int fd = Integer.parseInt(from.substring(6, 8));
			int ty = Integer.parseInt(to.substring(0, 4));
			int tm = Integer.parseInt(to.substring(4, 6));
			int td = Integer.parseInt(to.substring(6, 8));
			return jp.co.yobrain.util.DataFormat.getDiffrenceDate(fy, fm, fd, ty, tm, td);
		}catch(Exception e){	return -1;	}
	}
	/** 
         * 日付の差を取得
         * @param int Y
         * @param int m
         * @param int d
         * @param int Y
         * @param int M
         * @param int D
         */
	public static int getDiffrenceDate(int fy, int fm, int fd, int ty, int tm, int td){
		int reint = 0;
		try{
			if(ty - fy > 1){	return -1;
			}else if(ty - fy == 0){
				if(fm == tm){	reint = td - fd;	}else{
					for(int i = fm + 1; i < tm; i++){	reint += jp.co.yobrain.util.DataFormat.IsMonthday(fy, i);	}
					reint += jp.co.yobrain.util.DataFormat.IsMonthday(fy, fm) - fd + td;
				}
				return reint;
			}else{
				reint = jp.co.yobrain.util.DataFormat.IsMonthday(fy, fm) - fd;
				if(fm < 12){
					for(int i=fm+1; i <= 12; i++){	reint += jp.co.yobrain.util.DataFormat.IsMonthday(fy, i);	}
				}
				if(tm > 1){
					for(int i=1; i<tm-1; i++){	reint += jp.co.yobrain.util.DataFormat.IsMonthday(ty, i);	}
				}
				reint += td;
				return reint;
			}
		}catch(Exception e){	return -1;	}
	}
	/**
         * WWW用の表示補正を行う
         * @param String 日付文字列 
         */
 	public static String getDateTime4WWW(String strd){
		String reStr = "";
		if( strd.length() == 8 ){
		reStr = strd.substring(0, 4) + "/" + strd.substring(4, 6) + "/" + strd.substring(6, 8);
		}else if( strd.length() == 12 ){
			reStr = strd.substring(0, 4) + "/" 
				  + strd.substring(4, 6) + "/"
				  + strd.substring(6, 8) + " "
				  + strd.substring(8, 10) + ":" 
				  + strd.substring(10, 12);
		}
		return reStr;
	}
	/**
         * 日付の足し算 
         * @param String str
         * @param int 加算数
         */
	public static String AddToDate(String str, int n){
            if( n < 0){ 
                int  new_n = n * -1;
                return DelToDate( str, new_n );
            }
		int d_Year = Integer.parseInt(str.substring(0, 4));
		int d_Month = Integer.parseInt(str.substring(4, 6));
		int d_Day = Integer.parseInt(str.substring(6, 8));
		n = n + d_Day;
		while(n > jp.co.yobrain.util.DataFormat.IsMonthday(d_Year, d_Month)){
			n = n - jp.co.yobrain.util.DataFormat.IsMonthday(d_Year, d_Month);
			d_Month++;
			if(d_Month > 12){	d_Month = 1;	d_Year++;	}
		}
		return Integer.toString( d_Year * 10000 + d_Month * 100 + n );
	}
    public static String DelToDate(String str, int n){
          int d_Year = Integer.parseInt(str.substring(0, 4));
          int d_Month = Integer.parseInt(str.substring(4, 6));
          int d_Day = Integer.parseInt(str.substring(6, 8));
          if(d_Day - n > 0){
              d_Day = d_Day - n;
          }else{
              d_Month--;
              if(d_Month == 0){	d_Month = 12;	d_Year--;	}
              d_Day = DataFormat.IsMonthday(d_Year, d_Month) - ( n - d_Day );
          }
          return Integer.toString( d_Year * 10000 + d_Month * 100 + d_Day );
    }
	/**
         * 時間表示を行う 
         * @param String 変換前文字列
         */
	public static String FromStrToDate(String str){
		String reStr = "";
		try{
			if(str.length() <= 8){
				reStr = str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8);
				return reStr;
			}else{	return "";		}
		}catch(Exception e){	return "";	}
	}
	/**
         * 時間表示を行う 
         * @param String 変換前文字列
         */
	public static String FromStrToTime(String str){
		String reStr = "";
		try{
			if(str.length() <= 12){
				reStr = str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8) + " ";
				if(str.length() > 8){	reStr += str.substring(8, 10) + ":" +  str.substring(10, 12);	}
				return reStr;
			}else{	return "";	}
		}catch(Exception e){	return "";	}
	}
	/**
         * 曜日を取得する
         * @param String 対象日付文字列
         */
	public static int getWeekDay(String str){
		int YY, MM, DD;
		//Date start_date;
		Calendar start_date = Calendar.getInstance();
		try{
			YY = Integer.parseInt( str.substring(0, 4) );
			MM = Integer.parseInt( str.substring(4, 6) ) - 1;
			DD = Integer.parseInt( str.substring(6, 8) );
			start_date.set(YY, MM, DD);
			return start_date.get( start_date.DAY_OF_WEEK );
		}catch(Exception e){}
		return -1;
	}
	/**
	* 時間を分割して0つめする
	*/
	public static String TimeTokenizer(String str, String demi){
		String reStr = "";
		String neStr = "";
		String zStr = "";
		StringTokenizer st = new StringTokenizer(str , demi);
		int i = 0;
		while(st.hasMoreTokens()){
			neStr = st.nextToken();
			if(i == 0){
				for(int j=0; j < 4 - neStr.length(); j++){		zStr += "0";		}
				reStr += neStr + zStr;
			}else if(i == 1){
				for(int j=0; j < 2 - neStr.length(); j++){		zStr += "0";		}
				reStr += zStr + neStr;
			}else if(i == 2){
				for(int j=0; j < 2 - neStr.length(); j++){		zStr += "0";		}
				reStr += zStr + neStr;
			}
			i++;
			zStr = "";
			neStr = "";
		}
		return reStr;
	}
	/**
	 * ヨーロッパスタイルの日付の取得
	 */
	public static String getEurope(String newDate, int addday){
		String newdate = "";
		try{
			newdate = AddToDate(newDate, addday);
			 return newdate.substring(6) + " "
			 	+ getMonthString( newdate.substring(4, 6) )  + " "
			 	+ newdate.substring(0, 4);
		}catch(Exception e){}
		return "";		
	}
	/**
	 * 日付の取得
	 */
	private static String getMonthString(String newmonth){
		String[] MONTH = {"Jan", "Feb", "Mar", "Apl", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		try{
			if(newmonth.substring(0,1).equals("0")){
				newmonth.substring(1);
			}
			return MONTH[ Integer.parseInt( newmonth ) - 1 ];
		}catch(Exception e){}
		return "";
	}


}