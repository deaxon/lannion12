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
		LogConnexion logCo = new LogConnexion();
		boolean b = logCo.sendLogConnexion("http://exitlabserver.gel.usherbrooke.ca/sitescrum/logins/login", "admin1001", "admin");
		
		assertTrue(b == true);
	}
}

