import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class ServerNotifier implements Runnable {

	private LinkedList<ErrMess> toNotify;
	private LinkedList<ObjectOutputStream> subscribers;
	
	public ServerNotifier(LinkedList<ErrMess> toNotify, LinkedList<ObjectOutputStream> subscribers) {
		super();
		this.toNotify = toNotify;
		this.subscribers = subscribers;
	}

	@Override
	public void run() {
		try{
			while(true){
				ErrMess notify = null;
				synchronized(toNotify){
					while(toNotify.isEmpty()){
						toNotify.wait();
					}
					notify = toNotify.removeFirst();
				}
				synchronized(subscribers){
					for(ObjectOutputStream oos : subscribers){
						oos.writeObject(notify);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
