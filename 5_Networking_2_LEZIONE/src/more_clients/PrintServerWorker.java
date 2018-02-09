package more_clients;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Thread per gestire 1 connessione
 * @author andreap
 *
 */
public class PrintServerWorker implements Runnable {
	//
	// questo e' il socket client 
	// per la comunicazione con il client
	//
	private Socket s;

	public PrintServerWorker(Socket s) {
		this.s = s;
	}
	
	//
	// qui mettiamo il codice per gestire
	// 1 singola connessione, dopo la .accept....
	//

	public void run() {
		try {
			// get inputstream...
			InputStream is = s.getInputStream();

			ObjectInputStream ois = new ObjectInputStream(is);

			PrintRequest req = (PrintRequest)ois.readObject();

			System.out.println("received print request");
			System.out.println(req.toString());

			//
			// process the print request
			// takes some time...
			//
			Thread.sleep(3000);

			// get outputstream
			OutputStream os = s.getOutputStream();

			ObjectOutputStream oos = new ObjectOutputStream(os);

			PrintResponse response = new PrintResponse();
			response.setErrorCode("0");
			response.setErrorDesc("TUTTO OK");

			oos.writeObject(response);

			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
