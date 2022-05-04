package jp.co.lastminute.cart.members;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class YearsCalculater {
	/**
	 * ”äŠr‘ÎÆ@trget/ ”äŠrŒ³@yyyymmmdd
	 * target:20030120 yyyymm:dd19980513
	 */
	public static int[] getYearsOld( String trget, String yyyymmdd ){
		int[] reint = {-1, 0, 0};
		try{
			int trgetyear 	= Integer.parseInt( trget.substring(0, 4));
			int targetmonth = Integer.parseInt( trget.substring(4, 6));
			int targetday = Integer.parseInt( trget.substring(6));
			int yyyy = Integer.parseInt( yyyymmdd.substring(0, 4));
			int mm = Integer.parseInt( yyyymmdd.substring(4, 6));
			int dd = Integer.parseInt( yyyymmdd.substring(6));
			
			return getYearsOld( trgetyear, targetmonth, targetday, yyyy, mm, dd );
		}catch(Exception ex){	ex.printStackTrace();	}
		return reint;
	}
	/**
	 * 
	 */
	public static int[] getYearsOld( int trgetyear, int targetmonth, int targetday, int yyyy, int mm, int dd ){
		int[] reint = {-1, 0, 0};
		try{
			if(targetday - dd < 0)	targetmonth--;
			int sa = (trgetyear - yyyy )*12 + ( targetmonth - mm );
			int month = sa%12;
			int yeay = (sa - month) / 12;
			reint[0] = sa; reint[1] = yeay; reint[2] = month;
		}catch(Exception ex){	ex.printStackTrace();	}
		return reint;
	}
}
