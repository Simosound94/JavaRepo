import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		if(args.length!=1){
			System.err.println("Wrong arguments.");
			System.exit(0);
		}
		FileReader file = new FileReader(args[0]);
		Scanner in = new Scanner(file);
		assert(in.hasNext());
		int node = in.nextInt();
		Graph g = new Graph(node);
		int u,v;
		while(in.hasNext()){
			u= in.nextInt();
			v= in.nextInt();
			g.addEdge(u, v);
		}
		int ris = g.bfs(0, node-1);
		if(ris==-1)		System.out.print("NO ");
		else			System.out.print("SI ");
		System.out.print(ris);
		in.close();
	}

}
