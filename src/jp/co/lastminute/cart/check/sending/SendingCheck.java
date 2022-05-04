package jp.co.lastminute.cart.check.sending;

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
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.lastminute.cart.check.*;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.model.*;

import jp.co.yobrain.util.DataFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SendingCheck extends cartForm implements CheckInterface {
	public SendingCheck() {
	}
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ){
			this.mapping = mapping;
			this.form = form;
			this.request = request;
	        this.response = response;
	}
	synchronized public boolean CancellCheck(){
		return false;
	}
	/** Creates new ActivityCheck */
	public void init(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		Order orders,
		ServletContext servletContext) {
		this.mapping = mapping;
		this.form = form;
		this.request = request;
		this.response = response;
		this.servletContext = servletContext;
		this.errors = new ActionErrors();
		this.orders = orders;
	}
	synchronized public boolean Check() {
		int error_sum = 0;
		state_cd = ((cartForm) form).getState_cd();
		main_state = ((cartForm) form).getMain_state();
		ticketing = ((cartForm) form).getTicketing();
		payment_way = ((cartForm) form).getPayment_way();
		cartForm cartform = (cartForm) form;
		//チェックルーチン
		Check formchk;
		CheckError chError;
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getSending_month().trim(),
				0,
				true);
		this.sending_month = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_sending_month ] = -1;
			cartform.addError_comm( "配送日が不正です");
		}
		chError = null;
		formchk = null;
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getSending_day().trim(),
				0,
				true);
		this.sending_day = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_sending_day ] = -1;
			cartform.addError_comm( "配送日が不正です");
		}
		chError = null;
		formchk = null;
		/////配送日の確認ルーチン
		try{
			DataFormat df = null;
			int month = Integer.parseInt( sending_month );
			int day = Integer.parseInt( sending_day );
			int nowdate_int = Integer.parseInt( df.getNowDate( 0, true) );
			int year = 0;
			if(( df.getNowMonth() > 11 )&&( df.getNowMonth() > month )){
				year = df.getNowYear() + 1;
			}else{
				year = df.getNowYear();
			}
			int send_target = year*10000 + month * 100 + day ;
			formchk = new jp.co.yobrain.util.form.Check();
			chError =
				formchk.offSet( "" + send_target + "", 3 ,true);
			if (chError.getError() > 0) {
				error_sum++;
				cartform.ch_send_array[ cartform.ch_sending_day ] = -1;
				cartform.addError_comm( "配送日が不正です");
			}else{
				if( send_target < nowdate_int + 1){
					cartform.ch_send_array[ cartform.ch_sending_day ] = -1;
					cartform.addError_comm( "配送日が不正です");		
				}
			}
			chError = null;
			formchk = null;
		}catch(Exception ex){
			cartform.ch_send_array[ cartform.ch_sending_day ] = -1;
			cartform.addError_comm( "配送日が不正です");			
		}
		//ラスト
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getBuildaddress().trim(),
				1,
				false);
		buildaddress = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_buildaddress ] = -1;
			cartform.addError_comm( "贈リ主住所(建物)の入力が不正です");
		}
		chError = null;
		formchk = null;
		//ラスト
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_address().trim(),
				1,
				true);
		main_address = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_address ] = -1;
			cartform.addError_comm( "贈り主住所(建物)の入力が不正です");
		}
		chError = null;
		formchk = null;
		///////////////////////////////////////////////////////////////////////////////////
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_postalcode1().trim(),
				9,
				true);
		main_postalcode1 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_postalcode ] = -1;
			cartform.addError_comm( "贈り主郵便番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_postalcode2().trim(),
				9,
				true);
		main_postalcode2 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_postalcode ] = -1;
			cartform.addError_comm( "贈り主郵便番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		main_postalcode = main_postalcode1 + main_postalcode2;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getCity_name().trim(),
				1,
				true);
		city_name = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_city_name ] = -1;
			cartform.addError_comm( "贈り主住所(市町村)の入力が不正です");
		}
		chError = null;
		formchk = null;
		///////////////////////////////////////////////////////////////////////////////////
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_lastname_kana().trim(),
				1,
				false);
		main_lastname_kana = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_lastname_kana ] = -1;
			cartform.addError_comm( "贈り主　姓(かな)の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_firstname_kana().trim(),
				1,
				false);
		main_firstname_kana = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_firstname_kana ] = -1;
			cartform.addError_comm( "贈り主　名(かな)の入力が不正です");
		}
		chError = null;
		formchk = null;
		/////////////////////////////////////////////////
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_lastname_kanji().trim(),
				1,
				true);
		main_lastname_kanji = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_lastname_kanji ] = -1;
			cartform.addError_comm( "贈り主　姓の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMain_firstname_kanji().trim(),
				1,
				true);
		main_firstname_kanji = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_main_firstname_kanji ] = -1;
			cartform.addError_comm( "贈り主　名の入力が不正です");
		}
		chError = null;
		formchk = null;
		//////////////////////////////////////////////////////////////////////////////////
		//
		//ラスト
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getLastname_1().trim(),
				1,
				true);
		lastname_1 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_lastname_1 ] = -1;
			cartform.addError_comm( "送り先　姓の入力が不正です");
		}
		chError = null;
		formchk = null;
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getLastname_2().trim(),
				1,
				true);
		lastname_2 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_lastname_2 ] = -1;
			cartform.addError_comm( "贈り先　名の入力が不正です");
		}
		chError = null;
		formchk = null; //////////////////////////////
		lastname = lastname_1 + "\t" + lastname_2;
		/////////////////////////////////////////////
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getFirst_name1().trim(),
				1,
				true);
		first_name1 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_first_name1 ] = -1;
			cartform.addError_comm( "贈り先　姓の入力が不正です");
		}
		chError = null;
		formchk = null;
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getFirst_name2().trim(),
				1,
				true);
		first_name2 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_first_name2 ] = -1;
			cartform.addError_comm( "贈り先　名の入力が不正です");
		}
		chError = null;
		formchk = null; ///////////
		first_name = first_name1 + "\t" + first_name2;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getPostal_cd1().trim(),
				9,
				true);
		postal_cd1 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_postal_cd1 ]  = -1;
			cartform.addError_comm( "贈り先　郵便番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getPostal_cd2().trim(),
				9,
				true);
		postal_cd2 = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_postal_cd2 ] = -1;
			cartform.addError_comm( "贈り先　郵便番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		postal_cd = postal_cd1 + postal_cd2;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getCity_name().trim(),
				1,
				true);
		city_name = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_city_name ] = -1;
			cartform.addError_comm( "贈り先　郵便番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getAddress().trim(),
				1,
				true);
		address = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_address ] = -1;
			cartform.addError_comm( "贈り先　住所の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getDeliveryname().trim(),
				1,
				false);
		deliveryname = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_deliveryname ] = -1;
			cartform.addError_comm( "贈り先　配達者の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getTel_no().trim(),
				9,
				true);
		tel_no = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_tel_no ] = -1;
			cartform.addError_comm( "贈り先　電話番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getFax().trim(),
				9,
				false);
		fax = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_fax ] = -1;
			cartform.addError_comm( "贈り先　FAX番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet(cartform.getMobile_e_mail().trim(),
				9,
				true);
		mobile_e_mail = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_mobile_e_mail ] = -1;
			cartform.addError_comm( "贈り先　携帯番号の入力が不正です");
		}
		chError = null;
		formchk = null;
		//
		formchk = new jp.co.yobrain.util.form.Check();
		chError =
			formchk.offSet( cartform.getAddressflg().trim(),
				0,
				false);
		addressflg = chError.getRstr();
		if (chError.getError() > 0) {
			error_sum++;
			cartform.ch_send_array[ cartform.ch_addressflg ] = -1;
			cartform.addError_comm( "贈り先　配送先の入力が不正です");

		}
		chError = null;
		formchk = null;
		//
		this.form = cartform;
		if (error_sum == 0) {
			return true;
		}
		return false;
	}
	public ActionErrors getActionErrors() {
		return this.errors;
	}
	public Order getOrder() {
		return this.orders;
	}
	public ActionForm getForm(){
		return this.form;
	}
	private ActionMapping mapping;
	private ActionForm form;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ActionErrors errors;
	private ServletContext servletContext;
	private Order orders;
}
