import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

	public static void main(String[] args) {
		try{
			int port = Integer.parseInt(args[0]);
			HashMap<Integer, FIFOMacchinaStati> macchineStati = new HashMap<Integer, FIFOMacchinaStati>();
			
			ServerSocket serv = new ServerSocket(port);
			while(true){
				Socket s = serv.accept();
				ServerWorker sw = new ServerWorker(macchineStati, s);
				Thread tsw = new Thread(sw);
				tsw.start();
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
