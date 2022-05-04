package jp.co.lastminute.cart.user;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import jp.co.lastminute.cart.Constants;
import jp.co.lastminute.cart.mailsender.MailSender;
import jp.co.lastminute.cart.user.jdbc.dbAdapterUserTbl;
import jp.co.lastminute.cart.user.model.PostalCode;
import jp.co.lastminute.cart.user.model.Profile;
import jp.co.lastminute.cart.user.model.User_Tbl;
import jp.co.yobrain.util.form.Check;
import jp.co.yobrain.util.form.CheckError;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class UserUpdateAction extends Action{

    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
            
        HttpSession session = request.getSession();
        
        if( session.getAttribute( Constants.CartUser ) == null ){
        	return (mapping.findForward("notlogin"));
        }
        
        ServletContext servletContext = servlet.getServletContext();
        DataSource dss = (DataSource)servletContext.getAttribute(Action.DATA_SOURCE_KEY);
        Profile pf;
        User_Tbl ut;
        User us;
//        PostalCode pc;

        //エラーオブジェクト生成
        //ActionErrors errors = new ActionErrors();
		
        //フォームからデータを受け取る
        UserRegistrationForm actionform = (UserRegistrationForm)form;
        String e_mail = actionform.getE_mail();
        String passwd = actionform.getPasswd();
        String oncepasswd = actionform.getOncepasswd();
        String sei_kanji = actionform.getSei_kanji();
        String na_kanji = actionform.getNa_kanji();
        String sex = actionform.getSex();
        String postal_cda = actionform.getPostal_cda();
        String postal_cdb = actionform.getPostal_cdb();
        String html_mail_ok = actionform.getHtml_mail_ok();
        //////////////////////////////////////////////////////////////////////
        String sei_kana = actionform.getSei_kana();
        String na_kana = actionform.getNa_kana();
        String state_cd = actionform.getState_cd();
        String city_name = actionform.getCity_name();
        String address = actionform.getAddress();
        String tel_no = actionform.getTel_no();
        String birthyear = actionform.getBirthyear();
        String birthmonth = actionform.getBirthmonth();

        //チェックルーチン
        int error_sum = 0;
        Check formchk;
        CheckError chError;

        ////////////////////////////////////////////////////////////////////////////////////////
        //----------------------------------
        //E-MAILのチェック関係
        //----------------------------------

        int email_form_check_frg = 0;

        //E-MAILのフォーマットをチェックする
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(e_mail,4,true);
        e_mail = chError.getRstr();
        //E-MAILが入力されているかをチェック
        if(e_mail.length() == 0){
            error_sum++;
            actionform.addError_comm( "E MAILが未入力です" );
            email_form_check_frg |= 1;
        }

        //E-MAILのフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (email_form_check_frg & 1) == 0){
            error_sum++;	
            actionform.addError_comm( "E MAILに、不正な入力があります" );
            email_form_check_frg |= 2;
        }

        //E-MAILの文字数をチェック
        if(e_mail.length() > 120 && (email_form_check_frg & 3) == 0){
            error_sum++;	
            actionform.addError_comm( "E MAILが文字数制限を越えています" );
            email_form_check_frg |= 4;
        }

        //----------------------------------
        //パスワードのチェック関係
        //----------------------------------

        int passwd_form_check_frg = 0;

        //パスワードのフォーマットをチェックする
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(passwd,7,true);
        passwd = chError.getRstr();

        //パスワードが入力されているかをチェック
        if(passwd.length() == 0){
            error_sum++;
            actionform.addError_comm( "パスワードが未入力です" );
            passwd_form_check_frg |= 1;
        }
        
        //パスワードのフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (passwd_form_check_frg & 1) == 0){
            error_sum++;	
            actionform.addError_comm( "パスワードに不正な文字が含まれています" );
            passwd_form_check_frg |= 2;
        }

        //パスワードの文字数をチェック
        if(passwd.length() > 10 && (passwd_form_check_frg & 3) == 0){
            error_sum++;
            actionform.addError_comm( "パスワードは3文字以上、10文字以内です" );
            passwd_form_check_frg |= 4;
        }

        //----------------------------------
        //パスワード再入力のチェック関係
        //----------------------------------

        //パスワード再入力のフォーマットをチェックする
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(oncepasswd,7,true);
        oncepasswd = chError.getRstr();

        //パスワード再入力が入力されているかをチェック
        if(oncepasswd.length() == 0){
            error_sum++;	
            actionform.addError_comm( "確認用パスワードが未入力です" );
            passwd_form_check_frg |= 8;
        }
        
        //パスワード再入力のフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (passwd_form_check_frg & 8) == 0){
            error_sum++;
            actionform.addError_comm( "確認用パスワードに不正な文字が含まれています" );
            passwd_form_check_frg |= 16;
        }

        //パスワードとパスワードの再入力が同じかをチェック
        if(!passwd.equals(oncepasswd) && passwd_form_check_frg == 0){
            error_sum++;
            actionform.addError_comm( "パスワードと確認用パスワードの入力値が異なっています" );
            passwd_form_check_frg |= 32;
        }
        
        //----------------------------------
        //名前-姓漢字のチェック関係
        //----------------------------------

        //名前-姓漢字のフォーマットをチェックする
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(sei_kanji,1,true);
        sei_kanji = chError.getRstr();

        //名前-姓漢字が入力されているかをチェック
         if(sei_kanji.length() == 0){
            error_sum++;
            actionform.addError_comm( "姓　漢字の入力が間違っています" );
        }

        //名前-姓漢字の文字数をチェック
        if(sei_kanji.length() > 30){
            error_sum++;
            actionform.addError_comm( "姓　漢字の文字数が制限を越えています" );
        }

        //----------------------------------
        //名前-名漢字のチェック関係
        //----------------------------------

        //名前-名漢字のフォーマットをチェックする
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(na_kanji,1,true);
        na_kanji = chError.getRstr();

        //名前-名漢字が入力されているかをチェック
        if(na_kanji.length() == 0){
            error_sum++;
            actionform.addError_comm( "名　漢字の入力が間違っています" );
        }

        //名前-名漢字の文字数をチェック
        if(na_kanji.length() > 30){
            error_sum++;
            actionform.addError_comm( "名　漢字の文字数が制限を越えています" );
        }
        ////////////////////////////////////////////////////////
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(sei_kana,1,true);
        sei_kana = chError.getRstr();
        if(chError.getError() > 0){
            error_sum++;
            actionform.addError_comm( "姓　かなの入力が間違っています" );
        }
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(na_kana,1,true);
        na_kana = chError.getRstr();
        if(chError.getError() > 0){
            error_sum++;
            actionform.addError_comm( "名　かなの入力が間違っています" );
        }
        //----------------------------------
        //性別のフォーマットのチェック関係
        //----------------------------------

        int sex_form_check_frg = 0;

        //性別のフォーマットをチェック
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(sex,0,true);
        sex = chError.getRstr();

        //性別が入力されているかをチェック
         if(sex.length() == 0){
            error_sum++;
            actionform.addError_comm( "性別が未入力です" );
            sex_form_check_frg |= 1;
        }
        
        //性別のフォーマット違反が有る場合エラー
        int nsex = 0;
        if(chError.getError() > 0){	
            if((sex_form_check_frg & 1) == 0){
                error_sum++;
                actionform.addError_comm( "性別の入力値が間違っています" );
            }
        }else{
            nsex = Integer.parseInt(sex);
        }

        //----------------------------------
        //郵便番号（上）のチェック関係
        //----------------------------------

        int postal_form_check_frg = 0;

        //郵便番号（上）のフォーマットをチェック
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(postal_cda,0,true);
        postal_cda = chError.getRstr();

        //郵便番号（上）が入力されているかをチェック
         if(postal_cda.length() == 0){	
            error_sum++;
            actionform.addError_comm( "郵便番号の入力値が間違っています" );
            postal_form_check_frg |= 1;
        }

        //郵便番号（上）のフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (postal_form_check_frg & 1) == 0){
            error_sum++;		
			actionform.addError_comm( "郵便番号の入力値が間違っています" );
            postal_form_check_frg |= 2;
        }

        //郵便番号（上）の文字数をチェック
        postal_cda = chError.getRstr();
        if(postal_cda.length() != 3 && (postal_form_check_frg & 3) == 0){
            error_sum++;
            actionform.addError_comm( "郵便番号の入力値が間違っています" );
            postal_form_check_frg |= 4;
        }

        //----------------------------------
        //郵便番号（下）のチェック関係
        //----------------------------------

        //郵便番号（下）のフォーマットをチェック
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(postal_cdb,0,true);
        postal_cdb = chError.getRstr();

        //郵便番号（下）が入力されているかをチェック
         if(postal_cdb.length() == 0){
            error_sum++;
            actionform.addError_comm( "郵便番号の入力値が間違っています" );
            postal_form_check_frg |= 8;
        }
        
        //郵便番号（下）のフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (postal_form_check_frg & 8) == 0){
            error_sum++;
            actionform.addError_comm( "郵便番号の入力値が間違っています" );
            postal_form_check_frg |= 16;
        }

        //郵便番号（下）の文字数をチェック
        if(postal_cdb.length() != 4 && (postal_form_check_frg & 24) == 0){
            error_sum++;
            actionform.addError_comm( "郵便番号の入力値が間違っています" );
            postal_form_check_frg |= 32;
        }
/////////////////////////////////////////////
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(city_name,1,true);
        city_name = chError.getRstr();
        if(chError.getError() > 0){
            error_sum++;
            actionform.addError_comm( "都市名の入力が間違っています" );
        }
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(address,1,true);
        address = chError.getRstr();
        if(chError.getError() > 0){
            error_sum++;
            actionform.addError_comm( "住所の入力が間違っています" );
        }
        
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(tel_no,9,true);
        tel_no = chError.getRstr();	
        if(chError.getError() > 0){
            error_sum++;
            actionform.addError_comm( "電話番号の入力が間違っています" );
        }
        //----------------------------------
        //HTMLメールのチェック関係
        //----------------------------------

        int htmlmailok_form_check_frg = 0;

        //HTMLメールのフォーマットをチェック
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(html_mail_ok,7,true);
        html_mail_ok = chError.getRstr();

        //HTMLメールが入力されているかをチェック
         if(html_mail_ok.length() == 0){
            error_sum++;
            actionform.addError_comm( "OKフラグの入力が間違っています" );
            htmlmailok_form_check_frg |= 1;
        }
        
        //HTMLメールのフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (htmlmailok_form_check_frg & 1) == 0){
            error_sum++;
            actionform.addError_comm( "OKフラグの入力が間違っています" );
            htmlmailok_form_check_frg |= 2;
        }

        //HTMLメールの文字数をチェック
        if(html_mail_ok.length() != 1 && (htmlmailok_form_check_frg & 3) == 0){
            error_sum++;
            actionform.addError_comm( "OKフラグの入力が間違っています" );
            htmlmailok_form_check_frg |= 4;
        }
        //----------------------------------
        //DBを参照するチェック関係
        //----------------------------------

        //ID(E-MAIL)とパスワードの入力に異常が無い場合検索処理を行う
        int user_id = ((UserRegistrationForm) form).getUser_id();
        boolean update_flg = false;
        if(email_form_check_frg == 0 && passwd_form_check_frg == 0){
            //IDが実際にDBに存在するのかをチェック(すでに登録されている場合はエラー)
            //user_id = dut.checkUser(e_mail);
            dbAdapterUserTbl dut = new dbAdapterUserTbl(dss);
            int tempuser_id = dut.checkUserRegist( e_mail )[0];         
            if( tempuser_id != user_id && tempuser_id != 0){
            	error_sum++;
                actionform.addError_comm( "このID/パスワードは既に登録されています" );
            }
        }
        //郵便番号の入力に異常が無い場合検索処理を行う
        PostalCode[] pocd = null;
        String pocdstr = postal_cda + postal_cdb;
        int state_cd_int = 0;
        ////////////////////////////////////////////
        try{
            state_cd_int = Integer.parseInt( state_cd );
        }catch(Exception e){
            error_sum++;
            actionform.addError_comm( "都道府県が選択されていません" );
        }
        int birthday = 0;
        try{
            if((birthyear.length() > 0)&&(birthmonth.length()> 0)){
                birthday =  Integer.parseInt( birthyear + birthmonth );
            }else{
                error_sum++;
                actionform.addError_comm( "生年月日に不正な値が入力されています" );
            }
        }catch(Exception e){
            error_sum++;
            actionform.addError_comm( "生年月日に不正な値が入力されています" );
        }
        //エラーが発生した場合フォームに戻り、正常の場合はDBに追加書き込みの処理を行う
        if(error_sum == 0){
        	if( !((UserRegistrationForm)form).getThrowflg().equals( Constants.USER_UPDATE )){
       			((UserRegistrationForm)form).setThrowflg( Constants.PREVIEW );
        		return (new ActionForward(mapping.getInput()));
        	}
        	
        	dbAdapterUserTbl dut = new dbAdapterUserTbl(dss);
            
            
            //各モデルの初期化
            pf = new Profile();     //Profile
            ut = new User_Tbl();    //User_Tbl
            us = new User();        //User
            //pc = new PostalCode(); //PostalCode

            //User_Tblのデータをセットする
            ut.setUSER_ID(user_id);
            ut.setE_MAIL(e_mail);
            ut.setPASSWD(passwd);
            ut.setAUTH_FLG("");
            ut.setHTML_MAIL_OK(html_mail_ok);
            ut.setMAIL_MAG_OK("");
            ut.setCAMPAIGNUSERFLAG(0);
            ut.setDEALWATCHID("AAAA");
            us.setUser(ut);

            //Profileのデータをセットする
            pf.setUSER_ID(user_id);
            pf.setSEI_KANA(sei_kana);
            pf.setSEI_KANJI(sei_kanji);
            pf.setNA_KANA(na_kana);
            pf.setNA_KANJI(na_kanji);
            pf.setFIRST_NAME("");
            pf.setLASTNAME("");
            pf.setSEX(nsex);
            pf.setBIRTH_DAY( birthday );
            pf.setPOSTAL_CD( pocdstr );
            pf.setSTATE_CD( state_cd_int );
            pf.setCITY_NAME(city_name);
            pf.setADDRESS(address);
            pf.setTEL_NO(tel_no);
            pf.setFAX("");
            us.setProfile(pf);

            //DBに登録をする
            boolean db_success = false;
            db_success = dut.modifyUserTbl(ut, pf); //UserTblの追加
                                  
			if( db_success ){
	            //セッションで次の画面に渡す
	            session.setAttribute(Constants.CartUser ,us);
	            return (mapping.findForward("success_fail"));	//正常遷移
			}else{
				actionform.addError_comm( "システム障害です" );
			}
        }
		return (new ActionForward(mapping.getInput()));
        //次の画面へ飛ぶ
    }
}
