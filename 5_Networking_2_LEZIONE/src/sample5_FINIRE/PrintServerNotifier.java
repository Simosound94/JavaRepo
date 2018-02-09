package sample5_FINIRE;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class PrintServerNotifier implements Runnable {

	private List<PrintResponse> responses;

	
	public PrintServerNotifier(List<PrintResponse> responses) {
		super();
		this.responses = responses;
	}


	@Override
	public void run() {
		PrintResponse toNotify = null;
		while(true){
			synchronized(responses){
				while(responses.isEmpty()){
					try {
						responses.wait();
					} catch (InterruptedException e) {e.printStackTrace();}
				}
				toNotify = responses.remove(0);
			}
			try {
				/*
				 * Non so se funziona in locale perchè sto tentando di connettermi a me stesso
				 */
				Socket s = new Socket(toNotify.getSenderAddrForResponse(), toNotify.getSenderPortForResponse());
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(toNotify);
				oos.close();
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
	}
	

}
