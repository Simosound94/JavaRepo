package sample2;

public class Runner extends Thread {
	
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
