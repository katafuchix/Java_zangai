package jp.co.lastminute.cart.xml;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.*;

import java.util.StringTokenizer;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XMLElementGetter {
	public String getXmlString() {
		return xmlString;
	}

	/**
	 * xmlStringからXMLDomに変更する。
	 * @param　変更するXMLString
	 */
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
		//Make Reader with xmlString which is new xml String
		StringReader sr = new StringReader(this.xmlString);
		//make InputSource with StringReader of xml String
		InputSource is = new InputSource(sr);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			this.currentDom = builder.parse(is);
			Node root = currentDom.getDocumentElement();
			this.rootNode = root;
			//NodeList rowList = root.getChildNodes();

		} catch (SAXException sxe) {
			// Error generated during parsing)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();

		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();

		} catch (IOException ioe) {
			// I/O error
			ioe.printStackTrace();
		}

	}

	/**
	 * <blockquote><pre>
	 * <root>
	 * 		<abc>結果</abc>
	 * </root>
	 * </blockquote>
	 * abcの値を読み取るためにroot|abcのように
	 * ｜でelementを区別する。
	 * 
	 * @param elementName to get value（｜でelelmentを区別する。）　
	 * @return value of elementName
	 */
	public String getElementValue(String elementName) {
		String value = null;
		StringTokenizer elTokener = new StringTokenizer(elementName, "|");
		Node returnedNode = null;
		String elName = "";
		while (elTokener.hasMoreElements()) {
			if (returnedNode == null)
				returnedNode = rootNode;
			elName = elTokener.nextToken();
			returnedNode = getChildeNode(returnedNode, elName);
		} //end of while

		value = returnedNode.getChildNodes().item(0).getNodeValue();

		return value;
	}

	protected Node getChildeNode(Node node, String elementName) {
		if (node == null)
			return null;

		NodeList nodelist = node.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			if (nodelist.item(i).getNodeName().equals(elementName))
				return nodelist.item(i);
		}
		return null;
	}

	/**
	 * 変更するXMLString 
	 */
	protected String xmlString;
	protected Document currentDom = null;
	protected Node rootNode = null;

}
