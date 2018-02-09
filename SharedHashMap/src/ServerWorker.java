import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class ServerWorker implements Runnable{

	private Socket s;
	private HashMap<Integer, Streams> memoryOwners;
	private OwnerCounter memOwnerCounter;
	private Boolean acceptOwners;
	
	
	public ServerWorker(Socket s, HashMap<Integer, Streams> memoryOwners, OwnerCounter memOwnerCounter, Boolean acceptOwners) {
		super();
		this.s = s;
		this.memoryOwners = memoryOwners;
		this.memOwnerCounter = memOwnerCounter;
		this.acceptOwners = acceptOwners;
	}



	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Object o = ois.readObject();
			
			if(o instanceof RegisterRequest){
				//Chi ha fatto la richiesta è un MemoryOwner
				System.out.println("MemoryOwner arrived");
				Streams memoryOwner = new Streams(oos, ois);
				int id;
				synchronized(memoryOwners){
						Response r = new Response();
						if(acceptOwners){
							r.ack = true;
							id = memOwnerCounter.getCount();
							memOwnerCounter.inc();
							memoryOwners.put(id, memoryOwner);
						}
						else{
							r.ack = false;
						}
						oos.writeObject(r);
				}
			}
			else if(o instanceof Azione){
				Azione az = (Azione) o;
				System.out.println("Azione arrived: "+az);
				//Calcolo owner
				int ownerNumber;
				Streams owner = null;
				synchronized(memoryOwners){
					if(memOwnerCounter.getCount() == 0){
						Response r = new Response();
						r.ack = false;
						oos.writeObject(r);
						return;
					}
					acceptOwners = false;
					ownerNumber = az.key % memOwnerCounter.getCount();
					owner = memoryOwners.get(ownerNumber);
				}
				owner.out.writeObject(az);
				
				Response r = (Response) owner.in.readObject();
				oos.writeObject(r);
		
			}
			
		}catch(Exception e){e.printStackTrace();}
		
	}
	

}
