<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html" encoding="Shift_JIS"/>
<xsl:template match="/">【ラストミニット・ドット・コム】手配依頼メール
lastminute 申し込みメール
バーターリンク株式会社　御中

お世話になっております。
以下の内容にて依頼を受けましたので、
ご対応をお願いいたします。
なお、お客様へのご連絡は、下記の方法を厳守してください。
何卒宜しくお願い申し上げます。

株式会社ラストミニット・ドット・コム
03-3526-6221
supply@lastminute.co.jp

------------------- ご予約内容 --------------------

ORDER NO      : <xsl:value-of select="/ORDER/SUB_ORDER/ORDER_NO" />
SUB ORDER NO  : <xsl:value-of select="/ORDER/SUB_ORDER/SUB_ORDER_NO" />

---------------------------------------------
商品名：携帯電話レンタル
---------------------------------------------

メールアドレス： <xsl:value-of select="/ORDER/PRDUCT_SEND/E_MAIL" />

お客様名（漢字）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANJI" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANJI" />
お客様名（かな）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANA" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANA" />
住所： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/STATENAME" /><xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_ADDRESS" /><xsl:value-of select="/ORDER/PRDUCT_SEND/BUILDADDRESS" />
電話番号： <xsl:value-of select="/ORDER/PRDUCT_SEND/MOBILE_E_MAIL" />
携帯電話番号： <xsl:value-of select="/ORDER/PRDUCT_SEND/FAX" />


レンタル希望商品： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUSYOUHIN='A'">アメリカタイプ</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUSYOUHIN='B'">アメリカ・カナダタイプ</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUSYOUHIN='C'">南米ダイレクトタイプ</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUSYOUHIN='D'">グアム・サイパンタイプ</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUSYOUHIN='E'">韓国タイプ</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUSYOUHIN='F'">欧州・オセアニア・アジア・アフリカタイプ</xsl:if>
レンタル希望台数： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/KIBOUDAISUU" /> 台
商品受取希望日： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 7, 2)" /> 日 </xsl:if>
        				 <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKIN_DATE, 3, 2)" /> 日 </xsl:if>
商品返却予定日： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 7, 2)" /> 日 </xsl:if>
             		 <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/CHECKOUT_DATE, 3, 2)" /> 日 </xsl:if>
商品受取希望場所： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/UKETORIKIBOUBASYO='A'">成田空港第一</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/UKETORIKIBOUBASYO='B'">成田空港第二</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/UKETORIKIBOUBASYO='C'">関西空港</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/UKETORIKIBOUBASYO='D'">名古屋空港</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/UKETORIKIBOUBASYO='E'">宅配希望</xsl:if>
主たる利用国： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/RIYOUKOKU" />
安心のサービスの有無： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/SABISUUMU='0'">不要</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/SABISUUMU='1'">要</xsl:if>
支払い方法： <xsl:if test="/ORDER/PRDUCT_SEND/PAYMENT_WAY='0'">クレジットカード</xsl:if>
									 <xsl:if test="/ORDER/PRDUCT_SEND/PAYMENT_WAY='1'">現金前振込み</xsl:if>
ご連絡方法： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/MITUMORIRENRAKU='0'">どちらでも可</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/MITUMORIRENRAKU='1'">メールのみ</xsl:if>
									 <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/TRAVEL/MITUMORIRENRAKU='2'">電話のみ</xsl:if>

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
