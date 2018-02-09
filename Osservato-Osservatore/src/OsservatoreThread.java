
public class OsservatoreThread implements Runnable, Osservatore {
	
	private int nome;
	private boolean callBackChiamata;
	private int numEventiAttesi;
	private int numEventiArrivati;
	private Osservato o;
	
	public int getNome(){
		return this.nome;
	}
	

	public OsservatoreThread(int nome, Osservato o) {
		super();
		this.nome = nome;
		this.o = o;
		callBackChiamata = false;
		numEventiAttesi = (int)((Math.random()*10)+11);
		numEventiArrivati = 0;
		//mi registro a osservatore
		o.registraOsservatore(this);
	}

	
	@Override
	public synchronized void callBack(int event) {
		//Notifica che è stata eseguita la callBack
		callBackChiamata=true;
		System.out.println("Osservatore "+nome+", Arrivato evento: "+event+ " ("
							+numEventiArrivati+1+"/"+numEventiAttesi+")");
		this.notify();
	}

	@Override
	public void run() {
		try{
			while(true){
				synchronized(this){
					callBackChiamata = false;
					while(!callBackChiamata){
						this.wait();
					}
				//Vera funzione di callBack, se fosse bacata, si bloccherebbe questo thread
				//Non gli altri
				numEventiArrivati++;
				if(numEventiArrivati == numEventiAttesi){
					System.out.println("Osservatore "+nome+", raggiunto max EventiAttesi");
					o.rimuoviOsservatore(this);
					break;
				}
			}
							
			}
		}catch(Exception e){e.printStackTrace();}
		
	}

}
