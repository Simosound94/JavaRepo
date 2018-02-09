package iosample;

import java.io.File;
import java.io.FileOutputStream;

public class SimpleFileWriter {

	public static void main(String[] args) {
		String fileName = "miofile.txt";
		String message = "Hello File";
		
		try {
			File f = new File(fileName);
			FileOutputStream fos = new FileOutputStream(f);
			for(int i = 0; i < 100; i++) {
				//fos.write((byte)'l');
				// scrivo byte di una stringa
				fos.write(message.getBytes());
				fos.write((byte)'\n');
				
			}
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
