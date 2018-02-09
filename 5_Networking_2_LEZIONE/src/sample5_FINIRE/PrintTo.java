package sample5_FINIRE;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PrintTo {
	
	public final static int PORT = 1213;

	public static void main(String[] args) {
		// 
		// usage: java PrintTo <addr> <port> TEXT PRINTER ORIENTATION
		//
		
		String ipAddr = args[0];
		String port = args[1];
		String text = args[2];
		String printerName = args[3];
		String orient = args[4];
		
		try {
			// crea il socket e lo connette a ipAddr, port
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			// recupero outputstream
			OutputStream os = s.getOutputStream();
			// objectoutputstream...
			ObjectOutputStream oos = new ObjectOutputStream(os);
			// create the request
			PrintRequest req = new PrintRequest();
			req.setTextToPrint(text);
			req.setPrinterName(printerName);
			req.setOrientation(orient);
			req.setSenderAddr("10.0.0.1");
			req.setSenderPort(PORT);
			
			// serialize request
			oos.writeObject(req);
			
			
			// recupero inputstream
			InputStream is = s.getInputStream();
			// objectinputstream
			ObjectInputStream ois = new ObjectInputStream(is);
			
			PrintResponse response = (PrintResponse)ois.readObject();
			
			System.out.println("Server response: " + response.toString());
			
			// fine, chiudi il socket
			s.close();
			
			/*
			 * RISPOSTA ASINCRONA DAL SERVER
			 */
			
			ServerSocket serv = new ServerSocket(PORT);
			s = serv.accept();
			os = s.getOutputStream();
			os.flush();
			is = s.getInputStream();
			ois = new ObjectInputStream(is);
			response = (PrintResponse)ois.readObject();
			System.out.println("Server response: " + response.toString());
			
			s.close();
			serv.close();

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
