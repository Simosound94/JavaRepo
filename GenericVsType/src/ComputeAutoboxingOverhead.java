public class ComputeAutoboxingOverhead {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Non è stato indicato il numero di iterazioni");
		}
		
		int n = Integer.parseInt(args[0]);
		
		// Confrontiamo le prestazioni dello stack generico 
		// con quelle dello stack di interi per comprendere il
		// costo associato all'autoboxing.
		GenericStack<Integer> genericStack = new GenericStack<Integer>();
		StackOfInt stackOfInt = new StackOfInt();
		
		Timer t1 = new Timer();
		Timer t2 = new Timer();
		
		for (int i = 1; i <= n; ++i) {
			t1.start();
			genericStack.push(i);
			t1.stop();
	
			t2.start();
			stackOfInt.push(i);
			t2.stop();
			
			System.out.println(i + " " + t1.getElapsedNanoSeconds() + " " + t2.getElapsedNanoSeconds());
		}
		//RISULTATO:
		//MEDIAMENTE UNA CLASSE GENERICA È IL DOPPIO PIU LENTA CHE UN TIPO SPECIFICO
		
	}

}
