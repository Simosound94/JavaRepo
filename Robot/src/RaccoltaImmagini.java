import java.util.LinkedList;
import java.util.Random;

public class RaccoltaImmagini implements Runnable {
	
	private EventBroker eb;


	public RaccoltaImmagini(EventBroker eb) {
		this.eb = eb;
	}


	@Override
	public void run() {
		try{
				while(true){
				eb.getImmagine();
				//... elaborazione
				System.out.println("Elaborazione Immagine");
				Random r = new Random();
				double time = Math.abs(r.nextGaussian()*0.7+2);
				Thread.sleep((int)(time*2000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
