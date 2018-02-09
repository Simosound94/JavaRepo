import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * 
 * ClientSimulato
 * 
 * 	Client simulato "da linea di comando"
 *  
 */

public class ClientSimulato {


	public static void main(String[] args) {
		
		LinkedList<String> filesName;
		try{
		
		Socket s = new Socket("127.0.0.1", SyncServer.SERVERPORT);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

		filesName = (LinkedList<String>) ois.readObject();
		System.out.println("ListaFile: "+filesName.toString());
		
		//Thread che si occupa di stare ad ascoltare il server
		ClientSimulatoListener cl = new ClientSimulatoListener(ois, filesName);
		Thread t_cl = new Thread(cl);
		t_cl.start();
		
		while(true){
			System.out.println("Azione (0: crea, 1: ellimina): ");
			int azione = Integer.parseInt(System.console().readLine());
			System.out.println("NomeFile: ");
			String nomeFile = System.console().readLine();
			Azione az = new Azione();
			az.nomeFile = nomeFile;
			az.tipo = Azione.Tipo.values()[azione];
			synchronized(filesName){
				if(az.tipo == Azione.Tipo.crea){
					filesName.add(nomeFile);
				}
				else if(az.tipo == Azione.Tipo.ellimina){
					filesName.remove(nomeFile);
				}
				System.out.println(filesName.toString());
			}
			oos.writeObject(az);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
