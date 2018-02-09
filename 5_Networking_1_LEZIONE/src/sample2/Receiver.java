package sample2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Receiver {

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
				
				
				// get inputstream...
				InputStream is = s.getInputStream();
				// reader, per leggere caratteri...
				InputStreamReader is_reader = new InputStreamReader(is);
				// buffered reader, per leggere linee...
				BufferedReader buf_reader = new BufferedReader(is_reader);
				
				String name = buf_reader.readLine();
				
				System.out.println("Received: " + name);
				
				// get outputstream
				OutputStream os = s.getOutputStream();
				PrintWriter os_writer = new PrintWriter(os, true);
				
				os_writer.println("Hello, " + name + "!");
				
				s.close();
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} 

	}

}
