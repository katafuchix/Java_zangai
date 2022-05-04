package jp.co.yobrain.common;

import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MethodList {
	/**
	 * User Defined Parameter Object
	 */
	private Properties m_list;

	/**
	 * Default constructor.
	 */
	public MethodList() {
		m_list = new Properties();
	}

	/**
	 * Adds a method key/value pair to the list.
	 *
	 * @param methodName    Name of the method.
	 * @param methodClass   The full qualified class name to return.
	 */
	public void add(String methodName, String methodClass) {
		m_list.put(methodName, methodClass);
	}

	/**
	 * Removes a method key/value pair from the list.
	 *
	 * @param methodName    Name of the method.
	 */
	public void delete(String methodName) {
		m_list.remove(methodName);
	}

	/**
	 * Retrieves a class name from a passed in method name.
	 *
	 * @param methodName    Name of the Method.
	 * @return Corresponding class name for the method.
	 * @exception IllegalArgumentException if method not found in list.
	 */

	public String get(String methodName) throws IllegalArgumentException {
		String sRet = (String) m_list.get(methodName);
		if (sRet == null)
			throw new IllegalArgumentException("Method not found in list.");

		return sRet;
	}

	/**
	 * Retreives the number of methods in the list.
	 *
	 * @return int representing the number of methods in the list.
	 */

	public int getCount() {
		return m_list.size();
	}

}
