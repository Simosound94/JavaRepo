
public class MainApp {

	public static void main(String[] args) {

		CodaDiStampa coda = new CodaDiStampa();
		for(int i = 0; i<1;i++){
			Stampante s = new Stampante(i);
			Spooler sp = new Spooler(coda, s);
			coda.addPrinter(i);
			Thread t_s = new Thread(s);
			Thread t_sp = new Thread(sp);
			t_s.start();
			t_sp.start();
		}
	
		for(int i= 0; i<1; i++){
			Processo p = new Processo(coda, i);
			Thread t_p = new Thread(p);
			t_p.start();
		}
	
	}

}
