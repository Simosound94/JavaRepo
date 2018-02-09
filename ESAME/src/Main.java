import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	
	public static Graph readFile(String filePath, LinkedList<Edge> toCheck){
		FileReader file = null;
		try{
			file = new FileReader(filePath);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		Scanner in = new Scanner(file);
		int numNode = in.nextInt();
		Graph g = new Graph(numNode);
		String temp1 = in.next();
		String temp2;
		assert(temp1.equals("G"));
		while(true){
			temp1 = in.next();
			if(temp1.equals("Q")) break;
			temp2 = in.next();
			g.addEdge(Integer.parseInt(temp1), Integer.parseInt(temp2));
		}
		Edge toIns;
		while(in.hasNext()){
			temp1 = in.next();
			temp2 = in.next();
			toIns = new Edge(Integer.parseInt(temp1), Integer.parseInt(temp2));
			toCheck.add(toIns);
		}
		in.close();
		return g;
	}
	
	public static void main(String args[]){
		if(args.length!=1){
			System.out.println("Wrong arguments");
			System.exit(0);
		}
		LinkedList<Edge> toCheck = new LinkedList<Edge>();
		Graph g = readFile(args[0], toCheck);
		
		Iterator<Edge> check = toCheck.iterator();
		Edge temp;
		/*------ METODO 1-------------------- */
//		while(check.hasNext()){
//			temp = check.next();
//			if(g.checkPath(temp.from, temp.to))			System.out.println("SI");
//			else										System.out.println("NO");
//		}
		/*---------- METODO 2 ---------------------*/
		int[] sccID = g.computeSCC();
		while(check.hasNext()){
			temp = check.next();
			if(sccID[temp.from] == sccID[temp.to])		System.out.println("SI");
			else										System.out.println("NO");
		}
	}
	
	
}
