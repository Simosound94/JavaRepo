import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {

	public static void main(String[] args) {

		String address = args[0];
		int port = Integer.parseInt(args[1]);
		int id = Integer.parseInt(args[2]);
		String protocollo = args[3];
		String infoNecessarie = args[4];
		try{
			Socket s = new Socket(address, port);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.flush();
			Pacchetto p = new Pacchetto();
			p.id =id;
			p.protocollo = protocollo;
			p.infoNecessarie = infoNecessarie;
			
			oos.writeObject(p);
			
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			p = (Pacchetto) ois.readObject();
			System.out.println(p);
			s.close();
			
		}catch(Exception e){e.printStackTrace();}
	}

}
