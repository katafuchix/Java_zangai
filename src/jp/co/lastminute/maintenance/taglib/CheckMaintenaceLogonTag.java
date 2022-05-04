/*
 * CheckLogonTag.java
 *
 * Created on 2002/04/27, 13:36
 */

package jp.co.lastminute.maintenance.taglib;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import jp.co.lastminute.maintenance.*;

/**
 *
 * @author  skondo
 * @version 
 */
public class CheckMaintenaceLogonTag extends TagSupport {
    private String name = Property.USER_INFO ;
    private String page = Property.LOGON_PAGE;

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
	        	return (EVAL_PAGE); 
			}
	        pageContext.forward(page);
        }catch( Exception e ){	
        	e.printStackTrace();
        	throw new JspException(e.toString());
        }
        return (SKIP_PAGE);
    }
}
