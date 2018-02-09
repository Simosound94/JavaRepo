import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Publisher {

	public static void main(String[] args) {
		
		String ipAddr = args[0];
		String port = args[1];
		String payload = args[2];
		int arg = Integer.parseInt(args[3]);

		
		try {
			Event e = new Event(payload,Arguments.args.values()[arg]);
			System.out.println("Publisher send event");
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			// recupero outputstream
			OutputStream os = s.getOutputStream();
			// objectoutputstream...
			ObjectOutputStream oos = new ObjectOutputStream(os);
			/*
			 * 	/!\ SE NON SI FA LA WRITE OBJECT 
			 * 	LA CONNESSIONE VIENE EFFETTUATA LO STESSO
			 * 	ED IL SERVER SI BLOCCA AD ATTENDERE L'OGGETTO
			 * 	CHE CHIARAMENTE NON ARRIVERA' MAI
			 */
			oos.writeObject(e);

			
		}catch(Exception e){}
	}

}
