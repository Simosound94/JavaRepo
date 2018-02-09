import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class ServerNotifier implements Runnable {
	
	
	private LinkedList<AzioneStored> toNotify;
	private LinkedList<ObjectOutputStream> clients;

	
	public ServerNotifier(LinkedList<AzioneStored> toNotify, LinkedList<ObjectOutputStream> clients) {
		super();
		this.toNotify = toNotify;
		this.clients = clients;
	}
	

	@Override
	public void run() {
		try{
			while(true){
				AzioneStored azs = null;
				synchronized(toNotify){
					while(toNotify.isEmpty()){
						toNotify.wait();
					}
					azs = toNotify.removeFirst();
				}
				System.out.println("Notify azione "+azs.az);
				synchronized(clients){
					for(ObjectOutputStream client : clients){
						if(azs.client != client){
							client.writeObject(azs.az);
						}
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}

	}

}
