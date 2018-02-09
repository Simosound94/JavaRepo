package pipesample;

import java.io.PipedOutputStream;

public class WriterThread implements Runnable {
	private PipedOutputStream oStream;
	
	public WriterThread(PipedOutputStream oStream) {
		this.oStream = oStream;
	}
	
	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
				
				String messaggio = "Messaggio #" + i;
				this.oStream.write(messaggio.getBytes());
				this.oStream.write((byte)'\n');
				
				Thread.sleep(1000);
			}
			
			this.oStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
