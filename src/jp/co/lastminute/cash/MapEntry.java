package jp.co.lastminute.cash;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MapEntry {
	public MapEntry(){}
	public long lastModified = System.currentTimeMillis();
	public String xmlString;

	public MapEntry(long lastmodified, String xmlString) {
		this.lastModified = lastModified;
		this.xmlString = xmlString;
	}
}
