package jp.co.lastminute.cart.mailsender;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.model.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface MailArray {
	public boolean init( Order order, Sub_Order suborder, DataSource dss );
	public boolean isGoodToAdd( Sub_Order suborder);
	public String createBody();
	public boolean addSuborder( Sub_Order suborder, String XsltPath );
	public String getFax();
	public String getMailaddress();
	public String getMailtitle();
	public Order getOrder();
	public int getSub_order_no();
	public String getAgt_cd();
	public String getSending_email();
	public String getXsltpath( Sub_Order suborder );
}
