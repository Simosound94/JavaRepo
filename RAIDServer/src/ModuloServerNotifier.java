import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.LinkedList;

public class ModuloServerNotifier implements Runnable{
	private LinkedList<ObjectOutputStream> subscribers;
	private Socket mainServer;
	
	public ModuloServerNotifier(Socket mainServer, LinkedList<ObjectOutputStream> subscribers) {
		super();
		this.mainServer = mainServer;
		this.subscribers = subscribers;
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(mainServer.getOutputStream());
			oos.flush();
			SubscribeRequest sr = new SubscribeRequest();
			oos.writeObject(sr);
			ObjectInputStream ois = new ObjectInputStream(mainServer.getInputStream());
			while(true){
				ErrMess notify = (ErrMess) ois.readObject();
				notify.timeSteps.add(Calendar.getInstance().getTime());
				System.out.println("Arrived ErrMess: "+notify);
				synchronized(subscribers){
					for(ObjectOutputStream oosSubscribers : subscribers){
						oosSubscribers.writeObject(notify);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	

}
