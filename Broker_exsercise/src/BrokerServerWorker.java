import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;



public class BrokerServerWorker implements Runnable {

	Socket s;
	LinkedList<Event> toNotify;
	HashMap<Arguments.args, LinkedList<ObjectOutputStream>> subscribers;
	
	

	
	public BrokerServerWorker(Socket s, LinkedList<Event> toNotify,
							HashMap<Arguments.args, LinkedList<ObjectOutputStream>> subscribers) {
		super();
		this.s = s;
		this.toNotify = toNotify;
		this.subscribers = subscribers;
	}






	@Override
	public void run() {
		try{
			System.out.println("Elaborating request..");
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Object o = ois.readObject();

			if(o instanceof Event){
				//Chi mi ha mandato il messaggio è un publisher
				System.out.println("Arrived publisher msg");
				Event e = (Event) o;
				synchronized(toNotify){
					toNotify.add(e);
					toNotify.notify();
				}
				
			}
			else if(o instanceof Arguments.args){
				//Chi mi ha mandato il messaggio è un subscriber
				System.out.println("Arrived subscriber");
				Arguments.args a = (Arguments.args) o;
				LinkedList<ObjectOutputStream> toAdd = subscribers.get(a);
				synchronized(toAdd){
					toAdd.add(new ObjectOutputStream(s.getOutputStream()));
				}
			}
		}catch(Exception e){}
		
		
	}
	
}
