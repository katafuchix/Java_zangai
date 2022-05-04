package jp.co.lastminute.Entertainment;

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
public class EntertainmentSearchCondition extends SeachCondition implements Serializable{
	private String ticket_type = "";	//チケット種別 
	private String event_names = "";	//イベント名
	private String actors = "";		//出演者
	private String play_place = "";	//場所
	
	//変換ルーチン
    private static String getConv2Sjis( String str ) throws IOException {
    	return new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }

	/**
	 * Returns the actors.
	 * @return String
	 */
	public String getActors() {
		return actors;
	}

	/**
	 * Returns the event_names.
	 * @return String
	 */
	public String getEvent_names() {
		return event_names;
	}

	/**
	 * Returns the play_place.
	 * @return String
	 */
	public String getPlay_place() {
		return play_place;
	}

	/**
	 * Returns the ticket_type.
	 * @return String
	 */
	public String getTicket_type() {
		return ticket_type;
	}

	/**
	 * Sets the actors.
	 * @param actors The actors to set
	 */
	public void setActors(String actors) {
		try{
			this.actors = getConv2Sjis( actors );
		}catch(Exception ex){	ex.printStackTrace();	}
	}

	/**
	 * Sets the event_names.
	 * @param event_names The event_names to set
	 */
	public void setEvent_names(String event_names) {
		try{
			this.event_names = getConv2Sjis( event_names );
		}catch(Exception ex){	ex.printStackTrace();	}
	}

	/**
	 * Sets the play_place.
	 * @param play_place The play_place to set
	 */
	public void setPlay_place(String play_place) {
		try{
			this.play_place = getConv2Sjis( play_place );
		}catch(Exception ex){	ex.printStackTrace();	}
	}

	/**
	 * Sets the ticket_type.
	 * @param ticket_type The ticket_type to set
	 */
	public void setTicket_type(String ticket_type) {
		try{
			this.ticket_type = getConv2Sjis( ticket_type );
		}catch(Exception ex){	ex.printStackTrace();	}
	}

}
