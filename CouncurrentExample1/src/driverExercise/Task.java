package driverExercise;

public class Task implements Runnable{

	public int name;
	public Driver d;
	
	public Task(int name, Driver d){
		this.name = name;
		this.d = d;
	}
	
	@Override
	public void run() {
		synchronized(this){ //Serve solo per prendere il lock per il wait
		while(true){
			int sleepTime = (int)(Math.random()*2100);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
			int randomInterrupt = (int)(Math.random()*15);
			d.register(randomInterrupt, this);
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				
			}
			}
		}
	}
	
	public synchronized void callBack(){
		this.notify();
	}

}
