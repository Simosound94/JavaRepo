package sample1;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendTo {

	public static void main(String[] args) {
		// 
		// usage: java SendTo <addr> <port> <message>
		//
		
		String ipAddr = args[0];
		String port = args[1];
		String message = args[2];
		
		try {
			// crea il socket e lo connette a ipAddr, port
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			// recupero outputstream
			OutputStream os = s.getOutputStream();
			// printwriter per scrivere linee di caratteri...
			PrintWriter os_writer = new PrintWriter(os, true);
			// scrivi il messaggio
			os_writer.println(message);
			// fine, chiudi il socket
			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
