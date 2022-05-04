package jp.co.yobrain.util.text;

import java.io.*;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Hiragana_Katakana {
    private HashMap hiragana_katakana;
    
    public Hiragana_Katakana(){
        super();
        setHiragana_katakana();
    }
    /**
     * 
     */
    public HashMap getKanaMapping(){
        if( hiragana_katakana == null) initHiragana_katakana();
        return hiragana_katakana;
    }
    /**
     *
     */
    public void initHiragana_katakana(){
        if( hiragana_katakana == null )    setHiragana_katakana();
    }
    /**
     *
     */
    private void setHiragana_katakana(){
        try{
            hiragana_katakana = new HashMap();
            hiragana_katakana.put("を","ヲ");
            hiragana_katakana.put("ぁ","ァ");
            hiragana_katakana.put("ぃ","ィ");
            hiragana_katakana.put("ぅ","ゥ");
            hiragana_katakana.put("ぇ","ェ");
            hiragana_katakana.put("ぉ","ォ");
            hiragana_katakana.put("ゃ","ャ");
            hiragana_katakana.put("ゅ","ュ");
            hiragana_katakana.put("ょ","ョ");
            hiragana_katakana.put("っ","ッ");
            hiragana_katakana.put("ー","ー");
            hiragana_katakana.put("あ","ア");
            hiragana_katakana.put("い","イ");
            hiragana_katakana.put("う","ウ");
            //hiragana_katakana.put("う","ヴ");
            hiragana_katakana.put("え","エ");
            hiragana_katakana.put("お","オ");
            hiragana_katakana.put("か","カ");
            hiragana_katakana.put("が","ガ");
            hiragana_katakana.put("き","キ");
            hiragana_katakana.put("ぎ","ギ");
            hiragana_katakana.put("く","ク");
            hiragana_katakana.put("ぐ","グ");
            hiragana_katakana.put("け","ケ");
            hiragana_katakana.put("げ","ゲ");
            hiragana_katakana.put("こ","コ");
            hiragana_katakana.put("ご","ゴ");
            hiragana_katakana.put("さ","サ");
            hiragana_katakana.put("ざ","ザ");
            hiragana_katakana.put("し","シ");
            hiragana_katakana.put("じ","ジ");
            hiragana_katakana.put("す","ス");
            hiragana_katakana.put("ず","ズ");
            hiragana_katakana.put("せ","セ");
            hiragana_katakana.put("ぜ","ゼ");
            hiragana_katakana.put("そ","ソ");
            hiragana_katakana.put("ぞ","ゾ");
            hiragana_katakana.put("た","タ");
            hiragana_katakana.put("だ","ダ");
            hiragana_katakana.put("ち","チ");
            hiragana_katakana.put("ぢ","ヂ");
            hiragana_katakana.put("つ","ツ");
            hiragana_katakana.put("づ","ヅ");
            hiragana_katakana.put("て","テ");
            hiragana_katakana.put("で","デ");
            hiragana_katakana.put("と","ト");
            hiragana_katakana.put("ど","ド");
            hiragana_katakana.put("な","ナ");
            hiragana_katakana.put("に","ニ");
            hiragana_katakana.put("ぬ","ヌ");
            hiragana_katakana.put("ね","ネ");
            hiragana_katakana.put("の","ノ");
            hiragana_katakana.put("は","ハ");
            hiragana_katakana.put("ば","バ");
            hiragana_katakana.put("ぱ","パ");
            hiragana_katakana.put("ひ","ヒ");
            hiragana_katakana.put("び","ビ");
            hiragana_katakana.put("ぴ","ピ");
            hiragana_katakana.put("ふ","フ");
            hiragana_katakana.put("ぶ","ブ");
            hiragana_katakana.put("ぷ","プ");
            hiragana_katakana.put("へ","ヘ");
            hiragana_katakana.put("べ","ベ");
            hiragana_katakana.put("ぺ","ペ");
            hiragana_katakana.put("ほ","ホ");
            hiragana_katakana.put("ぼ","ボ");
            hiragana_katakana.put("ぽ","ポ");
            hiragana_katakana.put("ま","マ");
            hiragana_katakana.put("み","ミ");
            hiragana_katakana.put("む","ム");
            hiragana_katakana.put("め","メ");
            hiragana_katakana.put("も","モ");
            hiragana_katakana.put("や","ヤ");
            hiragana_katakana.put("ゆ","ユ");
            hiragana_katakana.put("よ","ヨ");
            hiragana_katakana.put("ら","ラ");
            hiragana_katakana.put("り","リ");
            hiragana_katakana.put("る","ル");
            hiragana_katakana.put("れ","レ");
            hiragana_katakana.put("ろ","ロ");
            hiragana_katakana.put("わ","ワ");
            hiragana_katakana.put("ん","ン");
        }catch( MissingResourceException mre ){
            mre.printStackTrace();
        }
    }

}
