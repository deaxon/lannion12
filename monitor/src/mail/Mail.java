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
	
	public boolean sendMail(InternetAddress[] toAddrs, String subject,String content) {
		boolean result = false;
		try {
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					toAddrs);
			message.setSubject(subject);
			message.setContent(content,
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
