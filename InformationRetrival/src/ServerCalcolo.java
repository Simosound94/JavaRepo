import java.util.HashMap;
import java.util.LinkedList;


public class ServerCalcolo implements Runnable {
	
	public static LinkedList<Information> codaDiLavorazione = new LinkedList<>();
	
	private HashMap<String, Information> cacheLavorati;
	private HashMap<String, Information> cacheInLavorazione;
	
	
	public ServerCalcolo(HashMap<String, Information> cacheLavorati,HashMap<String, Information> cacheInLavorazione) {
		super();
		this.cacheLavorati = cacheLavorati;
		this.cacheInLavorazione = cacheInLavorazione;
	}

	

	@Override
	public void run() {
		try{
		Information entryToRetrive = null;
			while(true){
				synchronized(codaDiLavorazione){
					//Lavorazione now serve 
					while(codaDiLavorazione.isEmpty()){
						codaDiLavorazione.wait();
					}
					entryToRetrive = codaDiLavorazione.removeFirst();
				}
				System.out.println("Elaborazione richiesta: "+entryToRetrive.key);
				Thread.sleep((int)(Math.random()*2000+6000));
				entryToRetrive.info = String.valueOf(Math.random()*200);
				synchronized(cacheLavorati){
					cacheLavorati.put(entryToRetrive.key, entryToRetrive);
					cacheLavorati.notifyAll();
				}
				synchronized(cacheInLavorazione){
					cacheInLavorazione.remove(entryToRetrive.key);
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
