package sample4;

public class Countdown implements Runnable {
	private String name;
	private Object mutex;
	
	public Countdown(String name, Object mutex) {
		this.name = name;
		this.mutex = mutex;
	}
	
	public void run() {
		synchronized(mutex) {
			for(int i = 10; i>=0; i--) {
				System.out.println("Countdown " + this.name + ": " + i);
			}
		}
	}
}
