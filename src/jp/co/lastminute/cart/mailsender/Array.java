package jp.co.lastminute.cart.mailsender;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import jp.co.lastminute.supply.Agent;
import jp.co.lastminute.supply.jdbc.*;

import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.model.*;

import jp.co.yobrain.util.ParseFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Array implements Serializable{
	protected String xmlfile = "";
	//////////////////////////////////////
	protected String agt_cd = "";
	protected int product_type_cd = 0;
	protected int sub_order_no = 0;
	protected String sending_email = "";
	//////////////////////////////////////
	protected String mailaddress = "";
	protected String mailbody = "";
	protected String mailsending = "";
	protected String mailcomment = "";
	protected String mailtitle = "";
	//////////////////////////////////////
	protected String fax = "";
	//////////////////////////////////////
	protected int price	= 0;
	protected int tax 	= 0;
	protected int sending_tax	= 0;
	protected int sending 		= 0;
	//////////////////////////////////////
	protected String mailhedder = "";
	protected String mailfooter = "";
	//////////////////////////////////////
	protected Order order = null;
	protected Vector bodys = null;
	protected DataSource dss = null;
	
	private ParseFormat pf = null;
	/**
	 * コンストラクタ
	 */
	public Array(){
		this.bodys = new Vector();
	}
	/**
	 * コンペア
	 */
	synchronized public boolean isGoodToAdd( Sub_Order suborder){
		if(( suborder.getPRODUCT_TYPE_CD() == this.product_type_cd )&&
			( suborder.getAGT_CD().equals( this.agt_cd ))){
				return true;
		}
		return false;
	}
	/**
	 * サブオーダーの追加
	 */
	synchronized public boolean addSuborder( Sub_Order suborder, String xsltPath ){
		String body = parseBody( suborder, xsltPath );
		if( body.length() == 0 )	return false;
		this.bodys.add( body );
		this.price += suborder.getPrices();
		this.tax += suborder.getTax();
		this.sending += suborder.getSending();
		this.sending_tax += suborder.getSending_tax();
		return true;
	}
	synchronized protected String getCalcString(){
		if( this.tax != 0 ){
		return "\n---ご請求金額-----------------------------------------------\n"
			  + "小計：\\" + pf.ToPriceFormat( this.price ) + "\n"
			  + "税　: \\" + pf.ToPriceFormat( this.tax ) + "\n"
			  + "合計 :\\" + pf.ToPriceFormat( this.price + this.tax ) + "\n"
			  + "お引落し日は、カード会社によって異なります。\n"
			  + "------------------------------------------------------------\n";
		}
		return "\n---ご請求金額-----------------------------------------------\n"
			  + "合計 :\\" + pf.ToPriceFormat( this.price ) + "(送料・税金込)\n"
			  + "お引落し日は、カード会社によって異なります。\n"
			  + "------------------------------------------------------------\n";
	} 
	/**
	 * サブオーダーのパージング
	 */
	synchronized protected String parseBody( Sub_Order suborder, String xsltPath ){
		String reStr = "";
		try{
			String xml = suborder.getXMLdocument();
			return AgentArrayHelper.getparseString( suborder.getXMLdocument(), xsltPath );
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 配送先のパージング
	 */
	synchronized　protected void parseSending( Order order, Agent agent, String xsltPath){
		String tempStr = "";
		Product_Send product_send = order.getProductSend(0);
		try{
			tempStr = AgentArrayHelper.getparseString( "<AGENT_INFO>"+ agent.getFIRST_NAME() + " " + agent.getLASTNAME() +"</AGENT_INFO>\n" + product_send.getXMLdocument(), xsltPath );
			this.mailtitle = tempStr.substring(0, tempStr.indexOf("\n"));
			tempStr = tempStr.substring(tempStr.indexOf("\n"));
			int point = tempStr.indexOf( AgentArrayHelper.DEMIRITER_STR );
			if( point != -1 ){	
				this.mailhedder += tempStr.substring(0, point);
				this.mailsending = tempStr.substring( point+AgentArrayHelper.DEMIRITER_STR.length() );
			}else{
				this.mailsending = tempStr;
			}
			this.sending_email = product_send.getE_MAIL();
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	/**
	 *　エージェントの取得
	 */
	synchronized protected Agent getAgent(String agt_cd) {
		return getAgent( agt_cd, this.dss );
	}
	/**
	 * エージェントの取得
	 */
	synchronized protected Agent getAgent(String agt_cd, DataSource dss ) {
		return AgentArrayHelper.getAgent( agt_cd, dss );
	}
	/**
	 * Returns the fax.
	 * @return String
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Returns the mailaddress.
	 * @return String
	 */
	public String getMailaddress() {
		return mailaddress;
	}
	/**
	 * Returns the mailtitle.
	 * @return String
	 */
	public String getMailtitle() {
		return mailtitle;
	}

	/**
	 * Returns the order.
	 * @return Order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Returns the sub_order_no.
	 * @return int
	 */
	public int getSub_order_no() {
		return sub_order_no;
	}

	/**
	 * Returns the agt_cd.
	 * @return String
	 */
	public String getAgt_cd() {
		return agt_cd;
	}

	/**
	 * Returns the sending_email.
	 * @return String
	 */
	public String getSending_email() {
		return sending_email;
	}
}
