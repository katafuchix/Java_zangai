package jp.co.yobrain.util.text;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class StringUtil {

	/**
	 * •¶Žš—ñ‚Ì’uŠ·
	 */
	public static String replaceInString(String in, String from, String to) {
		if (in == null || from == null || to == null) {
			return in;
		}

		StringBuffer newValue = new StringBuffer();
		char[] inChars = in.toCharArray();
		int inLen = inChars.length;
		char[] fromChars = from.toCharArray();
		int fromLen = fromChars.length;

		for (int i = 0; i < inLen; i++) {
			if (inChars[i] == fromChars[0] && (i + fromLen) <= inLen) {
				boolean isEqual = true;
				for (int j = 1; j < fromLen; j++) {
					if (inChars[i + j] != fromChars[j]) {
						isEqual = false;
						break;
					}
				}
				if (isEqual) {
					newValue.append(to);
					i += fromLen - 1;
				} else {
					newValue.append(inChars[i]);
				}
			} else {
				newValue.append(inChars[i]);
			}
		}
		return newValue.toString();
	}
	/**
	 * •¶Žš—ñ‚Ìíœ
	 */
	public static String removeChar(String source, char removeChar) {
		char ac[] = source.toCharArray();
		String s1 = "";
		for (int i = 0; i < ac.length; i++)
			if (ac[i] != removeChar)
				s1 = s1 + ac[i];
			else
				s1 = s1;

		return s1.trim();
	}

	public static String convertString2xml(String str) {
		if (str == null)
			return null;
		else {
			String returnStr = replaceInString(str, "&", "&amp;");
			return replaceInString(returnStr, "<", "&lt;");
		}
	}
	/**
	 * ”z—ñ‚É•ÏŠ·‚·‚éB
	 */
	public static String[] split(String str, String delim) {
		// Use a List to hold the splittee strings
		List store = new ArrayList();

		// Use a StringTokenizer to do the splitting
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		while (tokenizer.hasMoreTokens()) {
			store.add(tokenizer.nextToken());
		}

		String[] ret = new String[store.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = (String) store.get(i);
		}

		return ret;
	}
}
