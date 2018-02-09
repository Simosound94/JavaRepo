import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * 
 * @author simone
 *
 *	SyncServer
 *
 *	NOTA: se provato da terminale, tutte le volte devo cambiare la porta, in quanto
 *		la precedente risulta occupata
 *
 */

public class SyncServer {
	
	public static final int SERVERPORT = 1241;

	public static void main(String[] args) {
		//Buffer delle azioni avvenute, ancora da notificare
		LinkedList<AzioneStored> toNotify = new LinkedList<AzioneStored>();
		
		//Clienti registrati, a cui devo notificare i cambiamenti
		LinkedList<ObjectOutputStream> clients = new LinkedList<ObjectOutputStream>();
		
		//Lista dei file, mantenuta anche sul server per sincronizzare
		// gli utenti (All'arrivo gli verrà fornita la lista dei file presenti nella 
		// cartella sincronizzata, in modo che si possano allineare)
		LinkedList<String> filesName = new LinkedList<String>();

		
		for(int i = 0; i<3; i++){
			//Thread che si occupa di inviare le notifiche arrivate
			ServerNotifier sn = new ServerNotifier(toNotify, clients);
			Thread tsn = new Thread(sn);
			tsn.start();
		}
		
		
		try {
			ServerSocket serv = new ServerSocket(SERVERPORT);
			System.out.println("Waiting for connections...");
			while(true){
				Socket s = serv.accept();
				// Thread che si occupa di mantenere la connessione ai client e ricevere i loro
				// aggiornamenti
				ServerWorker sw = new ServerWorker(toNotify,clients,filesName, s);
				Thread tsw = new Thread(sw);
				tsw.start();
				
			}
		} catch (Exception e) { e.printStackTrace();}

	}

}
