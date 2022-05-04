package jp.co.yobrain.util.jdbc;

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
public final class Sqlmaker {
	public static String strPrintf(String format, String[] values){
		String buf = "";
		int ptr1 = 0, ptr2 = 0;
		for(int i1 = 0; i1 < values.length; i1++){
			ptr2 = format.substring(ptr1).indexOf("$" + (i1 + 1));
			if(ptr2 == -1) continue;
			if(ptr2 > 0) buf += format.substring(ptr1, ptr1 + ptr2);
			ptr1 += ptr2 + 2;
			buf += values[i1];
		}
		if(ptr1 < format.length()) buf += format.substring(ptr1);
		return buf;
	}
}
