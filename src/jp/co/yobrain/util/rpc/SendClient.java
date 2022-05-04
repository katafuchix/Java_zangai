package jp.co.yobrain.util.rpc;

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

public class SendClient implements Serializable{
  private String encoding = "";
  private String objname = "";
  public SendClient() {
    ResourceBundle resources = ResourceBundle.getBundle("jp.co.yobrain.util.rpc.resources.SendManager", Locale.getDefault());
    objname = resources.getString("objname");
    encoding = resources.getString("encoding");
  }

  private SendManager getReference(){
    SendManager mgr = null;
    Properties env = System.getProperties();
    try {
      // Create the initial context
      Context ctx = new InitialContext(env);
      mgr = (SendManager)ctx.lookup(objname);
      ctx.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mgr;
  }
  public String sendText(String url, Vector vec){
    return sendText(url, vec, encoding);
  }
  public String sendText(String url, Vector vec, String encode){
    String rslt = "";
    //System.err.println("Reffrence Ready");
    try {
      // Create the initial context
      rslt = getReference().sendMessage(url, vec, encode);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
    return rslt;
  }

  public String sendText(String url, String text){
    return sendText(url, text, encoding);
  }
  public String sendText(String url, String text, String encode){
    String rslt = "";
    try {
      // Create the initial context
      rslt = getReference().sendMessage(url, text, encode);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
    return rslt;
  }
}