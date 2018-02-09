package joinsample;

public class Scheduler {

	public static void main(String[] args) {
		Sleeper s = new Sleeper(1000);
		Thread t_s = new Thread(s);
		
		System.out.println("Scheduler is starting Sleeper");
		t_s.start();
		
		// wait for Sleeper to terminate
		try {
			t_s.join();
		} catch (InterruptedException e) {
			// 
		}
		
		System.out.println("Scheduler terminated");
	}

}
