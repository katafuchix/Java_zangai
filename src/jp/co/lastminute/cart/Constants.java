package jp.co.lastminute.cart;

import java.util.*;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Constants {
	public static final String path = "/home/alice/xml";
	public static final String[] colors = {"#F8F8F8", "#ffff99", "#ffff99", "#ffff99", "#ffff99"};
	public static final String KeyW = "LMJG";
	public static final String SESSION_COM = "SESSION_COM";
	public static final String CartOrder = "cart.order";
	public static final String CartUser = "cart.user";
	public static final String Cancell_SOrder = "Cancell.order.user";
	public static final String Product_Type_Mapping_ = "xslxmlxsd";
	
	//売上系
	public static final int NOT_AUTH = 0;
	public static final int AUTH_ONLY_ = 1;
	public static final int REAL_SALE_ = 2;
	public static final int SALE_INC_ = 3;
	public static final int ALE_OK_ = 3;
	public static final int AUTH_SALE_OK_ = 4;
	public static final int REAL_SALE_OK_ = 5;
	//変更系
	public static final int IS_MODIFY_OK = 0;
	public static final int IS_COUNTER_OK = 2;
	public static final int IS_MODIFY_NG = 1;
	public static final String CANCELL_LOGIN_NG_PAGE = "/cart/login_ng.jsp";
	//連続投入不可能
	public static final int IS_THROW_NG = 1;
	public static final int IS_THROW_OK = 0;
	//FAX関連
	public static final int IS_FAX_PASS = 0;
	public static final int IS_FAX_NEED = 1;
	//配送先必要
	public static final int IS_SENDING_PASS = 0;
	public static final int IS_SENDING_NEED = 1;
	//付加会員情報
	public static final int IS_MEMBER_PASS = 0;
	public static final int IS_MEMBER_NEED = 1;
	
	public static final int CANNOT_REMOVE_ = 6;
	public static final int CART_IN_ = 0;
	public static final int NOT_CONFIRM_ = 1;
	public static final int CON_CONFIRM_ = 2;
	public static final int CANCLL_STATUS_ = 9;
	
	//クラッキ
    public static final String CRACK_ = "/claking.jsp";
    //商品ライン
    public static final int Gift_ = 93;
    public static final int Ticket_ = 99;
    public static final int Restraunt_ = 91;
    
    //データベース
    public static final int INSERT_ = 1;
    public static final int UPDATE_ = 2;
    //カート
    public static final String CART_START_ = "/cart/CartIn.jsp";
    public static final String CART_FIAL_ = "/cart/cart_fail.jsp";
    public static final String CART_SUCCESS_ = "/xml/booking_success.xml";
    public static final String CART_FAILUR_ = "/xml/booking_fail.xml";
    //カート遷移
    public static String STEP01 = "1";
    public static String STEP01_CANCELL = "2";
    public static String STEP00_MEMBER = "00";
    public static String STEP03_SENDING = "3";
    public static String STEP04_CARD = "4";
    public static String STEP05_PREVIEW = "5";
    public static String STEP05_CONFIRM = "6";
    public static String PREVIEW = "1";
    public static String UPDATE_ACTION = "update.go";
    public static String DELETE_ACTION = "delete.go";
    //チェックログイン
    // ログインページ
    public static final String LOGON_PAGE = "/cart/user/login.jsp";
    public static final String AFTER_CARTID_ = "aftercartin";
    public static final String AFTER_CART_IN_REDIRECT_ = "/card_check.jsp";
    public static final String REDIRECT_PARAMETER_NAME = "redirect";
    // ログイン商品なし
    public static final String NO_PRODUCT = "/cart/no_product.jsp";
    //カレンダー
    public static final String Calendar = "show.Calendar";
    //アクションタイプ
    public static final String CANCELL = "cansel";
    public static final String MEMBER_UPDATE = "member_update";
    public static final String SENDING_UPDATE = "sending_update";
    public static final String CARD_ENDING = "card_ending";
    public static final String COUNTER_UPDATE = "counter_update";
    public static final String Cart_Err_Comment = "Cart_Err_Comment";
    public static final String USER_UPDATE = "user_update";
}
