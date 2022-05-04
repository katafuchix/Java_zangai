package jp.co.lastminute.cart.mailsender;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import jp.co.lastminute.cart.model.Order;
import jp.co.lastminute.cart.model.Sub_Order;
import jp.co.yobrain.util.*;

import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.mailsender.MailSender;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;

import jp.co.lastminute.supply.Agent;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CancellMailSender implements Serializable {
	private static String xmlfile = Property.xmlfile;
	private static String xmlfileuser = Property.xmlfileuser;
	private Sub_Order suborder = null;
	private DataSource dss = null;

	//パラメータ
	private String email = "";
	private String seding_name = "";
	////
	private static String LMJ_SUPPLY = Property.LMJ_SUPPLY;
	//"";s.kondo@lastminute.co.jp
	private static String LMJ_CUSTOMER = Property.LMJ_CUSTOMER;
	//"";s.kondo@lastminute.co.jp
	private static String LMJ_COPY = Property.LMJ_COPY;
	//"";s.kondo@lastminute.co.jp
	private static String CLIENT_CLASS = Property.CLIENT_CLASS;
	private static String AGENT_CLASS = Property.AGENT_CLASS;

	private static String templateMailPath = Property.BASE_DIR + "/cancell/";
	/** Creates new ConsumerMailSender */
	public CancellMailSender() {
	}
	/**
	 * 	初期化
	 */
	public void init(
		Sub_Order suborder,
		String email,
		String seding_name,
		DataSource dss) {
		this.suborder = suborder;
		this.seding_name = seding_name;
		this.email = email;
		this.dss = dss;
	}
	/**
	 * メール送信
	 */
	synchronized public void MailSend() {
		//メール送信先の取得
		String agt_cd = suborder.getAGT_CD();
		if( suborder.getPRODUCT_TYPE_CD() == Constants.Gift_) agt_cd = "LMJ";
		String xsltPath = templateMailPath + suborder.getPRODUCT_TYPE_CD() + "_" + agt_cd + this.xmlfile;
		String xsltPathUser = templateMailPath + suborder.getPRODUCT_TYPE_CD() + "_" + agt_cd + this.xmlfileuser;
		String title = "";
		String body = "";
		String mailcomment = "";
		String agent_mail = "";
		Agent agent = null;
		String xml = "";
		try{
			agent = AgentArrayHelper.getAgent( suborder.getAGT_CD(), this.dss );
			xml = "<AGENT_INFO>" +  agent.getFIRST_NAME() + " " + agent.getLASTNAME() + "</AGENT_INFO>"
				 + "<SENDING_NAME>" + seding_name + "</SENDING_NAME>"
				 + suborder.getXMLdocument();
			body = AgentArrayHelper.getparseString(xml, xsltPath);		
			title = body.substring(0, body.indexOf("\n"));
			body = body.substring(body.indexOf("\n"));
			body += "\n" + AgentArrayHelper.footer_Str;
			agent_mail = agent.getE_MAIL();
			//エージェントメール送信
			MailSender mail_sup = new MailSender();
			mail_sup.SendMail( this.LMJ_SUPPLY, 
							   agent_mail, 
							  "",
							  this.LMJ_COPY, 
							  title, 
							  body);
			mail_sup = null;
		//クライアントメールの送信
			body = AgentArrayHelper.getparseString(xml, xsltPathUser);
			title = body.substring(0, body.indexOf("\n"));
			body = body.substring(body.indexOf("\n"));
			body += "\n" + AgentArrayHelper.footer_Str;
			MailSender mail_user = new MailSender();
			mail_user.SendMail( this.LMJ_CUSTOMER,
								email,
								"",
								this.LMJ_COPY,
								title,
								body);
			mail_user = null;
		}catch(Exception ex){	ex.printStackTrace();	}
	}
}
