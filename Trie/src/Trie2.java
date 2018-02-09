
public class Trie2 {

	public class Node{
		Node[] next;
		int ref;
		
		public Node(){
			next = new Node[26];
			ref = 0;
		}
	}
	
	Node root;
	
	public void set(int ref, String toIns){
		root = set(root, ref, toIns, 0);
	}
	private Node set(Node x, int ref, String toIns, int index){
		if(x== null) x = new Node();
		if(index == toIns.length()){
			x.ref = ref;
			return x;
		}
		int j = (int)toIns.charAt(index)-97;
		x.next[j] = set(x.next[j], ref, toIns, index+1);
		return x;
	}
	
	public int find(String toIns){
		Node x = get(root, toIns, 0);
		if(x!=null)	return x.ref;
		else				return 0;
	}
	private Node get(Node x, String toIns, int index){
		if(x==null) return null;
		if(index==toIns.length())	return x;
		int j = (int)toIns.charAt(index)-97;
		return get(x.next[j], toIns, index+1);
	}
	
	public void delete(String toDel){
		root = del(root, toDel, 0);
	}
	public Node del(Node x, String toDel, int index){
		if(x== null) 					return null;
		if(index == toDel.length())		x.ref = 0;
		else{
			int j = (int)toDel.charAt(index)-97;
			x.next[j] = del(x.next[j], toDel, index+1);
		}
		if(x.ref!=0) return x;
		for(int i = 0; i<26; i++)
			if(x.next[i]!=null) return x;
		return null;
	}
	
	
	public static void main(String ags[]){
		Trie2 prova = new Trie2();
		prova.set(2, "ciao");
		prova.set(3, "jabbs");
		System.out.println(prova.find("asdd"));
		System.out.println(prova.find("ciao"));
		prova.delete("ciao");
	}
	
}
