package jp.co.lastminute;

import javax.sql.*;
import java.util.*;
import jp.co.lastminute.cart.model.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface dbProductAdapter {
	public void init(DataSource ds) ;
	public String getLIst( Object obj );
	public String getDetail( Object obj );
	public int checkAllotment( Object obj );
	public void returnAllotment( Object obj, int allot );
	public boolean getAllotment( Object obj, int allot );
	public boolean cancellOrder( int suborder );
	public boolean memberUpdate( Sub_Order suborder);
	public boolean suborderUpdate( Sub_Order suborder);
	public boolean sendingUpdate( Sub_Order suborder, Product_Send sending );
	public Sub_Order bookingOrder( Sub_Order suborder,HashMap xmlhash);
	public String confirmOrder( Sub_Order suborder );
}
