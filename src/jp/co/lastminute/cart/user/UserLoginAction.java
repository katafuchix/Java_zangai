package jp.co.lastminute.cart.user;

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
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.*;
import jp.co.lastminute.cart.user.jdbc.*;
import jp.co.yobrain.util.form.*;
import jp.co.lastminute.cart.jdbc.*;
import jp.co.lastminute.cart.model.*;
import jp.co.lastminute.cart.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class UserLoginAction extends Action{

    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
            
        HttpSession session = request.getSession();
        ServletContext servletContext = servlet.getServletContext();
        DataSource dss = (DataSource)servletContext.getAttribute(Action.DATA_SOURCE_KEY);
        Profile pf;
        User_Tbl ut;
        User us;
 
        //エラーオブジェクト生成
        //ActionErrors errors = new ActionErrors();
        UserLoginForm actionform = (UserLoginForm)form;
        
        //フォームからデータを受け取る
        String e_mail = actionform.getE_mail();
        String passwd = actionform.getPasswd();
        
        //チェックルーチン
        int error_sum = 0;
        Check formchk;
        CheckError chError;

        //----------------------------------
        //IDのチェック関係
        //----------------------------------

        int id_form_check_frg = 0;

        //IDのフォーマットをチェックする
        chError = null;
        formchk = null;
        formchk = new jp.co.yobrain.util.form.Check();
        chError = formchk.offSet(e_mail,4,true);
        e_mail = chError.getRstr();

        //IDが入力されているかをチェック
        if(e_mail.length() == 0){
            error_sum++;
            actionform.addError_comm( "E MAILが未入力です" );
            id_form_check_frg |= 1;
        }

        //IDのフォーマット違反が有る場合エラー
        if(chError.getError() > 0 && (id_form_check_frg & 1) == 0){
            error_sum++;
            actionform.addError_comm( "E MAILに、不正な入力があります" );
            id_form_check_frg |= 2;
        }

        //IDの文字数をチェック
        if(e_mail.length() > 120 && (id_form_check_frg & 3) == 0){
            error_sum++;
            actionform.addError_comm( "E MAILが文字数制限を越えています" );
            id_form_check_frg |= 4;
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
        	dbAdapterProfile dpf = new dbAdapterProfile(dss);
            us = dpf.findProfile( e_mail );
            if(( us.getUser().getUSER_ID() == 0 )&&( e_mail.length() > 0 )){
                ut = us.getUser();
                ut.setCAMPAIGNUSERFLAG( 0 );
                us.setUser( ut );
                session.setAttribute( Constants.CartUser, us);
                return (mapping.findForward("modify"));
            }
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

        //IDとパスワードの入力に異常が無い場合検索処理を行う
        int user_id = 0;
        if(id_form_check_frg == 0 && passwd_form_check_frg == 0){
            //IDとパスワードがDBに存在するのかをチェック
            dbAdapterUserTbl dut = new dbAdapterUserTbl(dss);
            user_id = dut.checkUser(e_mail,passwd);
            if(user_id == 0 && error_sum == 0){
                error_sum++;
                actionform.addError_comm( "会員登録をしていないようです。新規会員登録をお願いします" );
            }
        }
        //入力値が正しいか？
        if(error_sum > 0){	
            return (new ActionForward(mapping.getInput()));
        }
        //ログイン処理を行う
        dbAdapterProfile dpf = new dbAdapterProfile(dss);
        us = dpf.findProfile( user_id ); //DBからユーザ情報を読み込む        
        session.setAttribute( Constants.CartUser , us);
        //正常遷移
        return (mapping.findForward("success"));
    }
}
