package jp.co.lastminute.Dflight.Calendar;

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
		try{
			//必須のチェックと値のマッピング
			DflightSearchCondition forms = (DflightSearchCondition)this.form;
			
			String from_ = forms.getFrom();
			String to_ = forms.getTo();
			String days_ = forms.getDays();
			///日付の処理
			String Depdate = request.getParameter( "Depdate" );
			String Add_day = request.getParameter( "Add_day" );
			if( ( Add_day.length() == 0 ) || (Add_day.equals("0")) ){
				String DepTime = request.getParameter( "DepTime" );
				ParseFormat pat = null;
				//if( !DataFormat.getNowDate(1, true).equals( Depdate )){
				//	Depdate = Depdate;
				//	DepTime = "0000";
				//}else{
					int deptime = Integer.parseInt( DepTime ) + Property.nextlimit;
					DepTime = pat.ZeroUP( deptime, 4);
				//}						
				forms.setDays( Depdate );
				forms.setFlight_time( DepTime );
				String DepTimeCode = request.getParameter( "DepTimeCode" );
			}else{
				Depdate = DataFormat.AddToDate( Depdate, Integer.parseInt( Add_day )); 
				forms.setDays( Depdate );
				forms.setFlight_time( "0000" );
			}
			String DepLetterCode = request.getParameter( "DepLetterCode" );
			forms.setFrom( DepLetterCode );
			String ArvLetterCode = request.getParameter( "ArvLetterCode" );
			forms.setTo( ArvLetterCode );
			String Pax = request.getParameter( "Pax" );
			forms.setAllotment( Pax );
			String agt_cd = request.getParameter( "agt_cd" );
			forms.setAgtcode( agt_cd );
			
			return true;
		}catch(Exception ex){	ex.printStackTrace();	}
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
}
