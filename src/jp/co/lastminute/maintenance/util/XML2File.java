package jp.co.lastminute.maintenance.util;

import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
public class XML2File {
		public String xmlEncode = "UTF-8";
		public void write(File targetFile, Document doc, String encode) throws Exception{
			try {
				// Use a Transformer for output
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.ENCODING, encode);

				DOMSource source = new DOMSource(doc);
				StreamResult result =
					new StreamResult(new FileOutputStream(targetFile));
				transformer.transform(source, result);
			} catch (TransformerConfigurationException tce) {
				Throwable x = tce;
				if (tce.getException() != null){	x = tce.getException();	}
				x.printStackTrace();
				throw tce;
			} catch (TransformerException te) {
				te.printStackTrace();
				// Use the contained exception, if any
				Throwable x = te;
				if (te.getException() != null){	x = te.getException();	}
				x.printStackTrace();
				throw te;
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
				throw fnfe;
			}
		}

		public void write(String xmlString, String filePath, String encode) throws Exception{
			File file = new File(filePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			Document doc = null;
			String result = "";
			//Make Reader with xmlString which is new xml String
			StringReader sr = new StringReader(xmlString);
			//make InputSource with StringReader of xml String
			InputSource is = new InputSource(sr);
			try {
				builder = factory.newDocumentBuilder();
				doc = builder.parse(is);
				this.write(file, doc, encode);
			} catch (SAXException sxe) {
				// Error generated during parsing)
				Exception x = sxe;
				if (sxe.getException() != null) {
					x = sxe.getException();
					x.printStackTrace();
				} else {
					x.printStackTrace();
				}
				throw sxe;
			} catch (ParserConfigurationException pce) {
				// Parser with specified options can't be built
				pce.printStackTrace();
				throw pce;
			} catch (IOException ioe) {
				result = "fail";
				// I/O error
				ioe.printStackTrace();
				throw ioe;
			}
		}

		public void write(String xmlString, String filepath) throws Exception{
			this.write(xmlString, filepath, this.xmlEncode);
		}

	}
