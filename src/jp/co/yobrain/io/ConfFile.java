package jp.co.yobrain.io;

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
public class ConfFile {
	private Properties pProps;
	private String sFName;

	/**
	 * Constructor
	 *
	 * @param fName    The path and name of the configuration file
	 */
	public ConfFile(String fName) {
		pProps = new Properties();
		sFName = fName;
	}

	/**
	 * Loads the key/value pairs into the object
	 */
	public void load() throws IOException {
		FileInputStream fis;

		try {
			fis = new FileInputStream(sFName);
		} catch (FileNotFoundException e) {
			throw new IOException("Cannot open file:" + sFName);
		}

		pProps.load(fis);

		fis.close();
	}

	/**
	 * Saves the key/value pairs to a conf file.
	 */
	public void save() throws IOException {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(sFName);
		} catch (FileNotFoundException e) {
			throw new IOException("Cannot create file:" + sFName);
		}

		pProps.store(fos, "");

		fos.close();
	}

	/**
	 * Gets a value from a key
	 *
	 * @param sKey     A string containing the name of the key to query.
	 * @return         Value the corresponds to the key.
	 */
	public String getValue(String sKey) {
		return pProps.getProperty(sKey);
	}

	/**
	 * Sets/replaces a key/value pair for the conf file
	 *
	 * @param sKey     A string containing the name of the key to store.
	 * @param sValue   A string containing the value to store.
	 * @return
	 */
	public void putValue(String sKey, String sValue) {
		pProps.put(sKey, sValue);
	}

	/**
	 * Gets all key values in the conf file
	 *
	 * @return Enumeration of keys.
	 */
	public Enumeration getKeys() {
		return pProps.propertyNames();
	}

}
