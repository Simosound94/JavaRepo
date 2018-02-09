package readwritenogood;

public class Reader implements Runnable {
	private Resource myResource;
	private String myName;
	
	public Reader(String myName, Resource myResource) {
		this.myName = myName;
		this.myResource = myResource;
	}
	
	public void run() {
		while(true) {
			String s = this.myResource.read(this.myName);
		}
	}
}
