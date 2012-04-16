package timer;

import org.junit.Test;


public class TestTimer {

	@Test
	public void testTimerMonitoring() {
		
		Timer_monitoring timer_monitoring = new Timer_monitoring();
		
		timer_monitoring.startTimer();
		
		assert timer_monitoring.getTimeRemaining() == 0;

	}

}
