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
public class FormAction extends Action{
	public static final String OptionLabels_ = "_optionLabels_";
	public static final String ALtps_ = "aLtps_";
	private DataSource dss;
	public ActionForward perform(ActionMapping mapping,
				 ActionForm form,
				 HttpServletRequest request,
				 HttpServletResponse response)
	throws IOException, ServletException{
    	//ä¬ã´ïœêîÇéÊÇËèoÇ∑
    	////Session
		HttpSession session = request.getSession();
		ServletContext servletContext = servlet.getServletContext();
		this.dss = (DataSource) servletContext.getAttribute(Action.DATA_SOURCE_KEY);
		
		CreateDetailPage pages = new CreateDetailPage( mapping, form, request, response, servletContext, dss );
		return pages.returnPages();
    }
    /**
    private static String makeProductaltpxmlStr( Product_Altp[] altps ){
    	if( altps != null ){
    		try{
    			StringBuffer sb = new StringBuffer();
    			sb.append( "<product_lists>\n" );
    			for(int i=0; i<altps.length; i++){
    				sb.append( "<product_list>\n");					 
					sb.append( "<product_seq_no>"+altps[ i ].product_seq_no+"</product_seq_no>\n");
					sb.append( "<agt_cd>"+altps[ i ].agt_cd+"</agt_cd>\n");
					sb.append( "<supnbr>"+altps[ i ].supnbr+"</supnbr>\n");
					sb.append( "<product_id><![CDATA["+altps[ i ].product_id+"]]></product_id>\n");
					sb.append( "<campaign>"+altps[ i ].campaign+"</campaign>\n");
					sb.append( "<agt_roomtype><![CDATA["+altps[ i ].agt_roomtype+"]]></agt_roomtype>\n");
					sb.append( "<agt_roomtype_name><![CDATA["+altps[ i ].agt_roomtype_name+"]]></agt_roomtype_name>\n");
					sb.append( "<meal_code>"+altps[ i ].meal_code+"</meal_code>\n");
					sb.append( "<room_capa><![CDATA["+altps[ i ].room_capa+"]]></room_capa>\n");
					sb.append( "<max_nr>"+altps[ i ].max_nr+"</max_nr>\n");
					sb.append( "<min_nr>"+altps[ i ].min_nr+"</min_nr>\n");
					sb.append( "<last_day>"+altps[ i ].last_day+"</last_day>\n");
					sb.append( "<start_day>"+altps[ i ].start_day+"</start_day>\n");
					sb.append( "<altdat_from>"+altps[ i ].altdat_from+"</altdat_from>\n");
					sb.append( "<altdat_to>"+altps[ i ].altdat_to+"</altdat_to>\n");
					sb.append( "<price>"+altps[ i ].price+"</price>\n");
					sb.append( "<price_type>"+altps[ i ].price_type+"</price_type>\n");
					sb.append( "<lmjcampaign>"+altps[ i ].lmjcampaign+"</lmjcampaign>\n");
					sb.append( "<price_catch><![CDATA["+altps[ i ].price_catch+"]]></price_catch>\n");
					sb.append( "</product_list>\n");
				}    			
    			sb.append( "</product_lists>\n" );
    			return sb.toString();
    		}catch(Exception ex){	ex.printStackTrace();	}
    		return "";	
    	}
    	return "";
	}*/
}
