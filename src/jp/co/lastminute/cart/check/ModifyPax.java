package jp.co.lastminute.cart.check;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.Cancell.*;
import jp.co.lastminute.cart.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ModifyPax extends ModifyForm implements CheckInterface{
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Order orders, ServletContext servletContext ) {
        this.mapping = mapping;
        this.form = form;
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
        this.errors = new ActionErrors();
        this.orders = orders;
    }
	
	public void init( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ){
		this.mapping = mapping;
		this.form = form;
		this.request = request;
        this.response = response;
	}
	public boolean Check(){
		return CancellCheck();
	}
	public boolean CancellCheck(){
		ModifyForm modifyPax = (ModifyForm)form;
		Sub_Order suborder = modifyPax.getTemp_suborder();
		int pax = 0;
		try{
			pax = Integer.parseInt( modifyPax.getPax() );
		}catch(Exception ex){
			ex.printStackTrace();
			modifyPax.addError_comm("不正な入力です。");
			return false;
		}
		if( pax == 0){
			modifyPax.addError_comm("不正な入力です。");
			return false;
		}
		if(pax > suborder.getPax() ){
			modifyPax.addError_comm("不正な入力です。");
			return false;
		}
		suborder.setPax( pax );
		modifyPax.setTemp_suborder( suborder );
		return true;
	}
	public ActionForm getForm(){
		return this.form;
	}
	private ActionMapping mapping;
    private ActionForm form;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;
    private ActionErrors errors;
    private Order orders;
    public ActionErrors getActionErrors(){
        return this.errors;
    }
    public Order getOrder(){
    	return this.orders;	
    }
}
