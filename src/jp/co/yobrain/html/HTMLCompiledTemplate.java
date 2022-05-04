package jp.co.yobrain.html;

import java.io.*;
import java.util.*;
import java.net.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HTMLCompiledTemplate {
	private String sFName;
	private Vector vCompiled = null;

	private static final int TEXT = 0;
	private static final int DOLLAR = 1;
	private static final int FIELDNAME = 2;

	public HTMLCompiledTemplate(String fName) throws IOException {
		sFName = fName;

		vCompiled = new Vector();

		parseFile();
	}

	public HTMLCompiledTemplate(StringBuffer sBuf) throws IOException {
		sFName = "";

		vCompiled = new Vector();

		parse(sBuf);
	}

	public HTMLTemplate createHTMLTemplate() {
		return new HTMLTemplate(this);
	}

	protected Vector getCompiledVector() {
		return vCompiled;
	}

	private void parse(StringBuffer sBuf) throws IOException {
		int length = sBuf.length();
		StringBuffer fieldBuf = new StringBuffer();
		StringBuffer clearBuf = new StringBuffer();
		char charRead;

		int state = TEXT;

		for (int i = 0; i < length; i++) {
			switch (charRead = sBuf.charAt(i)) {
				case ('$') :
					if (state == DOLLAR) {
						clearBuf.append("$");
					}
					state = DOLLAR;
					break;
				case ('{') :
					if (state == DOLLAR) {
						if (clearBuf.length() > 0) {
							vCompiled.addElement(
								new HTMLTemplateItem(
									clearBuf.toString(),
									true));
						}
						clearBuf.setLength(0);
						fieldBuf.setLength(0);
						state = FIELDNAME;
					} else {
						clearBuf.append(charRead);
						state = TEXT;
					}
					break;
				case ('}') :
					if (state == FIELDNAME) {
						if (fieldBuf.length() != 0) {
							vCompiled.addElement(
								new HTMLTemplateItem(
									fieldBuf.toString().toUpperCase(),
									false));
						} else {
							clearBuf.append("${}");
						}
						state = TEXT;
					} else {
						if (state == DOLLAR) {
							clearBuf.append("$");
							state = TEXT;
						}

						clearBuf.append(charRead);
					}
					break;
				default :
					if (state == FIELDNAME) {
						fieldBuf.append(charRead);
					} else {
						if (state == DOLLAR) {
							clearBuf.append("$");
							state = TEXT;
						}
						clearBuf.append(charRead);
					}
					break;
			}
		}

		if (clearBuf.length() > 0) {
			vCompiled.addElement(
				new HTMLTemplateItem(clearBuf.toString(), true));
		}

		clearBuf.setLength(0);
		clearBuf = null;
		fieldBuf.setLength(0);
		fieldBuf = null;
		sBuf.setLength(0);
		sBuf = null;
	}

	private void parseFile() throws IOException {
		char cBuf[] = new char[4096];
		int cLen = 0;
		FileReader fr;

		StringBuffer sBuf = new StringBuffer();

		try {
			fr = new FileReader(sFName);
		} catch (FileNotFoundException e) {
			throw new IOException("Cannot find file:" + sFName);
		}

		while ((cLen = fr.read(cBuf)) > 0) {
			for (int c = 0; c < cLen; c++) {
				sBuf.append(cBuf[c]);
			}
		}

		fr.close();

		parse(sBuf);
	}

}
