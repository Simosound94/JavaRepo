package ritardi_permessi;

public class BufferCircolare{
	
	private String[] buffer;
	private int first;
	private int[] posizioneLettura;
//	private int numItems;
	private int maxItems;
	
	/*
	 * Per scrivere non devono aver letto tutti, semplicemente ci deve essere spazio nel buffer
	 * lo spazio nel buffer si crea quando tutti han letto quell'elemento
	 */
	public BufferCircolare(int maxItems, int maxLettori){
		buffer = new String[maxItems];
		posizioneLettura = new int[maxLettori];
		first = 0;
		for(int i=0; i<maxLettori;i++){
			posizioneLettura[i] = 0;
		}
		this.maxItems = maxItems;
//		numItems = 0;
	}
	
	public synchronized void put(String s){
		try{
			//Trovo posizione minima nel buff. circolare -> ultimo pacchetto che deve esser
			//ancora letto: (massima distanza)
			int max=0;
			for(int i = 0; i<posizioneLettura.length; i++){
				if(dist(posizioneLettura[i])>max){
					max = dist(posizioneLettura[i]);
				}
			}
			System.out.println("Distanza max: "+max);

			//fermati se l'ultimo item deve ancora essere letto
			//-1 perchè se la distanza è 1 (i puntatori sono attaccati) significa che è pieno
			while(max >= maxItems - 1){
				System.out.println("Blocked: Buffer pieno");
				this.wait();
			}
			buffer[first] = s;
			first = (first+1)%maxItems;
			this.notifyAll();	
		}catch(Exception e){e.printStackTrace();};
	}
	
	
	private int dist(int posLettore){
		if(first>posLettore)
			return first - posLettore;
		else if(first < posLettore){
			return posLettore - first + 1;
		}
		else{
			return 0;
		}
	}
	
	public synchronized String getFirst(int numLettore){
		String s = null;
		try{
			//fermati hai già letto tutto
			while(dist(posizioneLettura[numLettore]) == 0){
				System.out.println("Blocked: Buffer vuoto");
				this.wait();
			}
			s = buffer[posizioneLettura[numLettore]];
			posizioneLettura[numLettore] = (posizioneLettura[numLettore]+1)%maxItems;
			this.notifyAll();
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
