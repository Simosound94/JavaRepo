import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.TreeMap;

public class Map {
	
		
	public class Coordinate{
		public Integer col,row;
		
		private Coordinate(int r, int c){
			row = r;
			col = c;
		}
		
		public int hashCode(){
			return 17+31*col.hashCode()+127*row.hashCode();
		}
		
		public boolean equals(Object other){
			if(other == this) return true;
			if(!(other instanceof Map)) return false;
			Coordinate oth = (Coordinate) other;
			if(oth.col == this.col && oth.row == this.row)	return true;
			else												return false;
		}
		
		public String toString(){
			return "("+row+","+col+")";
		}
	}
	
		public Hashtable<Coordinate, Character> mappa;
		public int maxCol, maxRow;
		public boolean[] identifiers;
		public Coordinate[] idCoordinate;
		
		
		public Map(){
			mappa = new Hashtable<Coordinate, Character>();
			maxCol  = maxRow = 0;
			identifiers = new boolean[10];
			idCoordinate = new Coordinate[10];
		}
		
		
		
		public void readFile(Scanner in){
			int r = 0;
			int c=0;
			String line;
			char tempCh;
			Coordinate tempCord;
			while(in.hasNext()){
				line = in.nextLine();
				for(c = 0; c<line.length();c++){
					tempCh=line.charAt(c);
					tempCord = new Coordinate(r,c);
					mappa.put(tempCord, tempCh);
					
					if(Character.isDigit(tempCh)){
						identifiers[Character.getNumericValue(tempCh)] = true;
						idCoordinate[Character.getNumericValue(tempCh)] = tempCord;
					}
				}
				r++;
			}
			maxCol = c;
			maxRow = r;
		}
		
		
		public char valueAt(Coordinate x){
			return mappa.get(x);
		}
		
		public ArrayList<Coordinate> neighboor(Coordinate start){
			Character value;
			ArrayList<Coordinate> ris = new ArrayList<Coordinate>(4);
			if(start.row<maxRow-1){
				start.row++; //Sud
				value = valueAt(start);
				if(!value.equals('*')) ris.add(new Coordinate(start.row, start.col));
				start.row--;
			}
			if(start.row>0){
				start.row--; //Nord
				value = valueAt(start);
				if(!value.equals('*')) ris.add(new Coordinate(start.row, start.col));
				start.row++;
			}
			if(start.col>0){
				start.col--; //West
				value = valueAt(start);
				if(!value.equals('*')) ris.add(new Coordinate(start.row, start.col));
				start.col++;
			}
			if(start.col<maxCol-1){
				start.col++;
				value = valueAt(start);
				if(!value.equals('*')) ris.add(new Coordinate(start.row, start.col));
				start.col--;
			}
			return ris;
		}
		
		

}
