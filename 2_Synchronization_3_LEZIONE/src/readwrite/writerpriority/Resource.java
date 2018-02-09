package readwrite.writerpriority;

public class Resource {
	private String value;	
	private boolean writing;
	private int numReaders;
	private int numWritersWaiting;
	

	public Resource() {
		this.numReaders = 0;
		this.writing = false;
		this.numWritersWaiting = 0;
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
			while(this.writing || this.numWritersWaiting != 0){
				try {
					System.out.println("Read request wait");
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
		System.out.println("Write request");

		this.numWritersWaiting++;
		while(this.writing || this.numReaders > 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.writing = true;
		this.numWritersWaiting--;
	}
	
	
	private synchronized void releaseWriteLock(){
		this.writing = false;
		this.notifyAll();
		
	}
}
