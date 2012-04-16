package work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Timer_monitoring {
	
	private Timer timer;
	
	public Timer_monitoring(){
		
		this.timer = new Timer(1000, new MyTimerActionListener(30));
		
	}
	public void startTimer(){
		
	    this.timer.start();
	 
	    try {
	    	
	    	Thread.sleep(10000); 
	    	
	    } catch (InterruptedException e) {
	    	
	    	System.out.println(e);
	    	
	    }
	    
	    this.timer.stop();
	    
	}
}
	

	class MyTimerActionListener implements ActionListener {
		
	    private int time;

	    public MyTimerActionListener(int init) {
	          super();
	          this.time = init;
	    }

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println(this.time);
			this.time--;
	        if(this.time==0){
	        	System.out.println("Timer fini");  
	          }
		}
}
