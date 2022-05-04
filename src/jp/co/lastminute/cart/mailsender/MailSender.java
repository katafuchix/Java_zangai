/*
 * MailSender.java
 *
 * Created on 2002/11/22, 11:20
 */

package jp.co.lastminute.cart.mailsender;

/**
 *
 * @author  skondo
 */
import java.io.*;
import java.util.*;
import jp.co.yobrain.logger.EnterpriseLog;

import java.net.InetAddress;
import javax.mail.*;
import javax.mail.internet.*;

public final class MailSender {
    private static ResourceBundle resources;
    private static String smtpHost = "";
    private static String charset = "";
    private static String subject = "";
    private static String logfile = "";
    private static String mode = "";
    private static String logpathinfo = "";
	private static String logsize = "";
	private static String loggenerations= "";
    
    static {
        try {
            resources   = ResourceBundle.getBundle("jp.co.lastminute.cart.mailsender.resources.Mail", Locale.getDefault());
            smtpHost    = resources.getString("smtpHost");
            charset     = resources.getString("charset");
            mode		= resources.getString("mode");
            logpathinfo = resources.getString("logpathinfo");
            logsize 	= resources.getString("logsize");
            loggenerations = resources.getString("loggenerations");
             
        }catch (MissingResourceException mre){
            resources = null;
        }
    }
    ///////////////
    /** Creates new MailSender */
    public static boolean SendMail( String from, Object to, String subject, String body) {
        return SendMail(from, to, null, null, subject, body);
    }
    public static boolean SendMail(String from, Object to, Object cc, String subject, String body){
        return SendMail(from, to, cc, null, subject, body);
    }
    public static boolean SendMail(String from, Object to, Object cc, Object bcc, String subject, String body){
        String[] toStr = getAddress( to );
        String[] ccStr = getAddress( cc );
        String[] bccStr = getAddress( bcc );
        return send(from, toStr, ccStr, bccStr, subject, body);
    }
    private static boolean send( String from, String[] to, String[] cc, String[] bcc, String subject, String body){
        boolean returnflg = false;
        String logmessage = "SENDING FAIL";
        try {
        	if( mode.indexOf("test") == -1){
	            Properties props = System.getProperties();
	            props.put("mail.smtp.host", smtpHost);
	            props.put("mail.smtp.connectiontimeout", new Integer(10000));
	            props.put("mail.smtp.timeout", new Integer(10000));
	            Session session = Session.getDefaultInstance(props, null);
	            MimeMessage msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(from));
	            msg.setRecipients(Message.RecipientType.TO, getInternetAddress( to ) );
	            if( cc != null ) msg.setRecipients(Message.RecipientType.CC, getInternetAddress( cc ));
	            if( bcc != null )   msg.setRecipients(Message.RecipientType.BCC, getInternetAddress( bcc ));
	            msg.setSubject(subject, charset);
	            msg.setText(body, charset);
	            Transport.send(msg);
        	}
            returnflg = true;
        } catch( MessagingException msgex ){
            msgex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if( returnflg ) logmessage = "SENDING SUCCESS";
        EnterpriseLog logger = new EnterpriseLog( logpathinfo, logsize, loggenerations);
        try{
	        logger.openLog();
	        logger.logTrace(  "[SEND RESULT] " + logmessage + 
	                    " [FROM] " + from  + 
	                    " [TO] " + getMeetingAddress(to) + 
	                    " [CC] " + getMeetingAddress(cc) + 
	                    " [BCC]" + getMeetingAddress(bcc) + "\n" +
	                    " [SUBJECT]" + subject + "\n" +
	                    " [BODY]\n" + body);
	        logger.closeLog();
        }catch(Exception ex){	ex.printStackTrace();	}        
        return returnflg;
    }
    //////////////////////////////////////////////////////////////////////////////
    public static InternetAddress[] getInternetAddress( String[] to ){
        InternetAddress[] internetAddress = null;
        try{
            if( to != null ){		System.err.println("<--getParseAddress:102-->");
                int address_size = to.length;			System.err.println("<--getParseAddress:104 Address_Size " + address_size + " -->");
                internetAddress = new InternetAddress[ address_size ];
                for( int i=0; i<address_size; i++){	System.err.println("<--getParseAddress:106" + i + "-->");
                    String address = to[i];				System.err.println("<--getParseAddress:107 " + to[i] + "-->");
                    internetAddress[i] = new InternetAddress( address );
                    System.err.println("<--getParseAddress:109" + to[i] + "-->");
                }
            }
        }catch(Exception ex ){  ex.printStackTrace();   }
        return internetAddress;
    }
    protected static String[] getAddress( Object address ){
        String[] addresses = null;
        if( address != null ){
            try{
                if( address instanceof String[] ){	System.err.println("<--getParseAddress:118-->");
                    addresses = (String[])address;
                }else if( address instanceof String ){	System.err.println("<--getParseAddress:120-->");
                    String tempaddress = (String)address;
                    System.err.println( "<=---TEMP_ADDRESS_LENGHT--" + tempaddress.length() + "--->");
                    if( tempaddress.length() == 0 ){	return addresses;	}
                    addresses = getParseAddress( tempaddress );
                }
            }catch(Exception ex){   ex.printStackTrace();   }
        }
        return addresses;
    }
    protected static String[] getParseAddress( String tempaddress ){
        String[] addresses = null;
        if( tempaddress != null){
            if( tempaddress.indexOf( ';' ) == -1 ){		System.err.println("<--getParseAddress:131-->");
                addresses = new String[1];
                addresses[0] = tempaddress.trim();;
            }else if( tempaddress.indexOf( ';' ) > 0 ){	System.err.println("<--getParseAddress:134-->");
                StringTokenizer stringtoken = new StringTokenizer( tempaddress, ";", false );
                int count = stringtoken.countTokens();
                addresses = new String[count];
                for( int i=0; i<count; i++){				System.err.println("<--getParseAddress:138 " + i + " -->");
                    String temp = stringtoken.nextToken();	System.err.println("-----"+ temp + " -------");
                    addresses[i] = temp;
                }
            }
        }
        return addresses;
    }
    protected static String getMeetingAddress( String[] addresses ){
        String reStr = "";
        if( addresses != null ){
            try{
                for(int i=0; i<addresses.length; i++){
                    if( i != 0 ){
                        reStr += ";" + addresses[i];
                    }else{
                        reStr = addresses[i];
                    }
                }
            }catch(Exception ex){}            
        }
        return reStr;
    }
}

