package sample5;

public class Timer {

	public synchronized static void timeout(long ms) {
			System.out.println("Runner entered protected method, sleeping");
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {

			}
	}
}
