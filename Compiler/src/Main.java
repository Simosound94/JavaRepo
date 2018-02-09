import java.io.FileReader;
import java.util.Scanner;

public class Main {
		
	
	public static void main(String[] args){
		/*------------------------- INPUT ---------------------*/
		if(args.length!=1){
			System.err.println("Wrong parameters.");
			System.exit(0);
		}
		FileReader file = null;
		try{
			file = new FileReader(args[0]);
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		Scanner in = new Scanner(file);
		StringBuffer program = new StringBuffer();
		while(in.hasNext()){
			program.append(in.nextLine());
		}
		in.close();
		
		/* -------------- INTERPRETAZIONE ----------------------- */
		Interpreter interp = new Interpreter(program.toString());
		interp.lexicalAnalisys();
		interp.parsing();
		interp.semanticAnalisys();

	}
}
