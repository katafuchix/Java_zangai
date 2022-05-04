package jp.co.yobrain.util.rpc;

import java.util.Vector;
/**
 *
 * @author  skondo
 * @version
 */
public interface SendManager {
  public String sendMessage(String sendurl, String sendtext);
  public String sendMessage(String sendurl, String sendtext, String encoding);
  public String sendMessage(String sendurl, Vector poststring);
  public String sendMessage(String sendurl, Vector poststring, String encode);
  public String sendMessage(String sendurl);
}
