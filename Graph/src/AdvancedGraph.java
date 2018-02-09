import java.util.Iterator;
import java.util.LinkedList;

public class AdvancedGraph<NodeInfo, EdgeInfo> extends MyBasicGraph<NodeInfo, EdgeInfo> {

	public AdvancedGraph(int maxNodes) {
		super(maxNodes);
	}
	
	public LinkedList<Pair<Integer, NodeInfo>> dfs(){
		
		boolean[] marked = new boolean[V.size()];
		for(int i = 0; i<V.size(); i++)
			marked[i] = false;
		LinkedList<Pair<Integer, NodeInfo>> ris = new LinkedList<Pair<Integer, NodeInfo>>();
		for(int i = 0; i<V.size(); i++)
			if(marked[i]==false) dfsVisit(i, marked, ris);
		return ris;
	}
	
	
	public void dfsVisit(int i, boolean[] marked, LinkedList<Pair<Integer, NodeInfo>> ris){
		marked[i] = true;
		Iterator<Pair<Integer, EdgeInfo>> it = V.get(i).iterator();
		Pair<Integer, EdgeInfo> temp;
		while(it.hasNext()){
			temp = it.next();
			if(marked[temp.first.intValue()]==false) dfsVisit(temp.first.intValue(), marked, ris);
		}
		ris.add(new Pair<Integer, NodeInfo>(i, Vinfo[i]));
	}
	
	public AdvancedGraph<NodeInfo, EdgeInfo> graphTranspose(){
		AdvancedGraph<NodeInfo, EdgeInfo> ris = new AdvancedGraph<NodeInfo, EdgeInfo>(V.size());
		for(int u = 0; u<Vinfo.length; u++)
			ris.addNode(u, Vinfo[u]);
		for(int i = 0;  i<V.size(); i++){
			if(V.get(i)== null)	continue;
			Iterator<Pair<Integer, EdgeInfo>> it = V.get(i).iterator();
			Pair<Integer, EdgeInfo> temp;
			while(it.hasNext()){
				temp = it.next();
				ris.addEdge(temp.first, i, temp.second);
			}	
		}
		return ris;
	}
	
	public int[] computeSCC(){
		AdvancedGraph<NodeInfo, EdgeInfo> tran = this.graphTranspose();
		LinkedList<Pair<Integer, NodeInfo>> order = tran.dfs();
		int[]sccid = new int[V.size()];
		int id = 1;
		Iterator<Pair<Integer, NodeInfo>> it = order.iterator();
		int u;
		while(it.hasNext()){
			u =it.next().first;
			if(sccid[u]==0){
				this.visitSCC(u, id, sccid);
				id++;
			}
		}
		return sccid;
	}
	
	private void visitSCC(int toVisit, int id, int[] sccid){
		sccid[toVisit] = id;
		if(V.get(toVisit)==null) return;
		Iterator<Pair<Integer, EdgeInfo>> it = V.get(toVisit).iterator();
		int temp;
		while(it.hasNext()){
			temp = it.next().first;
			if(sccid[temp] ==0)	visitSCC(temp, id, sccid);
		}
	}
	
	
	public static void main(String[] args){
		AdvancedGraph<Integer, Integer> prova = new AdvancedGraph<Integer, Integer>(9);
		prova.addNode(1, null);
		prova.addNode(2, null);
		prova.addNode(3, null);
		prova.addNode(4, null);
		prova.addNode(5, null);
		prova.addNode(6, null);
		prova.addNode(7, null);
		prova.addNode(0, null);
		prova.addEdge(1, 2, null);
		prova.addEdge(2, 6, null);
		prova.addEdge(2, 5, null);
		prova.addEdge(2, 3, null);
		prova.addEdge(3, 4, null);
		prova.addEdge(4, 3, null);
		prova.addEdge(5, 6, null);
		prova.addEdge(6, 7, null);
		prova.addEdge(7, 6, null);
		prova.addEdge(7, 0, null);
		prova.addEdge(0, 0, null);
		prova.addEdge(3, 7, null);
		prova.addEdge(4, 0, null);
		prova.addEdge(5, 1, null);
		int[] id = prova.computeSCC();
		for(int i : id)
			System.out.print(i+" ");
		
		
	}
	
}
