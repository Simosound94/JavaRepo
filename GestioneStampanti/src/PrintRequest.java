
public class PrintRequest implements Comparable<PrintRequest>{
	
	public String document;
	int priority;
	
	
	@Override
	public String toString() {
		return "Priority: "+priority+" Document: "+document;
	}


	@Override
	public int compareTo(PrintRequest arg0) {
		if(this.priority > arg0.priority){
			return 1;
		}
		else if(this.priority < arg0.priority){
			return -1;
		}
		return 0;
	}
	
	

}
