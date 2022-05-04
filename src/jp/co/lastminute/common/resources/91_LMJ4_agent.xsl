<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html" encoding="Shift_JIS"/>
<xsl:template match="/">【ラストミニット・ドット・コム】手配依頼メール
lastminute 申し込みメール

お世話になっております。
以下の内容にて手配依頼を受けましたので、
ご対応をお願いいたします。

株式会社ラストミニット・ドット・コム
03-3526-6221
supply@lastminute.co.jp

------------------- ご予約内容 --------------------

ORDER NO      : <xsl:value-of select="/ORDER/SUB_ORDER/ORDER_NO" />
SUB ORDER NO  : <xsl:value-of select="/ORDER/SUB_ORDER/SUB_ORDER_NO" />

---------------------------------------------
レストラン名： <xsl:value-of select="/ORDER/SUB_ORDER/TITLE" />
Restaurant Serial： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/TARIFF_NO" />
Restaurant ID： <xsl:value-of select="/ORDER/SUB_ORDER/AGT_PROD_CD" />
---------------------------------------------

ご予約者名： <xsl:value-of select="/ORDER/PRDUCT_SEND/LASTNAME" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/FIRST_NAME" />
メールアドレス： <xsl:value-of select="/ORDER/PRDUCT_SEND/E_MAIL" />


ご利用者名（漢字）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANJI" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANJI" />
ご利用者名（かな）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANA" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANA" />
電話番号1： <xsl:value-of select="/ORDER/PRDUCT_SEND/FAX" />
電話番号2： <xsl:value-of select="/ORDER/PRDUCT_SEND/MOBILE_E_MAIL" />
携帯メールアドレス：<xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/MOBILE_EMAIL" />

予約日： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATE)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATE, 7, 2)" /> 日 </xsl:if>
             <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATE)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATE, 3, 2)" /> 日 </xsl:if>
予約時間： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/CHECKIN_DATETIME" />
人数： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/PAX" />
ご要望など： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/RESTRANT/ETC" />

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
