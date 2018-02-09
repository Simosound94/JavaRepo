package my.prodcons.deadlock;

public class MainApp {

	public static void main(String[] args) {
		BoundedBuffer bb = new BoundedBuffer(20);
		
		Producer p1 = new Producer("Producer 1 ", bb);
		
		
		Consumer c1 = new Consumer("Consumer 1 ", bb);
		
		Consumer c2 = new Consumer("Consumer 2", bb);
		Thread p1_t = new Thread(p1);
		Thread c1_t = new Thread(c1);
		Thread c2_t = new Thread(c2);
		
		p1_t.start();
		c1_t.start();
		c2_t.start();

	}

}
