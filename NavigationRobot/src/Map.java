/**
 * 
 * @author Simone Merello
 * 
 * 	MAP:
 * 	Questa classe gestisce una matrice corrispondente al mondo creato.
 * 	Mette inoltre a disposizione alcuni metodi per facilitare la navigazione
 * 	all'interno del mondo.
 *
 */
public class Map {
	
	public static enum State {Visited, Found, NotFound};
	public State[][] map;
	
	
	public Map(int max){
		map = new State[max][max];
		for(int r=0 ; r<max ; r++)
			for(int c=0 ; c<max ; c++)
				map[r][c]=State.NotFound;
	}
	
	
	//Setta lo stato di una coordinata
	public void mark(GridWorld.Coordinate toMark, State x){
		map[toMark.row][toMark.col] = x;
	}
	
	
	//Ritorna lo stato di una coordinata
	public State value(GridWorld.Coordinate toEvaluate){
		return map[toEvaluate.row][toEvaluate.col];
	}
	
	
	//Date due coordinate adiacenti, trova la direzione da una all'altra
	//Se le celle coincidono ritorna null
	public GridWorld.Direction findDirection(GridWorld.Coordinate start,
			GridWorld.Coordinate end){
	if(start.row < end.row) return GridWorld.Direction.SOUTH;
	if(start.row > end.row) return GridWorld.Direction.NORTH;
	if(start.col < end.col) return GridWorld.Direction.EAST;
	if(start.col > end.col) return GridWorld.Direction.WEST;
	return null;
	}

	
	// Valuta se una coordinata corrisponde all'arrivo
	// (così non devo muovere il robot per saperlo)
	public boolean endWay(GridWorld.Coordinate toEvaluate){
		if(toEvaluate.row == map.length-1 && toEvaluate.col == map.length-1)	return true;
		else																	return false;
	}
	
		
	
	
	public String toString(){
		StringBuffer ris = new StringBuffer();
		for(int r=0 ; r<map.length ; r++){
			for(int c=0 ; c<map.length ; c++){
				switch(map[r][c]){
				case Found:
					ris.append("F ");
					break;
				case NotFound:
					ris.append("N ");
					break;
				case Visited:
					ris.append("V ");
					break;
				}
			}
			ris.append("\n");
		}
		return ris.toString();		
	}
	
	
}
