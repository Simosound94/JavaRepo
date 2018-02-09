import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class Simulatore {
 
	public static Processo[] inputFile(String filePath){
		FileReader file = null;
		try{
			file = new FileReader(filePath);
		} catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		Scanner in = new Scanner(file);
		LinkedList<Processo> ris = new LinkedList<Processo>();
		Processo toIns;
 		while(in.hasNext()){
 			toIns = new Processo(in.nextInt(), in.nextInt(), in.nextInt());
			ris.add(toIns);
		}
 		in.close();
 		Processo[] result = new Processo[ris.size()];
 		ris.toArray(result);
 		return result;
	}
	
	public static void main(String[] args) {
		if(args.length!=1){
			System.err.println("Wrong Arguments.");
			System.exit(0);
		}
		Processo[] proc = inputFile(args[0]);
		PriorQueue queue = new PriorQueue(proc.length);
		for(Processo p : proc)
			queue.insert(p);
		Processo inExec;
		while(!queue.isEmpty()){
			inExec = queue.getMin();
			System.out.println(inExec.execProc(10));
			if(inExec.ended())
				queue.extractMin();
			else
				queue.changeKey(inExec.id);
		}
	}

}
