#!/bin/bash

# サーバ証明書導入バッチ

# JREホーム
# 例）/export/home/MerchantModules/jre
set JRE_HOME = /usr/java/j2sdk1.4.0

# keytoolコマンドのパス
# 例）$JRE_HOME/bin/keytool
set KEY_TOOL = $JRE_HOME/bin/keytool

# 導入先certsファイルパス
# 例）$JRE_PATH/lib/security/cacerts
set CERT_FILE = $JRE_HOME/jre/lib/security/cacerts

# 導入先certsファイルパス
# 例）p1
set ALIAS_NAME = p1

# 導入する証明書のファイル名
# 例）/export/home/MerchantModules/certs/CyberTrust.cer
set IMPORT_FILE = /usr/local/jakarta-tomcat-4.0.3/webapps/lmj/WEB-INF/classes/jp/co/lastminute/card/certs/GlobalRoot.cer


$KEY_TOOL -import -file $IMPORT_FILE -storepass changeit -alias $ALIAS_NAME -keystore $CERT_FILE

