package jp.co.lastminute.maintenance.common;

import java.util.*;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.struts.action.Action;

import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;
import jp.co.lastminute.maintenance.util.ComboKey;
import jp.co.lastminute.maintenance.producthandle.CommandException;
import jp.co.lastminute.maintenance.producthandle.CommandConstants;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ProductTypeWrapper {
	private CommonDBOCache cache;
	private int refreshDuration = 1;
	private DataSource dataSource;
	private Map type_cd_nameMap = new HashMap();
	private List type_cdList = new ArrayList();
	private List combos = new ArrayList();
	private Vector typeMaps = null;
	/**
	 * エージェントコードリストの取得
	 */
	public List getType_cdList( String producttype ) throws CommandException {
		getTypeMaps( producttype );
		return this.type_cdList;
	}
	public List getCombos( String producttype ) throws CommandException {
		getTypeMaps( producttype );
		return this.combos;
	}
	/**
	 * 商品種別ごとのエージェントの取得
	 */
	public Vector getTypeMaps( String producttype ) throws CommandException {
		return getTypeMaps(getDataSource(), producttype);
	}
	/**
	 * 商品種別ごとのエージェントの取得
	 */
	public Vector getTypeMaps(DataSource dataSource, String producttype ) throws CommandException {
		return getTypeMaps(dataSource, this.refreshDuration, producttype );
	}
	/**
	 * 商品種別ごとのエージェントの取得
	 */
	public Vector getTypeMaps(DataSource dataSource, int refreshDuration, String producttype )
		throws CommandException {
		makeTypeMaps(dataSource, refreshDuration, producttype );
		return this.typeMaps;
	}
	/**
	 * エージェントネームの取得
	 */
	public String getType_name(String agt_cd) {
		return (String) this.type_cd_nameMap.get(agt_cd);
	}
	/**
	 * エージェントテーブルの生成
	 */
	private void makeTypeMaps(DataSource dataSource, int refreshDuration, String producttype )
		throws CommandException {
		this.typeMaps = null;
		this.typeMaps = getDBO( dataSource, producttype);
		setMany( this.typeMaps );
	}
	/**
	 * チャイルドテーブルの取得
	 */
	private void setMany( List lists ){
		try{
			Vector vc = (Vector)lists;
			dbDataAccesser acceser = new dbDataAccesser( vc );
			this.type_cdList = new ArrayList();
			this.type_cd_nameMap = new HashMap();
			for (int i = 0; i < acceser.getRowsize(); i++) {
				this.type_cdList.add( acceser.getData( i, 0 ) );
				this.type_cd_nameMap.put(acceser.getData( i, 0 ), acceser.getData( i, 1 ));
				this.combos.add( new ComboKey(acceser.getData( i, 0 ), acceser.getData( i, 1 ) ) );
			}
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	/**
	 * エージェントテーブルの生成
	 */
	private Vector getDBO(DataSource dataSource, String producttype ){
		try{
			MapEntry maps = null;
			if ( (maps = cache.getDBO( "" + producttype + "" )) != null){
				return (Vector)maps.DBO;
			}else{
				String sql = "";				
				if( producttype.equals( "101") ){
					sql = "SELECT TICKET_TYPE, TYPE_NAMES "
						+ "FROM TICKETACTIVITY_TYPE_MASTAER "
						+ "WHERE PRODUCT_TYPE=" + producttype + " ORDER BY TICKET_TYPE";
				}else{
					sql = "SELECT TICKET_TYPE, TYPE_NAMES "
						+ "FROM TICKETACTIVITY_TYPE_MASTAER "
						+ "WHERE PRODUCT_TYPE=" + producttype + " ORDER BY TICKET_TYPE";
				}
				System.err.println( sql );
				JdbcAdapter db = new JdbcAdapter();
				if( db.init( dataSource ) ){
					Vector rows = db.dbSelect( sql );
					if( rows.size() > 0 ){
						//データ格納
						cache.flush( "" + producttype + "" );
						maps = new MapEntry( System.currentTimeMillis(), rows );
						this.cache.setMapEntry( maps,  "" + producttype + "" );
						return rows;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * ハッシュマップのクリア
	 */
	public void flush( String producttype ) {
		cache.flush( producttype );
	}
	/**
	 * データソースの取得
	 */
	public DataSource getDataSource() {
		return this.dataSource;
	}
	/**
	 * データソースのセット
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * 更新間隔の取得
	 */
	public int getRefreshDuration() {
		return refreshDuration;
	}
	/**
	 * リフレッシュタイムの取得
	 */
	public void setRefreshDuration(int refreshDuration) {
		this.refreshDuration = refreshDuration;
	}
	/**
	 * 初期化
	 */
	public ProductTypeWrapper( CommonDBOCache cache, DataSource dataSource ){
		if( cache == null ){	
			setDataSource( dataSource );
			this.cache = new CommonDBOCache();
		}else{
			this.dataSource = dataSource;
			this.cache = cache;
		}
	}
	/**
	 * キャッシュの取得
	 */
	public CommonDBOCache getCache(){
		return this.cache;
	}
}
