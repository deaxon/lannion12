package trace;

import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;

public class TraceTest {

	File FICHIER = new File("application" + ".log");
	boolean fonctionCharlotte = true;
	String nameSite = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
	Trace trace = new Trace(FICHIER, fonctionCharlotte, nameSite);

	@Test
	public void testCreateLog() {
		boolean res = false;
		res = trace.createLog();
		assertTrue(res == true);
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
		//String[] tab = trace.copyFileTab();
		//String[] newTab = trace.changeNumberCounter(tab);
		//assertTrue(newTab[4].equals("2012"));
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
		for(int i = 0; i<tab.length;i++){
			System.out.println(tab[i]);
		}
		assertTrue(tab[1].equals("Application OK 19 4 2012 12 42 16 1"));
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