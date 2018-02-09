import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;

public class ModuloSubscriber {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
	
		try{
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			SubscribeRequest sr = new SubscribeRequest();
			oos.writeObject(sr);
			
			ErrMess em =null;
			while(true){
				em =(ErrMess) ois.readObject();
				System.out.println("ErrMessage arrived: "+em);
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
