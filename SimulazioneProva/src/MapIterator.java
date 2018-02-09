import java.util.ArrayList;
import java.util.Iterator;

public class MapIterator {

	public Map.Coordinate currentPosition;
	public Map toIterate;
	/*
	 * false: non visitati
	 * true: ci sono passato
	 */
	boolean[][] state;
	
	
	
	public MapIterator(Map mappa){
		toIterate = mappa;
		state = new boolean[mappa.maxRow][mappa.maxCol]; /*inizializzati a false di default*/
		currentPosition = mappa.idCoordinate[0];
	}
	
	public void setPosition(Map.Coordinate pos){
		currentPosition = pos;
	}
	
	public Iterator<Map.Coordinate> neighboor(){
		return toIterate.neighboor(currentPosition).iterator();
	}
	
	public void setState(boolean x){
		state[currentPosition.row][currentPosition.col] = x;
		
	}
	public boolean getState(Map.Coordinate x){
		return state[x.row][x.col];
		
	}
	
	public void move(Map.Coordinate toMove){
		currentPosition = toMove;
	}
	
	public Character value(){
		return toIterate.valueAt(currentPosition);
	}
	
	
}
