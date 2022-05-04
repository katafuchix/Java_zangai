package jp.co.lastminute.cart;

import java.io.*;
import java.util.*;
import jp.co.lastminute.cart.prop.*;
import jp.co.lastminute.cart.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class CheckTool {
	public static boolean isPassCart( int cartnum ){
		int[] numArray = { Constants.Gift_, Constants.Ticket_ };
		for(int i=0; i<numArray.length; i++){
			if( numArray[i] == cartnum ){
				return true;
			}
		}
		return false;		
	}
	public static Sub_Order getTaretSuborder( Navigato tempnavi, Sub_Order suborder, Sub_Order target_suborder ){
		if( suborder.getStatus() < Constants.CON_CONFIRM_ 
			&& tempnavi.isThrow() == Constants.IS_THROW_NG  ){
			return suborder;
		}
		return target_suborder;
	}
	
	public static boolean getCard_flg( boolean card_flg, Navigato tempnavi, Sub_Order suborder ){
		if ( suborder.getStatus() == Constants.NOT_CONFIRM_ 
		&& tempnavi.isCardAuth() >= Constants.AUTH_ONLY_){
			return true;
		}
		return card_flg;
	}
	
	public static boolean getCartIsend( boolean cartIsend, Sub_Order suborder ){
		if( cartIsend  && suborder.getStatus() < Constants.CON_CONFIRM_ ){
			return false;
		}
		return cartIsend;
	}
}
