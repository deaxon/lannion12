/**
 * 
 */
package mail;

import static org.junit.Assert.assertTrue;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;

/**
 * @author solenemalledant
 * 
 */
public class MailTest {

	@Test
	public void testSendMail() {
		Mail mail = new Mail();
		InternetAddress toAddrs[] = new InternetAddress[2];
		try {
			toAddrs[0] = new InternetAddress("solene.malledant@gmail.com");
			toAddrs[1] = new InternetAddress("crenn.arthur@gmail.com");
		} catch (AddressException e) {
			e.printStackTrace();
		}
		boolean res = mail.sendMail(toAddrs);
		assertTrue(res == true);
	}

}
