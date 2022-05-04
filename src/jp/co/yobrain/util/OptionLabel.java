/*
 * OptionLabel.java
 *
 * Created on 2002/04/28, 17:59
 */

package jp.co.yobrain.util;

/**
 *
 *
 * @author  skondo　AND　 BASICMADEBY？
 * @version 
 */
import java.io.Serializable;


public class OptionLabel implements Serializable {

	private String label = null; // label displayed to the user
	private String value = null; // value returned to the server


	/**
         * コンストラクター
	 */
	public OptionLabel(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	/** Return a string representation of this object. */
	public String toString() {
		StringBuffer sb = new StringBuffer("OptionLabelValue[");
		sb.append(this.label);
		sb.append(", ");
		sb.append(this.value);
		sb.append("]");
		return (sb.toString());
	}
}

