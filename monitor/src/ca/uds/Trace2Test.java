package ca.uds;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

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
	public void countLineFileTest(){
		Trace trace = new Trace();
		int res = 0;
		res = trace.countLineFile();
		assertTrue(res == 12);
	}
	
	@Test 
	public void IncrementLogTest(){
		
	}
}