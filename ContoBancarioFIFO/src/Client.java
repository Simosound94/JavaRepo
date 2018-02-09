
public class Client implements Runnable {
	private int id;
	
	FifoRichieste[] f;
	
	
	public Client(int id, FifoRichieste[] f){
		this.id = id;
		this.f = f;
	}

	@Override
	public void run() {
		while(true){
			//Choose account
			int idAccount = (int) (Math.random()*f.length);
			int action = (int)(Math.random()*2);
			double amount = Math.random()*1000;
			Richiesta r = new Richiesta(amount, Richiesta.tipo.values()[action]);
			f[idAccount].add(r);
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
