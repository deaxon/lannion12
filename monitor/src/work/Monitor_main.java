package work;

import java.io.File;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import mail.Mail;
import sms.Sms;
import timer.Timer_monitoring;
import trace.Trace;
import urlConnection.ConnectionURL;

public class Monitor_main {

	/**
	 * @param args
	 */
	private String url;
	private File FICHIER;
	private boolean logConnect = true;
	private boolean urlConnect;
	private InternetAddress toAddrsMail[];
	private String number;
	private String operator;
	private ConnectionURL myURLConnection;
	private Sms sms;
	private Mail mail;

	public Monitor_main(String url, File FICHIER,
			InternetAddress toAddrsMail[], String number, String operator) {
		this.sms = new Sms();
		this.myURLConnection = new ConnectionURL();
		this.url = url;
		this.FICHIER = FICHIER;
		this.urlConnect = myURLConnection.urlConnect(url);
		this.mail = new Mail();
		this.toAddrsMail = toAddrsMail;
		this.number = number;
		this.operator = operator;
	}

	public boolean startMonitoring() {
		Timer_monitoring timer_monitoring = new Timer_monitoring();
		timer_monitoring.getTimer().start();
		String subject;
		String content = "";
		boolean finished = false;
		while (!finished) {
			try {
				Thread.sleep(10000);
				if (urlConnect && logConnect) {
					Trace trace = new Trace(FICHIER, logConnect, urlConnect);
					subject = "Access to sitescrum success";
					content = "Congratulations. The website is online and the connexion success.";
					sms.sendSms(number, operator, subject, content);
					mail.sendMail(toAddrsMail, subject, content);
					trace.createLog();
					timer_monitoring.getTimer().stop();
					finished = true;
				} else if (timer_monitoring.getTimeRemaining() <= 0) {
					Trace trace = new Trace(FICHIER, logConnect, urlConnect);
					subject = "Access to sitescrum fail";
					if (!urlConnect && logConnect) {
						content = "The website is not working. The access failed.";
					} else if (!logConnect && urlConnect) {
						content = "The connexion to the website failed.";
					} else {
						content = "The website is not working. The access and connexion to the website failed";
					}
					sms.sendSms(number, operator, subject, content);
					mail.sendMail(toAddrsMail, subject, content);
					trace.createLog();
					finished = true;
				}
				urlConnect = myURLConnection.urlConnect(url);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		return finished;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
		File FICHIER = new File("/home/exituser/Desktop/application.log");
		InternetAddress toAddrsMail[] = new InternetAddress[1];
		try {
			toAddrsMail[0] = new InternetAddress("crenn.arthur@gmail.com");
		} catch (AddressException e) {
			e.printStackTrace();
		}
		String number = "819 580 9150";
		String operator = "fido";
		Monitor_main monitor = new Monitor_main(url, FICHIER, toAddrsMail,
				number, operator);
		monitor.startMonitoring();
	}
}
