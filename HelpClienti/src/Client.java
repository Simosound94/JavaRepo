import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		int clientPort = Integer.parseInt(args[1]);
		String desc = args[2];
		
		Richiesta r = new Richiesta();
		r.descrizione = desc;
		r.ntel = "01234124";
		r.ipClient = "127.0.0.1";
		r.portClient = clientPort;
		
		try{
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush(); 
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("Sending request...");
			
		
			oos.writeObject(r);
			
			
			//Chiamata asincrona dell'operatore
			ServerSocket risp = new ServerSocket(clientPort);
			System.out.println("Waiting for responce...");
			Socket risposta = risp.accept(); 
			System.out.println("Connection...");
			
			ObjectOutputStream oosOperatore = new ObjectOutputStream(risposta.getOutputStream());
			oosOperatore.flush(); 
			ObjectInputStream oisOperatore = new ObjectInputStream(risposta.getInputStream());
			
			String simulazioneChiamata = (String) oisOperatore.readObject();
			System.out.println("Responce arrived");

			System.out.println("Chiamata: "+simulazioneChiamata);
			
			risposta.close();
			risp.close();
			s.close();
			
		}catch(Exception e){e.printStackTrace();}
	}

}
