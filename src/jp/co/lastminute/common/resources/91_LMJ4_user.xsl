<?xml version="1.0" encoding="Shift_JIS"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html" encoding="Shift_JIS"/>
<xsl:template match="/">【ラストミニット・ドット・コム】ご注文・ご予約確認
ORDER NO      : <xsl:value-of select="/ORDER/SUB_ORDER/ORDER_NO" />
SUB ORDER NO  : <xsl:value-of select="/ORDER/SUB_ORDER/SUB_ORDER_NO" />
--------------------------------------------------

<xsl:value-of select="/ORDER/PRDUCT_SEND/LASTNAME" /> <xsl:value-of select="/ORDER/PRDUCT_SEND/FIRST_NAME" /> 様

この度は、レストラン「<xsl:value-of select="/ORDER/SUB_ORDER/TITLE" />」のご予約を賜りまして
誠に有難うございます。
弊社で空席情報を確認の後、メールにて予約の可否をご連絡
させて頂きます。
いましばらくお待ちくださいますようお願い申し上げます。


（株）ラストミニット・ドット・コム

<xsl:value-of select="/ORDER/MAILCOMMENT" />

==================================================
do something lastminute.com
http://www.lastminute.co.jp

</xsl:template>
</xsl:stylesheet>
