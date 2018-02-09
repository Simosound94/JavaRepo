package my.readwrite;

public class Resource {
	private String value;	
	private int readers;
	private Object writersLock;
	private Object readersLock;

	public Resource() {
		this.writersLock = new Object();
		this.readersLock = new Object();
		this.readers = 0;
	}

	public void write(String value, String tname) {
		/*
		 * Direi che il deadlock è impossibile in quanto non può esserci una richiesta ciclica
		 * al più tutti gli scrittori prima richiedono writers e poi readers Lock
		 * in nessun caso avviene il contrario
		 */
		System.out.println("Writer " + tname + " tries to write");
		synchronized(this.writersLock) {
			synchronized(this.readersLock){
				try {
					
					while(readers  > 0){
						this.readersLock.wait();
					}
					System.out.println("Writer " + tname + " is writing the value");
					Thread.sleep(4000);
					System.out.println("Writer " + tname + " finishes to write");
				} catch (InterruptedException e) {e.printStackTrace();}
				this.value = value;
			}
		}
	}	


	public String read(String tname) {
		String val = null;
		/*
		 * Non può succedere che uno scrittore entri qui dentro se
		 * la risorsa è in scrittura
		 */
		System.out.println("Reader " + tname + " tries to read");
		synchronized(this.readersLock) {
			this.readers++;
		}
		System.out.println("Reader " + tname + " is reading the value");
		val = this.value;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Reader " + tname + " finishes to read");

		synchronized(this.readersLock){
			this.readers--;
			if(this.readers == 0){
				this.readersLock.notify();
				
			}
		}
		return val;
	}
}
