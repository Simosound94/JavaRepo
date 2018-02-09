package sample1;

public class MainApp {

	public static void main(String[] args) {
		Object o = new Object();
		
		Waiter w = new Waiter(o);
		Thread w_t = new Thread(w);
		w_t.start();
		
		Notifier n = new Notifier(o);
		Thread n_t = new Thread(n);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		n_t.start();

	}

}
