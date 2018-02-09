import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class Visitor {

	MapIterator mappa;
	
	public Visitor(MapIterator toVisit){
		mappa = toVisit;
	}
	
	public int[] visitMap(){
		int[] ris= new int[10]; //Inizializzati automaticamente a 0
		dfsVisit(0, ris);
		for(int i = 1; i<10; i++)
			if(mappa.toIterate.identifiers[i] && ris[i]<1)
				ris[i] = -1;
		return ris;	
	}
	
	private void dfsVisit(int length, int[] distances){
		mappa.setState(true);
		Map.Coordinate pos = mappa.currentPosition;
		if(Character.isDigit(mappa.value()))
			distances[Character.getNumericValue(mappa.value())] = length;	
		Iterator<Map.Coordinate> it = mappa.neighboor();
		while(it.hasNext()){
			mappa.move(it.next());
			if(!mappa.getState(mappa.currentPosition))
				dfsVisit(length+1, distances);
			mappa.move(pos);
		}
	}
	
	
	public static void main(String args[]){
		if(args.length!=1) System.exit(0);
		Map prova = new Map();
		FileReader file = null;
		try{
			file = new FileReader(args[0]);
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		Scanner in = new Scanner(file);
		prova.readFile(in);
		MapIterator itMap = new MapIterator(prova);
		Visitor visitatore = new Visitor(itMap);
		int[] distances = visitatore.visitMap();
		for(int i = 1; i<10; i++){
			if(prova.identifiers[i]){
				System.out.println(i+" "+distances[i]);
			}
		}
		
	}
		
	
}
