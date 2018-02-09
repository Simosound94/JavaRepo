import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Client {

	public static byte[] scriviFile(){
		byte[] file = new byte[10];
		for(int i= 0; i<10; i++){
			if(Math.random()>0.5)
				file[i] = 1;
			else
				file[i] = 0;
		}
		return file;
	}
	
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		int azione = Integer.parseInt(args[1]);
		int idFile = Integer.parseInt(args[2]);

		
		Azione az = new Azione();
		az.idFile = idFile;
		if(azione == 3){
			az.file = scriviFile();
		}
		az.tipo = Azione.Tipo.values()[azione];
		
		try{
			Socket s = new Socket("127.0.0.1", port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush(); 
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			System.out.println("Sending request...");
			oos.writeObject(az);
			
			FileTransf ft = (FileTransf) ois.readObject();
			if(!ft.ack){
				System.out.println("File not foud");

				switch(az.tipo){
				case lettura:{
					Azione risp = new Azione();
					risp.tipo = Azione.Tipo.fineLettura;
					oos.writeObject(risp);
					break;
					}
				
				
				case scrittura:{
					ft.ack = false;
					oos.writeObject(ft);
					
					break;
					}
				}
				
			}
		
			System.out.println("Ricevuto oggetto "+ft.idFile);
			
			switch(az.tipo){
			case lettura:{
				
				System.out.println("Reading file: "+Arrays.toString(ft.file));
				Azione risp = new Azione();
				Thread.sleep(10000); //Lettura...
				risp.tipo = Azione.Tipo.fineLettura;
				oos.writeObject(risp);
				break;
				}
			
			
			case scrittura:{
				ft.file = scriviFile();
				System.out.println("Writing file...");
				Thread.sleep(10000);
				ft.ack = true;
				oos.writeObject(ft);
				
				break;
				}
			
			
			case inserisci:{
				if(ft.ack){
					System.out.println("Insert done");
				}
				else{
					System.out.println("Insert error");

				}
				break;
				}
			}
			

		}catch(Exception e){e.printStackTrace();}
	}

}
