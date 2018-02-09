package sample5;

public class Launcher {

	public static void main(String[] args) {
		Runner r = new Runner();
		Thread t_r = new Thread(r);
		t_r.start();
		t_r.interrupt();
	}

}
