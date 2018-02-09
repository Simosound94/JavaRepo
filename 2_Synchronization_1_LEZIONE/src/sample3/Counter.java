package sample3;

public class Counter {
	
	private int id;
	
	//
	// synchronizzazione molto + semplice:
	// public synchronized int getId() {
	// //////...
	// }
	//
	public int getId() {
		int tmp = this.id;
		// simula procedura di calcolo di id lunga...
		for(int i=0; i < 1000000; i++);
		id++;
		return tmp;
		
	}
}
