package jp.co.lastminute.cart.members;

import java.io.*;
import java.lang.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import javax.servlet.*;
import javax.servlet.http.*;
import jp.co.lastminute.cart.*;
import jp.co.lastminute.cart.check.member.*;
import jp.co.lastminute.cart.user.*;
import jp.co.lastminute.cart.user.model.Profile;
import jp.co.yobrain.util.text.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Memberhandler implements Serializable {
	private boolean reBoolean;
	private Node members;
	private int size = Property.MEMBERLENGTH_;
	private String classname;
	private Member root;
	private int parentsize = 0;
	private int childsize = 0;
	private static int temp1 = Checkmember.parent_target; 
	private static int temp2 = Checkmember.infant_target;
	/**
	 * コンストラクタ
	 */
	public Memberhandler( String classname ){
		reBoolean = true;
		members = new Node();
		members.init( size );
		this.classname = classname;
		root = new Member();
	}
	public Memberhandler( Node node, String classname ){
		reBoolean = true;
		members = node;
		this.classname = classname;
		root = node.getMem();	//root = new Member();
	}
	public Memberhandler( String xmlstring, User user, String classname ){
		reBoolean = true;
		this.members = new Node();
		this.classname = classname;
		root = new Member();
		this.setRootUser( user );	//
		Vector tempMembers = setNodeFromString( xmlstring );
		System.err.println(" parentsize, childsize : " +  parentsize + ", " + childsize);
		this.init( parentsize, childsize );
		setVectorMembers( tempMembers );
	}
	/**
	 * 
	 */
	private void setVectorMembers( Vector tempMembers ){
		try{
			for(int i=0; i<tempMembers.size(); i++){
				Member tempmem = (Member)tempMembers.get(i);
				setMember( tempmem );
				if( tempmem.getDDate() == 1){
					root = tempmem;
				}
			}
		}catch(Exception ex){ ex.printStackTrace();	}
	}
	/**
	 * FULLチェック
	 * 
	 */
	public boolean fullCheck(){
		boolean rebool = true;
		try{
			Node[] childs = this.members.getChildArray();
			for( int i=0; i<childs.length; i++){
				if( childs[i] != null ){
					Member child = childs[i].getMem();
					if( !isCheckOk( child )){	return false;	}
					Node[] grandchilds = childs[i].getChildArray();
					for(int j=0; j<grandchilds.length; j++){
						if( grandchilds[j] != null){
							Member grandchild = grandchilds[j].getMem();
							if( !isCheckOk( grandchild )){
								return false;
							}
						}
					}
				}
			}				
		}catch(Exception ex){	ex.printStackTrace();	rebool = false;	}
		return rebool;
	}
	/**
	 * メンバーのチェック
	 */
	synchronized private boolean isCheckOk( Member member) throws Exception {
		Checkmember memb = (Checkmember)Class.forName( this.classname ).newInstance();
		memb.setMember( member );
		return memb.Check( );
	}
	/**
	 * ストリングからXMLパージングを行う
	 */
	public Vector setNodeFromString( String str ){
		Vector tempNode = new Vector();
		try{
			Document doc = ParsingTool.XMLToDoc( new StringReader( str ) );
			Element els = doc.getRootElement();
			List children = els.getChildren();
			Iterator iter = children.iterator();
			/////ループする///////
			while (iter.hasNext()) {				//LEVEL 0 : MEMBERS
				Element el = (Element) iter.next();
				String name = el.getName();
				String value;
				if (name.equals("MEMBER")) {		//LEVEL 1 : MEMBER
					List childrens = el.getChildren();
					Iterator iters = childrens.iterator();
					while (iters.hasNext()) {
						Element el2 = (Element) iters.next();
						String name2 = el2.getName();
						String value2 = el2.getTextTrim();
						Member mem = null; 
						if(name2.equals("PARENT")) {
							mem = getMemberObj( el2 );
						}
						if(name2.equals("CHILD")){
							mem = getMemberObj( el2 );
						}
						if( mem.getDDate()  >= temp2){
							childsize++;
						}else if( mem.getDDate()  >= temp1){
							parentsize++;
						}
						tempNode.add( mem );
						mem = null; 
					}
				}			
			}
		}catch(Exception ex){}
		return tempNode;
	}
	/**
	 * XMLオブジェクトから、メンバーを取得する
	 */
	private Member getMemberObj( Element element ){
		Member mem = new Member();
		List children = element.getChildren();
		Iterator iter = children.iterator();
		while (iter.hasNext()) {
			Element el = (Element) iter.next();
			String name = el.getName();
			String value = el.getTextTrim();
			if (name.equals("ALPHABET")) {
				String[] reStr = setSigunaturename( el );
				mem.setFirstname_alp( reStr[0] );
				mem.setLastnema_alp( reStr[1] );
				reStr = null;
			}
			if( name.equals("KANJI")){
				String[] reStr = setSigunaturename( el );
				mem.setFirstname_kanji( reStr[0] );
				mem.setLastname_kanji( reStr[1] );
				reStr = null;
			}
			if( name.equals("HKANA")){
				String[] reStr = setSigunaturename( el );
				mem.setFirstname_hkana( reStr[0] );
				mem.setLastname_kkana( reStr[1] );
				reStr = null;
			}
			if( name.equals("KKANA")){
				String[] reStr = setSigunaturename( el );
				mem.setFirstname_kkana( reStr[0] );
				mem.setLastname_kkana( reStr[1] );
				reStr = null;
			}
			if( name.equals("SEX")){
				mem.setSex( value );
			}
			if( name.equals("BIRTHDAY")){
				String[] reStr = setSigunaturename( el );
				mem.setYears( reStr[0] );
				mem.setMonths( reStr[1] );
				mem.setDays( reStr[2] );
				reStr = null;
			}
			if( name.equals("PASSPORT")){
				mem.setPassport( value );
			}
			if( name.equals("DDATE")){
				try{
					mem.setDDate( Integer.parseInt( value ) );
				}catch(Exception ex){	ex.printStackTrace();	}
			}
			if( name.equals("TELNO")){
				mem.setTelno( value );
			}
			if( name.equals("TELNO")){
				mem.setTelno( value );
			}
			if( name.equals("TARGETDATE")){
				mem.setTargetdate( value );
			}
		}
		return mem;
	}
	/**
	 * 
	 */
	synchronized private String[] setSigunaturename( Element element ){
		String[] reStr = {"", "", ""};
		try{
			List children = element.getChildren();
			Iterator iter = children.iterator();
			while(iter.hasNext()){
				Element el = (Element) iter.next();
				String name = el.getName();
				String value = el.getTextTrim();
				if( value.length() > 0 ){
					if( name.equals("FIRSTNAME")){
						reStr[0] = value;
					}
					if( name.equals("LASTSTNAME")){
						reStr[1] = value;
					}
					if( name.equals("YEAR") ){
						reStr[0] = value;
					}
					if( name.equals("MONTH") ){
						reStr[1] = value;
					}
					if( name.equals("DAY")){
						reStr[2] = value;
					}
				}
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * ルートのセット
	 */
	public void setRootUser( User user ){
		try{
			Profile profile = user.getProfile();
			root.firstname_alp = profile.getFIRST_NAME(); ;
			root.firstname_hkana = profile.getNA_KANA();
			root.firstname_kanji = profile.getNA_KANJI();
			root.firstname_kkana = KanaConversion.getConversionString( profile.getNA_KANA() );
			root.lastneme_alp = profile.getLASTNAME();
			root.lastname_hkana = profile.getSEI_KANA();
			root.lastname_kanji = profile.getSEI_KANJI();
			root.lastname_kkana = KanaConversion.getConversionString( profile.getSEI_KANA() );
			root.sex = "" + profile.getSEX() + "";
			root.telno = profile.getTEL_NO();
		}catch(Exception ex){	ex.printStackTrace();	}
	}
	/**
	 * メンバーの箱を作成
	 */
	public void init( int parent, int child ){
		members.init( 0 );
		members.setMem( root );
		Node[] tempchild = new Node[ child ];
		//Node[] tempparent = new Node[ parent ];
		//子供のノードを作成する。
		for(int j=0; j<child; j++){
			System.err.println("<-----------------Parent Count Out= " + j );
			if( j < parent ){
				//System.err.println("<-----------------Parent Count  In= " + j );
				Member temp = new Member();
				Node tempch = new Node();
				tempch.init(2);
				temp.dDate = 100 + j;
				temp.parentdDate = 10 + j;
				tempch.setMem( temp );
				tempchild[j] = tempch;
				tempch = null;
			}
		}
		for(int i=0; i<parent; i++){
			//System.err.println("<-----------------Parent:2 Count= " + i );
			Member temp = new Member();
			Node tempch = new Node();
			tempch.init( 1 );
			tempch.setParent( members );
			if( i==0 ){
				temp = root;
			}
			temp.dDate = 10 + i;			
			tempch.setMem( temp );
			if( i < child ){
				//System.err.println("<-----------------Child:2 Count= " + i );
				tempchild[ i ].setParent( tempch );	
				tempch.insertNode( tempchild[ i ] );
			}
			//tempparent[i] = tempch;
			members.insertNode( tempch );
			tempch = null;
			temp = null;
		}
	}
	/**
	 * メンバーをセットする。
	 */
	public void setMember( Member member ){
		//System.err.println( "<------Member setMember Start ------->");
		if( member == null )	return;
		int target = member.getDDate();
		Member temp = member; 
		//System.err.println( "<------Member dData------" + member.dDate + "------->");
		if( member.dDate >= temp2 ){
			int j = 0; int k =0;
			for(j=0; j<members.getChildArray().length; j++){
				if( members.getChildArray()[j] != null ){
					Node ch = members.getChildArray()[j];
					for( k=0; k<ch.getChildArray().length; k++){
						if( ch.getChildArray()[k] != null ){
							if( ch.getChildArray()[k].getMem().getDDate() == target){
								Node sm = ch.getChildArray()[k];
								sm.setMem( member );
								ch.insertNode( sm );
								members.insertNode( ch );
								return;
							}	
						}
					}
				}
			}		
		}else if( member.dDate >= temp1 ){
			//System.err.println( "<-------- MemberHundler -340 ------->");
			Node node = this.members.getChildArray( target - temp1 )[ target - temp1 ];//
			if( this.members.getChildArray()[ target - temp1 ] == null ){
				node = new Node();
				node.init( 1 );
				//this.members.setChildArray( node.getChildArray() );
			}
			//System.err.println( "<-----dDate " + member.getDDate() );
			//System.err.println( "<-----Lebal " + node.getLebel() );
			//System.err.println( "<-----Size  " + node.getChildArray().length );
			//System.err.println( "<-----Size  " + members.getChildArray().length );
			//System.err.println( "<-------- MemberHundler -342 ------->");
			node.setMem( member );
			//System.err.println( "<-------- MemberHundler -345 ------->");
			members.insertNode( node );
			//System.err.println( "<-------- MemberHundler -346 ------->");
			return;
		}
	}
	/**
	 * メンバーをセットする。
	 */
	public void setMember( HttpServletRequest request, String targetdate ){
		System.err.println("childsize *" + this.childsize );
		System.err.println("parentsize *" + this.parentsize );
		childsize = 0; parentsize = 0;
		HashMap chkdDate = getDeletedDate( request );
		Vector temp_mems = new Vector();	///////
		boolean exexflg = false;
		for( int index=0; index< request.getParameterValues( "dDate" ).length; index++ ){
			//chkdDateにて来たデータが正しいのか否か//
			if( isSurvival( chkdDate, request.getParameterValues( "dDate" )[index] ) ){
				Member mem = getMember( request, index, targetdate);
				if(mem.getDDate() >= temp2){
					childsize++;
				}else if(mem.getDDate() >= temp1){
					parentsize++;
				}
				//this.setMember( mem );
				temp_mems.add( mem );		///////
				mem = null;
			}else{
				exexflg = true;
			}
		}
		System.err.println("childsize *" + this.childsize );
		System.err.println("parentsize *" + this.parentsize );
		if( exexflg ){	this.init( this.parentsize, this.childsize );		}
		for(int i=0; i<temp_mems.size(); i++){
			this.setMember( (Member)temp_mems.get( i ) );
		}
		//サイズを変更する。
	}
	private boolean isSurvival( HashMap base, String str ){
		System.err.println( "HashMap size is : " + base.size() );
		System.err.println( "Str is : " + str );
		System.err.println( "base.get( str ) is : " + base.get( str ) );
		if(( str.length() > 0 )&&( base.get( str ) == null )){
			return true;
		}
		return false;
	}
	/**
	 * チェックを行う。
	 */
	private static HashMap getDeletedDate( HttpServletRequest request ){
		HashMap rehashMap = new HashMap();
		String[] chkdDate = request.getParameterValues( "chkdDate" );
		if( chkdDate != null ){
			for(int index=0; index<chkdDate.length; index++){
				rehashMap.put( chkdDate[index], chkdDate[index] );				
			}			
		}
		return rehashMap;
	}
	/**
	 * SJISコンバート
	 */
	//変換ルーチン
    private static String getConv2Sjis( String str ) throws IOException {
    	return new String( str.getBytes("ISO-8859-1"), "Shift_JIS" ) ;
    }
	/**
	 * メンバーのセット
	 */
	synchronized public Member getMember( HttpServletRequest request, int index, String targetdate ){
		Member member = new Member();
		try{
			if( request.getParameterValues( "dDate" ) != null ){
			member.dDate = Integer.parseInt( request.getParameterValues( "dDate" )[index] );
			}
			if( request.getParameterValues( "firstname_alp" ) != null ){
			member.firstname_alp = request.getParameterValues( "firstname_alp" )[index];
			}
			if( request.getParameterValues( "firstname_hkana") != null ){
			member.firstname_hkana = getConv2Sjis( request.getParameterValues( "firstname_hkana" )[index] );
			}
			if( request.getParameterValues( "firstname_kanji" ) != null ){
			member.firstname_kanji = getConv2Sjis( request.getParameterValues( "firstname_kanji" )[index] );
			}
			if( request.getParameterValues( "firstname_kkana" ) != null ){
			member.firstname_kkana = getConv2Sjis( request.getParameterValues( "firstname_kkana" )[index] );
			}
			if( request.getParameterValues( "lastneme_alp" ) != null ){
			member.lastneme_alp = request.getParameterValues( "lastneme_alp" )[index];
			}
			if( request.getParameterValues( "lastname_hkana" ) != null ){
			member.lastname_hkana = getConv2Sjis( request.getParameterValues( "lastname_hkana" )[index] );
			}
			if( request.getParameterValues( "lastname_kanji" ) != null ){
			member.lastname_kanji = getConv2Sjis( request.getParameterValues( "lastname_kanji" )[index] );
			}
			if( request.getParameterValues( "lastname_kkana" ) != null ){
			member.lastname_kkana = getConv2Sjis( request.getParameterValues( "lastname_kkana" )[index] );
			}
			if( request.getParameterValues( "sex" ) != null ){
			member.sex = request.getParameterValues( "sex" )[index];
			}
			if( request.getParameterValues( "years" ) != null ){
			member.years = request.getParameterValues( "years" )[index];
			}
			if( request.getParameterValues( "months" ) != null ){
			member.months = request.getParameterValues( "months" )[index];
			}
			if( request.getParameterValues( "days" ) != null ){
			member.days = request.getParameterValues( "days" )[index];
			}
			if( request.getParameterValues( "passport" ) != null ){
			member.passport = request.getParameterValues( "passport" )[index];
			}
			if( request.getParameterValues( "telno" ) != null ){
			member.telno = request.getParameterValues( "telno" )[index];
			}
			member.setTargetdate( targetdate );
			Checkmember memb = (Checkmember)Class.forName( this.classname ).newInstance();
			memb.setMember( member );
			if( !memb.Check()){
				this.reBoolean = false;
			}else{
				if( this.reBoolean ){
					this.reBoolean = true;
				}
			}
			return (Member)memb;
		}catch(Exception ex){
			ex.printStackTrace();
			this.reBoolean = false;
		}
		return member;
	}
	/**
	 * エラーの確認
	 */
	public boolean isChecked(){
		return reBoolean;
	}
	/**
	 * Returns the classname.
	 * @return String
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * Returns the members.
	 * @return Node
	 */
	public Node getMembers() {
		return members;
	}

	/**
	 * Returns the reBoolean.
	 * @return boolean
	 */
	public boolean isReBoolean() {
		return reBoolean;
	}

	/**
	 * Returns the size.
	 * @return int
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the classname.
	 * @param classname The classname to set
	 */
	public void setClassname(String classname) {
		this.classname = classname;
	}

	/**
	 * Sets the members.
	 * @param members The members to set
	 */
	public void setMembers(Node members) {
		this.members = members;
	}

	/**
	 * Sets the reBoolean.
	 * @param reBoolean The reBoolean to set
	 */
	public void setReBoolean(boolean reBoolean) {
		this.reBoolean = reBoolean;
	}

	/**
	 * Sets the size.
	 * @param size The size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the childsize.
	 * @return int
	 */
	public int getChildsize() {
		return childsize;
	}

	/**
	 * Returns the parentsize.
	 * @return int
	 */
	public int getParentsize() {
		return parentsize;
	}

	/**
	 * Sets the childsize.
	 * @param childsize The childsize to set
	 */
	public void setChildsize(int childsize) {
		this.childsize = childsize;
	}

	/**
	 * Sets the parentsize.
	 * @param parentsize The parentsize to set
	 */
	public void setParentsize(int parentsize) {
		this.parentsize = parentsize;
	}

}
