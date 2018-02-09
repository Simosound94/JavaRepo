package sample0;

public class SynchronizedBuffer implements Buffer {
	public synchronized void add(String s) {
		
	};
	public synchronized String retrieve() { 
		return null; 
	};
	
	public String retrieve1() {
		//E' lo stesso di sopra, il significato è identico
		synchronized(this) {
			//..
		}
		return null;
	}
}
