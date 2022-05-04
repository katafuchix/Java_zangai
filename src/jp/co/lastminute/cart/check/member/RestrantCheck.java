package jp.co.lastminute.cart.check.member;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.lastminute.cart.Constants;
import jp.co.lastminute.cart.members.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RestrantCheck extends Member implements Serializable, Checkmember {
	/**
	 * コンストラクタ
	 */
	public RestrantCheck(){
		super( );
	}
	public void setMember( Member member ){
		this.dDate = member.dDate;
		this.firstname_alp = member.firstname_alp;
		this.lastneme_alp = member.lastneme_alp;
		this.firstname_kanji = member.firstname_kanji;
		this.lastname_kanji = member.lastname_kanji;
		this.firstname_hkana = member.firstname_hkana;
		this.lastname_hkana = member.lastname_hkana;
		this.firstname_kkana = member.firstname_kkana;
		this.lastname_kkana = member.lastname_kkana;
		this.sex = member.sex;
		this.years = member.years;
		this.months = member.months;
		this.days = member.days;
		this.passport = member.passport;
		this.telno = member.telno;
		this.targetdate = member.targetdate;
	}
	public boolean Check(){
		try{
			int error_sum = 0;
			CheckError chError = null;
	        Check formchk = new jp.co.yobrain.util.form.Check();
	        chError = formchk.offSet(this.lastname_kanji,1,true);
	        this.lastname_kanji = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.lastname_kanji_flg = 1;
	        }else{
	        	this.lastname_kanji_flg = 0;
	        }
	        chError = null;
	        formchk = null;
	        formchk = new jp.co.yobrain.util.form.Check();
	        chError = formchk.offSet(this.firstname_kanji,1,true);
	        this.firstname_kanji = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.firstname_kanji_flg = 1;
	        }else{
	        	this.firstname_kanji_flg = 0;
	        }
	        chError = null;
	        formchk = null;
	        formchk = new jp.co.yobrain.util.form.Check();
	        chError = formchk.offSet(this.telno,9,true);
	        this.telno = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.telno_flg = 1;
	        }else{
	        	this.telno_flg = 0;
	        }
			if( error_sum == 0){
				return true;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	public void setFirstname_alpBySjis( String str ){
		try{
			this.firstname_alp = new String(str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
		}catch(Exception ex){}
	}
	public void setLastname_alpBySjis( String str ){
		try{
			this.lastneme_alp = new String(str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
		}catch(Exception ex){}
	}
	synchronized public String getPreView( Node node ){
		String[] colors =  {"#F8F8F8", "#ff0099", "#ff0099", "#ff0099", "#ff0099"};
		Member member =　node.getMem();
		String reStr = "";
		if( !member.isMember()){	return "";	}
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">\n");
			if( member.getDDate() == Checkmember.parent_target ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 連絡先　\n");
			}else if( member.getDDate() >= Checkmember.infant_target ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<input type='hidden' name='parentdDate' value=\"" + member.parentdDate + "\">\n");
			sb.append("<tr bgcolor=\"#FFCCFF\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 幼児　\n");
			}else{
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 同行者　\n");
			}		
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ 0 ] +"\" >\n");
			sb.append("<td>お名前</td>\n");
			sb.append("<td>姓：<input type=\"hidden\" name=\"lastname_kanji\" maxlength=\"16\" size=\"16\" value=\""+ member.getLastname_kanji() +"\" />"+ member.getLastname_kanji() +"/");
			sb.append(" 名：<input type=\"hidden\" name=\"firstname_kanji\" maxlength=\"16\" size=\"16\" value=\""+ member.getFirstname_kanji() +"\" />"+ member.getFirstname_kanji() +"\n");
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ 0 ] +"\">\n");
			sb.append("<td>電話番号(携帯)</td>\n");
			sb.append("<td><input type=\"hidden\" name=\"telno\" maxlength=\"4\" size=\"5\" value=\""+ member.getTelno() +"\" />"+ member.getTelno()+" \n");
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("</table>\n");
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}				
		return reStr;
	}
	synchronized public String getHtml( Node node ){
		return getHtml( node, false );
	}
	
	synchronized public String getHtml( Node node, boolean delaction ){
		String[] colors = {"#F8F8F8", "#ff0099", "#ff0099", "#ff0099", "#ff0099"};
		Member member =　node.getMem();
		String reStr = "";
		if( !member.isMember()){	return "";	}
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">\n");
			if( member.getDDate() == 10 ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 連絡先　\n");
			}else if( member.getDDate() >= 100 ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<input type='hidden' name='parentdDate' value=\"" + member.parentdDate + "\">\n");
			sb.append("<tr bgcolor=\"#FFCCFF\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 幼児　\n");
			}else{
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 同行者　\n");
			}
			if((delaction)&&(  member.getDDate() != 10 )){
				sb.append("<input type=\"checkbox\" name=\"chkdDate\" value=\""+member.getDDate()+"\">Delete");
			}	
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ member.firstname_alp_flg + member.lastneme_alp_flg ] +"\" >\n");
			sb.append("<td>お名前</td>\n");
			sb.append("<td bgcolor=\"#FFFFFF\">姓：<input type=\"text\" name=\"lastname_kanji\" maxlength=\"16\" size=\"16\" value=\""+ member.getLastname_kanji() +"\" STYLE=\"ime-mode:abled;\" /> /");
			sb.append(" 名：<input type=\"text\" name=\"firstname_kanji\" maxlength=\"16\" size=\"16\" value=\""+ member.getFirstname_kanji()+"\" STYLE=\"ime-mode:abled;\" />\n");
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ telno_flg ] +"\">\n");
			sb.append("<td>電話番号(携帯)</td>\n");
			sb.append("<td bgcolor=\"#FFFFFF\"><input type=\"text\" name=\"telno\" maxlength=\"21\" size=\"22\" value=\""+ member.getTelno() +"\" STYLE=\"ime-mode:disabled;\"/>  \n");
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("</table>\n");
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}				
		return reStr;
	}
	/**
	 * 
	 */
	public String getArrayHtml( Node node, String prevew ){
		String reStr = "";
		int flag = 1;
		try{
			if( prevew.equals( Constants.PREVIEW )){	
				flag = 0;
			}else if( prevew.equals( Constants.DELETE_ACTION)){
				flag = -1;
			}
			StringBuffer sb = new StringBuffer();
			for( int l=0; l<node.getChildArray().length; l++){	
				if( node.getChildArray()[l] != null ){
					Node parentNode = node.getChildArray()[l];
					if( flag == -1){	sb.append( getHtml( parentNode, true ) );
					}else if(  flag == 0 ){	sb.append( getHtml( parentNode ) );
					}else{			sb.append( getPreView( parentNode ) );	}
					sb.append( "\n" );
					for( int s=0; s<parentNode.getChildArray().length; s++){
						if( parentNode.getChildArray()[s] != null ){
							Node childArray = parentNode.getChildArray()[s];
							if( flag == -1 ){	sb.append( getHtml( childArray, true ));
							}else if( flag == 0 ){	sb.append( getHtml( childArray ) );
							}else{			sb.append( getPreView( childArray ) );	}
							sb.append( "\n" );
						}
					}
				}
			}
			reStr = sb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
}
