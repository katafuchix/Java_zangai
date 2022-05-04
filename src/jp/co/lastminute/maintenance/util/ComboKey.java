package jp.co.lastminute.maintenance.util;

import java.io.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ComboKey implements Serializable{
	public String code = "";
	public String value = "";
	
	public ComboKey( String code, String value){
		this.code = code;
		this.value =value;
	}
}
