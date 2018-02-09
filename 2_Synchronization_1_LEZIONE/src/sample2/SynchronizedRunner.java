package sample2;

public class SynchronizedRunner implements Runnable {
	private String name;
	private Timer timer;

	public SynchronizedRunner(String name, Timer timer) {
		this.name = name;
		this.timer = timer;
	}
	public void run() {

		while(true) {
			
			System.out.println("Runner " + name + " entering protected block");

			this.timer.timeout(10000);

			System.out.println("Runner " + name + " exited protected block");
		}
	}
}
