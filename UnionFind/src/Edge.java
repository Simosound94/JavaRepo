public class Edge implements Comparable<Edge> {

	public int first, second;
	public double weight;
	
	public Edge(int first, int sec, double weight){
		this.first = first;
		this.second = sec;
		this.weight = weight;
	}
	
	
	public int compareTo(Edge other) {
		if(this.weight > other.weight) return 1;
		else if(this.weight < other.weight) return -1;
		return 0;
	}
	
	public String toString(){
		return first+" "+second+" "+weight;
	}
}
