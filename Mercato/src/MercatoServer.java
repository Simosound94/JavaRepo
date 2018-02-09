import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class MercatoServer {

	public final static int PREZZO = 12;
	
	public static void main(String[] args) {
		
		int port = Integer.parseInt(args[0]);
		ServerSocket servs = null;
		try {
			servs = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HashMap<Merce.Tipi, LinkedList<Offerta>> offerte = new 
				HashMap<Merce.Tipi, LinkedList<Offerta>> ();

		offerte.put(Merce.Tipi.Albicocche, new LinkedList<Offerta>());
		offerte.put(Merce.Tipi.Banane, new LinkedList<Offerta>());
		offerte.put(Merce.Tipi.Mango, new LinkedList<Offerta>());
		offerte.put(Merce.Tipi.Cigliege, new LinkedList<Offerta>());


		//Rimango in ascolto per connessioni
		System.out.println("MercatoServer waiting for connections...");
		while(true){
			Socket s = null;
			try {
				s = servs.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Connection accepted...");
			MercatoServerWorker w = new MercatoServerWorker(s, offerte);
			Thread w_t = new Thread(w);
			w_t.start();
		}
}
	
}
