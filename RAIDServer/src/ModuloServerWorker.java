import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ModuloServerWorker implements Runnable {

	private Socket s;
	private LinkedList<ObjectOutputStream> subscribers;
	

	public ModuloServerWorker(Socket s, LinkedList<ObjectOutputStream> subscribers) {
		super();
		this.s = s;
		this.subscribers = subscribers;
	}


	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Object o = ois.readObject();
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
