package jp.co.lastminute.cart.check.member;

import jp.co.lastminute.cart.members.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Checkmember {
	public boolean Check();
	public void setMember( Member member);
	public String getHtml( Node node );
	public String getPreView( Node node );
	public String getArrayHtml( Node node, String prevew );
	public String getHtml( Node node, boolean delaction );
	public static final int parent_target = 10;
	public static final int infant_target = 100;
}
