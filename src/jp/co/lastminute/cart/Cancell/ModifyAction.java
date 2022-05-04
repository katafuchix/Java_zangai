package jp.co.lastminute.cart.Cancell;

import java.io.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import jp.co.yobrain.util.*;
import jp.co.lastminute.*;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.jdbc.*;
import jp.co.lastminute.cart.prop.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.check.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.members.*;
import jp.co.lastminute.cart.mailsender.*;
import jp.co.lastminute.cart.user.model.*;
import jp.co.lastminute.cardauth.CardAuthClient;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.yobrain.util.jdbc.Sqlmaker;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ModifyAction extends Action{
	DataSource dss;
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		////////////////
		ActionForward actionforward = new ActionForward();
		////////////////
		HttpSession session = request.getSession();
		ServletContext servletContext = servlet.getServletContext();
		HashMap xmlhmap = (HashMap) servletContext.getAttribute(Constants.Product_Type_Mapping_);
		this.dss = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		try{
			ModifyForm modifyform = (ModifyForm)form;
			//認証の確認
			if( session.getAttribute( Constants.Cancell_SOrder ) == null){
				return (mapping.findForward("modifyfail"));
			}
			User user = (User)((CancellForm)session.getAttribute( Constants.Cancell_SOrder )).getUser();
			Sub_Order suborder = (Sub_Order)((CancellForm)session.getAttribute( Constants.Cancell_SOrder )).getSuborder();
			String base_suborder = "";
			//////tempPoraryのサブオーダーを作成する////////
			Product_Send temp_sending = null;
			modifyform.setTemp_suborder( suborder );
			modifyform.setUser( user );
			//ベースの確保
			if( modifyform.getSuborder() == null ){
				modifyform.setSuborder( suborder );
			}
			//////起動するクラスファイルを見つける
			Navigato navi = (Navigato)suborder.getActionclass();
			
			/////メンバーを変更する場合////チェックルーチンを出力
			String chkclassname = "";
			String actclassname = "";
			boolean member_flg = false;
			int update_target = 0;
			System.err.println("<---- Action_flg SI " + modifyform.getAction_type()   + "   ---->");
			if( modifyform.getAction_type().equals( Constants.MEMBER_UPDATE ) ){
				System.err.println("<---- Member update Action_flg ---->");
				chkclassname = navi.getMemberInfoClass();
				actclassname = navi.getMemberUpdateClass();
				member_flg = true;
				update_target = 1;
			}else if( modifyform.getAction_type().equals( Constants.SENDING_UPDATE)){
				/////配送先を変更する場合///////
				System.err.println("<---- Sending update Action_flg ---->");
				chkclassname = navi.getSendingClass();
				actclassname = navi.getSendingUpdateClass();
				temp_sending = modifyform.getSending();
				modifyform.setTemp_sending( temp_sending );
				update_target = 2;
			}else if( modifyform.getAction_type().equals( Constants.COUNTER_UPDATE)){
				System.err.println("<---- Counter update Action_flg ---->");
				/////数量を変更する場合///////
				chkclassname = navi.getSubOrderCheckClass();
				actclassname = navi.getSubOrderUpdateClass();
				update_target = 3;
			}else{	///メンバーが指定できない場合は、エラー
				System.err.println("<---- No update Action_flg ---->");
				return (new ActionForward(mapping.getInput()));
			}
			/////内容の確認///////////////
			int check_flg = 0;
			if( modifyform.getPreviewflg().equals( Constants.PREVIEW )){
				check_flg = 1;
			}else if( modifyform.getPreviewflg().equals( Constants.UPDATE_ACTION )){
				check_flg = 2;
			}else{
				//アクションが指定されていないので、INPUTへ	
				return (new ActionForward(mapping.getInput()));
			}
			System.err.println("<---- check_flg IS " + check_flg  + "   ---->");
			/////チェックルーチン///////////////////////////
			
			if( member_flg ){	System.err.println("<---- Member UP IS   ---->");
				Memberhandler memberhandler = new Memberhandler( chkclassname );
				memberhandler.init(  suborder.getPax(), suborder.getInfant() );
				System.err.println("<----Pax= " + suborder.getPax() );
				System.err.println("<----InFabt= " + suborder.getInfant() );
				memberhandler.setMember( request, suborder.getSALES_DATE() );
				suborder.setMember( memberhandler.getMembers() );
				suborder.setPax( memberhandler.getParentsize() );
				modifyform.setTemp_suborder( suborder );
				if( !memberhandler.isReBoolean() ){	System.err.println("<---CartIn:145 Not Passwd--->");
					modifyform.addError_comm("メンバー登録の入力値に間違いがあります");
					ActionForward af = new ActionForward(mapping.getInput());
					modifyform.setPreviewflg( "" );
					memberhandler = null;
					return ( af );
				}
				memberhandler = null;
			}else{				System.err.println("<---- Others UP IS   ---->");
				CheckInterface infoform = 
						( CheckInterface )Class.forName( chkclassname ).newInstance();
					infoform.init( mapping, form, request, response ) ;
				////サブオーダーに変更を反映させなくてはいけない
				if( !infoform.CancellCheck()){
					modifyform = (ModifyForm)infoform.getForm();
					ActionForward af = new ActionForward(mapping.getInput());
					return ( af );
				}
				suborder = 	modifyform.getTemp_suborder();
			}
			/////プレビューだったらここでOUT INPUTに戻す/////
			if( check_flg == 1 ){	System.err.println("<---- Upend to Preview   ---->");
				modifyform.setPreviewflg( Constants.UPDATE_ACTION );
				return (mapping.findForward("preview"));
			}
			/////ベースデータシュトク//////
			base_suborder = createSubOrder( user.getUser().getE_MAIL(),
											user.getUser().getPASSWD(), 
											""+ suborder.getSUB_ORDER_NO() +"", xmlhmap).getXMLdocument();
			/////データベース更新//////////
			if( check_flg == 2 ){	System.err.println("<---- DB Upend to Ending   ---->");
				 if( !updateDatabase( suborder, temp_sending, actclassname, update_target) ){
				 	return (mapping.findForward("modifydamaged"));
				 }
			}			
			//////メール発送///////////////
			System.err.println("<---- Mail Sending   ---->");
				String seding_name = user.getProfile().getSEI_KANJI() + " " + user.getProfile().getNA_KANJI();
			ModifyMailSender modifyMailSender = new ModifyMailSender();
			modifyMailSender.init( suborder, 
								  base_suborder, 
								  user.getUser().getE_MAIL(), 
								  seding_name, this.dss );
			modifyMailSender.MailSend();
			
			System.err.println("<---- Mail Sending End  ---->");
			//////サクセスに遷移
			return (mapping.findForward("modifysuccess"));
		}catch(Exception ex){	ex.printStackTrace();	}
		return (mapping.findForward("modifyfail"));
	}
	synchronized private boolean updateDatabase( Sub_Order subs, Product_Send prsd, String actclassname, int update_target) throws Exception{
		Object obj = Class.forName( actclassname ).newInstance();
		dbProductAdapter dbproductAdapter = (dbProductAdapter)obj;
		dbproductAdapter.init( this.dss );
		boolean rebool = false;
		if( update_target == 1 ){
			rebool = dbproductAdapter.memberUpdate( subs );
		}
		if( update_target == 2 ){
			rebool = dbproductAdapter.sendingUpdate( subs, prsd );
		}
		if( update_target == 3){
			rebool = dbproductAdapter.suborderUpdate( subs );
		}
		dbproductAdapter = null;
		return rebool;
	}
	private Sub_Order createSubOrder( String id, String passward, String sub_order_no, HashMap xmlhmap) throws Exception{
		Vector result = null;
		JdbcAdapter db = new JdbcAdapter();
		dbDataAccesser accesser = null;
		DataFormat df  = null;
		String nowdatetime = df.getNowTimeDate("", "", "", "").substring(0, 12);
		String[] values = { id, passward, nowdatetime ,sub_order_no };
		String selectquery = Sqlmaker.strPrintf(Property.cancellsql, values);
		System.err.println(selectquery);
		if (db.init(dss)) {
			result = db.dbSelect(selectquery);
			accesser = new dbDataAccesser( result );
			db = null;
		}
		return CreateSubOrder.createSubOrder( accesser, CreateSubOrder.createUser( accesser ),  xmlhmap);
	}
}
