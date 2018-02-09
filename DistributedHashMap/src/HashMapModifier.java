import java.util.HashMap;
import java.util.LinkedList;

public class HashMapModifier implements Runnable {
	
	private HashMap<String, Data> dhm;
	private LinkedList<UpdateRequest> lastRequests;

	
	
	
	
	public HashMapModifier(HashMap<String, Data> dhm, LinkedList<UpdateRequest> lastRequests) {
		super();
		this.dhm = dhm;
		this.lastRequests = lastRequests;
	}


	
	
	/*
	 * Si occupa di fare le modifiche ed inviare la notifica
	 * al Notifier
	 */
	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep(2000);
				System.out.println("0 delete 1 put 2 get");
				int command = Integer.parseInt(System.console().readLine());
				System.out.print("Key: ");
				String key = System.console().readLine();
				Data data = new Data();
				data.key = key;
				if(command ==1){
					System.out.print("Data: ");
					//FUNZIONA SOLO CON DATI STRING
					data.data = System.console().readLine();
				}
				System.out.println(data);
				UpdateRequest nr = new UpdateRequest();
				nr.data = data;
				nr.key = key;
				nr.type = UpdateRequest.RequestTypes.values()[command];
				/*
				 * TODO: problema: se faccio get di una cosa di cui ho fatto la put
				 * poi ritorna null (anche sulla stessa hashMap)
				 */
				synchronized(dhm){
					switch(nr.type){
					case get:{
							Data d = dhm.get(key);
							if(d != null)
								System.out.println(data);
							else
								System.out.println("No such element");
							break;
							}
					case put:{
						dhm.put(key, data);
						System.out.println("New Object: "+data);
						break;
						}
					case delete:{
						Data d = dhm.remove(key);
						System.out.println("Removed Object: "+d);

						break;
					}
					}
				}
				if(command < 2){
					synchronized(lastRequests){
						System.out.println(nr);
						lastRequests.add(nr);
						if(lastRequests.size()-1 ==0){
							lastRequests.notify();
						}
					}
				}
			
			}
				
		}
		catch(Exception e){e.printStackTrace();}
	}

}
