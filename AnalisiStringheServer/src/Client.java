import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try{
			String addr = args[0];
			int port = Integer.parseInt(args[1]);
			int clientId = Integer.parseInt(args[2]);
			
			Socket s = null;
			while(true){
				s = new Socket(addr, port);
				System.out.println("0 submit 1 reply 2 synchReply");
				int command = Integer.parseInt(System.console().readLine());
				System.out.println("requestId");
				int requestId = Integer.parseInt(System.console().readLine());
				Pacchetto.Richiesta rich = Pacchetto.Richiesta.values()[command];
				Pacchetto p = new Pacchetto(rich, String.valueOf(Math.random()), 0,0,clientId, requestId);
				
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.flush();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);

				oos.writeObject(p);

				if(rich == Pacchetto.Richiesta.reply || rich == Pacchetto.Richiesta.syncReply){
					System.out.println((Pacchetto)ois.readObject());
				}
				s.close();
		}
		}catch(Exception e){e.printStackTrace();}
	}

}
