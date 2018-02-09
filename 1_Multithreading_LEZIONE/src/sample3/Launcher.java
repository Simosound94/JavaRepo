package sample3;

public class Launcher {
	public static void main(String[] args) {
		Runner r1 = new Runner("#1");
		
		Runner r2 = new Runner("#2");
		
		Thread r1_t = new Thread(r1);
		Thread r2_t = new Thread(r2);
		
		r1_t.start();
		r2_t.start();
		
		
		
	}
}
