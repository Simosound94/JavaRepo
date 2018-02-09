import java.util.ArrayList;
import java.util.TreeMap;

public class BST<K extends Comparable<K>, V> {
	
	 Node root;
	 int size;
	
	private class Node{
		protected Node left, right, father;
		protected K key;
		protected V value;
		
		public Node(K key, V value, Node left, Node right, Node father){
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.father = father;
		}
	}
	
	public BST(){
		root = null;
		size = 0;
	}
	
	private Node privSearch(K toSearch){
		Node next= root;
		while(next != null)
			if(toSearch.compareTo(next.key)> 0) next = next.right;
			else if(toSearch.compareTo(next.key)< 0) next = next.left;
			else return next;
		return null;
	}
	
	public V search(K toSearch){
		return privSearch(toSearch).value;
	}
	
	
	public boolean insert(K key, V value){
		Node toIns = new Node(key, value, null, null, null);
		if(root == null){
			root = toIns;
			return true;
		}
		Node father = position(toIns.key);
		if(father == null && root!=null) return false;
		toIns.father = father;
		if(key.compareTo(father.key)>0) father.right = toIns;
		else if(key.compareTo(father.key)<0) father.left = toIns;
		size++;
		return true;
	}
	
	private Node position(K toInsert){
		Node next= root;
		Node father = null;
		while(father != next){
			father = next;
			if(toInsert.compareTo(next.key)> 0 && next.right!= null) next = next.right;
			else if(toInsert.compareTo(next.key)< 0 && next.left !=null) next = next.left;
			else if(toInsert.compareTo(next.key)==0) return null;
		}
		return father;
	}
	
	
	public ArrayList<V> toArr(){
		ArrayList<V> ris = new ArrayList<V>(size);
		toArrRec(ris, root);
		return ris;
	}
	private void toArrRec(ArrayList<V> ris, Node toVisit){
		if(toVisit != null){
			toArrRec(ris, toVisit.left);
			ris.add(toVisit.value);
			toArrRec(ris, toVisit.right);
		}
	}
	
	public V remove(K key){
		Node toDel = privSearch(key);
		return nodeDelete(toDel).value;		
	}
	
	private Node nodeDelete(Node toDel){
		Node sostituto;
		if(toDel.left == null || toDel.right == null) 	sostituto = toDel;
														sostituto = nodeSucc(toDel);
		Node temp;
		if(sostituto.left != null) 	temp = sostituto.left;
		else						temp = sostituto.right;
		if(temp != null)			temp.father =sostituto.father;
		if(sostituto.father == null)	root = temp;
		else					if(sostituto == sostituto.father.left)	sostituto.father.left = temp;
								else sostituto.father.right = temp;
		if(sostituto != toDel){
			toDel.key = sostituto.key;
			toDel.value = sostituto.value;
		}
		return sostituto;
		
	}
	
	private Node nodeSucc(Node x){
		Node y;
		if(x.right != null){
			while(x.left != null)	x =  x.left;
			return x;
		}
		y = x.father;
		while(y != null && x == y.right){
			x = y;
			y = y.father;
		}
		return y;
	}
	
	
	
	

	public static void main(String[] args) {
		BST<Integer, Integer> prova = new BST<Integer, Integer>();
		System.out.println(prova.insert(2, 5));
		System.out.println(prova.insert(1, 1));
		System.out.println(prova.insert(5, 6));
		System.out.println(prova.insert(1, 6));
		System.out.println(prova.search(2));
		System.out.println(prova.remove(2));
		System.out.println(prova.toArr().toString());
		
		TreeMap<Integer, Integer> treeBil = new TreeMap<Integer, Integer>();
		treeBil.put(2, 5);
		System.out.println("TreeMap "+treeBil.containsKey(5));
		System.out.println("TreeMap "+treeBil.containsKey(2));
		
	}

}
