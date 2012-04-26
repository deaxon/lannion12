package work;

import static org.junit.Assert.*;

import java.io.File;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;

public class TestMonitor_main {

	@Test
	public void test() {
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
		assertTrue(monitor.startMonitoring(1,1));
	}

}
