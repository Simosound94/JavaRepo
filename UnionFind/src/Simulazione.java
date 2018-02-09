import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Simulazione {

	public static int readFile(String filePath, ArrayList<Edge> edge){
		FileReader file = null;
		try{
			file = new FileReader(filePath);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		Scanner in = new Scanner(file);
		int num= in.nextInt();
		int r= 0, c=0;
		int i = 0;
		while(in.hasNext()){
			while(c<=r){
				in.next();
				c++;
			}
			if(!in.hasNext()) break;
			edge.add(new Edge(r,c, in.nextDouble()));
			i++;
			c++;
			if(c == num){
				c=0;
				r++;
				}
		}
		return num;
	}
	
	
	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("Wrong arguments.");
			System.exit(0);
		}
		ArrayList<Edge> connection = new ArrayList<Edge>();
		ArrayList<Edge> mst = new ArrayList<Edge>();
		int point = readFile(args[0], connection);
		Collections.sort(connection);
		UnionFind<Integer>[] set = new UnionFind[point];
		for(int i = 0; i<point; i++){
			set[i] = new UnionFind<Integer>(i);
		}
		Edge temp;
		for(int i = 0; i<connection.size(); i++){
			temp = connection.get(i);
			if(!set[temp.first].find().equals(set[temp.second].find())){
				set[temp.first].union(set[temp.second]);
				mst.add(temp);
			}
		}
		Iterator<Edge> it = mst.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
	}

}
