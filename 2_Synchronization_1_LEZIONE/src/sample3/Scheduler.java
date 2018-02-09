package sample3;

public class Scheduler {

	public static void main(String[] args) {
		
		Counter counter = new Counter();
		Object mutex = new Object();
		
		for(int i = 0; i <100; i++) {
			Task t = new Task("#" + i, counter, mutex);
			Thread t_t = new Thread(t);
			t_t.start();
		}

	}

}
