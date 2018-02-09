import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ServerWorker implements Runnable {
	
	
	private LinkedList<AzioneStored> toNotify;
	private LinkedList<ObjectOutputStream> clients;
	private LinkedList<String> filesName;
	private Socket s;

	
	public ServerWorker(LinkedList<AzioneStored> toNotify, LinkedList<ObjectOutputStream> clients, LinkedList<String> filesName, Socket s) {
		super();
		this.toNotify = toNotify;
		this.clients = clients;
		this.filesName = filesName;
		this.s = s;
	}



	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("Arrived new client "+s.getRemoteSocketAddress());
			
			synchronized(filesName){
				//Appena si connette invio al client lo stato attuale della cartella
				oos.writeObject(filesName);
			}
			synchronized(clients){
				clients.add(oos);
			}
			
			
			//Connessione permanente, resto in ascolto per modifiche di quel client
			while(true){
				Azione az = (Azione) ois.readObject();
				System.out.println("Arrived new action "+az);
				synchronized(filesName){
					if(az.tipo == Azione.Tipo.crea){
						filesName.add(az.nomeFile);
					}
					else if(az.tipo == Azione.Tipo.ellimina){
						filesName.remove(az.nomeFile);
					}
				}
				
				//AzioneStored per salvare anche client, in modo da non reinviargli le sue modifiche
				AzioneStored azs = new AzioneStored();
				azs.az = az;
				azs.client = oos;
				synchronized(toNotify){
					toNotify.add(azs);
					if(toNotify.size()-1 == 0){
						toNotify.notify();
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
