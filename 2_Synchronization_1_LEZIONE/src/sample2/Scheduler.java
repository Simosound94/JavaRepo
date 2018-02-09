package sample2;

public class Scheduler {

	public static void main(String[] args) {
		
		Timer timer = new Timer();
		
		// passo la stessa istanza ai 2 thread
		// per essere usata come mutex...
		
		SynchronizedRunner s1 = new SynchronizedRunner("#1", timer);
		SynchronizedRunner s2 = new SynchronizedRunner("#2", timer);
		
		Thread t_s1 = new Thread(s1);
		Thread t_s2 = new Thread(s2);
		
		t_s1.start();
		t_s2.start();

	}

}
