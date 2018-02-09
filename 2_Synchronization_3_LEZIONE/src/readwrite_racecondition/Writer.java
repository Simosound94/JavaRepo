package readwrite_racecondition;

public class Writer implements Runnable {
	private Resource myResource;
	private String myName;
	
	public Writer(String myName, Resource myResource) {
		this.myName = myName;
		this.myResource = myResource;
	}
	
	public void run() {
		while(true) {
			// sleep for a while
			long sleepMs = (long)(Math.random() * 10000);
			try {
				Thread.sleep(sleepMs);
			} catch(Exception e) {
				
			}
			// write random value to the resource
			long value = (long)(Math.random() * 1000000);
			this.myResource.write(""+value, this.myName);
		}
	}
}
