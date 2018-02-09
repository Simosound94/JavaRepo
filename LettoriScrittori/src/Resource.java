
public class Resource<T extends ResourceInterface<T>> {
	private Object lockX, lockS;
	private T resource;
	private int readers, wreaders;
	boolean writing;
	
	public Resource(T res){
		readers = 0;
		wreaders = 0;
		lockX = new Object();
		lockS = new Object();
		this.resource = res;
		writing = false;
	}
	
	public T startRead() throws InterruptedException{
		synchronized(lockS){
			wreaders++;
			while(writing)
				lockS.wait();
			wreaders--;
			readers++;
		}
		//Sarebbe meglio una copia
		return (T) resource.clone();
	}
	
	
	public void finishRead(){
		/*
		 * ------------------------ERRORE:
		 * 
		 * qui c'è una corsa. 
		 * readers è una variabile dell'oggetto Resource
		 * condivisa tra piu thread
		 * DEVO proteggerla. se non la proteggo potrebbe succedere di tutto
		 * es. decrementarla una volta invece che due ecc..
		 * 
		 */
		readers--;
		if(readers == 0)
			synchronized(lockX){
				lockX.notify();
			}
	}
	
	
	public void write(T newValue, String name) throws InterruptedException{
		synchronized(lockX){
			/*
			 * La mutua esclusione tra scrittori è effettuata con synchronized, perciò poi
			 * non ce ne dobbiamo più occupare, in questo blocco di codice entra
			 * solo uno scrittore per volta
			 */
			writing = true;
			while(readers > 0)
				lockX.wait();
			System.out.println("Thread " + name + " writes");
			Thread.sleep((long) (Math.random()*3000));
			//resource.writeValue(newValue);
			// Writing.....
			writing = false;
			if(wreaders > 0)
				synchronized(lockS){
					// 			/!\
					// Devo notificare solo i reader, perchè mi blocco in wait solo se ci sono
					// reader in attesa, poi questi eseguiranno e sbloccheranno i lettori a lettura finita
					lockS.notifyAll();
				}
		}
	}
}
