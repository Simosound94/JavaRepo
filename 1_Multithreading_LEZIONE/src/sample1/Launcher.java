package sample1;

// WRONG ....
public class Launcher {
	public static void main(String[] args) {
		Runner r1 = new Runner("#1");
		
		Runner r2 = new Runner("#2");
		
		r1.run(); //WRONG perchè NON ho chiamato il thread, ho chiamato una funzione
		
		// dead code - il controllo
		// qui non arriva...
		r2.run();
		
	}
}
