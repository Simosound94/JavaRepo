package my.prodcons.deadlock;

public class Producer implements Runnable {
	private String name;
	private BoundedBuffer bb;
	
	public Producer(String name, BoundedBuffer bb) {
		this.name = name;
		this.bb = bb;
	}
	
	public void run() {		
		while(true) {
			String s = this.produce();		
			bb.put1(s, this.name);
			System.out.println("Thread " + this.name + " added string " + s);
		}
	}
	
	private String produce() {
		String s;
		
		// time to produce max 2 secs
		long msleep = (long)(Math.random() * 2000);
		try {
			Thread.sleep(msleep);
		} catch (InterruptedException e) {
			
		}
		s = "" + (long)(Math.random() * 100000);		
		return s;
	}
}
