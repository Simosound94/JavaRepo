
public class Richiesta {
	
	public static enum tipo{
		Prelevamento, Deposito
	};
	
	
	private double amount;
	private tipo tipoRichiesta;

	
	
	public Richiesta(double amount, tipo tipoRichiesta) {
		super();
		this.setAmount(amount);
		this.tipoRichiesta = tipoRichiesta;
	}
	
	
	public tipo getTipoRichiesta() {
		return tipoRichiesta;
	}
	public void setTipoRichiesta(tipo tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
