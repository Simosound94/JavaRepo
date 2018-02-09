import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class SlaveServer {
	
	private static HashMap<String, Data> dhm;
	private static Streams masterNode;
	private static LinkedList<UpdateRequest> lastRequests;

	public static void main(String[] args) {
		try{
			
			String ipAddr = args[0];
			int port = Integer.parseInt(args[1]);
			lastRequests = new LinkedList<UpdateRequest>();

			Socket s = null;
			try {
				s = new Socket(ipAddr, port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			masterNode = new Streams(oos, ois);
			
			dhm = (HashMap<String,Data>)ois.readObject();
			
			LinkedList<Streams> toNotify = new LinkedList<Streams>();
			toNotify.add(masterNode);
			HashMapNotifier not = new HashMapNotifier(lastRequests,toNotify);
			Thread t_not = new Thread(not);
			t_not.start();
			
			HashMapSynchronizer hms = new HashMapSynchronizer(dhm, masterNode, lastRequests);
			Thread t_hms = new Thread(hms);
			t_hms.start();
			
			HashMapModifier hmm = new HashMapModifier(dhm, lastRequests);
			Thread t_hmm = new Thread(hmm);
				t_hmm.start();
			
			

		}catch(Exception e){e.printStackTrace();}
	}

}
