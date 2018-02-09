package more_clients;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class PrintServer {

	public static void main(String[] args) {
		//
		// usage: java Receiver <port>
		//
		String port = args[0];
		
		try {
			// uso un ServerSocket...
			ServerSocket server = new ServerSocket(Integer.parseInt(port));
			
			while(true) {
				// server blocks until next connection
				System.out.println("Server listens for connections...");
				Socket s = server.accept();
				
				//
				// debug connection...
				//
				
				SocketAddress remote = s.getRemoteSocketAddress();
				System.out.println("accepted connection from: " + remote.toString());
				
				//
				// lancio thread per la gestione della connessione
				// Fatto così è INEFFICIENTE, è inefficiente creare un thread ogni volta
				// In realtà viene fatto con dei thread già esistenti chiamati THREAD POOL
				//
				PrintServerWorker worker = new PrintServerWorker(s);
				Thread worker_t = new Thread(worker);
				worker_t.start();
				
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} 

	}

}
