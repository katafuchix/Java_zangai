/*
 * CardAuth.java
 *
 * Created on 2002/04/29, 18:41
 */

package jp.co.lastminute.cardauth;

/**
 *
 * @author  skondo
 * @version
 */
public interface CardAuth {
  public String cardCheck(String cardNumber, String cardExpiry);
  public String cardCheck(String cardNumber, String cardExpiry, int price);
  public String cardCheck(String cardNumber, String cardExpiry, String name, int price);
  public String cardCheck(String cardNumber, String cardExpiry, String name, int price, boolean flag);
  public String cardCheck(String cardNumber, String cardExpiry, boolean flag);
  public String cardCheck(String cardNumber, String cardExpiry, int price, boolean flag);
}
