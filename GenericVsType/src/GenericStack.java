// Uno stack con capacità illimitata di tipo generico
// Item è un parametro per un generico tipo
public class GenericStack< Item > {
	
	private Item[] contents;  // elementi dello stack
	private int   top;        // indice della cima dello stack
	
	// Costruttore
	// Trattandosi di uno stack non limitato a priori
	// al costruttore di default non serve una dimensione
	public GenericStack() {
		// Non è permessa la creazione diretta di Item[]
		// contents = new Item[1]
		
		// Posso creare un array di "Object" e poi
		// effettuare un cast ad "Item"
		contents = (Item[]) new Object[1];
		top = -1;
	}
	
	// Costruttore
	// Ha senso prevedere anche la costruzione di uno
	// stack con una certa capacità iniziale
	// La assert controlla che la capacità iniziale sia 
	// un numero positivo
	public GenericStack(int cap) {
		assert(cap > 0);
		contents = (Item[]) new Object[cap];
		top = -1;
	}
	
	// Operatore push
	// Gestisce lo stack in modo dinamico
	public void push(Item e) {
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
	// La asssert controlla che valga top >= 0
	public Item pop() {
		assert(top >= 0);
		// Con questa implementazione il riferimento
		// all'oggetto "poppato" rimate attivo all'interno
		// di contents => non va in garbage collection
		// In inglese si chiama "loitering"
		//int index = top;
		//top -= 1;
		//return contents[index]; 
		
		// Con questa implementazione non rimangono
		// riferimenti appesi all'oggetto result
		// all'interno dello stack
		Item result = contents[top];
		contents[top] = null;
		top -= 1;
		return result;
		
	}
	
	// Operatore top
	// La assert controlla che top >= 0
	public Item top() {
		assert(top >= 0);
		return contents[top];
	}	
	
	// Predicato isEmpty
	public boolean isEmpty() {
		return (top == -1);
	}
	
	// Interrogazione delle dimensioni
	public int getSize() {
		return (top + 1);
	}
	
	private void resize() {
		Item[] temp = (Item[]) new Object[contents.length * 2];
		for (int i = 0; i < contents.length; ++i) {
			temp[i] = contents[i];
		}
		contents = temp;
	}
	
}
