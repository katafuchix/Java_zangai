package jp.co.lastminute.cart.taglib;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import jp.co.lastminute.cart.*;
import org.apache.struts.action.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.user.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CheckCancellLoginTag extends TagSupport{
	private String name = Constants.Cancell_SOrder;
    private String page = Constants.CANCELL_LOGIN_NG_PAGE;

    public String getName() {
	return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    /**
     * スタートタグ
     */
    public int doStartTag() throws JspException {
        return (SKIP_BODY);
    }
    /**
     * エンドタグ
     */
    public int doEndTag() throws JspException {
    	//ログインの判断
        boolean valid = false;	//遷移OK処理
        String redirect = "";
        HttpSession session = pageContext.getSession();
        HttpServletRequest req = ( HttpServletRequest )pageContext.getRequest();
    	try{
    		//ログイン処理
	        if ((session != null) && (session.getAttribute(name) != null)) {
	        	valid = true;
    		}
	        if (valid) {	//ログイン成立時
	        	return (EVAL_PAGE);                  
	        }
    	}catch( Exception e ){	
        	e.printStackTrace();
        	throw new JspException(e.toString());
        }
        return (SKIP_PAGE);
    }
}
