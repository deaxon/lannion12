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
		String line = "Application OK :  18/4/2012 9:56:31 0";
		String[] tab = trace.replaceCaractFileInTab(line);
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
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
	public void IncrementLogTest() {
		Trace trace = new Trace();
		trace.incrementLog();
	}
}