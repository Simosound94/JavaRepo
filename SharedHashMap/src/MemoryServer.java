import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MemoryServer {

	public static void main(String[] args) {
		
		
		HashMap<Integer, Streams> memoryOwners = new HashMap<Integer, Streams>();
		int port = Integer.parseInt(args[0]);
		OwnerCounter memOwnerCounter = new OwnerCounter();
		//Appena arriva una richiesta da parte di un client non si possono più aggiungere memoryOwner
		Boolean acceptOwners = true;
		
		ServerSocket serv = null;
		try {
			serv=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				Socket s =  serv.accept();
				ServerWorker sw = new ServerWorker(s, memoryOwners, memOwnerCounter, acceptOwners);
				Thread swt = new Thread(sw);
				swt.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
