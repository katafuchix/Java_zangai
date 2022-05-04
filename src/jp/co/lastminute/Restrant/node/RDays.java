package jp.co.lastminute.Restrant.node;

import java.io.*;
import jp.co.yobrain.util.DataFormat;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RDays implements Serializable{
	private Rnode[] rnodes = null;
	private static final int size = Property.detail_days_length;
	private int top = 0;
	private RShop rshop = null;
	/**
	 * コンストラクタ
	 */
	public RDays(　RShop rshop　){
		this.rshop = rshop;
		this.rnodes = new Rnode[ size ];
	}
	/**
	 * 日付ごとの在庫数をセットする。
	 */
	public void setRnode( Rnode rnode ) {
		if( canInsert()){
			Rnode[] tempRdays = new Rnode[ size ];
			int positon = 0;
			boolean setflg = false;
			try{
				int maxdate = 0;
				for(int i=0; i<size; i++){
					if( rnodes[i] != null ){
						if( rnodes[i].getAllotdate() < rnode.getAllotdate() ){
							tempRdays[ positon ] = rnode;
							setflg = true;
							positon++;
							tempRdays[ positon ] = this.rnodes[i];
						}else if(rnodes[i].getAllotdate() == rnode.getAllotdate()){
							setflg = true;
							tempRdays[ positon ] = rnode;
						}else{
							tempRdays[ positon ] = this.rnodes[i];
						}
						maxdate = rnodes[i].getAllotdate();
						positon++;
					}
				}
				if( maxdate < rnode.getAllotdate() ){
					tempRdays[ positon ] = rnode;
					setflg = true;
				}
				if( !setflg ){	//追加するポジションがないということは、
								//一番小さいということ
					tempRdays = replase( rnode );
				}
				top++;
				this.rnodes = tempRdays;
			}catch(Exception ex){
				ex.printStackTrace();	
			}
		}
	}
	/**
	 * リプレース
	 */
	public Rnode[] replase( Rnode rnode ) throws Exception{
		Rnode[] emprnode = new Rnode[ size ];
		emprnode[0] = rnode;
		for(int i=0; i<this.rnodes.length; i++){
			if( this.rnodes[i] != null ){
				emprnode[i+1] = this.rnodes[i];
			}
		}
		return emprnode;
	}
	/**
	 * 追加可能か否かの判断
	 */
	private boolean canInsert(){
		if( top < size - 1 ){
			return true;
		}
		return false;
	}
	/**
	 * 日付からNodeを取得する。
	 */
	public Rnode getRnodeBydate( int date, int rnodesize ){
		try{
			for(int i=0; i<rnodes.length; i++){
				if( rnodes[i] != null ){
					if( rnodes[i].getAllotdate() == date ){
						return rnodes[i];
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		Rnode returnrnode = new Rnode( rnodesize );
		returnrnode.setAllotdate( date );
		return returnrnode;
	}
	/**
	 * 特定のポジションの値を取得
	 */
	public Rnode getRnode( int positon ){
		try{
			return rnodes[positon];
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * 
	 */
	public String getXmlString(int taegetdate ){
		String reStr = "";
		DataFormat df = null;
		try{
			int index = 0;
			reStr += "<RDAYS>\n";
			for(int i=0; i<size; i++){
				for(int j = index; j<rnodes.length; j++){
					if( rnodes[j] != null){
						reStr += "<RDAY>\n" + rnodes[i].getXmlString() + "</RDAY>\n";
						index++;
						break;
					}
				}
				taegetdate = Integer.parseInt(df.AddToDate( "" + taegetdate + "",i+1));
			}
			reStr += "</RDAYS>\n";
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * 特定のポジションの値を取得
	 */
	public void setRnode( int positon, Rnode rnode ){
		this.rnodes[positon] = rnode;
	}
	/**
	 * Returns the top.
	 * @return int
	 */
	public int getTop() {
		return top;
	}

	/**
	 * Sets the top.
	 * @param top The top to set
	 */
	public void setTop(int top) {
		this.top = top;
	}

	/**
	 * Returns the rnodes.
	 * @return Rnode[]
	 */
	public Rnode[] getRnodes() {
		return rnodes;
	}

	/**
	 * Sets the rnodes.
	 * @param rnodes The rnodes to set
	 */
	public void setRnodes(Rnode[] rnodes) {
		this.rnodes = rnodes;
	}

}
