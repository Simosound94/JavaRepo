package joinsample;

public class Sleeper implements Runnable {
	private long msecs;
	
	public Sleeper(long msecs) {
		this.msecs = msecs;
	}
	
	public void run() {
		System.out.println("Sleeper has been started");
		try {
			Thread.sleep(this.msecs);
		} catch (InterruptedException e) {
			
		}
		System.out.println("Sleeper terminated");
	}
}
