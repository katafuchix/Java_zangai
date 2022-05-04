package jp.co.lastminute.Hotel.top;

import jp.co.lastminute.Hotel.Property;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class HCtrConverter {
	private static String pagt_cd = Property.pagt_cd;
	private static final String[] target = {"バス付62", "バス付61"};
	private static final String[] chStr = {"バス付デラックスツイン", "バス付スタンダードツイン"};
	public static String getMealType(String meal_code) {
		try {
			int id = Integer.parseInt(meal_code);
			if (id == 2)	return "朝夕食あり";
			if (id == 4)	return "朝食あり";
			if (id == 6)	return "食事なし";			
		} catch (Exception e){	e.printStackTrace();	}
		return "食事なし";
	}
	public String getMealCodeTxt(String code, String agt_cd ) {
		if( agt_cd.equals( pagt_cd ) ){	return getMealType( code );	}
		if ("00".equals(code))	return "食事なし";
		if ("10".equals(code))	return "朝食あり";
		return "朝夕食あり";
	}
	public static String modifyTxt(String str) {
		for( int i=0; i<target.length; i++){
			if( target[ i ].indexOf(str) >= 0){
				return chStr[ i ];
			}
		}
		return str;
	}
	public static String getRoomTypeTxt(String str) {
		if ( "007".equals(str) ) return  "シングル";
		else if ("107".equals(str)) return  "ツイン";
		else if ("108".equals(str)) return  "ダブル";
		else if ("041".equals(str)) return "洋室タイプ";
		else if ("038".equals(str)) return "和室タイプ";
		else if ("040".equals(str)) return "特別室";
		return "";

	}
	public static String getFooter( String agt_cd ){
		if( agt_cd.equals( pagt_cd ) ){
			return "旅行手配：近畿日本ツーリスト<br/>"
				   +"受付時間：平日 AM 6:00 ～ 翌 AM 3:00 / 休日 AM 8:00 ～ 翌 AM 0:00<br/>"
				   +"当商品をご予約の場合は、申込金をお支払いいただくことなしに、旅行契約の締結となります。<br/>"
				   +"なお、予約操作完了時に表示する「ご予約確認画面」をもって、「ご旅行引受書」としますので、印刷し、保管してください。";			
		}
		return "";
	}
}
