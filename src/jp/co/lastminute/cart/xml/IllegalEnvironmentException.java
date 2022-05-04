package jp.co.lastminute.cart.xml;

/**
 * 外部環境が不正であることを示す。
 * @author <a href="mailto:yasuko.akai@npcsystem.com">Yasuko AKAI</a>
 **/
public class IllegalEnvironmentException extends Exception {

    /**
     * コンストラクタ
     * @param message エラーメッセージ
     */
    public IllegalEnvironmentException(String message) {
        super(message);
    }
}
