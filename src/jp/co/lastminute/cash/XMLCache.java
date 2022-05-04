package jp.co.lastminute.cash;

import java.io.*;
import java.util.*;

import java.sql.SQLException;
import javax.sql.DataSource;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class XMLCache {
	public boolean isReload = false;
	/**
	 * リスタートが必要か判断する
	 */
	public boolean mustReload(){
		return isReload;
	}
	//public final static org.apache.log4j.Category cat =
	//	org.apache.log4j.Category.getInstance("develop");

	public static Map cache = new HashMap();
	public static synchronized void flushAll() {
		cache.clear();
	}
	public static synchronized void flush(Object act) {
		cache.remove(act);
	}

	public static long getRefreshTime(
		long lastModifiedLong,
		long refreshDuration) {
		return lastModifiedLong + (refreshDuration * 60 * 1000);
	}
	public XMLCache() {
		super();
	}
}
