
public class ListTemp<Item> {
	
	public class Node<Item> {
		//NON HA SENSO METTERLO PRIVATE PERCHE' E' UNA COSA CHE USI TU
		protected Node<Item> prec,succ; //Automatically inizialized at null value
		protected Item key;
		
		public Node(){
			prec=succ=null;
			key=null;
		}
		
		public Node(Node<Item> p, Node<Item> s, Item k){
			prec=p;
			succ=s;
			key=k;
		}
	}
	
	
	Node<Item> head,tail; //Inizializzati automaticamente a null
	
	
	public void InsertFront(Item x){
		Node<Item> n = new Node<Item>(null, head, x);
		if(head==null)
			head = tail = n;
		else {
			head.prec = n;
			head = n;
		}
	}
	
	public void InsertBack(Item x){
		Node<Item> n = new Node<Item>(tail, null, x);
		if(head==null)
			head = tail = n;
		else {
			tail.succ=n;
			tail=n;
		}
		
	}
	public boolean Search(Item x){  
		Node<Item> n = PrivSearch(x);			
		if(n == null)	return false;	
		else			return true;
	}
	
	
	public Item Remove(Item x) {
		Node<Item> n = PrivSearch(x);
		if(n == null) return null;
		Item ris = n.key;
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
	
private Node<Item> PrivSearch(Item x){
		Node<Item> n = head;
		while(n!=null && !(n.key.equals(x)))
			n=n.succ;
		return n;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ListTemp<Integer> prova = new ListTemp<Integer>();
		System.out.println(prova.Empty());
		for(int i=0;i<6;i++){
			prova.InsertFront(1);
		}
		System.out.println(prova.Empty());
		prova.Remove(1);
		System.out.println(prova.Search(1));
		prova.Remove(1);
		prova.Remove(1);
		prova.Remove(1);
		prova.Remove(1);
		prova.Remove(1);
		System.out.println(prova.Search(1));
		
		
	}

}
