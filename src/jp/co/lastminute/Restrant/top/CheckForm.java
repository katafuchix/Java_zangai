package jp.co.lastminute.Restrant.top;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;

import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

import jp.co.lastminute.Restrant.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckForm extends RestrantSearchCondition implements Serializable {
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
		RestrantSearchCondition forms = (RestrantSearchCondition)this.form;
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
