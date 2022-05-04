package jp.co.lastminute.maintenance.campaign;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.ContextProperty;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.campaign.model.*;

import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Lmj_campaignhandler extends Commander implements CommandIF{
	/**
	 * コンテキストのリロード
	 */
	public boolean reLoloadComtext(){
		String contextname = ContextProperty.LMJCAMPAIGNS_  ;
		
		try{
			FileOutputStream fout = new FileOutputStream(ContextProperty.LMJCAMPAIGNSLocate_ );
			ObjectOutput oout = new ObjectOutputStream(fout);		
			oout.writeObject(  getLmjCampaigns() );
			fout.flush();
			
			fout.close();
			return true;
		} catch (IOException e) {
      		e.printStackTrace();
    	}
    	return false;
	}
	public Hashtable getLmjCampaigns( ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if (db.init(this.dataSource)) {
				String sql = "SELECT LMJCAMPAIGN,"
						   + "CAMPAIGN_NAME FROM "
						   + "LMJ_CAMPAIGN "
						   + "ORDER BY LMJCAMPAIGN";
				System.err.println( sql );
				Vector rows = db.dbSelect(sql);
				if (rows.size() > 0) {
					Hashtable lmjcampaign = new Hashtable();
					//データ格納 
					dbDataAccesser acceser = new dbDataAccesser(rows);
					for (int i = 0; i < rows.size(); i++) {
						lmjcampaign.put( "" + acceser.getData(i, 0) + "", acceser.getData(i, 1));
					}
					return lmjcampaign;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * 詳細の更新
	 */
	public boolean updateLmj_campaign( Lmj_campaign lmj_campaign ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateLmj_campaignStr( lmj_campaign );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	/**
	 * 詳細のインサート
	 */
	public boolean addLmj_campaign( Lmj_campaign lmj_campaign ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = addLmj_campaignStr( lmj_campaign );
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;	
	}
	/**
	 * リストの取得
	 */
	public Lmj_campaign[] getList( HandlerCondition condition ){
		Lmj_campaign[] lmj_campaigns = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getListStr( condition ) ;
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					lmj_campaigns = new Lmj_campaign[ rows.size() ];
					//データ格納 
					dbDataAccesser acceser = new dbDataAccesser( rows );
					for( int i=0; i<rows.size(); i++){
						Lmj_campaign lmj_campaign = new Lmj_campaign();
						lmj_campaign.lmjcampaign  	= Integer.parseInt( acceser.getData( i, 0 ) );
						lmj_campaign.campaign_name	= acceser.getData( i, 1 );
						
						lmj_campaigns[ i ] = lmj_campaign;
						lmj_campaign = null;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return lmj_campaigns;
	}
		public String updateLmj_campaignStr( Lmj_campaign lmj_campaign ) throws Exception{
		return "UPDATE LMJ_CAMPAIGN "
				+"SET CAMPAIGN_NAME='" + lmj_campaign.campaign_name + "' "
				+"WHERE LMJCAMPAIGN=" + lmj_campaign.lmjcampaign ;	
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addLmj_campaignStr( Lmj_campaign lmj_campaign ) throws Exception {
		return "INSERT INTO LMJ_CAMPAIGN("
				+"LMJCAMPAIGN,"
				+"CAMPAIGN_NAME )VALUES"
				+"( (SELECT max( LMJCAMPAIGN) + 1 FROM LMJ_CAMPAIGN),"
				+"'" + lmj_campaign.campaign_name + "')";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr( HandlerCondition condition ) throws Exception{		
		return   "SELECT "
	    		+"LMJCAMPAIGN,"
				+"CAMPAIGN_NAME "
     			+" FROM LMJ_CAMPAIGN WHERE LMJCAMPAIGN> 0 ORDER BY LMJCAMPAIGN";
	}
}
