// Definizione di una classe per misurare il tempo impiegato
// da porzioni di codice. Utilizzo: 
// Timer t = new Timer();
// t.start();
// <porzione di codice da monitorare>
// t.stop();
public class Timer {

	// Costanti private per la conversione da nanosecondi
	private final long NANO2MICRO = 1000;
	private final long NANO2MILLI = 1000000;
	
	private boolean started; // true se il conteggio è avviato
	private long    epoch;   // numero di tick all'avvio del conteggio
	private long    elapsed; // numero di tick trascorsi (nanosecondi)
	
	public Timer() {
		started = false;
		epoch = elapsed = 0;
	}
	
	public void start() {
		// La misurazione parte solo se non è già stata avviata
		if (!started) {
			started = true;
			epoch = System.nanoTime();
		}
	}
	
	public void stop() {
		// La misurazione si ferma solo se è stata avviata in  precedenza
		if (started) {
			started = false;
			elapsed = System.nanoTime() - epoch;
		}
	}

	public void pause() {
		// Non ferma la misurazione, ma registra il tempo trascorso
		// In questo modo si possono prendere tempi intermedi
		elapsed = System.nanoTime() - epoch;
	}
	
	// Tempo trascorso in nanosecondi
	public long getElapsedNanoSeconds() {
		return elapsed;
	}
	
	// Tempo trascorso in microsecondi (troncato a 0 decimali)
	public long getElapsedMicroSeconds() {
		return elapsed / NANO2MICRO;
	}
	
	// Tempo trascorso in millisecondi (troncato a 0 decimali)
	long getElapsedMilliSeconds() {
		return elapsed / NANO2MILLI;
	}
}
