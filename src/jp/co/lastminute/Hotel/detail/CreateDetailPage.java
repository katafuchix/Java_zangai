package jp.co.lastminute.Hotel.detail;

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

import jp.co.lastminute.ContextProperty;
import jp.co.lastminute.Hotel.*;
import jp.co.lastminute.Hotel.jdbc.*;

import jp.co.yobrain.util.file.InportFile;

import jp.co.yobrain.util.ParseFormat;
import jp.co.yobrain.util.DataFormat;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class CreateDetailPage implements Serializable{
	private static final String OptionLabels_ = FormAction.OptionLabels_;
	private static final String ALtps_ = FormAction.ALtps_;
	private DataSource dss = null;;
	//
	private ActionMapping mapping = null;
	private ActionForm form = null;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private ServletContext servletContext = null;
	public CreateDetailPage( ActionMapping mapping, 
							  ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response,
							  ServletContext servletContext,
							  DataSource dss ){
		this.mapping = mapping;
		this.form = form;
		this.request = request;
		this.response = response;
		this.servletContext = servletContext;
		this.dss = dss;
	}
	public ActionForward returnPages()throws IOException, ServletException{
    	//環境変数を取り出す
    	Form actionform = (Form)form;    	
    	//チェックルーチンを通過する
		CheckForm checkForm = new CheckForm( mapping, form, request, response);
        if( !checkForm.Check() ){
        	//トークンを無視してエラー画面を出力
           return ( mapping.findForward("mappingfail") );
        }
        actionform = ( Form )checkForm.getForm();
        //ファイルからXMLを読み込む
        String filename = actionform.getAgtcode() + "/" + actionform.getSupnbr() + ".xml";
        //
        System.err.println( filename );
        //
        String xmlstring = getXmlAmount( filename );
        if( xmlstring.length() == 0 ){ return (mapping.findForward("mappingfail")); }
        	actionform.setXmlstring( xmlstring );
        	System.err.println("XMLSTR_Detail_001\n" + xmlstring);
    	//データベースから取得する
    	try{
	    	dbAdapterHotel db = new dbAdapterHotel( this.dss );
	    	db.setContext( servletContext );	System.err.println("line 65" );
	    	HotelSearchCondition sCondition = (HotelSearchCondition)actionform;	   	System.err.println("line 67" );
	    	HashMap maps = db.getDetailHash( actionform );		  	System.err.println("line 69" );
	    		    	
	    	actionform.setSelectionStr( makeSelectionStr( (OptionLabel[])maps.get( OptionLabels_ ), sCondition ) );
	    	System.err.println("line 71" );
	    	if( actionform.getProduct_id().length() > 0 ){
	    		System.err.println("line 90" );
	    		Webaltp[] altps = db.getDetailList( actionform );
	    		if( altps == null ){
					actionform.setProductaltpxml( "" );
					actionform.setFormHooter( "" );
				}else{
					PriceListMaker priceListMaker = new PriceListMaker( altps );
					actionform.setProductaltpxml( priceListMaker.getList() );
					actionform.setFormHooter( priceListMaker.getHoot() );
	    		}
	    	}
	    	return (mapping.findForward("success"));
    	}catch(Exception ex){	ex.printStackTrace();	}
    	return (mapping.findForward("dbfail"));	
    }
    private static String getXmlAmount( String filename ){
    	try{
    		String filepath = ContextProperty.basepath + ContextProperty._hotel_product_Dir;
    		InportFile inf = new InportFile();
			return inf.getFileAmount( filename, filepath );
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return "";
    }
    private static String makeSelectionStr( OptionLabel[] options, HotelSearchCondition searchCondition ){
    	if( options != null ){
    		try{
    			StringBuffer sb = new StringBuffer();
    			for(int i=0; i<options.length; i++){
					sb.append("<option value='" + options[i].getValue() + "'");
					if( options[i].getValue().equals( searchCondition.getProduct_id() ) ){
						sb.append(" selected>");					
					}else{
						sb.append(">");
					}
					sb.append( options[i].getLabel() );
					sb.append( "</option>\n");
				}
				return sb.toString();
    		}catch(Exception ex){	ex.printStackTrace();	}
    		return "";	
    	}
    	return "";
    }
}
