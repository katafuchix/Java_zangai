package jp.co.lastminute.cart.members;

import java.io.*;
import java.lang.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Node implements Serializable{
	private static final int ORDER = Property.MEMBERLENGTH_;
	/////////////////////////////////////////
	private int lebel;
	private Node parent;
	private Node[] childArray ; //= new Node[ ORDER ];
	private Member mem;
	public Node(){	}
	public void init( int lebal ){
		setChildArrayLebal( lebal );
	}
	private void setChildArrayLebal( int lebal ){
		if( lebal == 0 ){
			childArray = new Node[ ORDER ];
			this.lebel = 0;
		}else if( lebal == 1 ){
			childArray = new Node[ 1 ];
			this.lebel = 1;
		}else{
			childArray = null;
			this.lebel = 2;
		}
	}
	/**
	 * ノードの追加
	 */
	public boolean insertNode( Node node ){
		try{
			System.err.println("<-----------------Insert Node=" );
			int temp1 = 10 ; int temp2 = 100 ;
			if( node.lebel == 1 ){	
				System.err.println("<-----------------Insert Node= L : 1 ");
				childArray[ node.getMem().getDDate() - temp1] = node;
				return true;
			}else if( node.lebel == 2 ){
				System.err.println("<-----------------Insert Node= L : 2 ");
				childArray[ 0 ] = node;
				return true;
			}
		}catch(Exception ex){	ex.printStackTrace();	}
		return false;
	}
	public Node getParent(){
		return this.parent;	
	}
	/**
	 * 
	 */
	private Node getParent( Node node ) throws Exception{ 
		return node.parent;
	}
	private Node getChild( Node node, int index ) throws Exception{
		return node.childArray[index];
	}
	private int getCount( Node node ){
		if( node.lebel == 0 ){
			return ORDER;
		}
		return 1;
	}
	public String getXmlString(){
		Node node = this;
		Node base = node.parent;
		String reStr = "";
		try{
			StringBuffer stb = new StringBuffer();
			stb.append("<MEMBERS>\n");
			stb.append("<MAINPAERSON>\n");
			stb.append("</MAINPAERSON>\n");
			for(int j=0; j<getCount( node ); j++){
				if( (childArray[j] != null )&&(childArray[j].mem.isMember())){
					stb.append("<MEMBER>\n");
						stb.append("<PARENT>\n");
						stb.append( childArray[j].mem.getXmlString() );
						stb.append("</PARENT>\n");					
						if(( childArray[j].childArray[0] != null )
							&&( childArray[j].childArray[0].mem.isMember() )){
							stb.append("<CHILD>\n");
							stb.append( childArray[j].childArray[0].mem.getXmlString() );
							stb.append("</CHILD>\n");
						}			
					stb.append("</MEMBER>\n");
				}
			}
			stb.append("</MEMBERS>\n");
			reStr = stb.toString();
		}catch(Exception ex){	ex.printStackTrace();	}
		return reStr;
	}
	/**
	 * Returns the oRDER.
	 * @return int
	 */
	public static int getORDER() {
		return ORDER;
	}

	/**
	 * Returns the childArray.
	 * @return Node[]
	 */
	public Node[] getChildArray(){
		if( childArray == null ){
			System.err.println( "<------This Lebal IS ---" + this.lebel + "----->");
			setChildArrayLebal( this.lebel );
		}
		return childArray;
	}
	public Node[] getChildArray(int num){
		if( childArray == null ){
			System.err.println( "<------This Lebal IS ---" + this.lebel + "----->");
			setChildArrayLebal( this.lebel );
		}
		if( childArray[ num ] == null ){
			System.err.println( "<------This Lebal NULL AND IS ---" + this.lebel + "----->");
			if( childArray.length == 8 ){
				System.err.println( "<------This Lebal 1 ADD ----->");
				Node tempnode = new Node();
				tempnode.setChildArrayLebal(1);
				childArray[num] = tempnode;
				tempnode = null;
			}else if(childArray.length == 1){
				System.err.println( "<------This Lebal 2 ADD ----->");
				Node tempnode = new Node();
				tempnode.setChildArrayLebal(2);
				childArray[0] = tempnode;
				tempnode = null;
			}else if(childArray.length == 0){
				System.err.println( "<------This Lebal 0 ADD ----->");
				setChildArrayLebal( this.lebel );
			}
		}
		return childArray;
	}

	/**
	 * Returns the lebel.
	 * @return int
	 */
	public int getLebel() {
		return lebel;
	}

	/**
	 * 親要素を取り出す
	 */
	public static String[][] getParentNodeList( Node tagnode ){
		try{
			Node[] arrays = tagnode.getParent().childArray;
			int size = arrays.length;
			String[][] reStr = new String[2][size];
			String[] option = new String[size];
			String[] value = new String[size];
			for(int i=0; i<size; i++){
				value[i] = Double.toString( arrays[i].getMem().dDate );
				option[i] = arrays[i].getMem().getFirstname_alp()
						 + arrays[i].getMem().firstname_kanji
						 + arrays[i].getMem().lastname_kanji;
			}
			reStr[0] = value;
			reStr[1] = option;
			return reStr;
		}catch(Exception ex){	ex.printStackTrace();	}
		return null;
	}
	/**
	 * Sets the parent.
	 * @param parent The parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Sets the mem.
	 * @param mem The mem to set
	 */
	public void setMem(Member mem) {
		this.mem = mem;
	}

	/**
	 * Returns the mem.
	 * @return Member
	 */
	public Member getMem() {
		return mem;
	}

	/**
	 * Sets the childArray.
	 * @param childArray The childArray to set
	 */
	public void setChildArray(Node[] childArray) {
		this.childArray = childArray;
	}

	/**
	 * Sets the lebel.
	 * @param lebel The lebel to set
	 */
	public void setLebel(int lebel) {
		this.lebel = lebel;
	}

}
