import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerWorker implements Runnable {
	
	private Socket s;
	private HashMap<Integer, Resource> storage;
	

	
	
	public ServerWorker(Socket s, HashMap<Integer, Resource> storage) {
		super();
		this.s = s;
		this.storage = storage;
	}



	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			Azione az = (Azione) ois.readObject();
			
			switch(az.tipo){
			
			case lettura:{
				System.out.println("Arrived read request for: "+az.idFile);
				Resource r = null;
				synchronized(storage){
					r = storage.get(az.idFile);
				}
				FileTransf ris = new FileTransf();
				ris.ack = false;
				if(r != null){
					//Sincronizzazione gestita all'interno
					ris = r.lettura();
				}
				oos.writeObject(ris);
				if(ris.ack){
					System.out.println("File found");
					while(true){
						az = (Azione) ois.readObject();
						if(az.tipo == Azione.Tipo.fineLettura){
							break;
						}
					}
					r.fineLettura();
				}
				break;
			}
			
			
			case scrittura:{
				Resource r = null;
				System.out.println("Arrived write request for: "+az.idFile);
				synchronized(storage){
					r = storage.get(az.idFile);
				}
				FileTransf ris = new FileTransf();
				ris.ack = false;
				if(r != null){
					//Sincronizzazione gestita all'interno
					ris = r.scrittura();
				}
				oos.writeObject(ris);
				if(ris.ack = false){
					System.out.println("File not found");
					return;
				}
				FileTransf f = (FileTransf) ois.readObject();
				if(f.ack){
					r.fineScrittura(f);
				}
				else{
					r.fineScrittura();
				}
				break;
			}
			
			
			
			case inserisci:
				FileTransf f = new FileTransf();
				System.out.println("Arrived put request for: "+az.idFile);
				synchronized(storage){
					if(az.file != null){
						f.file = az.file;
						f.ack = true;
						f.idFile = az.idFile;
						Resource r = new Resource(f);
						storage.put(az.idFile, r);
					}
					else{
						f.ack = false;
					}
				}
				oos.writeObject(f);
				break;
			}
			
			
		}catch(Exception e){e.printStackTrace();}
		
	}

}
