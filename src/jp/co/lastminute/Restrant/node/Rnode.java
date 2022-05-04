package jp.co.lastminute.Restrant.node;

import java.io.*;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Rnode implements Serializable{
	private int allotdate = 0;
	private RStocks[] rstocks = null;
	
	public Rnode( int rstokcsize ){
		this.rstocks = new RStocks[ rstokcsize ];
	}
	/**
	 * ノードにストックをセットする
	 */
	public void setRnode( int postion, RStocks rstocks ){
		this.allotdate = rstocks.allotdate;
		this.rstocks[ postion ] = rstocks;
	}
	/**
	 * このノードに追加するか決める。
	 */
	public boolean isThisdate( int target ){
		if( allotdate == target ){
			return true;	
		}
		return false;
	}
	/**
	 * 
	 */
	public String getXmlString(){
		String reStr = "";
		try{
			for(int i=0; i<rstocks.length; i++){
				if( rstocks[i] != null){
					reStr += "<RNODE>\n" + rstocks[i].getXmlString() + "</RNODE>\n";
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * Returns the allotdate.
	 * @return int
	 */
	public int getAllotdate() {
		return allotdate;
	}

	/**
	 * Returns the rstocks.
	 * @return RStocks[]
	 */
	public RStocks[] getRstocks() {
		return rstocks;
	}

	/**
	 * Sets the allotdate.
	 * @param allotdate The allotdate to set
	 */
	public void setAllotdate(int allotdate) {
		this.allotdate = allotdate;
	}

	/**
	 * Sets the rstocks.
	 * @param rstocks The rstocks to set
	 */
	public void setRstocks(RStocks[] rstocks) {
		this.rstocks = rstocks;
	}

}
