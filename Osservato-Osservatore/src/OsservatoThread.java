import java.util.LinkedList;

public class OsservatoThread implements Runnable, Osservato {
	
	
	private LinkedList<Osservatore> osservatori;
	private int nome;
	private Integer lastEvent;
	
	

	public OsservatoThread(int nome) {
		super();
		this.osservatori = new LinkedList<Osservatore>();
		this.nome = nome;
		lastEvent = null;
	}

	
	@Override
	public void registraOsservatore(Osservatore o) {
		synchronized(osservatori){
			osservatori.add(o);
		}
		System.out.println("Osservatore "+o.getNome()+" registrato");
	}

	@Override
	public void rimuoviOsservatore(Osservatore o) {
		synchronized(osservatori){
			osservatori.remove(o);
		}
		System.out.println("Osservatore "+o.getNome()+" rimosso");

	}

	@Override
	public void nuovoEvento(int e) {
		synchronized(this){
			lastEvent = e;
			this.notify();
		}

	}

	
	@Override
	public void run() {
		try{
			while(true){
				synchronized(this){
					lastEvent = null;
					while(lastEvent == null){
						this.wait();
					}
				}
				System.out.println("Osservato: "+nome+" evento arrivato: "+lastEvent+" notifico osservatori..");
				//Notifico tutti gli osservatori
				/*
				 *  PROBLEMA: Dovrei fare una copia di lastEvent
				 *  o fatto meglio -> LinkedList FIFO "produttre/Consumatore"
				 *  se mi cambiano il lastEvent quando si sta notificando
				 *  potrei notificare per metà un evento e per meta un altro
				 *  
				 */
				synchronized(osservatori){
					for(Osservatore o : osservatori){
						o.callBack(lastEvent);
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
