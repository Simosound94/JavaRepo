package sample4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Serializer {

	public static void main(String[] args) {
		try {
			File f = new File("studentList.ser");
			
			// stream di byte verso il file
			FileOutputStream fos = new FileOutputStream(f);
			
			// per scrivere l'oggetto java
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			List<Studente> studentList = new ArrayList<Studente>();
			
			Studente s = new Studente();
			s.setMatricola("012345");
			s.setNome("Andrea");
			s.setCognome("Parodi");
			s.setDataIscrizione(new Date());
			s.setAnnoFrequenza(5);
			
			studentList.add(s);
			
			s = new Studente();
			s.setMatricola("543210");
			s.setNome("Gianni");
			s.setCognome("Bianchi");
			s.setDataIscrizione(new Date());
			s.setAnnoFrequenza(4);
			
			studentList.add(s);
			
			

			// serializzo s			
			oos.writeObject(studentList);
			
			oos.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
