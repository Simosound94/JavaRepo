package prodcons;

import java.util.ArrayList;
import java.util.List;

public class BoundedBuffer {
	private List<String> buffer;
	private int max;

	public BoundedBuffer(int max) {
		this.buffer = new ArrayList<String>();
		this.max = max;
	}

	public synchronized void put(String s, String tname) {
		// un solo thread per volta entra qui dentro!
		while(this.buffer.size() == this.max) {
			try {
				// buffer pieno. non posso scrivere...
				// attende e rilascia il lock
				System.out.println("Buffer is full. Thread " + tname + " must wait...");
				this.wait();
			} catch(InterruptedException e) {

			}
		}
		buffer.add(s);
		// risveglio TUTTI i thread in attesa
		// sull'oggetto this. 
		/*
		 * NON posso risvegliarne uno solo perch LE CONDIZIONI DI ATTESA SONO DIVERSE
		 * rischierei di svegliare un altro scrittore che si ribloccherebbe per il buffer pieno
		 */
		this.notifyAll();
	}

	public synchronized String get(String tname) {
		String s = null;
		while(this.buffer.size() == 0) {
			try {
				System.out.println("Buffer is empty. Thread " + tname + " must wait...");
				this.wait();
			} catch (InterruptedException e) {
				
			}
		}
		s = this.buffer.remove(0);
		this.notifyAll();
		
		return s;
	}
}
