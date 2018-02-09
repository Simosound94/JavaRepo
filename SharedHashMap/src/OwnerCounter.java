
public class OwnerCounter {
	
	private int count;
	
	public OwnerCounter(){
		count = 0;
	}
	
	public synchronized void inc(){
		count++;
	}
	
	public synchronized int getCount(){
		return count;
	}

}
