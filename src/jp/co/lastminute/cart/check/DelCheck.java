package jp.co.lastminute.cart.check;

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

import jp.co.lastminute.AllotCheckCondition;
import jp.co.lastminute.dbProductAdapter;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.prop.*;
import jp.co.lastminute.cart.form.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.rpc.*;

import jp.co.lastminute.common.xml.XmlReader;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DelCheck implements CheckInterface{
	private static final int CANNOT_REMOVE_  = Constants.CANNOT_REMOVE_ ;
	DataSource dss;
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ){
		this.mapping = mapping;
		this.form = form;
		this.request = request;
        this.response = response;
	}
	synchronized public boolean CancellCheck(){
		return false;
	}
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Order orders, ServletContext servletContext ) {
        this.mapping = mapping;
        this.form = form;
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
        this.errors = new ActionErrors();
        this.orders = orders;
    }
    synchronized public boolean Check(){
    	//パラメータの確認
    	cartForm cartform = (cartForm)form;
    	String order_NO = ((cartForm)form).getOrder_NO();
		String sub_order_no = ((cartForm)form).getSub_order_no();
		String action_type = ((cartForm)form).getAction_type();
		String product_type_cd = ((cartForm)form).getProduct_type_cd();
		String agtcode = ((cartForm)form).getAgtcode();
		////全ての値は、特定の条件を満たす
		boolean parametarflag = true;	
		if( order_NO.length() == 0 ){
			parametarflag = false;
			cartform.addError_comm("注文番号が必要です");
		}
		if( sub_order_no.length() == 0 ){
			parametarflag = false;
			cartform.addError_comm("小注文番号が必要です");
		}
		if( !action_type.equals( Constants.CANCELL) ){
			parametarflag = false;
			cartform.addError_comm("削除要求が必要です");
		}
		if( product_type_cd.length() == 0 ){
			parametarflag = false;
			cartform.addError_comm("商品種別が必要です");
		}
		if( agtcode.length() == 0){
			parametarflag = false;
			cartform.addError_comm("サプライヤーコードが必要です");
		}
		if( !parametarflag ){	//この時点でエラーであれば戻す
			return false;	
		}
		
    	//コンテキストの取得
    	try{
	    	dss = (DataSource)servletContext.getAttribute( Action.DATA_SOURCE_KEY );
	    }catch(Exception ex){
    		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.fail", "サーバー"));
    		return false;
    	}
    	//削除条件確認
    	Vector suborders =  orders.getSubOrders();
    	Sub_Order suborder = getremoveSuborder( order_NO, sub_order_no );
    	if( suborder == null ){
    		cartform.addError_comm("削除する商品がありません");
    		return false;
    	}
    	//削除データベースクラスの取得
       	Navigato navigate = (Navigato)suborder.getActionclass();
       	String classnames = navigate.getAllotReturnClass();
    	//データベース削除
    	AllotCheckCondition allotCheckCondition = new AllotCheckCondition();
    	allotCheckCondition.setProduct_cd( suborder.getPROD_CD() );
    	if( suborder.getSheat() > 0 ){
    		allotCheckCondition.setAllot( suborder.getSheat() );
    	}else{
    		allotCheckCondition.setAllot( suborder.getPax() );
    	}
    	try{
    		//在庫の戻し
    		dbProductAdapter dbdapter = (dbProductAdapter)Class.forName( classnames ).newInstance();
    		dbdapter.init( dss );
    		dbdapter.returnAllotment( allotCheckCondition, suborder.getPax() );
    		dbdapter = null;
    	}catch(Exception ex){
    		ex.printStackTrace();
    		cartform.addError_comm("システム状態が異常です");
    		return false;
    	}
    	return true;
    }
    public ActionErrors getActionErrors(){
        return this.errors;
    }
    public Order getOrder(){
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
    //
    private Sub_Order getremoveSuborder( String orderNo, String suborderNo){
    	Sub_Order suborder = null;
    	try{
    		Vector suborders = orders.getSubOrders();
    		int orderno = Integer.parseInt( orderNo );
    		int suborderno = Integer.parseInt( suborderNo );
			for(int i=0; i<suborders.size(); i++){
				Sub_Order temp = (Sub_Order)suborders.get( i );
				if(( temp.getORDER_NO() == orderno )&&
					( temp.getSUB_ORDER_NO() == suborderno)&&
					( temp.getGUARANTEE_FLG() < CANNOT_REMOVE_ )){
						suborder = temp;
						break;
				}
			}
    	}catch(Exception ex){}
		return suborder;	
    }
}
