/**
 * 	APPLICAZIONE PER LA GESTIONE DI CONTI BANCARI
 *  Un conto bancario è formato da:
 *  Conto bancario
 *  FifoRichieste:  coda fifo di richieste per quel conto
 *  RichiesteProcesser: thread che si occupa di elaborare le richieste per quel conto
 *  
 *  Un client manderà richieste a random sui conti
 * 
 * @author simone
 * 
 *
 */
public class MainApp {
	
	public static final int ACCOUNTS = 100;
	public static final int CLIENTS = 30;


	public static void main(String[] args) {

		
		
		FifoRichieste[] f = new FifoRichieste[ACCOUNTS];
		for(int i= 0; i<ACCOUNTS;i++){
			ContoBancario cb = new ContoBancario(i);
			f[i] =  new FifoRichieste(i);
			RichiesteProcesser rp = new RichiesteProcesser(f[i],cb);
			Thread rp_t = new Thread(rp);
			rp_t.start();
		}
		
		for(int i = 0; i<CLIENTS;i++){
			Client c = new Client(i, f);
			Thread c_t = new Thread(c);
			c_t.start();
		}
		
		
	}

}
