package readwrite;

public class Resource {
	private String value;	
	private boolean writing;
	private int numReaders;
	

	public Resource() {
		this.numReaders = 0;
		this.writing = false;
	}

	public void write(String value, String tname) {
		acquireWriteLock();
		System.out.println("Thread "+tname+ " risorsa in scrittura...");
		this.value = value;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		releaseWriteLock();

		
	}	


	public String read(String tname) {
		String val = null;
		acquireReadLock();
		System.out.println("Thread "+tname+ " risorsa in lettura...");
		val = this.value;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		releaseReadLock();
		return val;
	}
	
	
	private synchronized void acquireReadLock(){
			while(this.writing){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.numReaders++;
		
	}
	
	private synchronized void releaseReadLock(){
		this.numReaders--;
		if(this.numReaders == 0){
			this.notifyAll();
		}
		
	}
	
	private synchronized void acquireWriteLock(){
		while(this.writing || this.numReaders > 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.writing = true;
	}
	
	
	private synchronized void releaseWriteLock(){
		this.writing = false;
		this.notifyAll();
		
	}
}
