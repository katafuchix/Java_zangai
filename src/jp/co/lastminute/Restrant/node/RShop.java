package jp.co.lastminute.Restrant.node;

import java.io.*;
import java.util.*;
import jp.co.lastminute.Restrant.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RShop implements Serializable{
	private int skucount = 0;	//ショップに割り当てられているＳＫＵ数
	private int[] skuids;
	private String[] skuidsname;
	
	public RShop(int skucount){
		this.skucount = skucount;
		this.skuids = new int[ skucount ];
		this.skuidsname = new String[ skucount ];
	}
	/**
	 * skuidの追加ルーチン
	 */
	public boolean setSkuid( int skuid, String rts ){
		boolean isadd = true;
		int posiion = 0;
		for( int i=0; i<skuids.length; i++){
			if( skuids[i] == skuid ){
				return isadd;
			}
			if( skuids[i] == 0 ){
				i = posiion;
				isadd = false;
				break;
			}
		}
		this.skuids[ posiion ] = skuid;
		this.skuidsname[ posiion ] = rts;
		return isadd;
	}
	/**
	 * skuid のポジションの取得
	 */
	public int getPosition( int skuid ){
		for( int i=0; i<skuids.length; i++){
			if( skuids[i] == skuid ){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 
	 */
	public String getXmlString(){
		String reStr = "<SHOP>\n";	
		for( int i=0; i<skuids.length; i++){
			reStr += "<SKUS>\n"
				  + "<SKU>" + skuids[i] + "</SKU>\n"
				  + "<SKUNAME>"+ skuidsname[i] + "</SKUNAME>\n"
				  + "</SKUS>\n";
		}
		reStr += "</SHOP>\n";	
		return reStr;
	}
}
