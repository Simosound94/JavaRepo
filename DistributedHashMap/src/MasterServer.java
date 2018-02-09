import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class MasterServer {
	
	private static HashMap<String, Data> dhm;
	private static LinkedList<Streams> slaveNodes;
	private static LinkedList<UpdateRequest> lastRequests;

	public static void main(String[] args) {
		try{
			int port = Integer.parseInt(args[0]);
			dhm = new HashMap<String, Data>();
			slaveNodes = new LinkedList<Streams>();
			lastRequests = new LinkedList<UpdateRequest>();

			ServerSocket servs = null;
			try {
				servs = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			HashMapNotifier not = new HashMapNotifier(lastRequests,slaveNodes);
			Thread t_not = new Thread(not);
			t_not.start();
	
	
			//Rimango in ascolto per connessioni
			System.out.println("MasterServer waiting for connections...");
			while(true){
				Socket s = null;
				try {
					s = servs.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Connection accepted...");
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Streams slave = new Streams(oos,ois);
				synchronized(slaveNodes){
					slaveNodes.add(slave);
				}
				synchronized(dhm){
					oos.writeObject(dhm);
				}
				HashMapSynchronizer hms = new HashMapSynchronizer(dhm, slave, lastRequests);
				Thread t_hms = new Thread(hms);
				t_hms.start();

			}
		}catch(Exception e){e.printStackTrace();}
	}

}
