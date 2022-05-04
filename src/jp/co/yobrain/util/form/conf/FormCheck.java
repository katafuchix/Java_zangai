package jp.co.yobrain.util.form.conf;

import jp.co.yobrain.util.form.*;

/**
* FormDateチェック用インターフェース
*
*/

public interface FormCheck{
	/**
	* 文字列のコンバート及びエラーチェック
	* @return	CheckError	CheckErrorオブジェクト
	* @param	valu	チェックする文字列
	*/
	public CheckError convertCheck(String valu, CheckError checkError);

        /** 数値型を宣言 */
	public final static int TYPE_NUMBER_ = 0;
	/** 文字列型を宣言 */
	public final static int TYPE_STRING_ = 1;
	/** 小数点型を宣言 */
	public final static int TYPE_FLOAT_ = 2;
	/** 日付型を宣言 */
	public final static int TYPE_DATE_ =  3;
	/** E_MAIL型を宣言 */
	public final static int TYPE_EMAIL_ = 4;
	/** 通貨型を宣言 */
	public final static int TYPE_MONEY_ = 5;
	/** アルファベット型を宣言 */
	public final static int TYPE_ALPHABET_ = 6;
	/** 英数字型を宣言 */
	public final static int TYPE_ALPHABETNUMBER_ = 7;
	/** 日時型を宣言 */
	public final static int TYPE_DATETIME_ =  8;
	/** 電話型を宣言 */
	public final static int TYPE_PHONE_ =  9;
        /** 2バイト */
        public final static int TYPE_TWOBYTE_ =  10;
        /** タイプ数 */
	public final static int MAX = 11;
        
	/**
	*全角カタカナを返す
	*/
	public static final char[][] KANA = {
		{0x3002},				// 。
		{0x300c},				// 「
		{0x300d},				// 」
		{0x3001},				// 、
		{0x30fb},				// ・
		{0x30f2},				// ヲ
		{0x30a1},				// ァ
		{0x30a3},				// ィ
		{0x30a5},				// ゥ
		{0x30a7},				// ェ
		{0x30a9},				// ォ
		{0x30e3},				// ャ
		{0x30e5},				// ュ
		{0x30e7},				// ョ
		{0x30c3},				// ッ
		{0x002d},				// －
		{0x30a2},				// ア
		{0x30a4},				// イ
		{0x30a6,0x30f4},		// ウ,ヴ
		{0x30a8},				// エ
		{0x30aa},				// オ
		{0x30ab,0x30ac},		// カ,ガ
		{0x30ad,0x30ae},		// キ,ギ
		{0x30af,0x30b0},		// ク,グ
		{0x30b1,0x30b2},		// ケ,ゲ
		{0x30b3,0x30b4},		// コ,ゴ
		{0x30b5,0x30b6},		// サ,ザ
		{0x30b7,0x30b8},		// シ,ジ
		{0x30b9,0x30ba},		// ス,ズ
		{0x30bb,0x30bc},		// セ,ゼ
		{0x30bd,0x30be},		// ソ,ゾ
		{0x30bf,0x30c0},		// タ,ダ
		{0x30c1,0x30c2},		// チ,ヂ
		{0x30c4,0x30c5},		// ツ,ヅ
		{0x30c6,0x30c7},		// テ,デ
		{0x30c8,0x30c9},		// ト,ド
		{0x30ca},				// ナ
		{0x30cb},				// ニ
		{0x30cc},				// ヌ
		{0x30cd},				// ネ
		{0x30ce},				// ノ
		{0x30cf,0x30d0,0x30d1},	// ハ,バ,パ
		{0x30d2,0x30d3,0x30d4},	// ヒ,ビ,ピ
		{0x30d5,0x30d6,0x30d7},	// フ,ブ,プ
		{0x30d8,0x30d9,0x30da},	// ヘ,ベ,ペ
		{0x30db,0x30dc,0x30dd},	// ホ,ボ,ポ
		{0x30de},				// マ
		{0x30df},				// ミ
		{0x30e0},				// ム
		{0x30e1},				// メ
		{0x30e2},				// モ
		{0x30e4},				// ヤ
		{0x30e6},				// ユ
		{0x30e8},				// ヨ
		{0x30e9},				// ラ
		{0x30ea},				// リ
		{0x30eb},				// ル
		{0x30ec},				// レ
		{0x30ed},				// ロ
		{0x30ef},				// ワ
		{0x30f3},				// ン
		{0x309b},				//
		{0x309c}				//
	};

	/**
	*半角数字を返す
	*/
	public static final String NUMBER =
		"\u002d\u002e\u002f\u0030\u0031\u0032" +		// - . / 0 1 2
		"\u0033\u0034\u0035\u0036\u0037\u0038" +		// 3 4 5 6 7 8
		"\u0039";										// 9

	/**
	*半角英字を返す
	*/
	public static final String ALPHABET =
		"\u0040\u0041\u0042\u0043\u0044\u0045\u0046\u0047\u0048\u0049" +	/** @ A B C D E F G H I	*/
		"\u004a\u004b\u004c\u004d\u004e\u004f\u0050\u0051\u0052" +			/** J K L M N O P Q R	*/
		"\u0053\u0054\u0055\u0056\u0057\u0058\u0059\u005a\u005b" +			/** S T U V W X Y Z [	*/
		"\u00a5\u005d\u005e\u005f\u0060\u0061\u0062\u0063\u0064" +			/** \ ] ^ _ ` a b c d	*/
		"\u0065\u0066\u0067\u0068\u0069\u006a\u006b\u006c\u006d" +			/** e f g h i j k l m	*/
		"\u006e\u006f\u0070\u0071\u0072\u0073\u0074\u0075\u0076" +			/** n o p q r s t u v	*/
		"\u0077\u0078\u0079\u007a";											/** w x y z				*/

	/**
	*文字エンコードタイプ定数です。
	*/
	public static final String CODE = "SJIS";


}