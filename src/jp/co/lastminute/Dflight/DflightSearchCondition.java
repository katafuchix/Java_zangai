package jp.co.lastminute.Dflight;

import java.io.*;
import java.io.Serializable;
import jp.co.lastminute.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DflightSearchCondition extends SeachCondition implements Serializable{
	private String flightno = "";
	private String flightname = "";
	private String flighttimecode = "";
	private String flight_time = "";
	private String flightclass = "";
	private String bookingclass = "";
	private String childprice = "";
	private String infantprice = "";
	private String normalprice = "";
	private String arrival_time = "";
	private String allot_number = "";
	private String fromname = "";
	private String toname = "";
	
	/**
	 * Returns the flightname.
	 * @return String
	 */
	public String getFlightname() {
		return flightname;
	}

	/**
	 * Returns the flightno.
	 * @return String
	 */
	public String getFlightno() {
		return flightno;
	}

	/**
	 * Sets the flightname.
	 * @param flightname The flightname to set
	 */
	public void setFlightname(String flightname) throws IOException {
		this.flightname = getConv2Sjis( flightname ) ;
	}

	/**
	 * Sets the flightno.
	 * @param flightno The flightno to set
	 */
	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	/**
	 * Returns the flighttimecode.
	 * @return String
	 */
	public String getFlighttimecode() {
		return flighttimecode;
	}

	/**
	 * Sets the flighttimecode.
	 * @param flighttimecode The flighttimecode to set
	 */
	public void setFlighttimecode(String flighttimecode) {
		this.flighttimecode = flighttimecode;
	}

	/**
	 * Returns the bookingclass.
	 * @return String
	 */
	public String getBookingclass() {
		return bookingclass;
	}

	/**
	 * Returns the flightclass.
	 * @return String
	 */
	public String getFlightclass() {
		return flightclass;
	}

	/**
	 * Sets the bookingclass.
	 * @param bookingclass The bookingclass to set
	 */
	public void setBookingclass(String bookingclass) {
		this.bookingclass = bookingclass;
	}

	/**
	 * Sets the flightclass.
	 * @param flightclass The flightclass to set
	 */
	public void setFlightclass(String flightclass) {
		this.flightclass = flightclass;
	}

	/**
	 * Returns the childprice.
	 * @return String
	 */
	public String getChildprice() {
		return childprice;
	}

	/**
	 * Returns the infantprice.
	 * @return String
	 */
	public String getInfantprice() {
		return infantprice;
	}

	/**
	 * Returns the normalprice.
	 * @return String
	 */
	public String getNormalprice() {
		return normalprice;
	}

	/**
	 * Sets the childprice.
	 * @param childprice The childprice to set
	 */
	public void setChildprice(String childprice) {
		this.childprice = childprice;
	}

	/**
	 * Sets the infantprice.
	 * @param infantprice The infantprice to set
	 */
	public void setInfantprice(String infantprice) {
		this.infantprice = infantprice;
	}

	/**
	 * Sets the normalprice.
	 * @param normalprice The normalprice to set
	 */
	public void setNormalprice(String normalprice) {
		this.normalprice = normalprice;
	}

	/**
	 * Returns the arrival_time.
	 * @return String
	 */
	public String getArrival_time() {
		return arrival_time;
	}

	/**
	 * Sets the arrival_time.
	 * @param arrival_time The arrival_time to set
	 */
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	/**
	 * Returns the allot_number.
	 * @return String
	 */
	public String getAllot_number() {
		return allot_number;
	}

	/**
	 * Sets the allot_number.
	 * @param allot_number The allot_number to set
	 */
	public void setAllot_number(String allot_number) {
		this.allot_number = allot_number;
	}

	/**
	 * Returns the fromname.
	 * @return String
	 */
	public String getFromname() {
		return fromname;
	}

	/**
	 * Returns the toname.
	 * @return String
	 */
	public String getToname() {
		return toname;
	}

	/**
	 * Sets the fromname.
	 * @param fromname The fromname to set
	 */
	public void setFromname(String fromname) throws IOException {
		this.fromname = getConv2Sjis( fromname );
	}

	/**
	 * Sets the toname.
	 * @param toname The toname to set
	 */
	public void setToname(String toname) throws IOException {
		this.toname = getConv2Sjis( toname );
	}
	//ïœä∑ÉãÅ[É`Éì
    private static String getConv2Sjis( String str ) throws IOException {
    	return new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }
	/**
	 * Returns the flight_time.
	 * @return String
	 */
	public String getFlight_time() {
		return flight_time;
	}

	/**
	 * Sets the flight_time.
	 * @param flight_time The flight_time to set
	 */
	public void setFlight_time(String flight_time) {
		this.flight_time = flight_time;
	}

}
