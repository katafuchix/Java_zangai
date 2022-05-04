package jp.co.lastminute.Dflight.node;

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
public class ActFlights implements Serializable{
	private HashMap distnationsflight = new HashMap();
	/**
	 * フライトディスティネーションの保持
	 */
	public void setDistnationsflight( P2PAirport airport, FlightNodesList distnation){
		if( containsP2PAirport( airport )) {
			distnationsflight.remove( airport );
		}
		distnationsflight.put( airport, distnation );
	}
	/**
	 * ハッシュマップの取得
	 */
	public HashMap getHashActFlights(){
		return distnationsflight;
	}
	/**
	 * ディスティネーション毎のフライトリストの取得
	 */
	public boolean containsP2PAirport( P2PAirport airport ){
		try{
			return distnationsflight.containsKey( airport );
		}catch(Exception ex){}
		return false;
	}
	/**
	 * ディスティネーション毎のディスティネーションの取得
	 */
	public FlightNodesList getDistination( P2PAirport airport ){
		try{
			return (FlightNodesList) distnationsflight.get( airport );
		}catch(Exception ex){}
		return null;
	}
}
