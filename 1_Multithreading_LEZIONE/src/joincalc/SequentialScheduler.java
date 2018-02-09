package joincalc;

public class SequentialScheduler {

	public static void main(String[] args) {
		Calculator c1 = new Calculator(100000000);
		Calculator c2 = new Calculator(200000000);
		Calculator c3 = new Calculator(300000000);
		
		long start = System.currentTimeMillis();
		
		c1.run();
		c2.run();
		c3.run();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time elapsed = " + (end - start) + " ms");
	}

}
