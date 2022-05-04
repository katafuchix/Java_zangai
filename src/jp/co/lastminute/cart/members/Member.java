package jp.co.lastminute.cart.members;

import java.io.*;
import java.lang.*;
import jp.co.lastminute.common.StateToString;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Member implements Serializable{
	public int dDate;
	public int parentdDate;
	public String firstname_alp;
	public String lastneme_alp;
	public String firstname_kanji;
	public String lastname_kanji;
	public String firstname_hkana;
	public String lastname_hkana;
	public String firstname_kkana;
	public String lastname_kkana;
	public String sex;
	public String years;
	public String months;
	public String days;
	public String passport;
	public String telno;
	public int firstname_alp_flg = 0;
	public int lastneme_alp_flg = 0;
	public int firstname_kanji_flg = 0;
	public int lastname_kanji_flg = 0;
	public int firstname_hkana_flg = 0;
	public int lastname_hkana_flg = 0;
	public int firstname_kkana_flg = 0;
	public int lastname_kkana_flg = 0;
	public int sex_flg = 0;
	public int years_flg = 0;
	public int months_flg = 0;
	public int days_flg = 0;
	public int passport_flg = 0;
	public int telno_flg = 0;
	/////
	public String targetdate = "";
	/**
	 * コンストラクター
	 */
	public Member(){
		constract( 0 );
	}
	public Member( int dd){
		constract( dd );
	}
	public void constract( int dd){
		dDate = dd;
		parentdDate = 0;
		firstname_alp = "";
		lastneme_alp = "";
		firstname_kanji = "";
		lastname_kanji = "";
		firstname_hkana = "";
		lastname_hkana = "";
		firstname_kkana = "";
		lastname_kkana = "";
		sex = "";
		years = "";
		months = "";
		days = "";
		passport = "";
		targetdate = "";
		telno = "";
	}
	/**
	 * メンバーが有効か
	 */
	public boolean isMember(){
		if(( firstname_alp.length() == 0)&&( lastneme_alp.length() == 0)&&
		( firstname_kanji.length() == 0)&&( lastname_kanji.length() == 0)&&
		( firstname_hkana.length() == 0)&&( lastname_hkana.length() == 0)&&
		( firstname_kkana.length() == 0)&&( lastname_kkana.length() == 0)&&
		( sex.length() == 0)&&( years.length() == 0)&&
		( months.length() == 0)&&( days.length() == 0)&&
		( passport.length() == 0)&&( telno.length() == 0)){
			return false;
		}
		return true;
	}
	/**
	 * Returns the days.
	 * @return String
	 */
	public String getDays() {
		return days;
	}

	/**
	 * Returns the dDate.
	 * @return double
	 */
	public int getDDate() {
		return dDate;
	}

	/**
	 * Returns the firstname_alp.
	 * @return String
	 */
	public String getFirstname_alp() {
		return firstname_alp;
	}

	/**
	 * Returns the firstname_hkana.
	 * @return String
	 */
	public String getFirstname_hkana() {
		return firstname_hkana;
	}

	/**
	 * Returns the firstname_kanji.
	 * @return String
	 */
	public String getFirstname_kanji() {
		return firstname_kanji;
	}

	/**
	 * Returns the firstname_kkana.
	 * @return String
	 */
	public String getFirstname_kkana() {
		return firstname_kkana;
	}

	/**
	 * Returns the lastname_hkana.
	 * @return String
	 */
	public String getLastname_hkana() {
		return lastname_hkana;
	}

	/**
	 * Returns the lastname_kanji.
	 * @return String
	 */
	public String getLastname_kanji() {
		return lastname_kanji;
	}

	/**
	 * Returns the lastname_kkana.
	 * @return String
	 */
	public String getLastname_kkana() {
		return lastname_kkana;
	}

	/**
	 * Returns the lastnema_alp.
	 * @return String
	 */
	public String getLastnema_alp() {
		return lastneme_alp;
	}

	/**
	 * Returns the months.
	 * @return String
	 */
	public String getMonths() {
		return months;
	}

	/**
	 * Returns the passport.
	 * @return String
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * Returns the sex.
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Returns the years.
	 * @return String
	 */
	public String getYears() {
		return years;
	}

	/**
	 * Sets the days.
	 * @param days The days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * Sets the dDate.
	 * @param dDate The dDate to set
	 */
	public void setDDate(int dDate) {
		this.dDate = dDate;
	}

	/**
	 * Sets the firstname_alp.
	 * @param firstname_alp The firstname_alp to set
	 */
	public void setFirstname_alp(String firstname_alp) {
		this.firstname_alp = firstname_alp;
	}

	/**
	 * Sets the firstname_hkana.
	 * @param firstname_hkana The firstname_hkana to set
	 */
	public void setFirstname_hkana(String firstname_hkana) {
		this.firstname_hkana = firstname_hkana;
	}

	/**
	 * Sets the firstname_kanji.
	 * @param firstname_kanji The firstname_kanji to set
	 */
	public void setFirstname_kanji(String firstname_kanji) {
		this.firstname_kanji = firstname_kanji;
	}

	/**
	 * Sets the firstname_kkana.
	 * @param firstname_kkana The firstname_kkana to set
	 */
	public void setFirstname_kkana(String firstname_kkana) {
		this.firstname_kkana = firstname_kkana;
	}

	/**
	 * Sets the lastname_hkana.
	 * @param lastname_hkana The lastname_hkana to set
	 */
	public void setLastname_hkana(String lastname_hkana) {
		this.lastname_hkana = lastname_hkana;
	}

	/**
	 * Sets the lastname_kanji.
	 * @param lastname_kanji The lastname_kanji to set
	 */
	public void setLastname_kanji(String lastname_kanji) {
		this.lastname_kanji = lastname_kanji;
	}

	/**
	 * Sets the lastname_kkana.
	 * @param lastname_kkana The lastname_kkana to set
	 */
	public void setLastname_kkana(String lastname_kkana) {
		this.lastname_kkana = lastname_kkana;
	}

	/**
	 * Sets the lastnema_alp.
	 * @param lastnema_alp The lastnema_alp to set
	 */
	public void setLastnema_alp(String lastnema_alp) {
		this.lastneme_alp = lastnema_alp;
	}

	/**
	 * Sets the months.
	 * @param months The months to set
	 */
	public void setMonths(String months) {
		this.months = months;
	}

	/**
	 * Sets the passport.
	 * @param passport The passport to set
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/**
	 * Sets the sex.
	 * @param sex The sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Sets the years.
	 * @param years The years to set
	 */
	public void setYears(String years) {
		this.years = years;
	}
	/**
	 * XmLストリングの出力
	 */
	public String getXmlString(){
		try{
			StringBuffer stb = new StringBuffer();
			stb.append("<ALPHABET>\n");
			stb.append("<FIRSTNAME><![CDATA["+ this.firstname_alp+ "]]></FIRSTNAME>\n");
			stb.append("<LASTSTNAME><![CDATA["+ this.lastneme_alp + "]]></LASTSTNAME>\n");
			stb.append("</ALPHABET>\n");
			stb.append("<KANJI>\n");
			stb.append("<FIRSTNAME><![CDATA["+ this.firstname_kanji+ "]]></FIRSTNAME>\n");
			stb.append("<LASTSTNAME><![CDATA["+ this.lastname_kanji+ "]]></LASTSTNAME>\n");
			stb.append("</KANJI>\n");
			stb.append("<HKANA>\n");
			stb.append("<FIRSTNAME><![CDATA["+ this.firstname_hkana+ "]]></FIRSTNAME>\n");
			stb.append("<LASTSTNAME><![CDATA["+ this.lastname_hkana+ "]]></LASTSTNAME>\n");
			stb.append("</HKANA>\n");
			stb.append("<KKANA>\n");
			stb.append("<FIRSTNAME><![CDATA["+ this.firstname_kkana+ "]]></FIRSTNAME>\n");
			stb.append("<LASTSTNAME><![CDATA["+ this.lastname_kkana+ "]]></LASTSTNAME>\n");
			stb.append("</KKANA>\n");
			stb.append("<SEX>" + this.sex + "</SEX>\n");
			stb.append("<SEX_STR><![CDATA[" + StateToString.getSexCtr( this.sex ) + "]]></SEX_STR>\n");
			stb.append("<BIRTHDAY>\n");
			stb.append("<YEAR>" + this.years + "</YEAR>\n");
			stb.append("<MONTH>" + this.months + "</MONTH>\n");
			stb.append("<DAY>" + this.days + "</DAY>\n");
			stb.append("</BIRTHDAY>\n");
			stb.append("<PASSPORT><![CDATA[" + this.passport + "]]></PASSPORT>\n");
			stb.append("<TELNO><![CDATA[" + this.telno + "]]></TELNO>\n");
			stb.append("<TARGETDATE>" + this.targetdate + "</TARGETDATE>\n");
			stb.append("<YEARSOLDMONTH><![CDATA[" + getYearOldMonthString() + "]]></YEARSOLDMONTH>\n");
			stb.append("<YEARSOLD><![CDATA[" + getYearOldString() + "]]></YEARSOLD>\n");
			stb.append("<DDATE>" + this.dDate + "</DDATE>\n");
			return stb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return "";
	}
	/**
	 * yearsoldの取得
	 */
	public String getYearOldMonthString(){
		if(( this.targetdate.length() > 0)&&( this.years.length() > 0 )){
			int[] reInt = YearsCalculater.getYearsOld( this.targetdate, this.years + this.months + this.days);
			return reInt[1] + "才" + reInt[2] + "ヶ月";
		}
		return "";
	}
	/**
	 * yearsoldの取得
	 */
	public String getYearOldString(){
		if(( this.targetdate.length() > 0)&&( this.years.length() > 0 )){
			int[] reInt = YearsCalculater.getYearsOld( this.targetdate, this.years + this.months + this.days);
			return reInt[1] + "才" ;
		}
		return "";
	}
	/**
	 * Returns the days_flg.
	 * @return int
	 */
	public int getDays_flg() {
		return days_flg;
	}

	/**
	 * Returns the firstname_alp_flg.
	 * @return int
	 */
	public int getFirstname_alp_flg() {
		return firstname_alp_flg;
	}

	/**
	 * Returns the firstname_hkana_flg.
	 * @return int
	 */
	public int getFirstname_hkana_flg() {
		return firstname_hkana_flg;
	}

	/**
	 * Returns the firstname_kanji_flg.
	 * @return int
	 */
	public int getFirstname_kanji_flg() {
		return firstname_kanji_flg;
	}

	/**
	 * Returns the firstname_kkana_flg.
	 * @return int
	 */
	public int getFirstname_kkana_flg() {
		return firstname_kkana_flg;
	}

	/**
	 * Returns the lastname_hkana_flg.
	 * @return int
	 */
	public int getLastname_hkana_flg() {
		return lastname_hkana_flg;
	}

	/**
	 * Returns the lastname_kanji_flg.
	 * @return int
	 */
	public int getLastname_kanji_flg() {
		return lastname_kanji_flg;
	}

	/**
	 * Returns the lastname_kkana_flg.
	 * @return int
	 */
	public int getLastname_kkana_flg() {
		return lastname_kkana_flg;
	}

	/**
	 * Returns the lastnema_alp_flg.
	 * @return int
	 */
	public int getLastnema_alp_flg() {
		return lastneme_alp_flg;
	}

	/**
	 * Returns the months_flg.
	 * @return int
	 */
	public int getMonths_flg() {
		return months_flg;
	}

	/**
	 * Returns the passport_flg.
	 * @return int
	 */
	public int getPassport_flg() {
		return passport_flg;
	}

	/**
	 * Returns the sex_flg.
	 * @return int
	 */
	public int getSex_flg() {
		return sex_flg;
	}

	/**
	 * Returns the years_flg.
	 * @return int
	 */
	public int getYears_flg() {
		return years_flg;
	}

	/**
	 * Sets the days_flg.
	 * @param days_flg The days_flg to set
	 */
	public void setDays_flg(int days_flg) {
		this.days_flg = days_flg;
	}

	/**
	 * Sets the firstname_alp_flg.
	 * @param firstname_alp_flg The firstname_alp_flg to set
	 */
	public void setFirstname_alp_flg(int firstname_alp_flg) {
		this.firstname_alp_flg = firstname_alp_flg;
	}

	/**
	 * Sets the firstname_hkana_flg.
	 * @param firstname_hkana_flg The firstname_hkana_flg to set
	 */
	public void setFirstname_hkana_flg(int firstname_hkana_flg) {
		this.firstname_hkana_flg = firstname_hkana_flg;
	}

	/**
	 * Sets the firstname_kanji_flg.
	 * @param firstname_kanji_flg The firstname_kanji_flg to set
	 */
	public void setFirstname_kanji_flg(int firstname_kanji_flg) {
		this.firstname_kanji_flg = firstname_kanji_flg;
	}

	/**
	 * Sets the firstname_kkana_flg.
	 * @param firstname_kkana_flg The firstname_kkana_flg to set
	 */
	public void setFirstname_kkana_flg(int firstname_kkana_flg) {
		this.firstname_kkana_flg = firstname_kkana_flg;
	}

	/**
	 * Sets the lastname_hkana_flg.
	 * @param lastname_hkana_flg The lastname_hkana_flg to set
	 */
	public void setLastname_hkana_flg(int lastname_hkana_flg) {
		this.lastname_hkana_flg = lastname_hkana_flg;
	}

	/**
	 * Sets the lastname_kanji_flg.
	 * @param lastname_kanji_flg The lastname_kanji_flg to set
	 */
	public void setLastname_kanji_flg(int lastname_kanji_flg) {
		this.lastname_kanji_flg = lastname_kanji_flg;
	}

	/**
	 * Sets the lastname_kkana_flg.
	 * @param lastname_kkana_flg The lastname_kkana_flg to set
	 */
	public void setLastname_kkana_flg(int lastname_kkana_flg) {
		this.lastname_kkana_flg = lastname_kkana_flg;
	}

	/**
	 * Sets the lastnema_alp_flg.
	 * @param lastnema_alp_flg The lastnema_alp_flg to set
	 */
	public void setLastnema_alp_flg(int lastnema_alp_flg) {
		this.lastneme_alp_flg = lastnema_alp_flg;
	}

	/**
	 * Sets the months_flg.
	 * @param months_flg The months_flg to set
	 */
	public void setMonths_flg(int months_flg) {
		this.months_flg = months_flg;
	}

	/**
	 * Sets the passport_flg.
	 * @param passport_flg The passport_flg to set
	 */
	public void setPassport_flg(int passport_flg) {
		this.passport_flg = passport_flg;
	}

	/**
	 * Sets the sex_flg.
	 * @param sex_flg The sex_flg to set
	 */
	public void setSex_flg(int sex_flg) {
		this.sex_flg = sex_flg;
	}

	/**
	 * Sets the years_flg.
	 * @param years_flg The years_flg to set
	 */
	public void setYears_flg(int years_flg) {
		this.years_flg = years_flg;
	}
	////////Tools//////////

	public static final String[] MONTH_ = {"01", "02", "03","04","05","06","07","08","09","10","11", "12"};
	public static final String[] DAY_ = {"01","02","03","04","05","06","07","08","09","10",
									"11","12","13","14","15","16","17","18","19","20",
									"21","22","23","24","25","26","27","28","29","30",
									"31"};
	public static final String[] SEX_ = {"男性","女性"};
	public static final String[] SEX_CODD_ = {"1","2"};
	public static final String[] CABIN_ = {"MR","MRS", "MISS", "CHILD", "INF"};
	public static final String[] CABIN_CODE_ = {"1","2", "3", "4", "5"};
	public String getComboPrevStr( String comboname, String[] list, String[] option, String target ){
		String reStr = "";
		try{
			for(int i=0; i<list.length; i++){
				if( target.equals( list[i] )){
					reStr = "<input type='hidden' name='" + comboname + "' value=\"" + list[i] +"\">" + option[i] + "\n";
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	public String getComboPrevStr( String comboname, String[] list, String target ){
		return getComboPrevStr( comboname, list, list, target );
	}
	public String getComboHtmlStr( String comboname, String[] list, String[] option, String target ){
		String reStr = "";
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<select name='" + comboname + "'>\n");
			sb.append("<option value=''></option>\n");
			for(int i=0; i<list.length; i++){
				sb.append("<option value='" + list[i] + "'");
				if( target.equals( list[i] )){
					sb.append(" selected");
				}
				sb.append(">" + option[i] + "</option>\n");
			}
			sb.append("</select>\n");
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	public String getComboHtmlStr( String comboname, String[] list, String target ){
		return getComboHtmlStr( comboname, list, list, target );
	}
	/**
	 * 
	 */
	public String getCheckBoxStr( String comboname, String[] list, String[] option, String target ){
		String reStr = "";
		try{
			StringBuffer sb = new StringBuffer();
			
			for(int i=0; i<list.length; i++){
				sb.append("<input type='checkbox' name='" + comboname + "' value='" + list[i] + "'");
				if( target.equals( list[i] )){
					sb.append(" checked");
				}
				sb.append(">" + option[i] + "\n");
			}
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * Returns the targetdate.
	 * @return String
	 */
	public String getTargetdate() {
		return targetdate;
	}

	/**
	 * Sets the targetdate.
	 * @param targetdate The targetdate to set
	 */
	public void setTargetdate(String targetdate) {
		this.targetdate = targetdate;
	}

	/**
	 * Returns the lastneme_alp.
	 * @return String
	 */
	public String getLastneme_alp() {
		return lastneme_alp;
	}

	/**
	 * Returns the lastneme_alp_flg.
	 * @return int
	 */
	public int getLastneme_alp_flg() {
		return lastneme_alp_flg;
	}

	/**
	 * Returns the parentdDate.
	 * @return int
	 */
	public int getParentdDate() {
		return parentdDate;
	}

	/**
	 * Returns the telno.
	 * @return String
	 */
	public String getTelno() {
		return telno;
	}

	/**
	 * Returns the telno_flg.
	 * @return int
	 */
	public int getTelno_flg() {
		return telno_flg;
	}

	/**
	 * Sets the lastneme_alp.
	 * @param lastneme_alp The lastneme_alp to set
	 */
	public void setLastneme_alp(String lastneme_alp) {
		this.lastneme_alp = lastneme_alp;
	}

	/**
	 * Sets the lastneme_alp_flg.
	 * @param lastneme_alp_flg The lastneme_alp_flg to set
	 */
	public void setLastneme_alp_flg(int lastneme_alp_flg) {
		this.lastneme_alp_flg = lastneme_alp_flg;
	}

	/**
	 * Sets the parentdDate.
	 * @param parentdDate The parentdDate to set
	 */
	public void setParentdDate(int parentdDate) {
		this.parentdDate = parentdDate;
	}

	/**
	 * Sets the telno.
	 * @param telno The telno to set
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}

	/**
	 * Sets the telno_flg.
	 * @param telno_flg The telno_flg to set
	 */
	public void setTelno_flg(int telno_flg) {
		this.telno_flg = telno_flg;
	}

}
