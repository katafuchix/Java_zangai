package jp.co.yobrain.util.jdbc;

import java.lang.*;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.netbeans.junit.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SqlmakerTests extends NbTestCase{
	private Sqlmaker obj = null;
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SqlmakerTests.class);
	}
	public SqlmakerTests(java.lang.String testName) {
        super(testName);
    }
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for jp.co.yobrain.util.jdbc");
		//$JUnit-BEGIN$
		suite.addTest( new SqlmakerTests("strPrintf001") );
		//$JUnit-END$
		return suite;
	}
	public void strPrintf001() {
        //System.out.println("testFactorial");
        String sqlstr = jp.co.lastminute.Dflight.Property.listsql;
		String[] target = { "20021227", "800", "'HND','KOJ'", "'KOJ','HND'" };
		String resutl = jp.co.lastminute.Dflight.PropResult.listsql;
		System.out.println( obj.strPrintf( sqlstr, target ) );
		System.out.println( resutl );
        // Add your test code below by replacing the default call to fail.
        assertEquals( obj.strPrintf( sqlstr, target ), resutl );
        //fail("The test case is empty.");
    }
}
