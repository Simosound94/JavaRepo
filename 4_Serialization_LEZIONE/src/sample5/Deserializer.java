package sample5;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {

	public static void main(String[] args) {
		try {
			File f = new File("studente.ser");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Studente s = (Studente)ois.readObject();
			
			System.out.println(s.toString());
			
			ois.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
