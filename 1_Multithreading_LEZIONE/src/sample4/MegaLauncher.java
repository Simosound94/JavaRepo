package sample4;

public class MegaLauncher {

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			Sleeper r = new Sleeper("#" + i);
			Thread t_r = new Thread(r);
			t_r.start();
			t_r.interrupt();
		}

	}

}
