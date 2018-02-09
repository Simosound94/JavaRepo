import java.util.HashMap;
import java.util.LinkedList;

/*
 * Si occupa di notificare gli altri, una volta che mi è arrivato
 * un cambiamento
 * (Questa soluzione non implementa "l'integrita" -> dati letti in parti diverse
 * possono risultare diversi perchè momentaneamente non aggiornati)
 */
public class HashMapNotifier implements Runnable {

	private LinkedList<UpdateRequest> lastRequests;
	private LinkedList<Streams> toNotify;


	public HashMapNotifier(LinkedList<UpdateRequest> lastRequests, LinkedList<Streams> toNotify) {
		super();
		this.lastRequests = lastRequests;
		this.toNotify = toNotify;
	}


	@Override
	public void run() {
		try{
			while(true){
				synchronized(lastRequests){
					while(lastRequests.isEmpty()){
						lastRequests.wait();
					}
				}
				UpdateRequest up = lastRequests.removeFirst();
				for(Streams s : toNotify){
					s.out.writeObject(up);
				}
			}
		
		}catch(Exception e){e.printStackTrace();}
	}

}
