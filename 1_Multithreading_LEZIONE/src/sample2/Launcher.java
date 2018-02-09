package sample2;

public class Launcher {
	public static void main(String[] args) {
		Runner r1 = new Runner("#1");
		
		Runner r2 = new Runner("#2");
		
		r1.start();
		
		r2.start();
		
	}
}
