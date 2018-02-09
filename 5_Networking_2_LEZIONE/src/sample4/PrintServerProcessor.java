package sample4;

import java.util.List;

public class PrintServerProcessor implements Runnable {
	private List<PrintRequest> requestList;
	
	public PrintServerProcessor(List<PrintRequest> requestList) {
		this.requestList = requestList;
	}
	
	public void run() {
		while(true) {
			PrintRequest req = null;
			synchronized(this.requestList) {
				while(this.requestList.size() == 0) {
					try {
						// wait fino a quando non c'e' una richiesta da processare...
						this.requestList.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// preleva la prima richiesta disponibile...
				req = this.requestList.remove(0);
			}
			
			// simula la stampa
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// done...  (per ora)
			System.out.println("PROCESSED print request");
			System.out.println(req.toString());
		}
	}
}
