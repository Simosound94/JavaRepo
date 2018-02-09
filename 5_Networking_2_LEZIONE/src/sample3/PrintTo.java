package sample3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class PrintTo {

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
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
