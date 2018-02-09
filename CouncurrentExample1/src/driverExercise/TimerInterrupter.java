package driverExercise;

public class TimerInterrupter implements Runnable
{
	Driver d;

	public TimerInterrupter(Driver d){
		this.d = d;
	}
	
	@Override
	public void run() {
		while(true){
			int sleepTime = (int)(Math.random()*10000);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
			int randomInterrupt = (int)(Math.random()*15);
			d.sendInterrupt(randomInterrupt);
			
		}	
	}
	
	

}
