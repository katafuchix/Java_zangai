package jp.co.lastminute.maintenance.util;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class CharacterConverter {
	public static String toUTF_8(String s) {
		try {
			if (s == null)
				return null;
			else
				return new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return "";
		}

	}
	/**
	 * リプレース
	 */
	public static String replaceStr(String source, String ch1, String ch2 ) {
		return source.replaceAll( ch1 , ch2 );
	}
	/**
	 * 文字列を変更する
	 */
	public static String removeChar(String source ) {
		return removeChar(source, '"', "");
	}
	public static String removeChar(String source, char removeChar) {
		return removeChar(source, removeChar, "");
	}
	public static String removeChar( String source, char removeChar, String def) {
		if( source == null )	return "";
		if( source.length() == 0 )	return "";
		char ac[] = source.toCharArray();
		String s1 = "";
		for (int i = 0; i < ac.length; i++)
			if (ac[i] != removeChar)
				s1 = s1 + ac[i];
			else
				s1 = s1;
		if (s1.trim().length() == 0)
			return def;
		else
			return s1.trim();
	}
	public static String toJISAutoDetect(String s) {
		try {
			if (s == null){
				return null;
			}else{
				return new String(s.getBytes("ISO-8859-1"), "JISAutoDetect");
			}
		} catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
			return "";
		}
	}
	public static String xmlEncodeMono( String text, String base, String to ){
		if( text != null  &&  text.length() != 0){
			String reStr = "";
			boolean loop = true;
			while( loop ){
				int posion = text.indexOf( base );
				if( posion >= 0 ){
					String front = text.substring( 0, posion );
					String temp = text.substring( posion + base.length() );
					reStr = front + to + temp;
				}else{
					loop = false;
				}
			}
			return reStr;
		}
		return "";
	}
	public static String xmlEncode( String text ){
		text = xmlEncodeMono( text, "&amp;", "&" );
		text = xmlEncodeMono( text, "&yen;", "\\" );
		text = xmlEncodeMono( text, "&quot;", "\"" );
		text = xmlEncodeMono( text, "&lt;", "<" );
		text = xmlEncodeMono( text, "&gt;", ">" );
		text = xmlEncodeMono( text, "&rsquo;", "\'" );
		return text;
	}
	
	
	public static String htmlPreEncode(String text) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			switch (text.charAt(i)) {
				case '&' :
					buff.append("&amp;");
					break;
				case '\'' :
					buff.append("&rsquo;");
					break;
				case '"' :
					buff.append("&quot;");
					break;
				case '<' :
					buff.append("&lt;");
					break;
				case '>' :
					buff.append("&gt;");
					break;
				case '\\' :
					buff.append("&yen;");
					break;
				default :
					buff.append(text.charAt(i));
					break;
			}
		}
		return buff.toString();
	}

}
