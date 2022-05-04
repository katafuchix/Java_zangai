package jp.co.yobrain.html;

import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TemplateCacheList {
	private Hashtable m_list;

	public TemplateCacheList() {
		m_list = new Hashtable();
	}

	public void add(String templateName, HTMLCompiledTemplate ct) {
		m_list.put(templateName, ct);
	}

	public void delete(String templateName) {
		m_list.remove(templateName);
	}

	public HTMLCompiledTemplate get(String templateName)
		throws IllegalArgumentException {
		HTMLCompiledTemplate oTemplate =
			(HTMLCompiledTemplate) m_list.get(templateName);

		if (oTemplate == null) {
			throw new IllegalArgumentException("Template not found in list.");
		}

		return oTemplate;
	}

	public int getCount() {
		return m_list.size();
	}

}
