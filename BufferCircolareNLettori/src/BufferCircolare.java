
public class BufferCircolare{
	
	private int maxLettori;
	private int nLettoFirstItem;
	private String[] buffer;
	private int first;
	private int numItems;
	private int maxItems;
	
	public BufferCircolare(int maxItems, int maxLettori){
		buffer = new String[maxItems];
		first = -1;
		//All'inizio tutti hanno letto l'item "non esistente"
		nLettoFirstItem = maxLettori;
		this.maxLettori = maxLettori;
		this.maxItems = maxItems;
		numItems = 0;
	}
	
	public synchronized void put(String s){
		try{
			//fermati se pieno o se l'ultimo item non è stato letto da tutti
			while(numItems == maxItems || nLettoFirstItem != maxLettori){
				if(numItems == maxItems) System.out.println("Blocked: Buffer pieno");
				if(nLettoFirstItem != maxLettori) System.out.println("Blocked: Non tutti hanno letto");
				this.wait();
			}
			first = (first+1)%maxItems;
			buffer[first] = s;
			nLettoFirstItem = 0;
			numItems++;
			/*
			 * ERRORE:
			 * ho fatto notify ma ci sono thread addormentati su condizioni diverse
			 * se si sveglia quello sbagliato potrebbe riaddormentarsi e non svegliarsi mai piu
			 * (STESSA COSA SOTTO)
			 */
			this.notify();	
		}catch(Exception e){e.printStackTrace();};
	}
	
	
	public synchronized String getFirst(){
		String s = null;
		try{
			//fermati se vuoto
			while(numItems == 0){
				if(numItems == 0) System.out.println("Blocked: Buffer vuoto");
				this.wait();
			}
			nLettoFirstItem++;
			if(nLettoFirstItem==maxLettori){
				numItems--;
			}
			this.notify();	
			s = buffer[first];
		}catch(Exception e){e.printStackTrace();};
		return s;
	}

	@Override
	public String toString() {
		String ris ="[";
		synchronized(this){
				for(int i = 0; i<buffer.length;i++){
					ris+= buffer[i]+" ";
				}
			}
		ris+="]";
		return ris;
	}

	/*
	 * TODO: Get previous...
	 */
	
	
	

}
