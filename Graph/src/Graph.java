import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
/* Grafo orientato */

public class Graph <VerType extends Comparable<VerType>, ArcType extends Comparable<ArcType>> {
	
	///////////////////////////////
	//CLASSI INTERNE
	//////////////////////////////
	
	
	public enum Color{white, gray, black};
	
	private class Arco {
		public VerType destinazione;
		public ArcType arco;
		
		public Arco(ArcType ar, VerType dest){
			arco = ar;
			destinazione = dest;
		}
		
		public boolean equals(Arco other){
			return this.destinazione.equals(other.destinazione);
		}

	}

	//////////////////////////////
	//INTERFACCIA
	//////////////////////////////	
	
	private ArrayList<VerType> ver;
	private ArrayList<LinkedList<Arco>> arc;
	
	public Graph(){
		ver = new ArrayList<VerType>();
		arc = new ArrayList<LinkedList<Arco>>();
		
	}
	
	public boolean insertVer(VerType x){
		if(!ver.contains(x)){
			ver.add(x);
			arc.add((ver.size()-1), new LinkedList<Arco>());
			return true;
		}
		return false;
	}

	public boolean insertArc(VerType start, VerType end, ArcType arcToInsert){
		int index = ver.indexOf(start);
		LinkedList<Arco> target = arc.get(index);
		Arco ins = new Arco(arcToInsert, end);
		
		//Controllo se è già stato inserito
		Iterator<Arco> itArc = target.iterator();
		while(itArc.hasNext() && !itArc.next().equals(ins)){};
		if(!itArc.hasNext()){									/*Se non l'hai trovato*/
			target.add(ins);
			return true;
			}
		else{
			return false;
		}
	}
	
	
	public ArrayList<VerType> deepVisit(){
		Color[] marked = new Color[ver.size()];
		for(int i = 0 ; i<ver.size() ; i++)
			marked[i] = Color.white;
		ArrayList<VerType> ris = new ArrayList<VerType>();
		for(int i = 0 ; i<ver.size() ; i++)
			if(marked[i] == Color.white)
				ris.addAll(deepVisit(i, marked));
		return ris;
		}
	
	
	public ArrayList<VerType> breadthVisit(){
		Color[] marked = new Color[ver.size()];
		for(int i = 0 ; i<ver.size() ; i++)
			marked[i] = Color.white;
		ArrayList<VerType> ris = new ArrayList<VerType>();
		for(int i = 0 ; i<ver.size() ; i++)
			if(marked[i] == Color.white)
				ris.addAll(breadthVisit(i, marked));
		return ris;
	
	}
	
	public ArrayList<VerType> topologicalSort(){
		ArrayList<VerType> ris = deepVisit();
		Collections.reverse(ris);
		return ris;
	}
	
	
	////////////////////////////////
	//INTERNI
	///////////////////////////////
	
	private ArrayList<VerType> deepVisit(int index, Color[] marked){
		ArrayList<VerType> ris = new ArrayList<VerType>();
		marked[index] = Color.gray;
		// ris.add(ver.get(index));  						/*se processo all'inizio*/
		Iterator<Arco> itArc = arc.get(index).iterator();
		VerType destVer;
		int verInd;
		while(itArc.hasNext()){
			destVer = itArc.next().destinazione;
			verInd = ver.indexOf(destVer);
			if(marked[verInd] == Color.white)
				ris.addAll(deepVisit(verInd, marked));
		}
		marked[index] = Color.black;
		ris.add(ver.get(index));							/*se processo alla fine*/
		return ris;
	}
	
	private ArrayList<VerType> breadthVisit(int index, Color[] marked){
		FixedSizeQueue<VerType> visit = new FixedSizeQueue<VerType>(ver.size());
		ArrayList<VerType> ris = new ArrayList<VerType>();
		marked[index] = Color.gray;
		//ris.add(ver.get(index));
		visit.enQueue(ver.get(index));
		int verInd;
		VerType destVer, visitVer;
		while(!visit.isEmpty()){
			visitVer = visit.deQueue();
			index = ver.indexOf(visitVer);
			Iterator<Arco> itArc = arc.get(ver.indexOf(visitVer)).iterator();
			while(itArc.hasNext()){
				destVer = itArc.next().destinazione;
				verInd = ver.indexOf(destVer);
				if(marked[verInd] == Color.white){
					marked[verInd] = Color.gray;
					visit.enQueue(ver.get(verInd));
					//ris.add(ver.get(verInd));
				}
			
			}
			marked[index] = Color.black;
			ris.add(ver.get(index));
		}
		return ris;
	}
	
	
	
	
	/////////////////////
	//Main
	////////////////////
	
	public static void main(String[] args){
		Graph<Integer, Integer> prova = new Graph<Integer, Integer>();
		prova.insertVer(7);
		prova.insertVer(3);
		prova.insertVer(5);
		prova.insertVer(4);
		prova.insertVer(8);
		prova.insertVer(12);
		prova.insertVer(10);
		prova.insertArc(7, 3, 1);
		prova.insertArc(3, 12, 1);
		prova.insertArc(10, 8, 1);
		prova.insertArc(4, 3, 1);
		prova.insertArc(5, 10, 1);
		prova.insertArc(12, 5, 1);
		prova.insertArc(7, 4, 1);
		ArrayList<Integer> ris = prova.deepVisit();
		System.out.println("Deep visit: \t\t"+ris);
		ris = prova.breadthVisit();
		System.out.println("Breadth visit: \t\t"+ris);
		ris = prova.topologicalSort();
		System.out.println("Topological Sort: \t"+ris);
		
		
	}
	
	
	
	
}


