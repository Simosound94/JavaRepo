package sample4;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserializer {

	public static void main(String[] args) {
		try {
			File f = new File("studentList.ser");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			List<Studente> studentList = (List<Studente>)ois.readObject();
			
			for(Studente s : studentList) {
				System.out.println(s.toString());	
			}
			
			
			ois.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
