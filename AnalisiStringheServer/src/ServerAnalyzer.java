import java.util.LinkedList;

public class ServerAnalyzer implements Runnable {
	
	private LinkedList<Pacchetto> toAnalyze;
	private LinkedList<Pacchetto> analyzed;

	public ServerAnalyzer(LinkedList<Pacchetto> toAnalyze, LinkedList<Pacchetto> analyzed) {
		super();
		this.toAnalyze = toAnalyze;
		this.analyzed = analyzed;
	}

	@Override
	public void run() {
		Pacchetto p;
		try{
			while(true){
				synchronized(toAnalyze){
					while(toAnalyze.isEmpty()){
						toAnalyze.wait();
					}
					p = toAnalyze.removeFirst();
				}
				/*
				 * Effettua analisi...
				 */
				p.charDiv = (int)(Math.random()*1000+10);
				p.maxOccChar = (int)(Math.random()*100);
				Thread.sleep(10000);
				// Ogni volta che produco, sveglio tutti i processi serverWorker 
				// che si sono addormentati in attesa di un qualche risultato
				System.out.println("Pacchetto "+p+" elaborato");
				synchronized(analyzed){
					analyzed.add(p);
					analyzed.notifyAll();
				}

			}
		}catch(Exception e){e.printStackTrace();}
	}

}
