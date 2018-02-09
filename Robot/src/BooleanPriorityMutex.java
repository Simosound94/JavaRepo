
public class BooleanPriorityMutex {
	
	private boolean value;

	
	
	public BooleanPriorityMutex(boolean value) {
		super();
		this.value = value;
	}


	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
