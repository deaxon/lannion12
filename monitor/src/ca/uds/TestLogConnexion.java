/**
 * 
 */
package ca.uds;

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
		boolean b = logCo.sendLog(
				"http://tictacserver.gel.usherbrooke.ca/sitescrum", "lan1001",
				"lannion");
		assertTrue(b == true);
	}
}

