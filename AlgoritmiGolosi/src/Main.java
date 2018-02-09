import java.util.Iterator;

public class Main {
	
	public static int[] mstPrim(Graph g, int startPoint){
		int[] prec = new int[g.numNode];
		Double[] key = new Double[g.numNode];
		for(int i = 0; i<prec.length; i++){
			prec[i] = -1;
			key[i] = Double.MAX_VALUE;
		}
		key[startPoint] = (double) 0;
		int[] node = new int[g.numNode];
		for(int i = 0;  i<node.length; i++){
			node[i] =i;
		}
		IndexMinPQ<Double> taglio = new IndexMinPQ<Double>(node.length);
		for(int i = 0; i<node.length; i++)
			taglio.insert(node[i], key[i]);
		int u,v;
		while(!taglio.isEmpty()){
			u = taglio.delMin();
			Iterator<Integer> it = g.V[u].iterator();
			while(it.hasNext()){
				v= it.next();
				if(taglio.contains(v) && key[v]> g.weight[u][v]){
					key[v] = g.weight[u][v];
					prec[v] = u;
					taglio.decreaseKey(v, key[v]);
				}
			}
		}
		return prec;
	}

	public static void main(String[] args) {
		Graph g = new Graph(4);
		int[] startPoint = {0, 0, 1, 1, 2, 2};
		int[] endPoint = {2, 1, 3, 0, 3, 1};
		double[] weight = {2, 90, 1, 5, 80, 1};
		int[] nodes = {0,1,2,3};
		g.insertNodes(nodes);
		g.insertEdges(startPoint, endPoint, weight);
		int[] mst = mstPrim(g, 0);
		for(int i : mst){
			System.out.println(i);
		}
		
		

	}

}
