import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class ServerChat {

	public static void main(String[] args) {
		LinkedList<Messaggio> toDeliver = new LinkedList<Messaggio>();
		HashMap<String, User> users = new HashMap<String, User>();
		int port = Integer.parseInt(args[0]);
		
		for(int i = 0; i<3; i++){
			ServerNotifier sn = new ServerNotifier(toDeliver, users);
			Thread tsn = new Thread(sn);
			tsn.start();
		}
		
		
		try {
			ServerSocket serv = new ServerSocket(port);
			System.out.println("Waiting for connections...");
			while(true){
				Socket s = serv.accept();
				ServerWorker sw = new ServerWorker(toDeliver,users,s);
				Thread tsw = new Thread(sw);
				tsw.start();
				
			}
		} catch (IOException e) { e.printStackTrace();}

	}

}
