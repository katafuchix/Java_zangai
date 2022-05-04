/*
 * XmlPostHelper.java
 *
 * Created on 2002/05/01, 11:09
 */

package jp.co.lastminute.cart.xml;

import java.io.Serializable;
import java.util.Vector;
import jp.co.yobrain.util.xml.Xslt;
import jp.co.lastminute.cart.xml.XmlToPostStrings;
import jp.co.yobrain.util.rpc.SendClient;

/**
 *
 * @author  skondo
 * @version
 */
public class XmlPostHelper implements Serializable{
    
    public static XmlBeanBase querySupplyer(String posturl, String xmlstring, String xslfile)
    throws Exception {
        try{
            return querySupplyer(posturl, xmlstring, xslfile, "XmlBeanBase");
        } catch(Exception e){
            throw e;
        }
    }
    public static XmlBeanBase querySupplyer(String posturl, String xmlstring, String xslfile, String classname)
    throws Exception {
        System.err.println( "POST URl=\n" + posturl );
        System.err.println( "POST XML=\n" + xmlstring );
        System.err.println( "POST XSL=\n" + xslfile );
        System.err.println( "POST CLASS=\n" + classname );
        // ƒeƒLƒXƒgƒx[ƒX‚Ì‚w‚l‚k‚Æ‚w‚r‚kƒtƒ@ƒCƒ‹‚t‚q‚h‚©‚ç‚w‚l‚k‚ğì¬‚·‚é
        Xslt xslt = new Xslt();         System.err.println( "XmlPostHelper:37" );
        xslt.setTextXml(xmlstring);     System.err.println( "XmlPostHelper:38" );
        xslt.setTextXsl( xslfile );     System.err.println( "XmlPostHelper:39" );
        //xslt.setXsl(xslfile);
        String result = xslt.applyToString("UTF-8");  
        xslt = null;                                  
        // ‚w‚l‚k‚©‚çƒpƒ‰ƒ[ƒ^ƒŠƒXƒg‚ğì¬‚·‚é
        Vector vector;
        try{
        	vector = XmlToPostStrings.toVector(result);
        } catch(Exception e){
            throw e;
        }
        // ƒTƒvƒ‰ƒCƒ„[‚É‚o‚n‚r‚s‚·‚é
        SendClient client = new SendClient();
        result = client.sendText(posturl, vector);
        client = null;
        // •ÔM‚ğJAVA CLASS ‚É“ü‚ê‚é
        String clname = "jp.co.lastminute.cart.xml." + classname;
        XmlBeanBase obj;
        try {
            obj = (XmlBeanBase)Class.forName(clname).newInstance();
            obj.fill(result);
        } catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    
}
