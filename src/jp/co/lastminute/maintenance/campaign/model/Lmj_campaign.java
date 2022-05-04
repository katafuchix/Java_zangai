package jp.co.lastminute.maintenance.campaign.model;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import jp.co.yobrain.util.form.*;
import jp.co.lastminute.maintenance.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Lmj_campaign extends Models implements Serializable{

	public int lmjcampaign = 0;
	public String campaign_name = "";
	
	/**
	 * コンストラクター
	 */
	public Lmj_campaign(){error_flg = new int[ 2 ];
	}
	/**
	 * 追加用
	 */
	public Lmj_campaign getNewReq( HttpServletRequest req ){
		clear();
		this.campaign_name 	= checkStr( req, "campaign_name", 1, 1, true );
		return this;
	}
	/**
	 * 更新用
	 */
	public Lmj_campaign getRequest( HttpServletRequest req ){
		clear();
		this.lmjcampaign = checkInt( req, "lmjcampaign", 0, 0 ,true );
		this.campaign_name = checkStr( req, "campaign_name", 1, 1 ,true );

		return this;
	}
}
