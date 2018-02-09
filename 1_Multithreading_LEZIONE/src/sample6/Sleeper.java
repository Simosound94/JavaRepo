package sample6;

public class Sleeper implements Runnable {
	private int nLoops;
	
	public Sleeper(int nLoops) {
		this.nLoops = nLoops;
	}
	public void run() {
		for(int i = 0; i < nLoops; i++)  {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Sleeper interrupt while sleeping");
				//break;
			}
			if(Thread.interrupted()) {
				System.out.println("Sleeper interrupt while running");
			}
		}
		System.out.println("Sleeper terminated");
	}
}
