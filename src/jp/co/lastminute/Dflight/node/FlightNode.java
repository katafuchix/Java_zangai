package jp.co.lastminute.Dflight.node;

import java.io.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FlightNode implements Serializable{
	public String flight_date = "";
	public int weekday = 0;
	public String flight_time = "";
	public int flight_timecode = 0;
	public String departture = "";
	public String arrival = "";
	public String flightclass = "";
	public String bookingclass = "";
	public String agentCode = "";
	public int allot_number = 0;
	public int max_member = 0;
	public int min_member = 0;
	public int price = 0;
	public int childprice = 0;
	public int infantprice = 0;
	public int normalprice = 0;
	public int deputure_time = 0;
	public int arrival_time = 0;
	public String flight_no = "";
	public String comment = "";
	public String lastsale = "";
	public String endsale = "";
	public String flight_name = "";
	/////////////////////////////////
	public int allot_seq = 0;
	public int sku_id = 0;
	public String jan_cd = "";
}
