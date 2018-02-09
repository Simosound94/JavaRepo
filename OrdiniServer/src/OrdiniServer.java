import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class OrdiniServer {
	

	public static void main(String[] args) {
		try{
			HashMap<String, LinkedList<Oggetto>> magazzino = new HashMap<String, LinkedList<Oggetto>>();
			
			int port = Integer.parseInt(args[0]);
			
			ServerSocket serv = new ServerSocket(port);
			System.out.println("OrdiniServer wait for connection..");
			
			while(true){
				Socket s = serv.accept();
				ServerWorker sw = new ServerWorker(s,magazzino);
				Thread t_sw = new Thread(sw);
				t_sw.start();
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
