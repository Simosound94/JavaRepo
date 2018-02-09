package iosample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SimpleFileReader {

	public static void main(String[] args) {
		try {
			File f = new File("miofile.txt");
			FileInputStream fis = new FileInputStream(f);
			int c = fis.read();
			while(c != -1) {
				// DEVO FARE LA CONVERSIONE, SE NO SONO BYTE
				System.out.print((char)c);
				c = fis.read();
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
