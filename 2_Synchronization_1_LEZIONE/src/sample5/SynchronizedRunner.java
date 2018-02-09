package sample5;

public class SynchronizedRunner implements Runnable {
	private String name;

	public SynchronizedRunner(String name) {
		this.name = name;
	}
	public void run() {

		while(true) {
			
			System.out.println("Runner " + name + " entering protected block");
			
			Timer.timeout(10000);
			
			//this.timer.timeout(10000);

			System.out.println("Runner " + name + " exited protected block");
		}
	}
}
