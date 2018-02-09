package eserc;

public class MacchinaCaffe {
	
	public String tipoCialda;
	public int alimentazione;
	public String tipo;
	
	private boolean accesa;
	
	
	public MacchinaCaffe(String tipoCialda, int alimentazione, String tipo) {
		this.tipoCialda = tipoCialda;
		this.alimentazione = alimentazione;
		this.tipo = tipo;
		this.accesa = false;
	}


	public int getAlimentazione() {
		return alimentazione;
	}


	public void setAlimentazione(int alimentazione) {
		if(alimentazione == 220 || alimentazione == 500)
			this.alimentazione = alimentazione;
	}
	
	public boolean accendi(boolean corrente){
		if(corrente){
			this.accesa = true;
			return true;
		}
		return false;
	}
	

}
