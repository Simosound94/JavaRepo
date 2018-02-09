import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Fornitore {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		int nome = Integer.parseInt(args[1]);
		String categoria = args[2];
		int count = 0;
		
		
		while(true){
			Oggetto ogg = new Oggetto();
			ogg.categoria = categoria;
			ogg.nome = "#"+count+" Fornitore #"+nome;
			
			
			try{
				Socket s = new Socket("127.0.0.1", port);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.flush(); 
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				System.out.println("Sending request...");
				oos.writeObject(ogg);
				count++;
				
				Thread.sleep(5000);
	
			}catch(Exception e){e.printStackTrace();}
		}
	}

}
