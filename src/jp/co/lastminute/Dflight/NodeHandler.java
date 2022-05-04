package jp.co.lastminute.Dflight;

import java.io.*;
import java.util.*;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.ParseFormat;
import jp.co.yobrain.util.DataFormat;
import jp.co.lastminute.Dflight.Property;
import jp.co.lastminute.Dflight.node.*;
import java.lang.*;
import jp.co.lastminute.common.WeekdayToString;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class NodeHandler implements Serializable {
	ActFlights flightlits = null;
	Vector Airport_list = null;
	HashMap Airports = null;
	Vector node = null;
	
	public NodeHandler(){
		flightlits = new ActFlights();
		Airport_list = new Vector();
		Airports = new HashMap();
		node = new Vector();
	}
	/**
	 * ディスティネーションの検索
	 * エアポートがあるか否かを判断している。
	 */
	protected boolean find( P2PAirport airports ){
		return flightlits.containsP2PAirport( airports );
	}
	/**
	 * エアポート毎のフライトリストの作成、投入
	 */
	protected void insert( P2PAirport airports ){
		FlightNodesList flightNodesList = new FlightNodesList();
		flightNodesList.distination = airports;
		flightlits.setDistnationsflight( airports, flightNodesList );
		Airports.put( airports, airports );
		Airport_list.add( airports );
	}
	/**
	 * エアポートごとの日付を保持する
	 */
	protected void insert( P2PAirport airports, String target_date ){
		((P2PAirport)Airports.get( airports )).setDays( target_date );
	}
	/**
	 * エアポートごとのフライト全リストの取得
	 */
	protected FlightNodesList getflightNodesList( P2PAirport airports ){
		return flightlits.getDistination( airports );
	}
	/**
	 * エアポートから日付を作る
	 */
	protected void setdailyFlight() throws Exception {
		Object[] airset = Airports.keySet().toArray();
		//System.err.println("</////// airset.length ----" + airset.length );
		for(int i=0; i<airset.length; i++){
			//System.err.println("</////// airset.length ----" + airset.length + ":  " + i + "------");
			P2PAirport destination = (P2PAirport)airset[i];
			Object[] dates = destination.days.keySet().toArray();
			FlightNodesList flightNodesList = getflightNodesList( destination );
			//System.err.println("</////// dates.length ----" + dates.length );
			for(int j=0; j<dates.length; j++){
				//System.err.println("</////// airset.length ----" + dates.length + ":  " + j + "------");
				flightNodesList.setDayflight( getFlight( (String)dates[j], destination.dep, destination.arv ) );		
			}
			flightlits.setDistnationsflight( destination, flightNodesList );
		}
	}
	/**
	 * 日毎のフライトの取得
	 */
	public Dayflight getFlight( String targetdate, String departture, String arrival ){
		//デイフライトがあるか否かチェック
		Dayflight dayflight = new Dayflight();
		try{
			dayflight.flightdate = targetdate;
			int[] size = new int[ NodeProperty.NodeLength ];
			int count = 0;
			for( int i=0; i<node.size(); i++){
				FlightNode flight = (FlightNode)node.get(i);
				if(( flight.flight_date.equals( targetdate ) )
					&&( flight.departture.equals( departture ) )
					&&( flight.arrival.equals( arrival ) )){
					dayflight.setFlightNode( flight, flight.flight_timecode );
					size[ count ] = i;
					count++;
				}					
			}
			for( int i=count - 1; i>=0; i--){
				this.node.remove( size[i] );
			}
		}catch(Exception ex){		}
		return dayflight;
	}
	/**
	 * データの保持
	 */
	public void setSqldata( Vector rows ){
		try{
		//FlightNodesList
		dbDataAccesser accesser = new dbDataAccesser( rows );
		//新しいノードを作成
		FlightNode[] flightNodes = new FlightNode[ accesser.getRowsize() ];
		//
		//System.err.println("--Row Size ------" +  accesser.getRowsize() + "-----------------");
		for(int i=0; i<accesser.getRowsize(); i++){
			int weekday 			= accesser.getDatabyInt(i, 0 );	//00
			String target_real_hour = accesser.getData( i, 1 ); 	//01
			int target_hour 		= accesser.getDatabyInt( i, 2 );//02
			String departture 		= accesser.getData( i, 3 ); 	//03
			String arrival 			= accesser.getData( i, 4 ); 	//04
			String cabin_class 		= accesser.getData( i, 5 ); 	//05
			String booking_class 	= accesser.getData( i, 6 );		//06
			String agt_cd 			= accesser.getData( i, 7 );		//07
			int allot_number 		= accesser.getDatabyInt( i, 8 );//08
			int max_member			= accesser.getDatabyInt( i, 9 );//09
			int min_member 		= accesser.getDatabyInt( i, 10);//10
			int price_base 		= accesser.getDatabyInt( i, 11);//11
			int price_child 		= accesser.getDatabyInt( i, 12);//12
			int price_infant 		= accesser.getDatabyInt( i, 13);//13
			int price_normal 		= accesser.getDatabyInt( i, 14);//14
			int allot_seq 			= accesser.getDatabyInt( i, 15);//15
			int sku_id 			= accesser.getDatabyInt( i, 16);//16
			String jan_cd 			= accesser.getData( i, 17 );	//17
			String date_target 		= accesser.getData( i, 18 );	//18
			String departurename 	= accesser.getData( i, 19 );	//19
			String arrivalname 		= accesser.getData( i, 20 );	//20
			int deputure_time 		= accesser.getDatabyInt( i, 21);//21
			int arrival_time 		= accesser.getDatabyInt( i, 22);//22
			String flight_no 		= accesser.getData( i, 23 );	//23
			String comment 			= accesser.getData( i, 24 );	//23
			String flight_Name 		= accesser.getData( i, 25 );	//24
			//FlightNodeの生成
			FlightNode flightnode = new FlightNode();
			flightnode.flight_no = flight_no;
			flightnode.weekday = weekday;
			flightnode.flight_time = target_real_hour;
			flightnode.flight_timecode = target_hour;
			flightnode.departture = departture;
			flightnode.arrival = arrival;
			flightnode.flightclass = cabin_class;
			flightnode.bookingclass = booking_class;
			flightnode.agentCode = agt_cd;
			flightnode.allot_number = allot_number;
			flightnode.max_member = max_member;
			flightnode.min_member = min_member;
			flightnode.price = price_base;
			flightnode.childprice = price_child;
			flightnode.infantprice = price_infant;
			flightnode.normalprice = price_normal;
			flightnode.allot_seq = allot_seq;
			flightnode.sku_id = sku_id;
			flightnode.jan_cd = jan_cd;
			flightnode.flight_date = date_target;
			flightnode.deputure_time = deputure_time;
			flightnode.arrival_time = arrival_time;
			flightnode.comment = comment;
			flightnode.lastsale = Property.getLastsaleTime( date_target, Property.endsale );
			flightnode.endsale = Property.getCansellTime( date_target, Property.cancellsale );
			flightnode.flight_name = flight_Name;
			///////////
			P2PAirport airports = new P2PAirport();
			airports.dep = departture;
			airports.arv = arrival;
			airports.depname = departurename;
			airports.arvname = arrivalname;
			if( !find( airports ) )	insert( airports );
			insert( airports, date_target );
			//System.err.println("<------------------------------" + date_target );
			node.add( flightnode );
		}
		setdailyFlight();
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	/**
	 * XMLデータの取得
	 */
	public String getXmlString(){
		String reStr = "";
		try{
			StringBuffer sub = new StringBuffer();
			sub.append("<dflight>\n" );
			//Object[] airset = Airports.keySet().toArray();
			//Airport_list		
			HashMap lists = flightlits.getHashActFlights();
			for( int j=0; j<Airport_list.size(); j++ ){
				P2PAirport targetname = (P2PAirport)Airport_list.get( j );
				P2PAirport destination = (P2PAirport)Airports.get( targetname );
				FlightNodesList Destinations = ( FlightNodesList ) lists.get( destination );
				sub.append("<airport>\n" );
					sub.append("\t<departure>\n" );
						sub.append("\t\t<lettercode>" + destination.dep + "</lettercode>\n" );
						sub.append("\t\t<airportname>" + destination.depname + "</airportname>\n" );
					sub.append("\t</departure>\n" );
					sub.append("\t<arrival>\n" );
						sub.append("\t\t<lettercode>" + destination.arv + "</lettercode>\n" );
						sub.append("\t\t<airportname>" + destination.arvname + "</airportname>\n" );
					sub.append("\t</arrival>\n" );
				//////////日付ごと
				for(int i=0;i<Destinations.flightslistbyday.size(); i++){
					Dayflight dayflight = (Dayflight)Destinations.flightslistbyday.get( i );
					sub.append("<targetdates>\n");
					sub.append( getDayList( dayflight ) );
					sub.append("</targetdates>\n");
				}
				sub.append("</airport>\n" );
			}
			sub.append("</dflight>\n" );
			reStr = sub.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return reStr;
	}
	/**
	 * 日毎のフライトを作る
	 */
	private static String getDayList( Dayflight dayflight){
		String reStr = "";
		DataFormat df = null;
		try{
			StringBuffer sub = new StringBuffer();
			//for(int i=0; i<dayflight.flights.length; i++){
				sub.append("<targetdate>\n");
					sub.append("\t<value>"+ dayflight.flightdate +"</value>\n");
					sub.append("\t<valuestr>"+ df.getDateTime4WWW( dayflight.flightdate).substring(5) + "(" +
					WeekdayToString.getWeekdayStr( dayflight.getWeekDay(), WeekdayToString._Japan ) + ")" + "</valuestr>\n");
					sub.append("\t<datetimes>\n");
					sub.append( getFlights( dayflight.flights ) );
					sub.append("\t</datetimes>\n");
				sub.append("</targetdate>\n");
			//}
			reStr = sub.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return reStr;	
	}
	/**
	 * 時間ごとのフライトを取得する
	 */
	private static String getFlights( FlightNode[] flightnodes ){
		String reStr = "";
		ParseFormat pf = null;
		try{
			StringBuffer sub = new StringBuffer();
			for(int i=0; i<flightnodes.length; i++){
				sub.append("<datetime>\n");
				if(flightnodes[i] != null){
					sub.append("\t<flightno>" + flightnodes[i].flight_no + "</flightno>\n");
					sub.append("\t<weekday>" + flightnodes[i].weekday + "</weekday>\n");
					sub.append("\t<flight_time>" + flightnodes[i].flight_time + "</flight_time>\n");
					sub.append("\t<flight_timecode>" + flightnodes[i].flight_timecode + "</flight_timecode>\n");
					sub.append("\t<departture>" + flightnodes[i].departture + "</departture>\n");
					sub.append("\t<arrival>" + flightnodes[i].arrival + "</arrival>\n");
					sub.append("\t<flightclass>" + flightnodes[i].flightclass + "</flightclass>\n");
					sub.append("\t<bookingclass>" + flightnodes[i].bookingclass + "</bookingclass>\n");
					sub.append("\t<agentCode>" + flightnodes[i].agentCode + "</agentCode>\n");
					sub.append("\t<allot_number>" + flightnodes[i].allot_number + "</allot_number>\n");
					sub.append("\t<max_member>" + flightnodes[i].max_member + "</max_member>\n");
					sub.append("\t<min_member>" + flightnodes[i].min_member + "</min_member>\n");
					sub.append("\t<price>" + flightnodes[i].price + "</price>\n");
					sub.append("\t<pricestr>" + pf.ToPriceFormat( flightnodes[i].price ) + "</pricestr>\n");
					sub.append("\t<childprice>" + flightnodes[i].childprice + "</childprice>\n");
					sub.append("\t<infantprice>" + flightnodes[i].infantprice + "</infantprice>\n");
					sub.append("\t<normalprice>" + flightnodes[i].normalprice + "</normalprice>\n");
					sub.append("\t<allot_seq>" + flightnodes[i].allot_seq + "</allot_seq>\n");
					sub.append("\t<deputure_time>" + flightnodes[i].deputure_time + "</deputure_time>\n");
					sub.append("\t<arrival_time>" + flightnodes[i].arrival_time + "</arrival_time>\n");
					sub.append("\t<sku_id>" + flightnodes[i].sku_id + "</sku_id>\n");
					sub.append("\t<jan_cd>" + flightnodes[i].jan_cd + "</jan_cd>\n");
					sub.append("\t<comment><![CDATA[" + flightnodes[i].comment + "]]></comment>\n");
					sub.append("\t<lastsale>" + flightnodes[i].lastsale + "</lastsale>\n");
					sub.append("\t<endsale>" + flightnodes[i].endsale + "</endsale>\n");
					sub.append("\t<flight_name><![CDATA[" + flightnodes[i].flight_name + "]]></flight_name>\n");
				}
				sub.append("</datetime>\n");
			}
			reStr = sub.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return reStr;	
	} 
}
