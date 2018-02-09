package pipesample;

import java.io.PipedInputStream;

public class ReaderThread implements Runnable {
	private PipedInputStream iStream;
	
	public ReaderThread(PipedInputStream iStream) {
		this.iStream = iStream;
	}
	
	public void run() {
		try {
			int c = iStream.read();			
			while(c != -1) {
				System.out.print((char)c);
				c = iStream.read();
			}			
			this.iStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
