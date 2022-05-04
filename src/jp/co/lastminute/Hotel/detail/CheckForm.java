package jp.co.lastminute.Hotel.detail;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.lastminute.Hotel.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckForm extends HotelSearchCondition implements Serializable {
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
		// 	TYPE_NUMBER_ = 0;
	//	TYPE_STRING_ = 1;
	//	TYPE_FLOAT_ = 2;
	//	TYPE_DATE_ =  3;
	//	TYPE_EMAIL_ = 4;
	//	TYPE_MONEY_ = 5;
	//	TYPE_ALPHABET_ = 6;
	//	TYPE_ALPHABETNUMBER_ = 7;
	//	TYPE_DATETIME_ =  8;
	//	TYPE_PHONE_ =  9;
	//	TYPE_TWOBYTE_ =  10;
	synchronized public boolean Check(){
		HotelSearchCondition forms = (HotelSearchCondition)this.form;
		String product_id = forms.getProduct_id();
		String catid = forms.getCatid();
		//必須のチェックと値のマッピング
		int error_sum = 0;
		//
		CheckError chError = null;
		Check formchk = new jp.co.yobrain.util.form.Check();
		
		chError = formchk.offSet( forms.supnbr, 1, true );
		forms.supnbr = chError.getRstr();
		if(chError.getError() > 0){
		  	error_sum++;
		}
		chError = null;
		formchk = null;
		
		formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet( forms.getAgtcode(), 1, true );
		forms.setAgtcode( chError.getRstr() );
		if(chError.getError() > 0){
		  	error_sum++;
		}
		chError = null;
		formchk = null;
		//チェックイン日の保持
		if( forms.getCheckindate().length() == 0){
			forms.setCheckindate( dataformat.getNowDate( 0, true ) );
		}
		formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet( forms.getCheckindate(), 3, true );
		forms.setCheckindate( chError.getRstr() );
		if(chError.getError() > 0){
		  	error_sum++;
		}
		chError = null;
		formchk = null;
		//宿泊数の保持
		if( forms.getNight().length() == 0){
			forms.setNight( "0" );
		}
		formchk = new jp.co.yobrain.util.form.Check();
		chError = formchk.offSet( forms.getNight(), 0, true );
		forms.setNight( chError.getRstr() );
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
