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
public class Areas implements Serializable{
	private String state_cd = "";
	private String city_cd = "";
	public Hashtable states = null;
	///////
	public Areas(){
		this.states = new Hashtable();
	}
	public Areas( Hashtable states ){
		System.err.println(" Area Set Instance ");
		this.states = states;		
		System.err.println(" Area Set Instance End ");
	}
	public void putState( StateParam state ){
		if( states.containsKey( state.state_cd ) ){
			states.remove( state.state_cd );
		}
		states.put( state.state_cd, state );
	}
	public StateParam getStateParam( String state_cd ){
		try{
			if( states.get( state_cd )  != null ){
				return (StateParam)states.get( state_cd );
			}
			StateParam state = new StateParam();
			return state;
		}catch(Exception ex){
			return (new StateParam());	
		}
	}
	public StateParam getState(){
		return (StateParam)states.get( state_cd );
	}
	/**
	 * Returns the city_cd.
	 * @return String
	 */
	public String getCity_cd() {
		return city_cd;
	}

	/**
	 * Returns the state_cd.
	 * @return String
	 */
	public String getState_cd() {
		return state_cd;
	}

	/**
	 * Returns the states.
	 * @return HashMap
	 */
	public Hashtable getStates() {
		return states;
	}

	/**
	 * Sets the city_cd.
	 * @param city_cd The city_cd to set
	 */
	public void setCity_cd(String city_cd) {
		this.city_cd = city_cd;
	}

	/**
	 * Sets the state_cd.
	 * @param state_cd The state_cd to set
	 */
	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}

	/**
	 * Sets the states.
	 * @param states The states to set
	 */
	public void setStates(Hashtable states) {
		this.states = states;
	}

}
