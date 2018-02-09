package readwrite;

public class MainApp {

	public static void main(String[] args) {
		Resource res = new Resource();
		
		for(int i = 0; i < 10; i++) {
			Writer w = new Writer("Writer #" + i, res);
			Thread w_t = new Thread(w);
			w_t.start();
		}

		for(int i = 0; i < 4; i++) {
			Reader r = new Reader("Reader #" + i, res);
			Thread r_t = new Thread(r);
			r_t.start();
		}

	}

}
