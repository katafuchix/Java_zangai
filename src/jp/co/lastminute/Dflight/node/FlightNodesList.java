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
public class FlightNodesList implements Serializable {
	public P2PAirport distination = null;
	public Vector flightslistbyday = new Vector();
	private int dayscounts = 0;
	/**
	 * ディスティネーションの保持
	 */
	public void setDistination( P2PAirport distination ){
		this.distination = distination;		
	}
	/**
	 * ディスティネーションの取得
	 */
	public P2PAirport getDistination(){
		return this.distination;	
	}
	/**
	 * 日毎のフライトの保持
	 */
	public void setDayflight( Dayflight dayflights ){
		try{
			int targetdate = Integer.parseInt( dayflights.flightdate );
			if( flightslistbyday.size() == 0 ){
				flightslistbyday.add( dayflights );
			}else{
				boolean actionflg = false;
				int max = 0;
				for(int i=0; i<flightslistbyday.size(); i++){
					Dayflight flights = (Dayflight)flightslistbyday.get( i );
					int basedate = Integer.parseInt( flights.flightdate );
					if( targetdate == basedate ){
						actionflg = true;
					}
					if( basedate > max ){
						max = basedate;
					}
				}
				if( !actionflg ){
					for(int i=0; i<flightslistbyday.size(); i++){
						//サイズが１の時は、必ず追加 //小さいものが前にくる。
						if( flightslistbyday.size() == 1){
							int nowstatus = Integer.parseInt( ((Dayflight)flightslistbyday.get( i ) ).flightdate );
							if( nowstatus < targetdate ){
								flightslistbyday.add( dayflights );
							}else{
								flightslistbyday.add( 0, dayflights );
							}
							break;
						}else if( i== flightslistbyday.size() -1 ){	// 全て回ってしまったとき
							if( max < targetdate ){
								flightslistbyday.add( dayflights );
							}else{
								flightslistbyday.add( 0, dayflights );
							}
							break;
						}else{	//
							int nowstatus = Integer.parseInt( ((Dayflight)flightslistbyday.get( i ) ).flightdate );
							int nexttatus = Integer.parseInt( ((Dayflight)flightslistbyday.get( i+1 )).flightdate );
							if(( nowstatus < targetdate )&&( nexttatus > targetdate)){
								flightslistbyday.add( i+1 , dayflights );
								break;
							}		
						}		
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();	
		}
	}
	/**
	 * 日毎のフライトの取得 
	 */
	public Dayflight getDayflight( int index ){
		if( index > flightslistbyday.size() )	return null;
		if( flightslistbyday.get( index ) == null )	return null;
		return (Dayflight) flightslistbyday.get( index );
	}
	/**
	 * 日毎リストの取得
	 */
	public Vector getDayflights(){
		return flightslistbyday;
	}

}
