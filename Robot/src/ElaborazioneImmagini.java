
public class ElaborazioneImmagini implements Runnable {
	
	private EventBroker b;

	public ElaborazioneImmagini(EventBroker b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
				Immagine imm = new Immagine();
				//Invio immagine a event broker
				b.immagine(imm);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
