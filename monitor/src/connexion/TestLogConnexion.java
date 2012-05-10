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
		boolean b = logCo.sendLogConnexion("http://tictacserver.gel.usherbrooke.ca/projectus/logins/login", "Lannion12ownp1001", "produ");
		
		assertTrue(b == true);
	}
}

