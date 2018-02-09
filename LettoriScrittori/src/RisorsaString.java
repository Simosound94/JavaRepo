public class RisorsaString implements ResourceInterface<String> {
	
	private String ris;
	
	public RisorsaString(String ris){
		this.ris = ris;
	}
	
	@Override
	public ResourceInterface clone() {
		return new RisorsaString(this.ris);
	}

	@Override
	public void writeValue(String newValue) {
		this.ris = newValue;
	}

}
