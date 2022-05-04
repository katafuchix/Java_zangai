package jp.co.lastminute.cart.prop;

import jp.co.lastminute.cart.model.Sub_Order;
import jp.co.lastminute.cart.Constants;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Ticket implements Navigato{
	private int flow = Constants.IS_THROW_NG;
	private int cards = Constants.REAL_SALE_;
	private int fax = Constants.IS_FAX_PASS;
	private int sending = Constants.IS_SENDING_NEED;
	private int member = Constants.IS_MEMBER_PASS;
	private int modify = Constants.IS_MODIFY_OK;

	public Ticket(){}
	public int isThrow(){
		return flow;
	}
	public String getMemberInfoClass(){
		return "";
	}
	public String getSendingClass(){
		return "jp.co.lastminute.cart.check.sending.SendingCheck";
	}
	public String getBookingClass(){
		return "";
	}
	public int isMember(){
		return member;	
	}
	public int isSending(){
		return sending;
	}
	public int isCardAuth(){
		return cards;
	}
	public int isFax(){
		return fax;
	}
	public int isModify(){
		return modify;
	}
	public String getFinalCancellClass(){
		return "jp.co.lastminute.Entertainment.jdbc.dbAdapterEntertainment";
	}
	public String getCancellClass(){
		return "jp.co.lastminute.cart.check.DelCheck";
	}
	public String getAllotCheckClass(){
		return "jp.co.lastminute.Entertainment.jdbc.dbAdapterEntertainment";
	}
	public String getAllotReturnClass(){
		return "jp.co.lastminute.Entertainment.jdbc.dbAdapterEntertainment";
	}
	public String getCardAuthClass(){
		return "jp.co.lastminute.cart.check.CardCheck";
	}
	public String getMemberUpdateClass(){
		return "jp.co.lastminute.Entertainment.jdbc.dbAdapterEntertainment";
	}
	public String getSendingUpdateClass(){
		return "jp.co.lastminute.Entertainment.jdbc.dbAdapterEntertainment";
	}
	public String getSubOrderCheckClass(){
		return "jp.co.lastminute.cart.check.ModifyPax";
	}
	public String getSubOrderUpdateClass(){
		return "jp.co.lastminute.Entertainment.jdbc.dbAdapterEntertainment";
	}
}
