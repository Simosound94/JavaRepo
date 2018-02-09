package readwrite_racecondition;

public class Resource {
	// questo e' il valore che viene letto e scritto
	private String value;	
	//
	// usiamo una contatore 
	// per tenere traccia del numero dei lettori attivi
	//
	private int numReaders;
	//
	// questa variabile segnala che uno scrittore sta scrivendo
	//
	private boolean writing;

	// oggetto di lock per l'attesa degli scrittori.
	private Object writersLock;
	// oggetto di lock per l'attesa dei lettori
	private Object readersLock;

	public Resource() {
		this.writersLock = new Object();
		this.readersLock = new Object();
		this.numReaders = 0;
		this.writing = false;
	}

	public void write(String value, String tname) {
		synchronized(this.writersLock) {
			// un solo scrittore per volta entra qui dentro !
			// inizio scrittura
			this.writing = true;

			while(this.numReaders > 0) {
				// controllo che non ci siano lettori attivi
				// altrimenti, attendo
				try {
					this.writersLock.wait();
				} catch (Exception e) {e.printStackTrace();}
			}
			
			// ok ora posso scrivere...
			// questa scrittura e' protetta, siamo sempre 
			// dentro il blocco synchronized su writersLock
			System.out.println("Writer " + tname + " is writing the value");
			this.value = value;
			
			// segnalo ai lettori il termine delle operazioni di scrittura.
			this.writing = false;
			// notifico i lettori che il thread scrittore
			// ha terminato di scrivere e possono leggere
			// per farlo devo acquisire il lock su readersLock
			synchronized(this.readersLock) {
				this.readersLock.notifyAll();
			}
		}
	}	


	public String read(String tname) {
		String val = null;
		synchronized(this.readersLock) {
			// un solo lettore entra qui dentro !
			while(this.writing) {
				try {
					// operazioni di scrittura in corso...
					// attesa per lettori
					this.readersLock.wait();
				} catch (Exception e) { e.printStackTrace();}
			}
			
			
			// variabile numreaders protetta in scrittura da readersLock 
			this.numReaders++;
		} // fine blocco sincronizzato

		// posso leggere...
		// nota: questa sezione non e' sincronizzata. 
		// molti lettori possono passare qui contemporaneamente
		System.out.println("Reader " + tname + " is reading the value");
		val = this.value;

		synchronized(this.readersLock) {
			// segnalo termine operazione di lettura
			// c'e' un lettore in meno...
			// nota: variabile numreaders protetta in scrittura da readersLock 
			// (1 solo thread lettore per volta...)
			this.numReaders--;
			// notifico i thread scrittori in attesa
			// se sono l'ultimo scrittore
			if(this.numReaders == 0) {
				// devo acquisire il lock su writersLock
				synchronized(this.writersLock) {
					this.writersLock.notifyAll();
				}
			}
		}

		return val;
	}
}
