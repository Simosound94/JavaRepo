import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class InfoRetrivalServer {

	public static void main(String[] args) {
		try{
			
			HashMap<String, Information> cacheLavorati = new HashMap<String, Information>();
			HashMap<String, Information> cacheInLavorazione = new HashMap<String, Information>();
			int port = Integer.parseInt(args[0]);
			
			
			for(int i = 0; i<3; i++){
				ServerCalcolo sc = new ServerCalcolo(cacheLavorati, cacheInLavorazione);
				Thread tsc = new Thread(sc);
				tsc.start();
			}
			
			
			ServerSocket serv = new ServerSocket(port);
			
			System.out.println("InfoRetrival waiting for requests");
			while(true){
				Socket s = serv.accept();
				ServerWorker sw = new ServerWorker(cacheLavorati,cacheInLavorazione, s);
				Thread tsw = new Thread(sw);
				tsw.start();
			}
			
			
		}catch(Exception e){e.printStackTrace();}
	}

}
