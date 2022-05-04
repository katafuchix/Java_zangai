package jp.co.yobrain.util.rpc;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.net.ssl.*;
import java.security.*;

import javax.naming.*;
import javax.naming.directory.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SendManagerServlet extends HttpServlet implements SendManager {

  private String encoding = "";
  private String objname = "";
  private String urlparam = "";
  private String textparam = "";
  private String encparam = "";
  private String mode = "";

  private ServletContext servletContext;
  private ResourceBundle resources;

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init( servletConfig );
    servletContext = servletConfig.getServletContext();

    try {
      resources = ResourceBundle.getBundle("jp.co.yobrain.util.rpc.resources.SendManager", Locale.getDefault());
      encoding = resources.getString("encoding");
      objname = resources.getString("objname");
      urlparam = resources.getString("urlparam");
      textparam = resources.getString("textparam");
      encparam = resources.getString("encparam");
	  mode = resources.getString("mode");
	  
      System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
      Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

      Properties env = System.getProperties();
      Context ictx = new InitialContext(env);
      ictx.bind(objname, this);
      Object obj = ictx.lookup(objname);
      System.out.println(obj);
      ictx.close();

    } catch(Exception e){
      System.out.println(e);
    }
  }
  public void destroy(){
    try {
      Properties env = System.getProperties();
      Context ictx = new InitialContext(env);
      ictx.unbind(objname);
    } catch(Exception e){
      System.out.println(e);
    }
    super.destroy();
  }

  public String postText(String posturl, String posttext, String encod){
    BufferedWriter out = null;
    BufferedReader in = null;
    StringBuffer ret = new StringBuffer("");
    try{
      URL url = new URL(posturl);
      URLConnection con = url.openConnection();
      con.setDoInput(true);
      con.setDoOutput(true);
      out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), encod));
      out.write(posttext, 0, posttext.length());
      out.flush();
      if( mode.indexOf( "test" ) != -1 ){	System.out.println("SEND \n" + posttext);	}
      con.connect();
      in = new BufferedReader(new InputStreamReader(con.getInputStream(), encod));
      String buf;
      while((buf = in.readLine()) != null){
        ret.append(buf);
        //E-LINEópÇÃURLÇÕÅAèúäO
        if( posturl.startsWith( "http://10.9.1.18:8989/" ) ){
            ret.append("\n");
        }
      }
      if( mode.indexOf( "test" ) != -1 ){
      		System.out.println("RCV \n" + ret.toString());
      }
    } catch(Exception e){
      System.out.println(e);
      e.printStackTrace(System.out);
      ret.append(e);
    } finally {
      try { out.close(); in.close();} catch(Exception e){}
    }
    if( mode.indexOf( "test" ) != -1 ){
	    System.err.println( "RETURN IS =" + ret.toString() );
    }
    return ret.toString();
  }
  public String sendMessage(String sendurl){
    return postText(sendurl, "", encoding);
  }
  public String sendMessage(String sendurl, String sendtext){
    return postText(sendurl, sendtext, encoding);
  }
  public String sendMessage(String sendurl, String sendtext, String encode){
    return postText(sendurl, sendtext, encode);
  }

  public String sendMessage(String sendurl, Vector poststring){
    return sendMessage(sendurl, poststring, encoding);
  }

  public String sendMessage(String sendurl, Vector poststring, String encode){
    StringBuffer sb = new StringBuffer();
    String param;     
    if( mode.indexOf( "test" ) != -1 ){
    	System.err.println( "URL=" + sendurl );
    }
    try{
      for(int i = 0; i < poststring.size(); i++){
        PostString postval = (PostString)poststring.get(i);
        sb.append(postval.geteqName()); 
        if( mode.indexOf( "test" ) != -1 ){
        	System.err.print( "SENDCLIENT:NAME=" + postval.geteqName() );
        }
        sb.append(URLEncoder.encode(postval.getValue(), encode));   
        if( mode.indexOf( "test" ) != -1 ){
        	System.err.print( "SENDCLIENT:NAME=" + postval.getValue() );
        }
        if(i != poststring.size() -1){
          sb.append("&");
        }
        postval = null;
      }
      param = sb.toString();
      if( mode.indexOf( "test" ) != -1 ){
      	System.err.println("SND PARAM \n" + param);
      }
    } catch(Exception e){
      return "";
    }
    return postText(sendurl, param, encode);
  }
}
