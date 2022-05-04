package jp.co.lastminute.Hotel.detail.Agent;

import java.io.*;
import java.util.*;
import jp.co.lastminute.Hotel.detail.*;
import jp.co.lastminute.Hotel.detail.Form;

import jp.co.yobrain.util.rpc.PostString;
import jp.co.yobrain.util.*;
import jp.co.yobrain.util.form.*;
import jp.co.yobrain.util.form.Check;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class KNT extends SendParam implements CheckAllote, Serializable{
	public KNT(){}	//コンストラクター
	public static final String url_ = "http://shop.knt.co.jp/asp-bin/lmj/3101.asp";
	public static String confirm_url_ = "";
	public String getConfirmUrl(){
		return confirm_url_;
	}
	/**
	 * チェックする
	 */
	public String resultresultFromWebsite( Vector paramsVector) {
		return resultresultFromWebsite( url_, paramsVector);
	}
	/**
	 * ベクターをチェックする
	 */
	public Vector setParameter( Form condition ){
		condition = checkValue( condition );
		if( condition.getViewErrorCopmment("").length() == 0){
			Vector paramsVector = new Vector();
			paramsVector.add( new PostString("A_COURSE_NUM", condition.getProduct_id() ));
			paramsVector.add( new PostString("N_SYOKUJI", condition.meal_cd ));
			paramsVector.add( new PostString("CONST_M_DEPT_DATE", condition.checkInMonth ));
			paramsVector.add( new PostString("CONST_D_DEPT_DATE", condition.checkInDay ));
			int night = Integer.parseInt( condition.nights ) + 1;
			paramsVector.add( new PostString("A_HAKU_CNT", "" + night + "" ));
			paramsVector.add( new PostString("N_SITUSU", condition.numberOfRooms ));
			if( condition.numberOfMales.length() > 0 ){
				paramsVector.add( new PostString("N_M_NUM", condition.numberOfMales ));
				paramsVector.add( new PostString("N_F_NUM", condition.numberOfFemales ));
				
			}else{
				paramsVector.add( new PostString("N_M_NUM", condition.numberOfAdults ));
			}
			paramsVector.add( new PostString("N_C_SCHOOL", condition.numberOfKids ));
			paramsVector.add( new PostString("N_C_YOJI", condition.numberOfYoji1 ));
			paramsVector.add( new PostString("N_C_YOJI2", condition.numberOfYoji2 ));			
			//	A_COURSE_NUM
			//	N_SYOKUJI
			// 	CONST_M_DEPT_DATE
			//	CONST_D_DEPT_DATE
			//	A_HAKU_CNT
			//	N_SITUSU
			//	N_M_NUM
			//	N_F_NUM
			//	N_C_SCHOOL
			//	N_C_YOJI
			//	N_C_YOJI2
			return paramsVector;
		}
		return null;
	}

	/**
	 * チェック
	 */
	//public CheckError checkValue(String str,int type,boolean need) throws Exception{
	/**
	 * 内容のチェック
	 */
	public Form checkValue( Form condition ){
		condition.clearError_comm();
		try{
			CheckError ch = checkValue(modifyCourseNumber( condition.getProduct_id()), 1, true );
			condition.setProduct_id( ch.getRstr());
			if( ch.getError() > 0 ){
				condition.addError_comm("商品の選択が不正です");
			}
			ch = null;
			//
			ch = checkValue( condition.meal_cd , 1, true );
			condition.meal_cd = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("食事の選択が不正です");
			}
			ch = null;
			ch = checkValue( condition.checkInDate, 3, true );
			condition.checkInDate = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("日付の選択が不正です");
			}else{
				condition.checkInMonth = condition.checkInDate.substring(4, 6);
				condition.checkInDay = condition.checkInDate.substring(6);
			}
			ch = null;
			ch = checkValue( condition.nights , 0, true );
			condition.nights = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("宿泊数が不正です");
			}
			ch = null;
			ch = checkValue( condition.numberOfRooms , 0, false );
			condition.numberOfRooms = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("部屋数が不正です");
			}
			ch = null;
			ch = checkValue( condition.numberOfAdults , 0, false );
			condition.numberOfAdults = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("大人人数が不正です");
			}
			ch = null;
			ch = checkValue( condition.numberOfFemales , 0, false );
			condition.numberOfFemales = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("女性が不正です");
			}
			ch = null;
			ch = checkValue( condition.numberOfKids , 0, false );
			condition.numberOfKids = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("児童数が不正です");
			}
			ch = null;
			ch = checkValue( condition.numberOfYoji1 , 0, false );
			condition.numberOfYoji1 = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("幼児の入力が不正です");
			}
			ch = null;
			ch = checkValue( condition.numberOfYoji2 , 0, false );
			condition.numberOfYoji2 = ch.getRstr();
			if( ch.getError() > 0 ){
				condition.addError_comm("乳児の入力が不正です");
			}
			ch = null;
		}catch(Exception ex){}
		return condition;
	}
	// 	TYPE_NUMBER_ = 0;
	//	TYPE_STRING_ = 1;
	//	TYPE_FLOAT_ = 2;
	//	TYPE_DATE_ =  3;
	//	TYPE_EMAIL_ = 4;
	//	TYPE_MONEY_ = 5;
	//	TYPE_ALPHABET_ = 6;
	//	TYPE_ALPHABETNUMBER_ = 7;
	//	TYPE_DATETIME_ =  8;
	//	TYPE_PHONE_ =  9;
	//	TYPE_TWOBYTE_ =  10;
}
