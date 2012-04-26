/**
 * 
 */
package mail;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		String subject = "Test Mail.";
		String content = "This is a test of the function sendMail.";
		ArrayList<InternetAddress> internetAdresses = new ArrayList<InternetAddress>();
		try {
			internetAdresses.add(new InternetAddress("solene.malledant@gmail.com"));
			internetAdresses.add(new InternetAddress("crenn.arthur@gmail.com"));
		} catch (AddressException e) {
//		InternetAddress toAddrs[] = new InternetAddress[2];
//		try {
//			toAddrs[0] = new InternetAddress("solene.malledant@gmail.com");
//			toAddrs[1] = new InternetAddress("crenn.arthur@gmail.com");
//		} catch (AddressException e) {
		e.printStackTrace();
		}
		boolean res = mail.sendMail(internetAdresses, subject,content);
		assertTrue(res == true);
	}

}
