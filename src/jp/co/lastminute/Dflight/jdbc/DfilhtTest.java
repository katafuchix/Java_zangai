package jp.co.lastminute.Dflight.jdbc;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.netbeans.junit.*;
import jp.co.lastminute.Dflight.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DfilhtTest extends NbTestCase{
	private dbAdapterDflight obj = null;
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public DfilhtTest(java.lang.String testName) {
        super(testName);
    }

	public static Test suite() {
		TestSuite suite =
			new TestSuite("Test for jp.co.lastminute.Dflight.jdbc");
		//$JUnit-BEGIN$
		suite.addTest( new DfilhtTest("DfilhtTest01") );
		//$JUnit-END$
		return suite;
	}
	
	public void DfilhtTest01() {
        //System.out.println("testFactorial");
        DflightSearchCondition searchCondition = new DflightSearchCondition();
        // Add your test code below by replacing the default call to fail.
        //assertEquals( obj.getLIst(5), 120 );
        //fail("The test case is empty.");
    }
    protected void setUp(){
        obj = new dbAdapterDflight();
    }
}
