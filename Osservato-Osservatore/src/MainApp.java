import java.util.LinkedList;

public class MainApp {

	public static void main(String[] args) {
		OsservatoThread o = new OsservatoThread(1);
		Thread t_o = new Thread(o);
		t_o.start();
		
		LinkedList<Osservato> osservati = new LinkedList<Osservato>();
		osservati.add(o);
		
		GeneratoreEventi e1 = new GeneratoreEventi(osservati, 1);
		GeneratoreEventi e2 = new GeneratoreEventi(osservati, 2);
		Thread t_e1 = new Thread(e1);
		Thread t_e2 = new Thread(e2);
		t_e1.start();
		t_e2.start();
		//Osservatore buggato
		OsservatoreThreadBug ossBug = new OsservatoreThreadBug(111, o);
		Thread t_ossBug = new Thread(ossBug);
		t_ossBug.start();
		//Genero 10 osservatori in circa 30 secondi
		for(int i = 0; i<10; i++){
			OsservatoreThread oss = new OsservatoreThread(i, o);
			Thread t_oss = new Thread(oss);
			t_oss.start();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		
	}

}
