import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Operatore {

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		int name = Integer.parseInt(args[1]);
		
		
		/*
		 * PROBLEMA:
		 * Sono stupido, ho preso il server dell'HelpClienti
		 */

		try{
			Socket s = null;
			while(true){
				Thread.sleep((int)(Math.random()*2000+2000));
				s =new Socket("127.0.0.1", port);
				
				System.out.println("Connect for requests..");
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				OperatoreDisponibile od = new OperatoreDisponibile();
				od.idOperatore = name;
				oos.writeObject(od);
				
				
				Richiesta r = (Richiesta) ois.readObject();
				System.out.println(r);
				String ack = null;
				Socket client =  null;
				if(Math.random()>0.4){
					client =new Socket(r.ipClient, r.portClient);
					System.out.println("Connect at client..");
					ObjectOutputStream oosClient = new ObjectOutputStream(client.getOutputStream());
					oos.flush();
					ObjectInputStream oisClient = new ObjectInputStream(client.getInputStream());
					//Riesco a chiamare
					System.out.println("Client found");
					String simulazioneChiamata ="Ciao sono l'operatore #"+name+" la soluzione è ...";
					oosClient.writeObject(simulazioneChiamata);
					oosClient.flush();
					ack = "ACK";
					
					client.close();

				}
				else{
					System.out.println("Client not found");
					ack = "NACK";
				}
				oos.writeObject(ack);
				s.close();
			}
			
		}catch(Exception e){e.printStackTrace();}
	
	}

}
