package jp.co.lastminute.cart.check.member;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.lastminute.cart.Constants;
import jp.co.lastminute.cart.members.*;
import jp.co.lastminute.cart.members.YearsCalculater;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DflightCheck extends Member implements Serializable, Checkmember {
	public static final int infant_limit = 2;
	/**
	 * コンストラクタ
	 */
	public DflightCheck(){
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
	        chError = formchk.offSet(this.firstname_alp,6,true);
	        this.firstname_alp = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.firstname_alp_flg = 1;
	        }else{
	        	this.firstname_alp_flg = 0;
	        }
	        chError = null;
	        formchk = null;
	        formchk = new jp.co.yobrain.util.form.Check();
	        chError = formchk.offSet(this.lastneme_alp,6,true);
	        this.lastneme_alp = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.lastneme_alp_flg = 1;
	        }else{
	        	this.lastneme_alp_flg = 0;
	        }
	        chError = null;
	        formchk = null;
	        formchk = new jp.co.yobrain.util.form.Check();
	        chError = formchk.offSet(this.sex,0,true);
	        this.sex = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.sex_flg = 1;
	        }else{
	        	this.sex_flg = 0;
	        }
			/**
			 * 日付の確認
			 */
			chError = null;
	        formchk = null;
	        formchk = new jp.co.yobrain.util.form.Check();
	        String datetarget = this.years + this.months + this.days;
	        chError = formchk.offSet(datetarget,3,true);
	        datetarget = chError.getRstr();
	        if(chError.getError() > 0){
	            error_sum++;
	            this.years_flg = 1;
	            this.months_flg = 1;
	            this.days_flg = 1;
	        }else{
	        	this.years_flg = 0;
	            this.months_flg = 0;
	            this.days_flg = 0;
	        }
	        if( this.dDate >= Checkmember.infant_target ){
	        	int[] reInt =  YearsCalculater.getYearsOld( this.targetdate, this.years + this.months + this.days);
	        	System.err.println( "reInt[0] :" + reInt[0] );
	        	System.err.println( "reInt[1] :" + reInt[1] );
	        	System.err.println( "reInt[2] :" + reInt[2] );
	        	if( reInt[1] > infant_limit ){
	        		error_sum++;
		            this.years_flg = 1;
		            this.months_flg = 1;
		            this.days_flg = 1;
	        	}//else if(reInt[1] == infant_limit && reInt[2] > 0){
	        	//	error_sum++;
		        //   this.years_flg = 1;
		        //  this.months_flg = 1;
		        //  this.days_flg = 1;
	        	//}
	        }
	        /**
	         * 幼児をチェックする
	         */
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
		String[] colors =  Constants.colors;
		Member member =　node.getMem();
		String reStr = "";
		//if( !member.isMember()){	return "";	}
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">\n");
			if( member.getDDate() == Checkmember.parent_target ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 代表者　\n");
			}else if( member.getDDate() >= Checkmember.infant_target ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<input type='hidden' name='parentdDate' value=\"" + member.parentdDate + "\">\n");
			sb.append("<tr bgcolor=\"#FFCCFF\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 幼児　\n");
			}else{
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 同行者　"+(member.dDate-10)+"\n");
			}		
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ 0 ] +"\" >\n");
			sb.append("<td>お名前(ローマ字)</td>\n");
			sb.append("<td>");
			sb.append(" 姓(LASTNAME):<input type=\"hidden\" name=\"lastneme_alp\" maxlength=\"16\" size=\"16\" value=\""+ member.getLastnema_alp()+"\" />"+ member.getLastnema_alp() );
			sb.append("/\n");
			sb.append(" 名(FIRSTNAME):<input type=\"hidden\" name=\"firstname_alp\" maxlength=\"16\" size=\"16\" value=\""+ member.getFirstname_alp() +"\" />"+ member.getFirstname_alp() );
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ 0 ] + "\">\n");
			sb.append("<td>性別</td>\n");
			sb.append("<td>\n");
			sb.append( this.getComboPrevStr( "sex", this.SEX_CODD_, this.SEX_, member.getSex() ) + "</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ 0 ] +"\">\n");
			sb.append("<td>生年月日</td>\n");
			sb.append("<td><input type=\"hidden\" name=\"years\" maxlength=\"4\" size=\"5\" value=\""+ member.getYears() +"\" />"+ member.getYears()+" 年 \n");
			sb.append( this.getComboPrevStr( "months", this.MONTH_, member.getMonths() ) + "月\n");
			sb.append( this.getComboPrevStr( "days", this.DAY_, member.getDays() ) + "日生\n");
			sb.append( "　( ご搭乗日の年齢:" + member.getYearOldString() + ") \n" );
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
		String[] colors = Constants.colors;
		Member member =　node.getMem();
		String reStr = "";
		//System.err.println(" member.isMember() ----" + member.isMember() );
		//if( !member.isMember()){	return "";	}
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">\n");
			if( member.getDDate() == 10 ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 代表者　\n");
			}else if( member.getDDate() >= 100 ){
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<input type='hidden' name='parentdDate' value=\"" + member.parentdDate + "\">\n");
			sb.append("<tr bgcolor=\"#FFCCFF\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 幼児　\n");
			}else{
			sb.append("<input type=\"hidden\" name=\"dDate\" value=\""+member.getDDate()+"\">\n");
			sb.append("<tr bgcolor=\"#EEEEEE\">\n");
			sb.append("<td class=\"groupTitle\" colspan=\"2\"> 同行者　"+(member.dDate-10)+"\n");
			}
			if((delaction)&&(  member.getDDate() != 10 )){
				sb.append("<input type=\"checkbox\" name=\"chkdDate\" value=\""+member.getDDate()+"\">Delete");
			}	
			sb.append("</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ member.firstname_alp_flg + member.lastneme_alp_flg ] +"\" >\n");
			sb.append("<td>お名前(ローマ字)</td>\n");
			sb.append("<td bgcolor=\"#FFFFFF\">");
			sb.append(" 姓(LASTNAME):<input type=\"text\" name=\"lastneme_alp\" maxlength=\"16\" size=\"16\" value=\""+ member.getLastnema_alp()+"\" STYLE=\"ime-mode:disabled;\" />");
			sb.append("/\n");
			sb.append(" 名(FIRSTNAME):<input type=\"text\" name=\"firstname_alp\" maxlength=\"16\" size=\"16\" value=\""+ member.getFirstname_alp() +"\" STYLE=\"ime-mode:disabled;\" />");
			sb.append("<br/><font size=\"-1\" color=\"red\">(MAGIWA TARO)</font></td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ member.sex_flg ] + "\">\n");
			sb.append("<td>性別</td>\n");
			sb.append("<td bgcolor=\"#FFFFFF\">");
			sb.append( this.getComboHtmlStr( "sex", this.SEX_CODD_, this.SEX_, member.getSex() ) + "</td>\n");
			sb.append("</tr>\n");
			sb.append("<tr bgcolor=\""+ colors[ member.years_flg + member.months_flg + member.days_flg ] +"\">\n");
			sb.append("<td>生年月日</td>\n");
			sb.append("<td bgcolor=\"#FFFFFF\"><input type=\"text\" name=\"years\" maxlength=\"4\" size=\"5\" value=\""+ member.getYears() +"\" STYLE=\"ime-mode:disabled;\"/> 年 \n");
			sb.append( this.getComboHtmlStr( "months", this.MONTH_, member.getMonths() ) + "月\n");
			sb.append( this.getComboHtmlStr( "days", this.DAY_, member.getDays() ) + "日生\n");
			sb.append("<font size=\"-1\" color=\"red\">(西暦・月・日の順にご記入ください)</font></td>\n");
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
			//System.err.println(",--------," + node.getChildArray().length );
			for( int l=0; l<node.getChildArray().length; l++){	
				//System.err.println(",-------- Node Count Go ," + l );
				if( node.getChildArray()[l] != null ){
					//System.err.println(",-------- Node Count In ," + l );
					Node parentNode = node.getChildArray()[l];
					if( flag == -1){	sb.append( getHtml( parentNode, true ) );
					}else if(  flag == 0 ){	sb.append( getHtml( parentNode ) );
					}else{			sb.append( getPreView( parentNode ) );	}
					sb.append( "\n" );
					for( int s=0; s<parentNode.getChildArray().length; s++){
						//System.err.println(",-------- Node Child Count Ready," + s );
						if( parentNode.getChildArray()[s] != null ){
							//System.err.println(",-------- Node Child Count IN ," + s );
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
