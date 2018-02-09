package deadlock;

public class Runner_Two implements Runnable {
	private Object mutex1;
	private Object mutex2;
	
	public Runner_Two(Object mutex1, Object mutex2) {
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
	}
	
	public void run() {
		synchronized(mutex2) {
			System.out.println("Runner_Two got lock on mutex2");
			try {
				//Sleep per dar tempo all'altro runner di prendere l'altro mutex
				System.out.println("Runner_Two sleeping");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
			}
			synchronized(mutex1) {
				System.out.println("Runner_Two got lock on mutex1");
				try {
					System.out.println("Runner_Two sleeping");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					
				}
				System.out.println("Runner_Two released lock on mutex1");
			}
			System.out.println("Runner_Two released lock on mutex2");
		}
		
	}
}
