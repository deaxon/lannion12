package trace;

import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;

import urlConnection.ConnectionURL;

public class TraceTest {

	File FICHIER = new File("application" + ".log");
	boolean loginConnect = true;
	String nameSite = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
	ConnectionURL myURLConnection = new ConnectionURL();
	boolean urlConnect = myURLConnection.urlConnect(nameSite);
	Trace trace = new Trace(FICHIER, loginConnect, urlConnect);

	@Test
	public void testCreateLog() {
		boolean res = false;
		res = trace.createLog();
		assertTrue(res == true);
	}
	
	@Test
	public void testFormatDateTime(){
		int number = 6;
		String newNumber = trace.formatDateTime(number);
		System.out.println(newNumber);
		assertTrue(newNumber.equals("06"));
	}

	@Test
	public void testCountLineFile() {
		int res = 0;
		res = trace.countLineFile();
		assertTrue(res == 1);
	}

	@Test
	public void testReplaceCaractFileInTab() {
		String line = "Application OK : 18/4/2012 9:56:31 0";
		String[] tab = trace.replaceCaractFileInTab(line);
		assertTrue(tab[4].equals("2012"));
		assertTrue(tab[8].equals("0"));

	}

	@Test
	public void testChangeNumberCounter() {
		trace.createLog();
		trace.incrementLog();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeNumberCounter(tab);
		assertTrue(newTab[4].equals("2012"));
	}

	@Test
	public void testChangeFormat1Line() {
		trace.createLog();
		trace.incrementLog();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeFormat1Line(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testCopyFileTab() {
		String[] tab = trace.copyFileTab();
		assertTrue(tab[1].equals("Application OK 19 4 2012 12 52 5 1"));
	}

	@Test
	public void testReplaceFormat() {
		String line = "Application OK 19 12 2012 91 11 11 1";
		line = trace.replaceFormat(line);
		System.out.println(line);
		assertTrue(line.equals("Application OK 19/12/2012 9:1:1 1"));
	}

	@Test
	public void testIncrementLog() {
		trace.incrementLog();
	}

	@Test
	public void testAccessConnexionSuccess() {
		trace.createLog();
		trace.incrementLog();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.accessConnexionSuccess(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testAccessFailed() {
		trace.createLog();
		trace.incrementLog();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.accessFailed(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testConnexionFailed() {
		trace.createLog();
		trace.incrementLog();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.connexionFailed(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testConnexionAccessFailed() {
		trace.createLog();
		trace.incrementLog();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.connexionAccessFailed(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testEraseFile() throws Exception {
		File file = new File("application" + ".log");
		boolean res = false;
		res = trace.eraseFile(file);
		assertTrue(res == true);
	}

}