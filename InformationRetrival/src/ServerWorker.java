import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;


public class ServerWorker implements Runnable {
	
	public ServerWorker(HashMap<String, Information> cacheLavorati, HashMap<String, Information> cacheInLavorazione, Socket s) {
		super();
		this.cacheLavorati = cacheLavorati;
		this.cacheInLavorazione = cacheInLavorazione;
		this.s = s;
	}

	private HashMap<String, Information> cacheLavorati;
	private HashMap<String, Information> cacheInLavorazione;
	private Socket s;
	
	
	
	private Information ricercaLavorati(String chiave){
		Information ris;
		synchronized(cacheLavorati){
			ris = cacheLavorati.get(chiave);
		}
		return ris;
	}
	
	private Information ricercaInLavorazione(String chiave) throws Exception{
		Information ris;
		synchronized(cacheInLavorazione){
			ris = cacheInLavorazione.get(chiave);
		}
		if(ris!=null){
			System.out.println("Retrived info in state workInProgress for: "+chiave);
			//La chiave è il lavorazione
			synchronized(cacheLavorati){
				while(!cacheLavorati.containsKey(chiave)){
					cacheLavorati.wait();
					//Metodo conservativo, avrei potuto dormire anche sul pacchetto
				}
				ris = cacheLavorati.get(chiave);
			}
		}
		return ris;
		
	}

	
	
	@Override
	public void run() {
		try{
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		Request r = (Request) ois.readObject();
		//Chiave di ricerca gia lavorata
		Information ris = ricercaLavorati(r.chiaveRicerca);
		if(ris !=null){
			System.out.println("Retrived info in cache for: "+r.chiaveRicerca);
			oos.writeObject(ris);
			return;
		}
		
		//Chiave di ricerca tra quelle in lavorazione
		ris = ricercaInLavorazione(r.chiaveRicerca);
		if(ris !=null){
			oos.writeObject(ris);
			return;
		}
		
		//Nuova chiave di ricerca
		System.out.println("Istantiated new request for: "+r.chiaveRicerca);
		Information inf = new Information();
		inf.key = r.chiaveRicerca;
		synchronized(cacheInLavorazione){
			cacheInLavorazione.put(r.chiaveRicerca, inf);
		}
		synchronized(ServerCalcolo.codaDiLavorazione){			
			ServerCalcolo.codaDiLavorazione.add(inf);
			ServerCalcolo.codaDiLavorazione.notifyAll();
		}
		ris = ricercaInLavorazione(r.chiaveRicerca);
		System.out.println("Sending response for: "+r.chiaveRicerca);
		oos.writeObject(ris);
		
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
