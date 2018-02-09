import java.io.FileWriter;


public class Test {

	public static void main(String[] args) {
		if (args.length<1){
			System.out.println("Insert file for statistics");
			System.exit(0);
		}
		ArtificialPark world = new ArtificialPark();
		world.simulation();
		FileWriter stat=null;
		try{
			stat = new FileWriter(args[0]);
			world.statistics(stat);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		System.out.println("It's created the file "+args[0]+" containing statistics");
		

	}

}
