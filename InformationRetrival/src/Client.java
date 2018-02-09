import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	
	public static void main(String[] args) {
		try{
			
			int port = Integer.parseInt(args[0]);
			String reqKey = args[1];
			Request r = new Request();
			r.chiaveRicerca = reqKey;
			System.out.println("Sending request for: "+r.chiaveRicerca);
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			oos.writeObject(r);
			Information info = (Information) ois.readObject();
			System.out.println("Received information: "+info.info);
			s.close();
			
			
		}catch(Exception e){e.printStackTrace();}
	}

}
