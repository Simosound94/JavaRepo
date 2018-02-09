package sample4;

public class MainApp {
	public static void main(String[] args) {
		String mutex = "MUTEX";
		System.out.println("mutex hash: " + mutex.hashCode());
		
		Countdown c1 = new Countdown("#1", mutex);
		
		// se passo una nuova istanza...
		// non funziona + !!
		// i thread sono mutuamente esclusivi
		// se sono sincronizzati sullo stesso *OGGETTO*
		// non *VARIABILE*, *OGGETTO* !!!!
		
		String mutex1 = "MUTEX";
		
		System.out.println("mutex1 hash: " + mutex1.hashCode());
		
		Countdown c2 = new Countdown("#2", mutex1);
		
		Thread t_c1 = new Thread(c1);
		
		Thread t_c2 = new Thread(c2);
		
		t_c1.start();
		
		t_c2.start();
		
	}
}
