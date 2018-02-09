import java.util.LinkedList;

public class MainApp {

	public static void main(String[] args) {

		LinkedList<Immagine> imm = new LinkedList<Immagine>();
		LinkedList<Ostacolo> ost = new LinkedList<Ostacolo>();
		
		Object priorityMUTEX = new Object();
		
		EventBroker eb = new EventBroker(imm, ost);
		
		
		//raccolgono i dati..
		ElaborazioneImmagini elIm = new ElaborazioneImmagini(eb);
		ElaborazioneOstacoli elOs = new ElaborazioneOstacoli(eb);
		
		
		//Elaborano i dati....
		RaccoltaImmagini ri = new RaccoltaImmagini(eb);
		RaccolaOstacoli ro = new RaccolaOstacoli(eb);
		
		Thread t_elIm = new Thread(elIm);
		Thread t_elOs = new Thread(elOs);
		Thread t_ri = new Thread(ri);
		Thread t_ro = new Thread(ro);
		
		t_elIm.start();
		t_elOs.start();
		t_ri.start();
		t_ro.start();
		
		
	}

}
