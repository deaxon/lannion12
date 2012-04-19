package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							"solene.malledant@gmail.com", "duriane01");
				}
			});
	
	public boolean sendMail() {
		boolean result = false;
		try {
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Message message = new MimeMessage(session);
			
			InternetAddress toAddrs[] = new InternetAddress[2];
			toAddrs[0] = new InternetAddress("solene.malledant@gmail.com");
			toAddrs[1] = new InternetAddress("crenn.arthur@gmail.com");
			message.setRecipients(Message.RecipientType.TO,
					toAddrs);
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
