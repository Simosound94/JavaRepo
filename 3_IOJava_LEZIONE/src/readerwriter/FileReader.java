package readerwriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileReader {

	public static void main(String[] args) {
		try {
			File f = new File("miofile.txt");
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader reader = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(reader);
			
			String line = buffer.readLine();
			while(line != null) {
				System.out.println(line);
				line = buffer.readLine();
			}
			buffer.close();
		} catch (Exception e) {

		}

	}

}
