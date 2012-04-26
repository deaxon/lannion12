package work;

import java.io.File;
import java.util.ArrayList;

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
	private String _url;
	private File _file;
	private boolean _logConnect = true;
	private boolean _urlConnect;
	private ArrayList<InternetAddress> _internetAdresses;
	private String _number;
	private String _operator;
	private ConnectionURL myURLConnection;
	private Sms sms;
	private Mail mail;

	public Monitor_main(String url, File file,
			ArrayList<InternetAddress> internetAdresses, String number, String operator) {
		this.sms = new Sms();
		this.myURLConnection = new ConnectionURL();
		this._url = url;
		this._file = file;
		this._urlConnect = myURLConnection.urlConnect(url);
		this.mail = new Mail();
		this._internetAdresses = internetAdresses;
		this._number = number;
		this._operator = operator;
	}

	public boolean startMonitoring(int t, int sleep) {
		Timer_monitoring timer_monitoring = new Timer_monitoring(t);
		timer_monitoring.getTimer().start();
		String subject;
		String content = "";
		boolean finished = false;
		while (!finished) {
			try {
				Thread.sleep(sleep);
				if (_urlConnect && _logConnect) {
					Trace trace = new Trace(_file, _logConnect, _urlConnect);
					subject = "Access to sitescrum success";
					content = "Congratulations. The website is online and the connexion success.";
					finished = sendMessage(subject, content, trace);
					timer_monitoring.getTimer().stop();
				} else if (timer_monitoring.getTimeRemaining() <= 0) {
					Trace trace = new Trace(_file, _logConnect, _urlConnect);
					subject = "Access to sitescrum fail";
					if (!_urlConnect && _logConnect) {
						content = "The website is not working. The access failed.";
					} else if (!_logConnect && _urlConnect) {
						content = "The connexion to the website failed.";
					} else {
						content = "The website is not working. The access and connexion to the website failed";
					}
					finished = sendMessage(subject, content, trace);
				}
				_urlConnect = myURLConnection.urlConnect(_url);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		return finished;
	}

	private boolean sendMessage(String subject, String content, Trace trace) {
		boolean finished;
		sms.sendSms(_number, _operator, subject, content);
		mail.sendMail(_internetAdresses, subject, content);
		trace.createLog();
		finished = true;
		return finished;
	}

	public static void main(String[] args) {
		System.out.println("hello");
		String url = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
		//File file = new File("/home/exituser/Desktop/application.log");
		File file = new File("."+ File.separator + "temp4test"+ File.separator + "application" + ".log");
		//InternetAddress toAddrsMail[] = new InternetAddress[1];
		ArrayList<InternetAddress> _internetAdresses = new ArrayList<InternetAddress>();
		try {
			_internetAdresses.add(new InternetAddress("crenn.arthur@gmail.com"));
			_internetAdresses.add(new InternetAddress("ruben.gonzalez-rubio@gel.usherbrooke.ca"));
		} catch (AddressException e) {
			e.printStackTrace();
		}
		String phone = "819 572 14 96";
		String operator = "rogers";
		Monitor_main monitor = new Monitor_main(url, file, _internetAdresses,
				phone, operator);
		monitor.startMonitoring(60, 10000);
		System.out.println("bye");
	}
}
