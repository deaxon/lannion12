package work;

import java.io.File;

import mail.Mail;
import sms.Sms;
import timer.Timer_monitoring;
import trace.Trace;
import urlConnection.ConnectionURL;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Sms sms = new Sms();
		String url = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
		ConnectionURL myURLConnection = new ConnectionURL();
		File FICHIER = new File("application" + ".log");
		boolean fonctionCharlotte = false;
		Trace trace = new Trace(FICHIER, fonctionCharlotte, url);
		Mail mail = new Mail();
		String toMail = "crenn.arthur@gmail.com";
		String subject;
		String content;
		//String number = "819 580 9150";
		//String operator = "fido";
		Timer_monitoring timer_monitoring = new Timer_monitoring();
		timer_monitoring.getTimer().start();
		
		try{
			Thread.sleep(10000);
			if(myURLConnection.urlConnect(url)){
				timer_monitoring.getTimer().stop();
				subject = "Access to sitescrum success";
				content = "Congratulations. The website is online.";
				mail.sendMail(toMail, subject, content);
				trace.createLog();
			}
		}catch (InterruptedException ex){
			ex.printStackTrace();
		}
		
		if(timer_monitoring.getTimeRemaining()==0){
			subject = "Access to sitescrum fail";
			content = "Congratulations. The website is not working.";
			mail.sendMail(toMail, subject, content);
			trace.createLog();
		}
	}
}
