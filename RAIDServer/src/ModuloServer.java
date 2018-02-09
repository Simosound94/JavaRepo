import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ModuloServer {

	public static void main(String[] args) {
		LinkedList<ObjectOutputStream> subscribers = new LinkedList<ObjectOutputStream>();
		
		int port = Integer.parseInt(args[0]);
		int portFather = Integer.parseInt(args[1]);


		
		
		try {
			Socket mainServer = new Socket("127.0.0.1", portFather);
			ServerSocket serv = new ServerSocket(port);
			
			ModuloServerNotifier msn = new ModuloServerNotifier(mainServer, subscribers);
			Thread t_msn = new Thread(msn);
			t_msn.start();
			
			while(true){
				Socket s = serv.accept();
				ModuloServerWorker sw = new ModuloServerWorker(s, subscribers);
				Thread tsw = new Thread(sw);
				tsw.start();
				
			}
		} catch (IOException e) { e.printStackTrace();}
	}

}
