package sample3;

public class Task implements Runnable {
	private String name;
	private Counter counter;
	private Object mutex;
	//
	// ID UNIVOCO
	//
	private int id;
	
	public Task(String name, Counter c, Object m) {
		this.name = name;
		this.counter = c;
		this.mutex = m;
	}
	
	public void run() {
		
		//
		// con il synchronized sul metodo diventa cosi' :
		//
		// this.id = this.counter.getid();
		//
		synchronized(mutex) {
			this.id = this.counter.getId();
		}
		
		// start running, do something with id...
		
		System.out.println(this.name + ";" + this.id);
		
	}
}
