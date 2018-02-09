package readwritenogood;

public class Resource {
	/*
	 *  - Solo un thread puo accedere alla volta, in lettura o in scrittura
	 *  - l'Object look non serve, è come se fosse un monitor, potrei sincronizzarmi su this
	 * 
	 */
	private String value;
	private Object lock;
	
	public Resource() {
		this.lock = new Object();
	}
	
	public void write(String value, String tname) {
		synchronized(this.lock) {
			System.out.println("Thread " + tname + " is writing value");
			this.value = value;
		}
	}
	
	public String read(String tname) {
		synchronized(this.lock) {
			System.out.println("Thread " + tname + " is reading value");
			return this.value;
		}
	}
}
