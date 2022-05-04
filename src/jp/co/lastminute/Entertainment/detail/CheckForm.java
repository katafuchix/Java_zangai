package jp.co.lastminute.Entertainment.detail;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.lastminute.Entertainment.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckForm extends EntertainmentSearchCondition implements Serializable {
	DataFormat dataformat = null;
	public CheckForm( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ){
		this.mapping = mapping;
        this.form = form;
        this.request = request;
        this.response = response;
        this.errors = new ActionErrors();	
	}
	/**
	 * チェック
	 */
	synchronized public boolean Check(){
		EntertainmentSearchCondition forms = (EntertainmentSearchCondition)this.form;
		String product_id = forms.getProduct_id();
		String catid = forms.getCatid();
		//必須のチェックと値のマッピング
		int error_sum = 0;
		//
		CheckError chError = null;
		Check formchk = new jp.co.yobrain.util.form.Check();
		
		chError = formchk.offSet( forms.getProduct_id(), 7, true );
		forms.setProduct_id( chError.getRstr() );
		if(chError.getError() > 0){
		  	error_sum++;
		}
		chError = null;
		formchk = null;
		
		formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet( forms.getCatid(), 0, true );
		forms.setCatid( chError.getRstr() );
		if(chError.getError() > 0){
		  	error_sum++;
		}
		chError = null;
		formchk = null;
		if( error_sum == 0 ){	return true;	}
		return false;		
	}
	
	public ActionErrors getActionErrors(){
        return this.errors;
    }
    private ActionMapping mapping;
    private ActionForm form;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ActionErrors errors;
	/**
	 * Returns the errors.
	 * @return ActionErrors
	 */
	public ActionErrors getErrors() {
		return errors;
	}

	/**
	 * Returns the form.
	 * @return ActionForm
	 */
	public ActionForm getForm() {
		return form;
	}

	/**
	 * Sets the errors.
	 * @param errors The errors to set
	 */
	public void setErrors(ActionErrors errors) {
		this.errors = errors;
	}

	/**
	 * Sets the form.
	 * @param form The form to set
	 */
	public void setForm(ActionForm form) {
		this.form = form;
	}

}
