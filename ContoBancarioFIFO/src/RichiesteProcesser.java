
public class RichiesteProcesser implements Runnable {

	FifoRichieste rich;
	ContoBancario cb;
	
	
	public RichiesteProcesser(FifoRichieste rich, ContoBancario cb) {
		super();
		this.rich = rich;
		this.cb = cb;
	}



	@Override
	public void run() {
		Richiesta toProcess = null;
		while(true){
			synchronized(rich){
				while(rich.isEmpty()){
					try {
						rich.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				toProcess = rich.remove();
			}
			if(toProcess.getTipoRichiesta() == Richiesta.tipo.Deposito){
				cb.deposita(toProcess.getAmount());
			}
			else{
				cb.preleva(toProcess.getAmount());
			}
			
		}	
	}

}
