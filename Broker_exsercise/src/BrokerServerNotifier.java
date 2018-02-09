import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;



public class BrokerServerNotifier implements Runnable {
	
	LinkedList<Event> toNotify;
	HashMap<Arguments.args, LinkedList<ObjectOutputStream>> subscribers;
	


	public BrokerServerNotifier(LinkedList<Event> toNotify, 
								HashMap<Arguments.args, LinkedList<ObjectOutputStream>> subscribers) {
		super();
		this.toNotify = toNotify;
		this.subscribers = subscribers;
	}




	@Override
	public void run() {
		try{
			System.out.println("Notifier started");
			while(true){
				Event eventToNotify = null;
				synchronized(toNotify){
					while(toNotify.isEmpty()){
						System.out.println("Notifier waiting for events");
						toNotify.wait();
						System.out.println("Notifier: event arrived");
						
					}
					eventToNotify = toNotify.removeFirst();
				}
				System.out.println(eventToNotify);
				Arguments.args a = eventToNotify.getArgomento();
				LinkedList<ObjectOutputStream> notify = subscribers.get(a);
				synchronized(notify){
					Iterator<ObjectOutputStream> it = notify.iterator();
					while(it.hasNext()){
						ObjectOutputStream oosSubscr = it.next();
						oosSubscr.writeObject(eventToNotify);;
					}
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
