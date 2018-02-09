import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

public class Client {

	public static void main(String[] args) {
		try{
		int port = Integer.parseInt(args[0]);
		String myId = args[1];
		
		Socket s = new Socket("127.0.0.1", port);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		//Mi registro al server
		User me = new User();
		me.idUtente = myId;
		oos.writeObject(me);
		Ack ok = (Ack) ois.readObject();
		if(!ok.ack){
			System.out.println("Id gia in uso");
			return;
		}
		
		ClientListener cl = new ClientListener(ois);
		Thread t_cl = new Thread(cl);
		t_cl.start();
		
		while(true){
			System.out.println("Utente Destinatario: ");
			String destinatario = System.console().readLine();
			System.out.println("Testo: ");
			String testMess = System.console().readLine();
			Socket messSocket = new Socket("127.0.0.1", port);
			ObjectOutputStream oosMessaggio = new ObjectOutputStream(messSocket.getOutputStream());
			Messaggio m = new Messaggio();
			m.mittente = myId;
			m.ricevente = destinatario;
			m.testo = testMess;
			m.time = Calendar.getInstance().getTime();
			oosMessaggio.writeObject(m);
			Thread.sleep(1000);
			messSocket.close();
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
