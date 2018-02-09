import java.util.LinkedList;

public class EventBroker {
	
	private LinkedList<Immagine> immagini;
	private LinkedList<Ostacolo> ostacoli;
	private BooleanPriorityMutex priorityMUTEX;
	
	
	public EventBroker(LinkedList<Immagine> immagini, LinkedList<Ostacolo> ostacoli) {
		super();
		this.immagini = immagini;
		this.ostacoli = ostacoli;
		this.priorityMUTEX = new BooleanPriorityMutex(false);
	}
	

	/*
	 * Mi arriva un ostacolo
	 */
	public void ostacolo(Evento e){
		System.out.println("Ostacolo arrivato");
		synchronized(ostacoli){
			ostacoli.add((Ostacolo) e);
			ostacoli.notify();
		}
		
	}
	
	
	/*
	 * Mi arriva un immagine 
	 */
	public void immagine(Evento e){
		System.out.println("Immagine arrivata");
		synchronized(immagini){
			immagini.add((Immagine) e);
			immagini.notify();
		}
	}
	
	public Ostacolo getOstacolo(){
		Ostacolo ost = null;
		try{
			synchronized(ostacoli){
				while(ostacoli.isEmpty()){
					System.out.println("No ostacoli");
					/*
					 * IL PRIOPRITY MUTEX FATTO COSI' NON FUNZIONA NEL CASO DI PIU' PROCESSI
					 * CHE ELABORANO GLI OSTACOLI
					 * SE UNO STA LAVORANDO, ARRIVA L'ALTRO, TROVA OSTACOLI VUOTO E NOTIFICA
					 * IL PRIORIRY MUTEX
					 *  -> DEVE ESSERE UN COUNTER 
					 *  
					 */
					synchronized(priorityMUTEX){
						System.out.println("No ostacoli notify immagine");
						priorityMUTEX.setValue(false);
						priorityMUTEX.notify();
					}
					ostacoli.wait();
				}
				synchronized(priorityMUTEX){
					priorityMUTEX.setValue(true);;
				}
				ost = ostacoli.removeFirst();
			}
		}catch(Exception e){e.printStackTrace();}
		return ost;
	}
	
	public void fineElaborazioneOstacolo(){
		synchronized(ostacoli){
			if(ostacoli.size() == 0){
				synchronized(priorityMUTEX){
					System.out.println("Notify immagine");
					priorityMUTEX.setValue(false);;
					priorityMUTEX.notify();
				}
			}
		}
	}
	
	
	public Immagine getImmagine(){
		Immagine imm=null;
		try {
				synchronized(immagini){
					while(immagini.isEmpty()){
						System.out.println("No immagine");
						immagini.wait();
					}
					imm = immagini.removeFirst();
				}
				synchronized(priorityMUTEX){
					while(priorityMUTEX.getValue()){
						System.out.println("Wait for priority ostacolo");
						priorityMUTEX.wait();
						System.out.println("Fine prioriry ostacolo");
					}
				}
		
		}catch(Exception e){e.printStackTrace();}
		return imm;
	}
	
}
