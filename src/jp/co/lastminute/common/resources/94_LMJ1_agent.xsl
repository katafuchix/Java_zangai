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
商品名：クリスタルヨットクラブ 東京湾クルーズ
---------------------------------------------

ご予約者名： <xsl:value-of select="/ORDER/PRDUCT_SEND/LASTNAME" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/FIRST_NAME" />
メールアドレス： <xsl:value-of select="/ORDER/PRDUCT_SEND/E_MAIL" />


ご利用者名（漢字）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANJI" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANJI" />
ご利用者名（かな）： <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_LASTNAME_KANA" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/MAIN_FIRSTNAME_KANA" />
緊急連絡先電話番号： <xsl:value-of select="/ORDER/PRDUCT_SEND/FAX" />

プラン名： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='0'">ランチクルーズ</xsl:if>
<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='1'">ディナークルーズ（イタリアンコース）</xsl:if>
<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PLAN='2'">ナイトクルーズ</xsl:if>

利用予定日： <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE)=8">
               	<xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 5, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 7, 2)" /> 日 </xsl:if>
             <xsl:if test="string-length(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE)=4">
	              <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 1, 2)" /> 月 <xsl:value-of select="substring(/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/CHECKIN_DATE, 3, 2)" /> 日 </xsl:if>
利用人数（大人）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXADULT" /> 名
（小人）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXCHILD" /> 名
（幼児）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXINFANT" /> 名
（合計）： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PAXSUM" /> 名
年齢など： <xsl:value-of select="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/ETC" />
利用目的： <xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='1'">バースデー</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='2'">記念日</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='3'">懇親会</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='4'">接待・商談</xsl:if>
	<xsl:if test="/ORDER/SUB_ORDER/BUY_PROP/COLLEGE/PURPOSE='5'">その他</xsl:if>

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
