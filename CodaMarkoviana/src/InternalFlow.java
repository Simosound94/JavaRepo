
public class InternalFlow extends Flow {
	
	private CodaMarkoviana other;
	public float percentage;
	
	public InternalFlow(CodaMarkoviana in, CodaMarkoviana other, float probability){
		this.other = other;
		percentage = probability;
		this.in = in;
		
	}

	public boolean update() {
		return other.addClient();
		
	}
}
