<?xml version="1.0" encoding="EUC-JP"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:template match="/">
<postMessage>
	<product_type_cd>
		<xsl:value-of select="ORDER/SUB_ORDER/PRODUCT_TYPE_CD"/>
	</product_type_cd>
	<agt_cd>
		<xsl:value-of select="ORDER/SUB_ORDER/AGT_CD"/>
	</agt_cd>
	<order_no>
		<xsl:value-of select="ORDER/SUB_ORDER/SUB_ORDER_NO"/>
	</order_no>
	<flex_id>
		<xsl:value-of select="ORDER/SUB_ORDER/PROD_CD"/>
	</flex_id>
	<eline_course_name>
		<xsl:value-of select="ORDER/SUB_ORDER/TITLE"/>
	</eline_course_name>
	<branch_id>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/BRANCH_ID"/>
	</branch_id>
	<from>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/FROM"/>
	</from>

	<email>
		<xsl:value-of select="ORDER/PRODUCT_SEND/E_MAIL"/>
	</email>
	<zip>
		<xsl:value-of select="ORDER/PRODUCT_SEND/MAIN_POSTALCODE"/>
	</zip>
	<state>
		<xsl:value-of select="ORDER/PRODUCT_SEND/MAIN_STATE"/>
	</state>
	<address1>
		<xsl:value-of select="ORDER/PRODUCT_SEND/MAIN_ADDRESS"/>
	</address1>
	<address2>
		<xsl:value-of select="ORDER/PRODUCT_SEND/BUILDADDRESS"/>
	</address2>
	<tel1>
		<xsl:value-of select="ORDER/PRODUCT_SEND/MOBILE_E_MAIL"/>
	</tel1>
	<tel_type>
		<xsl:value-of select="ORDER/PRODUCT_SEND/ADDRESSFLG"/>
	</tel_type>
	<ticketing>
		<xsl:value-of select="ORDER/PRODUCT_SEND/TICKETING"/>
	</ticketing>
	<payment_way>
		<xsl:value-of select="ORDER/PRODUCT_SEND/PAYMENT_WAY"/>
	</payment_way>
	<addressflg>1</addressflg>

	<pax_adl_str>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/ADT_PAX"/>
	</pax_adl_str>
	<pax_chd_str>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/CHD_PAX"/>
	</pax_chd_str>
	<pax_inf_str>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/INF_PAX"/>
	</pax_inf_str>
	<air_remarks>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/ETC"/>
	</air_remarks>
	<password>
		<xsl:value-of select="ORDER/PRODUCT_SEND/PASSWD"/>
	</password>

	<k_lname>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAMEJPN/LASTNAMEJPN"/>
	</k_lname>
	<k_fname>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAMEJPN/FIRSTNAMEJPN"/>
	</k_fname>
	<r_lname>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAME/LASTNAME"/>
	</r_lname>
	<r_fname>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/NAME/FIRSTNAME"/>
	</r_fname>
	<sex>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/TITLE"/>
	</sex>
	<birthday>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/BIRTHDAY"/>
	</birthday>
	<title>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/LEADER/TITLE"/>
	</title>

	<xsl:for-each select="ORDER/SUB_ORDER/BUY_PROP/MEMBER">
		<k_lname>
			<xsl:value-of select="NAMEJPN/LASTNAMEJPN"/>
		</k_lname>
		<k_fname>
			<xsl:value-of select="NAMEJPN/FIRSTNAMEJPN"/>
		</k_fname>
		<r_lname>
			<xsl:value-of select="NAME/LASTNAME"/>
		</r_lname>
		<r_fname>
			<xsl:value-of select="NAME/FIRSTNAME"/>
		</r_fname>
		<sex>
			<xsl:value-of select="TITLE"/>
		</sex>
		<birthday>
			<xsl:value-of select="BIRTHDAY"/>
		</birthday>
		<title>
			<xsl:value-of select="TITLE"/>
	</title>
	</xsl:for-each>

	<numberOfNights>
		<xsl:value-of select="ORDER/SUB_ORDER/numberOfNights" />
	</numberOfNights>
	<arrive_time_year>
		<xsl:value-of select="substring(ORDER/SUB_ORDER/BUY_PROP/tours/DEPARTURE_DATE, 0, 4)"/>
	</arrive_time_year>
	<arrive_time_month>
		<xsl:value-of select="substring(ORDER/SUB_ORDER/BUY_PROP/tours/DEPARTURE_DATE, 5, 2)"/>
	</arrive_time_month>
	<arrive_time_day>
		<xsl:value-of select="substring(ORDER/SUB_ORDER/BUY_PROP/tours/DEPARTURE_DATE, 7, 2)"/>
	</arrive_time_day>
	<dept>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/DEPARTURE_CITY" />
	</dept>
	<arrive>
		<xsl:value-of select="ORDER/SUB_ORDER/BUY_PROP/tours/DESTINATION_CITY"/>
	</arrive>
	<dept_time>
		<xsl:value-of select="DEPT_TIME"/>
	</dept_time>
</postMessage>
</xsl:template>
</xsl:stylesheet>
