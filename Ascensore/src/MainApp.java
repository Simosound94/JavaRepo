import java.util.ArrayList;
import java.util.LinkedList;

public class MainApp {

	public static void main(String[] args) {
		
		ArrayList<LinkedList<Persona>> peopleRequests = new ArrayList<LinkedList<Persona>>(Ascensore.NUMPIANI);
		for(int i = 0; i<Ascensore.NUMPIANI; i++){
			peopleRequests.add(new LinkedList<Persona>());
		}
		
		Ascensore a = null;
		for(int i = 0; i<2; i++){
			a = new Ascensore(peopleRequests, i);
			Thread t_a = new Thread(a);
			t_a.start();
		}
		
		
		for(int i = 0; i<5; i++){
			//Basta passare un ascensore a caso
			Persona p = new Persona(a, i);
			Thread t_p = new Thread(p);
			t_p.start();
		}
	}

}
