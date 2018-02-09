
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ClientSimulatoListener implements Runnable {
	
	private ObjectInputStream ois;
	private LinkedList<String> filesName;
	
	public ClientSimulatoListener(ObjectInputStream ois, LinkedList<String> filesName) {
		super();
		this.ois = ois;
		this.filesName = filesName;
	}
	

	@Override
	public void run() {
		while(true){
			try {
				Azione az = (Azione) ois.readObject();
				System.out.println("Azione arrivata: "+az);
				synchronized(filesName){
					if(az.tipo == Azione.Tipo.crea){
						filesName.add(az.nomeFile);
					}
					else if(az.tipo == Azione.Tipo.ellimina){
						filesName.remove(az.nomeFile);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}

	}

}
