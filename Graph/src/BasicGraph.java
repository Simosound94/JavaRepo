import java.util.ListIterator;

// Un grafo con le seguenti caratteristiche:
// - tutti i nodi sono identificati con un indice numerico
//   nel range [0, maxNodes - 1];
// - maxNodes è il numero massimo di nodi fornito  al costruttore;
// - ogni nodo può essere etichettato con un oggetto di tipo 'NodeInfo'
// - ogni arco può essere etichettato con un oggetto di tipo 'EdgeInfo'
public class BasicGraph <NodeInfo, EdgeInfo> {

	protected BasicList< Pair< Integer, EdgeInfo > >[] V;
	protected NodeInfo[] Vinfo;
	protected int        nodeCount;
	protected int        edgeCount;
	
	// Costruttore: è necessario specificare il numero massimo di nodi
	public BasicGraph(int maxNodes) {
		// 'V' è un array di oggetti 'BasicList' che rappresentano le liste 
		// di adiacenza
		// Gli elementi di 'BasicList' sono oggetti 'Pair' formati da un
		// intero (il codice del nodo destinazione) e un oggetto 'EdgeInfo' 
		
		
		//SIGNIFICATO:
		//Sto castando un array di object ad una lista, che contiene oggetti di tipo
		//Pair (coppia) formata da oggetti integer con descrizione
		V = (BasicList< Pair< Integer, EdgeInfo > >[]) new Object[maxNodes];
		// Le informazioni associate ai nodi sono memorizzate in 'Vinfo'
		Vinfo = (NodeInfo[]) new Object[maxNodes];
		nodeCount = edgeCount = 0;
	}
	
	// Interrogazione del numero di nodi e archi nel grafo
	public int getNodeCount() {
		return nodeCount;
	}
	public int getEdgeCount() {
		return edgeCount;
	}

	// Aggiunta di nodi e archi
	public void addNode(int id, NodeInfo nodeInfo) {
		if (check(id) && (V[id] == null)) {
			// Se l'id è valido e il nodo non è già  inserito, lo aggiungo
			V[id] = new BasicList< Pair< Integer, EdgeInfo > >();
			Vinfo[id] = nodeInfo;
			nodeCount += 1;
		}
	}
	public void addEdge(int from, int to, EdgeInfo edgeInfo) {
		if (check(from) && check(to) && 
				(V[from] != null) && (V[to] != null)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// aggiungo un elemento alla lista delle adiacenze di 'from'
			// che "punta" al nodo 'to'
			Pair<Integer, EdgeInfo> edgeLabel = new Pair<Integer, EdgeInfo>(to, edgeInfo);
			V[from].insertFront(edgeLabel);
			edgeCount += 1;
		}
	}

	// Rimozione di nodi e archi
	public void removeNode(int id) {
		if (check(id) && (V[id] != null)) {
			// Se l'id è valido e il nodo è presente, elimino la sua lista
			// delle adiacenze e le informazioni ad esso associate
			V[id] = null;
			Vinfo[id] = null;
			// Devo rimuovere anche tutti gli archi entranti
			for (BasicList< Pair< Integer, EdgeInfo > > adjList : V) {
				if (adjList != null) {
					Pair<Integer, EdgeInfo> dummyKey = new Pair<Integer, EdgeInfo>(id, null);
					adjList.delete(dummyKey);
				}
			}
			nodeCount -= 1;
		}
	}
	public void removeEdge(int from, int to) {
		if (check(from) && check(to) && 
				(V[from] != null) && (V[to] != null)) {
			// Se 'from'  e 'to' sono validi e i relativi nodi esistono
			// elimino l'arco dalla lista delle adiacenze di 'from'
			BasicList< Pair< Integer, EdgeInfo > > adjList = V[from];
			Pair<Integer, EdgeInfo> dummyKey = new Pair<Integer, EdgeInfo>(to, null);
			adjList.delete(dummyKey);
			edgeCount -= 1;
		}
	}
	
	// Restituzione di un iteratore sulla lista delle adiacenze di un nodo
	public ListIterator< Pair< Integer, EdgeInfo > > getEdges(int id) {
		if (check(id) && (V[id] != null)) {
			return V[id].getIterator();
		} else {
			return null;
		}
	}
	
	// Restituzione delle informazioni associate a nodi e archi
	public NodeInfo getNodeInfo(int id) {
		if (check(id) && (V[id] != null)) {
			return Vinfo[id];
		} else {
			return null;
		}
	}
	public EdgeInfo getEdgeInfo(int from, int to) {
		if (check(from) && check(to) && 
				(V[from] != null) && (V[to] != null)) {
			BasicList< Pair< Integer, EdgeInfo > > adjList = V[from];
			Pair<Integer, EdgeInfo> dummyKey = new Pair<Integer, EdgeInfo>(to, null);
			ListIterator< Pair< Integer, EdgeInfo > > itr = adjList.find(dummyKey);
			if (itr != null) {
				return itr.next().second;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	// Predicati per il controllo dell'esistenza di nodi e archi
	public boolean hasNode(int id) {
		return (V[id]!= null);
	}
	public boolean hasEdge(int from, int to) {
		if (check(from) && check(to) && 
				(V[from] != null) && (V[to] != null)) {
			BasicList< Pair< Integer, EdgeInfo > > adjList = V[from];
			Pair<Integer, EdgeInfo> dummyKey = new Pair<Integer, EdgeInfo>(to, null);
			ListIterator< Pair< Integer, EdgeInfo > > itr = adjList.find(dummyKey);
			if (itr != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// Predicato per il controllo della validità di un id
	public boolean check(int id) {
		return ((id >= 0) && (id < V.length));
	}
}
