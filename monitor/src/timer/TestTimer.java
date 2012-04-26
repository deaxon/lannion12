package timer;

import org.junit.Test;

public class TestTimer {

	@Test
	public void testTimerMonitoring() {
		Timer_monitoring timer_monitoring = new Timer_monitoring(1);
		timer_monitoring.startTimer(1);
		assert timer_monitoring.getTimeRemaining() == 0;
	}

}
