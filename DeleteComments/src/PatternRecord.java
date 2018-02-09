public class PatternRecord {
	
	public String world;
	int start, end;
	
	public PatternRecord(String world, int start, int end){
		this.world = world;
		this.start = start;
		this.end = end;
	}
	
	public String toString(){
		return "\n World: "+world + " from: "+start+" to: "+end;
	}
	
	
}
