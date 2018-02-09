package sample1;

public class Notifier implements Runnable {
	private Object guard;
	
	public Notifier(Object o) {
		this.guard = o;		
	}
	
	public void run() {
		synchronized(this.guard) {
			System.out.println("Notifier is notifying..");
			this.guard.notify();
		}
	}
	
}
