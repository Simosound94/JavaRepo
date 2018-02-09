import java.util.LinkedList;

public class FifoRichieste {
	
	LinkedList<Richiesta> richieste;
	private int id;

	public FifoRichieste(int id) {
		super();
		this.richieste = new LinkedList<Richiesta>();
		this.id = id;
	}

	
	public synchronized void add(Richiesta r){
		System.out.println("Try to add request Account: "+id+" of: "+r.getAmount());
		richieste.add(r);
		//Se era vuoto notifica...
		if(richieste.size() == 1 ){
			this.notify();
		}
	}
	
	public synchronized Richiesta remove(){
		return richieste.removeFirst();
	}
	
	public synchronized boolean isEmpty(){
		return richieste.isEmpty();
	}
	

}
