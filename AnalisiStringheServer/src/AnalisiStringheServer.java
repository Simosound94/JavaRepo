import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class AnalisiStringheServer {

	public static LinkedList<Pacchetto> toAnalyze;
	public static LinkedList<Pacchetto> analyzed;
	
	
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		
		toAnalyze = new LinkedList<Pacchetto>();
		analyzed = new LinkedList<Pacchetto>();

		
		try{
			ServerSocket serv =  new ServerSocket(port);
			System.out.println("Waiting for connections..");
			
			ServerAnalyzer sa = new ServerAnalyzer(toAnalyze, analyzed);
			Thread t_sa = new Thread(sa);
			ServerAnalyzer sa2 = new ServerAnalyzer(toAnalyze, analyzed);
			Thread t_sa2 = new Thread(sa2);
			t_sa.start();
			t_sa2.start();
			
			while(true){
				Socket s = serv.accept();
				System.out.println("Connection arrived from "+ s.getRemoteSocketAddress());
				ServerWorker sw = new ServerWorker(toAnalyze, analyzed, s);
				Thread t_sw = new Thread(sw);
				t_sw.start();
			}
			
			
		}catch(Exception e){e.printStackTrace();}

	}

}
