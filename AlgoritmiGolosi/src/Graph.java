import java.util.LinkedList;

public class Graph {

	public LinkedList<Integer>[] V;
	public int numNode;
	public double[][] weight;
	
	
	
	public Graph(int maxNode){
		V = new LinkedList[maxNode];
		numNode = 0;
		weight = new double[maxNode][maxNode];
	}
	
	public void insertNodes(int[] nodes){
		for(int i : nodes){
			V[i] = new LinkedList<Integer>();
			numNode++;
		}
	}
	
	public void insertEdges(int[] startPoint, int[] endPoint, double[] weight){
		assert(startPoint.length == endPoint.length);
		for(int i = 0; i<startPoint.length; i++){
			assert(V[startPoint[i]]!=null);
			V[startPoint[i]].add(endPoint[i]);
			this.weight[startPoint[i]][endPoint[i]] = weight[i];
		}
		
	}
	
}
