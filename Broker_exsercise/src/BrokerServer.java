import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BrokerServer {
	
	

	public static void main(String[] args) {
		
		int port = Integer.parseInt(args[0]);
		
		ServerSocket servs = null;
		try {
			servs = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HashMap<Arguments.args, LinkedList<ObjectOutputStream>> subscribers = new HashMap<Arguments.args, 
				LinkedList<ObjectOutputStream>>();
		/*
		 *   /!\ ATTENZIONE
		 *   ad un OutputStream è sensato collegare SOLO UN OBJECTOUTPUTSTREAM
		 *   se no si causano collisioni del tipo: due oos che vanno a confluire in un os
		 *   
		 *   perciò visto che per notificare i subscribers ci vuole un oos è meglio passare direttamente
		 *   quello e non crearlo ogni volta (passando un socket)
		 *   
		 *   			----- NON PASSARE MAI SOCKET -------------------
		 *   
		 *   /!\ ATTENZIONE AD ERRORI STRANI DOVUTI ALLE ECCEZIONI
		 *   
		 *   ---------- NON FARE MAI THROW EXCEPTION, FARE SEMPRE CATCH E.PRINTSTACKTRACE --------
		 * 
		 */
		
		subscribers.put(Arguments.args.Politics, new LinkedList<ObjectOutputStream>());
		subscribers.put(Arguments.args.Sport, new LinkedList<ObjectOutputStream>());
		subscribers.put(Arguments.args.News, new LinkedList<ObjectOutputStream>());
		LinkedList<Event> notify = new LinkedList<Event>();
		
		
		//Faccio partire il thread che notifica...
		BrokerServerNotifier n = new BrokerServerNotifier(notify, subscribers);
		Thread n_t = new Thread(n);
		n_t.start();
		
		//Rimango in ascolto per connessioni
		System.out.println("BrokerServer waiting for connections...");
		while(true){
			Socket s = null;
			try {
				s = servs.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Connection accepted...");
			BrokerServerWorker w = new BrokerServerWorker(s, notify, subscribers);
			Thread w_t = new Thread(w);
			w_t.start();
		}
		
	}

}
