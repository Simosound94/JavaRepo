import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Subscriber {
	
public static void main(String[] args) {
		
		String ipAddr = args[0];
		String port = args[1];
		int arg = Integer.parseInt(args[2]);
		
		try {
			Socket s = new Socket(ipAddr, Integer.parseInt(port));
			
			// recupero outputstream
			OutputStream os = s.getOutputStream();
			// objectoutputstream...
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject(Arguments.args.values()[arg]);
			System.out.println("Subscriber waits events on argument: "+Arguments.args.values()[arg]);
			
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Event e = null;
			while(true){
				try{
				e = (Event)ois.readObject();
				}catch(Exception ex){ex.printStackTrace();}
				
				System.out.println(e);
			}
			
			
			
		}catch(Exception e){}
	}

}
