package jp.co.yobrain.html;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class HTMLTemplateItem {
	// Is the sText Unaltered Text or a field name
	// to be translated ??
	public boolean bClearText = false;

	// Either clear text to be passed or a field name.
	public String sText = null;

	public HTMLTemplateItem(String text, boolean clearText) {
		sText = text;
		bClearText = clearText;
	}
}
