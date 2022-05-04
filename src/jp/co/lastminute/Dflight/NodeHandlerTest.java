package jp.co.lastminute.Dflight;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.netbeans.junit.*;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class NodeHandlerTest extends NbTestCase {
	private NodeHandler obj = null;
	/**
	 * Constructor for NodeHandlerTest.
	 * @param arg0
	 */
	public NodeHandlerTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(NodeHandlerTest.class);
	}

	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		obj = new NodeHandler();
	}
	public static Test suite() {
		TestSuite suite =
			new TestSuite("Test for jp.co.lastminute.Dflight.NodeHandlerT");
		//$JUnit-BEGIN$
		suite.addTest( new NodeHandlerTest("testSetSqldata") );
		//$JUnit-END$
		return suite;
	}
	public void testNodeHandler() {
	}

	public void testFind() {
	}

	/*
	 * void insert ‚ÌƒeƒXƒg(P2PAirport)
	 */
	public void testInsertP2PAirport() {
	}

	public void testGetflightNodesList() {
	}

	/*
	 * void insert ‚ÌƒeƒXƒg(P2PAirport, String)
	 */
	public void testInsertP2PAirportString() {
	}

	public void testSetdailyFlight() {
	}

	public void testGetFlight() {
	}

	public void testSetSqldata() {
		int count = 32;
		int size = 21;
		Vector rows = new Vector();
		for(int i=0; i<count; i++){
			Vector row = new Vector();
			for(int j=0; j<size; j++){
				if( j == 0 || j == 2 || j == 8 || j == 9 
				|| j == 10 || j == 11 || j == 12 || j == 13
				|| j == 14 || j == 15 || j == 16 ){
						if( sampledate[i][j].length() == 0 )	sampledate[i][j] = "0";
						row.add( Integer.valueOf( sampledate[i][j] ) );
					}else{
						row.add( sampledate[i][j] );
					}
				System.out.println( "i:" + i + ": J" + j + " = " + sampledate[i][j]  );
			}
			rows.add( row );
		}
		obj.setSqldata( rows );
		//obj.getXmlString();
		System.out.println( obj.getXmlString() );		
	}

	public void testGetXmlString() {
	}

	public void testObject() {
	}

	public void testGetClass() {
	}

	public void testHashCode() {
	}

	public void testEquals() {
	}

	public void testClone() {
	}

	public void testToString() {
	}

	public void testNotify() {
	}

	public void testNotifyAll() {
	}

	/*
	 * void wait ‚ÌƒeƒXƒg(long)
	 */
	public void testWaitJ() {
	}

	/*
	 * void wait ‚ÌƒeƒXƒg(long, int)
	 */
	public void testWaitJI() {
	}

	/*
	 * void wait ‚ÌƒeƒXƒg()
	 */
	public void testWait() {
	}

	public void testFinalize() {
	}
	private static final String[][] sampledate = { 
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "1", "801", "DF00001", "20021225", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "2", "801", "DF00001", "20021226", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "3", "801", "DF00001", "20021227", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "4", "801", "DF00001", "20021228", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "5", "801", "DF00001", "20021229", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "6", "801", "DF00001", "20021230", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "7", "801", "DF00001", "20021231", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "8", "801", "DF00001", "20030101", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "11", "801", "DF00002", "20021225", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "12", "801", "DF00002", "20021226", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "13", "801", "DF00002", "20021227", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "14", "801", "DF00002", "20021228", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "15", "801", "DF00002", "20021229", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "16", "801", "DF00002", "20021230", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "17", "801", "DF00002", "20021231", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","911", "1010", "KOJ", "HND", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "18", "801", "DF00002", "20030101", "Ž­Ž™“‡", "“Œ‹ž‰H“c"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "31", "801", "DF00003", "20021225", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "32", "801", "DF00003", "20021226", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "33", "801", "DF00003", "20021227", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "34", "801", "DF00003", "20021228", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "35", "801", "DF00003", "20021229", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "36", "801", "DF00003", "20021230", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "37", "801", "DF00003", "20021231", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","811", "1008", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "38", "801", "DF00003", "20030101", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "41", "801", "DF00004", "20021225", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "42", "801", "DF00004", "20021226", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "43", "801", "DF00004", "20021227", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "44", "801", "DF00004", "20021228", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "45", "801", "DF00004", "20021229", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "46", "801", "DF00004", "20021230", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "47", "801", "DF00004", "20021231", "“Œ‹ž‰H“c", "Ž­Ž™“‡"},
							{"3","911", "1010", "HND", "KOJ", "E", "Y", "SKY", "20", "8", "1", "15000", "", "", "", "48", "801", "DF00004", "20030101", "“Œ‹ž‰H“c", "Ž­Ž™“‡" }
	};
}
