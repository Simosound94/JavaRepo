package sample5;

public class Scheduler {

	public static void main(String[] args) {
		
		
		SynchronizedRunner s1 = new SynchronizedRunner("#1");
		SynchronizedRunner s2 = new SynchronizedRunner("#2");
		
		Thread t_s1 = new Thread(s1);
		Thread t_s2 = new Thread(s2);
		
		t_s1.start();
		t_s2.start();

	}

}
