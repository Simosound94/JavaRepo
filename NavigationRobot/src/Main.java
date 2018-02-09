import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	
	/*
	 * Ritorna una stringa corrispondente all'output richiesto
	 * in quanto System.out.println(way.toString())
	 * non è conforme a tale output
	 */
	private static String printWay(LinkedList<GridWorld.Coordinate> way){
		StringBuffer ris = new StringBuffer();
		Iterator<GridWorld.Coordinate> itWay = way.iterator();
		ris.append(itWay.next());
		while(itWay.hasNext())
			ris.append(" "+itWay.next());
		return ris.toString();
	}

	
	public static void main(String[] args){
		
		//Parametri in input
		if(args.length != 3){
			System.err.println("Insert parameters.");
			System.exit(0);
		}
		int dim = Integer.valueOf(args[0]);
		double density = Double.valueOf(args[1]);
		long seed = Long.valueOf(args[2]);
		if(dim < 0 || density < 0 || density > 1){
			System.err.println("Wrong parameters.");
			System.exit(0);
		}
		
		//Creazione elementi necessari
		GridWorld world = new GridWorld(dim, density, seed);
		Robot robot = new Robot(world, dim);
		LinkedList<GridWorld.Coordinate> way;
		
		//Ricerca soluzione
		way = robot.findMinWay();
		if(world.checkPathAcyclic(way))
			System.out.println(printWay(way));
		else
			System.out.println("Errore");
	}
}
