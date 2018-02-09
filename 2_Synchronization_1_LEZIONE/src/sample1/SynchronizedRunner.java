package sample1;

public class SynchronizedRunner implements Runnable {
	private String name;
	private Object mutex;

	public SynchronizedRunner(String name, Object mutex) {
		this.name = name;
		this.mutex = mutex;
	}
	public void run() {

		while(true) {
			
			System.out.println("Runner " + name + " entering protected block");

			synchronized(mutex) {
				// questo e' un blocco di codice protetto
				// 1 solo thread per volta qui dentro
				// i thread rispettano la mutua esclusivita'
				// solo se sono synchronizzati sullo stesso *oggetto* (mutex)
				System.out.println("Runner " + name + " entered protected block, sleeping");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {

				}
				System.out.println("Runner " + name + " exited protected block");
			}
		}
	}
}
