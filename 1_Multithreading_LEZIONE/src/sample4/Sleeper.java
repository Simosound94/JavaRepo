package sample4;

public class Sleeper implements Runnable {
	private String name;
	
	public Sleeper(String name) {
		this.name = name;
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Sleeper going to sleep");
				Thread.sleep(2000);
				System.out.println("Sleeper wake up");
			} catch (InterruptedException e) {
				System.out.println("Sleeper has been interrupted");
			}
		}
	}
}
