import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class CodaDiStampa {
	
	private HashMap<Integer, PriorityQueue<PrintRequest>> coda;
	
	public CodaDiStampa(){
		this.coda = new HashMap<Integer, PriorityQueue<PrintRequest>>();
	}
	
	
	public synchronized void addPrinter(int number){
		this.coda.put(number, new PriorityQueue<PrintRequest>());
		System.out.println("added printer #"+number);
	}
	
	
	public synchronized void addRequest(PrintRequest pr){
		System.out.println("Add Request: [ "+pr+" ]");
		try{
			boolean ok = false;
			do{
				Iterator<Entry<Integer, PriorityQueue<PrintRequest>>> it = coda.entrySet().iterator();
				while(it.hasNext()){
					PriorityQueue<PrintRequest> codaStampante = it.next().getValue();
					if(codaStampante.size() < 3){
						System.out.println("Found free queue");
						codaStampante.add(pr);
						synchronized(codaStampante){
							codaStampante.notify();
						}
						ok = true;
						break;
					}
				}
				if(!ok){
					System.out.println("Not found free queue");
					this.wait();
				}
				
			}while(!ok);
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	
	
	public PrintRequest getRequest(int numStampante){
		PrintRequest ris = null;
		try{
			PriorityQueue<PrintRequest> codaStampante = null;
			synchronized(this){
				codaStampante = coda.get(numStampante);
			}
			synchronized(codaStampante){
				while(codaStampante.isEmpty()){
					codaStampante.wait();
				}
				ris = codaStampante.remove();
			}
			synchronized(this){
				this.notify();		
			}
		}catch(Exception e){e.printStackTrace();}
		return ris;
	}

	
}
