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
		assertTrue(res == 8);
	}

	@Test
	public void testReplaceCaractFileInTab() {
		String line = "Application OK : 18/4/2012 9:56:31 0";
		String[] tab = trace.replaceCaractFileInTab(line);
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
		assertTrue(tab[4].equals("2012"));
		assertTrue(tab[8].equals("0"));
		assertTrue(tab[6].equals("90"));
	}

	@Test
	public void testEraseFile() throws Exception {
		File file = new File("application" + ".log");
		boolean res = false;
		res = trace.eraseFile(file);
		assertTrue(res == true);
	}
	
	@Test
	public void testChangeNumberCounter(){
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeNumberCounter(tab);
		for (int i = 0; i < newTab.length; i++) {
			System.out.println(newTab[i]);
		}
		assertTrue(newTab[8].equals("1"));
	}
	
	@Test
	public void testChangeFormat1Line(){
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeFormat1Line(tab);
		for (int i = 0; i < newTab.length; i++) {
			System.out.println(newTab[i]);
		}
		assertTrue(newTab[8].equals("4"));
	}

	@Test
	public void testCopyFileTab() {
		String[] tab = trace.copyFileTab();
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
		assertTrue(tab[0].equals("Application OK :  18/4/2012 14:24:51 0"));
	}

	@Test
	public void testIncrementLog() {
		trace.incrementLog();
	}
	
	@Test
	public void testAccessConnexionSuccess() {
		
	}

	@Test
	public void testAccessFailed() {
		
	}

	@Test
	public void testConnexionFailed() {
		
	}

	@Test
	public void testConnexionAccessFailed() {
		
	}

}