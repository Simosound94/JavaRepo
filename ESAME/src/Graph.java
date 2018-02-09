import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	private ArrayList<LinkedList<Integer>> V;
	private int numNode;
	
	public Graph(int node){
		numNode = node;
		V = new ArrayList<LinkedList<Integer>>(numNode);
		for(int i = 0; i<node; i++)
			V.add(new LinkedList<Integer>());
	}
	
	public void addEdge(int from, int to){
		assert(from< numNode && to<numNode);
		LinkedList<Integer> ins = V.get(from);
		if(ins.contains(to)) return;
		ins.add(to);
		ins = V.get(to);
		ins.add(from);
	}
	
	
	public boolean checkPath(int from, int to){
		boolean[] marked  = new boolean[numNode];
		return dfsVisit(from, to, marked);
		
	}
	
	public boolean dfsVisit(int from, int to, boolean[] marked){
		marked[from] = true;
		Iterator<Integer> adiacenti = V.get(from).iterator();
		int v;
		while(adiacenti.hasNext()){
			v = adiacenti.next();
			// ----------- ATTENZIONE PENSO DI ESSERMI DIMENTICATO IL CONTROLLO SU MARKED -----
			if(marked[v]==false){
				if(v == to)	return true;
				if(dfsVisit(v, to, marked))
					return true;
			}
		}
		return false;
	}
	
	
	public int[] computeSCC(){
		int id = 1;
		int[] sccID = new int[numNode];
		for(int i = 0; i<numNode; i++)
			if(sccID[i]==0){
				visitSCC(i, id, sccID);
				id++;
			}
		return sccID;
	}
	
	private void visitSCC(int from, int id, int[]sccID){
		sccID[from] = id;
		Iterator<Integer> adiacenti = V.get(from).iterator();
		int v;
		while(adiacenti.hasNext()){
			v = adiacenti.next();
			if(sccID[v]== 0)
				visitSCC(v, id, sccID);
		}
	}
	
	
}
