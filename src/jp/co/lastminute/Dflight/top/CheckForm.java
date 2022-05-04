package jp.co.lastminute.Dflight.top;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.lastminute.Dflight.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckForm extends DflightSearchCondition implements Serializable {
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
		DflightSearchCondition forms = (DflightSearchCondition)this.form;
		String from_ = forms.getFrom();
		String to_ = forms.getTo();
		String days_ = forms.getDays();
		//必須のチェックと値のマッピング
		if( from_.length() == 0 ){
			forms.setFrom( Property.from );
		}
		if( to_.length() == 0 ){
			forms.setTo( Property.to );
		}
		if( days_.length() == 0 ){
			forms.setDays( dataformat.getNowDate(2, true) );
		}
		return true;
	}
	
	public ActionErrors getActionErrors(){
        return this.errors;
    }
    private ActionMapping mapping;
    private ActionForm form;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ActionErrors errors;
}
