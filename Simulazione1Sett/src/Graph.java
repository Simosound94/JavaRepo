import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	
	LinkedList<Integer>[] V;
	
	
	public Graph(int node){
		V = new LinkedList[node];
		for(int i = 0; i<node; i++)
			V[i] = new LinkedList<Integer>();
	}
	
	public void addEdge(int start, int end){
		assert(start< V.length && end<V.length);
		V[start].add(end);
	}
	
	
	
	public int bfs(int start, int end){
		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean[] marked = new boolean[V.length];
		int[] distances = new int[V.length];
		distances[start] = 0;
		queue.addFirst(start);
		int u,v;
		while(!queue.isEmpty()){
			u = queue.removeLast();
			marked[u] =true;
			Iterator<Integer> it = V[u].iterator();
			while(it.hasNext()){
				v =it.next();
				if(marked[v] == false){
					distances[v] = distances[u]+1;
					if(v == end)
						return distances[v];
					queue.addFirst(v);
				}
			}
		}
		return -1;
	}
	
}
