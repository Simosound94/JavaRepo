import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.LinkedList;

public class ServerWorker implements Runnable {
	
	private Socket s;
	private LinkedList<ErrMess> toNotify;
	private LinkedList<ObjectOutputStream> subscribers;
	

	public ServerWorker(Socket s, LinkedList<ErrMess> toNotify, LinkedList<ObjectOutputStream> subscribers) {
		super();
		this.s = s;
		this.toNotify = toNotify;
		this.subscribers = subscribers;
	}


	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Object o = ois.readObject();
			if(o instanceof ErrMess){
				ErrMess em = (ErrMess) o;
				synchronized(toNotify){
					em.timeSteps.add(Calendar.getInstance().getTime());
					System.out.println("Arrived ErrMess: "+em);
					toNotify.add(em);
					toNotify.notifyAll();
				}
				
			}
			if(o instanceof SubscribeRequest){
				synchronized(subscribers){
					System.out.println("Arrived subscriber: "+(SubscribeRequest)o);
					subscribers.add(oos);
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
