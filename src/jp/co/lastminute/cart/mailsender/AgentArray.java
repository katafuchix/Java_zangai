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
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AgentArray extends Array implements MailArray, Serializable{
	protected static final String xmlfile = Property.xmlfile;
	/**
	 * コンストラクタ
	 */
	public AgentArray(){
		this.bodys = new Vector();
	}
	public boolean init( Order order, Sub_Order suborder, DataSource dss ){
		this.order = order;
		this.addSuborder( suborder, getXsltpath( suborder ));
		//this.bodys.add( suborder );
		this.dss = dss;
		this.product_type_cd = suborder.getPRODUCT_TYPE_CD();
		if( product_type_cd != Constants.Gift_ ){
			this.agt_cd = suborder.getAGT_CD();
		}else{
			this.agt_cd = "LMJ";	
		}
		this.sub_order_no = suborder.getSUB_ORDER_NO();
		Agent agent = getAgent( suborder.getAGT_CD() );
		if( agent == null)	return false;
		this.mailaddress = agent.getE_MAIL();
		this.fax = agent.getFAX();
		this.mailcomment = agent.getMAILCOMMENT();
		int point = this.mailcomment.indexOf( AgentArrayHelper.DEMIRITER_STR );
		if( point != -1 ){
			this.mailhedder = this.mailcomment.substring(0, point);
			this.mailcomment = "";
		}
		this.mailcomment = "";
		parseSending( order , agent, getFooterXsltpath( suborder ));	//フッターの制作
		return true;
	}
	/**
	 * メール本文の生成
	 */
	synchronized public String createBody(){
		String reStr = "";
		reStr += this.mailhedder;
		try{
			for(int i=0; i<bodys.size(); i++){			
				String tempbody = (String)bodys.get(i);	
				if( tempbody.length() > 0 ){
					this.mailbody += AgentArrayHelper.Otder_DEMI + tempbody;
				}
			}
			reStr += this.mailbody;
		}catch(Exception ex){	ex.printStackTrace();	}
		reStr += getCalcString()
			  + this.mailsending + "\n"
			  + AgentArrayHelper.footer_Str;
		return reStr;
	}
	/**
	 * メールXSLTファイルの取得
	 */
	synchronized public String getXsltpath( Sub_Order suborder  ){
		return AgentArrayHelper.templateMailPath + suborder.getPRODUCT_TYPE_CD() + "_" + suborder.getAGT_CD() + xmlfile;
	}
	/**
	 * FooterXSLTファイルの取得
	 */
	synchronized public String getFooterXsltpath( Sub_Order suborder  ){
		return AgentArrayHelper.templateHeaderPath + suborder.getPRODUCT_TYPE_CD() + "_" + suborder.getAGT_CD() + xmlfile;
	}
}
