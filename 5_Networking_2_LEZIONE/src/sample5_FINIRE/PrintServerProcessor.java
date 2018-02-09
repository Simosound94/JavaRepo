package sample5_FINIRE;

import java.util.List;

public class PrintServerProcessor implements Runnable {
	private List<PrintRequest> requestList;
	private List<PrintResponse> responses;





	public PrintServerProcessor(List<PrintRequest> requestList, List<PrintResponse> responses) {
		super();
		this.requestList = requestList;
		this.responses = responses;
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
			PrintResponse resp = new PrintResponse();
			resp.setErrorCode("0");
			resp.setErrorDesc("PRINT "+req.getRequestId()+" DONE");
			//Aggiungi risposta e sveglia notifier
			synchronized(responses){
				responses.add(resp);
				responses.notify();
			}
		}
	}
}
