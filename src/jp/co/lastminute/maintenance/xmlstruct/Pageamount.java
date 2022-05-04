package jp.co.lastminute.maintenance.xmlstruct;

import java.io.*;
import java.util.*;
import jp.co.lastminute.maintenance.*;
import jp.co.yobrain.util.text.StringUtil;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Pageamount implements Serializable {
	public String today = "";
	public String todaynumber = "";
	public String title = "";
	public int types = 0;
	
	private int tcount = 0;
	private int lcount = 0;
	
	private Vector topixs = null;
	private Vector lists = null;
	
	public Pageamount( int tcount, int lcount ){
		this.tcount = tcount;
		this.lcount = lcount;
		topixs = new Vector();
		lists = new Vector();
	}
	/**
	 * トピックスの追加
	 */
	public void setTopix( Production production ){
		System.err.println( "Pageamount setTopix" );
		topixs.add( production );
	}
	/**
	 * リストの追加
	 */
	public void setList( Production production ){
		System.err.println( "Pageamount setList" );
		lists.add( production );
	}
	/**
	 * XMLジェネレート
	 */
	synchronized public String generateXml(){
		String reStr = Property.xmlheader + "<pageamount>\n"
					+ "<today>" + this.today + "</today>\n"
					+ "<types>" + this.types + "</types>\n"
					+ "<todaynumber>" + this.todaynumber + "</todaynumber>\n"
					+ "<title><![CDATA[" + replaceInString( this.title ) + "]]></title>\n"
					+ "<products>\n<topix>\n";
		for(int i=0; i<this.tcount; i++){
			try{
				reStr += ((Production) topixs.get( i )).generateXml() + "\n";
			}catch(Exception ex){	ex.printStackTrace();	}
		}
		reStr += "</topix>\n";
		reStr += "<list>\n";
		for(int i=0; i<this.lcount; i++){
			try{
				reStr += ((Production) lists.get( i )).generateXml() + "\n";
			}catch(Exception ex){	ex.printStackTrace();	}
		}
		reStr += "</list>\n</products>\n</pageamount>";
		return reStr;
	}
	private static String replaceInString( String str ){
		str = StringUtil.replaceInString( str, "&","&amp;" );
		str = StringUtil.replaceInString( str,"<","&lt;" );
		str = StringUtil.replaceInString( str,">","&gt;" );
		str = StringUtil.replaceInString( str,"'","&rsquo" );
		str = StringUtil.replaceInString( str,"\"","&rdquo" );
		return str;
	}
	/**
	 * Returns the lcount.
	 * @return int
	 */
	public int getLcount() {
		return lcount;
	}

	/**
	 * Returns the tcount.
	 * @return int
	 */
	public int getTcount() {
		return tcount;
	}

	/**
	 * Returns the title.
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the today.
	 * @return String
	 */
	public String getToday() {
		return today;
	}

	/**
	 * Returns the todaynumber.
	 * @return String
	 */
	public String getTodaynumber() {
		return todaynumber;
	}

	/**
	 * Sets the lcount.
	 * @param lcount The lcount to set
	 */
	public void setLcount(int lcount) {
		this.lcount = lcount;
	}

	/**
	 * Sets the tcount.
	 * @param tcount The tcount to set
	 */
	public void setTcount(int tcount) {
		this.tcount = tcount;
	}

	/**
	 * Sets the title.
	 * @param title The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the today.
	 * @param today The today to set
	 */
	public void setToday(String today) {
		this.today = today;
	}

	/**
	 * Sets the todaynumber.
	 * @param todaynumber The todaynumber to set
	 */
	public void setTodaynumber(String todaynumber) {
		this.todaynumber = todaynumber;
	}

}
