package ca.uds;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;


public class Trace2Test {

	private Trace2 trace = new Trace2();
	@Test
	public void createLogTest() {
		boolean res = false;
		String website = "http://tictacserver.gel.usherbrooke.ca/sitescrum";
		res = trace.createLog(website);
		assertTrue(res == true);
	}
	
	/*@Test
	public void countLineFileTest(){
		int res = 0;
		res = trace.countLineFile();
		assertTrue(res == 12);
	}
	*/
	@Test 
	public void IncrementLogTest(){
		trace.incrementLog();
	}
}