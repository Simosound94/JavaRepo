import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class ServerWorker implements Runnable {
	private LinkedList<Messaggio> toDeliver;
	private HashMap<String, User> users;
	private Socket s;
	

	@Override
	public void run() {
		try{
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		Object o = ois.readObject();
		if(o instanceof User){
			User newUser = (User) o;
			newUser.stream = oos;
			System.out.println("New User arrived id: "+newUser.idUtente);
			Ack ok = new Ack();
			ok.ack = false;
			synchronized(users){
				if(!users.containsKey(newUser.idUtente)){
					users.put(newUser.idUtente,newUser);
					ok.ack = true;
				}
			}
			oos.writeObject(ok);
		}
		if(o instanceof Messaggio){
			Messaggio newMess = (Messaggio) o;
			System.out.println("Message arrived");
			synchronized(toDeliver){
				toDeliver.add(newMess);
				toDeliver.notify();
			}
		}
		}catch(Exception e){e.printStackTrace();}
	}


	public ServerWorker(LinkedList<Messaggio> toDeliver, HashMap<String, User>  users, Socket s) {
		super();
		this.toDeliver = toDeliver;
		this.users = users;
		this.s = s;
	}

}
