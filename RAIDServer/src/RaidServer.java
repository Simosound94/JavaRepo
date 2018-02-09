import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * 	RAIDServer
 * 	crea una rete di nodi a cui inviare informazioni derivate da un client
 * 	la rete viene creata nel seguente modo:
 * 
 * client 	-> RaidServer 	-> ModuloSubscriber
 * 							-> ModuloServer		-> ModuloSubscriber
 * 							-> ModuloServer		-> ModuloServer		-> ...	-> ModuloSubscriber
 * 
 * @author simone
 *
 */


public class RaidServer {

	public static final int MAINSERVERPORT = 1257;
	
	public static void main(String[] args) {
		LinkedList<ErrMess> toNotify = new LinkedList<ErrMess>();
		LinkedList<ObjectOutputStream> subscribers = new LinkedList<ObjectOutputStream>();
		
		for(int i = 0; i<3; i++){
			ServerNotifier sn = new ServerNotifier(toNotify, subscribers);
			Thread tsn = new Thread(sn);
			tsn.start();
		}
		
		
		try {
			ServerSocket serv = new ServerSocket(MAINSERVERPORT);
			
			while(true){
				Socket s = serv.accept();
				ServerWorker sw = new ServerWorker(s,toNotify, subscribers);
				Thread tsw = new Thread(sw);
				tsw.start();
				
			}
		} catch (IOException e) { e.printStackTrace();}

	}

}
