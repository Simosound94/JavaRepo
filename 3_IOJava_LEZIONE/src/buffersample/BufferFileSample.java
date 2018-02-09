package buffersample;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class BufferFileSample {

	public static void main(String[] args) {		
		String fileName = "miofile.txt";
		String message = "Hello File";
		
		try {
			File f = new File(fileName);
			FileOutputStream fos = new FileOutputStream(f);
			BufferedOutputStream buffer = new BufferedOutputStream(fos);
			for(int i = 0; i < 5; i++) {
				buffer.write(message.getBytes());
				buffer.write((byte)'\n');				
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// il buffer viene svuotato...
			buffer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
