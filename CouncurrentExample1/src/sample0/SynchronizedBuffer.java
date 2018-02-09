package sample0;

public class SynchronizedBuffer implements Buffer {
	public synchronized void add(String s) {
		
	};
	public synchronized String retrieve() { 
		return null; 
	};
	
	public String retrieve1() {
		synchronized(this) {
			//..
		}
		return null;
	}
}
