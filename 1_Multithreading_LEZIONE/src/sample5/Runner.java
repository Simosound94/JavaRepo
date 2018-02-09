package sample5;

public class Runner implements Runnable {
	public void run() {
		// run forever...
		while(true) {
			System.out.println("Runner is running...");
			if(Thread.interrupted()) {
				System.out.println("Runner has been interrupted...");
				break;
			}
		}
	}
}
