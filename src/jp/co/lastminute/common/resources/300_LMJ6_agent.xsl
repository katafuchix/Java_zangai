<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html" encoding="Shift_JIS"/>
<xsl:template match="/">【ラストミニット・ドット・コム】手配依頼メール
lastminute 申し込みメール
ホワイトパーキング　御中

お世話になっております。
以下の内容にて手配依頼を受けましたので、
ご確認をお願いいたします。
なお、お客様へは当日現金でのお支払いをお願いしております。
何卒宜しくお願い申し上げます。

株式会社ラストミニット・ドット・コム
03-3526-6221
supply@lastminute.co.jp

------------------- ご予約内容 --------------------

ORDER NO      : <xsl:value-of select="/ORDER/SUB_ORDER/ORDER_NO" />
SUB ORDER NO  : <xsl:value-of select="/ORDER/SUB_ORDER/SUB_ORDER_NO" />

---------------------------------------------
商品名：羽田空港駐車場サービス
---------------------------------------------

メールアドレス： <xsl:value-of select="/ORDER/PRDUCT_SEND/E_MAIL" />

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

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
