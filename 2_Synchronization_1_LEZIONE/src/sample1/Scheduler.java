package sample1;

public class Scheduler {

	public static void main(String[] args) {
		
		Object mutex = new Object();
		
		// passo la stessa istanza ai 2 thread
		// per essere usata come mutex...
		
		SynchronizedRunner s1 = new SynchronizedRunner("#1", mutex);
		SynchronizedRunner s2 = new SynchronizedRunner("#2", mutex);
		
		Thread t_s1 = new Thread(s1);
		Thread t_s2 = new Thread(s2);
		
		t_s1.start();
		t_s2.start();

	}

}
