package sample6;

public class Launcher {

	public static void main(String[] args) {
		Sleeper s = new Sleeper(10);
		Thread t_s = new Thread(s);
		t_s.start();
		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			
		}
		
		t_s.interrupt();
	}

}
