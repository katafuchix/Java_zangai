package jp.co.lastminute.cart.xml;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XMLCDATAGetter extends XMLElementGetter{
	public String getCDATA(String elementName)
    {
        String value = "";
        StringTokenizer elTokener = new StringTokenizer(elementName, "|");
        Node returnedNode = null;
        String elName = "";

        while(elTokener.hasMoreElements())
		{
            if(returnedNode == null)
                returnedNode = rootNode;
            elName =elTokener.nextToken();
            returnedNode =  getChildeNode(returnedNode,elName);

		}//end of while

        return getCDATAValue(returnedNode);
    }

	public Vector getCDATAofChild(String elementName)
    {
        Vector value = new Vector();
        StringTokenizer elTokener = new StringTokenizer(elementName, "|");
        Node returnedNode = null;
        String elName = "";

        while(elTokener.hasMoreElements())
		{
            if(returnedNode == null)
                returnedNode = rootNode;
            elName =elTokener.nextToken();

            //System.out.println(returnedNode);

            returnedNode =  getChildeNode(returnedNode,elName);
            //System.out.println(returnedNode);
		}//end of while
		NodeList nodelist = returnedNode.getChildNodes();

		Node childNode = null;
        int type = 0;
        for (int i=0;i<nodelist.getLength();i++ )
		{	childNode = nodelist.item(i);
			type =childNode.getNodeType();
            if(type ==Node.ELEMENT_NODE)
            {
                value.add(getCDATAValue(childNode));
            }
		}
        return value;
    }

    protected String getCDATAValue(Node node)
    {
		NodeList nodelist = node.getChildNodes();
        int type =0;
		String value = "";
        for (int i=0;i<nodelist.getLength();i++ )
		{
            node = nodelist.item(i);
            type =node.getNodeType();
            if(type ==Node.CDATA_SECTION_NODE)
            {
                value = node.getNodeValue();
            }
		}
        return value;
    }
	public static void main(String[] args)
    {
        System.out.println("Start ====");
        StringBuffer statement = new StringBuffer();
       // statement.append(" <?xml version=\"1.0\" encoding=\"Shift-JIS\"?>                                                             ");
		statement.append(" <NEWBOKOUT>                                                                                            ");
		statement.append(" <SUCCESS>                                                                                              ");
		statement.append(" 	<ORDERNO>R84-721-512</ORDERNO>                                                                 ");
		statement.append(" <PRICE>19000</PRICE>                                                                                   ");
		statement.append(" <DISCRIPTION><CONTENT><![CDATA[ホテル　城ヶ倉]]></CONTENT>                                             ");
		statement.append(" <CONTENT><![CDATA[017-738-0658]]></CONTENT>                                                            ");
		statement.append(" <CONTENT><![CDATA[バス付ツイン]]></CONTENT>                                                            ");
		statement.append(" <CONTENT><![CDATA[夕朝食付]]></CONTENT>                                                                ");
		statement.append(" <CONTENT><![CDATA[(宿泊料金)19000円 + (諸税)1250円]]></CONTENT>                                        ");
		statement.append(" <CONTENT><![CDATA[06月27日から1泊　大人9500円×2名]]></CONTENT>                                        ");
		statement.append(" <CONTENT><![CDATA[2002年06月24日から　20％　(取消し料率は、取消し受付日によって変わります)]]></CONTENT>");
		statement.append(" </DISCRIPTION>                                                                                         ");
		statement.append(" </SUCCESS>                                                                                             ");
		statement.append(" </NEWBOKOUT>      ");


        XMLCDATAGetter xg = new XMLCDATAGetter();
        xg.setXmlString(statement.toString());
		System.out.println(xg.getCDATA("NEWBOKOUT|SUCCESS|DISCRIPTION|CONTENT"));

        Vector  v =xg.getCDATAofChild("NEWBOKOUT|SUCCESS|DISCRIPTION");
        for(int i =0; i< v.size(); i++)
        {
            System.out.println((String)v.get(i));
        }

    }

}
