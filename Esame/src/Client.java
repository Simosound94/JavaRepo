import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * 
 * Client
 * 
 * 	NOTA: provato da terminale la cartella di sincronizzazione deve risiedere
 * 		dentro il "bin" del progetto
 *  
 */

public class Client {


	public static void main(String[] args) {
		
		LinkedList<String> filesName;
		String pathDirectory = args[0];
		
		
		try{
			File dir = new File(pathDirectory);
			
			if(!dir.exists() || !dir.isDirectory()){
				System.out.println("The path must be an existing directory");
				return;
			}
				
			Socket s = new Socket("127.0.0.1", SyncServer.SERVERPORT);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			//Mi sincronizzo con il server..
			//(Non è synchronized in quanto sin ora c'è solo questo thread)
			filesName = (LinkedList<String>) ois.readObject();
			for(String fileName : filesName){
				File f = new File(pathDirectory+"/"+fileName);
				f.createNewFile();
			}
			
	
			
			//Thread che si occupa di stare ad ascoltare il server
			ClientListener cl = new ClientListener(ois, filesName, dir);
			Thread t_cl = new Thread(cl);
			t_cl.start();
			
			while(true){
				Thread.sleep(5000);
				//Se ci sono dei file appena creati non ancora sincronizzati, sincronizzali
				String[] actualListFile = dir.list();
				synchronized(filesName){
					for(int i = 0; i<actualListFile.length; i++){
						if(!filesName.contains(actualListFile[i])){
							System.out.println("E' stato creato un nuovo file: "+actualListFile[i]);
							filesName.add(actualListFile[i]);
							Azione az = new Azione();
							az.nomeFile = actualListFile[i];
							az.tipo = Azione.Tipo.crea;
							oos.writeObject(az);
						}
					}
					//Se non ci sono piu dei file che erano presenti prima, notifica la cancellazione
					LinkedList<String> toDelete = new LinkedList<String>();
					for(String name : filesName){
						if(!find(actualListFile, name)){
							System.out.println("E' stato elliminato un file: "+name);
							toDelete.add(name);
							Azione az = new Azione();
							az.nomeFile = name;
							az.tipo = Azione.Tipo.ellimina;
							oos.writeObject(az);
						}
					}
					// (ho preferito effettuarlo dopo, per evitare problemi nello scorrimento
					//	della lista filesName)
					for(String name : toDelete){
						filesName.remove(name);
					}
				}
				System.out.println(filesName.toString());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private static boolean find(String[] listFile, String file){
		boolean ris = false;
		for(int i = 0; i<listFile.length; i++){
			if(listFile[i].equals(file)){
				ris = true;
				break;
			}
		}
		return ris;
	}

}
