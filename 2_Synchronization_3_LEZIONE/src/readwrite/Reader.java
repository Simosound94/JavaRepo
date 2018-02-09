package readwrite;

public class Reader implements Runnable {
	private Resource myResource;
	private String myName;

	public Reader(String myName, Resource myResource) {
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
			// read the resource
			this.myResource.read(this.myName);
		}
	}
}
