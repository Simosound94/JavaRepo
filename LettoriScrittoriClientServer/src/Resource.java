
public class Resource {
	
	private boolean writing;
	private int numReaders;
	private FileTransf file;
	
	
	public Resource(FileTransf file) {
		this.numReaders = 0;
		this.writing = false;
		this.file = file;
	}
	
	
	
	public synchronized FileTransf lettura(){
		this.acquireReadLock();
		return file;
	}
	
	public synchronized void fineLettura(){
		this.releaseReadLock();
	}
	
	public synchronized FileTransf scrittura(){
		this.acquireWriteLock();
		return file;
	}
	
	public synchronized void fineScrittura(FileTransf f){
		this.file = f;
		this.releaseWriteLock();
	}
	
	public synchronized void fineScrittura(){
		//nel caso in cui la scrittura sia "non ack" non sovrascrivere il file
		this.releaseWriteLock();
	}
	
	
	
	
	
	
	
	
	private synchronized void acquireReadLock(){
		while(this.writing){
			try {
				System.out.println("Blocked: file "+file.idFile+" is being written");
				this.wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Sblocked: file "+file.idFile+" write completed");
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
				System.out.println("Blocked: file "+file.idFile+" is being read");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Sblocked: file "+file.idFile+" read completed");
		this.writing = true;
	}
	
	
	
	private synchronized void releaseWriteLock(){
		this.writing = false;
		this.notifyAll();
		
	}

}
