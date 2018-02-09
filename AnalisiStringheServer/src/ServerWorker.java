import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ServerWorker implements Runnable {
	

	private LinkedList<Pacchetto> toAnalyze;
	private LinkedList<Pacchetto> analyzed;
	private Socket s;
	

	public ServerWorker(LinkedList<Pacchetto> toAnalyze, LinkedList<Pacchetto> analyzed, Socket s) {
		super();
		this.toAnalyze = toAnalyze;
		this.analyzed = analyzed;
		this.s = s;
	}






	@Override
	public void run() {
		try{
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.flush();
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			Pacchetto p = (Pacchetto) ois.readObject();
			
			switch(p.type){
				case reply:{
					System.out.println("Reply request arrived");
					Pacchetto find = new Pacchetto(Pacchetto.Richiesta.reply,"NOT FOUND",0,0,0,0);
					boolean trovato = false;
					synchronized(analyzed){
						for(Pacchetto pacc : analyzed){
							if(pacc.idClient == p.idClient && pacc.idRequest == p.idRequest){
								find = pacc;
								trovato = true;
								break;
							}
						}
					}
					if(trovato){
						System.out.println("Pacchetto trovato");
						analyzed.remove(find);
					}
					else{
						System.out.println("Pacchetto non trovato");
					}
					oos.writeObject(find);
					break;
				}
				
				
				
				case syncReply:{
					System.out.println("SyncReply request arrived");
					boolean trovato  = false;
					Pacchetto find = null;
					synchronized(analyzed){
						while(!trovato){
							for(Pacchetto pacc : analyzed){
								if(pacc.idClient == p.idClient && pacc.idRequest == p.idRequest){
									trovato =true;
									find = pacc;
									break;
								}
							}
							if(trovato){
								System.out.println("Pacchetto trovato");
								oos.writeObject(find);
								analyzed.remove(find);
							}
							else{
								System.out.println("Pacchetto non trovato -> sleep");
								analyzed.wait();
								System.out.println("Pacchetto non trovato -> Wakeup");
							}
						}	
					}
					break;
				}
				
				
				
				case submit:{
					System.out.println("Submit request arrived");
					synchronized(toAnalyze){
						toAnalyze.add(p);
						if(toAnalyze.size() -1 == 0){
							toAnalyze.notify();
						}
					}
					break;
				}
			}
			
		}catch(Exception e){e.printStackTrace();}
	}

}
