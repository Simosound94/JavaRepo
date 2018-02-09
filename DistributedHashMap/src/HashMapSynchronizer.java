import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * Un Hash map synchronizer per ogni possibile connessione verso questo nodo
 * un HashMapSynchronized si occupa di ascoltare un flusso ed aggiornare la dhm 
 * nel caso di modifiche
 */
public class HashMapSynchronizer implements Runnable {
	
	private HashMap<String, Data> dhm;
	private Streams toSynchronize;
	private LinkedList<UpdateRequest> lastRequests;

	

	
	@Override
	public void run() {
		while(true){
			try {
				UpdateRequest ur = (UpdateRequest) toSynchronize.in.readObject();

				synchronized(dhm){
					//Se le operazioni gia son state fatte, non far nulla
					if(ur.type == UpdateRequest.RequestTypes.delete && !dhm.containsKey(ur.key)){
						System.out.println("fine");
						continue;
					}
					if(ur.type == UpdateRequest.RequestTypes.put && dhm.containsKey(ur.key)){
						Data d = dhm.get(ur.key);
						if(d.equals(ur.data)){
							System.out.println("fine");
							continue;
						}
					}
					switch(ur.type){
						case delete:{
							Data d = dhm.remove(ur.key);
							System.out.println("Removed "+d);
							break;
						}
						case put:{
							dhm.put(ur.key, ur.data);
							System.out.println("added "+ur.data);

							break;
						}
					}
				}
				synchronized(lastRequests){
					lastRequests.add(ur);
					if(lastRequests.size() -1 == 0){
						lastRequests.notify();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}




	public HashMapSynchronizer(HashMap<String, Data> dhm, Streams toSynchronize,
			LinkedList<UpdateRequest> lastRequests) {
		super();
		this.dhm = dhm;
		this.toSynchronize = toSynchronize;
		this.lastRequests = lastRequests;
	}

}
