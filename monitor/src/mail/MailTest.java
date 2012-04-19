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
		boolean res = mail.sendMail();
		assertTrue(res == true);
	}

}
