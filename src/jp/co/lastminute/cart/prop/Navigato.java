package jp.co.lastminute.cart.prop;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Navigato {
	public int isThrow();
	public String getMemberInfoClass();
	public String getSendingClass();
	public String getCardAuthClass();
	public String getCancellClass();
	public String getAllotCheckClass();
	public String getAllotReturnClass();
	public String getFinalCancellClass();
	public String getMemberUpdateClass();
	public String getSendingUpdateClass();
	public String getSubOrderCheckClass();
	public String getSubOrderUpdateClass();
	public int isMember();
	public int isSending();
	public int isCardAuth();
	public int isFax();
	public int isModify();
	public String getBookingClass();
}
