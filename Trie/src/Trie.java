import java.util.ArrayList;

public class Trie<Item> {
	

	private class Node{
		Item key;
		ArrayList<Node> next;
		int ref;
		
		public Node(int R){
			next = new ArrayList<Node>(R);
			ref=0;
			for(int i = 0 ;i<R; i++)
				next.add(null);
		}
		
	}
	
	
	int R;
	Node root;
	
	public Trie(int uniDim){
		this.R = uniDim;
		root = new Node(R);
	}
	
	
	public void insert(String toIns, Item key){
		int index = 0;
		char ins;
		Node actual  = root;
		while(index < toIns.length()){
			ins = toIns.charAt(index);
			if(actual.next.get((int)ins-97) == null){
				actual.next.set((int)ins-97, new Node(R));
				actual.ref++;
			}
			actual = actual.next.get((int)ins-97);
			index++;
		}
		actual.key = key;
	}
	

	public Item find(String toFind){
		int index = 0;
		char ins = toFind.charAt(index);
		Node actual = root;
		while(actual.next.get((int)ins-97)!=null && index<toFind.length()){
			actual = actual.next.get((int)ins-97);
			index++;
			if(index<toFind.length()) ins = toFind.charAt(index);
		}
		if(index==toFind.length())	return actual.key;
		return null;
	}
	
	public void remove(String toDel){
		root = removeRec(root, toDel, 0);
	}
	
	private Node removeRec(Node actual, String toDel, int index){
		if(actual == null) return null;
		if(index == toDel.length()) actual.key = null;
		else{
			Node modified = removeRec(actual.next.get((int)toDel.charAt(index)-97), toDel, index+1);
			//if(modified == null) actual.next.get((int)toDel.charAt(index)).ref--;
			actual.next.set((int)toDel.charAt(index)-97, modified);
		}
		if(actual.key != null) return actual;
		for(int i = 0; i<R; i++)
			if(actual.next.get(i)!=null) return actual;
		return null;
	}
	
	

	
	
	
	
	public static void main(String[] args) {
		Trie<Integer> prova = new Trie<Integer>(26);
		prova.insert("asdf", 2);
		prova.insert("gionni", 14);
		System.out.println(prova.find("asdddd"));
		System.out.println(prova.find("asdf"));
		System.out.println(prova.find("gionni"));
		prova.remove("asdddd");
		prova.remove("asdf");
		System.out.println(prova.find("asdf"));
		

	}

}
