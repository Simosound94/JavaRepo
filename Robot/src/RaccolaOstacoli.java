import java.util.Random;

public class RaccolaOstacoli implements Runnable {
	private EventBroker eb;

	


	public RaccolaOstacoli(EventBroker eb) {
		this.eb = eb;
	}


	@Override
	public void run() {
		try {
			while(true){
				eb.getOstacolo();
				//... elaborazione
				System.out.println("Elaborazione ostacolo");
				Random r = new Random();
				double time = Math.abs(r.nextGaussian()*0.2+1);
				Thread.sleep((int)(time*2000));
				eb.fineElaborazioneOstacolo();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
