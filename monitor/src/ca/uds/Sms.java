/**
 * 
 */
package ca.uds;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Charlotte
 *
 */
public class Sms {
		
	public boolean sendSms(String phoneNumber, String operator){
		boolean result = false;
		phoneNumber = phoneNumber.replaceAll(" ", "");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		try {
			Session session = Session.getDefaultInstance(props,

			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							"solene.malledant@gmail.com", "duriane01");
				}
			});
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(phoneNumber + "@" + operator + ".ca"));
			message.setSubject("Blablabla");
			message.setContent("Ceci est un mail.",
					"text/html; charset=ISO-8859-1");
			Transport.send(message);
			result = true;
		} catch (MessagingException ex) {
			System.err.println("Cannot send email. " + ex);
			result = false;
		}
		return result;
	}
}