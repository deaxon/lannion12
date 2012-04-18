/**
 * 
 */
package ca.uds;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author solenemalledant
 * 
 */
public class MailTest {

	@Test
	public void sendMailTest() {
		Mail mail = new Mail();
		String to = "solene.malledant@gmail.com";
		boolean res = mail.sendMail(to);
		assertTrue(res == true);

	}

}
