package jp.co.lastminute.maintenance.Tool;

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
public class Product_Altp implements Serializable{
	public String agt_cd = "";
	public String supnbr = "";
	public String product_id = "";
	public String campaign = "";
	public String agt_roomtype = "";
	public String agt_roomtype_name = "";
	public String meal_code = "";
	public String room_capa = "";
	public int max_nr = 0;
	public int min_nr = 0;
	public int last_day = 0;
	public int start_day = 0;
	
	public int product_seq_no = 0;
	public String altdat_from = "";
	public String  altdat_to = "";
	public int price = 0;
	
	public int price_type = 0;
	public int lmjcampaign = 0; 
	
	public String price_catch = "";
	
	public boolean sunncess = false;
	public boolean websuccess = false;
}
