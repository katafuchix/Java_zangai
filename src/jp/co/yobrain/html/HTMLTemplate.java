package jp.co.yobrain.html;

import java.io.*;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HTMLTemplate {
	private Properties pList = null;
	private String sFname = null;
	private HTMLCompiledTemplate oTemplate = null;

	public HTMLTemplate(String fName) {
		pList = new Properties();
		sFname = fName;
		oTemplate = null;
	}

	protected HTMLTemplate(HTMLCompiledTemplate ct) {
		pList = new Properties();
		sFname = null;
		oTemplate = ct;
	}

	public void substitute(String var, String str) {
		if (var != null) {
			if (str != null) {
				pList.put(var.toUpperCase(), str);
			} else {
				pList.put(var.toUpperCase(), "");
			}
		}
	}

	public String toString() {
		try {
			return toStringBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public StringBuffer toStringBuffer() throws IOException {
		StringBuffer sb = new StringBuffer();
		return this.toStringBuffer(sb);
	}

	public StringBuffer toStringBuffer(StringBuffer sb) throws IOException {
		if (oTemplate == null) {
			oTemplate = new HTMLCompiledTemplate(sFname);
		}

		Vector v = oTemplate.getCompiledVector();

		for (Enumeration e = v.elements(); e.hasMoreElements();) {
			HTMLTemplateItem ti = (HTMLTemplateItem) e.nextElement();

			if (ti.bClearText) {
				sb.append(ti.sText);
			} else {
				String parm = pList.getProperty(ti.sText);

				if (parm == null) {
					parm = "";
				}

				sb.append(parm);
			}
		}

		return sb;
	}

	public void doHTML(PrintWriter out) throws IOException {
		out.print(this.toString());
		out.flush();
	}

}
