package readerwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileWriter {

	public static void main(String[] args) {
		try {
			File f = new File("miofile.txt");
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter pw = new PrintWriter(fos);
			
			pw.println("Hello !");
			pw.println("Hello Again !");
			
			pw.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
