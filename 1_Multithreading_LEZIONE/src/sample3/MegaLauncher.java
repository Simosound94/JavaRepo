package sample3;

public class MegaLauncher {

	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++) {
			Runner r = new Runner("#" + i);
			Thread t_r = new Thread(r);
			t_r.start();
		}

	}

}
