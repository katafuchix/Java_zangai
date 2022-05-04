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
商品名：ヘリコプター　ナイトクルージング
---------------------------------------------

ご予約者名： <xsl:value-of select="/ORDER/PRDUCT_SEND/LASTNAME" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/FIRST_NAME" />
メールアドレス： <xsl:value-of select="/ORDER/PRDUCT_SEND/E_MAIL" />


ご利用者名（漢字）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANJI" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANJI" />
ご利用者名（かな）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANA" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANA" />
緊急連絡先電話番号： <xsl:value-of select="/ORDER/PRDUCT_SEND/FAX" />

利用日（第一希望）： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 7, 2)" /> 日 </xsl:if>
             <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 3, 2)" /> 日 </xsl:if>
利用日（第二希望）： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE_2)=8">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE_2, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE_2, 7, 2)" /> 日 </xsl:if>
             <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE_2)=4">
               <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE_2, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE_2, 3, 2)" /> 日 </xsl:if>
フライト時間帯： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='17'">17時台</xsl:if>
<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='18'">18時台</xsl:if>
<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='19'">19時台</xsl:if>
<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='20'">20時台</xsl:if>
利用人数（大人）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXADULT" /> 名
（小人）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXCHILD" /> 名
（合計）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXSUM" /> 名
コース名： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='1'">ダイアモンドコース</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='3'">ダイアモンドコース（2名貸切）</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='0'">パールコース</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='2'">パールコース（2名貸切）</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='4'">サンセットコース</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='5'">ベイライトコース</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='6'">ベイライトコース（2名貸切）</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='7'">ベイアクセス(片道)</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='8'">ベイアクセス(往復)</xsl:if>
お支払方法： <xsl:if test="/ORDER/PRDUCT_SEND/PAYMENT_WAY='1'">銀行振り込み</xsl:if>
	<xsl:if test="/ORDER/PRDUCT_SEND/PAYMENT_WAY='2'">クレジットカード支払い</xsl:if>

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
