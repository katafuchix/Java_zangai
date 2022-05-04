package jp.co.lastminute.maintenance.common;

import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MapEntry {
	public long lastModified = System.currentTimeMillis();
	public Object DBO;

	MapEntry(long lastmodified, Object DBO) {
		this.lastModified = lastModified;
		this.DBO = DBO;
	}
}
