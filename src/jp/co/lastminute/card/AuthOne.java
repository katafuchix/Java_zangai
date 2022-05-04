/**
 * @(#)AuthOne.java 2001/09/01
 * Product: Merchant Module for Java
 * Copyright 2000-2001 Payment-One,Inc. All Rights Reserved.
 */
package jp.co.lastminute.card;

import java.io.*;
import java.util.*;
import com.ec_one.cBank.payment.*;
import com.ec_one.cBank.payment.model.*;

/**
 * 決済サンプル for 売上一括<BR>
 *
 * @version 1.0 2001/11/01
 */
public class AuthOne implements Serializable {

	private String myShopCode;
	private String myShopPassword;

	private ResourceBundle resources;

	public AuthOne(){}
	
	/**
	 * オーソリ実行<BR>
	 */
	public void authory(String cardNumber, String cardExpiry) {
		PaymentRequestor requestor = null;
		try {
			// プロパティの取得、設定
			resources = ResourceBundle.getBundle("p1", Locale.getDefault());
			myShopCode = resources.getString("shopcode");
			System.out.println(myShopCode);
			myShopPassword	= resources.getString("shoppwd");
			System.out.println(myShopPassword);
			String propspath = resources.getString("propspath");
			System.out.println(propspath);
			Properties props = System.getProperties();
			props.put("appl.props.path", propspath);

			// オーソリリクエスターのインスタンスを取得
			requestor = PaymentRequestorFactory.getInstance();

			// PaymentRequestor#init()でオーソリに使用する端末識別番号を取得する
			String terminalId = requestor.init();
			System.out.println("端末識別番号："+terminalId);

			// PaymentRequestor#getNumber()でオーソリに使用する端末処理通番を取得する
			// 注意：端末処理通番は、端末（識別番号）毎に発行されます
			String seqNum = requestor.getNumber(terminalId);
			System.out.println("端末処理通番："+seqNum);

			// 決済用モデル作成＆データセット
			PaymentModel model = PaymentModelFactory.getInstance();
			
			model.setAuthType(PaymentModel.AUTH_TYPE_CARDCHECK);
			model.setSeqNum(seqNum);
//			model.setAmount(1000);
//			model.setTax(50);
			model.setCreditCardNumber(cardNumber);
			model.setCreditCardExpiry(cardExpiry);
			model.setProDivision("00");
			model.setShopCode(myShopCode);
			model.setShopPassword(myShopPassword);

			requestor.setPaymentModel(model);

			// オーソリ実行
			requestor.cardCheck();

			// オーソリ結果取得
			String errorCode = requestor.getResErrorCode();
			System.out.println("決済結果:" + errorCode);

			System.out.println("******************");
			System.out.println("オーソリ結果応答");
			System.out.println("******************");
			System.out.println("エラーコード        ["+requestor.getResErrorCode()+"]");
			System.out.println("リクエストコード    ["+requestor.getResRequestCode()+"]");
			System.out.println("モジュールバージョン["+requestor.getResModuleVersion()+"]");

			System.out.println("通信日時            ["+requestor.getResTranDate()+"]");
			System.out.println("端末識別番号        ["+requestor.getResTermCode()+"]");
			System.out.println("伝票番号            ["+requestor.getResReceiptNum()+"]");
			System.out.println("承認番号            ["+requestor.getResAdmitNum()+"]");
			System.out.println("カード番号          ["+requestor.getResCardNum()+"]");
			System.out.println("有効期限            ["+requestor.getResEffectiveTerm()+"]");
			System.out.println("金額                ["+requestor.getResAmount()+"]");
			System.out.println("税送料              ["+requestor.getResTax()+"]");
			System.out.println("オーソリ日          ["+requestor.getResAuthDate()+"]");
			System.out.println("支払区分            ["+requestor.getResPaymentDivision()+"]");
			System.out.println("業務区分            ["+requestor.getResTransDivision()+"]");
			System.out.println("カード会社コード    ["+requestor.getResRcvCompanyCode()+"]");
			System.out.println("カード会社名        ["+requestor.getResRcvCompanyName()+"]");
			System.out.println("取引番号            ["+requestor.getResTransactionNum()+"]");
			System.out.println("元取引伝票番号      ["+requestor.getResDelReceiptNum()+"]");
			System.out.println("元取引支払区分      ["+requestor.getResDelPaymentDivision()+"]");
			System.out.println("元取引通信日時      ["+requestor.getResDelTranDate()+"]");

		} catch (PaymentUserException userEx) {
			if (userEx instanceof PaymentAuthoryUserException) {
				PaymentAuthoryUserException ex = (PaymentAuthoryUserException)userEx;
				System.out.println("Code:"+ex.getCode()+" Message:"+ex.getMessage());
			} else if (userEx instanceof PaymentInvalidUserParameterException) {
				PaymentInvalidUserParameterException ex = (PaymentInvalidUserParameterException)userEx;
				System.out.println(" Message:"+ex.getMessage());
			} else {
				System.out.println("Catch PaymentUserException:");
				userEx.printStackTrace();
			}
		} catch (PaymentSystemException systemEx) {
			if (systemEx instanceof PaymentAuthorySystemException) {
				PaymentAuthorySystemException ex = (PaymentAuthorySystemException)systemEx;
				System.out.println("Code:"+ex.getCode()+" Message:"+ex.getMessage());
			} else if (systemEx instanceof PaymentInvalidSystemParameterException) {
				PaymentInvalidSystemParameterException ex = (PaymentInvalidSystemParameterException)systemEx;
				System.out.println(" Message:"+ex.getMessage());
			} else {
				System.out.println("Catch PaymentSystemException:");
				systemEx.printStackTrace();
			}
		} catch (Throwable t) {
			System.out.println("予期しない例外をキャッチしました.["+t.toString()+"]");
		} finally {
			if (null != requestor)
				requestor.end();
		}
	}

	public static void main(String[] args){
		try {
			AuthOne auth = new AuthOne();
			auth.authory("1234123412341234   ", "0501");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
