/*
 * NodeReader.java
 *
 * Created on 2002/06/21, 09:00
 */

package jp.co.lastminute.common.xml;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import jp.co.lastminute.common.xml.*;

/**
 *
 * @author  ser
 * @version 
 */
public class NodeReader implements Serializable{

    /** コンストラクター */
    public NodeReader() {
    }
    
    /** 
     * あるノードから値を取得
     * @param  Element e, String TagName
     */
	public static String getElementContent(Element el, String TagName) {
		try{
			NodeList nl = el.getElementsByTagName(TagName);
			Node n = nl.item(0);
			Node content = n.getFirstChild();

			if( content.getNodeValue() != null )
				return content.getNodeValue();
			else
				return "";
		}catch(Exception e){
		}
		return "";

	}
     
}