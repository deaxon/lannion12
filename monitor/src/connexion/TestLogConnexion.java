/**
 * 
 */
package connexion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * @author Charlotte
 * 
 */
public class TestLogConnexion {
	@Test
	public void testSendLog() {
		//System.out.println("in testSendLog");
		LogConnexion logCo = new LogConnexion();
		//boolean b = logCo.sendLogConnexion(
				//"http://www.lesdelliens.com/forums/index.php?app=core&module=global&section=login", "victordayeur", "fdeb599f767fe92");
		boolean b = logCo.sendLogConnexion(
				"http://tictacserver.gel.usherbrooke.ca/sitescrum/", "lan1001", "lannion");
		assertTrue(b == true);
	}
}

