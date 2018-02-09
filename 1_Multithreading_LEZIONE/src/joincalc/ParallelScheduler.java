package joincalc;

public class ParallelScheduler {

	public static void main(String[] args) {
		Calculator c1 = new Calculator(100000000);
		Calculator c2 = new Calculator(200000000);
		Calculator c3 = new Calculator(300000000);
		
		Thread t_c1 = new Thread(c1);
		Thread t_c2 = new Thread(c2);
		Thread t_c3 = new Thread(c3);
		
		long start = System.currentTimeMillis();
		
		t_c1.start();
		t_c2.start();
		t_c3.start();
		
		try {
			t_c1.join();
			t_c2.join();
			t_c3.join();
		} catch (InterruptedException e) {
			
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time elapsed = " + (end - start) + " ms");
	}

}
