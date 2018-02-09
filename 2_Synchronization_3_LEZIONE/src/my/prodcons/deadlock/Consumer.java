package my.prodcons.deadlock;

public class Consumer implements Runnable {
	private String name;
	private BoundedBuffer bb;

	public Consumer(String name, BoundedBuffer bb) {
		this.name = name;
		this.bb = bb;
	}

	public void run() {		
		while(true) {
			String s = bb.get1(this.name);	
			System.out.println("Thread " + this.name + " retrieved string " + s);
			this.consume(s);
		}
	}

	private void consume(String s) {
		// time to consume max 2 secs
		long msleep = (long)(Math.random() * 2000);
		try {
			Thread.sleep(msleep);
		} catch (InterruptedException e) {

		}
	}
}