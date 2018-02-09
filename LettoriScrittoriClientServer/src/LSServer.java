import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class LSServer {

	public static void main(String[] args) {
		try{
			HashMap<Integer, Resource> storage = new HashMap<Integer, Resource>();
			
			int port = Integer.parseInt(args[0]);
			
			ServerSocket serv = new ServerSocket(port);
			System.out.println("LSServer wait for connection..");
			
			while(true){
				Socket s = serv.accept();
				ServerWorker sw = new ServerWorker(s,storage);
				Thread t_sw = new Thread(sw);
				t_sw.start();
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
