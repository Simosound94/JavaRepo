package sample1;

public class Runner {
	private String name;
	
	public Runner(String name) {
		this.name = name;
	}
	
	public void run() {
		while(true) {
			System.out.println("sono il Runner " + this.name);
		}
	}
}
