import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class MemoryOwner {


	public static void main(String[] args) {
		try{
			HashMap<Integer, String> memory = new HashMap<Integer, String>();
			int port = Integer.parseInt(args[0]);
			int nome = Integer.parseInt(args[1]);

	
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush(); 
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			RegisterRequest rr = new RegisterRequest();
			rr.id = nome;
			oos.writeObject(rr);
			
			Response ok = (Response) ois.readObject();
			if(!ok.ack){
				System.out.println("Server does not accept others memory owner");
				return;
			}
			System.out.println("Connected, waiting for requests...");
			while(true){
				Azione az = (Azione) ois.readObject();
				Response  res = new Response();
				res.key = az.key;
				
				switch(az.type){
					case put:{
						System.out.println("Stored: "+ az);
						memory.put(az.key, az.data);
						res.ack = true;
						break;
					}
					case get:{
						res.data = memory.get(az.key);
						System.out.println(az);
						if(res.data == null){
							System.out.println("Not Found");
							res.ack = false;
						}
						else{
							System.out.println("Found");
							res.ack = true;
						}
						break;
					}
					case delete:{
						System.out.println(az);
						res.data = memory.remove(az.key);
						if(res.data == null){
							res.ack = false;
						}
						else{
							res.ack = true;
						}
						break;
					}
				}
				
				oos.writeObject(res);
			}
			
			
		}catch(Exception e){e.printStackTrace();}
	}
	
}
