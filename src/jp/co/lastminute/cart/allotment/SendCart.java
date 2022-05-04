package jp.co.lastminute.cart.allotment;

import java.util.Vector;

import java.io.IOException;

// Imported TraX classes
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;

// Imported java classes
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import java.io.StringReader;
import java.io.Reader;

import java.io.StringWriter;
import java.io.Writer;

import jp.co.yobrain.util.rpc.PostString;
import jp.co.yobrain.util.rpc.SendClient;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SendCart {
	protected static final String CONST_AGT_CD = "AGT_CD";
	protected static final String CONST_PRODUCT_TYPE_CD = "PRODUCT_TYPE_CD";
	protected static final String CONST_CONN_STYLE = "CONN_STYLE";
	protected static final String CONST_XML_DOC = "XML_DOC";
	protected static final String SUCCESS = "SUCCESS";
	protected static final String FAIL = "FAIL";
	/////
	private String cartReservationResultErrorXLSFilePathError;

	private String registrationAddress;

	/**
	* Sends an Order
	*/
	public String sendOrder(Object forms, String xmlString)
		throws IOException {
		Vector paramsVector = new Vector(4);
		paramsVector.add(
			new PostString(
				CONST_AGT_CD,
				((RegistrationForm) forms).getAgtcodeTarget()));
		paramsVector.add(
			new PostString(
				CONST_PRODUCT_TYPE_CD,
				((RegistrationForm) forms).getProductTypeCodeTarget()));
		paramsVector.add(
			new PostString(
				CONST_CONN_STYLE,
				((RegistrationForm) forms).getConnectionStyleTarget()));

		paramsVector.add(new PostString(CONST_XML_DOC, xmlString));
		return sendOrder(paramsVector);
	}
	protected String sendOrder(Vector paramsVector )
		throws IOException {
		try {
			//LogHelper logger = new LogHelper();
			SendClient postManager = new SendClient();
			String resultFromWebsite =
				postManager.sendText(registrationAddress, paramsVector);
			if (resultFromWebsite == null || resultFromWebsite.length() == 0) {
				throw new IOException("Invalid result from the Web Site");
			}
			//System.err.println("resultFromWebsite: " + resultFromWebsite);
			Reader result = (Reader) new StringReader(resultFromWebsite);
			DocumentBuilderFactory factory =
				DocumentBuilderFactory.newInstance();
			Document document;
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				document = builder.parse(new org.xml.sax.InputSource(result));
				//document.normalize();
				if (document.getFirstChild().getFirstChild().getNodeName()
					== null) {
					return null;
				}
				if (document
					.getFirstChild()
					.getFirstChild()
					.getNodeName()
					.equals(SUCCESS)) {
					return resultFromWebsite;
				}
				if (resultFromWebsite.indexOf(SUCCESS) != -1) {
					return resultFromWebsite;
				}
				return null;

			} catch (SAXParseException spe) {
				return null;
			} catch (SAXException sxe) {
				return null;
			} catch (ParserConfigurationException pce) {
				return null;
			} catch (NullPointerException npe1) {
				return null;
			}
			//return resultFromWebsite; 
		} catch (IOException ioe1) {
			throw ioe1;
		}
	}
	public SendCart(String registrationAddress)
		throws TransformerConfigurationException, NullPointerException {
		if (registrationAddress == null) {
			NullPointerException npe =
				new NullPointerException("The registration address cannot be null");
			throw npe;
		}
		this.registrationAddress = registrationAddress;
	}
}
