import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		int key = Integer.parseInt(args[1]);
		int type = Integer.parseInt(args[2]);
		String data = args[3];
		
		Azione az = new Azione();
		az.key = key;
		az.type = Azione.Tipo.values()[type];
		if(az.type == Azione.Tipo.put){
			az.data = data;
		}
		
		
		try{
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush(); 
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("Sending request...");
			
		
			oos.writeObject(az);
			
			Response r = (Response) ois.readObject();
			System.out.println("Arrived response "+r);
			s.close();
			
		}catch(Exception e){e.printStackTrace();}

	}

}
