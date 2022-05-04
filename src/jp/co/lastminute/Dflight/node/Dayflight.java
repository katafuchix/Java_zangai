package jp.co.lastminute.Dflight.node;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.DataFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 * “ú–ˆ‚ÌİŒÉó‘Ô‚Ì•Û
 */
public class Dayflight implements Serializable{
	private int NodeLength = NodeProperty.NodeLength;
	private int[] Target = NodeProperty.Target;
	public String flightdate = "";
	public int weekday = 0;
	DataFormat df = null;
	public FlightNode[] flights = new FlightNode[ NodeLength ];
	public Dayflight(){
		super();		
	}
	public int getWeekDay(){
		return df.getWeekDay( flightdate );
	}
	/**
	 * Node‚Ì•Û
	 */
	public void setFlightNode(FlightNode flight, int index ){
		for(int i=0; i<NodeLength; i++){
			if( index%100 == Target[i] ){
				flights[ i ] = flight;
				break;
			}
			int backtarget = NodeLength-i-1;
			if( index%100 == Target[ backtarget ]){
				flights[ backtarget ] = flight;
				break;
			}
		}				
	}
	/**
	 * Nodes‚Ìæ“¾
	 */
	public FlightNode[] getFlightNodes(){
		return flights;
	}
	/**
	 * Node‚Ìæ“¾
	 */
	public FlightNode getFlightNode(int index ){
		return flights[ index ];
	}
}
