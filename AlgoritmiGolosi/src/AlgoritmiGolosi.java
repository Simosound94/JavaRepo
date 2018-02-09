import java.util.Iterator;

public class AlgoritmiGolosi {

	public static int[] mstPrim(Graph g, int startPoint){
		int[] prec = new int[g.numNode];
		double[] key = new double[g.numNode];
		for(int i = 0; i<prec.length; i++){
			prec[i] = -1;
			key[i] = Integer.MAX_VALUE;
		}
		key[startPoint] = 0;
		int[] node = new int[g.numNode];
		for(int i = 0;  i<node.length; i++){
			node[i] =i+1;
		}
		MinHeap taglio = new MinHeap(key, node);
		int u,v;
		while(taglio.size>0){
			u = taglio.extractMin()-1;
			Iterator<Integer> it = g.V[u].iterator();
			while(it.hasNext()){
				v= it.next();
				if(taglio.find(v) && key[v]> g.weight[u][v]){
					key[v] = g.weight[u][v];
					prec[v] = u;
					taglio.changeKey(v, key[v]);
				}
			}
		}
		return prec;
	}

	public static void djkstra(Graph g,  int s, int[] prec, double[] stima){
		assert(prec.length == stima.length);
		for(int i = 0; i<prec.length; i++){
			prec[i] = -1;
			stima[i] = Double.MAX_VALUE;
		}
		stima[s] = 0;
		int[] node = new int[g.numNode];
		for(int i= 0; i<node.length; i++)
			node[i] = i;
		MinHeap min = new MinHeap(stima, node);
		int u,v;
		while(min.size>0){
			u= min.extractMin();
			Iterator<Integer> it = g.V[u].iterator();
			while(it.hasNext()){
				v = it.next();
				if(relax(u, v, g.weight[u][v], stima, prec)){
					min.changeKey(v, stima[v]);
				}
			}
			
		}
		
		
	}
	
	public static boolean relax(int u, int v,double weigh, double[] stima, int[] prec){
		if(stima[v]> stima[u]+weigh){
			stima[v] = stima[u]+weigh;
			prec[v] = u;
			return true;
		}
		return false;
	}
	
}
