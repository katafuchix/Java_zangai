/*
 * PostManager.java
 *
 * Created on 2002/04/29, 18:41
 */

package jp.co.yobrain.util.rpc;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class PostManager implements Serializable{
    private Vector poststring;
    private String urlstring;
    private SendClient sendClient;
    
    /** Creates new PostManager */
    public PostManager(Vector post, String url) {
        sendClient = new jp.co.yobrain.util.rpc.SendClient();
        this.poststring = post;
        this.urlstring = url;
    }
    public String sendMessage() throws IOException{ 
        return sendClient.sendText( this.urlstring , this.poststring );
    }
    
    
    public String sendMessage(String encoding) throws IOException{ 
        return sendClient.sendText( this.urlstring , this.poststring , encoding);
    }
}
