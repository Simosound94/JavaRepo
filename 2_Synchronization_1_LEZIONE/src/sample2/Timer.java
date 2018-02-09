package sample2;

public class Timer {

	/*
	 * e' come se qui fosse scritto:
	 * 
	 * public void timeout(long ms) {
	 * 		synchronized(this) {
	 * 
	 * 		////// etc.
	 * 
	 * 		}
	 * }
	 */
	public synchronized void timeout(long ms) {
		System.out.println("Runner entered protected method, sleeping");
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			
		}
	}
}
