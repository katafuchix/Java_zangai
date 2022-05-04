package jp.co.lastminute.maintenance.ticket;

import java.io.*;
import java.util.*;
import jp.co.yobrain.util.dbDataAccesser;
import jp.co.yobrain.util.jdbc.*;

import jp.co.lastminute.maintenance.*;
import jp.co.lastminute.maintenance.producthandle.*;
import jp.co.lastminute.maintenance.ticket.model.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TicketInfohandler extends Commander implements CommandIF{
	//extends Commander implements CommandIF{
	/**
	 * ストップフラグの更新
	 */
	public boolean setStopflg( int product_seq, int status){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = "UPDATE TICKETSACTIVTY SET STOP_FLG=" + status + " WHERE PRODUCT_SEQ=" + product_seq;
				System.err.println( sql );
				return db.dbUpdate( sql );
			}
			if( db != null ){	db = null;	}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
		
	}
	/**
	 * 詳細の更新
	 */
	public boolean updateTicketInfo( TicketInfo ticket ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = updateTicketInfoStr( ticket );
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
	public boolean addTicketInfo( TicketInfo ticket ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = addTicketInfoStr( ticket );
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
	public TicketInfo[] getList( HandlerCondition condition ){
		TicketInfo[] ticketInfos = null;
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getListStr( condition ) ;
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){
					ticketInfos = new TicketInfo[ rows.size() ];
					//データ格納 
					dbDataAccesser acceser = new dbDataAccesser( rows );
					for( int i=0; i<rows.size(); i++){
						TicketInfo ticket = new TicketInfo();
						ticket.product_seq 	= acceser.getDatabyInt( i, 0 );
						ticket.jan_cd 		= acceser.getData( i, 1 );
						ticket.group_cd 	= acceser.getDatabyInt( i, 2 );
						ticket.ticket_type 	= acceser.getDatabyInt( i, 3 );
						ticket.agt_cd 		= acceser.getData( i, 4 );
						ticket.product_type = acceser.getDatabyInt( i, 5 );
						ticket.product_name = acceser.getData( i, 7 );
						ticket.product_sub_title = acceser.getData( i, 8 );
						ticket.catchcopy 	= acceser.getData( i, 9 );
						ticket.price_catch 	= acceser.getData( i, 10 );
						ticket.opening 		= acceser.getData( i, 11 );
						ticket.actors 		= acceser.getData( i, 12 );
						ticket.produce 		= acceser.getData( i, 13 );
						ticket.stop_flg 	= acceser.getDatabyInt( i, 14 );
						ticket.linkedurl 	= acceser.getData( i, 15 );
						ticket.event_names 	= acceser.getData( i, 16 );
						ticket.type_names 	= acceser.getData( i, 6 );
						ticket.first_name 	= acceser.getData( i, 18 );
						ticket.lastname 	= acceser.getData( i, 19 );
						ticketInfos[ i ] = ticket;
						ticket = null;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return ticketInfos;
	}
	/**
	 * 詳細の出力
	 */
	public TicketInfo getDetail( HandlerCondition condition ){
		try{
			JdbcAdapter db = new JdbcAdapter();
			if( db.init( this.dataSource ) ){
				String sql = getDetailStr( condition );
				System.err.println( sql );
				Vector rows = db.dbSelect( sql );
				if( rows.size() > 0 ){	
				//データ格納 
					dbDataAccesser acceser = new dbDataAccesser( rows );
					TicketInfo ticket = new TicketInfo();
					ticket.product_seq	= acceser.getDatabyInt( 0, 0 );
					ticket.jan_cd 		= acceser.getData( 0, 1 );
					ticket.group_cd 	= acceser.getDatabyInt( 0, 2 );
					ticket.ticket_type 	= acceser.getDatabyInt( 0, 3 );
					ticket.agt_cd 		= acceser.getData( 0, 4 );
					ticket.product_type = acceser.getDatabyInt( 0, 5 );
				
					ticket.product_name = acceser.getData( 0, 7 );
					ticket.product_sub_title = acceser.getData( 0, 8 );
					ticket.catchcopy 	= acceser.getData( 0, 9 );
					ticket.price_catch 	= acceser.getData( 0, 10 );
					ticket.opening 		= acceser.getData( 0, 11 );
					ticket.actors 		= acceser.getData( 0, 12 );
					ticket.produce 		= acceser.getData( 0, 13 );
					ticket.play_place 	= acceser.getData( 0, 14 );
					ticket.pointofaccess = acceser.getData( 0, 15 );
					ticket.accesspoint_url = acceser.getData( 0, 16 );
					ticket.description 	= acceser.getData( 0, 17 );
					ticket.image_01_url = acceser.getData( 0, 18 );
					ticket.image_01_url_1 = acceser.getData( 0, 19 );
					ticket.image_01_url_2 = acceser.getData( 0, 20 );
					ticket.image_01_url_3 = acceser.getData( 0, 21 );
					ticket.stop_flg 	= acceser.getDatabyInt( 0, 22 );
					ticket.linkedurl 	= acceser.getData( 0, 23 );
					ticket.event_names 	= acceser.getData( 0, 24 );
					ticket.type_names 	= acceser.getData( 0, 6 );
					ticket.first_name 	= acceser.getData( 0, 26 );
					ticket.lastname 	= acceser.getData( 0, 27 );
					return ticket;
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	public String updateTicketInfoStr( TicketInfo ticket ) throws Exception{
		return "UPDATE TICKETSACTIVTY "
				+"SET JAN_CD='" + ticket.jan_cd + "',"
				+"GROUP_CD=" + ticket.group_cd + ","
				+"AGT_CD='" + ticket.agt_cd + "',"
				+"PRODUCT_TYPE=" + ticket.product_type + ","
				+"TYPE_NAMES='" + ticket.type_names + "',"
				+"PRODUCT_NAME='" + ticket.product_name + "',"
				+"PRODUCT_SUB_TITLE='" + ticket.product_sub_title + "',"
				+"CATCHCOPY='" + ticket.catchcopy + "',"
				+"OPENING='" + ticket.opening + "',"
				+"ACTORS='" + ticket.actors + "',"
				+"PRODUCE='" + ticket.produce + "',"
				+"PLAY_PLACE='" + ticket.play_place + "',"
				+"POINTOFACCESS='" + ticket.pointofaccess + "',"
				+"ACCESSPOINT_URL='" + ticket.accesspoint_url + "',"
				+"DESCRIPTION='" + ticket.description + "',"
				+"IMAGE_01_URL='" + ticket.image_01_url + "',"
				+"IMAGE_01_URL_1='" + ticket.image_01_url_1 + "',"
				+"IMAGE_01_URL_2='" + ticket.image_01_url_2 + "',"
				+"IMAGE_01_URL_3='" + ticket.image_01_url_3 + "',"
				+"STOP_FLG=" + ticket.stop_flg + ","
				+"LINKEDURL='" + ticket.linkedurl + "',"
				+"EVENT_NAMES='" + ticket.event_names + "',"
				+"TICKET_TYPE=" + ticket.ticket_type + ","
				+"PRICE_CATCH='" + ticket.price_catch + "',"
				+"UP_DATE=SYSDATE " 
				+"WHERE PRODUCT_SEQ=" + ticket.product_seq ;	
	}
	/**
	 * チケットの新規追加ストリング
	 */
	public String addTicketInfoStr( TicketInfo ticket ) throws Exception {
		return "INSERT INTO TICKETSACTIVTY("
				+"PRODUCT_SEQ,"
				+"JAN_CD,"
				+"GROUP_CD,"
				+"AGT_CD,"
				+"PRODUCT_TYPE,"
				+"TYPE_NAMES,"
				+"PRODUCT_NAME,"
				+"PRODUCT_SUB_TITLE,"
				+"CATCHCOPY,"
				+"OPENING,"
				+"ACTORS,"
				+"PRODUCE,"
				+"PLAY_PLACE,"
				+"POINTOFACCESS,"
				+"ACCESSPOINT_URL,"
				+"DESCRIPTION,"
				+"IMAGE_01_URL,"
				+"IMAGE_01_URL_1,"
				+"IMAGE_01_URL_2,"
				+"IMAGE_01_URL_3,"
				+"STOP_FLG,"
				+"MAKE_DATE,"
				+"LINKEDURL,"
				+"EVENT_NAMES,"
				+"TICKET_TYPE,"
				+"PRICE_CATCH)VALUES"
				+"("
				+"TICKETSACTIVTY_SEQ.NEXTVAL,"
				+"'" + ticket.jan_cd + "',"
				+ ticket.group_cd + ","
				+"'" + ticket.agt_cd + "',"
				+ ticket.product_type + ","
				+"'" + ticket.type_names + "',"
				+"'" + ticket.product_name + "',"
				+"'" + ticket.product_sub_title + "',"
				+"'" + ticket.catchcopy + "',"
				+"'" + ticket.opening + "',"
				+"'" + ticket.actors + "',"
				+"'" + ticket.produce + "',"
				+"'" + ticket.play_place + "',"
				+"'" + ticket.pointofaccess + "',"
				+"'" + ticket.accesspoint_url + "',"
				+"'" + ticket.description + "',"
				+"'" + ticket.image_01_url + "',"
				+"'" + ticket.image_01_url_1 + "',"
				+"'" + ticket.image_01_url_2 + "',"
				+"'" + ticket.image_01_url_3 + "',"
				+"1,"
				+"SYSDATE,"
				+"'" + ticket.linkedurl + "',"
				+"'" + ticket.event_names + "',"
				+ ticket.ticket_type + ","
				+"'" + ticket.price_catch + "'"
				+")";
	}
	/**
	 * 詳細情報の取得
	 */
	public String getListStr( HandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.getJan_cd().length() > 0 ){
			where += "A.JAN_CD='" + condition.getJan_cd() + "' AND ";
		}
		if( condition.getAgt_cd().length() > 0 ){
			where += "A.AGT_CD='" + condition.getAgt_cd() + "' AND ";
		}
		if( condition.getProduct_name().length() > 0 ){
			where += "A.PRODUCT_NAME='%" + condition.getProduct_name() + "%' AND ";
		}
		String[] limitStr = {"A.STOP_FLG=0 AND ", "A.STOP_FLG=1 AND ", "STOP_FLG<9 AND "};
		String[] sortStr = {"ORDER BY A.JAN_CD", 
							"ORDER BY A.PRODUCT_NAME", 
							"ORDER BY A.JAN_CD", 
							"ORDER BY A.JAN_CD"}; 
		
		return   "SELECT "
    			+"A.PRODUCT_SEQ,"		//00
        		+"A.JAN_CD,"			//01
        		+"A.GROUP_CD,"			//02
        		+"A.TICKET_TYPE,"		//03
        		+"A.AGT_CD,"			//04
        		+"A.PRODUCT_TYPE,"		//05
        		+"A.TYPE_NAMES,"		//06
        		+"A.PRODUCT_NAME,"		//07
        		+"A.PRODUCT_SUB_TITLE,"	//08
        		+"A.CATCHCOPY,"			//09
        		+"A.PRICE_CATCH,"		//10
        		+"A.OPENING,"			//11
        		+"A.ACTORS,"			//12
        		+"A.PRODUCE,"			//13
        		+"A.STOP_FLG,"			//14
        		+"A.LINKEDURL,"			//15
        		+"A.EVENT_NAMES,"		//16
        		+"B.TYPE_NAMES,"		//17
        		+"C.FIRST_NAME,"		//18
        		+"C.LASTNAME "			//19
     			+" FROM TICKETSACTIVTY A,"
     			+"TICKETACTIVITY_TYPE_MASTAER B,"
     			+"AGENT_TBL C "
     			+"WHERE A.TICKET_TYPE=B.TICKET_TYPE AND "
     			+"A.PRODUCT_TYPE=B.PRODUCT_TYPE AND "
     			+ where
     			+ limitStr[ condition.getLimittype() ]
     			+"A.AGT_CD=C.AGT_CD "
    			+ sortStr[ condition.getSorttype() ] ;
	}
	/**
	 * 詳細情報の取得
	 */
	public String getDetailStr( HandlerCondition condition ) throws Exception{
		String where = "";
		if( condition.getProduct_seq().length() > 0 ){
			where += "A.PRODUCT_SEQ=" + condition.getProduct_seq() + " AND ";
		}
		if( condition.getJan_cd().length() > 0 ){
			where += "A.JAN_CD='" + condition.getJan_cd() + "' AND ";
		}
		if( condition.getAgt_cd().length() > 0 ){
			where += "A.AGT_CD='" + condition.getAgt_cd() + "' AND ";
		}
		if( condition.getProduct_name().length() > 0 ){
			where += "A.PRODUCT_NAME='%" + condition.getProduct_name() + "%' AND ";
		}
		//String[] limitStr = {"A.STOP_FLG=0 AND ", "A.STOP_FLG=1 AND ", ""};
		String[] sortStr = {"ORDER BY A.JAN_CD", 
							"ORDER BY A.PRODUCT_NAME", 
							"ORDER BY A.JAN_CD", 
							"ORDER BY A.JAN_CD"}; 
		
		return   "SELECT "
    			+"A.PRODUCT_SEQ,"		//00
        		+"A.JAN_CD,"			//01
        		+"A.GROUP_CD,"			//02
        		+"A.TICKET_TYPE,"		//03
        		+"A.AGT_CD,"			//04
        		+"A.PRODUCT_TYPE,"		//05
        		+"A.TYPE_NAMES,"		//06
        		+"A.PRODUCT_NAME,"		//07
        		+"A.PRODUCT_SUB_TITLE,"	//08
        		+"A.CATCHCOPY,"			//09
        		+"A.PRICE_CATCH,"		//10
        		+"A.OPENING,"			//11
        		+"A.ACTORS,"			//12
        		+"A.PRODUCE,"			//13
        		+"A.PLAY_PLACE,"		//14
        		+"A.POINTOFACCESS,"		//15
        		+"A.ACCESSPOINT_URL,"	//16
        		+"A.DESCRIPTION,"		//17
        		+"A.IMAGE_01_URL,"		//18
        		+"A.IMAGE_01_URL_1,"	//19
        		+"A.IMAGE_01_URL_2,"	//20
        		+"A.IMAGE_01_URL_3,"	//21
        		+"A.STOP_FLG,"			//22
        		+"A.LINKEDURL,"			//23
        		+"A.EVENT_NAMES,"		//24
        		+"B.TYPE_NAMES,"		//25
        		+"C.FIRST_NAME,"		//26
        		+"C.LASTNAME "			//27
     			+" FROM TICKETSACTIVTY A,"
     			+"TICKETACTIVITY_TYPE_MASTAER B,"
     			+"AGENT_TBL C "
     			+"WHERE A.TICKET_TYPE=B.TICKET_TYPE AND "
     			+"A.PRODUCT_TYPE=B.PRODUCT_TYPE AND "
     			+ where
     			+"A.AGT_CD=C.AGT_CD "
    			+ sortStr[ condition.getSorttype() ] ;
	}
}
