package deadlock;

public class Runner_One implements Runnable {
	private Object mutex1;
	private Object mutex2;
	
	public Runner_One(Object mutex1, Object mutex2) {
		this.mutex1 = mutex1;
		this.mutex2 = mutex2;
	}
	
	public void run() {
		synchronized(mutex1) {
			System.out.println("Runner_One got lock on mutex1");
			System.out.println("Runner_One sleeping");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			synchronized(mutex2) {
				System.out.println("Runner_One got lock on mutex2");
				try {
					System.out.println("Runner_One sleeping");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					
				}
				System.out.println("Runner_One released lock on mutex2");
			}
			System.out.println("Runner_One released lock on mutex1");
		}
	}
}
