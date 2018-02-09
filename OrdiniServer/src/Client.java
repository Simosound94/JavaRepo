import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		String categoria = args[1];
		
		RichiestaOggetto r = new RichiestaOggetto();
		r.categoria = categoria;
		
		
		try{
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush(); 
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("Sending request...");
			oos.writeObject(r);
			
			Oggetto o = (Oggetto) ois.readObject();
			System.out.println("Ricevuto oggetto "+o.nome);
			

		}catch(Exception e){e.printStackTrace();}
	}

}
