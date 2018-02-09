
public class OsservatoreThreadBug implements Runnable, Osservatore {
	
	private int nome;
	private boolean callBackChiamata;
	private int numEventiAttesi;
	private int numEventiArrivati;
	private Osservato o;
	
	public int getNome(){
		return this.nome;
	}
	

	public OsservatoreThreadBug(int nome, Osservato o) {
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
					System.out.println("Osservatore "+nome+", Arrivato evento ("
							+numEventiArrivati+"/"+numEventiAttesi+")");
				//Vera funzione di callBack, se fosse bacata, si bloccherebbe questo thread
				//Non gli altri
				//Vediamo cosa succede se qui ci fosse un bug (null pointer)
				String s = null;
				s.indexOf('2');
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
