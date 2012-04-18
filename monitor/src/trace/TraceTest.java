package trace;

import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;

public class TraceTest {

	@Test
	public void createLogTest() {
		Trace trace = new Trace();
		boolean res = false;
		String website = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
		res = trace.createLog(website);
		assertTrue(res == true);
	}

	@Test
	public void countLineFileTest() {
		Trace trace = new Trace();
		int res = 0;
		res = trace.countLineFile();
		assertTrue(res == 8);
	}

	@Test
	public void replaceCaractFileInTabTest() {
		Trace trace = new Trace();
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
	public void eraseFileTest() throws Exception {

		Trace trace = new Trace();
		File file = new File("application" + ".log");
		boolean res = false;
		res = trace.eraseFile(file);
		assertTrue(res == true);
	}
	
	@Test
	public void changeNumberCounterTest(){
		Trace trace = new Trace();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeNumberCounter(tab);
		for (int i = 0; i < newTab.length; i++) {
			System.out.println(newTab[i]);
		}
		assertTrue(newTab[8].equals("1"));
	}
	
	@Test
	public void changeNumberCounter1LineTest(){
		Trace trace = new Trace();
		String[] tab = trace.copyFileTab();
		String[] newTab = trace.changeNumberCounter1Line(tab);
		for (int i = 0; i < newTab.length; i++) {
			System.out.println(newTab[i]);
		}
		assertTrue(newTab[8].equals("4"));
	}

	@Test
	public void copyFileTabTest() {
		Trace trace = new Trace();
		String[] tab = trace.copyFileTab();
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
		assertTrue(tab[0].equals("Application OK :  18/4/2012 14:24:51 0"));
	}

	@Test
	public void IncrementLogTest() {
		Trace trace = new Trace();
		trace.incrementLog();
	}
}