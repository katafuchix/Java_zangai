package jp.co.lastminute.Hotel.areas;

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
public class StateParam implements Serializable{
	public StateParam(){
	}
	public String state_cd = "";
	public String state_name = "";
	public Vector citys = new Vector();
	
	public void addCity( CityParam city ){
		citys.add( city );
	}
}
