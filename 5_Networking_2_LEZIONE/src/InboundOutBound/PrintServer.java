package InboundOutBound;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class PrintServer {

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
				
				ObjectInputStream ois = new ObjectInputStream(is);
				
				PrintRequest req = (PrintRequest)ois.readObject();
				
				System.out.println("received print request");
				System.out.println(req.toString());
				
				//
				// process the print request
				// takes some time...
				//
				try {
					Thread.sleep(3000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				// get outputstream
				OutputStream os = s.getOutputStream();
				
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				PrintResponse response = new PrintResponse();
				response.setErrorCode("0");
				response.setErrorDesc("TUTTO OK");
				
				oos.writeObject(response);
				
				s.close();
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} 

	}

}
