package sample2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendTo {

	public static void main(String[] args) {
		// 
		// usage: java SendTo <addr> <port> <name>
		//
		
		String ipAddr = args[0];
		String port = args[1];
		String name = args[2];
		
		try {
			// crea il socket e lo connette a ipAddr, port
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			// recupero outputstream
			OutputStream os = s.getOutputStream();
			// printwriter per scrivere linee di caratteri...
			PrintWriter os_writer = new PrintWriter(os, true);
			// scrivi il mio nome
			os_writer.println(name);
			
			// recupero inputstream
			InputStream is = s.getInputStream();
			InputStreamReader is_reader = new InputStreamReader(is);
			BufferedReader buf_reader = new BufferedReader(is_reader);
			
			String response = buf_reader.readLine();
			
			System.out.println("Server response: " + response);
			
			// fine, chiudi il socket
			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
