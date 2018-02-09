import java.io.FileReader;

public class List {
	
	public class Node {
		//NON HA SENSO METTERLO PRIVATE PERCHE' E' UNA COSA CHE USI TU
		protected Node prec,succ; //Automatically inizialized at null value
		protected Object key;
		
		public Node(){
			prec=succ=null;
			key=null;
		}
		
		public Node(Node p, Node s, Object k){
			prec=p;
			succ=s;
			key=k;
		}
	}
	
	
	Node head,tail; //Inizializzati automaticamente a null
	
	
	public void InsertFront(Object x){
		Node n = new Node(null, head, x);
		if(head==null)
			head = tail = n;
		else {
			head.prec = n;
			head = n;
		}
	}
	
	public void InsertBack(Object x){
		Node n = new Node(tail, null, x);
		if(head==null)
			head = tail = n;
		else {
			tail.succ=n;
			tail=n;
		}
		
	}
	public boolean Search(Object x){  
		Node n = PrivSearch(x);			
		if(n == null)	return false;	
		else			return true;
	}
	
	
	public Object Remove(Object x) {
		Node n = PrivSearch(x);
		if(n == null) return null;
		Object ris = n.key;
		if(n.prec!=null) n.prec.succ=n.succ;
		else			head =n.succ;
		if(n.succ!=null) n.succ.prec=n.prec;
		else			tail=n.prec;
		return ris;
	}
	
	public boolean Empty(){
		return (head == null);
	}

	//---------------------------PRIVATE-----------------
	
private Node PrivSearch(Object x){
		Node n = head;
		while(n!=null && !(n.key.equals(x)))
			n=n.succ;
		return n;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List prova = new List();
		System.out.println(prova.Empty());
		FileReader f = new FileReader("../EsercitazionePAA1/bin/matr1.txt");
		for(int i=0;i<6;i++){
			prova.InsertFront(f);
		}
		System.out.println(prova.Empty());
		prova.Remove(f);
		System.out.println(prova.Search(f));
		prova.Remove(f);
		prova.Remove(f);
		prova.Remove(f);
		prova.Remove(f);
		prova.Remove(f);
		System.out.println(prova.Search(f));
		//-----------------
		System.out.println("Prova int:");
		List prova2 = new List();
		prova2.InsertFront(2);
		prova2.InsertFront(3);
		prova2.InsertFront(5);
		System.out.println("Rimozione: "+prova2.Remove(3));
		System.out.println("C'è 3: "+prova2.Search(3));
		System.out.println("C'è 2: "+prova2.Search(2));
		System.out.println(prova2.Empty());
		
		
	}

}
