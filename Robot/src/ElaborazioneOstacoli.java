
public class ElaborazioneOstacoli implements Runnable {

	private EventBroker b;

	public ElaborazioneOstacoli(EventBroker b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(4000);
				Ostacolo ost = new Ostacolo();
				b.ostacolo(ost);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
