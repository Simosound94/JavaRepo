
public class Forchetta {
	public int name;
	private boolean busy;
	
	public Forchetta(int name){
		this.name = name;
	}
	
	public boolean isBusy() {
		return busy;
	}
	
	public void setBusy() {
		this.busy = true;
	}
	
	public void setFree() {
		this.busy = false;
	}
	
	
	
	

}
