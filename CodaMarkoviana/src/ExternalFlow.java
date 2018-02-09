
public class ExternalFlow extends Flow {

	public float rate;
	
	public ExternalFlow(CodaMarkoviana in, float rate){
		this.in = in;
		this.rate = rate;
		percentage = 1;
	}

	public boolean update() {
		return in.addClient();
	}

}
