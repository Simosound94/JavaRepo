package sample1;

public class Waiter implements Runnable {
	private Object guard;
	
	public Waiter(Object o) {
		this.guard = o;
	}
	public void run() {
		System.out.println("Waiter started");
		synchronized(this.guard) {
			try {
				System.out.println("Waiter is waiting...");
				this.guard.wait(); // 
			} catch (InterruptedException e) {
				
			}		
			System.out.println("Waiter resuming");
		}
	}
}
