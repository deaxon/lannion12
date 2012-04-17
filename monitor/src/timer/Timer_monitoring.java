package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Timer_monitoring {
	
	public static final int TIME = 20;
	
	private Timer timer;
	private int timeRemaining = 0;
	
	public Timer_monitoring(){
		
		this.timer = new Timer(1000, new MyTimerActionListener(TIME));
		
	}
	public void startTimer(){
		
	    this.timer.start();
	    
	    try{
	    	
	    	Thread.sleep(1000*TIME);
	    	
	    } catch (InterruptedException e) {
	    	
	    	System.out.println(e);
	    	    	
	    }	
	 
	    this.timer.stop();
	    
	}
	public int getTimeRemaining() {
		return timeRemaining;
	}

	public Timer getTimer(){
		return timer;
	}

	class MyTimerActionListener implements ActionListener {
		
	    private int time;

	    public MyTimerActionListener(int init) {
	          super();
	          this.time = init;
	          timeRemaining = init;
	          
	    }

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			//System.out.println(this.time);
			
			this.time--;
			timeRemaining--;
			
	        if(this.time==0){
	        	 
	        	timer.stop();
	        	
	        }
		}
	}
}
