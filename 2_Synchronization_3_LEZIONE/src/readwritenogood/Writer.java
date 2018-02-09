package readwritenogood;

public class Writer implements Runnable {
	private Resource myResource;
	private String myName;
	
	public Writer(String myName, Resource myResource) {
		this.myName = myName;
		this.myResource = myResource;
	}
	
	public void run() {
		while(true) {
			String value = "" + (long)(Math.random() * 100000);		
			this.myResource.write(value, this.myName);
		}
	}
}
