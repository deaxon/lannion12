/**
 * 
 */
package mail;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author solenemalledant
 * 
 */
public class MailTest {

	@Test
	public void testSendMail() {
		Mail mail = new Mail();
		String to = "solene.malledant@gmail.com";
		boolean res = mail.sendMail(to);
		assertTrue(res == true);
	}

}
