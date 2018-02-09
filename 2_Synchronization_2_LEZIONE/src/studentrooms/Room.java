package studentrooms;

public class Room {
	private int number;
	private boolean busy;
	
	public Room(int number) {
		this.number = number;
		this.busy = false;
	}
	
	public void occupy() {
		this.busy = true;
	}
	
	public void free() {
		this.busy = false;
	}
	
	public boolean isBusy() {
		return this.busy;
	}
	
	public int getNumber() {
		return this.number;
	}
}
