<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
<xsl:template match="/">
<postMessage>
	<HOTEL_ID>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/SUPPLIERNUMBER" />
	</HOTEL_ID>
	<ROOMTYPE_CD>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/ROOMTYPE_CD" />
	</ROOMTYPE_CD>
	<MEAL_CD>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/MEAL_CD" />
	</MEAL_CD>
	<M_USE_DT>
		<xsl:value-of select="substring(ORDER/SUB_ORDER/BUY_PROP/HOTEL/CHECKINDATA, 5, 2)" />
	</M_USE_DT>
	<D_USE_DT>
		<xsl:value-of select="substring(ORDER/SUB_ORDER/BUY_PROP/HOTEL/CHECKINDATA, 7, 2)" />
	</D_USE_DT>
	<STAY>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/NUMBEROFNIGHTS" />
	</STAY>
	<ROOM_CNT>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/NUMBEROFROOMS" />
	</ROOM_CNT>
	<MALE_CNT>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/NUMBEROFADULT" />
	</MALE_CNT>
	<FEMALE_CNT>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/NUMBEROFFOMALE" />
    </FEMALE_CNT>
	<CHILD_A_CNT>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/NUMBEROFKIDS" />
    </CHILD_A_CNT>
	<CHILD_B_CNT>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/HOTEL/NUMBEROFINFANT" />
    </CHILD_B_CNT>
	<FIRST_NM>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAMEJPN/FIRSTNAMEJPN" />
	</FIRST_NM>
	<LAST_NM>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAMEJPN/LASTNAMEJPN" />
	</LAST_NM>
	<K_LAST_NM>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAME/LASTNAME" />
	</K_LAST_NM>
	<K_FIRST_NM>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAME/FIRSTNAME" />
	</K_FIRST_NM>
	<SEX>1</SEX>
	<PREFECTURE_CD>00</PREFECTURE_CD>
	<TEL>
		<xsl:value-of select="ORDER/PRODUCT_SEND/MOBILE_E_MAIL" />
	</TEL>
	<EMERGENCY_TEL>
		<xsl:value-of select="ORDER/PRODUCT_SEND/MOBILE_E_MAIL" />
	</EMERGENCY_TEL>
	<EMERGENCY_TEL_CD>
	<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/TELTYPE" />
	</EMERGENCY_TEL_CD>
	<EMAIL>
		<xsl:value-of select="ORDER/PRODUCT_SEND/E_MAIL" />
	</EMAIL>
	<LOGIN_ID>
		<xsl:value-of select="ORDER/PRODUCT_SEND/USER_ID" />
	</LOGIN_ID>
	<CHECKIN_TM>
	<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/CHECKINTIME" />
	</CHECKIN_TM>
	<TRANSPORTTYPE_FG>
	<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/TRANTYPE" />
	</TRANSPORTTYPE_FG>
</postMessage>
</xsl:template>
</xsl:stylesheet><!-- Stylus Studio meta-information - (c)1998-2001 eXcelon Corp.
<metaInformation>
<scenarios/><MapperInfo srcSchemaPath="" srcSchemaRoot="" srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
</metaInformation>
-->
