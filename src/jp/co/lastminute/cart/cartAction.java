package jp.co.lastminute.cart;

import java.io.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.jdbc.JdbcAdapter;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.Sending.*;
import jp.co.lastminute.cart.user.jdbc.*;
import jp.co.lastminute.cart.user.model.*;
import jp.co.lastminute.cart.mailsender.*;
import jp.co.lastminute.cardauth.CardAuthClient;
import jp.co.yobrain.util.form.*;
import jp.co.lastminute.common.xml.*;
import jp.co.lastminute.*;

///////////////////////////////////////
import jp.co.lastminute.cart.prop.*;
import jp.co.lastminute.cart.check.*;
import jp.co.lastminute.cart.check.member.*;
import jp.co.lastminute.cart.jdbc.*;
import jp.co.lastminute.cart.check.product.*;
import jp.co.lastminute.cart.members.*;
import jp.co.lastminute.cart.rpc.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class cartAction extends Action {
	DataSource dss;
	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		////////////////
		ActionForward actionforward = new ActionForward();
		ActionErrors errors = null;
		////////////////
		HttpSession session = request.getSession();	System.err.println("<---Cart In-49-->");
		///セッションの判定
		if( session.getAttribute( Constants.CartOrder ) == null){	System.err.println("<---Cart In-51-->");
			return (mapping.findForward("no_product"));
		}
		if(session.getAttribute( Constants.CartUser ) == null ){	System.err.println("<---Cart In-54-->");
				//何もしないで、遷移変更
				System.err.println("<---Cart In-56-->");
			return (mapping.findForward("no_login"));	
		}
		Order orders = (Order) session.getAttribute( Constants.CartOrder );
		if( orders.getMinStatus() >= Constants.CON_CONFIRM_ ){
			return (mapping.findForward("complete"));
		}
		User users = (User)session.getAttribute( Constants.CartUser );
		ServletContext servletContext = servlet.getServletContext();
		this.dss = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		//////////////////////////////////////////////
		cartForm cartform = (cartForm)form;
		////////////////遷移制御///////////////////////
		String throwflg = request.getParameter( "throwflg");
		String previewflg = request.getParameter( "previewflg");
		//ユーザーログイン、ユーザー登録は、遷移を投げる？
		//まだ、決済未のサブオーダーの取得
		Sub_Order suborder = getSelectedSubOrder( orders, cartform.sub_order_no, cartform.action_type );
		int status = 0;	System.err.println("<---Cart In-71-->");
		//エラー処理
		if( request.getParameter( "throwflg") == null ){	System.err.println("<---Cart In-74-->");
			return (mapping.findForward("noaction"));
		}
		if( request.getParameter( "previewflg") == null ){	System.err.println("<---Cart In-76-->");
			return (mapping.findForward("noaction"));
		}
		if( suborder == null ){
			System.err.println("<-----<---" + throwflg + "---->----->");
			if((!throwflg.equals( Constants.STEP04_CARD ))&&
				(!throwflg.equals( Constants.STEP05_PREVIEW ))&&
				(!throwflg.equals( Constants.STEP05_CONFIRM) )){	System.err.println("<---Cart In-82-->");
				return (mapping.findForward("noaction"));
			}	System.err.println("<---Cart In-83-->");
			return (new ActionForward(mapping.getInput()));
		}
		status = suborder.status; //ステータスを取得
		//インスタンス化するクラスを起動
		Navigato navigate = (Navigato)suborder.getActionclass();
		//
		boolean profile_update = false; //プロファイルの更新を行うか否か判断
		String formname = "";	//ロードするクラス名
		//遷移パラメータ
		int flow = navigate.isThrow();	//1であったらカード決済まで行かなくては終了しない。
		int card = navigate.isCardAuth();
		int sending = navigate.isSending();
		suborder.setFax( navigate.isFax() ); //FAX送信フラグをセット
		
		//
		System.err.println("<-----<---" + throwflg + "---->----->");
		//キャンセル系
		boolean cansel = false;
		if( throwflg.equals( Constants.STEP01_CANCELL ) ){
			if( status >= Constants.NOT_CONFIRM_ ){	//ステータスが、完了していたらキャンセルできない
				cartform.addError_comm("注文処理が完了しているため、キャンセルできません。" );	//アクションさせない
				System.err.println("<---Cart In-104-->");
				return (new ActionForward(mapping.getInput()));
			}else{
				formname = navigate.getCancellClass();
				cansel = true;
			}
		}
		System.err.println("<---Cart In-110-->");
		//付加情報系
		boolean member = false;
		if( throwflg.equals( Constants.STEP00_MEMBER ) ){
			if( status >= Constants.CON_CONFIRM_ ){	//ステータスが、完了していたら情報の変更はできない
				cartform.addError_comm("注文処理が完了しているため、メンバー情報の変更はできません"); //アクションさせない
				System.err.println("<---Cart In-117-->");
				return (new ActionForward(mapping.getInput()));
			}else{
				formname = navigate.getMemberInfoClass();
				member = true;
			}
		}	System.err.println("<---CartIn:111--->");
		//配送先
		boolean lastflag = false; //最終画面か否かを判断
		boolean sendingflg = false;
		if( throwflg.equals( Constants.STEP03_SENDING) ){
			System.err.println("Sending_Flag Up 132");
			if( status >= Constants.CON_CONFIRM_ ){
				System.err.println("Sending_Erre Status Get 134");
				cartform.addError_comm( "注文処理が完了しているため、配送先情報の変更はできません");
				return (new ActionForward(mapping.getInput()));
			}else{
				System.err.println("Sending_ClassName Get 137");
				formname = navigate.getSendingClass();
				sendingflg = true;
			}
			if(( sendingflg )&&(orders.getSending() == 1)&&
			 (cartform.getPreviewflg().equals( Constants.SENDING_UPDATE ))){
				orders.setSending( 0 );
				orders = setSubOrdersStatus( orders, Constants.NOT_CONFIRM_, Constants.CART_IN_ );
				sendingflg = false;
				session.setAttribute( Constants.CartOrder, orders );
				return (new ActionForward(mapping.getInput()));				
			}
			if(( sendingflg )&&( cartform.getUpdate_flg().equals( "1" ) )){
				profile_update = true;
			}
			System.err.println("Sending_ClassName Get Name 145 + " + formname );
		}	System.err.println("<---CartIn:126--->");		
		//カード関連
		boolean card_flg = false;
		if( throwflg.equals( Constants.STEP04_CARD ) ){	System.err.println("<---CartIn:128--->");
			formname = navigate.getCardAuthClass();
			card_flg = true;
			if( previewflg.equals( "" + Constants.UPDATE_ + "" ) ){	System.err.println("<---CartIn:146--->");
				cartform.setThrowflg( Constants.STEP04_CARD );
				orders.setCard( 0 );
				session.setAttribute( Constants.CartOrder, orders );
				return (new ActionForward(mapping.getInput()));
			}
		}
		if( throwflg.equals( Constants.STEP05_CONFIRM ) ){ 
			System.err.println("<---CartIn:LAST FLAG IS OK--->");	
			lastflag = true;
		}
		try{
			if( member ){
				Memberhandler memberhandler = new Memberhandler( formname );
				memberhandler.init(  suborder.getPax(), suborder.getInfant() );
				System.err.println("<----TARGET DATE CART--" + suborder.getSALES_DATE() + "--->");
				memberhandler.setMember( request, suborder.getSALES_DATE() );
				///更新作業の際は、ステータスを落とす//
				if( previewflg.equals( Constants.MEMBER_UPDATE ) ){
					orders = setRelation(orders, suborder, memberhandler.getMembers(), 0 );
					session.setAttribute( Constants.CartOrder, orders );
					ActionForward af = new ActionForward(mapping.getInput());
					return ( af );
				}
				if( !memberhandler.isReBoolean() ){	System.err.println("<---CartIn:145 Not Passwd--->");
					orders = setRelation(orders, suborder, memberhandler.getMembers(), suborder.getStatus() );
					session.setAttribute( Constants.CartOrder, orders );
					cartform.addError_comm("メンバー登録の入力値に間違いがあります");
					ActionForward af = new ActionForward(mapping.getInput());
					return ( af );
				}else{	System.err.println("<---CartIn:151-->");
					//フラグをカード用に変える
					String next = "";
					int nextstatus = 0;
					//配送先の入力を要求されていてまだ、終了していない場合
					System.err.println("<---CartIn:156-->");
					if(( navigate.isSending() == 1 )&&( orders.sending == 0 )){
						System.err.println("<---CartIn:158-->");
						next = Constants.STEP03_SENDING;
					}else if(( navigate.isSending() == 0)&&( navigate.isCardAuth() >= Constants.AUTH_ONLY_)){
						System.err.println("<---CartIn:161-->");
						next = Constants.STEP04_CARD;
						nextstatus = Constants.NOT_CONFIRM_;
					}else if(( navigate.isSending() == 0)&&( navigate.isCardAuth() == 0)){
						System.err.println("<---CartIn:165-->");
						next = Constants.STEP05_PREVIEW;
						nextstatus = Constants.NOT_CONFIRM_;
					}
					System.err.println("<---CartIn:169-->");
					orders = setRelation(orders, suborder, memberhandler.getMembers(), nextstatus );
					//セッションに保存する。
					session.setAttribute( Constants.CartOrder, orders );
					cartform.setThrowflg( next );
					ActionForward af = new ActionForward(mapping.getInput());
					return ( af );
				}
			} //else{  System.err.println("<---CartIn:199 IN " + formname + "--->");
				if( !lastflag ){
					CheckInterface infoform = 
						( CheckInterface )Class.forName( formname ).newInstance();
					infoform.init( mapping, form, request, response, orders, servletContext ) ;	
					System.err.println("<---CartIn:201 INFORM CHK --->");
					boolean check_flg = infoform.Check();
					cartform = (cartForm)infoform.getForm();
					if( !check_flg ){	System.err.println("<---CartIn:202 INFORM FALSE --->");
						session.setAttribute( Constants.CartOrder, infoform.getOrder() );	
						ActionForward af = new ActionForward(mapping.getInput());
						///異常系処理
						return ( af );
					}	System.err.println("<---CartIn:208 INFORM END --->");
					orders = infoform.getOrder();
					//カードオーソリ確認ならば、カードのステータスを上げましょう
					if(card_flg){	System.err.println("<---CartIn:234 CARD FLG == --->");						
						Card_Auth cards = orders.getCard_Auth();
						cards.setCard_No( cartform.getCard_No() );
						cards.setCard_type( cartform.getCard_type() );
						cards.setExpire( cartform.getExpireMonth() + cartform.getExpire() );
						orders.setCardAuth( cards );
						orders.setCard( 1 );
					}
					//セッションに保持する。
					//session.setAttribute( Constants.CartOrder, orders );				
					if( cansel ){	//キャンセルを行う
						//サブオーダー削除
						orders.removeSubOrder( cartform.sub_order_no );
				    	//金額の再計算
				    	orders.calcTotal();
				    	//データベース更新
				    	modifyCannsel( orders, cartform.sub_order_no );
				    	//削除してサブオーダーがなくなったらエラー画面へ
				    	if( orders.getSubOrderCount() <= 0 ){	//商品がないよ画面へ
				    		///異常系処理
				    		return (mapping.findForward("no_product"));
				    	}
					}
					System.err.print( "L262 sendingflg IS " +  sendingflg );
					System.err.print( "L263 previewflg IS " +  previewflg );
					if( sendingflg ){	//配送先の更新
						cartform.setAction_type( Constants.CARD_ENDING );
						System.err.print( "Sending Update Go" );
						SendingLogicHandler handler = null;
						Product_Send productsend = handler.getProductSend( cartform, orders.getProductSend(0) );
						orders = setSubOrdersStatus( orders, Constants.CART_IN_, Constants.NOT_CONFIRM_ );//サブオーダーのステータスを代えて
						//suborder.setStatus( Constants.NOT_CONFIRM_ );	
						orders.setSending( 1 );							//配送先確定
						orders.setSubOrder( suborder );	
						orders.setProductSend(productsend, 0);			//センディングのステータスを代える
					}
					if( profile_update ){	//ユーザーの更新
						Product_Send productsend = orders.getProductSend(0);
						//プロファイルからユーザーデータへの変換
						Profile profile = productsend.getUserProfile(users.getProfile(), productsend);
						users.setProfile( profile );
						session.setAttribute( Constants.CartUser, users );
						orders.setProfile( 1 );		
					}
					//成功フラグを付け入力系に戻す
					cartform.setThrowflg( Constants.STEP05_PREVIEW );
					actionforward = new ActionForward(mapping.getInput());
				}else{
					//カードのオーソリを行う
					if( isCardAuth( orders )) {
						String cardchekcclass = "jp.co.lastminute.cart.check.CardCheck";
						CheckInterface infoform = 
							( CheckInterface )Class.forName( cardchekcclass ).newInstance();
						infoform.init( mapping, form, request, response, orders, servletContext ) ;	
						if( !infoform.Check() ){	System.err.println("<---CartIn:257 INFORM FALSE --->");
							cartform = (cartForm)infoform.getForm();
							session.setAttribute( Constants.CartOrder, infoform.getOrder() );	
							ActionForward af = new ActionForward(mapping.getInput());
							///異常系処理
							return ( af );
						}
						String booleingclassname = getBookingClassname( orders );
						if( booleingclassname.length() > 0 ){
							String authnumber = orders.getCard_Auth().getAuth_Code();
							//ブッキングを行う。
							dbProductAdapter db = (dbProductAdapter)Class.forName( booleingclassname ).newInstance();
							HashMap xmlhash = (HashMap) servletContext.getAttribute(Constants.Product_Type_Mapping_);
							Sub_Order bookingsuborder = db.bookingOrder( getBookingSubOrder(orders) , xmlhash );
							bookingsuborder.setAuthnumber( authnumber );
							//ブッキングクラスを戻す。
							orders.setSubOrder( bookingsuborder );
							//ブッキングが成功しているのか？
							if( bookingsuborder.isBooking() ){
								session.setAttribute( Constants.CartOrder, orders );
								return (mapping.findForward("noaction"));
							}
						}
					}
					//メールを送信する。
					CartMailSender cartmailsender = new CartMailSender();
					cartmailsender.init( orders, users.getProfile(), this.dss );
					cartmailsender.MailSend();
					//データベースを更新しステータスをコンファームにアップする。
					orders = setConfirmStatus( orders, Constants.NOT_CONFIRM_, Constants.CON_CONFIRM_ , users);
					if( orders == null ){
						///異常系処理
						return (mapping.findForward("noaction"));					
					}
					//最終画面に遷移
					//actionforward = new ActionForward(mapping.getInput());
					actionforward = mapping.findForward("complete");
				}
				session.setAttribute( Constants.CartOrder, orders );
				return (actionforward);
		}catch(ClassNotFoundException cnfex){
			cnfex.printStackTrace();
		}catch(IllegalAccessException iex){
				iex.printStackTrace();
		}catch(InstantiationException itex){
			itex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();	
		}
		return (mapping.findForward("actionfail"));
	}
	/**
	 * ブッキングクラスの名の取得
	 */
	private String getBookingClassname( Order orders ){
		for( int i=orders.getSubOrderCount()-1; i>=0; i-- ){
			Sub_Order subo = orders.getSubOrder( i );
			if( subo.status == Constants.NOT_CONFIRM_ ){
				return ((Navigato)subo.getActionclass()).getBookingClass();
			}
		}
		return "";
	}
	private Sub_Order getBookingSubOrder( Order orders ){
		for( int i=orders.getSubOrderCount()-1; i>=0; i-- ){
			Sub_Order subo = orders.getSubOrder( i );
			if( subo.status == Constants.NOT_CONFIRM_ ){
				return subo;
			}
		}
		return null;
	}
	/**
	 * 配送先のステータスを変更するために全てのステータスをUP
	 */
	private Order setSubOrdersStatus( Order order, int beforestatus, int nextstatus ){
		try{
			for( int i=0; i<order.getSubOrderCount(); i++ ){
				Sub_Order temp_suborder = order.getSubOrder( i );
				if(( temp_suborder.status == beforestatus )
					&&( CheckTool.isPassCart( temp_suborder.getPRODUCT_TYPE_CD() ))){
					temp_suborder.setStatus( nextstatus );
					order.setSubOrder( temp_suborder, i );
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return order;
	}
	/**
	 * リレーションをセットしオーダーを返す
	 */
	private Order setRelation( Order order, Sub_Order sub, Node members, int nextstatus ){
		int targetstatus = sub.status;
		for(int i=0; i<order.getSubOrderCount(); i++){
			System.err.println("<---CartIn:273:" + i + "-->");
			Sub_Order temp = order.getSubOrder( i );
			System.err.println( temp.getPRODUCT_TYPE_CD() ); System.err.println( temp.getAGT_CD() ); System.err.println( temp.status );
			System.err.println( sub.status );
			if(( temp.status < Constants.CON_CONFIRM_ )
			&&( temp.getPRODUCT_TYPE_CD() == sub.getPRODUCT_TYPE_CD())
			&&( temp.getAGT_CD().equals( sub.getAGT_CD() ))
			&&( temp.status == targetstatus ) ) {
				System.err.println("<---CartIn:279-->");
				temp.setMember( members );
				temp.status = nextstatus;
				order.setSubOrder( temp );
			}
			System.err.println("<---CartIn:284-->");
		}
		System.err.println("<---CartIn:286-->");
		return order;
	}
	/**
	 * サブオーダーの取得を行う
	 */
	private Sub_Order getSelectedSubOrder( Order orders, String sub_order_no, String action_type ) {
		try{
			if( sub_order_no.length() == 0){	System.err.println("<----selectedSuborder 325---->");
				for(int i=0; i<orders.getSubOrderCount(); i++){
					if( ((Sub_Order)orders.getSub_order().get( i )).status < Constants.CON_CONFIRM_ ){
						System.err.println("<----selectedSuborder 328:i " +  i +"---->");
						return ((Sub_Order)orders.getSub_order().get( i ));
					}
				}
				return null;
			}else if(action_type.equals( Constants.CANCELL )){
				System.err.println("<----selectedSuborder 332---->");
				for(int i=0; i<orders.getSubOrderCount(); i++){
					if( ((Sub_Order)orders.getSub_order().get( i )).status < Constants.NOT_CONFIRM_ ){
						Sub_Order suborder = (Sub_Order)orders.getSub_order().get( i );
						if( suborder.getSUB_ORDER_NO() ==  Integer.parseInt( sub_order_no ) ){
							return suborder;
						}
					}
				}
				return null;
			}else if( action_type.equals( Constants.MEMBER_UPDATE) ){
				System.err.println("<----selectedSuborder 343---->");
				for(int i=0; i<orders.getSubOrderCount(); i++){
					if( ((Sub_Order)orders.getSub_order().get( i )).status < Constants.CON_CONFIRM_ ){
						Sub_Order suborder = (Sub_Order)orders.getSub_order().get( i );
						if( suborder.getSUB_ORDER_NO() ==  Integer.parseInt( sub_order_no ) ){
							Navigato navig = (Navigato)suborder.getActionclass();
							if( navig.isMember() == Constants.IS_MEMBER_NEED){
								return suborder;
							}
							return null;
						}
						return null;
					}
				}
				return null;
			}
		}catch(Exception ex){	ex.printStackTrace();	}		
		return null;
	}
	/**
	 * カードオーソリが必要か？
	 */
	private boolean isCardAuth( Order order ){
		for( int i=0; i<order.getSubOrderCount(); i++){	
			Sub_Order temp = order.getSubOrder( i );
			if( temp.getStatus() == Constants.NOT_CONFIRM_ ){
				Navigato navi = (Navigato)temp.getActionclass();
				if( navi.isCardAuth() == Constants.AUTH_ONLY_ ){
					return true;
				}
				if( navi.isCardAuth() == Constants.REAL_SALE_ ){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * ステータスを上げオーダーを返す,上げるオーダーがない場合は、NULLを返す,
	 * また、データベースへの更新処理を行う
	 */
	private Order setConfirmStatus( Order order, int status1, int status2, User users ){
		Vector query = new Vector();
		try{
			for(int i=0; i<order.getSubOrderCount(); i++){
				Sub_Order temp = order.getSubOrder(i);
				if( temp.status == status1 ){
					temp.setStatus( status2 );
					dbAdapterSubOrder db = new dbAdapterSubOrder();
					query.add( db.modifySubOrderStr( temp ) );				
					order.setSubOrder(temp, i);
					db = null;
				}
			}
			if( query.size() > 0 ){
				dbAdapterOrder db = new dbAdapterOrder( this.dss );
				String orderSql = db.getModifyOrderStr( order );
				int add_counter = 2;
				String profilesql = "";
				if( order.getProfile() == 1 ){
					Profile profile = (order.getProductSend(0)).getUserProfile(users.getProfile(), order.getProductSend(0) );
					dbAdapterProfile profiledb  = null;
					try{
						profilesql = profiledb.getModifyProfileStr( profile, (new JdbcAdapter()) );
						add_counter = 3;
					}catch(Exception ex){	ex.printStackTrace();	}
				}
				String[] subordersql = new String[query.size() + add_counter ];
				subordersql[ query.size() ] = orderSql;
				subordersql[ query.size() + 1 ] = getSendingSql( order.getProductSend(0) );	//配送先送信
				if( profilesql.length() > 0 ){
					subordersql[ query.size() + 2 ] = profilesql; //プロファイル更新
				}
				for(int j=0; j<query.size(); j++){
					subordersql[j] = (String)query.get(j);
				}
				if( db.doBatchUpdate( subordersql )){
					db = null;
					return order;
				}
				if( db != null )	db = null;
			}
		}catch(Exception ex){	ex.printStackTrace();	
			return null;
		}
		return null;
	}
	/**
	 * 配送先クエリーの取得
	 */
	private String getSendingSql(Product_Send prs ) throws Exception{
		dbAdapterProductSend dbp = null;
		String reStr = "";
		if( prs.getSEND_ID() > 0){
			dbp = new dbAdapterProductSend();
			reStr = dbp.getModifyProductSendStr( prs );
		}else{
			dbp = new dbAdapterProductSend( this.dss );
			reStr = dbp.getAddProductSendStr( prs );	
		}
		dbp = null;
		return reStr;
	}
	/**
	 * キャンセル処理を行う
	 */
	private void modifyCannsel( Order order, String sub_order_no ){
		try{
			dbAdapterSubOrder subdb = new dbAdapterSubOrder();
			dbAdapterOrder db = new dbAdapterOrder( this.dss );
			String[] query = { subdb.removeSuborderStr( sub_order_no ), db.getModifyOrderStr( order )} ;
			subdb = null;
			if( db.doBatchUpdate( query )){
				db = null;
			}
			if( db != null )	db = null;
		}catch(Exception ex){	ex.printStackTrace();	}
	}
}