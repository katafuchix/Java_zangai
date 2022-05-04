package jp.co.lastminute.maintenance.common;

import java.io.*;
import java.util.*;

import java.sql.SQLException;
import javax.sql.DataSource;

import jp.co.lastminute.maintenance.producthandle.CommandConstants;
import jp.co.lastminute.maintenance.producthandle.CommandException;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CommonDBOCache {
	private Map cache = new HashMap();
	public static int refreshDuration = 10;
	private int product_type = 0;

	public synchronized void flushAll() {
		cache.clear();
	}

	public synchronized void flush(String productype) {
		cache.remove( productype );
	}
	public void setMapEntry(MapEntry mapEntry, String productype ){
		cache.put( productype, mapEntry );
	}

	public synchronized MapEntry getDBO(
		String productype)
		throws CommandException {
		return getDBO(CommonDBOCache.refreshDuration, productype);
	}

	public synchronized MapEntry getDBO(
		int refreshDuration,
		String productype )
		throws CommandException {
		MapEntry entry = (MapEntry) cache.get( productype );

		if (entry != null) {
			long lastModifiedLong = entry.lastModified;
			long nowLong = System.currentTimeMillis();
			long refreshTimeLong =	getRefreshTime(lastModifiedLong, refreshDuration);
			if (refreshTimeLong < nowLong) {
				entry = null;
			}
		}
		return entry;
	}

	public static long getRefreshTime(
		long lastModifiedLong,
		int refreshDuration) {
		return lastModifiedLong + (refreshDuration * 60 * 1000);
	}
	public CommonDBOCache() {
	}

}
