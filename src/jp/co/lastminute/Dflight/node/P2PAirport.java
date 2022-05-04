package jp.co.lastminute.Dflight.node;

import java.io.*;
import java.util.*;

import jp.co.lastminute.Dflight.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class P2PAirport extends Airports implements Serializable{
	public String depname = "";
	public String arvname = "";
	public HashMap days = new HashMap();
	
	public P2PAirport(){
	}
	/**
	 * “ú•t‚Ì•Û
	 */
	public void setDays( String day ){
		if( !days.containsKey( day ) ){
			try{
			days.put( day, new Integer( Integer.parseInt( day ) ) );
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
