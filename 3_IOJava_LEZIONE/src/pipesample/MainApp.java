package pipesample;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class MainApp {

	public static void main(String[] args) {
		try {
			PipedInputStream iStream = new PipedInputStream();
			PipedOutputStream oStream = new PipedOutputStream(iStream);
			
			ReaderThread r = new ReaderThread(iStream);
			WriterThread w = new WriterThread(oStream);
			
			Thread r_t = new Thread(r);
			Thread w_t = new Thread(w);
			
			r_t.start();
			w_t.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
