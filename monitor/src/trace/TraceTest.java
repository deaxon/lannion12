package trace;

import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;

import urlConnection.ConnectionURL;

public class TraceTest {

	File file = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application" + ".log");
	boolean loginConnect = true;
	String nameSite = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
	ConnectionURL myURLConnection = new ConnectionURL();
	boolean urlConnect = myURLConnection.urlConnect(nameSite);
	Trace trace = new Trace(file, loginConnect, urlConnect);

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
	public void testChangeFormat1Line() {
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeFormat1Line(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testCopyFileTab() {
		File file2 = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application2" + ".log");
		Trace trace2 = new Trace(file2, loginConnect, urlConnect);
		String[] tab = trace2.copyFileTab();
		assertTrue(tab[0].equals("Application OK 27/04/2012 10:01:07 0 "));
	}

	@Test
	public void testReplaceFormat() {
		String line = "Application OK 19 12 2012 91 11 11 1";
		line = trace.replaceFormat(line);
		assertTrue(line.equals("Application OK 19/12/2012 91:11:11 1"));
	}

	@Test
	public void testIncrementLog() {
		trace.incrementLog();
	}

	@Test
	public void testAccessConnexionSuccess() {
		File file3 = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application3" + ".log");
		Trace trace3 = new Trace(file3, loginConnect, urlConnect);
		String[] tab = trace3.copyFileTab();
		String[] newTab = trace3.accessConnexionSuccess(tab);
		assertTrue(newTab[1].equals("OK"));
	}

	@Test
	public void testAccessFailed() {
		File file4 = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application4" + ".log");
		Trace trace4 = new Trace(file4, loginConnect, false);
		String[] tab = trace4.copyFileTab();
		String[] newTab = trace4.accessFailed(tab);
		assertTrue(newTab[1].equals("non"));
	}

	@Test
	public void testConnexionFailed() {
		File file5 = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application5" + ".log");
		Trace trace5 = new Trace(file5, false, urlConnect);
		String[] tab = trace5.copyFileTab();
		String[] newTab = trace5.connexionFailed(tab);
		assertTrue(newTab[1].equals("non"));
	}

	@Test
	public void testConnexionAccessFailed() {
		File file6 = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application6" + ".log");
		Trace trace6 = new Trace(file6, false, false);
		String[] tab = trace6.copyFileTab();
		String[] newTab = trace6.connexionAccessFailed(tab);
		assertTrue(newTab[1].equals("non"));
	}

	@Test
	public void testChangeNumberCounter() {
		File file7 = new File("."+ File.separator + "temp4test"+ File.separator + "testing_application7" + ".log");
		Trace trace7 = new Trace(file7, true, urlConnect);
		String[] tab = trace7.copyFileTab();
		String[] newTab = trace7.changeNumberCounter(tab);
		assertTrue(newTab[8].equals("1"));
	}
	
	@Test
	public void testEraseFile() throws Exception {
		boolean res = false;
		res = trace.eraseFile(file);
		assertTrue(res == true);
	}

}