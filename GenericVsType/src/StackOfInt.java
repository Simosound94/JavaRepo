import java.util.Iterator;

// Stack di interi con capacità illimitata
// e possibilità di iterare sugli elementi
public class StackOfInt {
	private int[] contents;  // elementi dello stack
	private int   top;       // indice della cima dello stack
	
	// Costruttore
	// Trattandosi di uno stack non limitato a priori
	// al costruttore di default non serve una dimensione
	public StackOfInt() {
		contents = new int[1];
		top = -1;
	}
	
	// Costruttore
	// Ha senso prevedere anche la costruzione di uno
	// stack con una certa capacità iniziale
	// Viene generata un'eccezione a runtime se cap <= 0
	public StackOfInt(int cap) {
		assert(cap > 0);
		contents = new int[cap];
		top = -1;
	}
	
	// Operatore push
	// Gestisce lo stack in modo dinamico
	public void push(int e) {
		if ((top + 1) == contents.length) {
			// Il vettore contents è saturo, quindi va ingrandito
			resize();
		}
		// Arrivati a questo punto 
		// (top + 1) < contents.length
		top += 1;
		contents[top] = e;
	}
	
	// Operatore pop
	// Genera un'eccezione a runtime se top < 0
	public int pop() {
		assert(top >= 0);
		int index = top;
		top -= 1;
		return contents[index]; 
	}
	
	// Operatore top
	// Genera un'eccezione a runtime top < 0
	public int top() {
		assert(top >= 0);
		return contents[top];
	}	
	
	// Metodo "factory" per ottenere un iteratore
	public Iterator<Integer> getIterator() {
		return new ReverseArrayIterator();
	}
	
	// Predicato isEmpty
	public boolean isEmpty() {
		return (top == -1);
	}
	
	// Interrogazione delle dimensioni
	public int getSize() {
		return (top + 1);
	}
	
	// Metodo interno per il ridimensionamento dei contenuti
	private void resize() {
		int[] temp = new int[contents.length * 2];
		for (int i = 0; i < contents.length; ++i) {
			temp[i] = contents[i];
		}
		contents = temp;
	}
	
	// Classe interna per l'implementazione dell'iteratore
	// di sola lettura (l'implementazione del metodo remove 
	// non esegue alcuna operazione)
	private class ReverseArrayIterator implements Iterator<Integer> {
		
		private int i = top;
		
		public boolean hasNext() { 
			return i >= 0; 
		}
		
		public Integer next() {
			i -= 1;
			return contents[i+1];
		}
		
		public void remove() {
			// L'iteratore è di sola lettura
		}
	}
	
}
