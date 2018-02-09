
import java.io.File;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ClientListener implements Runnable {
	
	private ObjectInputStream ois;
	private LinkedList<String> filesName;
	private File dir;
	
	public ClientListener(ObjectInputStream ois, LinkedList<String> filesName, File dir) {
		super();
		this.ois = ois;
		this.filesName = filesName;
		this.dir = dir;
	}
	

	@Override
	public void run() {
		while(true){
			try {
				Azione az = (Azione) ois.readObject();
				System.out.println("Azione arrivata: "+az);
				File f = new File(dir.getAbsolutePath()+"/"+az.nomeFile);
				synchronized(filesName){
					if(az.tipo == Azione.Tipo.crea){
						filesName.add(az.nomeFile);
						f.createNewFile();
					}
					else if(az.tipo == Azione.Tipo.ellimina){
						filesName.remove(az.nomeFile);
						f.delete();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}

	}

}
