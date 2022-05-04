package jp.co.lastminute.cart.xml;

import java.util.Vector;
import java.io.StringReader;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import jp.co.yobrain.util.rpc.PostString;

/**
 * 単純なXML を解析して 内容を PostString の Vector にするユーティリティクラス。
 * ルート要素の直下に、PostString に対応する要素が出現することを想定。
 * 要素名がPostStringの名前、内容が値。
 * 要素が属性を持っている場合はすべて無視する。
 * 名前空間の解析は行わず、Prefix があっても、Prefix 済の要素名をそのまま使用する。
 * （名前空間、Prefix なしのXMLが入力されることを想定しているため）
 * もしも名前空間への対応が必要な場合は SAXParserFactory へ設定を追加すること。
  * @author <a href="mailto:yasuko.akai@npcsystem.com">Yasuko AKAI</a>
 */
public final class XmlToPostStrings {

    /**
     * XML内容を Postメッセージ内容に変換する
     * @param xmlContent XML内容
     * @return Postメッセージ内容 (PostStringのVector)
     * @exception org.xml.sax.SAXException xmlContentのパーズ時の例外(xmlContentの内容が不正等）
     * @exception jp.co.lastminute.basket.xml.IllegalEnvironmentException SAX環境が不正
     * @exception java.lang.NullPointerException xmlContentがnull
     * @exception java.lang.IllegalArgumentException xmlContentに関するIO例外
     **/
    public static Vector toVector(String xmlContent)
        throws IllegalEnvironmentException, SAXException {
        if (xmlContent == null) {
            throw new NullPointerException("xmlContent");
        }       System.err.println("XMLContents\n" + xmlContent );
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();       System.err.println("XmlToPostStrings:37");
            Handler handler = new Handler();                                        System.err.println("XmlToPostStrings:38");
            parser.parse(new InputSource(new StringReader(xmlContent)), handler);       System.err.println("XmlToPostStrings:38");
                                                                                     System.err.println("XmlToPostStrings:39");
            return handler.getVector();
        } catch (ParserConfigurationException e) {
            throw new IllegalEnvironmentException("Could not create SAXParser. "+e.toString());
        } catch (java.io.IOException e) {
            throw new IllegalArgumentException("Unexpected internal IO Exception : "+e.toString());
        }
    }
    
    /**
     * SAXで解析しながら 内部に Vector を溜める内部クラス。
     * ※List でなく Vector なのは単に取得したいのが Vector だから。
     **/
    private static class Handler extends DefaultHandler {
        private Vector vector_ = new Vector();
        private int level_ = 0;
        StringBuffer buffer_ = null;

        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
            level_++;
            if (level_ == 2) {
                buffer_ = new StringBuffer(50);
            }
        }
        public void characters(char[] ch, int start, int length) {
            if (level_ == 2) {
                buffer_.append(ch, start, length);
            }
        }

        public void endElement(String namespaceURI, String localName, String qName) {
            if (level_ == 2) {
                PostString postString = new PostString(qName, buffer_.toString());
                vector_.add(postString);
            }
            level_--;
        }

        public Vector getVector() {
            return vector_;
        }

    }

}
