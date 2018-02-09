package sample4;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class PrintServer {

	private String port;
	//
	// questa e' la coda di stampa
	// condivisa tra tutti i worker
	//
	private List<PrintRequest> requestList;
	
	public static void main(String[] args) {
		//
		// usage: java Receiver <port>
		//
		String port = args[0];
		PrintServer server = new PrintServer(port);
		server.startServer();
		
	}
	
	public PrintServer(String port) {
		this.port = port;
		this.requestList = new ArrayList<PrintRequest>();
	}
	
	private void startServer() {
		try {
			
			//
			// lancio un certo numero di processor...
			//
			for(int i = 0; i < 5; i++) {
				PrintServerProcessor processor = new PrintServerProcessor(this.requestList);
				Thread processor_t = new Thread(processor);
				processor_t.start();
			}
			
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
				// passo ai thread la risorsa condivisa
				//
				PrintServerWorker worker = new PrintServerWorker(s, this.requestList);
				Thread worker_t = new Thread(worker);
				worker_t.start();
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

}
