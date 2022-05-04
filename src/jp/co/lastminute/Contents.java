/*
 * Contents.java
 *
 * Created on 2002/04/14, 22:20
 */

package jp.co.lastminute;

/**
 *
 * @author  skondo
 * @version 
 */
public final class Contents {

    // index
    public static final String INDEX_PAGE = "/index.jsp";
    // ログインページ
    public static final String LOGON_PAGE = "/user/login.jsp";
    //
    public static final int UNSELECTED_VALUE = -1;
    public static final String CATEGORY_TABLE_KEY = "category";
    
    
    //ログインショッピング
    public static final String ORDERS_ = "orders";
    public static final String CARD_ = "card_result";
    public static final String ORDERS_CONTOL_ = "orders_control_";
    public static final String CART_START_ = "/basket/cart.jsp";
    public static final String CART_FIAL_ = "/basket/cart_fail.jsp";
    public static final String CART_DESC_ = "/basket/cart_description_e.jsp";
    public static final String CART_DESC_NOR_ = "/basket/cart_description.jsp";
    public static final String CARD_CHECK_PAGE_ = "/basket/card_check.jsp";
    public static final String CART_CONFIRM_ = "/basket/cardconfirm.jsp";
    public static final String PRODUCT_SEND_PAGE_ = "/basket/product_send.jsp";
    public static final String PRODUCT_SEND_TOURS_ = "/basket/product_send_tours.jsp";
    public static final String PRODUCT_SEND_FLIGHTS_ = "/basket/product_send_flights.jsp";
    public static final String NO_SHOPPING_ = "/basket/no_shopping.jsp";
    public static final String CART_SUCCESS_ = "/xml/booking_success.xml";
    public static final String CART_FAIL_ = "/xml/booking_fail.xml";
    public static final String AFTER_CARTID_ = "aftercartin";	// add 20020609 by skondo
    public static final String ASP_CART_SESSION_ = "asp_cart_session";  // add 20020612 by skondo
    public static final String AFTER_CART_IN_REDIRECT_ = "/card_check.jsp";	// add 20020609 by skondo
    public static final String NO_PRODUCT_ = "/no_goods.jsp";
    public static final String BAD_OPERATION_ = "/basket/bad_operation.jsp";
    
    public static final String ADD_ON_SESSION_ = "add_on_session";
    //クラッキ
    public static final String CRACK_ = "/claking.jsp";
    public static final String CCTYPE_ARRAY_KEY_ = "cctype";
    public static final String CCTYPE_ARRAY_KEY_CT_ = "cccttype";
    public static final String BYEARS_ = "byear";
    public static final String CYEARS_ = "cyear";
    public static final String CMONTHS_ = "cmonth";
    public static final String CDAY_ = "cday";
    public static final String CPAX_ = "cpax";
    public static final String TITLES_ = "titles";
    public static final String BSTATE_ = "bstates";
    public static final String STATE_ = "states";
    public static final String NSTATE_ = "nstates";
    public static final String AMPM_ = "ampm";
    public static final String HOURS_ = "hours";
    public static final String MINUTES_Q_ = "minute_q";
    //XSL　マッピング
    public static final String X_L_ = "xslxmlxsd";
     
    public static final String USER_ = "userTable";
    public static final String USER_KEY_ = "userTable";
    
    //検索状態
    public static final String SERCH_CONDITION_ = "searchcondition";
    //
    public static final String DATABASE_KEY = "database";
    
    
    // TopDestination
    public static final String TOPDESTINATION_  = "topdestination";
    // TOP SELECTION
    public static final String SPECIALSELECTION_ = "selection";
    // DOMESTIC HOTEL
    public static final String DOMESTICHOTEL_ = "domestic_hotel";
    // DOMESTIC TOURS
    public static final String DOMESTICTOURS_ = "domestic_tours";
    //
    public static final String OVERSEASTOURS_ = "oveeseatours";
    public static final String OVERSEAHOTELS_ = "overseahotels";
    public static final String OVERSEAAIRTICKET_ = "overseaairticket";
    public static final String RESTRANTS_ = "restrants";
    public static final String ENTERTAINMENT_ = "entertainment";
    public static final String GIFT_ = "gift";
    public static final String SPORT_RELAXATION_ = "sport_relaxation";
    public static final String SEX_ = "sex_sex";
}
