/**
 * @author Simone Merello
 * 	ROBOT:
 *  classe corrispondente all'oggetto che visita il mondo.
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


public class Robot {
	
	private GridWorld world;
	private Map mappa;
	private int worldDimension;
	
	public Robot(GridWorld world, int n){
		this.world = world;
		worldDimension = n;
	}
		
	
	//////////////////////////////
	//
	// TROVA PERCORSO ACICLICO - DFS
	//
	////////////////////////////
	
	
	public LinkedList<GridWorld.Coordinate> findWay(){
		mappa = new Map(worldDimension);
		Stack<GridWorld.Coordinate> percorso =
				new Stack<GridWorld.Coordinate>();
		findWayRec(world.getCurrentCell(), percorso);
		LinkedList<GridWorld.Coordinate> ris = new LinkedList<GridWorld.Coordinate>();
		ris.addAll(percorso);
	return ris;
	} 
	

	/*Funziona come una dfsVisit
	 * 
	 * 
	 * 
	 * invariante: toAnalyze è sempre una cella vicina alla posizione del robot
	 * in GridWorld
	 */
	private void findWayRec(GridWorld.Coordinate toAnalyze,
			Stack<GridWorld.Coordinate> way){
		
		GridWorld.Coordinate next;
		
		//Trova la direzione in cui muoversi, dalla cella corrente a toAnalyze
		GridWorld.Direction move = mappa.findDirection(world.getCurrentCell(), toAnalyze);
		if(move != null)
			world.moveToAdjacentCell(move);
		
		//Inserisco il nodo corrente nel percorso fatto
		way.push(toAnalyze);						
		mappa.mark(toAnalyze, Map.State.Visited);
		Iterator<GridWorld.Coordinate> it = world.getAdjacentFreeCells().iterator();
		while(it.hasNext()){
			
			//Se con la strada precedente il robot è arrivato, si deve fermare
			if(world.targetReached())	break;
			next = it.next();
			if(mappa.value(next) != Map.State.Visited)
					findWayRec(next, way);
		}
		
		//Se il robot non è riuscito ad arrivare per quella strada, allora torna indietro
		//ed ellimina il percorso fatto
		if(!world.targetReached()){
			next = way.pop();
			move = mappa.findDirection(next, way.peek());
			world.moveToAdjacentCell(move);
			
		}
	}
	
	
	///////////////////////////
	//
	// TROVA PERCORSO ACICLICO MINIMO - BFS
	//
	//////////////////////////
	
	
	public LinkedList<GridWorld.Coordinate> findMinWay(){
		mappa = new Map(worldDimension);
		LinkedList<GridWorld.Coordinate> ris = new LinkedList<GridWorld.Coordinate>();
		ris.addAll(findMinWayRec());
		return ris;
	}
	
	/*
	 * Funziona come una bfsVisit ---> garantisce il percorso minimo
	 * 
	 */
	private Stack<GridWorld.Coordinate>  findMinWayRec(){
		
		//Lista utilizzata come queue di percorsi
		LinkedList<Stack<GridWorld.Coordinate>> possibleWays = new LinkedList<Stack<GridWorld.Coordinate>>();
		Stack<GridWorld.Coordinate> tempWay, currentWay = new Stack<GridWorld.Coordinate>();
		GridWorld.Coordinate next;
		currentWay.push(world.getCurrentCell());
		possibleWays.addFirst(currentWay);
		mappa.mark(world.getCurrentCell(), Map.State.Found);
		while(!possibleWays.isEmpty()){
			
			//rimuovo il primo percorso dalla coda (ultimo nella lista)
			currentWay = possibleWays.removeLast();	
			
				//Posiziono il robot alla fine di quel percorso
				runWayForward(currentWay);
				Iterator<GridWorld.Coordinate> it = world.getAdjacentFreeCells().iterator();
				while(it.hasNext()){
					
					//per ogni possibile percorso non visitato, aggiungilo alla coda dei percorsi
					//da esaminare
					tempWay = new Stack<GridWorld.Coordinate>();
					tempWay.addAll(currentWay);
					next = it.next();
					if(mappa.value(next) == Map.State.NotFound){
						tempWay.push(next);
						possibleWays.addFirst(tempWay);
						mappa.mark(next, Map.State.Found);
						if(mappa.endWay(next))	return tempWay;	//Se l'ultimo è l'arrivo, termina,
																//il primo che trovo è minimo
					}
				
				}
				
			//Se non ho trovato la fine, sincronizza il robot con la via successiva
			//(che verrà ispezionata la seguente iterazione)
			meetingPointWay(currentWay, possibleWays.getLast());
			mappa.mark(currentWay.peek(), Map.State.Visited);
		}
		
		return null;
	}
	
	
	/*
	 * Basandomi sul fatto che al più toRun.size = toSyncronyze.size -1
	 * per come è fatto l'algoritmo bst
	 * voglio trovare i punti in comune per vedere se è possibile evitare di tornare con 
	 * il robot ogni volta fino a (0,0), ma ritornare solamente fino ad un punto in cui le vie
	 * si incontrano
	 * 
	 */
	private void meetingPointWay(Stack<GridWorld.Coordinate> toWalk, 
			Stack<GridWorld.Coordinate> toSyncronyze){
		/*
		 * Utilizzo i due stack come se fossero due vettori
		 * per non compromettere i dati salvati (facendo pop di parti di percorso)
		 *  evitando di far delle copie
		 */
		GridWorld.Direction move;
		GridWorld.Coordinate meetingPoint = null;
		int i = toWalk.size();
		
		//Trovo il punto d'incontro tra i due vettori
		while(meetingPoint==null){
			i--;	 //all'inizio size-1 perchè l'indice parte da 0
			if(toWalk.elementAt(i).equals(toSyncronyze.elementAt(i)) ||
					toWalk.elementAt(i).equals(toSyncronyze.elementAt(i-1)))
				meetingPoint = toWalk.elementAt(i);
		}
		i=toWalk.size()-1;
		
		//Mi muovo sulla via da percorrere fino al punto d'incontro
		while(!world.getCurrentCell().equals(meetingPoint)){
			move = mappa.findDirection(world.getCurrentCell(), toWalk.elementAt(i));
			if(move != null)
				world.moveToAdjacentCell(move);
			i--;
		}
		
	}

	
	// Percorre una via passante per la posizione iniziale del robot
	private void runWayForward(Stack<GridWorld.Coordinate> way){
		GridWorld.Coordinate position = world.getCurrentCell();
		GridWorld.Direction move;
		GridWorld.Coordinate temp;
		Iterator<GridWorld.Coordinate> it = way.iterator();
		while(it.hasNext())
			if(it.next().equals(position)) break;	//Raggiungi la posizione corrente
		while(it.hasNext()){						//Una volta raggiunta, percorri il percorso
			temp = it.next();
			move = mappa.findDirection(world.getCurrentCell(), temp);
			if(move != null)
				world.moveToAdjacentCell(move);
			
		}
		

	}	
	
	
	/*Stampa su StdDraw il percorso fatto, ho semplicemente utilizzato un vecchio compito*/
	public void printTurtle(LinkedList<GridWorld.Coordinate> percorso, int size){
		Penna draw = new Penna(size);
		Iterator<GridWorld.Coordinate> it = percorso.iterator();
		draw.PenDown();
		GridWorld.Direction dir = GridWorld.Direction.NORTH;
		GridWorld.Coordinate next, prev =it.next();
		while(it.hasNext()){
			next = it.next();
			dir = mappa.findDirection(prev, next);
			switch(dir){
			case NORTH:
				draw.setDegrees(90);
				break;
			case SOUTH:
				draw.setDegrees(270);
				break;
			case EAST:
				draw.setDegrees(0);
				break;
			case WEST:
				draw.setDegrees(180);
				break;
			}
			draw.Move(1, true);
			prev = next;
		}
		draw.clear();
	}
	
	
	
	
//	//------------MAIN DI PROVA
//	public static void main(String[] args){
//		int dim = 50;
//		int i = 0;
//	while(true){
//		for(int j=0;j<6;j++){
//			GridWorld x = new GridWorld(dim, j*2/10,i);
//			Robot prova = new Robot(x, dim);
//			LinkedList<GridWorld.Coordinate> per;
//			per = prova.findMinWay();
//			System.out.println(per);
//			prova.printTurtle(per, dim);
//			boolean ok = x.checkPath(per);
//			System.out.println("Percorso buono: "+ok);
//			System.out.println("Percorso aciclico: "+x.checkPathAcyclic(per));
//			if(!ok) break;
//			System.out.println("Seed: "+i);
//			i++;
//			}
//		}
//	
//	}

}
