
import java.io.ObjectInputStream;

public class ClientListener implements Runnable {
	
	private ObjectInputStream ois;
	
	public ClientListener(ObjectInputStream ois) {
		super();
		this.ois = ois;
	}

	@Override
	public void run() {
		while(true){
			try {
				Messaggio m = (Messaggio) ois.readObject();
				System.out.println(m);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}

	}

}
