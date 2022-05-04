package jp.co.lastminute.cart.mailsender;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.lastminute.cart.model.Order;
import jp.co.lastminute.cart.model.Sub_Order;
import jp.co.yobrain.util.*;
import jp.co.lastminute.cart.mailsender.MailSender;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CartMailSender implements Serializable {
	private Order order = null;
	private Profile profile = null;
	private DataSource dss = null;

	//パラメータ
	////
	private static String LMJ_SUPPLY = Property.LMJ_SUPPLY;
	//"";s.kondo@lastminute.co.jp
	private static String LMJ_CUSTOMER = Property.LMJ_CUSTOMER;
	//"";s.kondo@lastminute.co.jp
	private static String LMJ_COPY = Property.LMJ_COPY;
	//"";s.kondo@lastminute.co.jp
	private static String CLIENT_CLASS = Property.CLIENT_CLASS;
	private static String AGENT_CLASS = Property.AGENT_CLASS;

	/** Creates new ConsumerMailSender */
	public CartMailSender() {
	}
	/**
	 * 	初期化
	 */
	public void init(
		Order order,
		Profile profile,
		DataSource dss) {
		this.order = order;
		this.profile = profile;
		this.dss = dss;
	}
	/**
	 * メール送信
	 */
	synchronized public void MailSend() {
		//メール送信先の取得
		Vector Agents = new Vector();
		Vector Clients = new Vector();
		Agents = AgentArrayHelper.AddAgentArray( Agents, this.order, this.dss, AGENT_CLASS );
		Clients = AgentArrayHelper.AddAgentArray( Clients, this.order, this.dss, CLIENT_CLASS );
		
		//エージェントメール送信
		try{
			for(int index=0; index<Agents.size(); index++){	
				MailArray mailarray = (MailArray)Agents.get( index );
				MailSender mail_sup = new MailSender();
				mail_sup.SendMail( this.LMJ_SUPPLY,
								  mailarray.getMailaddress(), 
								  "",
								  this.LMJ_COPY, 
								  mailarray.getMailtitle(), 
								  mailarray.createBody() );
				mail_sup = null;
				makeString(	mailarray.getOrder().getORDER_NO(), 
							mailarray.getSub_order_no(),
							mailarray.getSending_email(),
							mailarray.getAgt_cd(),
							mailarray.getMailtitle(),
							mailarray.createBody());
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		//クライアントメールの送信
		try{
			for(int index=0; index<Clients.size(); index++){
				MailArray mailarray = (MailArray)Clients.get( index );
				MailSender mail_user = new MailSender();
				mail_user.SendMail( this.LMJ_CUSTOMER,
									mailarray.getSending_email(), 
									"",
									this.LMJ_COPY,
									mailarray.getMailtitle(),
									mailarray.createBody());
				mail_user = null;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	/**
	 * メールトラッカー穂族文
	 */
	synchronized public void makeString(
		int order_no,
		int sub_order_no,
		String e_mail,
		String agt_cd,
		String title,
		String body) {
		String reStr = "";
		try {
			reStr =	"[Order No]" + order_no	+ "\n"
				　+ "[SubOrder No]"+ sub_order_no　+ "\n"
				　+ "[Agt Cd]"　+ agt_cd　+ "\n"
				　+ "[User Email]"+ e_mail+ "\n"
				　+ "[Status X] O\n"
				　+ "[Description] "+ body;
			String mail =
				order_no+ "-"+ sub_order_no	+ "_"+ agt_cd+ "_asp_order@mls.lastminute.co.jp";
			MailSender mail_track = new MailSender();
			mail_track.SendMail( e_mail, mail, "", title, reStr);
			mail_track = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
