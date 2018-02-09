package filesample;

import java.io.File;

public class FileSample {

	public static void main(String[] args) {
		File f = new File("miofile.txt");
		
		// il file esiste ?		
		System.out.println("Il file esiste ? : " + f.exists());
		
		try {
			f.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// il file esiste ?		
		System.out.println("Il file esiste ? : " + f.exists());
		
		File dir = new File(".");
		
		try {
			// elenco dei file nella directory
			File[] files = dir.listFiles();
			for(int i = 0; i < files.length; i++) {
				System.out.println((files[i].isDirectory() ? "Dir " : "File ") + files[i].getAbsolutePath());
				
			}			
		} catch (Exception e) {
			
		}
		
		f.delete();

	}

}
