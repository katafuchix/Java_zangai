<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html" encoding="Shift_JIS"/>
<xsl:template match="/">【ラストミニット・ドット・コム】ご注文・ご予約確認
<xsl:value-of select="/ORDER/PRDUCT_SEND/LASTNAME" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/FIRST_NAME" /> 様

この度は、空港駐車場のご予約賜りまして誠に有難うございます。
以下の内容で承りました。当日現地にて代金をお支払い下さい。
尚、お支払いは現金のみでお願いいたします。
予約内容についてお問い合わせをさせて頂く場合がございます。
その場合は、直接ご連絡を取らせていただきます。
あらかじめご了承下さい。
今後のお問い合わせ・ご連絡は、直接、提携駐車場へご連絡を
お願いいたします。
ご利用、誠に有難うございました。

------------------- ご予約内容 --------------------

ORDER NO      : <xsl:value-of select="/ORDER/SUB_ORDER/ORDER_NO" />
SUB ORDER NO  : <xsl:value-of select="/ORDER/SUB_ORDER/SUB_ORDER_NO" />

---------------------------------------------
商品名：羽田空港駐車場サービス
---------------------------------------------

ご利用者名（漢字）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANJI" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANJI" />
ご利用者名（かな）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANA" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANA" />
携帯電話番号： <xsl:value-of select="/ORDER/PRDUCT_SEND/FAX" />

プラン： <xsl:if test="/ORDER/SUB_ORDER/TITLE='A'">空港ターミナル受け渡しプラン  </xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/TITLE='B'">送迎プラン  </xsl:if>
  <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/NIGHT" /> 日間

出発日： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 7, 2)" /> 日</xsl:if>
         <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 3, 2)" /> 日</xsl:if>
出発便： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/DEP_LINE" />
帰着日： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 7, 2)" /> 日 </xsl:if>
         <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 3, 2)" /> 日 </xsl:if>
帰着便： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/ARRIVAL_LINE" />
車種名： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CAR_TYPE" />
車ナンバー： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CAR_NO" />

<xsl:value-of select="/ORDER/MAILCOMMENT" />

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
