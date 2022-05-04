package jp.co.lastminute.card;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.io.*;
import java.util.*;

import javax.naming.*;

public class CardAuthClient implements Serializable, CardAuth{
  private String encoding = "Shift_JIS";
  private static ResourceBundle resources;
  private static String objname;
  private static String mode;
  static {
  	try {
    	resources   = ResourceBundle.getBundle("jp.co.lastminute.card.resources.CardAuth", Locale.getDefault());
        objname = resources.getString("objname");
        mode     = resources.getString("mode");
    }catch (MissingResourceException mre){
    	resources = null;
    } 	
  }
  public CardAuthClient() {
  }
  public String cardCheck(String cardNumber, String cardExpiry) {
  	return cardCheck( cardNumber, cardExpiry, "", 0, false);
  }
  public String cardCheck(String cardNumber, String cardExpiry, boolean flag) {
  	return cardCheck( cardNumber, cardExpiry, "", 0, flag );
  }
  public String cardCheck(String cardNumber, String cardExpiry, int price) {
  	return cardCheck( cardNumber, cardExpiry, "", price, false);
  }
  public String cardCheck(String cardNumber, String cardExpiry, int price, boolean flag) {
  	return cardCheck( cardNumber, cardExpiry, "", price, flag);
  }
  public String cardCheck(String cardNumber, String cardExpiry, String name, int price) {
  	return cardCheck(cardNumber, cardExpiry, name, price, false);
  }
  public String cardCheck(String cardNumber, String cardExpiry, String name, int price, boolean flag) {
    String rslt = "";
    Properties env = System.getProperties();
	String action_type = "";
	if( mode.indexOf( "test" ) >= 0 ){
		action_type = "Buy Test";
		System.err.println("<------This Is Test Mode Of Card Auth------>");
		rslt = "000000";
	}else{
	    try {
	      // Create the initial context
	      	Context ctx = new InitialContext(env);
	      	CardAuth auth = (CardAuth)ctx.lookup(objname);
	      	ctx.close();
	      	System.out.println("[ auth program ");
	      	System.out.print(auth);
	      	System.out.println(" ]");
	      	
			if( price == 0 ){
				rslt = auth.cardCheck(cardNumber, cardExpiry, flag );
				action_type = "Check only";
			}else if( name.length() == 0){
				rslt = auth.cardCheck(cardNumber, cardExpiry, price, flag );
				action_type = "Buy Real";
			}else{
				action_type = "Buy Real Ext";
	      		rslt = auth.cardCheck(cardNumber, cardExpiry, name, price, flag );
			}
			
	    } catch (Exception e) {
	      System.out.println("Operation failed: " + e);
	    }
	}
    System.out.print("[ auth info ");
    System.out.print( action_type + ":" + cardNumber + ":" + cardExpiry + ":" + price + ":" + name );
    System.out.println(" ]");
    return rslt;
  }
}