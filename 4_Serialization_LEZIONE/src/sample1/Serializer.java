package sample1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Serializer {

	public static void main(String[] args) {
		try {
			// L'estensione è a caso /!\
			File f = new File("studente.ser");
			
			// stream di byte verso il file
			FileOutputStream fos = new FileOutputStream(f);
			
			// per scrivere l'oggetto java
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			Studente s = new Studente();
			s.setMatricola("012345");
			s.setNome("Andrea");
			s.setCognome("Parodi");
			s.setDataIscrizione(new Date());
			s.setAnnoFrequenza(5);

			// serializzo s			
			oos.writeObject(s);
			
			oos.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
