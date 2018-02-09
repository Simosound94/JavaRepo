import java.util.ListIterator;

// Lista base con funzioni di ricerca, iterazione e ordinamento.
// Gli elementi della lista devono implementare  l'interfaccia
// 'Comparable<T>' di java.lang. Precisando che il parametro "Item"
// implementa tale interfaccia con T=Item imponiamo che l'argomento 
// del template List possieda un metodo "compareTo" che consente 
// di confrontare oggetti di classe "Item" tra di loro.
// NOTA: si utilizza la keyword "extends" anziché "implements"
// per precisare che Item deve implementare Comparable. 


// Con un'interfaccia non sarebbe la stessa cosa?
		/*che significa?
		 * Nel parametro devo mettere extends per sintassi
		 *  */
//Differenza tra usare Object per il template o <Item> ?
	/*
	 * Con item è più sicuro: item viene sostituito con un oggetto
	 * e poi siamo sicuri di trattare solo su quell'oggetto
	 * mentre object potrebbe essere anche eterogeneio
	 */
//Perchè Object funziona anche con gli interi? (vedi List)
	/* perchè grazie all'autoboxing di java questi vengono immeditamente
	 * tramutati in Integer
	 */
public class BasicList< Item extends Comparable<Item> > {
	
	
	// /!\ BasicList è fatta con oggetti item che estendono Comparable
	// cioè che sono comparabili, per la quale è definito compareTo
	
	

	////////////////////////////////////////////////
	// INTERFACCIA
	////////////////////////////////////////////////
	
	// Costruttori
	public BasicList() {
		dummy = new Node(null);				//Nodo sentinella
		dummy.prev = dummy.next = dummy;
		size = 0;
	}
	public BasicList(BasicList<Item> sample) {
		dummy = new Node(null);
		dummy.prev = dummy.next = dummy;
		size = 0;
		ListIterator<Item> fw = sample.getIterator();
		while (fw.hasNext()) {
			this.insertBack(fw.next());
		}
	}
	
	// Predicato di lista vuota
	public boolean isEmpty() {
		return (size == 0);
	}
	
	// Inserimento di un nodo in testa alla lista
	public void insertFront(Item newKey) {
		Node node = new Node(newKey);
		node.next =  head();
		head().prev = node;
		dummy.next = node;
		node.prev = dummy;
		size += 1;
	}
	
	// Inserimento di un nodo in fondo alla lista
	public void insertBack(Item newKey) {
		Node node = new Node(newKey);
		node.prev = tail();
		tail().next = node;
		dummy.prev = node;
		node.next = dummy;
		size += 1;
	}
	
	// Accesso alla dimensione della lista
	public int getSize() {
		return size;
	}
	
	// Accesso al primo elemento
	public Item getFront() {
		// Equivalentemente: assert(dummy.next != dummy);
		assert(size > 0);
		return head().key;   
							//il metodo head (privato, vedi sotto)
							//ritorna la var di referenza della testa.
							// il .key seleziona la chiave dell'oggetto ritornato
							// (cioè la chiave di testa)
	}
		
	// Accesso al secondo elemento
	public Item getBack() {
		// Equivalentemepnte: assert(dummy.prev != dummy);
		assert(size > 0);
		return tail().key; 			//con l'assert genera errore in caso size<=0?
									//non sarebbe meglio ritornare un item null
									//in caso di errore?
	}

	
	// Rimozione del nodo in testa alla lista
	// con restituzione dell'elemento contenuto
	public Item removeFront() {
		assert(size > 0);
		Item t = head().key;    
		remove(head());
		size -= 1;
		return t;
	}
	
	// Rimozione del nodo in coda alla lista
	// con restituzione dell'elemento contenuto
	public Item removeBack() {
		assert(size > 0);
		Item t = tail().key;
		remove(tail());
		size -= 1;
		return t;
	}
	
	
	
	//         /!\
	// Ricerca di un elemento
	// Se l'elemento è presente nella lista, restituisce
	// un ListIterator che è inizializzato all'elemento
	// antecedente a quello cercato, cosicché il metodo
	// next() restituisca l'elemento cercato; se l'elemento
	// non è presente nella lista restituisce null;
	public ListIterator<Item> find(Item searchKey) {
		InternalIterator i = new InternalIterator(); 
		while (i.hasNext()) {
			if (searchKey.compareTo(i.next()) == 0) {
				i.previous();
				return i;
			}
		}
		return null;
	}
	
	// Cancellazione di un elemento
	// Se l'elemento non è presente nella lista non ha alcun effetto
	public void delete(Item searchKey) {
		Node node = search(searchKey);
		if (node != end()) {
			remove(node);
			size -= 1;
		}
	}
	
	// Restituisce un iteratore 
	public ListIterator<Item> getIterator() {
		return (new InternalIterator());    
				//Ritorna "l'indirizzo di memoria" dell'oggetto appena creato
				//uguale a fare:	Object temp = new Object();	
				//					return temp;
	}
	
	// Restituisce un iteratore che è la copia dell'iteratore corrente
	public ListIterator<Item> cloneIterator(ListIterator<Item> other) {
		if (other instanceof BasicList.InternalIterator) {
			InternalIterator proxy = (InternalIterator) other; 
			ListIterator<Item> clone = new InternalIterator(proxy);
			return clone;
		} else {
			return null;
		}
	}

	////////////////////////////////////////////////
	// IMPLEMENTAZIONE
	////////////////////////////////////////////////
	
	private Node dummy;
	private int  size;

	////////////////////////////////////////////////
	// METODI PRIVATI
	////////////////////////////////////////////////
	
	// Alias per la testa della lista;
	private Node head() {
		return dummy.next;
	}
	
	// Alias per la coda della lista;
	private Node tail() {
		return dummy.prev;
	}
	
	// Alias per il fine lista (nodo dummy)
	private Node end() {
		return dummy;
	}
	
	// Metodo interno per la ricerca. Restituisce end() se
	// l'elemento "searchKey" non è presente nella lista
	private Node search(Item searchKey) {
		Node result = head();
		while ((result != end()) && (searchKey.compareTo(result.key) != 0)) {
			result = result.next;
		}
		return result;
	}
	
	// Metodo interno per la rimozione di un nodo
	private void remove(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
	}
	
	////////////////////////////////////////////////
	// CLASSI INTERNE
	////////////////////////////////////////////////
	
	// Classe interna "Node". Notare l'utilizzo dei campi
	// "public" anziché "private". Trattandosi di una classe
	// che raggruppa informazioni ma non fornisce specifiche
	// funzionalità (a.k.a. "record di dati") risulta inutilmente
	// macchinoso prevedere "getter" e "setter"
	private class Node {
		public Item key;
		public Node prev, next;
		
		public Node(Item key) {
			this.key = key;
			prev = next = null;
		}
	}
	
	// Classe interna per implementazione di ListIterator
	// Si veda http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html
	// per una specifica del funzionamento dei metodi
	private class InternalIterator implements ListIterator<Item> {
		private Node nextNode;   // Per definizione, questo è il nodo *dopo* il cursore
		private int  nextIndex;  // Questo è l'indice del nodo dopo il cursore (da 0 a n-1)
		private Node lastNode;   // L'ultimo nodo restituito da next() o previous()

		// Costruttori
		public InternalIterator() {
			nextNode = head();
			nextIndex = 0;
			lastNode = null;
		}
		public InternalIterator(InternalIterator sample) {
			nextNode = sample.nextNode;				//Posso accedervi anche se sono privati?
			nextIndex = sample.nextIndex;			//È perchè sono comunque dento la classe?
			lastNode = sample.lastNode;
		}
		
		// Iterazione in avanti
		public boolean hasNext() {
			return (nextNode != end());
		}
		public Item next() {
			if (hasNext()) {
				lastNode = nextNode;
				nextNode = nextNode.next;
				nextIndex += 1; 
				return lastNode.key;
			} else {
				return null;
			}
		}
		public int nextIndex() {
			return nextIndex;
		}

		// Iterazione all'indietro
		public boolean hasPrevious() {
			return (nextNode.prev != end());
		}
		public Item previous() {
			if (hasPrevious()) {
				lastNode = nextNode.prev;
				nextNode = nextNode.prev;
				nextIndex -= 1;
				return lastNode.key;
			}
			return null;
		}
		public int previousIndex() {
			return (nextIndex - 1);
		}
		
		// Rimozione di "lastNode"
		// Il contatore nextIndex deve essere decrementato
		// in quanto vi è un nodo in meno nella lista
		// lastNode viene impostato a null per evitare
		// doppie rimozioni
		public void remove() {
			if (lastNode != null) {
				lastNode.next.prev = lastNode.prev;
				lastNode.prev.next = lastNode.next;
				nextIndex -= 1;
				lastNode = null;
			}
		}

		// Aggiunta di un nodo nella posizione corrente
		// Il nodo viene aggiunto tra next() e previous()
		// Il nodo successivo rimane nextNode, e quindi
		// il contatore nextIndex deve essere incrementato
		// in quanto vi è un nodo in più nella lista
		public void add(Item e) {
			Node n = new Node(e);
			Node previousNode = nextNode.prev;
			previousNode.next = n;
			n.prev = previousNode;
			n.next = nextNode;
			nextNode.prev = n;
			nextIndex += 1;
		}
		
		// Cambiamento del valore di "lastNode"
		public void set(Item e) {
			if (lastNode != null) {
				lastNode.key = e;
			}
		}

	}


}

