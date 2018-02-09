import java.util.HashMap;
import java.util.LinkedList;

public class ServerNotifier implements Runnable {
	private LinkedList<Messaggio> toDeliver;
	private HashMap<String, User>  users;

	@Override
	public void run() {
		try{
			Messaggio m = null;
			while(true){
				synchronized(toDeliver){
					while(toDeliver.isEmpty()){
						toDeliver.wait();
					}
					m = toDeliver.removeFirst();
				}
				System.out.println("Notify message...");
				User destinatario = null;
				synchronized(users){
					destinatario = users.get(m.ricevente);
				}
				if(destinatario !=null){
					destinatario.stream.writeObject(m);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public ServerNotifier(LinkedList<Messaggio> toDeliver, HashMap<String, User> users) {
		super();
		this.toDeliver = toDeliver;
		this.users = users;
	}

}
