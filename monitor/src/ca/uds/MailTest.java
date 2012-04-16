/**
 * 
 */
package ca.uds;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import javax.mail.internet.InternetAddress;


/**
 * @author solenemalledant
 *
 */
public class MailTest {

	@Test
	public void sendMailTest() throws Exception{
		Mail mail = new Mail();
		String from = "solene.malledant@gmail.com";
		boolean res = mail.sendMail(from);
		assertTrue(res == true);
		
	}
	
}
